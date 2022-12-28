package com.example.repositories;

import com.example.domains.Vegetable;
import java.util.*;
import io.micronaut.data.annotation.*;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.model.*;


import io.micronaut.data.repository.CrudRepository;




@Repository
@JdbcRepository(dialect = Dialect.H2)
public interface VegetableRepository extends CrudRepository<Vegetable, Long> {

    public Optional<Vegetable> findByName(String name);

}

