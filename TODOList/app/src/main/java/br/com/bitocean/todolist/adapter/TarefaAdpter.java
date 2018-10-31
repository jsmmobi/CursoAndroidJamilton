package br.com.bitocean.todolist.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.bitocean.todolist.R;
import br.com.bitocean.todolist.model.Tarefa;

public class TarefaAdpter extends BaseAdapter {
    private List<Tarefa> tarefas;
    private Activity activity;

    public TarefaAdpter(List<Tarefa> tarefas, Activity activity) {
        this.tarefas = tarefas;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return tarefas.size();
    }

    @Override
    public Object getItem(int position) {
        return tarefas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return tarefas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(R.layout.lista_tarefa_item,parent,false);
        Tarefa tarefa = tarefas.get(position);

        //Pegando referencias dos components
        TextView tvId = view.findViewById(R.id.tvId);
        TextView tvTarefa = view.findViewById(R.id.tvTarefa);

        // Populando view
        tvId.setText(String.valueOf(tarefa.getId()));
        tvTarefa.setText(tarefa.getTarefa());

        return view;
    }
}
