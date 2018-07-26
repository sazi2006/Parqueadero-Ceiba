package co.com.ceiba.parqueadero.excepcion;

public class IngresoExcepcion extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IngresoExcepcion(String message) {
		super(message);
	}
}
