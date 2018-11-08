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

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import br.com.bitocean.intagrancloneparse.R;

public class CadastroActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtUsuario, edtEmail,edtSenha;
    private Button btCadastrar;
    private TextView tvFacaLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().hide();
        
        init();
    }

    private void init() {
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtSenha = (EditText)findViewById(R.id.edtSenha);
        edtUsuario = (EditText)findViewById(R.id.edtUsuario);
       
        btCadastrar = (Button)findViewById(R.id.btCadastrar);
        btCadastrar.setOnClickListener(this);
        
        tvFacaLogin = (TextView)findViewById(R.id.tvFacaLogin);
        tvFacaLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btCadastrar:
                cadastrar();
                break;
            case R.id.tvFacaLogin:
                finish();
                break;
        }
    }

    private void cadastrar() {

        EditText edits[] = {edtUsuario,edtSenha,edtEmail};
        for(EditText e:edits){
            if(e.getText().toString().trim().isEmpty()){
                Toast.makeText(this,"Usuário,Email e senha são obrigatórios",Toast.LENGTH_LONG).show();
                return;
            }
        }
        final ProgressDialog dialog = ProgressDialog.show(this,"Aguarde!!!","Cadastrando usuário",true);
        if(ParseUser.getCurrentUser() !=null){
            ParseUser.logOut();
        }

        ParseUser user = new ParseUser();
        user.setPassword(edtSenha.getText().toString());
        user.setUsername(edtUsuario.getText().toString());
        user.setEmail(edtEmail.getText().toString());

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    dialog.dismiss();
                    showToast("Salvo com sucesso!");
                    callLogin();
                }else{
                    dialog.dismiss();
                    showToast("Erro: "+e.getMessage());
                }
            }
        });
    }

    private void callLogin(){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void showToast(String message){
        Toast.makeText(CadastroActivity.this,message,Toast.LENGTH_LONG).show();

    }
}
