package br.com.bitocean.intagrancloneparse.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import br.com.bitocean.intagrancloneparse.R;
import br.com.bitocean.intagrancloneparse.adapter.TabsAdapter;
import br.com.bitocean.intagrancloneparse.fragment.HomeFragment;
import br.com.bitocean.intagrancloneparse.widget.SlidingTabLayout;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private SlidingTabLayout stlTabs;
    private TabsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.instagramlogo);

        viewPager = (ViewPager)findViewById(R.id.viewPager);
        stlTabs = (SlidingTabLayout) findViewById(R.id.stlTabs);

        adapter = new TabsAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(adapter);


        stlTabs.setCustomTabView(R.layout.tab_view,R.id.tvItemTab);
        stlTabs.setDistributeEvenly(true);
        stlTabs.setViewPager(viewPager);


    }


    public void logout(){
        if(ParseUser.getCurrentUser()!=null){
            ParseUser.logOut();
            callLogin();
        }
    }

    private void callLogin() {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_sair:
                logout();
                return true;
            case R.id.action_configuracoes:
                return true;
            case R.id.action_compartilhar:
                compartilharFoto();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }

    }

    private void compartilharFoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK && data != null){
            //recupera local do recurso
            Uri localImageSelecionada = data.getData();
            Bitmap image;
            try {
                // recupera imagem do local que foi selecionada
                image = MediaStore.Images.Media.getBitmap(getContentResolver(),localImageSelecionada);
                // comprime em png
                final ByteArrayOutputStream stream = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.PNG,70,stream);

                byte[] byteArray = stream.toByteArray();

                // Criar imagem com formato para o parse
                ParseFile parseFile = new ParseFile(UUID.randomUUID().toString()+".png",stream.toByteArray());
                ParseObject parseObject = new ParseObject("Image");
                parseObject.put("iserId",ParseUser.getCurrentUser().getObjectId());
                parseObject.put("image",parseFile);

                //Salvar os dados
                parseObject.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null){
                            showToast("Salvo com sucesso!");
                            updateFragment(0);
                        }else{
                             showToast(e.getMessage());
                        }
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateFragment(int position) {
        //TabsAdapter tabsAdapter = (TabsAdapter)viewPager.getAdapter();
        HomeFragment frag = (HomeFragment) adapter.getFragment(position);
        frag.listarPostagens();
    }


    private void showToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
