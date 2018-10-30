package br.com.bitocean.checkbox;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CheckBox checkBoxCao, checkBoxGato,checkBoxPapagaio;
    private Button btnMostrar;
    private TextView tvResultado;
    private Context self;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.self = this;

        initComponents();
    }

    private void initComponents() {
        checkBoxCao = (CheckBox)findViewById(R.id.checkBoxCão);
        checkBoxGato = (CheckBox)findViewById(R.id.checkBoxGato);
        checkBoxPapagaio = (CheckBox)findViewById(R.id.checkBoxPapagario);



         btnMostrar = (Button)findViewById(R.id.btnMostrar);
         btnMostrar.setOnClickListener(this);

        tvResultado = (TextView)findViewById(R.id.tvResultado);
    }

    @Override
    public void onClick(View v) {
         StringBuilder msg = new StringBuilder();
         if(checkBoxCao.isChecked()){
             msg.append("Cão selecionado\n");
         }

         if(checkBoxPapagaio.isChecked()){
             msg.append("Papagaio selecionado\n");
         }

        if(checkBoxGato.isChecked()){
            msg.append("Gato selecionado\n");
        }

        tvResultado.setText(msg);
    }
}
