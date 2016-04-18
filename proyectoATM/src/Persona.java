public class Persona{
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private Direccion direccion;
	private Fecha fechaDeNacimiento;
	private int edad;
	
	//Constructores
	public Persona(String nombre,String apellidoPaterno,String apellidoMaterno){
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		fechaDeNacimiento = new Fecha();
	}
	
	public Persona(){
		
	}
	
	//Métodos
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	
	public void setApellidoPaterno(String apellidoPaterno){
		this.apellidoPaterno = apellidoPaterno;
	}
	
	public void setApellidoMaterno(String apellidoMaterno){
		this.apellidoMaterno = apellidoMaterno;
	}
	
	public void setDireccion(Direccion direccion){
		this.direccion = direccion;
	}
	
	public void setFechaDeNacimiento(Fecha fechaDeNacimiento){
		this.fechaDeNacimiento = fechaDeNacimiento;
		calcularEdad();
	}
	
	public String getNombre(){
		return this.nombre;
	}
	
	public String getApellidoPaterno(){
		return this.apellidoPaterno;
	}
	
	public String getApellidoMaterno(){
		return this.apellidoMaterno;
	}
	
	public String getDireccion(){
		return this.direccion.toString();
	}
	
	public String getFechaDeNacimiento(){
		return this.fechaDeNacimiento.toString();
	}
	
	private void calcularEdad(){
		this.edad = 2016 - Integer.parseInt(this.fechaDeNacimiento.toString().substring(6,10));
	}
	
	public int getEdad(){
		return this.edad;
	}
}