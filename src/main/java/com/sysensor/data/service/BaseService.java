package com.sysensor.data.service;


import java.util.List;

public interface BaseService<T, S> {

    T create(T object) throws Exception;

    T get(long id);

    T update(T object);

    T delete(T object);

    List<T> getAll();

}
