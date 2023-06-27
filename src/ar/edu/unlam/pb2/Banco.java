package ar.edu.unlam.pb2;

import java.util.TreeSet;

public class Banco {

	private TreeSet<Cuenta> cuentas;

	public Banco() {
		this.cuentas = new TreeSet<Cuenta>();
	}
	// orden natural por nombre

	public Boolean agregarCuenta(Cuenta cuenta) {
		return this.cuentas.add(cuenta);
	}

	public Integer cuentasEnElBanco() {
		return this.cuentas.size();

	}
	
	public Cuenta buscarCuenta(Integer CBU) throws CuentaNoEncontradaException {
		for (Cuenta cuenta : cuentas) {
			if (cuenta.getCBU().equals(CBU)) {
				return cuenta;
			}
		}
		throw new CuentaNoEncontradaException("No se pudo encontrar la cuenta");
	}

	public TreeSet<Cuenta> presetarCuentasOrdenadasPorCBU() {
		TreeSet<Cuenta> cuentaPorCbu = new TreeSet<Cuenta>(new PorCbuComparator());
		// creo una nueva coleccion con un nuevo criterio de ordenamiento
		cuentaPorCbu.addAll(cuentas);
		// le agrego todos los clientes que tengo
		return cuentaPorCbu;
		// retorno los clientes que tengo
	}
	
	public Boolean transferir(Double monto, Cuenta origen, Cuenta destino) throws MontoInsuficienteException {
		if(origen.extraer(monto)) {
			destino.depositar(monto);
			return true;
		}
		return false;
	}

	public Boolean transferir(Integer CBUorigen, Integer CBUdestino, Double monto) throws CuentaNoEncontradaException, MontoInsuficienteException {
		Cuenta origen = buscarCuenta(CBUorigen);
		Cuenta destino = buscarCuenta(CBUdestino);
		return transferir(monto, origen, destino);
	}
}
