package dominio.unitaria;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import co.com.ceiba.parqueadero.constantes.Constantes;
import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.excepcion.IngresoExcepcion;
import co.com.ceiba.parqueadero.persistencia.RepositorioParqueaderoImpl;
import co.com.ceiba.parqueadero.validaciones.CalcularCobro;
import co.com.ceiba.parqueadero.validaciones.ValidarDisponibilidad;
import co.com.ceiba.parqueadero.validaciones.ValidarPlaca;
import co.com.ceiba.parqueadero.validaciones.ValidarVehiculoExiste;
import testdatabuilder.ParqueaderoTestDataBuilder;



public class VigilanteTest {
	
	@Test
	public void validarCarroParqueaderoDisponibleTest() {
		Vehiculo vehiculo =  new ParqueaderoTestDataBuilder("Carro").build();
		
		RepositorioParqueaderoImpl repositorioParqueaderoImpl = mock (RepositorioParqueaderoImpl.class);
		
		when(repositorioParqueaderoImpl.contarVehiculos(vehiculo.getTipoVehiculo(), Constantes.VEHICULO_ACTIVO)).thenReturn((long) 15);
		
		ValidarDisponibilidad validarDisponibilidad = new ValidarDisponibilidad(repositorioParqueaderoImpl);
		
		try {
			validarDisponibilidad.realizarValidacion(vehiculo);
			fail();
		}catch(Throwable e){
			Assert.assertNotEquals(Constantes.MENSAJE_PARQUEADERO_LLENO_CARRO, e.getMessage());
		}
	}
	
	@Test
	public void validarMotoParqueaderoDisponibleTest() {
		Vehiculo vehiculo = new ParqueaderoTestDataBuilder("Moto").build();
		
		RepositorioParqueaderoImpl repositorioParqueaderoImpl = mock (RepositorioParqueaderoImpl.class);
		
		when(repositorioParqueaderoImpl.contarVehiculos(vehiculo.getTipoVehiculo(), Constantes.VEHICULO_ACTIVO)).thenReturn((long) 0);
		
		ValidarDisponibilidad validarDisponibilidad = new ValidarDisponibilidad(repositorioParqueaderoImpl);
		
		try {
			validarDisponibilidad.realizarValidacion(vehiculo);
			fail();
		}catch(Throwable e){
			Assert.assertNotEquals(Constantes.MENSAJE_PARQUEADERO_LLENO_MOTO, e.getMessage());
		}
	}
	
	@Test
	public void validarCarroParqueaderoNoDisponibleTest() {
		Vehiculo vehiculo = new ParqueaderoTestDataBuilder("Carro").build();
		
		RepositorioParqueaderoImpl repositorioParqueaderoImpl = mock (RepositorioParqueaderoImpl.class);
		
		when(repositorioParqueaderoImpl.contarVehiculos(vehiculo.getTipoVehiculo(), Constantes.VEHICULO_ACTIVO)).thenReturn((long) 20);
		
		ValidarDisponibilidad validarDisponibilidad = new ValidarDisponibilidad(repositorioParqueaderoImpl);
		
		try {
			validarDisponibilidad.realizarValidacion(vehiculo);
		}catch(IngresoExcepcion e){
			Assert.assertEquals(Constantes.MENSAJE_PARQUEADERO_LLENO_CARRO, e.getMessage());
		}
	}
	
	@Test
	public void validarMotoParqueaderoNoDisponibleTest() {
		Vehiculo vehiculo = new ParqueaderoTestDataBuilder("Moto").build();
		
		RepositorioParqueaderoImpl repositorioParqueaderoImpl = mock (RepositorioParqueaderoImpl.class);
		
		when(repositorioParqueaderoImpl.contarVehiculos(vehiculo.getTipoVehiculo(), Constantes.VEHICULO_ACTIVO)).thenReturn((long) 20);
		
		ValidarDisponibilidad validarDisponibilidad = new ValidarDisponibilidad(repositorioParqueaderoImpl);
		
		try {
			validarDisponibilidad.realizarValidacion(vehiculo);
		}catch(IngresoExcepcion e){
			Assert.assertEquals(Constantes.MENSAJE_PARQUEADERO_LLENO_MOTO, e.getMessage());
		}
	}
	
	@Test
	public void ingresarPlacaValidaTest() {
		ParqueaderoTestDataBuilder parqueaderoTestDataBuilder = new ParqueaderoTestDataBuilder("Carro");
		Vehiculo vehiculo = parqueaderoTestDataBuilder.setPlaca("ABC123").setFechaIngreso(LocalDateTime.parse("2018-07-23T07:00:00")).build();
		
		ValidarPlaca validarPlaca = new ValidarPlaca();
		
		try {
			validarPlaca.realizarValidacion(vehiculo);
			fail();
		}catch(Throwable e){
			Assert.assertNotEquals(Constantes.MENSAJE_DIA_INVALIDO, e.getMessage());
		}
	}
	
	@Test
	public void ingresarPlacaNoValidaTest() {
		ParqueaderoTestDataBuilder parqueaderoTestDataBuilder = new ParqueaderoTestDataBuilder("Carro");
		Vehiculo vehiculo = parqueaderoTestDataBuilder.setPlaca("ABC123").setFechaIngreso(LocalDateTime.parse("2018-07-25T07:00:00")).build();
		
		ValidarPlaca validarPlaca = new ValidarPlaca();
		
		try {
			validarPlaca.realizarValidacion(vehiculo);
		}catch(IngresoExcepcion e){
			Assert.assertEquals(Constantes.MENSAJE_DIA_INVALIDO, e.getMessage());
		}
	}
	
	@Test
	public void calcularCobroTest() {
		ParqueaderoTestDataBuilder parqueaderoTestDataBuilder = new ParqueaderoTestDataBuilder("Carro");
		Vehiculo vehiculo = parqueaderoTestDataBuilder.setFechaIngreso(LocalDateTime.parse("2018-07-26T07:00:00")).setFechaSalida(LocalDateTime.parse("2018-07-26T07:02:00")).build();
		
		CalcularCobro calcularCobro = new CalcularCobro();
		
		calcularCobro.calcularCobro(vehiculo, Constantes.VALOR_DIA_CARRO, Constantes.VALOR_HORA_CARRO, Constantes.COBRO_ADICIONAL_CARRO);
		Assert.assertEquals(1000, (long)vehiculo.getValorCobrar());
	}
	
	@Test
	public void calcularCobroHorasCarroMenor9Test() {
		ParqueaderoTestDataBuilder parqueaderoTestDataBuilder = new ParqueaderoTestDataBuilder("Carro");
		Vehiculo vehiculo = parqueaderoTestDataBuilder.setFechaIngreso(LocalDateTime.parse("2018-07-26T07:00:00")).setFechaSalida(LocalDateTime.parse("2018-07-26T10:00:00")).build();
		
		CalcularCobro calcularCobro = new CalcularCobro();
		
		calcularCobro.realizarValidacion(vehiculo);
		Assert.assertEquals(3000, (long)vehiculo.getValorCobrar());
	}
	
	@Test
	public void calcularCobroHorasCarroMayor9Test() {
		ParqueaderoTestDataBuilder parqueaderoTestDataBuilder = new ParqueaderoTestDataBuilder("Carro");
		Vehiculo vehiculo = parqueaderoTestDataBuilder.setFechaIngreso(LocalDateTime.parse("2018-07-26T07:00:00")).setFechaSalida(LocalDateTime.parse("2018-07-26T17:00:00")).build();
		
		CalcularCobro calcularCobro = new CalcularCobro();
		
		calcularCobro.realizarValidacion(vehiculo);
		Assert.assertEquals(8000, (long)vehiculo.getValorCobrar());
	}
	
	@Test
	public void calcularCobroHorasMotoMenor9Test() {
		ParqueaderoTestDataBuilder parqueaderoTestDataBuilder = new ParqueaderoTestDataBuilder("Moto");
		Vehiculo vehiculo = parqueaderoTestDataBuilder.setFechaIngreso(LocalDateTime.parse("2018-07-25T09:00:00")).setFechaSalida(LocalDateTime.parse("2018-07-25T12:00:00")).build();
		
		CalcularCobro calcularCobro = new CalcularCobro();
		
		calcularCobro.realizarValidacion(vehiculo);
		Assert.assertEquals(1500, (long)vehiculo.getValorCobrar());
	}
	
	@Test
	public void calcularCobroHorasMotoMayor9Test() {
		ParqueaderoTestDataBuilder parqueaderoTestDataBuilder = new ParqueaderoTestDataBuilder("Moto");
		Vehiculo vehiculo = parqueaderoTestDataBuilder.setFechaIngreso(LocalDateTime.parse("2018-06-11T07:00:00")).setFechaSalida(LocalDateTime.parse("2018-06-12T07:00:00")).build();
		
		CalcularCobro calcularCobro = new CalcularCobro();
		
		calcularCobro.realizarValidacion(vehiculo);
		Assert.assertEquals(4000, (long)vehiculo.getValorCobrar());
	}
	
	@Test
	public void calcularCobroCilindrajeMenor500ccTest() {
		ParqueaderoTestDataBuilder parqueaderoTestDataBuilder = new ParqueaderoTestDataBuilder("Moto");
		Vehiculo vehiculo = parqueaderoTestDataBuilder.setCilindraje(500).setFechaIngreso(LocalDateTime.parse("2018-06-11T07:00:00")).setFechaSalida(LocalDateTime.parse("2018-06-11T10:00:00")).build();
		
		CalcularCobro calcularCobro = new CalcularCobro();
		
		calcularCobro.realizarValidacion(vehiculo);
		Assert.assertEquals(1500, (long)vehiculo.getValorCobrar());
	}
	
	@Test
	public void calcularCobroCilindrajeMayor500ccTest() {
		ParqueaderoTestDataBuilder parqueaderoTestDataBuilder = new ParqueaderoTestDataBuilder("Moto");
		Vehiculo vehiculo = parqueaderoTestDataBuilder.setCilindraje(600).setFechaIngreso(LocalDateTime.parse("2018-06-11T07:00:00")).setFechaSalida(LocalDateTime.parse("2018-06-11T10:00:00")).build();
		
		CalcularCobro calcularCobro = new CalcularCobro();
		
		calcularCobro.realizarValidacion(vehiculo);
		Assert.assertEquals(3500, (long)vehiculo.getValorCobrar());
	}
	
	@Test
	public void validarVehiculoYaRegistradoTest() {
		Vehiculo vehiculo = new ParqueaderoTestDataBuilder("Moto").build();
		
		RepositorioParqueaderoImpl repositorioParqueaderoImpl = mock (RepositorioParqueaderoImpl.class);
		
		when(repositorioParqueaderoImpl.buscarVehiculo(vehiculo.getPlaca(), vehiculo.getTipoVehiculo(),Constantes.VEHICULO_ACTIVO)).thenReturn(vehiculo);
		
		ValidarVehiculoExiste validarVehiculoExiste = new ValidarVehiculoExiste(repositorioParqueaderoImpl);
		try {
			validarVehiculoExiste.realizarValidacion(vehiculo);
		}catch (IngresoExcepcion e) {
			Assert.assertEquals(Constantes.MENSAJE_VEHICULO_YA_REGISTRADO, e.getMessage());
		}
		
	}

}
