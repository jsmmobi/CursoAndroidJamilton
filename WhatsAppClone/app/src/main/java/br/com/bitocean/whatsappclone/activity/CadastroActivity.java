package br.com.bitocean.whatsappclone.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import br.com.bitocean.whatsappclone.R;
import br.com.bitocean.whatsappclone.config.FirebaseConfig;
import br.com.bitocean.whatsappclone.model.Usuario;

public class CadastroActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtNome,edtEmail,edtEmailConfirme,edtSenha;
    private Button btnCadastrar;

    private Usuario usuario;
    private FirebaseAuth auth;

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().hide();

        auth = FirebaseConfig.getAuth();

        init();
    }

    private void init() {
        edtNome = (EditText)findViewById(R.id.edtNome);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtEmailConfirme = (EditText)findViewById(R.id.edtEmailConfirme);
        edtSenha = (EditText)findViewById(R.id.edtSenha);

        btnCadastrar = (Button)findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(this);
    }

    private boolean isEmailEquals(EditText email1,EditText email2){
        return email1.getText().toString().trim().equals(email2.getText().toString().trim());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCadastrar:
                cadastrar();
             break;
        }
    }

    private void cadastrar() {
        if(!isEmailEquals(edtEmail,edtEmailConfirme)){
            showToast("Email não estão iguais");
            return;
        }

        if(isEmpty(edtEmail,edtEmailConfirme,edtNome,edtSenha)){
            showToast("Verifique se todos os campos estão preenchidos");
            return;
        }

        usuario = new Usuario();
        usuario.setEmail(edtEmail.getText().toString().trim())
                .setNome(edtNome.getText().toString().trim())
                .setSenha(edtSenha.getText().toString().trim());
        castrarUsuario();
    }

    private void castrarUsuario() {
        dialog = ProgressDialog.show(this,"Aguarde!!!","Registrando usuário",true);

        auth.createUserWithEmailAndPassword(usuario.getEmail(),usuario.getSenha())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            dialog.setMessage("Login criado com sucesso!");
                            String objectId = task.getResult().getUser().getUid();
                            usuario
                             .setObjectId(objectId)
                             .setProviderId(task.getResult().getUser().getProviderId());
                             usuario.salvar(CadastroActivity.this);
                             dialog.setMessage("Cadastro finalizado com sucesso!");
                             //showToast("Cadastrado com sucesso!"+objectId);
                             abrirUsuarioLogago();
                             dialog.dismiss();
                             finish();
                        }else{
                            dialog.dismiss();
                            String erro;
                            try {
                                throw task.getException();
                            }catch (FirebaseAuthWeakPasswordException e){
                                erro = "Digite uma senha mais forte com mais de 6 caracteres";
                            }catch (FirebaseAuthInvalidCredentialsException e){
                                erro = "O email digitado é inválido.";
                            }catch (FirebaseAuthUserCollisionException e){
                                erro = "Email já cadastrado.";
                            }catch (Exception e){
                                erro = e.getMessage();
                                e.printStackTrace();
                            }
                            showToast("Erro ao cadastrar usuário: Erro -> "+erro);
                        }
                    }
                });
    }

    private void abrirUsuarioLogago() {
        Intent intent = new Intent(this,LoginEmailActivity.class);
        startActivity(intent);
    }


    private boolean isEmpty(EditText... editTexts){
        for(EditText e:editTexts){
            if (e.getText().toString().trim().isEmpty()){
                return true;
            }
        }
        return false;
    }

    private void showToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
