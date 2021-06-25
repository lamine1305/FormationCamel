package com.lamine.sectionOne;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import com.lamine.beans.GetCurrentTimeBean;

public class CamelRouteTimerEx2 extends RouteBuilder {

	@Autowired
	GetCurrentTimeBean getCurrentTimeBean;

	@Override
	public void configure() throws Exception {
		// @formatter:off
		
		/* in : Timer out : log* msg :constante */
		from("timer:second-timer")
		.bean("getCurrentTimeBean","getTime")
		.to("log:mySecondLog"); 

		// @formatter:on
		/* sortie */
		/*
		 * 
		 * 2021-06-01 21:57:30.545 INFO 2624 --- [://second-timer] mySecondLog :
		 * Exchange[ExchangePattern: InOnly, BodyType: String, Body: Il est
		 * 2021-06-01T21:57:30.545611300]
		 * 
		 */
	}

}