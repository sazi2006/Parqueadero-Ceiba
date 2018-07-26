package co.com.ceiba.parqueadero.validaciones;

import java.time.Duration;

import co.com.ceiba.parqueadero.constantes.Constantes;
import co.com.ceiba.parqueadero.dominio.Moto;
import co.com.ceiba.parqueadero.dominio.Vehiculo;

public class CalcularCobro implements Validacion{

	@Override
	public void realizarValidacion(Vehiculo vehiculo) {
		if (vehiculo.getTipoVehiculo().equals(Constantes.TIPO_CARRO)) {
			calcularCobro(vehiculo, Constantes.VALOR_DIA_CARRO, Constantes.VALOR_HORA_CARRO, Constantes.COBRO_ADICIONAL_CARRO);
		}
		
		if (vehiculo.getTipoVehiculo().equals(Constantes.TIPO_MOTO)) {
			Moto moto = (Moto) vehiculo;
			if (moto.getCilindraje() > Constantes.CILINDRAJE_LIMITE_MOTO) {
				calcularCobro(vehiculo, Constantes.VALOR_DIA_MOTO, Constantes.VALOR_HORA_MOTO, Constantes.COBRO_ADICIONAL_CILINDRAJE_MOTO);
			}else {
				calcularCobro(vehiculo, Constantes.VALOR_DIA_MOTO, Constantes.VALOR_HORA_MOTO, Constantes.COBRO_ADICIONAL_NULO);
			}
		}
	}
	
	public void calcularCobro(Vehiculo vehiculo, double valorDia, double valorHora, double valorAdicional) {
		double valorCobrar = valorAdicional;
		double horas = Duration.between(vehiculo.getFechaIngreso(), vehiculo.getFechaSalida()).toHours();
		
		if (horas == 0) {
			horas++;			
		}
		double dias = Math.round(horas/Constantes.HORAS_DIA);
		int diasCompletos = (int)dias;
		int horasRestantes = (int)(horas - (diasCompletos*Constantes.HORAS_DIA));		
		
		if (horasRestantes >= Constantes.LIMITE_HORAS_DIA) {
			diasCompletos++;
			horasRestantes = 0;
		}
		valorCobrar += diasCompletos * valorDia;
		valorCobrar += horasRestantes * valorHora;

		vehiculo.setValorCobrar(valorCobrar);
	}
}
