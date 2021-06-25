package com.lamine.sectionOne;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

public class CamelRouteProcessorEx3 extends RouteBuilder {
	/*
	 * Un processeur ne modifie pas la source de la donnée en entrée /*Transform :
	 * modifie la source de données en entrée
	 */

	@Override
	public void configure() throws Exception {
		// @formatter:off

		from("timer:mon-Timer3")
		.log("${body}")// renvoie null
		.bean("simpleProcessor")//Appel dune methode de processing qui ne fait qu'afficher un log et ne modifie pas la source de donnée 
		.log("${body}");// renvoie null
		// @formatter:on

	}

}


@Component
class SimpleProcessor {

	public void getLog() {
		Logger logger = LoggerFactory.getLogger(SimpleProcessor.class);
		logger.info("ceci est un processeur qui affiche un message sans modifier le message en entrée de camel . . . ");
	}
}