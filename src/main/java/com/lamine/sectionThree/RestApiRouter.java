package com.lamine.sectionThree;

import org.apache.camel.builder.RouteBuilder;

public class RestApiRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		restConfiguration().host("localhost").port(8080);
		// @formatter:off
		//Appel d'une API rest
		from("timer:timer-rest-api?period=2000")
		.log("${body}")
		.setHeader("nom",()->"MEHIDI")
		.setHeader("prenom",()->"Lamine")
		.setHeader("age",()->"34")
		.to("rest:get:/infos/{nom}/{prenom}/{age}")
		.log("${body}");
		// @formatter:on
	}

}
