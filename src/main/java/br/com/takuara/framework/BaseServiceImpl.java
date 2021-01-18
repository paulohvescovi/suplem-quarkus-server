package br.com.takuara.framework;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.transaction.Transactional;
import java.util.List;

public abstract class BaseServiceImpl<T extends PanacheEntity> implements BaseService<T>{

    protected abstract PanacheRepository<T> getRepository();

    @Override
    public List<T> findAll() {
        return getRepository().findAll().list();
    }

    @Override
    @Transactional
    public void delete(T bean) {
        getRepository().delete(
                getRepository().findById(bean.id)
        );
    }

    @Override
    @Transactional
    public T save(T bean) {
        preProcessorSave(bean);
        if (bean.id == null) {
            getRepository().persistAndFlush(bean);
        } else {
            bean.getEntityManager().merge(bean);
        }
        postProcessorSave(bean);
        return bean;
    }

    @Override
    public T findById(Long id) {
        return getRepository().findById(id);
    }

    @Override
    public T postProcessorSave(T entity) {
        return entity;
    }

    @Override
    public T preProcessorSave(T entity) {
        return entity;
    }


}
