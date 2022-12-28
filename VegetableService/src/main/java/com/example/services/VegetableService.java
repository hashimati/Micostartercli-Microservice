package com.example.services;


import jakarta.inject.Inject;
import io.micronaut.http.multipart.CompletedFileUpload;
import jakarta.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.micrometer.core.annotation.Timed;
import javax.transaction.Transactional;
import com.example.domains.Vegetable;
import com.example.repositories.VegetableRepository;








@Transactional
@Singleton
public class VegetableService {

    private static final Logger log = LoggerFactory.getLogger(VegetableService.class);
    @Inject private VegetableRepository vegetableRepository;
    

    @Timed(value = "com.example.services.vegetableService.save", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for saving vegetable object")
    public Vegetable save(Vegetable vegetable ){
        log.info("Saving  Vegetable : {}", vegetable);
        //TODO insert your logic here!
        //saving Object
        return vegetableRepository.save(vegetable);

    }

    
    @Timed(value = "com.example.services.vegetableService.findById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a vegetable object by id")
    public Vegetable findById(long id ){
        log.info("Finding Vegetable By Id: {}", id);
        return vegetableRepository.findById(id).orElse(null);
    }

    @Timed(value = "com.example.services.vegetableService.deleteById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for deleting a vegetable object by id")
    public boolean deleteById(long id ){
        log.info("Deleting Vegetable by Id: {}", id);
        try{
            vegetableRepository.deleteById(id);
            log.info("Deleted Vegetable by Id: {}", id);
            return true;
        }
        catch(Exception ex)
        {
            log.info("Failed to delete Vegetable by Id: {}", id);
            ex.printStackTrace();
            return false;
        }
    }

    @Timed(value = "com.example.services.vegetableService.findAll", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding all vegetable objects")
    public Iterable<Vegetable> findAll( ) {
        log.info("Find All");
      return  vegetableRepository.findAll();
    }

    public boolean existsById(long id )
    {
        log.info("Check if id exists: {}", id);
        return  vegetableRepository.existsById(id);

    }

    @Timed(value = "com.example.services.vegetableService.update", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for update a vegetable object")
    public Vegetable update(Vegetable vegetable )
    {
        log.info("update {}", vegetable);
        return vegetableRepository.update(vegetable);

    }
    
    @Timed(value = "com.example.services.vegetableService.findByName", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a vegetable object by id")
    public Vegetable findByName(String query ){
          log.info("Finding Vegetable By Name: {}", query);
          return vegetableRepository.findByName(query).orElse(null);
    }



}

