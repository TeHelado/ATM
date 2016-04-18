public class DespachadorDeBilletes{
	private int numBilletes;
	private double denominacionBillete;

	public DespachadorDeBilletes(int numBilletes, double denominacionBillete){
		this.numBilletes = numBilletes;
		this.denominacionBillete = denominacionBillete;
	}

	public int getNumBilletes(){
		return this.numBilletes;
	}
	
	public double getDenominacionBillete(){
		return this.denominacionBillete;
	}

	public void venderBillete(){
		if(numBilletes>0){
			numBilletes = numBilletes - 1;
		}
	}
}	