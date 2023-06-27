package ar.edu.unlam.pb2;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCuentaBancaria {

	@Test
	public void queSeCreeUnBanco() {
		Banco b = new Banco();
		assertNotNull(b);
	}

	@Test
	public void queSeAgregueUnaCuentaAlBanco() {
		Banco banco = new Banco();
		Cuenta cuenta = new Cuenta(1);
		Boolean agrego = banco.agregarCuenta(cuenta);
		assertTrue(agrego);
	}

	@Test
	public void queSePuedaDepositarEnLaCuenta() {
		Banco banco = new Banco();
		Cuenta cuenta = new Cuenta(1);
		banco.agregarCuenta(cuenta);
		cuenta.depositar(5.0);
		assertEquals(105.0, cuenta.getSaldo(), 0.1);

	}

	@Test(expected = MontoInsuficienteException.class)
	public void queSePuedaExtraerDeLaCuenta() throws MontoInsuficienteException {
		Banco banco = new Banco();
		Cuenta cuenta = new Cuenta(1);
		banco.agregarCuenta(cuenta);
		cuenta.extraer(5.0);
		assertEquals(95.0, cuenta.getSaldo(), 0.1);

	}

	@Test(expected = MontoInsuficienteException.class)
	public void queAlQuererExtraerYNoTenerSaldoLargueExcepcion() throws MontoInsuficienteException {
		Banco banco = new Banco();
		Cuenta cuenta = new Cuenta(1);
		banco.agregarCuenta(cuenta);
		cuenta.extraer(105.0);

	}

	@Test
	public void queSeMuestrenCuentasPorOrdenDeCBU() {
		Banco banco = new Banco();
		Cuenta cuenta = new Cuenta(1);
		Cuenta cuenta1 = new Cuenta(9);
		Cuenta cuenta2 = new Cuenta(5);
		banco.agregarCuenta(cuenta);
		banco.agregarCuenta(cuenta1);
		banco.agregarCuenta(cuenta2);
		assertEquals(banco.presetarCuentasOrdenadasPorCBU().first(), cuenta);
		assertEquals(banco.presetarCuentasOrdenadasPorCBU().last(), cuenta1);
	}

	@Test
	public void queSeAgregueUnaCajaDeAhorrosAlBanco() {
		Banco banco = new Banco();
		CajaDeAhorros caja = new CajaDeAhorros(1);
		Boolean agrego = banco.agregarCuenta(caja);
		assertTrue(agrego);
	}

	@Test(expected = MontoInsuficienteException.class)
	public void queLuegoDeLaQuintaExtraccionCobreComisionDe6$() throws MontoInsuficienteException {
		Banco banco = new Banco();
		CajaDeAhorros caja = new CajaDeAhorros(1);
		banco.agregarCuenta(caja);
		caja.extraer(10.0);
		caja.extraer(10.0);
		caja.extraer(10.0);
		caja.extraer(10.0);
		caja.extraer(10.0);
		caja.extraer(10.0);
		caja.extraer(10.0);
		caja.extraer(10.0);
		assertEquals(2.0, caja.getSaldo(), 0.1);

	}

	@Test(expected = MontoInsuficienteException.class)
	public void queAlQuererExtraerCajaYNoTenerSaldoLargueExcepcion() throws MontoInsuficienteException {
		Banco banco = new Banco();
		CajaDeAhorros caja = new CajaDeAhorros(1);
		banco.agregarCuenta(caja);
		caja.extraer(105.0);

	}

	@Test
	public void queSeAgregueUnaCuentaCorrienteAlBanco() {
		Banco banco = new Banco();
		CuentaCorriente corriente = new CuentaCorriente(1);
		Boolean agrego = banco.agregarCuenta(corriente);
		assertTrue(agrego);
	}

	@Test(expected = MontoInsuficienteException.class)
	public void transferenciaDesdeCuentaHaciaCajaDeAhorros() throws CuentaNoEncontradaException, MontoInsuficienteException {
		Banco banco = new Banco();
		Cuenta cuenta = new Cuenta(1);
		banco.agregarCuenta(cuenta);
		CajaDeAhorros caja = new CajaDeAhorros(2);
		banco.agregarCuenta(caja);

		// Realizar transferencia
		banco.transferir(1, 2, 5.0);

		// Verificar que la transferencia se haya realizado correctamente
		assertEquals(50.0, cuenta.getSaldo(), 0.1);
		assertEquals(150.0, caja.getSaldo(), 0.1);
	}

	@Test(expected = MontoInsuficienteException.class)
	public void transferenciaDesdeCajaDeAhorrosHaciaCuentaCorriente() throws CuentaNoEncontradaException, MontoInsuficienteException {
		Banco banco = new Banco();
		CajaDeAhorros caja = new CajaDeAhorros(2);
		banco.agregarCuenta(caja);
		CuentaCorriente corriente = new CuentaCorriente(1);
		banco.agregarCuenta(corriente);

		// Realizar transferencia
		banco.transferir(2, 1, 30.0);

		// Verificar que la transferencia se haya realizado correctamente

		assertEquals(130.0, corriente.getSaldo(), 0.1);
		assertEquals(70.0, caja.getSaldo(), 0.1);
	}

	@Test(expected = MontoInsuficienteException.class)
	public void transferenciaDesdeCuentaCorrienteHaciaCuenta() throws CuentaNoEncontradaException, MontoInsuficienteException {
		Banco banco = new Banco();
		CuentaCorriente corriente = new CuentaCorriente(1);
		banco.agregarCuenta(corriente);
		Cuenta cuenta = new Cuenta(2);
		banco.agregarCuenta(cuenta);

		// Realizar transferencia desde la cuenta de origen a la cuenta de destino
		banco.transferir(1, 2, 150.0);

		// Verificar que la transferencia se haya realizado correctamente
		assertEquals(250.0, cuenta.getSaldo(), 0.1);
		assertEquals(152.5, corriente.getSaldo(), 0.1);
		//transifere con limite descubierto y se cobra la comision del 5%

	}

	@Test(expected = MontoInsuficienteException.class)
	public void queSePuedaExtraerDeLaCuentaCorriente() throws MontoInsuficienteException {
		Banco banco = new Banco();
		CuentaCorriente corriente = new CuentaCorriente(1);
		banco.agregarCuenta(corriente);
		corriente.extraer(160.0);
		assertEquals(137.0, corriente.getSaldo(), 0.1);

	}
	
	@Test (expected = CuentaNoEncontradaException.class)
	public void queAlBuscarUnaCuentaQueNoExistaDuvuelvaUnaExcepcion() throws CuentaNoEncontradaException{
		Cuenta cuentaCorriente = new CuentaCorriente(1);
		Banco banco = new Banco();
		banco.agregarCuenta(cuentaCorriente);
		banco.buscarCuenta(5);
	}

}
