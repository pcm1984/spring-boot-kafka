package com.pankaj.mulay.kafkatomongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pankaj.mulay.kafkatomongodb.executor.KafkaExecutorService;

@SpringBootApplication
public class KafkaToMongodbApplication implements CommandLineRunner {

	@Autowired
	KafkaExecutorService executorService;

	public static void main(String[] args) {
		SpringApplication.run(KafkaToMongodbApplication.class, args);
	}

	/**
	 * Callback used to run the bean.
	 *
	 * @param args incoming main method arguments
	 * @throws Exception on error
	 */
	@Override
	public void run(String... args) throws Exception {
		executorService.execute();
	}
}
