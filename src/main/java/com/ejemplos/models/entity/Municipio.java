package com.ejemplos.models.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the municipio database table.
 * 
 */
@Entity
@Table(name="municipio")
@NamedQuery(name="Municipio.findAll", query="SELECT m FROM Municipio m")
public class Municipio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cod_municipio")
	private String codMunicipio;

	@Column(name="nombre_municipio")
	private String nombreMunicipio;

	//bi-directional many-to-one association to Sendero
	@OneToMany(mappedBy="municipio")
	private List<Sendero> senderos;

	public Municipio() {
	}

	public String getCodMunicipio() {
		return this.codMunicipio;
	}

	public void setCodMunicipio(String codMunicipio) {
		this.codMunicipio = codMunicipio;
	}

	public String getNombreMunicipio() {
		return this.nombreMunicipio;
	}

	public void setNombreMunicipio(String nombreMunicipio) {
		this.nombreMunicipio = nombreMunicipio;
	}

	public List<Sendero> getSenderos() {
		return this.senderos;
	}

	public void setSenderos(List<Sendero> senderos) {
		this.senderos = senderos;
	}

	public Sendero addSendero(Sendero sendero) {
		getSenderos().add(sendero);
		sendero.setMunicipio(this);

		return sendero;
	}

	public Sendero removeSendero(Sendero sendero) {
		getSenderos().remove(sendero);
		sendero.setMunicipio(null);

		return sendero;
	}

}