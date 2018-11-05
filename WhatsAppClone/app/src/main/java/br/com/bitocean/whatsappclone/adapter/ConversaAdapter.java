package br.com.bitocean.whatsappclone.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.bitocean.whatsappclone.R;
import br.com.bitocean.whatsappclone.model.Contato;
import br.com.bitocean.whatsappclone.model.Conversa;

public class ConversaAdapter extends ArrayAdapter<Conversa> {
    public ArrayList<Conversa> conversas;
    private Context context;

    public ConversaAdapter(@NonNull Context context, @NonNull ArrayList<Conversa> objects) {
        super(context, 0, objects);
        this.conversas = objects;
        this.context = context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;

        if(conversas !=null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_conversas,parent,false);
            TextView tvNome = (TextView)view.findViewById(R.id.tvNome);
            TextView tvEmail = (TextView)view.findViewById(R.id.tvEmail);

            Conversa conversa = conversas.get(position);

            tvNome.setText(conversa.getNome());
            tvEmail.setText(conversa.getMensagem().getTextMessage());


        }

        return view;
    }


}
