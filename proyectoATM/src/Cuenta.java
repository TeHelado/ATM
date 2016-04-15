public class Cuenta{
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
	
	public boolean retirarCuenta(double cantidad){
		if(cantidad <= this.saldo){
			this.saldo -= cantidad;
			return true;
		}
		else
			return false;
	}
}