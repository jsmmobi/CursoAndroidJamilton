package br.com.bitocean.whatsappclone.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import br.com.bitocean.whatsappclone.R;
import br.com.bitocean.whatsappclone.adapter.ConversaAdapter;
import br.com.bitocean.whatsappclone.config.FirebaseConfig;
import br.com.bitocean.whatsappclone.model.Conversa;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConversaFragment extends Fragment {

    private ValueEventListener valueEventListenerConversa;
    private ListView listConversas;

    private ArrayAdapter<Conversa> adapter;
    private ArrayList<Conversa> conversas;

    private DatabaseReference reference;


    public ConversaFragment() {
        // Required empty public constructor
    }

    public ValueEventListener getValueEventListenerConversa() {
        valueEventListenerConversa = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    conversas.clear();
                    for(DataSnapshot ds:dataSnapshot.getChildren()){
                        Conversa conversa = ds.getValue(Conversa.class);
                        conversas.add(conversa);
                    }
                    adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        return valueEventListenerConversa;
    }

    @Override
    public void onStart() {
        super.onStart();
        reference.addValueEventListener(valueEventListenerConversa);
    }

    @Override
    public void onStop() {
        super.onStop();
        reference.removeEventListener(valueEventListenerConversa);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        reference = FirebaseConfig.getReference().child(Conversa.NODE).child(FirebaseConfig.idUsuarioLogado());
        conversas = new ArrayList<>();
        View view = inflater.inflate(R.layout.fragment_conversa, container, false);
        listConversas = (ListView)view.findViewById(R.id.listConvesas);

        adapter = new ConversaAdapter(getActivity(),conversas);
        listConversas.setAdapter(adapter);
        getValueEventListenerConversa();
        return view;
    }

}
