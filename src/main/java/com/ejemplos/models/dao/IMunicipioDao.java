package com.ejemplos.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.ejemplos.models.entity.Municipio;

public interface IMunicipioDao extends CrudRepository<Municipio,String>{
	
}
