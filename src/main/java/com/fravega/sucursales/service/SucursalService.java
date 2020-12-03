package com.fravega.sucursales.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.geotools.referencing.GeodeticCalculator;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fravega.sucursales.entities.Sucursal;
import com.fravega.sucursales.modelo.SucursalDto;
import com.fravega.sucursales.repository.SucursalRepository;

@Service
public class SucursalService {
	
	private static final org.slf4j.Logger LOG =  LoggerFactory.getLogger(SucursalService.class);
	
	@Autowired
	SucursalRepository sucursalRepository;
	
	@Autowired
	ConverterService converterService;
	
	private static Map<String,Sucursal>sucursales=new HashMap<String,Sucursal>();

	
	public Sucursal saveSucursal(SucursalDto sucursalDto) {

		Sucursal sucursal = converterService.sucursaDtoToEntity(sucursalDto);
		return sucursalRepository.save(sucursal);
	}
	
	public void deleteSucursal(Long idSucursal) {
		LOG.info("Borrando Sucursal con ID_SUCURSAL "+idSucursal);
		
		try {
			sucursalRepository.deleteById(idSucursal);
		} catch (Exception e) {
			LOG.error("No existe una sucursal con el id "+idSucursal);
			e.printStackTrace();
		}
	}
	
	public Optional<Sucursal> getByIDSucursal(Long idSucursal) {
		return sucursalRepository.findById(idSucursal);
	}
	
	public Optional<Sucursal> updateSucursal(SucursalDto sucursalDto){
		
		Optional <Sucursal> optionalSucursal = sucursalRepository.findById(sucursalDto.getIdSucursal());
		
		if (optionalSucursal.isPresent()) {
			Sucursal updateSucursal = converterService.sucursaDtoToEntity(sucursalDto);
			sucursalRepository.save(updateSucursal);
		}else {
			
		}
		
		return optionalSucursal;
	}

	public Iterable<Sucursal> getSucursales(){
		return sucursalRepository.findAll();
	}
	
	public Double getDistance(Double latituInicial, Double longitudInicial,Double latitudDestino, Double longitudDestino) {

	    GeodeticCalculator calc = new GeodeticCalculator();
	    calc.setStartingGeographicPoint(longitudInicial, latituInicial);
	    calc.setDestinationGeographicPoint(longitudDestino, latitudDestino);

	    Double distance = calc.getOrthodromicDistance();
	    
	    return distance;

	   
	}
	
	public Sucursal getShortedDistance(Double latitud, Double longitud) {
		LoadSucursales();
		Double min=null;
		String key="";
		
				for (Entry<String, Sucursal> entry : sucursales.entrySet()) {
					Double distance = getDistance(entry.getValue().getLatitud(),entry.getValue().getLongitud(),latitud,longitud);
					if (min==null||distance<min) {
						min=distance;
						key = entry.getKey();
					}
					
				}
				return sucursales.get(key);
	}
	
	
	
	public void LoadSucursales() {
		
		 ArrayList<Sucursal> sucursalesBuscadas = new ArrayList<Sucursal>();
		 
		  sucursalesBuscadas = (ArrayList<Sucursal>) sucursalRepository.findAll();
		  
		  for(Sucursal sucursal:sucursalesBuscadas) {
			  String key = sucursal.getIdSucursal().toString();
			  
			  if (sucursales.get(key)==null) {
				  sucursales.put(key, sucursal);
			  }
			  
		  }
		 
		
		
	}
}
