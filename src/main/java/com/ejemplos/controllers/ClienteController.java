package com.ejemplos.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ejemplos.models.entity.Sendero;
import com.ejemplos.models.entity.Municipio;
import com.ejemplos.models.service.SenderoServiceImpl;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("cliente")
public class ClienteController {
	//autoinyecta el bean
	//Es decir busca un componente registrado de Spring que lo 
	//implementa y localiza @Service
	@Autowired
	private SenderoServiceImpl clienteService;
	
	
	@GetMapping(value="/")
	public String index() {
		return("index");
	}
	
	@GetMapping(value="/listar")
	public String listar(Model model) {
		model.addAttribute("titulo","Listado de clientes");
		model.addAttribute("clientes",clienteService.TMuni());
		return ("listar");
	}
	/*
	@RequestMapping(value="/form")
	public String crear(Map<String,Object> model, BindingResult result) {
		if(result.hasErrors()) {
			return("form");
		}
		Cliente cliente=new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Formulario de Cliente");
		return ("form");
	}*/
	
	@RequestMapping(value="/form")
	public String crear(@Valid Cliente cliente,BindingResult result, Model model, SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cliente");
			return("form");
		}
		//model.addAttribute("cliente", cliente);
		clienteService.save(cliente);
		//model.addAttribute("titulo", "Formulario de Cliente");
		return ("redirect:/listar");
	}
	
	@RequestMapping(value="/listar/{id}")
	public String buscar(@PathVariable(value = "id") Long id,Map<String,Object> model) {
		Cliente cliente=clienteService.findOne(id);
		model.put("cliente", cliente);
		model.put("titulo", "Editar Cliente");
		
		//***********************
		//Hace falta @SessionAtributes("cliente") pq al llamar a metodo save
		//perderiamos el id y crearia un nuevo registro con los mismos datos
		//y no actualizaria
		//***********************
		return ("form");
	}
	
	
	@PostMapping(value="/form")
	public String crearCliente(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status) {
		
		//@Valid su entidad activará una función de verificación.
		//utilice un objeto BindingResult como
		//argumento para un método de validación de un Validator dentro de un controlador.
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cliente");
			return "form";
		}
		
		clienteService.save(cliente);
		//al ejecutar setComplete() elimina el objeto cliente de la sesion
		status.setComplete();
		//si no ponemos redirect al metodo, carga la vista listar y "clientes" está vacío
		return "redirect:listar";
	}
	
	@GetMapping(value="/form/{id}")
	public String editarCliente(Model model, @PathVariable Long id) {
		
		Cliente cliente = clienteService.findOne(id);
		//paso datos a la vista
		model.addAttribute("cliente", cliente);
		model.addAttribute("titulo", "Editar cliente "+cliente.getId());
		//IMPORTANTE: Hace falta el @SessionAtributes("cliente") porque al llamar al metodo save
		//perderiamos el id y crearia un nuevo registro con los mismos datos y no actualizaria 
		return "form";
		}
	
	@GetMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable Long id) {
		clienteService.delete(id);
		
		return "redirect:/listar";
	}
	

}
