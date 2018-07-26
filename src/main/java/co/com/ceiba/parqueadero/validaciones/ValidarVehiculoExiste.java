package co.com.ceiba.parqueadero.validaciones;

import co.com.ceiba.parqueadero.constantes.Constantes;
import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.excepcion.IngresoExcepcion;
import co.com.ceiba.parqueadero.persistencia.RepositorioParqueaderoImpl;

public class ValidarVehiculoExiste implements Validacion {
	
RepositorioParqueaderoImpl repositorioParqueaderoImpl;
	
	public ValidarVehiculoExiste(RepositorioParqueaderoImpl repositorioParqueaderoImpl) {
		this.repositorioParqueaderoImpl = repositorioParqueaderoImpl;
	}

	@Override
	public void realizarValidacion(Vehiculo vehiculo) {
		if (repositorioParqueaderoImpl.buscarVehiculo(vehiculo.getPlaca(), vehiculo.getTipoVehiculo(), Constantes.VEHICULO_ACTIVO)!= null) {
			throw new IngresoExcepcion(Constantes.MENSAJE_VEHICULO_YA_REGISTRADO);
		}		
	}

}
