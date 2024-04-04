package com.ejemplos.models.entity;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the tramo database table.
 * 
 */
@Entity
@Table(name="tramo")
@NamedQuery(name="Tramo.findAll", query="SELECT t FROM Tramo t")
public class Tramo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cod_tramo")
	private long codTramo;

	private int dificultad;

	@Column(name="km_fin")
	private float kmFin;

	@Column(name="km_inicio")
	private float kmInicio;

	//bi-directional many-to-one association to Sendero
	@ManyToOne
	@JoinColumn(name="cod_sendero")
	private Sendero sendero;

	public Tramo() {
	}

	public long getCodTramo() {
		return this.codTramo;
	}

	public void setCodTramo(long codTramo) {
		this.codTramo = codTramo;
	}

	public int getDificultad() {
		return this.dificultad;
	}

	public void setDificultad(int dificultad) {
		this.dificultad = dificultad;
	}

	public float getKmFin() {
		return this.kmFin;
	}

	public void setKmFin(float kmFin) {
		this.kmFin = kmFin;
	}

	public float getKmInicio() {
		return this.kmInicio;
	}

	public void setKmInicio(float kmInicio) {
		this.kmInicio = kmInicio;
	}

	public Sendero getSendero() {
		return this.sendero;
	}

	public void setSendero(Sendero sendero) {
		this.sendero = sendero;
	}

}