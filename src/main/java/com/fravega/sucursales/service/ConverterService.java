package com.fravega.sucursales.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fravega.sucursales.entities.Sucursal;
import com.fravega.sucursales.modelo.SucursalDto;

@Service
public class ConverterService {

	@Autowired
	private ModelMapper modelMapper;
	
	public Sucursal sucursaDtoToEntity (SucursalDto sucursalDto) {
		return modelMapper.map(sucursalDto,Sucursal.class);
	} 
	
	public SucursalDto sucursalEntityToDto (Sucursal sucursal) {
		return modelMapper.map(sucursal,SucursalDto.class);
	} 
	
}
