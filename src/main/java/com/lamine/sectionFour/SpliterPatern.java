package com.lamine.sectionFour;

import org.apache.camel.builder.RouteBuilder;

public class SpliterPatern extends RouteBuilder {
	@Override
	public void configure() throws Exception {
		// split le message en ligne csv chaque ligne est envoyé à part
		// @formatter:off
		from("file:files/csv")
		.unmarshal().csv().split(body()).
		log(">> ${body}")
		.to("activemq:csvQ");
		
		// @formatter:on

		// @formatter:on

	}

}
