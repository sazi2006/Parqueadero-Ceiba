package dominio.integracion;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.parqueadero.ParqueaderoApplication;
import co.com.ceiba.parqueadero.constantes.Constantes;
import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.dominio.Vigilante;
import co.com.ceiba.parqueadero.excepcion.IngresoExcepcion;
import co.com.ceiba.parqueadero.persistencia.RepositorioParqueadero;
import co.com.ceiba.parqueadero.persistencia.RepositorioParqueaderoImpl;
import testdatabuilder.ParqueaderoTestDataBuilder;


@RunWith(SpringRunner.class)
@SpringBootTest(classes= {Vigilante.class, ParqueaderoApplication.class})
public class VigilanteTest {
	
	@Autowired
	RepositorioParqueaderoImpl repositorioParqueaderoImpl;
	
	@Autowired
	RepositorioParqueadero repositorioParqueadero;
	
	@Before
	public void limpiar() {
		repositorioParqueadero.deleteAll();
	}
			
	@Test
	public void ingresarNuevoCarroTest() {
		// arrange
		Vehiculo vehiculo = new ParqueaderoTestDataBuilder("Carro").setPlaca("C1").build();
		
		Vigilante vigilante = new Vigilante(repositorioParqueaderoImpl);
				
		// act
		vigilante.ingresarVehiculo(vehiculo);
		
		// assert
		Assert.assertNotNull(repositorioParqueaderoImpl.buscarVehiculo(vehiculo.getPlaca(), vehiculo.getTipoVehiculo(), Constantes.VEHICULO_ACTIVO));
	}
	
	@Test
	public void ingresarNuevaMotoTest() {
		// arrange
		Vehiculo vehiculo = new ParqueaderoTestDataBuilder("Moto").setPlaca("M1").build();
		
		Vigilante vigilante = new Vigilante(repositorioParqueaderoImpl);
				
		// act
		vigilante.ingresarVehiculo(vehiculo);

		// assert
		Assert.assertNotNull(repositorioParqueaderoImpl.buscarVehiculo(vehiculo.getPlaca(), vehiculo.getTipoVehiculo(),Constantes.VEHICULO_ACTIVO));
	}
	
	@Test
	public void ingresarCarroConMismaPlacaTest() {
		// arrange
		Vehiculo vehiculo = new ParqueaderoTestDataBuilder("Carro").setPlaca("C2").build();
		
		Vigilante vigilante = new Vigilante(repositorioParqueaderoImpl);
				
		// act
		vigilante.ingresarVehiculo(vehiculo);
		
		try {
			vigilante.ingresarVehiculo(vehiculo);
		}catch(IngresoExcepcion e){
			// assert
			Assert.assertEquals(Constantes.MENSAJE_VEHICULO_YA_REGISTRADO, e.getMessage());
		}
		
	}
	
	@Test
	public void ingresarMotocConMismaPlacaTest() {
		// arrange
		Vehiculo vehiculo = new ParqueaderoTestDataBuilder("Moto").setPlaca("M2").build();
		
		Vigilante vigilante = new Vigilante(repositorioParqueaderoImpl);
				
		// act
		vigilante.ingresarVehiculo(vehiculo);
		
		try {
			vigilante.ingresarVehiculo(vehiculo);
		}catch(IngresoExcepcion e){
			// assert
			Assert.assertEquals(Constantes.MENSAJE_VEHICULO_YA_REGISTRADO, e.getMessage());
		}
	}
	
	@Test
	public void retirarCarroTest() {
		// arrange
		Vehiculo vehiculo = new ParqueaderoTestDataBuilder("Carro").setPlaca("C3").setFechaIngreso(LocalDateTime.parse("2018-06-11T07:00:00")).build();
		
		Vigilante vigilante = new Vigilante(repositorioParqueaderoImpl);
		
		vigilante.ingresarVehiculo(vehiculo);
				
		// act
		vigilante.retirarVehiculo(vehiculo.getPlaca(), vehiculo.getTipoVehiculo());

		Assert.assertNotNull(repositorioParqueaderoImpl.buscarVehiculo(vehiculo.getPlaca(), vehiculo.getTipoVehiculo(),Constantes.VEHICULO_INACTIVO));
	}
	
	@Test
	public void retirarMotoTest() {
		// arrange
		Vehiculo vehiculo = new ParqueaderoTestDataBuilder("Moto").setPlaca("M3").setFechaIngreso(LocalDateTime.parse("2018-06-11T07:00:00")).build();
		
		Vigilante vigilante = new Vigilante(repositorioParqueaderoImpl);
		
		vigilante.ingresarVehiculo(vehiculo);
				
		// act
		vigilante.retirarVehiculo(vehiculo.getPlaca(), vehiculo.getTipoVehiculo());

		Assert.assertNotNull(repositorioParqueaderoImpl.buscarVehiculo(vehiculo.getPlaca(), vehiculo.getTipoVehiculo(),Constantes.VEHICULO_INACTIVO));
	}
	
	@Test
	public void consultarParqueaderoTest() {
		//arrange
		List<Vehiculo> parqueadero = new ArrayList<>();
		Vehiculo vehiculo = new ParqueaderoTestDataBuilder("Carro").setPlaca("C4").build();
		
		parqueadero.add(vehiculo);
				
		Vigilante vigilante = new Vigilante(repositorioParqueaderoImpl);
		
		//act
		List<Vehiculo> resultadoConsulta = vigilante.consultarVehiculos();
		
		//assert
		Assert.assertNotNull(resultadoConsulta);
	}

}
