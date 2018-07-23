package co.com.ceiba.parqueadero.persistencia;

import co.com.ceiba.parqueadero.constantes.Constantes;
import co.com.ceiba.parqueadero.dominio.Carro;
import co.com.ceiba.parqueadero.dominio.Moto;
import co.com.ceiba.parqueadero.dominio.Vehiculo;

public class ParqueaderoBuilder {

	private ParqueaderoBuilder() {		
		// TODO Auto-generated constructor stub
	}
	
	public static Vehiculo convertirADominio(ParqueaderoEntidad parqueaderoEntidad) {
		Vehiculo vehiculo = null;
		if (parqueaderoEntidad != null) {
			if (parqueaderoEntidad.getTipoVehiculo().equals(Constantes.TIPO_CARRO)) {
				vehiculo = new Carro(parqueaderoEntidad.getPlaca(), parqueaderoEntidad.getTipoVehiculo(), parqueaderoEntidad.getFechaIngreso(), 
						parqueaderoEntidad.getFechaSalida(), parqueaderoEntidad.getValorCobrar());
			}
			if (parqueaderoEntidad.getTipoVehiculo().equals(Constantes.TIPO_MOTO)) {
				vehiculo = new Moto(parqueaderoEntidad.getPlaca(), parqueaderoEntidad.getTipoVehiculo(), parqueaderoEntidad.getCilindraje(), 
						parqueaderoEntidad.getFechaIngreso(), parqueaderoEntidad.getFechaSalida(), parqueaderoEntidad.getValorCobrar());
			}
		}
		return vehiculo;
	}
	
	public static ParqueaderoEntidad convertirAEntidad(Vehiculo vehiculo) {
		ParqueaderoEntidad parqueaderoEntidad = new ParqueaderoEntidad();
		parqueaderoEntidad.setPlaca(vehiculo.getPlaca());
		parqueaderoEntidad.setTipoVehiculo(vehiculo.getTipoVehiculo());
		parqueaderoEntidad.setFechaIngreso(vehiculo.getFechaIngreso());
		parqueaderoEntidad.setFechaSalida(vehiculo.getFechaSalida());
		parqueaderoEntidad.setValorCobrar(vehiculo.getValorCobrar());
		
		if (vehiculo.getTipoVehiculo().equals(Constantes.TIPO_MOTO)) {
			parqueaderoEntidad.setCilindraje(((Moto)vehiculo).getCilindraje());
		}
		
		return parqueaderoEntidad;
	}
	

}
