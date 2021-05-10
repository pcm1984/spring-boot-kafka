package com.pankaj.mulay.writer.kafkamsg;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.pankaj.mulay.writer.config.PropertiesConfig;
import com.pankaj.mulay.writer.model.Data;

import lombok.extern.slf4j.Slf4j;

/**
 * Producer using Apache configuration for sending messages
 */
@Component
@Slf4j
public class MessageSender {

	KafkaProducer<String,String> msgKafkaProducer;

	@Autowired
	PropertiesConfig config;


	public void initializeKafkaPrducer(){
		if(msgKafkaProducer == null) {
			Properties properties = getProperties();
			msgKafkaProducer = new KafkaProducer<String, String>(properties);
		}
	}

	private Properties getProperties(){
		Properties properties = new Properties();
		properties.put("bootstrap.servers", config.getKafkaServer());
		properties.put("key.serializer", config.getKey());
		properties.put("value.serializer", config.getMsgValue());
		return properties;
	}

	public void sendJson(Data data){
		initializeKafkaPrducer();
		log.info("Message before sending to kafka " + data.toString());
		String topic = config.getJsonTopic();
		log.info("Topic : " + topic);

		ProducerRecord<String,String> msgProducer = new ProducerRecord<>(topic,topic,data.toString());
		msgKafkaProducer.send(msgProducer);
	}
}
