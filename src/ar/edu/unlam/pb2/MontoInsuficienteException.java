package ar.edu.unlam.pb2;

public class MontoInsuficienteException extends Exception {
	
	public MontoInsuficienteException(String string) {
		super("No se encontro el monto");
	}

}
