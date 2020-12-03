package com.fravega.sucursales.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fravega.sucursales.annotations.NumberConstraint;
import com.fravega.sucursales.entities.Sucursal;
import com.fravega.sucursales.modelo.SucursalDto;
import com.fravega.sucursales.service.SucursalService;

@RestController
@RequestMapping("/")
@Validated
public class SucursalController {
	
	@Autowired
	SucursalService sucursalService;
	
	@PostMapping
	public ResponseEntity<Sucursal> createSucursal(@Valid @RequestBody SucursalDto sucursalDto){
		
		Sucursal sucursal = sucursalService.saveSucursal(sucursalDto);
		return ResponseEntity.ok(sucursal);
				
	}
	
	@DeleteMapping("/eliminarSucursal/{idSucursal}")
	public ResponseEntity<Sucursal> deleteSucursal(@Valid @NumberConstraint @PathVariable("idSucursal")String idSucursal){
		
		sucursalService.deleteSucursal(new Long(idSucursal));
		return ResponseEntity.ok(null);
				
	}
	
	@GetMapping("/elegirSucursal/{idSucursal}")
	public ResponseEntity<Sucursal> GetSucursal(@Valid @NumberConstraint @PathVariable("idSucursal")String idSucursal){
		
		Optional<Sucursal> optionalSucursal = sucursalService.getByIDSucursal(new Long(idSucursal));
		
		if(optionalSucursal.isPresent()) {
			return ResponseEntity.ok(optionalSucursal.get());
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PutMapping
	public ResponseEntity<Sucursal> updateSucursal(@Valid @RequestBody SucursalDto sucursalDto){
		
		Optional<Sucursal> optionalUpdateSucursal = sucursalService.updateSucursal(sucursalDto);
		
		if(optionalUpdateSucursal.isPresent()) {
			return ResponseEntity.ok(optionalUpdateSucursal.get());
		}else {
			return ResponseEntity.noContent().build();
		}
	}
				
	@GetMapping("/todasLasSucursales")
	public ResponseEntity<Iterable<Sucursal>> GetSucursales(){
		
		Iterable<Sucursal> sucursales = sucursalService.getSucursales();
		
		if (sucursales!=null) {
			return ResponseEntity.ok(sucursales);
		}else {
			return ResponseEntity.noContent().build();

		}
		
		
		
	}
	
	@GetMapping("/busquedaSucursal")
	public ResponseEntity<Sucursal> getSucursalShortDistance(@RequestParam("latitud") Double latitud,
			@RequestParam("longitud") Double longitud){
		
		   Sucursal sucursal =  sucursalService.getShortedDistance(latitud, longitud);
	
		   if (sucursal!=null) {
				return ResponseEntity.ok(sucursal);
			}else {
				return ResponseEntity.noContent().build();

			}
	}
		
		
}
