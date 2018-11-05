package br.com.bitocean.whatsappclone.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import br.com.bitocean.whatsappclone.R;
import br.com.bitocean.whatsappclone.util.AndroidPermissions;
import br.com.bitocean.whatsappclone.util.Preferences;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtNome, edtDDI,edtDDD,edtNumero;
    private Button btnCadastrar;
    private SimpleMaskFormatter smf = new SimpleMaskFormatter("N NNNN NNNN");
    private SimpleMaskFormatter smfCodiPais = new SimpleMaskFormatter("+NN");
    private SimpleMaskFormatter smfDDD = new SimpleMaskFormatter("NN");

    private String[] permissoes = {
            Manifest.permission.SEND_SMS,
            Manifest.permission.INTERNET
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        AndroidPermissions.validaPermissoes(this,permissoes,1);


        init();
    }



    private void init() {
        edtDDD = (EditText)findViewById(R.id.edtDDD);
        edtNome = (EditText)findViewById(R.id.edtNome);
        edtDDI = (EditText)findViewById(R.id.edtDDI);
        edtNumero = (EditText)findViewById(R.id.edtNumero);
        btnCadastrar = (Button)findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(this);

        MaskTextWatcher mask = new MaskTextWatcher(edtNumero,smf);
        edtNumero.addTextChangedListener(mask);

        MaskTextWatcher maskCodiPais = new MaskTextWatcher(edtDDI,smfCodiPais);
        edtDDI.addTextChangedListener(maskCodiPais);

        MaskTextWatcher maskDDD = new MaskTextWatcher(edtDDD,smfDDD);
        edtDDD.addTextChangedListener(maskDDD);

        Log.i("TOKEN",token());
    }

    @Override
    public void onClick(View view) {
           switch (view.getId()){
               case R.id.btnCadastrar:
                   String nome = edtNome.getText().toString();
                   String ddi = edtDDI.getText().toString();
                   String ddd = edtDDD.getText().toString();
                   String numero = edtNumero.getText().toString();

                   StringBuilder numeroCompleto= new StringBuilder();
                   numeroCompleto.append(ddi).append(ddd).append(numero);
                   String telFinal = numeroCompleto.toString();

                   telFinal = telFinal.replace("+","");
                   telFinal = telFinal.replace(" ","");
                   Log.i("TELEFONE",telFinal);

                   String token  = token();

                   Map<String,String> map = new HashMap<>();
                   map.put(Preferences.KEY_TOKEN,token);
                   map.put(Preferences.KEY_NOME,nome);
                   map.put(Preferences.KEY_TELEFONE,telFinal);

                   Preferences pref = Preferences.getInstance(this);
                   pref.putString(map);
                   pref.printMap();

                   // Evia SMS
                   boolean enviado = enviaSMS(telFinal,"TOKEN: "+token);

                   if(enviado){
                       Intent intent = new Intent(this,ValidatorActivity.class);
                       startActivity(intent);
                       finish();
                   }else{
                       Toast.makeText(this,"Erro ao enviar o SMS",Toast.LENGTH_LONG).show();
                   }

                   break;
           }
    }

    private boolean enviaSMS(String telefone,String mensagem){

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("+"+telefone,null,mensagem,null,null);
            return true;
        }catch (Exception e){
            e.printStackTrace();

        }

        return false;
    }

    private String token(){
        Random random = new Random();
        int numero=random.nextInt(9999-1000)+1000;
        return String.valueOf(numero);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        for(int resultado:grantResults){
            if(resultado == PackageManager.PERMISSION_DENIED){
                alertaValidacaoPermissao();
                break;
            }
        }
    }

    private void alertaValidacaoPermissao() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false)
                .setTitle("Permissões negadas")
                .setMessage("Para utilizar o aplicativo é nessario aceitar as permissões")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
        .create()
        .show();
    }
}
