package br.com.bitocean.whatsappclone.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import br.com.bitocean.whatsappclone.R;
import br.com.bitocean.whatsappclone.config.FirebaseConfig;
import br.com.bitocean.whatsappclone.model.Usuario;

public class LoginEmailActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvCadastreSe;
    private EditText edtEmail,edtSenha;
    private Button btnLogin;

    private DatabaseReference reference;

    private Usuario usuario;

    private FirebaseAuth auth;

    private ProgressDialog dialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_email);
        getSupportActionBar().hide();

        reference = FirebaseConfig.getReference();
        auth = FirebaseConfig.getAuth();

        isLogado();
        init();

    }

    private void init() {
        tvCadastreSe = (TextView)findViewById(R.id.tvCadastreSe);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtSenha = (EditText)findViewById(R.id.edtSenha);
        btnLogin = (Button)findViewById(R.id.btnLogin);

        tvCadastreSe.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    private void isLogado(){
       if(auth.getCurrentUser() !=null){
           Intent intent = new Intent(this,PrincipalActivity.class);
           startActivity(intent);
           finish();
       }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tvCadastreSe:
                novoRegistro();
                break;
            case R.id.btnLogin:
                login();
                break;
        }
    }
    public void novoRegistro(){
        Intent intent = new Intent(this,CadastroActivity.class);
        startActivity(intent);

    }
    private void login() {
        String email = edtEmail.getText().toString().trim();
        String senha = edtSenha.getText().toString().trim();

        if(email.isEmpty() || senha.isEmpty()){
            showToast("Preencha email e senha");
            return;
        }
        dialog = ProgressDialog.show(this,"Aguarde!!!","Autenticando usuário",true);



        usuario = new Usuario(email,senha);

        validarLogin();
    }

    private void validarLogin() {
        auth.signInWithEmailAndPassword(usuario.getEmail(),usuario.getSenha())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            dialog.dismiss();
                            showToast("Login efetuado com sucesso!");
                            Intent intent =new Intent(LoginEmailActivity.this,PrincipalActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            dialog.dismiss();
                            showToast("Erro: "+task.getException().getMessage());
                        }
                    }
                });
    }


    private void showToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
