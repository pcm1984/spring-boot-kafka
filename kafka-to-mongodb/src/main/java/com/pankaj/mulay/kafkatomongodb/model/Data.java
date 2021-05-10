package com.pankaj.mulay.kafkatomongodb.model;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class Data {
	private String key;
	private String value;
}
