package personales;
public class Fecha{
	private int dia;
	private int mes;
	private int anio;
	
	public void setFecha(int d,int m,int a){
		if(validarFecha(d,m,a)==true){
			this.dia = d;
			this.mes = m;
			this.anio = a;
		}
		else{
			System.out.println("Error: Fecha invalida");
		}
	}
	
	public String getFecha(){
		if(this.mes==1){
			return(this.dia + " de Enero del " + this.anio);
		}
		if(this.mes==2){
			return(this.dia + " de Febrero del " + this.anio);
		}
		if(this.mes==3){
			return(this.dia + " de Marzo del " + this.anio);
		}
		if(this.mes==4){
			return(this.dia + " de Abril del " + this.anio);
		}
		if(this.mes==5){
			return(this.dia + " de Mayo del " + this.anio);
		}
		if(this.mes==6){
			return(this.dia + " de Junio del " + this.anio);
		}
		if(this.mes==7){
			return(this.dia + " de Julio del " + this.anio);
		}
		if(this.mes==8){
			return(this.dia + " de Agosto del " + this.anio);
		}
		if(this.mes==9){
			return(this.dia + " de Septiembre del " + this.anio);
		}
		if(this.mes==10){
			return(this.dia + " de Octubre del " + this.anio);
		}
		if(this.mes==11){
			return(this.dia + " de Noviembre del " + this.anio);
		}
		if(this.mes==12){
			return(this.dia + " de Diciembre del " + this.anio);
		}
		else{
			return(null);
		}
	}
	
	public String toString(){
		if(this.mes>=1&&this.mes<=9){
			if(this.dia>=1&&this.dia<=9){
				return("0" + this.dia + "/0" + this.mes + "/" +this.anio);
			}
			else{
				return(this.dia + "/0" + this.mes + "/" +this.anio);
			}
		}
		if(this.dia>=1&&this.dia<=9){
			return("0" + this.dia + "/" + this.mes + "/" +this.anio);
		}
		else{
			return(this.dia + "/" + this.mes + "/" +this.anio);
		}
	}
	
	private boolean validarFecha(int d,int m,int a){
		if(a>=0&&a<=2016){
			if(m==1||m==3||m==5||m==7||m==8||m==10||m==12){
				if(d>=1&&d<=31){
					return(true);
				}
				else{
					return(false);
				}
			}
			if(m==4||m==6||m==9||m==11){
				if(d>=1&&d<=31){
					return(true);
				}
				else{
					return(false);
				}
			}
			else{
				if(a%4==0){
					if(a%100==0){
						if(a%400==0){
							if(d>=1&&d<=29){
								return(true);
							}
							else{
								return(false);
							}
						}
						else{
							if(d>=1&&d<=28){
								return(true);
							}
							else{
								return(false);
							}
						}
					}
					else{
						if(d>=1&&d<=29){
							return(true);
						}
						else{
							return(false);
						}
					}
				}
				else{
					if(d>=1&&d<=28){
						return(true);
					}
					else{
						return(false);
					}
				}
			}
		}
		else{
			return(false);
		}
	}
}