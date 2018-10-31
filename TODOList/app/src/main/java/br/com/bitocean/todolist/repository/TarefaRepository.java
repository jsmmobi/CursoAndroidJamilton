package br.com.bitocean.todolist.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.bitocean.todolist.database.DataBase;
import br.com.bitocean.todolist.model.Tarefa;

public class TarefaRepository implements  RepositoryInterface<Tarefa> {
    private SQLiteDatabase db;
    private DataBase dataBase;

    private Context context;

    public TarefaRepository(Context context) {
        this.context = context;
        dataBase = new DataBase(context);
    }

    @Override
    public Tarefa save(Tarefa entity) {
        // preenche os valores
        ContentValues values = new ContentValues();
        values.put(Tarefa.COL_ID,entity.getId());
        values.put(Tarefa.COL_TAREFA,entity.getTarefa());
        try {
            // Abre o Banco
            db = dataBase.getWritableDatabase();

            Long resultado = db.insert(Tarefa.TABLE_NAME,null,values);

            if(resultado == -1){
                Toast.makeText(context,"Erro ao inserir registro "+resultado,Toast.LENGTH_LONG).show();
            }else{
                entity.setId(resultado);
                Toast.makeText(context,"Registro inserido com sucesso! "+resultado,Toast.LENGTH_LONG).show();
            }

            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public Tarefa update(Tarefa entity) {
        //Toast.makeText(context,"Atualizando",Toast.LENGTH_LONG).show();
        ContentValues values = new ContentValues();
        values.put(Tarefa.COL_TAREFA,entity.getTarefa());

        String where = Tarefa.COL_ID + " = "+entity.getId();
        try {
            db = dataBase.getWritableDatabase();
            db.update(Tarefa.TABLE_NAME,values,where,null);
            db.close();
            Toast.makeText(context,"Atualizado com sucesso!",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        return findById(entity.getId());
    }

    @Override
    public void delete(Tarefa entity) {
        String where = Tarefa.COL_ID+" = "+entity.getId();
        try {
            db = dataBase.getReadableDatabase();
            db.delete(Tarefa.TABLE_NAME,where,null);
            db.close();
            Toast.makeText(context,"Excluído com sucesso!",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String where = Tarefa.COL_ID+" = "+id;
        try {
            db = dataBase.getReadableDatabase();
            db.delete(Tarefa.TABLE_NAME,where,null);
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Tarefa> listAll() {
        List<Tarefa> tarefas = new ArrayList<>();
        Cursor cursor;

        try {
            db = dataBase.getWritableDatabase();
            cursor = db.query(Tarefa.TABLE_NAME,Tarefa.CAMPOS,null,null,null,null,null);
            while(cursor.moveToNext()){
                Tarefa tarefa = new Tarefa(cursor.getLong(cursor.getColumnIndex(Tarefa.COL_ID)),cursor.getString(cursor.getColumnIndex(Tarefa.COL_TAREFA)));
                tarefas.add(tarefa);
            }
            db.close();
            cursor.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return tarefas;
    }

    @Override
    public Tarefa findById(Long id) {
        Cursor cursor = null;
        String where = Tarefa.COL_ID +" = "+id;
        Tarefa tarefa = new Tarefa();
        try{
            db= dataBase.getReadableDatabase();
            cursor = db.query(Tarefa.TABLE_NAME,Tarefa.CAMPOS,where,null,null,null,null);

            if(cursor !=null){
                cursor.moveToFirst();
                tarefa.setId(cursor.getLong(cursor.getColumnIndex(Tarefa.COL_ID)));
                tarefa.setTarefa(cursor.getString(cursor.getColumnIndex(Tarefa.COL_TAREFA)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return tarefa;
    }
}
