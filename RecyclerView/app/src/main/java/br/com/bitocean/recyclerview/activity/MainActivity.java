package br.com.bitocean.recyclerview.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.bitocean.recyclerview.R;
import br.com.bitocean.recyclerview.adapter.RecyclerViewAdapter;
import br.com.bitocean.recyclerview.listener.RecyclerItemClickListener;
import br.com.bitocean.recyclerview.model.Filme;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private List<Filme> filmes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        criarLista();
        adapter = new RecyclerViewAdapter(filmes);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayout.VERTICAL));
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        //Evento de click
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Filme filme = filmes.get(position);
                Toast.makeText(getApplicationContext(),
                        "Click:: "+filme.getTitulo(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongItemClick(View view, int position) {
                Filme filme = filmes.get(position);
                Toast.makeText(getApplicationContext(),
                        "Long:: "+filme.getTitulo(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        }));



    }

    private void criarLista(){
        filmes = new ArrayList<>();
        filmes.add(new Filme("A Era do Gelo","2015","Animação"));
        filmes.add(new Filme("O Bem Amado","2000","Romançe"));
        filmes.add(new Filme("Homem Aranha","2014","Animação"));
        filmes.add(new Filme("Liga da Justiça","1985","Animação"));
        filmes.add(new Filme("Capitão América","1992","Animação"));
        filmes.add(new Filme("Troia","2010","Animação"));
        filmes.add(new Filme("Vendaval","1999","Terror"));
        filmes.addAll(filmes);

    }
}
