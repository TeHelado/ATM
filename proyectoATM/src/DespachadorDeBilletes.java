public class DespachadorDeBilletes{
	private double nombreBillete;
	private int numBilletes;
	
	public DespachadorDeBilletes(double nombreBillete,int numBilletes){
		this.nombreBillete = nombreBillete;
		this.numBilletes = numBilletes;
	}
	
	public double getNombreBillete(){
		return this.nombreBillete;
	}
	
	public int getNumBilletes(){
		return this.numBilletes;
	}
	
	public void venderProducto(){
		if(this.numBilletes>0)
			this.numBilletes--;
	}
}