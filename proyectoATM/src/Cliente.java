import java.time.*;
public class Cliente extends Persona{
	private String claveCliente;
	private String RFC;
	private Cuenta cuenta[];
	private int numCuentas;
	private String NIP;
	
	public Cliente(){
		
	}
	
	public Cliente(String nombre,String apellidoPaterno,String apellidoMaterno){
		super.setNombre(nombre);
		super.setApellidoPaterno(apellidoPaterno);
		super.setApellidoMaterno(apellidoMaterno);
		this.cuenta = new Cuenta[2];
		setRFC();
	}
	
	public Cuenta getCuenta(int i){
		return this.cuenta[i];
	}
	
	public boolean agregarCuenta(Cuenta cuenta){
		if(getNumCuentas()<2){
			this.cuenta[getNumCuentas()] = cuenta;
			this.numCuentas++;
			return true;
		}
		else
			return false;
	}
	
	public int getNumCuentas(){
		return this.numCuentas;
	}
	
	private void setClaveCliente(){
		LocalDateTime thisSec;
		thisSec = LocalDateTime.now();
		this.claveCliente = Math.abs(thisSec.hashCode());
	}
	
	public String getClaveCliente(){
		return this.claveCliente;
	}
	
	public void setNIP(String NIP){
		this.NIP = NIP;
	}
	
	public String getNIP(){
		return this.NIP;
	}
	
	private void setRFC(){
		RFC rfcBanco = new RFC();
		rfcBanco.setInicialesRFC(super.getNombre(),super.getApellidoPaterno(),super.getApellidoMaterno());
		rfcBanco.setFechaRFC(super.getFechaDeNacimiento());
		this.RFC = rfcBanco.toString();
	}
}