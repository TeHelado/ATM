public class TestATM(){
	public void main(String args[]){
		Scanner teclado = new Scanner(System.in);
		Banco banco = new Banco();
		CajeroAutomatico ca = new CajeroAutomatico();
		int opc;
		
		do
		{
			System.out.println("BIENVENIDO");
			System.out.println("1.-Administrador de clientes");
			System.out.println("2.-ATM");
			System.out.println("3.-Salir");
			opc = teclado.nextInt();
	
			switch(opc){
				case 1:{
					banco.mostrarMenuCliente(teclado);
				}break;
				case 2:{
					ca.mostrarMenuATM(banco,teclado);
				}
				case 3:{
					System.out.println("Baia baia :v"):
				}
			}
		}while(opc != 3);
	}

}

