package br.com.bitocean.localizacaousuario;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class AndroidPermissions {

    public static boolean validaPermissoes(Activity activity,String[] permissoes,int requestCode){
        List<String> listaPermissoes = new ArrayList<>();
        if(Build.VERSION.SDK_INT >= 23){
           for(String permissao:permissoes){

               boolean temPermissao = ContextCompat.checkSelfPermission(activity,permissao) ==  PackageManager.PERMISSION_GRANTED;

               if(!temPermissao){
                   listaPermissoes.add(permissao);
               }
           }

           if(listaPermissoes.isEmpty()){
               return true;
           }

           // Solicita Permissao;

            String [] novasPermissoes = new String[listaPermissoes.size()];
           listaPermissoes.toArray(novasPermissoes);

            ActivityCompat.requestPermissions(activity,novasPermissoes,requestCode);
        }

        return true;
    }
}
