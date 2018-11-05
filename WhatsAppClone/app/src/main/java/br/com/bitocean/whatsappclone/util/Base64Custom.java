package br.com.bitocean.whatsappclone.util;

import android.util.Base64;

public class Base64Custom {

    public static final String encode(String input){
        return Base64.encodeToString(input.getBytes(),Base64.DEFAULT).replaceAll("(\\n|\\r)","");
    }

    public static final String decode(String input){
        return new String(Base64.decode(input,Base64.DEFAULT));
    }
}
