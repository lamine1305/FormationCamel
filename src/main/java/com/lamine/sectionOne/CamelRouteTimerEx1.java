package com.lamine.sectionOne;

import java.time.LocalDateTime;

import org.apache.camel.builder.RouteBuilder;

public class CamelRouteTimerEx1 extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// @formatter:off

		/* in : Timer out : log* msg :constante */
		from("timer:first-timer")
		.transform().constant("salut " + LocalDateTime.now())
		.to("log:myFistLog"); 

		// @formatter:on
		/* sortie */
		/*
		 * 2021-06-01 21:20:05.061 INFO 6016 --- [r://first-timer] myFistLog :
		 * Exchange[ExchangePattern: InOnly, BodyType: String, Body: salut
		 * 2021-06-01T21:19:44.837035300]
		 */
	}

}