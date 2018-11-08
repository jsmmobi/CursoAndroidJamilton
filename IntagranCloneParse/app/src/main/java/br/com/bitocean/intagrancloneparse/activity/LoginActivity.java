package br.com.bitocean.intagrancloneparse.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import br.com.bitocean.intagrancloneparse.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtUsuario,edtSenha;
    private Button btLogar;
    private TextView tvCrieConta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        virificarLogado();
        init();

    }

    private void virificarLogado(){
        if(ParseUser.getCurrentUser() !=null){
            callMain();
        }
    }

    private void init() {
        edtSenha = (EditText)findViewById(R.id.edtSenha);
        edtUsuario = (EditText)findViewById(R.id.edtUsuario);

        btLogar = (Button)findViewById(R.id.btLogar);
        btLogar.setOnClickListener(this);

        tvCrieConta = (TextView)findViewById(R.id.tvCrieConta);
        tvCrieConta.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tvCrieConta:
                criarConta();
                break;
            case R.id.btLogar:
                logar();
                break;
        }
    }
    private void criarConta(){
        Intent intent = new Intent(this,CadastroActivity.class);
        startActivity(intent);
    }

    private void callMain(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void logar() {

        EditText[] edts = {edtUsuario,edtSenha};
        for(EditText e: edts){
            if(e.getText().toString().isEmpty()){
                showToast("Preencha usuário e senha");
                return;
            }

        }
        final ProgressDialog dialog = ProgressDialog.show(this,"Aguarde!!!","Fazendo Login",true);
        ParseUser.logInInBackground(edtUsuario.getText().toString(), edtSenha.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e == null){
                    virificarLogado();
                    dialog.dismiss();
                }else{
                    dialog.dismiss();
                    Toast.makeText(LoginActivity.this,"Erro: "+e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showToast(String message){
        Toast.makeText(LoginActivity.this,"Erro: "+message,Toast.LENGTH_SHORT).show();
    }
}
