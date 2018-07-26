package co.com.ceiba.parqueadero.validaciones;

import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.MONDAY;

import co.com.ceiba.parqueadero.constantes.Constantes;
import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.excepcion.IngresoExcepcion;

public class ValidarPlaca implements Validacion{

	@Override
	public void realizarValidacion(Vehiculo vehiculo) {
		if (vehiculo.getPlaca().toUpperCase().startsWith("A") && vehiculo.getFechaIngreso().getDayOfWeek() != SUNDAY 
				&& vehiculo.getFechaIngreso().getDayOfWeek() != MONDAY) {
			throw new IngresoExcepcion(Constantes.MENSAJE_DIA_INVALIDO);
		}
		
	}

}
