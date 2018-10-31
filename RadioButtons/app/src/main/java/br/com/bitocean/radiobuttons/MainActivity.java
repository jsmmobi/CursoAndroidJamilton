package br.com.bitocean.radiobuttons;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioButton rbLasanha,rbBife,rbMacarao;
    private Button btnSalvar;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
    }

    private void initComponents() {
        rbBife = (RadioButton)findViewById(R.id.rbBife);
        rbLasanha = (RadioButton)findViewById(R.id.rbLasanha);
        rbMacarao = (RadioButton)findViewById(R.id.rbMacarrao);

        btnSalvar = (Button)findViewById(R.id.btSalvar);
        btnSalvar.setOnClickListener(this);

        tvResultado = (TextView)findViewById(R.id.tvResultado);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btSalvar:
                salvar();
                break;

        }
    }

    private void salvar() {
        StringBuilder msg = new StringBuilder();
        RadioButton[] rbs = {rbMacarao,rbLasanha,rbBife};
        for(RadioButton rb:rbs){
            if(rb.isChecked()){
                msg.append(rb.getText()).append("\n");
                break;
            }
        }

        tvResultado.setText("Você gosta de: "+msg);
    }
}
