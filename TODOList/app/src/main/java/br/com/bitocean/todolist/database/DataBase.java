package br.com.bitocean.todolist.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import br.com.bitocean.todolist.model.Tarefa;

public class DataBase extends SQLiteOpenHelper {
    private static final int DB_VERSION=1;
    private static final String DB_NAME="database_tarefas";

    public DataBase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Tarefa.SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
