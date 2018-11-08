package br.com.bitocean.intagrancloneparse.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import br.com.bitocean.intagrancloneparse.R;
import br.com.bitocean.intagrancloneparse.activity.FeedUsuarioActivity;
import br.com.bitocean.intagrancloneparse.adapter.UsuarioAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsuarioFragment extends Fragment {
    private ListView listView;
    private ArrayAdapter<ParseUser> adapter;
    private ArrayList<ParseUser> usuarios;

    private ParseQuery<ParseUser> query;


    public UsuarioFragment() {
        usuarios = new ArrayList<>();
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_usuario, container, false);
        listView = (ListView)view.findViewById(R.id.listView);

        adapter = new UsuarioAdapter(getActivity(),usuarios);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                ParseUser user = usuarios.get(position);
                Intent intent = new Intent(getActivity(),FeedUsuarioActivity.class);
                intent.putExtra("objectId",user.getObjectId());
                intent.putExtra("username",user.getUsername());
                startActivity(intent);
            }
        });

        getUsuarios();
        return view;
    }

    private void getUsuarios() {
        query = ParseUser.getQuery();
        query.whereNotEqualTo("objectId",ParseUser.getCurrentUser().getObjectId());
        query.orderByAscending("username");

        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                usuarios.clear();
                if(e == null){
                    for(ParseUser u:objects){
                        usuarios.add(u);
                    }

                    adapter.notifyDataSetChanged();
                }else{
                    e.printStackTrace();
                }
            }
        });
    }

}
