package co.com.ceiba.parqueadero.dominio;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "vehiculo")
public class Vehiculo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty
	String placa;
	
	@JsonProperty
	String tipoVehiculo;
	
	@JsonProperty
	LocalDateTime fechaIngreso;
	
	@JsonProperty
	LocalDateTime fechaSalida;
	
	@JsonProperty
	double valorCobrar;
	
	public Vehiculo() {
		
	}
	

	public Vehiculo(String placa, String tipoVehiculo, LocalDateTime fechaIngreso, LocalDateTime fechaSalida, double valorCobrar) {
		
		this.placa = placa;
		this.tipoVehiculo = tipoVehiculo;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.valorCobrar = valorCobrar;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public LocalDateTime getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDateTime fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public LocalDateTime getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(LocalDateTime fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public double getValorCobrar() {
		return valorCobrar;
	}

	public void setValorCobrar(double valorCobrar) {
		this.valorCobrar = valorCobrar;
	}
	
	
}
