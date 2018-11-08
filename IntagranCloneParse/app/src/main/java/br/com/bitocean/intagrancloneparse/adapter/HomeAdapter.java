package br.com.bitocean.intagrancloneparse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.parse.ParseObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.com.bitocean.intagrancloneparse.R;

public class HomeAdapter extends ArrayAdapter<ParseObject> {
    private ArrayList<ParseObject> objectList;
    private Context context;
    public HomeAdapter(Context context, ArrayList<ParseObject> objectList) {
        super(context, 0,objectList);
        this.objectList = objectList;
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ImageView imageView;
        if(view == null){

            //
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
           view = inflater.inflate(R.layout.lista_postagem,parent,false);

        }

        if(objectList.size() > 0){
            imageView = view.findViewById(R.id.ivImagem);
            ParseObject po = objectList.get(position);

            Picasso
                    .get()
                    .load(po.getParseFile("image").getUrl())
                    .fit()
                    .into(imageView);


        }


        return view;
    }


}
