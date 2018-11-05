package br.com.bitocean.whatsappclone.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

import br.com.bitocean.whatsappclone.R;
import br.com.bitocean.whatsappclone.adapter.TabAdapter;
import br.com.bitocean.whatsappclone.config.FirebaseConfig;
import br.com.bitocean.whatsappclone.model.Contato;
import br.com.bitocean.whatsappclone.model.Usuario;
import br.com.bitocean.whatsappclone.widget.SlidingTabLayout;

public class PrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth auth;
    private SearchView searchView;

    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;

    private DatabaseReference ref;

    private ProgressDialog dialog;
    private Usuario contato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setElevation(0);

        auth = FirebaseConfig.getAuth();
        ref = FirebaseConfig.getReference();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        //.setAction("Action", null).show();
                adicionarUsuario();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        slidingTabLayout = (SlidingTabLayout)findViewById(R.id.stl_tabs);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this,R.color.colorAccent));
        viewPager = (ViewPager)findViewById(R.id.view_pager);

        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        slidingTabLayout.setViewPager(viewPager);


    }

    private void adicionarUsuario() {

        final EditText  input = new EditText(this);
        input.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_input_add)
                .setCancelable(false)
                .setTitle("Novo contato")
                .setMessage("Email do contato")
        .setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                   adicionarContato(input.getText().toString().trim());
            }
        })

        .setNegativeButton("Cancelar",null)
                .setView(input)
        .create()
        .show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id){
            case R.id.nav_logout:
                logout();
                break;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logout() {
        auth.signOut();
        Intent intent = new Intent(this,LoginEmailActivity.class);
        startActivity(intent);
        finish();
    }


    private void adicionarContato(String email){

        if(email.isEmpty()){
            Toast.makeText(this,"Preencha o email",Toast.LENGTH_LONG).show();
            return;
        }

        dialog = ProgressDialog.show(this,"Aguarde!!!","Adicionando contato",true);

        ref.child(Usuario.NODE)
                .orderByChild(Usuario.PROP_EMAIL)
                .equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.getValue() !=null){

                    for(DataSnapshot ds:dataSnapshot.getChildren()){
                        contato = (Usuario) ds.getValue(Usuario.class);
                   }
                    //Toast.makeText(PrincipalActivity.this,contato.toString(),Toast.LENGTH_LONG).show();
                    ref.child(Contato.NODE)
                            .child(auth.getCurrentUser().getUid())
                            .child(contato.getObjectId())
                            .setValue(contato.usuarioToContato())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    dialog.dismiss();
                                    if(task.isSuccessful()){
                                        Toast.makeText(PrincipalActivity.this,"Contato acidionado com sucesso!",Toast.LENGTH_LONG).show();
                                    }else{
                                        Toast.makeText(PrincipalActivity.this,"Erro ao adicionar o contato. ",Toast.LENGTH_LONG).show();
                                    }


                                }
                            });
                }else{
                    dialog.dismiss();
                    Toast.makeText(PrincipalActivity.this,"Usuário não encontrado",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                   dialog.dismiss();
            }
        });
    }
}
