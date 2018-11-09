package br.com.bitocean.cardview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import br.com.bitocean.cardview.adapter.PostAdapter;
import br.com.bitocean.cardview.model.Post;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Post> postagens;
    private PostAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Define o Layout
        //RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        //LinearLayoutManager manager = new LinearLayoutManager(this);
        //manager.setOrientation(LinearLayout.HORIZONTAL);
        GridLayoutManager manager = new GridLayoutManager(getApplicationContext(),2);


        loadPosts();

        // Define o Adapter
        adapter = new PostAdapter(postagens);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    private void loadPosts() {
        //String usuario, int imagem, String text
        postagens = new ArrayList<>();
        postagens.add(new Post("Moisés Juvenal da silva",R.drawable.imagem1,"#ToFeliz"));
        postagens.add(new Post("Andre Silva Rosa",R.drawable.imagem2,"#PraAcabar"));
        postagens.add(new Post("Maria de Fátima Silva",R.drawable.imagem3,"#ToFeliz"));
        postagens.add(new Post("Cosme Juvenal da Silva",R.drawable.imagem4,"#ToFeliz"));
    }
}
