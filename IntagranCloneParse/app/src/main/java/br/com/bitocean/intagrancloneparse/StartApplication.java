package br.com.bitocean.intagrancloneparse;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class StartApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Habilite armazenamento local.
        Parse.enableLocalDatastore(this);

        // Codigo de configuração do App
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId(getResources().getString(R.string.app_id))
                .clientKey(getResources().getString(R.string.cliente_id))
                .server(getResources().getString(R.string.server_url))
                .build()
        );


/*
      // Teste de configuração do App
      ParseObject pontuacao = new ParseObject("Pontuacao");
      pontuacao.put("pontos", 200);
      pontuacao.saveInBackground(new SaveCallback() {
          public void done(ParseException e) {
              if (e == null) {
                  Log.i("TesteExecucao", "Salvo com sucesso!!!");
              } else {
                  Log.i("TesteExecucao", "Falha ao salvar os dados!!!"+e.getMessage());
              }
          }
      });


        ParseUser.enableAutomaticUser();

        */
        ParseACL defaultACL = new ParseACL();
        // Optionally enable public read access.
         defaultACL.setPublicReadAccess(true);
       // ParseACL.setDefaultACL(defaultACL, true);
    }
}
