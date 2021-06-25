package com.lamine.sectionThree;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lamine.beans.Personne;

@RestController
public class MonRestControler {

	@GetMapping("/infos/{nom}/{prenom}/{age}")
	public Personne getInfo(@PathVariable String nom, @PathVariable String prenom, @PathVariable int age) {
		return new Personne(nom, prenom, age);
	}

}
