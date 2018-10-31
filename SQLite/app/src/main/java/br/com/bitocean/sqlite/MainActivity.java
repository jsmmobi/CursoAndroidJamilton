package br.com.bitocean.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase banco = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            banco = openOrCreateDatabase("app", MODE_PRIVATE, null);
            // cria tabela
            banco.execSQL("CREATE TABLE IF NOT EXISTS pessoa(nome VARCHAR(100),idade INT(3))");

            // INSERE REGISTRO
            //banco.execSQL("INSERT INTO pessoa(nome,idade) VALUES('MOISÉS DA SILVA',36)");
            //banco.execSQL("INSERT INTO pessoa(nome,idade) VALUES('MARIA DE SILVA',20)");

            Cursor cursor = banco.rawQuery("SELECT * FROM pessoa", null);


            while (cursor.moveToNext()) {
                String nome = cursor.getString(cursor.getColumnIndex("nome"));
                int idade = cursor.getInt(cursor.getColumnIndex("idade"));
                Log.i(getClass().getSimpleName(), "Nome: " + nome + " Idade: " + idade);

            }

            banco.close();
            cursor.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
