import java.util.Scanner;
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
	
	public void mostrarMenuCliente(Scanner teclado){
		int opc;
		do{
			System.out.println("1.Alta Cliente");
			System.out.println("2.Alta Cuenta");
			System.out.println("3.Salir");
			System.out.print("\nOpcion: ");
			opc = teclado.nextInt();
			switch(opc){
				case 1:
					String n,aP,aM;
					System.out.print("Nombre: ");
					n = teclado.next();
					System.out.print("Apellido Paterno: ");
					aP = teclado.next();
					System.out.print("Apellido Materno: ");
					aM = teclado.next();
					Cliente nuevoCliente = new Cliente(n,aP,aM);
					
					String calle,colonia;
					int numero;
					System.out.print("Calle: ");
					calle = teclado.next();
					System.out.print("Numero: ");
					numero = teclado.nextInt();
					System.out.print("Colonia: ");
					colonia = teclado.next();
					Direccion dirCliente = new Direccion(calle,numero,colonia);
					System.out.print("Delegacion: ");
					dirCliente.setDelegacion(teclado.next());
					System.out.print("Ciudad: ");
					dirCliente.setCiudad(teclado.next());
					System.out.print("Pais: ");
					dirCliente.setPais(teclado.next());
					nuevoCliente.setDireccion(dirCliente);
					
					Fecha fechaCliente = new Fecha();
					int dia,mes,anio;
					System.out.print("Dia de nacimiento: ");
					dia = teclado.nextInt();
					System.out.print("Mes de nacimiento: ");
					mes = teclado.nextInt();
					System.out.print("Anio de nacimiento: ");
					anio = teclado.nextInt();
					fechaCliente.setFecha(dia,mes,anio);
					nuevoCliente.setFechaDeNacimiento(fechaCliente);
					
					System.out.print("\nIngresa un NIP: ");
					nuevoCliente.setNIP(teclado.next());
					
					clientes.add(nuevoCliente);
					break;
				case 2:
					int pos=0;
					boolean NIPReconocido = false;	
					System.out.print("\nInserte su NIP: ");
					String NIPIngresado = teclado.next();
					for(int i = 0; i < getUltimoElemento(); i++){
						if(NIPIngresado.equals(getCliente(i).getNIP())){
							pos = i;
							NIPReconocido = true;
						}
					}
					if(NIPReconocido == true){
						if (getCliente(pos).getNumCuentas() < 2)
						{
							int opc2;
							double saldo;
							do{
								System.out.println("1.Alta cuenta de ahorro");
								System.out.println("2.Alta cuenta de cheques");
								System.out.print("\nOpcion: ");
								opc2 = teclado.nextInt();
								switch(opc2){
									case 1:
										System.out.println("Deposita in saldo inicial (minimo $50): ");
										saldo = teclado.nextDouble();
										if (saldo >= 50)
											getCliente(pos).agregarCuenta(new CuentaAhorro(saldo, 0));
										else
											System.out.println("La cantidad minima son $50");
										break;
									case 2:
										System.out.println("Deposita in saldo inicial (minimo $50): ");
										saldo = teclado.nextDouble();
										if (saldo >= 50)
											getCliente(pos).agregarCuenta(new CuentaCheques(saldo));
										else
											System.out.println("La cantidad minima son $50");
										break;
									default:
										System.out.println("Inserta un numero valido");
								}
							}while(opc2!=3);
						}
						else{
							System.out.println("No puedes crear mas cuentas.");
						}
					}
					else{
						System.out.println("El NIP no coincide con ningun usuario registrado");
					}
					break;
				default:
					System.out.println("Selecciona una opcion valida");
			}
		}while(opc!=3);
	}
}