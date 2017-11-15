package com.sysensor.data.service.impl;

import com.sysensor.data.entity.BaseEntity;
import com.sysensor.data.repository.BaseRepository;
import com.sysensor.data.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public abstract class BaseServiceImpl<T extends BaseEntity, S extends BaseRepository<T>> implements BaseService<T, S> {

    @Autowired
    protected S repository;

    @Override
    public T create(T entity) throws DataIntegrityViolationException {
        if (entity.getId() != null) {
            throw new DataIntegrityViolationException("Attempting to CREATE an entity with an ID already present. Did you mean to update instead?");
        }
        return repository.save(entity);
    }

    @Override
    public T get(long id) {
        return repository.findOne(id);
    }

    @Override
    public List<T> getAll(){
        return repository.findAll();
    }

    @Override
    public T update(T entity) {
        return repository.save(entity);
    }

    @Override
    public T delete(T entity) {
        repository.delete(entity);
        return entity;
    }
}
