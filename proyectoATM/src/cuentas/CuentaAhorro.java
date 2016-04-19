package cuentas;
public class CuentaAhorro extends Cuenta{
	private double tasaInteres;
	
	public CuentaAhorro(double saldoInicial,double tasaInteres){
		super(saldoInicial);
		this.tasaInteres = tasaInteres;
	}
	
	public void setTasaInteres(double tasaInteres){
		this.tasaInteres = tasaInteres;
	}
	
	public double getTasaDeInteres(){
		return this.tasaInteres;
	}
}