import java.util.Collection;
import java.util.Vector;
public class Banco{
	private Vector<Cliente> clientes;
	
	public Banco(){
		clientes = new Vector<Cliente>();
	}
	
	public Cliente getCliente(int i){
		return this.clientes.get(i);
	}
	
	public int getUltimoElemento(){
		return this.clientes.lastIndexOf(clientes);
	}
}