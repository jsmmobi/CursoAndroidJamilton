package br.com.bitocean.alertdialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnAlert;
    private AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnAlert = (Button) findViewById(R.id.btnAlert);
        btnAlert.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAlert:
                showAlert();
                break;
        }
    }

    private void showAlert() {
        builder = new AlertDialog.Builder(MainActivity.this);

        //Inpede o fechamento do dialog ao clicar fora dos botões de ações
        builder.setCancelable(false);


        builder.setTitle("Titulo do Dialog")
                .setMessage("Mensagem do Dialog")
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                         showToast("Não");
                    }
                })
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showToast("Sim");
                    }
                })
                .setIcon(android.R.drawable.btn_star)
                .create();
        builder.show();


    }


    private void showToast(String text){
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
    }
}
