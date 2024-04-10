package com.ejemplos.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejemplos.models.dao.ISenderoDao;
import com.ejemplos.models.entity.Sendero;

@Service
public class SenderoServiceImpl {
	
	@Autowired
	private ISenderoDao senderoDao;


	@Transactional(readOnly=true)
	public List<Sendero> TSendero(){
		return (List<Sendero>)senderoDao.findAll();
	}

	@Transactional
	public void save(Sendero sendero) {
		senderoDao.save(sendero);
	}
	
	public Sendero findOne(String id) {
		return senderoDao.findById(id).orElse(null);
	}
	
	public void delete(String id) {
		senderoDao.deleteById(id);
	}
}
