package br.com.bitocean.whatsappclone.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.bitocean.whatsappclone.model.Usuario;

public final class FirebaseConfig {
    private static DatabaseReference reference;

    private static FirebaseAuth auth;

    public static DatabaseReference getReference() {
        return reference==null?FirebaseDatabase.getInstance().getReference():reference;
    }

    public static FirebaseAuth getAuth() {
        return auth == null?FirebaseAuth.getInstance():auth;
    }

    public static String idUsuarioLogado(){
        return getAuth().getCurrentUser().getUid();
    }


}
