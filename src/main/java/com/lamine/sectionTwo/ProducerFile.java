package com.lamine.sectionTwo;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lamine.beans.Personne;

public class ProducerFile extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// @formatter:off
		from("file:files/json/")
		.unmarshal().json(JsonLibrary.Jackson,Personne.class)
		.bean("correcteurProcesseur")
		.log("${body}")
		.bean("correcteurTransformer")
		.to("activemq:file_json_personne");
		// @formatter:on

		// @formatter:off
		from("file:files/xml/")
		.unmarshal().jacksonxml(Personne.class)
		.bean("correcteurProcesseur")
		.log("${body}")
		.bean("correcteurTransformer")
		.to("activemq:file_xml_personne");
		// @formatter:on

	}
}

@Component
class CorrecteurTransformer {
	Logger logger = LoggerFactory.getLogger(CorrecteurTransformer.class);

	// une méthode qui modifie le message : TRANSFORMER
	public Personne corrigerAge(Personne p) {
		p.corrigerAge(5);
		logger.info("--------> " + p.toString());
		return p;
	}
}

@Component
class CorrecteurProcesseur {
	Logger logger = LoggerFactory.getLogger(CorrecteurProcesseur.class);

	// une méthode qui ne modifie pas le message : processor
	public void corrigerAge(Personne p) {

		logger.info("--------> L'age de" + p.toString() + " doit etre corrigé");
	}
}
