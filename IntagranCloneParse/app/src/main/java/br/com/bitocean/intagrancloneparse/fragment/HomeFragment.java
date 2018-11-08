package br.com.bitocean.intagrancloneparse.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import br.com.bitocean.intagrancloneparse.R;
import br.com.bitocean.intagrancloneparse.adapter.HomeAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private ListView listView;
    private ArrayList<ParseObject> lista = new ArrayList<>();
    private HomeAdapter adapter;
    private ParseQuery<ParseObject> query;

    public HomeFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        listView = (ListView)view.findViewById(R.id.listView);
        adapter = new HomeAdapter(getActivity(),lista);
        listView.setAdapter(adapter);

        listarPostagens();
        return view;
    }


    public void listarPostagens(){
        String userObjectId = ParseUser.getCurrentUser().getObjectId();
         query = ParseQuery.getQuery("Image");
         query.whereEqualTo("iserId",userObjectId);
         query.orderByDescending("createdAt");

         query.findInBackground(new FindCallback<ParseObject>() {
             @Override
             public void done(List<ParseObject> objects, ParseException e) {
                 if(e == null){
                    if(objects.size() >0) {
                        lista.clear();
                        for(ParseObject po:objects){
                            lista.add(po);
                        }

                        adapter.notifyDataSetChanged();
                    }
                 }else{
                     e.printStackTrace();
                 }
             }
         });

    }

}
