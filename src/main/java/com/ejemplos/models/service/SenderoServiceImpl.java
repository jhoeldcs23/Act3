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
public class SenderoServiceImpl {
	
	@Autowired
	private ISenderoDao senderoDao;
	@Autowired
	private IMunicipioDao municipioDao;

	@Transactional(readOnly=true)
	public List<Sendero> TSendero(){
		return (List<Sendero>)senderoDao.findAll();
	}
	@Transactional(readOnly=true)
	public List<Municipio> TMuni(){
		return (List<Municipio>)municipioDao.findAll();
	}
	@Transactional
	public void save(Sendero sendero) {
		senderoDao.save(sendero);
	}
	@Transactional
	public void save(Municipio muni) {
		municipioDao.save(muni);
	}
}
