package com.example.clients;


import io.micronaut.context.annotation.Parameter;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;
import jakarta.inject.Inject;
import com.example.domains.Vegetable;

import io.micronaut.http.MediaType;
import io.micronaut.http.multipart.CompletedFileUpload;





@Client("/api/v1/vegetable")
public interface VegetableClient {

    @Post("/save")
    public Vegetable save(Vegetable vegetable);

    @Get("/get")
    public Vegetable findById(@Parameter("id") long id);

    @Delete("/delete/{id}")
    public boolean deleteById(@PathVariable("id") long id);

    @Get("/findAll")
    public Iterable<Vegetable> findAll( );

    @Put("/update")
    public Vegetable update(@Body Vegetable vegetable);


    @Get("/findAllByName")
    public Vegetable findByName(String query);

}


