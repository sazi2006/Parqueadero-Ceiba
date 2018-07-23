package co.com.ceiba.parqueadero.persistencia;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="parqueadero")
public class ParqueaderoEntidad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="placa",nullable=false)
	private String placa;
	
	@Column(name="tipoVehiculo",nullable=false)
	private String tipoVehiculo;
	
	@Column(name="cilindraje")
	private double cilindraje;
	
	@Column(name="fechaIngreso")
	private LocalDateTime fechaIngreso;
	
	@Column(name="fechaSalida")
	private LocalDateTime fechaSalida;
	
	@Column(name="valorCobrar")
	private double valorCobrar;
	
	@Column(name="estado",nullable=false)
	private String estado;

	public ParqueaderoEntidad() {
		// TODO Auto-generated constructor stub
	}

	public ParqueaderoEntidad(String placa, String tipoVehiculo, double cilindraje, LocalDateTime fechaIngreso,
			LocalDateTime fechaSalida, double valorCobrar, String estado) {
		
		this.placa = placa;
		this.tipoVehiculo = tipoVehiculo;
		this.cilindraje = cilindraje;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.valorCobrar = valorCobrar;
		this.estado = estado;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public double getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(double cilindraje) {
		this.cilindraje = cilindraje;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	

}
