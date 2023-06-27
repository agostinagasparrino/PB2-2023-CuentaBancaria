package ar.edu.unlam.pb2;

public class CajaDeAhorros extends Cuenta {

	private Integer contadorExtracciones = 0;

	public CajaDeAhorros(Integer CBU) {
		super(CBU);
		// TODO Auto-generated constructor stub
	}

	public void depositar(Double monto) {
		this.saldo += monto;
	}

	public Boolean extraer(Double monto) throws MontoInsuficienteException {
		if (this.saldo >= monto) {
			Double saldoFinal = getSaldo() - monto;
			setSaldo(saldoFinal);

			contadorExtracciones++;
			if (contadorExtracciones > 5) {
				Double comision = 6.0 + (contadorExtracciones - 5);
				Double saldoDescontado = saldoFinal - comision;
				setSaldo(saldoDescontado);
			}
		}
		throw new MontoInsuficienteException("Monto Insuficiente");
	}
	
	public String getTipo() {
		return "CajaDeAhorros";
	}
	
//	public Boolean transferirDesdeCaja(Cuenta cuentaDestino, double monto) {
//        if (saldo >= monto) {
//            saldo -= monto;
//            cuentaDestino.depositar(monto);
//            return true;
//        } else {
//            return false;
//        }
//    }
}
