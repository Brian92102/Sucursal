package com.fravega.sucursales.modelo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class SucursalDto {
	
	private Long idSucursal;
	
	private String direccion;
	
	@NotNull
	@Min(value = -90, message = "La longitud no puede ser menor a -90")
	@Max(value = 90, message = "La longitud no puede ser mayor a 90")
	private Double latitud; 
	
	@NotNull
	@Min(value = -180, message = "La longitud no puede ser menor a -180")
	@Max(value = 180, message = "La longitud no puede ser mayor a 180")
	private Double longitud;
	
	
	public SucursalDto(Long idSucursal, String direccion,
			@NotNull @Min(value = -90, message = "La longitud no puede ser menor a -90") @Max(value = 90, message = "La longitud no puede ser mayor a 90") Double latitud,
			@NotNull @Min(value = -180, message = "La longitud no puede ser menor a -180") @Max(value = 180, message = "La longitud no puede ser mayor a 180") Double longitud) {
		super();
		this.idSucursal = idSucursal;
		this.direccion = direccion;
		this.latitud = latitud;
		this.longitud = longitud;
	}
	
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
	
	
	

	
}
