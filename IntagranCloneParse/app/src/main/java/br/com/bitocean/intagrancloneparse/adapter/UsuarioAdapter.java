package br.com.bitocean.intagrancloneparse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import br.com.bitocean.intagrancloneparse.R;

public class UsuarioAdapter extends ArrayAdapter<ParseUser> {
    private Context context;
    private ArrayList<ParseUser> usuarios;
    public UsuarioAdapter(Context context,  ArrayList<ParseUser> objects) {
        super(context, 0,  objects);
        this.context = context;
        this.usuarios = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if(view == null){

            //
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lista_usuario,parent,false);

        }

        if(usuarios.size() > 0){
            TextView tvUsername =(TextView) view.findViewById(R.id.tvUserName);
            TextView tvEmail =(TextView) view.findViewById(R.id.tvEmail);

            ParseUser pu = usuarios.get(position);
            tvEmail.setText(pu.getEmail());
            tvUsername.setText(pu.getUsername());

        }


        return view;
    }
}
