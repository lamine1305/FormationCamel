package com.lamine.sectionFour;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Body;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.el.stream.Stream;

@Component

public class MySpliterPatern extends RouteBuilder {
	@Autowired
	SpliterClass mySpliter;

	@Override
	public void configure() throws Exception {
		// split le body en paquet séparés par ; chacun envoyé à part
		// @formatter:off
		from("file:files/csv")
		.convertBodyTo(String.class)
		.split(body(),";")
		.log(">> ${body}")
		.to("activemq:csvQ");
		
		//Fait la meme chose en appelant une methode de split spécifique
		from("file:files/out")
		.convertBodyTo(String.class)
		.split(method(mySpliter))
		.log(">> ${body}")
		.multicast()
		.to("activemq:csvQ","file:files/in");
		// @formatter:on
	}

}

@Component
class SpliterClass {

	public List<String> spliter(@Body String body) {
		ArrayList<String> list = new ArrayList();
		Stream s;
		for (String x : body.split(";"))
			list.add(x + "*");
		return list;
	}

}