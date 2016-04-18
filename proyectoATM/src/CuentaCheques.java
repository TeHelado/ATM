public class CuentaCheques extends Cuenta{
	private CuentaAhorro proteccion;
	
	public CuentaCheques(double saldoInicial){
		super(saldoInicial);
	}
	
	public CuentaCheques(double saldoInicial,CuentaAhorro proteccion){
		super(saldoInicial);
		this.proteccion = proteccion;
	}
	
	public void setCuentaDeAhorro(CuentaAhorro proteccion){
		this.proteccion = proteccion;
	}
	
	public CuentaAhorro getCuentaDeAhorro(){
		return this.proteccion;
	}
	
	public boolean retirarACuenta(double cantidad){
		return super.retirarACuenta(cantidad);
	}
}