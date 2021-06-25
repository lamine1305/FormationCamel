package com.lamine.sectionTwo;

import org.apache.camel.builder.RouteBuilder;

public class ProducerActiveMQ extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// @formatter:off
		from("timer:mytimer?period=500")
		.log("${body}")
		.transform()
		.constant("mon message")
		.log("${body}")
		.to("activemq:ma_queue");
		// @formatter:on
	}

}
