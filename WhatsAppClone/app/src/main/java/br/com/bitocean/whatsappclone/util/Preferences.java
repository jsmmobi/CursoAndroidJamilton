package br.com.bitocean.whatsappclone.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Preferences {
    public static final String KEY_TOKEN="KeyToken";
    public static final String KEY_NOME="KeyNome";
    public static final String KEY_TELEFONE="KeyTelefone";

    public static final String ARQUIVO_PREFERENCES="MyPreferences";
    private static Preferences instance;
    private Context context;
    private static final int MODE=0;
    public static Preferences getInstance(Context context) {
        return instance==null?new Preferences(context):instance;
    }

    private Preferences(Context context) {
        this.context = context;
    }

    public void putString(String key,String value){
        SharedPreferences.Editor editor = getEditor();
        editor.putString(key,value);
        editor.commit();
    }

    public void putString(Map<String,String> prefs){
        SharedPreferences.Editor editor = getEditor();
        Iterator it = prefs.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            editor.putString((String) pair.getKey(),(String) pair.getValue());
        }

        editor.commit();
    }

    private SharedPreferences.Editor getEditor(){
        SharedPreferences.Editor editor = getPreferences().edit();
        return editor;
    }

    private SharedPreferences getPreferences(){
        SharedPreferences preferences = context.getSharedPreferences(ARQUIVO_PREFERENCES,MODE);//o = privado
        return preferences;
    }


    public  Map<Object,Object> map(){
        Map<Object,Object> mapa = new HashMap<>();
        mapa.put(KEY_TOKEN,getString(KEY_TOKEN));
        mapa.put(KEY_NOME,getString(KEY_NOME));
        mapa.put(KEY_TELEFONE,getString(KEY_TELEFONE));

        return mapa;
    }

    public String getString(String key){
        SharedPreferences preferences = getPreferences();
        if(preferences.contains(key)){
            String value = preferences.getString(key,"");
            return value;
        }

        return "";
    }

    public void printMap() {
        Map map = map();
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Log.i("PREFERENCES",pair.getKey() + " = " + pair.getValue());
            //it.remove(); // avoids a ConcurrentModificationException
        }
    }
}
