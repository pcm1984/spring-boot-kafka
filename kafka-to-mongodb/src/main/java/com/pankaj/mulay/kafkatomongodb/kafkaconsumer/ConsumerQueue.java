package com.pankaj.mulay.kafkatomongodb.kafkaconsumer;

import java.util.LinkedList;
import java.util.Queue;

import org.springframework.stereotype.Component;

import com.pankaj.mulay.kafkatomongodb.model.Data;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class ConsumerQueue {
	private Queue<Data> receivedMsgs = new LinkedList<>();
}
