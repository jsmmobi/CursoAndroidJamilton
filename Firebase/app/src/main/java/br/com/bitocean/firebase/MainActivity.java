package br.com.bitocean.firebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();



        //salvar();

        listar();

    }

    public void listar(){
         reference.child("Usuario").addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                      //List<Usuario> usuarios = (List<Usuario>) dataSnapshot.getValue();

                      for(DataSnapshot ds: dataSnapshot.getChildren()){
                          Log.i("FIREBASE","XX"+ds);
                      }



             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {
                 Log.i("FIREBASE","XX"+databaseError.getDetails());
             }
         });
    }

    private void salvar(){


        String key = UUID.randomUUID().toString();
        Usuario usuario = new Usuario();
        usuario.setIdade(40)
                .setNome("Adriana Juvenal da Silva")
                .setSexo("F")
                .setSobrenome("da Silva")
                .setObjectId(key);
        //reference.child("Usuario").setValue(key);
        reference.child("Usuario").child(key).setValue(usuario);
    }
}
