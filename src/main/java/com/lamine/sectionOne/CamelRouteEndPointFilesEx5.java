package com.lamine.sectionOne;

import org.apache.camel.builder.RouteBuilder;

public class CamelRouteEndPointFilesEx5 extends RouteBuilder {
	/*
	 * Lentrée est unn repertoire contenant des fichier
	 */

	@Override
	public void configure() throws Exception {
		// @formatter:off

		from("file:files/input")
		.log("${body}")
		.to("file:files/output");
		// @formatter:on

	}

}