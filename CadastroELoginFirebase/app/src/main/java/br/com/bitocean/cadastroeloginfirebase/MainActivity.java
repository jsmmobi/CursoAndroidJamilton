package br.com.bitocean.cadastroeloginfirebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        logout();

        login("jsilva.moises@gmail.com","123456");
        if(isLogado()){
            Toast.makeText(this,"Usuario Logado....",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Usuario deslogado :)....",Toast.LENGTH_LONG).show();
        }
    }

    private void cadastrar(String email, String password){
       auth.createUserWithEmailAndPassword(email,password)
       .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful()){
                   Toast.makeText(MainActivity.this,"Cadastrado com sucesso.",Toast.LENGTH_LONG).show();
               }else{
                   Toast.makeText(MainActivity.this,"Erro ao cadastrar."+task.getException().getMessage(),Toast.LENGTH_LONG).show();
               }
           }
       });
    }

    private void login(String email,String senha){
        auth.signInWithEmailAndPassword(email,senha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Logado com sucesso.",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(MainActivity.this,"Erro ao logar."+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    private boolean isLogado(){
       return auth.getCurrentUser() !=null;
    }

    private void logout(){
        auth.signOut();
    }
}
