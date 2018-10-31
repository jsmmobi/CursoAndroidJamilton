package br.com.bitocean.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.bitocean.todolist.adapter.TarefaAdpter;
import br.com.bitocean.todolist.database.DataBase;
import br.com.bitocean.todolist.model.Tarefa;
import br.com.bitocean.todolist.repository.TarefaRepository;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    
    private EditText edtInput;
    private Button btSalvar;
    private ListView listView;
    private TarefaRepository repository;
    private TarefaAdpter adapter;
    private List<Tarefa> tarefaList;
    private Tarefa tarefaSelecionada = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        listView = (ListView)findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tarefaSelecionada = tarefaList.get(position);
                edtInput.setText(tarefaSelecionada.getTarefa());
                edtInput.requestFocus();

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Tarefa tarefa = tarefaList.get(position);
                repository.delete(tarefa);
                loadListView();
                return true;
            }
        });
        btSalvar = (Button)findViewById(R.id.btSalvar);
        edtInput = (EditText)findViewById(R.id.edtInput);
        btSalvar.setOnClickListener(this);
        
        repository = new TarefaRepository(this);

        loadListView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btSalvar:
                savaOrUpdate();
                break;
        }
    }

    private void savaOrUpdate(){
        if(edtInput.getText().toString().isEmpty()){
            Toast.makeText(this,"Verifique o campo",Toast.LENGTH_LONG).show();
            return;
        }
        if(tarefaSelecionada != null){
            tarefaSelecionada.setTarefa(edtInput.getText().toString());
            update();
        }else {
            salvar();
        }

        tarefaSelecionada = null;
        edtInput.setText("");
        loadListView();
    }

    private void update(){
        repository.update(tarefaSelecionada);
    }
    private void salvar() {
        Tarefa tarefa = new Tarefa(edtInput.getText().toString());
        repository.save(tarefa);
    }

    private void loadListView() {
        tarefaList = repository.listAll();
        adapter = new TarefaAdpter(tarefaList,this);
        listView.setAdapter(adapter);
    }
}
