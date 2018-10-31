package br.com.bitocean.todolist.repository;

import java.util.List;

public interface RepositoryInterface<T> {

    public T save(T entity);

    public T update(T entity);

    public void delete(T entity);

    public void delete(Long id);

    public List<T> listAll();

    public T findById(Long id);
}
