package com.pankaj.mulay.kafkatomongodb.controller;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pankaj.mulay.kafkatomongodb.kafkaconsumer.ConsumerQueue;
import com.pankaj.mulay.kafkatomongodb.model.Data;
import com.pankaj.mulay.kafkatomongodb.repository.KafkaToMongodbRepository;
@RestController
@RequestMapping("kafka")
public class KafkaToMongodbController {

	@Autowired
	ConsumerQueue kafkaReceiver;

        @Autowired
        KafkaToMongodbRepository mongodbRepository;

	@PostMapping("/msg")
	public Data getMsg(){
		Data receivedData = kafkaReceiver.getReceivedMsgs().remove();
		kafkaReceiver.getReceivedMsgs().clear();
                Data savedData;
                try {
     savedData = mongodbRepository.save(receivedData);
    } catch (Exception e) {
      //TODO return ResponseEntity<>
      return null;
    }
		return savedData;
	}
}
