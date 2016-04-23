package personales;
public class Direccion implements java.io.Serializable{
	private String calle;
	private int numero;
	private String colonia;
	private String delegacion;
	private String ciudad;
	private String pais;
	
	//Constructores
	public Direccion(){
		
	}
	
	public Direccion(String calle,int numero,String colonia){
		this.calle = calle;
		this.numero = numero;
		this.colonia = colonia;
	}
	
	//Métodos
	public void setCalle(String c){
		this.calle = c;
	}

	public void setNumero(int n){
		this.numero = n;
	}

	public void setColonia(String c){
		this.colonia = c;
	}

	public void setDelegacion(String d){
		this.delegacion = d;
	}

	public void setCiudad(String c){
		this.ciudad = c;
	}

	public void setPais(String p){
		this.pais = p;
	}

	public String getCalle(){
		return this.calle;
	}

	public int getNumero(){
		return this.numero;
	}

	public String getColonia(){
		return this.colonia;
	}

	public String getDelegacion(){
		return this.delegacion;
	}

	public String getCiudad(){
		return this.ciudad;
	}

	public String getPais(){
		return this.pais;
	}

	public String toString(){
		return this.calle + " "+ this.numero + " " + this.colonia + " "+this.delegacion + " "+ this.ciudad + " " + this.pais;
	}
}