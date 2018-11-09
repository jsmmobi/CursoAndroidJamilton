package br.com.bitocean.recyclerview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.bitocean.recyclerview.R;
import br.com.bitocean.recyclerview.model.Filme;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ObjectViewHolder>  {
    private List<Filme> filmes;

    public RecyclerViewAdapter(List<Filme> filmes) {
        this.filmes = filmes;
    }

    @NonNull
    @Override
    public ObjectViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lista_filmes,viewGroup,false);
        ObjectViewHolder holder = new ObjectViewHolder(view);
        return holder ;
    }

    @Override
    public void onBindViewHolder(@NonNull ObjectViewHolder holder, int position) {
        Filme filme = filmes.get(position);
        holder.tvTitulo.setText(filme.getTitulo());
        holder.tvAno.setText(filme.getAno());
        holder.tvGenero.setText(filme.getGenero());
    }

    @Override
    public int getItemCount() {
        return filmes.size();
    }

    public  class ObjectViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTitulo,tvAno,tvGenero;

        public ObjectViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitulo = (TextView)itemView.findViewById(R.id.tvTitulo);
            tvAno = (TextView)itemView.findViewById(R.id.tvAno);
            tvGenero = (TextView)itemView.findViewById(R.id.tvGenero);
        }
    }
}
