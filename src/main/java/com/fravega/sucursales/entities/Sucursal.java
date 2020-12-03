package com.fravega.sucursales.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="SUCURSAL")
public class Sucursal implements Serializable {

	private static final long serialVersionUID = -1556983411424838682L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sucursalSeq")
	@SequenceGenerator(name="sucursalSeq", sequenceName = "SUCURSAL_SEQ", allocationSize = 1)
	@Column(name = "ID_SUCURSAL")
	private Long idSucursal;
	
	@Column(name="DIRECCION")
	private String direccion;
	
	@Column(name= "LATITUD")
	private Double latitud; 
	
	@Column(name= "LONGITUD")
	private Double longitud;

	

	public Long getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(Long idSucursal) {
		this.idSucursal = idSucursal;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
