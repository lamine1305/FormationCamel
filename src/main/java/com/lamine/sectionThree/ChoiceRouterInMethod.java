package com.lamine.sectionThree;

import java.util.Map;

import org.apache.camel.Body;
import org.apache.camel.Headers;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class ChoiceRouterInMethod extends RouteBuilder {

	@Autowired
	BeanDecider beanDecider;

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
		.when(method(beanDecider))
		.log("Fichier Non xml mais contient Lamine")
		.otherwise()
		.log("fichier avec une extension inconnue")
		.end()
		.log("${messageHistory} \n ${headers.CamelFileAbsolutePath} ");
		// @formatter:on
	}

}

@Component
class BeanDecider {
	Logger log = LoggerFactory.getLogger(this.getClass());

	public boolean decider(@Body String body, @Headers Map<String, String> headers) {
		log.info(">>>>> headers :" + headers + "body :" + body + "<<<<<<<");
		if (body.contains("lamine"))
			return false;
		else
			return true;
	}

}
