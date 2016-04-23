package cuentas;
public class Cuenta implements java.io.Serializable{
	private double saldo;
	
	public Cuenta(double saldoInicial){
		this.saldo = saldoInicial;
	}
	
	public double getSaldo(){
		return this.saldo;
	}
	
	public boolean depositarCuenta(double cantidad){
		if(cantidad > 0){
			this.saldo += cantidad;
			return true;
		}
		else
			return false;
	}
	
	public void retirarACuenta(double cantidad) throws OverdraftException{
		if(cantidad <= this.saldo){
			this.saldo -= cantidad;
		}
		else
			throw new OverdraftException("Fondos Insuficientes Tu deficit es: ",(cantidad-saldo));
	}
}