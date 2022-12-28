package com.example.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.micronaut.http.MediaType;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.core.annotation.NonNull;




import io.micronaut.retry.annotation.CircuitBreaker;
import io.micronaut.context.annotation.Parameter;
import io.micronaut.core.version.annotation.Version;
import io.micronaut.http.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import io.micrometer.core.annotation.Timed;
import io.micronaut.tracing.annotation.NewSpan;
import io.micronaut.tracing.annotation.SpanTag;
import jakarta.inject.Inject;



import com.example.domains.Vegetable;
import com.example.services.VegetableService;


@Tag(name = "Vegetable")
@Controller("/api/v1/vegetable")
@CircuitBreaker(attempts = "5", maxDelay = "3s", reset = "30")
public class VegetableController {

    private static final Logger log = LoggerFactory.getLogger(VegetableController.class);

    @Inject private VegetableService vegetableService;


    @NewSpan("Vegetable-service")
    @Timed(value = "com.example.controllers.vegetableController.save", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for saving vegetable object")
    
    @Post("/save")
    @Version("1")
    @Operation(summary = "Creating a vegetable and Storing in the database",
            description = "A REST service, which saves Vegetable objects to the database.",
            operationId = "SaveVegetable"
    )
    @ApiResponse(
            content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Object Supplied")
    @ApiResponse(responseCode = "404", description = "Vegetable not stored")
    public Vegetable save(@SpanTag("save.vegetable") @NonNull @Body Vegetable vegetable  ){
        log.info("Saving  Vegetable : {}", vegetable);
        //TODO insert your logic here!

        //saving Object
        return vegetableService.save(vegetable );
    }


    @NewSpan("Vegetable-service")
    @Timed(value = "com.example.controllers.vegetableController.findById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a vegetable object by id")
    
    @Get("/get")
    @Version("1")
    @Operation(summary = "Getting a vegetable by Id",
        description = "A REST service, which retrieves a Vegetable object by Id.",
        operationId = "FindByIdVegetable"
    )
    @ApiResponse(
        content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Id Supplied")
    @ApiResponse(responseCode = "404", description = "Vegetable not found")
    public Vegetable findById(@SpanTag("findById.id") @Parameter("id") long id ){
        return vegetableService.findById(id );
    }

    @NewSpan("Vegetable-service")
    @Timed(value = "com.example.controllers.vegetableController.deleteById", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for deleting a vegetable object by id")
    
    @Delete("/delete/{id}")
    @Version("1")
    @Operation(summary = "Deleting a vegetable by ID",
            description = "A REST service, which deletes Vegetable object from the database.",
            operationId = "DeleteByIdVegetable"
    )
    @ApiResponse(
            content = @Content(mediaType = "boolean")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Id Supplied")
    @ApiResponse(responseCode = "404", description = "Vegetable not found")
    public boolean deleteById(@SpanTag("deleteById.id") @PathVariable("id") long id ){
        log.info("Deleting Vegetable by Id: {}", id);
        return  vegetableService.deleteById(id );
    }

    @NewSpan("Vegetable-service")
    @Timed(value = "com.example.controllers.vegetableController.findAll", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding all vegetable objects")
    
    @Get("/findAll")
    @Version("1")
    @Operation(summary = "Retrieving all vegetable objects as Json",
            description = "A REST service, which returns all Vegetable objects from the database.",
            operationId = "FindAllVegetable"
    )
    @ApiResponse(
            content = @Content(mediaType = "application/json")
    )
    public Iterable<Vegetable> findAll( ){
        log.info("find All");
        return vegetableService.findAll( );
    }

    @NewSpan("Vegetable-service")
    @Timed(value = "com.example.controllers.vegetableController.update", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for update a vegetable object")
    
    @Put("/update")
    @Version("1")
    @Operation(summary = "Updating a vegetable.",
            description = "A REST service, which update a Vegetable objects to the database.",
            operationId = "UpdateVegetable"
    )
    @ApiResponse(
            content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "404", description = "Vegetable not found")
    public Vegetable update(@SpanTag("update.vegetable") @NonNull @Body Vegetable vegetable )
    {
        log.info("update {}", vegetable);
        return vegetableService.update(vegetable );

    }

    
    @Get("/findByName")
    @NewSpan("Vegetable-service")
    @Timed(value = "com.example.controllers.vegetableController.findByName", percentiles = { 0.5, 0.95, 0.99 }, description = "Observing all service metric for finding a vegetable object by Name")
    @Operation(summary = "Find an entity by Name",
    description = "A REST service, which retrieves a Vegetable object by Name."
    )
    @ApiResponse(
    content = @Content(mediaType = "application/json")
    )
    @ApiResponse(responseCode = "400", description = "Invalid Id Supplied")
    @ApiResponse(responseCode = "404", description = "Vegetable not found")
    public Vegetable findByName(String query ){
          log.info("Finding Vegetable By Name: {}", query);
          return vegetableService.findByName(query );
    }



}


