# Micostartercli Microservices
This is a simple Micronaut Microservices configured using MicrostarterCLI 0.2.5. 
The Microservices consists of:
1. Fruit Service. 
2. Vegetable Service
3. Eureka Service Discovery Server. 
4. Spring Cloud Gateway. 

# How to generate the example: 

## 1. Generating Eureka Server
run 
```shell
> mc eureka -version 2.7.7 --javaVersion 11

```
## 2. Generate Fruit Service. 
First run 
```shell
> mc init --name FruitService
```
Then, run: 
```shell
> cd FruitService
> mc entity -e Fruit
```
Note: Ensure to select "No" in "Is Monolithic?" question, and configure "eureka" as localhost. 

## 3. Generate Vegetable Service. 
First run 
```shell
> mc init --name VegetableService
```
Then, run: 
```shell
> cd FruitService
> mc entity -e Vegetable
```
Note: Ensure to select "No" in "Is Monolithic?" question, and configure "eureka" as localhost. 

## 4. Generate Spring Cloud Gateway & Configure the Routes

First, run:
```shell
mc gateway -version 2.7.7 --javaVersion 11
```
Second, Enter the Gateway directory: 
```shell
> cd gateway
```

Run the following command twice to configure the routes of FruitService, and VegetableService 
```shell
> mc gateway register
```

