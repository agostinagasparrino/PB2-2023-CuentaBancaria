package ar.edu.unlam.pb2;

public class CuentaCorriente extends Cuenta {

	private Double limiteDescubierto = 200.0;

	public CuentaCorriente(Integer CBU) {
		super(CBU);
		this.limiteDescubierto = limiteDescubierto;
	}

	public Double getLimiteDescubierto() {
		return limiteDescubierto;
	}

	public void setLimiteDescubierto(Double limiteDescubierto) {
		this.limiteDescubierto = limiteDescubierto;
	}

	public void depositar(Double monto) {
		this.saldo += monto;
	}

	public Boolean extraer(Double monto) throws MontoInsuficienteException {
		if (this.saldo >= (monto + limiteDescubierto)) {
			if (monto > saldo) {
				Double montoDescubiertoUtilizado = monto - saldo;
				Double cargoDescubierto = montoDescubiertoUtilizado * 0.05;

				Double saldoFinal = 0.0;
				Double montoDescubiertoFinal = limiteDescubierto - (montoDescubiertoUtilizado + cargoDescubierto);

				setSaldo(saldoFinal);
				setLimiteDescubierto(montoDescubiertoFinal);
			} else {
				double saldoFinal = getSaldo() - monto;
				setSaldo(saldoFinal);
			}
		}
		throw new MontoInsuficienteException("Monto Insuficiente");
	}

//	public Boolean transferirDesdeCorriente(Cuenta cuentaDestino, double monto) {
//		if (saldo >= monto) {
//			saldo -= monto;
//			cuentaDestino.depositar(monto);
//			return true;
//		} else {
//			return false;
//		}
//	}

	public String getTipo() {
		return "CuentaCorriente";
	}

}
