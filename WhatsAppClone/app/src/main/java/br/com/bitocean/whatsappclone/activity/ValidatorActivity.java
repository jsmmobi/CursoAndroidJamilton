package br.com.bitocean.whatsappclone.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import br.com.bitocean.whatsappclone.R;
import br.com.bitocean.whatsappclone.util.Preferences;

public class ValidatorActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtCodigo;
    private Button btnValidar;

    private SimpleMaskFormatter smf = new SimpleMaskFormatter("NNNN");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validator);

        getSupportActionBar().hide();
        
        edtCodigo = (EditText)findViewById(R.id.edtCodigo);
        btnValidar = (Button) findViewById(R.id.btnValidar);

        btnValidar.setOnClickListener(this);

        MaskTextWatcher maskCodiPais = new MaskTextWatcher(edtCodigo,smf);
        edtCodigo.addTextChangedListener(maskCodiPais);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnValidar){
            validarCodigo();
        }
    }

    private void validarCodigo() {
       String tokenGerado = Preferences.getInstance(this).getString(Preferences.KEY_TOKEN);
       String tokenDigitado = edtCodigo.getText().toString();

       if(tokenGerado.equals(tokenDigitado)){
           Toast.makeText(this,"Token Validado",Toast.LENGTH_LONG).show();
       }else{
           Toast.makeText(this,"Token Errado",Toast.LENGTH_LONG).show();
       }
    }
}
