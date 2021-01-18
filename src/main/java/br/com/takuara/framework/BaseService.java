package br.com.takuara.framework;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import java.util.List;

public interface BaseService<T extends PanacheEntity> {

    T findById(Long id);

    List<T> findAll();

    T save(T entity);

    void delete(T entity);

    T preProcessorSave(T entity);

    T postProcessorSave(T entity);

}
