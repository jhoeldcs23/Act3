package com.ejemplos.controllers;

import java.util.ArrayList;
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
import com.ejemplos.models.service.MunicipioServiceImpl;
import com.ejemplos.models.service.SenderoServiceImpl;

import jakarta.validation.Valid;

@Controller
public class SenderoController {

	@Autowired
	private SenderoServiceImpl senderoService;
	@Autowired
	private MunicipioServiceImpl muniService;
	
	@GetMapping("/form")
	public String mostrarFormulario(Model model) {
		model.addAttribute("sendero", new Sendero());
		model.addAttribute("municipios", muniService.TMuni());
		return "form";
	}

	@PostMapping("/mostrar")
	public String guardarSendero(@Valid Sendero sendero,BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("municipios", muniService.TMuni());
	        return "form";
	    }
		//Aqui compruebo que el codigo sendero no se repita
		Sendero existe = senderoService.findOne(sendero.getCodSendero());
	    if (existe != null) {
	        // El código del sendero ya existe, rechazamos la validación y mostramos un mensaje de error
	        bindingResult.rejectValue("codSendero", "error.sendero", "El código del sendero ya existe");
	        model.addAttribute("municipios", muniService.TMuni());
	        return "form";
		}
		senderoService.save(sendero);
	    return "redirect:/listado";
	    
	}
	
	@GetMapping("/listado")
	public String mostrarSendero(Model model) {
		model.addAttribute("senderos",senderoService.TSendero() );
		return "listar";
	}

}
