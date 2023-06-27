package ar.edu.unlam.pb2;

import java.util.Objects;

public class Cuenta implements Comparable<Cuenta> {

	protected Integer CBU;
	protected Double saldo = 100.0;

	public Cuenta(Integer CBU) {
		this.CBU = CBU;
		this.saldo = saldo;
	}

	public Integer getCBU() {
		return CBU;
	}

	public void setCBU(Integer cBU) {
		CBU = cBU;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(CBU);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cuenta other = (Cuenta) obj;
		return Objects.equals(CBU, other.CBU);
	}

	public void depositar(Double monto) {
		this.saldo += monto;
	}

	public Boolean extraer(Double monto) throws MontoInsuficienteException{
		if (this.saldo >= monto) {
			Double saldoFinal = getSaldo() - monto;
			setSaldo(saldoFinal);

		}throw new MontoInsuficienteException("Monto Insuficiente");
	}

	@Override
	public int compareTo(Cuenta otro) {
		return this.CBU.compareTo(otro.CBU);
	}
	
	public String getTipo() {
		return "CuentaSueldo";
	}
	
//	public Boolean transferirDesdeCuenta(Cuenta cuentaDestino, double monto) {
//        if (saldo >= monto) {
//            saldo -= monto;
//            cuentaDestino.depositar(monto);
//            return true;
//        } else {
//            return false;
//        }
//    }

}
