package com.ejemplos.models.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the sendero database table.
 * 
 */
@Entity
@Table(name="sendero")
@NamedQuery(name="Sendero.findAll", query="SELECT s FROM Sendero s")
public class Sendero implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cod_sendero")
	private String codSendero;

	private String dificultad;

	private int distancia;

	private String nombre;

	//bi-directional many-to-one association to Municipio
	@ManyToOne
	@JoinColumn(name="cod_municipio")
	private Municipio municipio;

	//bi-directional many-to-one association to Tramo
	@OneToMany(mappedBy="sendero")
	private List<Tramo> tramos;

	public Sendero() {
	}

	public String getCodSendero() {
		return this.codSendero;
	}

	public void setCodSendero(String codSendero) {
		this.codSendero = codSendero;
	}

	public String getDificultad() {
		return this.dificultad;
	}

	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}

	public int getDistancia() {
		return this.distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Municipio getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public List<Tramo> getTramos() {
		return this.tramos;
	}

	public void setTramos(List<Tramo> tramos) {
		this.tramos = tramos;
	}

	public Tramo addTramo(Tramo tramo) {
		getTramos().add(tramo);
		tramo.setSendero(this);

		return tramo;
	}

	public Tramo removeTramo(Tramo tramo) {
		getTramos().remove(tramo);
		tramo.setSendero(null);

		return tramo;
	}

}