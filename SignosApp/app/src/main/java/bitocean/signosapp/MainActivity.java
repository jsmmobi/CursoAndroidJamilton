package bitocean.signosapp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    private String[] signos={
            "Áries","Touro","Gêmeos","Câncer","Leão","Virgem",
            "Libra","Escorpião","Sagitário","Capricórnio","Aquário","Peixes"
    };

    private String[] perfil ={
            "Arianos são Animados is simply dummy text of the printing and typesetting industry.",
            "Touros is simply dummy text of the printing and typesetting industry.",
            "Gêmeos is simply dummy text of the printing and typesetting industry.",
            "Cãncer is simply dummy text of the printing and typesetting industry.",
            "Leão is simply dummy text of the printing and typesetting industry.",
            "Virgem is simply dummy text of the printing and typesetting industry.",
            "Libra is simply dummy text of the printing and typesetting industry.",
            "Escorpião is simply dummy text of the printing and typesetting industry.",
            "Sagitários is simply dummy text of the printing and typesetting industry.",
            "Capricornios is simply dummy text of the printing and typesetting industry.",
            "Aquarios is simply dummy text of the printing and typesetting industry.",
            "Peixes is simply dummy text of the printing and typesetting industry."

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                signos
                );
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),perfil[position],Toast.LENGTH_LONG).show();
            }
        });

    }
}
