package com.capgemini.movies.adapter;

public interface EntityAdapter <D, E> {

    D asDomainObject(E dbEntity);

    E asDbObject(D domainObj);
}
