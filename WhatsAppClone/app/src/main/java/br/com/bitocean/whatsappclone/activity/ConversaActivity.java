package br.com.bitocean.whatsappclone.activity;

import android.content.Intent;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import br.com.bitocean.whatsappclone.R;
import br.com.bitocean.whatsappclone.adapter.MensagemAdapter;
import br.com.bitocean.whatsappclone.config.FirebaseConfig;
import br.com.bitocean.whatsappclone.model.Contato;
import br.com.bitocean.whatsappclone.model.Conversa;
import br.com.bitocean.whatsappclone.model.Mensagem;
import br.com.bitocean.whatsappclone.model.Usuario;

public class ConversaActivity extends AppCompatActivity {
    public static final String PARAM_CONTATO="param_contato";

    private Contato contato;
    private ListView listView;
    private ImageView ivSend;
    private EditText edtMensagem;
    private String idUsuarioLogado;
    private DatabaseReference reference;
    private ArrayList<Mensagem> conversas;
    private ArrayAdapter<Mensagem> adapter;


    private ValueEventListener valueEventListenerMensagem;

    private ValueEventListener valueEventListenerConversa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversa);

        Bundle extras = getIntent().getExtras();

        if(extras !=null){
            contato = (Contato) extras.getSerializable(PARAM_CONTATO);
            getSupportActionBar().setTitle(contato.getNome());
        }

        idUsuarioLogado = FirebaseConfig.idUsuarioLogado();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (ListView)findViewById(R.id.listView);
        ivSend = (ImageView)findViewById(R.id.ivSend);
        ivSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviar();
            }
        });
        edtMensagem = (EditText)findViewById(R.id.edtMensagem);

        conversas = new ArrayList<>();

        /*adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                conversas
        );*/
        adapter = new MensagemAdapter(this,conversas);
        listView.setAdapter(adapter);


        recuperarMensagens();

    }

    @Override
    protected void onStop() {
        super.onStop();
        reference.removeEventListener(valueEventListenerMensagem);
    }

    private void recuperarMensagens(){
        reference = FirebaseConfig.getReference()
        .child(Mensagem.NODE)
        .child(idUsuarioLogado)
        .child(contato.getObjectId());
        getValueEventListenerMensagem();
        reference.addValueEventListener(valueEventListenerMensagem);

    }



    private ValueEventListener getValueEventListenerMensagem(){
        valueEventListenerMensagem = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                  conversas.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                      Mensagem mensagem = ds.getValue(Mensagem.class);
                      conversas.add(mensagem);
                  }

                  adapter.notifyDataSetChanged();
                //listView.scrollListBy(conversas.size()-1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        return valueEventListenerMensagem;
    }

    private void enviar() {
        String mensagem = edtMensagem.getText().toString();
        if(mensagem.isEmpty()){
            return;
        }

        Mensagem msg = new Mensagem();
        msg.setIdClient(contato.getObjectId())
                .setIdSender(idUsuarioLogado)
                .setTextMessage(mensagem);
        
        salvarMensagem(msg);
    }

    private boolean salvarMensagem(Mensagem msg ) {


        try {
            reference = FirebaseConfig.getReference()
                    .child(Mensagem.NODE);
            String messageKey = reference
                    .child(msg.getIdSender())
                    .child(msg.getIdClient())
                    .push().getKey();
            msg.setObjectId(messageKey);
            // Salva mensagem para usuario que enviou
            reference
                    .child(msg.getIdSender())
                    .child(msg.getIdClient())
                    .child(messageKey)
                    .setValue(msg).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        edtMensagem.setText("");
                    }else{
                        Toast.makeText(ConversaActivity.this,"Erro: "+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            });
            // Salva mensagem para usuario de destino
            reference
                    .child(msg.getIdClient())
                    .child(msg.getIdSender())
                    .child(messageKey)
                    .setValue(msg).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        edtMensagem.setText("");
                    }else{
                        Toast.makeText(ConversaActivity.this,"Erro: "+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            });

            // Salvando ultima conversa
            salvarConversa(msg);



            return true;
        }catch (Exception e){
            Log.e(getClass().getSimpleName(),e.getMessage());
            return false;
        }
    }

    private Conversa conversa;
    private boolean salvarConversa(Mensagem mensagem){
        //String idUsuario, String nome, Mensagem mensagem
        conversa = new Conversa();
        conversa.setIdUsuario(contato.getObjectId())
                .setNome(contato.getNome())
                .setMensagem(mensagem);
        try{
            // Salva conversa para o remetente
            reference = FirebaseConfig.getReference().child(Conversa.NODE);
            reference.child(idUsuarioLogado)
                    .child(contato.getObjectId())
                    .setValue(conversa);

            // Salva a mensagem para o destinatário
            DatabaseReference referenceUsuario = FirebaseConfig.getReference().child(Usuario.NODE);
            referenceUsuario.child(idUsuarioLogado).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Contato contato = dataSnapshot.getValue(Contato.class);
                    //Toast.makeText(ConversaActivity.this,contato.toString(),Toast.LENGTH_LONG).show();
                    conversa.setIdUsuario(idUsuarioLogado).setNome(contato.getNome());

                    reference = FirebaseConfig.getReference().child(Conversa.NODE);
                    reference.child(contato.getObjectId())
                            .child(idUsuarioLogado)
                            .setValue(conversa);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
