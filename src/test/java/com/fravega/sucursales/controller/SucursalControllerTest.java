package com.fravega.sucursales.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fravega.sucursales.entities.Sucursal;
import com.fravega.sucursales.service.SucursalService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SucursalController.class)
public class SucursalControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private SucursalService sucursalService;
	
	//Las coordenas son de Del Viso, Pilar. La sucursal mas cercana es la de tortuguitas
	Double latitudCliente = -34.42658683584383;
	Double longitudCliente = -58.77989424316492;
	
	String ejemploJsonSucursalTortuguitas = "{\r\n" + 
			"    \"direccion\":\"Tortuguitas\",\r\n" + 
			"    \"latitud\":-34.452138593957244,\r\n" + 
			"    \"longitud\":-58.72684065850388\r\n" + 
			"}";
	
	String ejemploSucursalNoValida = "{\r\n" + 
			"    \"direccion\":\"Tortugas\",\r\n" + 
			"    \"latitud\":-94.452138593957244,\r\n" + 
			"    \"longitud\":-58.72684065850388\r\n" + 
			"}";
		
	@Test
	public void createSucursal() throws Exception {
		
				RequestBuilder requestBuilder = MockMvcRequestBuilders
						.post("/")
						.accept(MediaType.APPLICATION_JSON).content(ejemploJsonSucursalTortuguitas)
						.contentType(MediaType.APPLICATION_JSON);

				MvcResult result = mockMvc.perform(requestBuilder).andReturn();

				MockHttpServletResponse response = result.getResponse();

				assertEquals(HttpStatus.OK.value(), response.getStatus()); 

	}
	
	@Test
	public void createSucursalNoValide() throws Exception {
		
				RequestBuilder requestBuilder = MockMvcRequestBuilders
						.post("/")
						.accept(MediaType.APPLICATION_JSON).content(ejemploSucursalNoValida)
						.contentType(MediaType.APPLICATION_JSON);

				MvcResult result = mockMvc.perform(requestBuilder).andReturn();

				MockHttpServletResponse response = result.getResponse();

				assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus()); 

	}

	@Test
	public void calculateDistance() throws Exception{
		
		Sucursal sucursalTortuguitas = new Sucursal();
		sucursalTortuguitas.setIdSucursal(1L);
		sucursalTortuguitas.setDireccion("Tortuguitas");
		sucursalTortuguitas.setLatitud(-34.452138593957244);
		sucursalTortuguitas.setLongitud(-58.72684065850388);
		
		
		Mockito.when(
				sucursalService.getShortedDistance(latitudCliente, longitudCliente)).thenReturn(sucursalTortuguitas);

		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/busquedaSucursal").accept(
				MediaType.APPLICATION_JSON).param("latitud", latitudCliente.toString())
				.param("longitud", longitudCliente.toString());

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String jsonSucursalEsperado = "{\r\n" + 
				"    \"idSucursal\":1,\r\n" + 
				"	\"direccion\":\"Tortuguitas\",\r\n" + 
				"    \"latitud\":-34.452138593957244,\r\n" + 
				"    \"longitud\":-58.72684065850388\r\n" + 
				"}";

		JSONAssert.assertEquals(jsonSucursalEsperado, result.getResponse()
				.getContentAsString(), false);
		
		
	}
	
}


