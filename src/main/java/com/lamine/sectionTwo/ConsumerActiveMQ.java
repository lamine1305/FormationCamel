package com.lamine.sectionTwo;

import org.apache.camel.builder.RouteBuilder;

public class ConsumerActiveMQ extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// coonsomme le message depuis la file activemq ma_queue
		from("activemq:ma_queue")
				// redirige vers un fichier
				.to("file:my_file")
				// redirige vers un log
				.to("log:Meslog");

	}

}
