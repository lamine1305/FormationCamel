package com.lamine.sectionTwo;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import com.lamine.beans.Personne;

public class ConsumerKafka extends RouteBuilder {

	Personne personne;

	/*
	 * Message envoyé depuis files/json vers le topic "My_kafka_topic" Puis le
	 * message est mapé en objet personne puis tansfomer le nom et enfin envoyé vers
	 * activemq
	 */
	@Override
	public void configure() throws Exception {
		// @formatter:off
		from("file:files/json")
		.log("${body}")
		.to("kafka:My_kafka_topic")
		.unmarshal().json(JsonLibrary.Jackson,Personne.class)
		.bean("transformer")
		.to("activemq:kafka_mq")
		.log("${body}")
		;
		// @formatter:on
	}
}

@Component
class Transformer {
	public Personne passerNomMaj(Personne p) {
		p.setNom(p.getNom().toLowerCase());
		return p;
	}
}
