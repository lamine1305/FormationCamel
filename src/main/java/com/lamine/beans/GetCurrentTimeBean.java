package com.lamine.beans;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class GetCurrentTimeBean {

	public String getTime() {
		return "Il est " + LocalDateTime.now();

	}
}
