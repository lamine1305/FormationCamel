package com.lamine.sectionThree;

import org.apache.camel.builder.RouteBuilder;

public class ChoiceRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// @formatter:off
		from("file:files/out")
		.transform().body(String.class)
		.choice()
			.when(simple("${file:ext} ends with 'json'"))
					.log("Fichier json")
					.to("file:files/json")
			.when(simple("${file:ext} ends with 'xml'"))
					.log("Fichier xml")
			.when(simple("${body} contains 'Lamine'"))	
					.log("Fichier Non xml mais contient Lamine")
			.otherwise()
					.log("fichier avec une extension inconnue")
			.end()
			.log("${messageHistory} \n ${headers.CamelFileAbsolutePath} ");
		// @formatter:on
	}

}
