# Spring Boot Kafka Mongodb

This project demonstrates the implementation of Spring boot microservices which work together to get data from the user and push it to MongoDB via Kafka. The microservices also read the configuration from spring boot config server.
I have also tried my hands a little bit on the Executor service as that seemed interesting and I had never got a chance to actually use it. 

## Overview
![GitHub Logo](/Design.png)

## Project structure:
1. Write-to-kafka
2. kafka-to-mongo
3. spring-boot-config-server
4. docker-compose.yml

### Docker-compose.yml
Kafka, zookeeper, write-to-kafka, kafka-to-mongodb,spring-boot-config-server

Zookeeper is only used for Kafka as so far kafka does not work without it. 

### write-to-kafka
Uses Apache.kafka library KafkaProducer to send the Json received from user.

##### Only the following Json format will make it all the way to MongoDb as the kafka-to-mongodb service only supports this format.
```
{
  "key":"value"
}
```

#### API Endpoint:

POST http://localhost:8081/kafka/data

Inpug Json:
```
{
  "key":"value"
}
``` 

#### Note
>To run write-to-kafka in IDE, please change **kafka.server=localhost:29092** in  **application.properties**. 

### kafka-to-mongodb
Uses Apache kafka library KafkaConsumer to retrive messages from the kafka and uses Spring data JPA mongodb repository to write the same to MongoDb
#### API Endpoint: 

POST http://localjost:8082/kafka/msg



#### Note
>To run kafka-to-mongodb in IDE, please change **kafka.server=localhost:29092** in  **application.properties**. 


### Build write-to-kafka
Navigate to write-to-kafka folder
```
mvn spring-boot:build-image
```

### Build kafka-to-mongodb
Navigate to consumer folder
```
mvn spring-boot:build-image
```

### Running End to End Dockers
Navigate to the root directory of this project 
```
docker-compose up -d
```

### Stopping docker containers
Navigate to the root directory of this project
```
docker-compose down
```

#### Running only zookeeper and kafka [Only if you want to run the microservices in IDE]
For this you may need to create another docker compose file having only kafka and zookeeper. I am leaving this upto the consumer.



## Whats next?

1.Write the same microservices using WebFlux and Reactor library : Leaving this out for now because of the time constraints and other commitments I had to stick to. But definitely ssomething I would like to explore.
2. Add swagger to the project for easy API visualization and testing.
3. Add Unit tests : Ideally this is something that must be done as part of the development. But the goal being learning and demonstrating the integration of kafka, mongo and the config servers I am leaving this for the next time. 

## Important Note

I have tried to test the compose file on my personal free AWS account but I was facing some memory related issues which I could not fix in time. These might be related to the system configuration available or there might be some twicks which will fix them. But as of now those remain unsolved.

Thanks a lot 
