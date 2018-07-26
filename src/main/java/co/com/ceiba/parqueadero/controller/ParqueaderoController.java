package co.com.ceiba.parqueadero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.parqueadero.dominio.Parqueadero;
import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.dominio.Vigilante;
import co.com.ceiba.parqueadero.persistencia.RepositorioParqueaderoImpl;

@RestController
@CrossOrigin
@RequestMapping("/v1")
public class ParqueaderoController {
	
	@Autowired
	RepositorioParqueaderoImpl repositorioParqueaderoImpl;
	
	@Autowired
	Vigilante vigilante;
	
	@PostMapping(value = "/ingresarVehiculo")
	public Message ingresarVehiculo(@RequestBody Parqueadero parqueadero) {
		return new Message(vigilante.ingresarVehiculo(parqueadero.crearVehiculo()));
	}
	
	@PutMapping(value = "/retirarVehiculo")
	public Message retirarVehiculo(@RequestBody Vehiculo vehiculo) {
		return new Message(vigilante.retirarVehiculo(vehiculo.getPlaca(), vehiculo.getTipoVehiculo()));		
	}
	
	@GetMapping("/consultarVehiculos")
	public List<Vehiculo> consultarVehiculos(){
		return vigilante.consultarVehiculos();
	}
}
