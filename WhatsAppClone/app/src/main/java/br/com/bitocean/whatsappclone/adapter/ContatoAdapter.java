package br.com.bitocean.whatsappclone.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.bitocean.whatsappclone.R;
import br.com.bitocean.whatsappclone.model.Contato;

public class ContatoAdapter extends ArrayAdapter<Contato> {
    public ArrayList<Contato> contatos;
    private Context context;

    public ContatoAdapter(@NonNull Context context,  @NonNull ArrayList<Contato> objects) {
        super(context, 0, objects);
        this.contatos = objects;
        this.context = context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;

        if(contatos !=null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_contatos,parent,false);
            TextView tvNome = (TextView)view.findViewById(R.id.tvNome);
            TextView tvEmail = (TextView)view.findViewById(R.id.tvEmail);

            Contato contato = contatos.get(position);

            tvNome.setText(contato.getNome().toUpperCase());
            tvEmail.setText(contato.getEmail());


        }

        return view;
    }


}
