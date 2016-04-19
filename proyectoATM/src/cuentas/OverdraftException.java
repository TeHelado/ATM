package cuentas;
public class OverdraftException extends Exception{
	private double deficit;
	
	public OverdraftException(String msj, double deficit){
		super(msj);
		this.deficit = deficit;
	}
	
	public double getDeficit(){
		return this.deficit;
	}		
	
}

