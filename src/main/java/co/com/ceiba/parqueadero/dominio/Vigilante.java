package co.com.ceiba.parqueadero.dominio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import co.com.ceiba.parqueadero.constantes.Constantes;
import co.com.ceiba.parqueadero.persistencia.RepositorioParqueaderoImpl;
import co.com.ceiba.parqueadero.validaciones.CalcularCobro;
import co.com.ceiba.parqueadero.validaciones.Validacion;
import co.com.ceiba.parqueadero.validaciones.ValidarDisponibilidad;
import co.com.ceiba.parqueadero.validaciones.ValidarPlaca;
import co.com.ceiba.parqueadero.validaciones.ValidarVehiculoExiste;

@Service
public class Vigilante {
	
	private List<Validacion> validacionesIngreso = new ArrayList<>();
	private List<Validacion> validacionesRetiro = new ArrayList<>();
	private RepositorioParqueaderoImpl repositorioParqueaderoImpl;
	
	public Vigilante(RepositorioParqueaderoImpl repositorioParqueaderoImpl) {
		this.repositorioParqueaderoImpl = repositorioParqueaderoImpl;
		validacionesIngreso.add(new ValidarDisponibilidad(repositorioParqueaderoImpl));
		validacionesIngreso.add(new ValidarVehiculoExiste(repositorioParqueaderoImpl));
		validacionesIngreso.add(new ValidarPlaca());
		validacionesRetiro.add(new CalcularCobro());
	}
	
	public String ingresarVehiculo(Vehiculo vehiculo) {
		String msg;
		try {
			validacionesIngreso.forEach(validacion -> validacion.realizarValidacion(vehiculo));
			repositorioParqueaderoImpl.ingresarVehiculo(vehiculo);
			msg = "El vehículo con placa "+vehiculo.getPlaca()+" se ingresó exitosamente";
		} catch (Exception e) {
			msg = e.getMessage();
		}
		return msg;
	}
	
	public String retirarVehiculo(String placa, String tipoVehiculo) {
		Vehiculo vehiculo = repositorioParqueaderoImpl.buscarVehiculo(placa, tipoVehiculo, Constantes.VEHICULO_ACTIVO);
		vehiculo.setFechaSalida(LocalDateTime.now());
		validacionesRetiro.forEach(validacionSalida -> validacionSalida.realizarValidacion(vehiculo));
		repositorioParqueaderoImpl.retirarVehiculo(vehiculo);
		return "El vehículo con placa "+vehiculo.getPlaca()+" se retiró exitosamente. El valor a cobrar es: "+vehiculo.getValorCobrar();
	}
	
	public List<Vehiculo> consultarVehiculos(){
		return repositorioParqueaderoImpl.consultarVehiculos(Constantes.VEHICULO_ACTIVO);
	}

}
