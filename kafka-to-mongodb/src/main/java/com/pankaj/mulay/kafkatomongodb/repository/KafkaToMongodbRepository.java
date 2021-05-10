package com.pankaj.mulay.kafkatomongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pankaj.mulay.kafkatomongodb.model.Data;

public interface KafkaToMongodbRepository extends MongoRepository<Data, String> {
}
