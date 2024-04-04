package com.ejemplos.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.ejemplos.models.entity.Sendero;

public interface ISenderoDao extends CrudRepository<Sendero,String>{
	
}
