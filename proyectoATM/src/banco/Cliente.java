package banco;
import java.time.*;
import personales.*;
import cuentas.*;
import java.util.ArrayList;
public class Cliente extends Persona{
	private String claveCliente;
	private String RFC;
	private Cuenta cuenta[];
	private int numCuentas;
	private String NIP;
	private ArrayList<String> movimientos;
	
	public Cliente(){
		
	}
	
	public Cliente(String nombre,String apellidoPaterno,String apellidoMaterno, Fecha fecha){
		super.setNombre(nombre);
		super.setApellidoPaterno(apellidoPaterno);
		super.setApellidoMaterno(apellidoMaterno);
		super.setFechaDeNacimiento(fecha);
		this.cuenta = new Cuenta[2];
		setRFC();
		setClaveCliente();
		movimientos = new ArrayList<String>();
	}
	
	public void setMovimiento(String movimiento){
		this.movimientos.add(movimiento);
	}
	
	public ArrayList<String> getListaMovimientos(){
		return movimientos;
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
		this.claveCliente = String.valueOf(Math.abs(thisSec.hashCode()));
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
	
	public String getRFC(){
		return this.RFC;
	}
}