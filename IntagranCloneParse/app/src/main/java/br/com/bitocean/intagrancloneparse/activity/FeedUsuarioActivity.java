package br.com.bitocean.intagrancloneparse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import br.com.bitocean.intagrancloneparse.R;
import br.com.bitocean.intagrancloneparse.adapter.HomeAdapter;

public class FeedUsuarioActivity extends AppCompatActivity {
    private ListView listView;
    private String userId;
    private String userName;
    private ArrayAdapter<ParseObject> adapter;
    private ArrayList<ParseObject> postagens = new ArrayList<>();

    private ParseQuery<ParseObject> query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorBlack));
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        userId = intent.getStringExtra("objectId");
        userName = intent.getStringExtra("username");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(userName);

        listView = (ListView)findViewById(R.id.listView);
        adapter = new HomeAdapter(this,postagens);
        listView.setAdapter(adapter);

        buscarPostagens();


    }

    private void buscarPostagens() {
        query = ParseQuery.getQuery("Image");
        query.whereEqualTo("iserId",userId);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null){
                    postagens.clear();

                   for(ParseObject po:objects) {
                       postagens.add(po);
                   }
                    adapter.notifyDataSetChanged();
                }else{
                    e.printStackTrace();
                }
            }
        });
    }

}
