package com.lamine.sectionThree;

import org.apache.camel.builder.RouteBuilder;

public class RoutePerso extends RouteBuilder {

	/*
	 * Construction d'une route avec direct:// et envoi du message vers et depuis
	 * cette route
	 */

	@Override
	public void configure() throws Exception {
		// @formatter:off

		from("file:files/xml")
		.log("${body}")
		.to("direct://ma_route");

		//Envoi du message depuis la nouvelle route vers ActiveMQ
		from("direct:ma_route")
		.log("${body}")
		.to("activemq:fileMsge");
		// @formatter:on

	}

}
