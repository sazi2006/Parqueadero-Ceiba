package co.com.ceiba.parqueadero.validaciones;

import co.com.ceiba.parqueadero.constantes.Constantes;
import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.excepcion.IngresoExcepcion;
import co.com.ceiba.parqueadero.persistencia.RepositorioParqueaderoImpl;

public class ValidarDisponibilidad implements Validacion{
	
	RepositorioParqueaderoImpl repositorioParqueaderoImpl;
	
	public ValidarDisponibilidad(RepositorioParqueaderoImpl repositorioParqueaderoImpl) {		
		this.repositorioParqueaderoImpl = repositorioParqueaderoImpl;
	}

	@Override
	public void realizarValidacion(Vehiculo vehiculo) {
		long cantidadVehiculos = repositorioParqueaderoImpl.contarVehiculos(vehiculo.getTipoVehiculo(), Constantes.VEHICULO_ACTIVO);
		
		if (vehiculo.getTipoVehiculo().equals(Constantes.TIPO_CARRO) && cantidadVehiculos >= Constantes.CAPACIDAD_CARROS) {
			throw new IngresoExcepcion(Constantes.MENSAJE_PARQUEADERO_LLENO_CARRO);
		}
		
		if (vehiculo.getTipoVehiculo().equals(Constantes.TIPO_MOTO) && cantidadVehiculos >= Constantes.CAPACIDAD_MOTOS) {
			throw new IngresoExcepcion(Constantes.MENSAJE_PARQUEADERO_LLENO_MOTO);
		}
	}

}
