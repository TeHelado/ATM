public class Banco
	private String name;
	private Cliente[] cliente;
	private int cont = 0;

	public Banco(){
		name = "LE BANQUE";
		cliente = new Cliente[20];
	}

	public void setCliente(Cliente c){
		cliente(this.cont) = c;
		this.cont = this.cont + 1;
	}
	
	public Cliente getCliente(int index){
		return cliente[index];
	}
}
