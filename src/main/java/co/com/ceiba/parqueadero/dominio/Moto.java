package co.com.ceiba.parqueadero.dominio;

import java.time.LocalDateTime;

public class Moto extends Vehiculo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	double cilindraje;

	public Moto(String placa, String tipoVehiculo, double cilindraje, LocalDateTime fechaIngreso, LocalDateTime fechaSalida,
			double valorCobrar) {
		super(placa, tipoVehiculo, fechaIngreso, fechaSalida, valorCobrar);
		this.cilindraje = cilindraje;
	}

	public double getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(double cilindraje) {
		this.cilindraje = cilindraje;
	}
		
}
