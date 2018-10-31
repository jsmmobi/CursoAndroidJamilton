package br.com.bitocean.todolist.model;

import java.util.Objects;

public class Tarefa {
    public static final String TABLE_NAME="tarefa";
    public static final String COL_ID="_id";
    public static final String COL_TAREFA="tarefa";
    public static final String CAMPOS[] = {COL_ID,COL_TAREFA};
    private Long id;
    private String tarefa;

    public static final String SQL_CREATE=
            "CREATE TABLE "+TABLE_NAME+" (" +
                    COL_ID+" integer primary key autoincrement,"+
                    COL_TAREFA+" VARCHAR(100) "+
                    ")";


    public Tarefa() {
    }

    public Tarefa(Long id, String tarefa) {
        this.id = id;
        this.tarefa = tarefa;
    }

    public Tarefa(String tarefa) {
        this.tarefa = tarefa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTarefa() {
        return tarefa;
    }

    public void setTarefa(String tarefa) {
        this.tarefa = tarefa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tarefa tarefa = (Tarefa) o;

        return id != null ? id.equals(tarefa.id) : tarefa.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
