package com.lamine.sectionTwo;

import org.apache.camel.builder.RouteBuilder;

public class ProducerKafka extends RouteBuilder {

	/*
	 * Message envoyé depuis le topic "My_kafka_topic" vers le dossier files/output
	 */
	@Override
	public void configure() throws Exception {
		// @formatter:off
		from("kafka:My_kafka_topic")

		.to("file:files/output")
		.to("activemq:file_kafka");
		
		// @formatter:on
	}

}
