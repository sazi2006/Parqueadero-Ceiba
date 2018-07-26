package co.com.ceiba.parqueadero.dominio;

import java.time.LocalDateTime;

public class Carro extends Vehiculo{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Carro(String placa, String tipoVehiculo, LocalDateTime fechaIngreso, LocalDateTime fechaSalida, double valorCobrar) {
		super(placa, tipoVehiculo, fechaIngreso, fechaSalida, valorCobrar);
	}	

}
