package co.com.ceiba.parqueadero.persistencia;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.parqueadero.constantes.Constantes;
import co.com.ceiba.parqueadero.dominio.Vehiculo;

@Service
public class RepositorioParqueaderoImpl {
	
	@Autowired
	RepositorioParqueadero repositorioParqueadero;
	
	public void ingresarVehiculo(Vehiculo vehiculo) {
		ParqueaderoEntidad parqueaderoEntidad = ParqueaderoBuilder.convertirAEntidad(vehiculo);
		parqueaderoEntidad.setEstado(Constantes.VEHICULO_ACTIVO);
		repositorioParqueadero.save(parqueaderoEntidad);
	}
	
	public void retirarVehiculo(Vehiculo vehiculo) {
		ParqueaderoEntidad parqueaderoEntidad = repositorioParqueadero.findByPlacaAndTipoVehiculoAndEstado(vehiculo.getPlaca(), 
				vehiculo.getTipoVehiculo(), 
				Constantes.VEHICULO_ACTIVO);
		parqueaderoEntidad.setEstado(Constantes.VEHICULO_INACTIVO);
		parqueaderoEntidad.setValorCobrar(vehiculo.getValorCobrar());
		parqueaderoEntidad.setFechaSalida(vehiculo.getFechaSalida());
		repositorioParqueadero.save(parqueaderoEntidad);
	}
	
	public Vehiculo buscarVehiculo(String placa, String tipoVehiculo, String estado) {
		ParqueaderoEntidad parqueaderoEntidad = repositorioParqueadero.findByPlacaAndTipoVehiculoAndEstado(placa, tipoVehiculo, estado);
		return ParqueaderoBuilder.convertirADominio(parqueaderoEntidad);
	}
	
	public long contarVehiculos(String tipoVehiculo, String estado) {
		return repositorioParqueadero.countByTipoVehiculoAndEstado(tipoVehiculo, estado);
	}
	
	public List<Vehiculo> consultarVehiculos(String estado) {
		List<Vehiculo> parqueadero = new ArrayList<>();
		List<ParqueaderoEntidad> parqueaderoEntidad = repositorioParqueadero.findByEstado(estado);
		for(ParqueaderoEntidad vehiculo : parqueaderoEntidad) {
			parqueadero.add(ParqueaderoBuilder.convertirADominio(vehiculo));
		}
		return parqueadero;
	}
}
