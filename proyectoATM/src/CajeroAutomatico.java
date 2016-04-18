import java.util.Random;

public class CajeroAutomatico{
	private DespachadorDeBilletes[] billetes;
	private double efectivoCaja;
	private Random rnd;

	public CajeroAutomatico(double nombreBilletes[]){
		billetes = new DespachadorDeBilletes[nombreBilletes.length];
		this.efectivoCaja = 0;
		inicializarDespachadorDeBilletes(nombreBilletes);
	}
	
	public DespachadorDeBilletes getBillete(int index){
		return this.billetes[index - 1];
	}
	
	public void mostrarMenuPrincipal(){
		mostrarProductos();
	}
	
	public double getEfectivo(){
		return this.efectivoCaja;
	}
	
	private void mostrarProductos(){
		System.out.println("BIENVENIDO");
		System.out.format("\nEfectivo en cajero: $%.2f\n", this.getEfectivo());
		for(int i=0; i<billetes.length;i++){
			System.out.format((i+1) + ".-  $%.2f", billetes[i].getDenominacionBillete());
			System.out.print(", Cantidad: \n" + billetes[i].getNumBilletes());
		}
	}

	private void inicializarDespachadorDeBilletes(double nombreBilletes[]){
		rnd = new Random();
		int rand;
		for(int i=0; i<nombreBilletes.length;i++){
			rand = rnd.nextInt(10);
			billetes[i] = new DespachadorDeBilletes(rand, nombreBilletes[i]);
			this.efectivoCaja += rand * nombreBilletes[i];
		}
	}
	
}	
