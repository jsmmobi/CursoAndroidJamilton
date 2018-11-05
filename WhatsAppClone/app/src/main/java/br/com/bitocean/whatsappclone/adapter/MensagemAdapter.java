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
import br.com.bitocean.whatsappclone.config.FirebaseConfig;
import br.com.bitocean.whatsappclone.model.Contato;
import br.com.bitocean.whatsappclone.model.Mensagem;

public class MensagemAdapter extends ArrayAdapter<Mensagem> {
    public ArrayList<Mensagem> mensagens;
    private Context context;
    private String idSender;


    public MensagemAdapter(@NonNull Context context, @NonNull ArrayList<Mensagem> objects) {
        super(context, 0, objects);
        this.mensagens = objects;
        this.context = context;
        this.idSender = FirebaseConfig.idUsuarioLogado();
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;

        if(mensagens !=null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            Mensagem msg = mensagens.get(position);
            //
            if(msg.getIdSender().equals(idSender)){
                view = inflater.inflate(R.layout.item_mensagem_enviada,parent,false);
            }else{
                view = inflater.inflate(R.layout.item_mensagem_recebida,parent,false);
            }

            TextView tvMensagem = (TextView)view.findViewById(R.id.tvMensagem);
            tvMensagem.setText(msg.getTextMessage());



        }

        return view;
    }


}
