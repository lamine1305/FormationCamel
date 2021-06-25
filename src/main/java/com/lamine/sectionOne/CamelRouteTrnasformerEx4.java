package com.lamine.sectionOne;

import java.time.LocalDateTime;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


public class CamelRouteTrnasformerEx4 extends RouteBuilder {
	/*
	 * Une methode transform modifie la source de la donnée en entrée Appel d'une
	 * méthode renvoyant un type(string ,int ...) Ou bien appel de la methode
	 * transform
	 */

	@Override
	public void configure() throws Exception {
		// @formatter:off

		from("timer:mon-Timer4")
		.log("${body}")// renvoie null
		.transform().constant("BONJOUR LES AMIS")// Le message est transformé 
		.log("${body}")//renvoie BONJOUR LES AMIS 
		.bean("simpleTransformer")//Appel dune methode de transformation qui renvoie une chaine 
		.log("${body}");// renvoie *** Il est *** " + LocalDateTime.now(); 
		// @formatter:on

		/*
		 * SORTIE : 2021-06-01 22:53:54.684 INFO 7664 --- [er://mon-Timer4] route1 :
		 * null 2021-06-01 22:53:54.685 INFO 7664 --- [er://mon-Timer4] route1 : BONJOUR
		 * LES AMIS 2021-06-01 22:53:54.686 INFO 7664 --- [er://mon-Timer4] route1 : ***
		 * Il est *** 2021-06-
		 */
	}

}

@Component
class SimpleTransformer {

	// Doit renvoyer un type
	public String getHeure() {
		return "*** Il est *** " + LocalDateTime.now();
	}
}