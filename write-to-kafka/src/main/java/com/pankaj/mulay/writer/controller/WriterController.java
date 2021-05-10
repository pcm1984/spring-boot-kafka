package com.pankaj.mulay.writer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pankaj.mulay.writer.kafkamsg.MessageSender;
import com.pankaj.mulay.writer.model.Data;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("kafka")
public class WriterController {

	@Autowired
	private MessageSender kafkaMsgSender;

	@PostMapping("/data")
	public void sendData(@RequestBody Data data){
		log.info("Received Json : " + data);
		kafkaMsgSender.initializeKafkaPrducer();
		kafkaMsgSender.sendJson(data);
	}
}
