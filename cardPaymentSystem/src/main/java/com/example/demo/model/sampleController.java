package com.example.demo.model;


import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/jpa")
public class sampleController {
	
	@Autowired 
	private sampleRepository repository;
	
	
	@GetMapping(value="/sample2")
	public List<sampleEntity> findAllSample() {
		return repository.findAll();
	}
	
	@GetMapping(value="/add")
	public sampleEntity addSample(){
//		final sampleEntity sample = sampleEntity.b
		
		return repository.save(null);
	}
	
}
