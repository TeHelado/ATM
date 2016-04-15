package ejercicioRFC;
public class RFC{
	private String inicialesRFC;
	private String fechaRFC;
	
	public void setInicialesRFC(String n,String aP,String aM){
		if (inicialesRFC == null){
			inicialesRFC = "";
		}
		inicialesRFC = inicialesRFC + aP.substring(0,2).toUpperCase();
		inicialesRFC += aM.substring(0,1).toUpperCase();
		inicialesRFC += n.substring(0,1).toUpperCase();
		inicialesRFC = inicialesRFC.trim(); //quitar el espacio en blanco anteriormente añadido
	}
	
	public void setFechaRFC(String f){
		if (fechaRFC == null){
			fechaRFC = "";
		}
		if (f.length() == 10){
			fechaRFC = fechaRFC + f.substring(8,10);
			fechaRFC += f.substring(3,5);
			fechaRFC += f.substring(0,2);
			fechaRFC = fechaRFC.trim();
		}
		else{
			fechaRFC = fechaRFC + f.substring(7,9);
			fechaRFC += f.substring(2,4);
			fechaRFC += "0" + f.substring(0,1);
			fechaRFC = fechaRFC.trim();
		}
	}
	
	public String toString(){
		return(inicialesRFC + fechaRFC);
	}
}