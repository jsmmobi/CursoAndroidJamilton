package br.com.bitocean.whatsappclone.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.com.bitocean.whatsappclone.R;
import br.com.bitocean.whatsappclone.activity.ConversaActivity;
import br.com.bitocean.whatsappclone.adapter.ContatoAdapter;
import br.com.bitocean.whatsappclone.config.FirebaseConfig;
import br.com.bitocean.whatsappclone.model.Contato;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContatoFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView listContatos;

    private ArrayAdapter adapter;
    private ArrayList<Contato> contatos;

    private DatabaseReference reference;
    private String idUsuarioLogado;

    private ValueEventListener valueEventListener;


    public ContatoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contato, container, false);

        reference = FirebaseConfig.getReference();
        idUsuarioLogado = FirebaseConfig.idUsuarioLogado();


        contatos = new ArrayList<>();

        listContatos = (ListView) view.findViewById(R.id.listViewContatos);
        listContatos.setOnItemClickListener(this);

       /* adapter = new ArrayAdapter(getActivity(),
                R.layout.row_contatos,
                contatos
                );*/
        adapter = new ContatoAdapter(getActivity(),contatos);
        listContatos.setAdapter(adapter);
        valueEventListener();
        recuperarUsuario();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        reference.addValueEventListener(valueEventListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if(valueEventListener !=null){
            reference.removeEventListener(valueEventListener);
        }

    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

    private void recuperarUsuario(){
       reference = FirebaseConfig.getReference()
                .child(Contato.NODE)
                .child(idUsuarioLogado);



    }

    public ValueEventListener valueEventListener() {
       valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                contatos.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    //System.out.println(ds.getValue(Contato.class));
                    Contato contato = ds.getValue(Contato.class);
                    contatos.add(contato);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        return valueEventListener;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent intent = new Intent(getActivity(),ConversaActivity.class);
        intent.putExtra(ConversaActivity.PARAM_CONTATO,contatos.get(position));
        startActivity(intent);
    }
}
