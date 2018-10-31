package br.com.bitocean.minhasanotacoes;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtTexto;
    private ImageView ivSalvar;
    private static final String FILE_NAME="anotacao.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       initComponents();

       carregarArquivo();
    }

    private void carregarArquivo(){
        String conteudo = readFile(FILE_NAME);
        edtTexto.setText(conteudo);
    }

    private void initComponents() {
            edtTexto = (EditText)findViewById(R.id.edtTexto);
            ivSalvar = (ImageView)findViewById(R.id.ivSalvar);
            ivSalvar.setOnClickListener(MainActivity.this);

    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.ivSalvar:
                 salvar();
                 break;

         }
    }

    private void salvar() {
        String texto = edtTexto.getText().toString();
        gravarNoArquivo(texto);
    }

    private void gravarNoArquivo(String texto) {
        Log.i(getClass().getSimpleName(),"Gravando arquivo.");
        try {
            OutputStreamWriter writer = new OutputStreamWriter(openFileOutput(FILE_NAME,Context.MODE_PRIVATE));
            writer.write(texto);
            writer.flush();
            writer.close();
            Log.i(getClass().getSimpleName(),"Arquivo gravado com sucesso!.");
            Toast.makeText(getApplicationContext(),"Gravado com sucesso!",Toast.LENGTH_SHORT).show();
        }catch (IOException e){
            Log.v(getClass().getSimpleName(),e.toString());
        }
    }

    public String readFile(String fileName)  {
        Log.i(getClass().getSimpleName(),"Iniciando Leitura do Arquivo.");
        StringBuilder conteudo=new StringBuilder();
        InputStream arquivo = null;
        try {
            //Abrir arquivo
            arquivo = openFileInput(fileName);
            if(arquivo !=null){
                //Ler arquivo
                InputStreamReader reader = new InputStreamReader(arquivo);
                //Gerar buffer do arquivo lido
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line="";
                while((line = bufferedReader.readLine())!=null){
                    conteudo.append(line);
                }
            }

            if(arquivo !=null){
                arquivo.close();
                Log.i(getClass().getSimpleName(),"Arquivo carregado.");
            }

        }catch (IOException e){
            Log.v(getClass().getSimpleName(),e.toString());
        }
        return conteudo.toString();
    }
}
