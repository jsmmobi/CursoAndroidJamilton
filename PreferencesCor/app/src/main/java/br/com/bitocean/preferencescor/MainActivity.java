package br.com.bitocean.preferencescor;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RadioButton rbAzul,rbLaranja,rbVerde;
    private RadioGroup radioGroup;
    private Button btSalvar;

    private static final String ARQUIVO_PREFERENCES="MyPreferences";
    private static final String KEY_COLOR="KeyColor";

    private RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = (RadioGroup)findViewById(R.id.radioGroup) ;

        rbAzul = (RadioButton)findViewById(R.id.rbAzul);
        rbLaranja = (RadioButton)findViewById(R.id.rbLaranja);
        rbVerde = (RadioButton)findViewById(R.id.rbVerde);

        layout = (RelativeLayout)findViewById(R.id.layoutId) ;

        btSalvar = (Button)findViewById(R.id.btSalvar);
        btSalvar.setOnClickListener(this);

        changeBackgroundColor();

    }

    @Override
    public void onClick(View v) {
       int id = radioGroup.getCheckedRadioButtonId();
       String cor = "";
       switch (id){
           case R.id.rbAzul:
               cor = (String)findViewById(R.id.rbAzul).getTag();
               break;
           case R.id.rbLaranja:
               cor = (String)findViewById(R.id.rbLaranja).getTag();
               break;
           case R.id.rbVerde:
               cor = (String)findViewById(R.id.rbVerde).getTag();
               break;
       }

       putString(KEY_COLOR,cor.trim());
       changeBackgroundColor();
    }

    private void changeBackgroundColor(){
        String cor = getString(KEY_COLOR);
        if(!cor.isEmpty()){
            layout.setBackgroundColor(Color.parseColor(cor));
        }
    }

    private void putString(String key,String value){
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCES,0);//o = privado
        SharedPreferences.Editor editor = preferences.edit();
        if(value.isEmpty()){
            Toast.makeText(this,"Verifique o valor",Toast.LENGTH_SHORT).show();
            return;
        }

        editor.putString(key,value);
        editor.commit();
    }

    private String getString(String key){
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCES,0);
        if(preferences.contains(key)){
            String value = preferences.getString(key,"");
            return value.trim();
        }

        return "";
    }
}
