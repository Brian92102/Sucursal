package com.fravega.sucursales.repository;

import org.springframework.data.repository.CrudRepository;

import com.fravega.sucursales.entities.Sucursal;

public interface SucursalRepository extends CrudRepository<Sucursal, Long> {
	

}
