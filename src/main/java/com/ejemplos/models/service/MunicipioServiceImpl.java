package com.ejemplos.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejemplos.models.dao.IMunicipioDao;
import com.ejemplos.models.dao.ISenderoDao;
import com.ejemplos.models.entity.Municipio;
import com.ejemplos.models.entity.Sendero;

@Service
public class MunicipioServiceImpl {
	

	@Autowired
	private IMunicipioDao municipioDao;

	@Transactional(readOnly=true)
	public List<Municipio> TMuni(){
		return (List<Municipio>)municipioDao.findAll();
	}
	

}
