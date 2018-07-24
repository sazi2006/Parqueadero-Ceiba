package co.com.ceiba.parqueadero.persistencia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioParqueadero extends JpaRepository<ParqueaderoEntidad, Long>{
	
	public ParqueaderoEntidad findByPlaca(String placa);
	
	public ParqueaderoEntidad findByPlacaAndTipoVehiculoAndEstado(String placa, String tipoVehiculo, String estado);
	
	public List<ParqueaderoEntidad> findByEstado(String estado);
	
	public long countByTipoVehiculoAndEstado(String tipoVehiculo, String estado);
}
