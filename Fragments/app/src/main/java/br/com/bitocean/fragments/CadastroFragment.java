package br.com.bitocean.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CadastroFragment extends Fragment {

    TextView tvInfoCadastro;


    public CadastroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cadastro, container, false);
        // Inflate the layout for this fragment
        tvInfoCadastro = (TextView)view.findViewById(R.id.tvInfocadastro);
        tvInfoCadastro.setText("Aqui mudou o texto da tela de CADASTRO [RUNTIME]");
        return view;
    }

}
