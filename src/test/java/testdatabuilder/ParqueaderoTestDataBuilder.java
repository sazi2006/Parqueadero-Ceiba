package testdatabuilder;

import java.time.LocalDateTime;

import co.com.ceiba.parqueadero.dominio.Carro;
import co.com.ceiba.parqueadero.dominio.Moto;
import co.com.ceiba.parqueadero.dominio.Vehiculo;

public class ParqueaderoTestDataBuilder {
	
	private static final String PLACA = "SMS020";
	private static final LocalDateTime FECHA_INGRESO = LocalDateTime.now();
	
	String placa;
	LocalDateTime fechaIngreso;
	LocalDateTime fechaSalida;
	double valorCobrar;
	String tipoVehiculo;
	double cilindraje;
	
	public ParqueaderoTestDataBuilder() {
		this.placa = PLACA;
		this.fechaIngreso = FECHA_INGRESO;
	}
	
	public ParqueaderoTestDataBuilder(String tipoVehiculo) {
		this.placa = PLACA;
		this.fechaIngreso = FECHA_INGRESO;
		this.tipoVehiculo = tipoVehiculo;
	}
	
	public String getPlaca() {
		return placa;
	}

	public ParqueaderoTestDataBuilder setPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public LocalDateTime getFechaIngreso() {
		return fechaIngreso;
	}

	public ParqueaderoTestDataBuilder setFechaIngreso(LocalDateTime fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
		return this;
	}

	public LocalDateTime getFechaSalida() {
		return fechaSalida;
	}

	public ParqueaderoTestDataBuilder setFechaSalida(LocalDateTime fechaSalida) {
		this.fechaSalida = fechaSalida;
		return this;
	}

	public double getValorCobrar() {
		return valorCobrar;
	}

	public ParqueaderoTestDataBuilder setValorCobrar(double valorCobrar) {
		this.valorCobrar = valorCobrar;
		return this;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public ParqueaderoTestDataBuilder setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
		return this;
	}

	public double getCilindraje() {
		return cilindraje;
	}

	public ParqueaderoTestDataBuilder setCilindraje(double cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}

	public Vehiculo build() {
		if (this.tipoVehiculo.equals("Carro")) {
			return new Carro(this.placa, this.tipoVehiculo, this.fechaIngreso, this.fechaSalida, this.valorCobrar);
		}
		
		if (this.tipoVehiculo.equals("Moto")) {
			return new Moto(this.placa, this.tipoVehiculo, this.cilindraje, this.fechaIngreso, this.fechaSalida, this.valorCobrar);
		}
		
		return new Vehiculo(this.placa, this.tipoVehiculo, this.fechaIngreso, this.fechaSalida, this.valorCobrar);
	}
}
