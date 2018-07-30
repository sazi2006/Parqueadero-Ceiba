package co.com.ceiba.parqueadero.dominio;

import java.io.Serializable;
import java.time.LocalDateTime;

import co.com.ceiba.parqueadero.constantes.Constantes;

public class Parqueadero implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String placa;
	String tipoVehiculo;
	LocalDateTime fechaIngreso;
	LocalDateTime fechaSalida;
	double valorCobrar;
	double cilindraje;
	
	public Parqueadero() {
	}

	public String getPlaca() {
		return placa;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public LocalDateTime getFechaIngreso() {
		return fechaIngreso;
	}

	public LocalDateTime getFechaSalida() {
		return fechaSalida;
	}

	public double getValorCobrar() {
		return valorCobrar;
	}

	public double getCilindraje() {
		return cilindraje;
	}
	
	public Vehiculo crearVehiculo() {
		if (getTipoVehiculo().equals(Constantes.TIPO_CARRO)) {
			return new Carro(getPlaca(), getTipoVehiculo(), LocalDateTime.now(), getFechaSalida(), getValorCobrar());
		}else {
			return new Moto(getPlaca(), getTipoVehiculo(), getCilindraje(), LocalDateTime.now(), getFechaSalida(), getValorCobrar());
		}
	}	

}
