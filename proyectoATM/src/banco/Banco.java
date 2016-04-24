package banco;
import java.io.*;
import java.util.Scanner;
import java.util.Collection;
import java.util.Vector;
import javax.swing.JOptionPane;
import personales.*;
import cuentas.*;

public class Banco{
	private Vector<Cliente> clientes;
	
	public Banco(){
		try{
			FileInputStream archivoEntrada = new FileInputStream("clientes.ser");
			ObjectInputStream entrada = new ObjectInputStream(archivoEntrada);
			clientes = (Vector<Cliente>)entrada.readObject();
			entrada.close();
				String movimiento;
				for(int i = 0 ; i < getUltimoElemento() ; i++){
					/*
					try{
						String nombreArchivo = getCliente(i).getNombre() + getCliente(i).getApellidoPaterno() + getCliente(i).getApellidoMaterno() + "movimientos.txt";
						FileReader lector = new FileReader(nombreArchivo);
						BufferedReader bufferlector = new BufferedReader(lector);
						while((movimiento = bufferlector.readLine()) != null){
							getCliente(i).setMovimiento(movimiento);
						}
						lector.close();
					}catch(IOException e){
					}*/
				}
		}catch(IOException e){
			clientes = new Vector<Cliente>(20, 1);
		}catch(ClassNotFoundException e1){
		}
	}
	
	public Cliente getCliente(int i){
		return this.clientes.get(i);
	}
	
	public int getUltimoElemento(){
		return this.clientes.size();
	}
	
	public void addClient(String n, String aP, String aM, int dia, int mes, int anio, String calle, String colonia, int numero, String delegacion, String ciudad, String pais, String NIP){
		Fecha fechaCliente = new Fecha();
		fechaCliente.setFecha(dia,mes,anio);
		Cliente nuevoCliente = new Cliente(n,aP,aM, fechaCliente);
		Direccion dirCliente = new Direccion(calle,numero,colonia);
		dirCliente.setDelegacion(delegacion);
		dirCliente.setCiudad(ciudad);
		dirCliente.setPais(pais);
		nuevoCliente.setDireccion(dirCliente);
		nuevoCliente.setNIP(NIP);	
		clientes.addElement(nuevoCliente);
		JOptionPane.showMessageDialog(null, "Cliente nuevo: " + n, "Aviso", JOptionPane.PLAIN_MESSAGE);
	}
	
	public void addCuenta(int tipo, int pos, double saldo){
		switch(tipo){
			case 1:
				if (saldo >= 50){
					getCliente(pos).agregarCuenta(new CuentaAhorro(saldo, 0));
					JOptionPane.showMessageDialog(null, "Cuenta nueva creada.", "Aviso", JOptionPane.PLAIN_MESSAGE);
				}
					
				else
					System.out.println("La cantidad minima son $50");
				break;
			case 2:
				if (getCliente(pos).getNumCuentas() == 0)
					JOptionPane.showMessageDialog(null, "Necesitas una cuenta de ahorro.", "Error", JOptionPane.ERROR_MESSAGE);
				else
				{
					if (saldo >= 50)
					{
						getCliente(pos).agregarCuenta(new CuentaCheques(saldo));
						((CuentaCheques)getCliente(pos).getCuenta(1)).setCuentaDeAhorro((CuentaAhorro)getCliente(pos).getCuenta(0));
						JOptionPane.showMessageDialog(null, "Cuenta nueva creada.", "Aviso", JOptionPane.PLAIN_MESSAGE);
					}
					else
						System.out.println("La cantidad minima son $50");
				}
				break;
		}
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
					
					Fecha fechaCliente = new Fecha();
					int dia,mes,anio;
					System.out.print("Dia de nacimiento: ");
					dia = teclado.nextInt();
					System.out.print("Mes de nacimiento: ");
					mes = teclado.nextInt();
					System.out.print("Anio de nacimiento: ");
					anio = teclado.nextInt();
					fechaCliente.setFecha(dia,mes,anio);
					
					Cliente nuevoCliente = new Cliente(n,aP,aM, fechaCliente);
					
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
					
				
					
					System.out.print("\nIngresa un NIP: ");
					nuevoCliente.setNIP(teclado.next());
					
					clientes.addElement(nuevoCliente);
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
								System.out.println("3.Salir");
								System.out.print("\nOpcion: ");
								opc2 = teclado.nextInt();
								switch(opc2){
									case 1:
										System.out.print("Deposita in saldo inicial (minimo $50): ");
										saldo = teclado.nextDouble();
										if (saldo >= 50)
											getCliente(pos).agregarCuenta(new CuentaAhorro(saldo, 0));
										else
											System.out.println("La cantidad minima son $50");
										break;
									case 2:
										if (getCliente(pos).getNumCuentas() == 0)
											System.out.println("Necesitas una cuenta de ahorro.");
										else
										{
											System.out.print("Deposita in saldo inicial (minimo $50): ");
											saldo = teclado.nextDouble();
											if (saldo >= 50)
											{
												getCliente(pos).agregarCuenta(new CuentaCheques(saldo));
												((CuentaCheques)getCliente(pos).getCuenta(1)).setCuentaDeAhorro((CuentaAhorro)getCliente(pos).getCuenta(0));
											}
											else
												System.out.println("La cantidad minima son $50");
										}
										break;
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
			}
		}while(opc!=3);
		try{
			FileOutputStream archivoSalida = new FileOutputStream("clientes.ser");
			ObjectOutputStream salida = new ObjectOutputStream(archivoSalida);
			salida.writeObject(clientes);
			salida.close();
		}catch(IOException e){
			System.err.println("No se pudo guardar el archivo");
		}
	}
}