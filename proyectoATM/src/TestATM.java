import java.util.*;
import banco.*;
import atm.*;
import cuentas.*;
import java.io.*;
public class TestATM{
	public static void main(String args[]){
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
			System.out.print("\nOpcion: ");
			opc = teclado.nextInt();
	
			switch(opc){
				case 1:{
					banco.mostrarMenuCliente(teclado);
				}break;
				case 2:{
					ca.mostrarMenuATM(banco,teclado);
				}break;
				case 3:{
					System.out.println("Baia baia :v");
				}break;
			}
		}while(opc != 3);
		try{
			FileOutputStream archivoSalida = new FileOutputStream("clientes.ser");
			ObjectOutputStream salida = new ObjectOutputStream(archivoSalida);
			salida.writeObject(banco.getBanco());
			salida.close();
		}catch(IOException e){
			System.err.println("No se pudo guardar el archivo");
		}
	}

}