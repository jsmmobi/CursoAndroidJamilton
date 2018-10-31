package br.com.bitocean.sharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText edtNome;
    private Button btSalvar;
    private TextView tvResult;

    private static final String ARQUIVO_PREFERENCES="MyPreferences";
    private static final String KEY_NOME="KeyNome";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edtNome = (EditText)findViewById(R.id.edtNome);
        btSalvar = (Button)findViewById(R.id.btSalvar);
        tvResult = (TextView)findViewById(R.id.tvResult);
        btSalvar.setOnClickListener(this);
        updateLabel();
    }

    @Override
    public void onClick(View v) {
        putString(KEY_NOME,edtNome.getText().toString());
        updateLabel();
        edtNome.setText("");
    }

    private void updateLabel(){
        tvResult.setText("Olá,"+getString(KEY_NOME));
    }

    private void putString(String key,String value){
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCES,0);//o = privado
        SharedPreferences.Editor editor = preferences.edit();
        if(value.isEmpty()){
            Toast.makeText(this,"Verifique o campo nome",Toast.LENGTH_SHORT).show();
            return;
        }

        editor.putString(key,value);
        editor.commit();
    }

    private String getString(String key){
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCES,0);
        if(preferences.contains(key)){
            String value = preferences.getString(key,"");
            return value;
        }

        return "";
    }
}
