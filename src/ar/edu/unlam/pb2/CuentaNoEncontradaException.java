package ar.edu.unlam.pb2;

public class CuentaNoEncontradaException extends Exception {

	public CuentaNoEncontradaException(String string) {
		super("No se pudo encontrar la cuenta");
	}
}
