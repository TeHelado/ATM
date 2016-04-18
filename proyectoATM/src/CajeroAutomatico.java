import java.util.Scanner;
import java.util.Random;
public class CajeroAutomatico{
	private DespachadorDeBilletes[] billetes;
	private double efectivoCajero;
	private int numMaxBilletes;
	
	public CajeroAutomatico(){
		billetes = new DespachadorDeBilletes[5];
		this.numMaxBilletes = 5;
		this.efectivoCajero = 0;
		inicializarDespachadorDeBilletes();
	}
	
	public int getNumMaxBilletes(){
		return this.numMaxBilletes;
	}
	
	public DespachadorDeBilletes getBillete(int i){
		return this.billetes[i];
	}
	
	public void mostrarMenuATM(Banco banco, Scanner teclado){
		int pos=0;
		boolean NIPReconocido = false;	
		System.out.print("\nInserte su NIP: ");
		String NIPIngresado = teclado.next();
		for(int i = 0; i < banco.getUltimoElemento(); i++){
			if(NIPIngresado.equals(banco.getCliente(i).getNIP())){
				pos = i;
				NIPReconocido = true;
			}
		}
		if(NIPReconocido == true){
			int opc;
			do{
				System.out.println("1.- Cambio de NIP");
				System.out.println("2.- Consulta de Saldo");
				System.out.println("3.- Abono en Cuenta");
				System.out.println("4.- Retiro en Cuenta");
				System.out.println("5.- Pago de Servicios");
				System.out.println("6.- Salir");
				System.out.print("\nOpcion: ");
				opc = teclado.nextInt();
				switch(opc){
					case 1:
						System.out.println("Inserte el nuevo NIP: ");
						banco.getCliente(pos).setNIP(teclado.next());
						System.out.println("NIP cambiado");
						break;
					case 2:
						if(banco.getCliente(pos).getNumCuentas()>0){
							double miSaldo = 0;
							for(int i = 0; i < banco.getCliente(pos).getNumCuentas(); i++){
								miSaldo += banco.getCliente(pos).getCuenta(i).getSaldo();
							}
							System.out.println("Tu saldo es de: $" + miSaldo);
						}
						else{
							System.out.println("No tienes cuentas, favor de crear una");
						}
						break;
					case 3:
						if(banco.getCliente(pos).getNumCuentas()>0){
							int cuenta = 0;
							do{
								System.out.println("Tienes " + banco.getCliente(pos).getNumCuentas() + " cuentas");
								System.out.println("En que cuenta deseas depositar? (1 = ahorro, 2 = cheques) Opcion: ");
								cuenta = teclado.nextInt();
							} while(cuenta<1||cuenta>banco.getCliente(pos).getNumCuentas());
							cuenta--;
							System.out.println("Inserte el dinero a depositar: ");
							if(banco.getCliente(pos).getCuenta(cuenta).depositarCuenta(teclado.nextDouble())==true)
								System.out.println("Depositado con exito en la cuenta " + cuenta);
							else
								System.out.println("No se pudo depositar en la cuenta" + cuenta);
						}
						else{
							System.out.println("No tienes cuentas, favor de crear una");
						}
						break;
					case 4:
						if(banco.getCliente(pos).getNumCuentas()>0){
							int cuenta = 0;
							double saldo, saldo2;
							do{
								System.out.println("Tienes " + banco.getCliente(pos).getNumCuentas() + " cuentas");
								System.out.println("En que cuenta deseas retirar? (1 = ahorro, 2 = cheques) Opcion: ");
								cuenta = teclado.nextInt();
							} while(cuenta<1||cuenta>banco.getCliente(pos).getNumCuentas());
							cuenta--;
							System.out.println("Inserte el dinero a retirar: ");
							saldo = teclado.nextDouble();
							if (this.efectivoCajero < saldo)
								System.out.println("Fondos insuficientes en el cajero.");
							else
							{
								if (CalculaBilletes((int)saldo))
								{
									if (banco.getCliente(pos).getCuenta(cuenta) instanceof CuentaAhorro)
									{
										if(banco.getCliente(pos).getCuenta(cuenta).retirarACuenta(saldo)==true)
										{
											System.out.println("Retirado con exito de la cuenta " + cuenta);
											RetirarBilletes((int)saldo);
										}	
										else
											System.out.println("No se pudo retirar de la cuenta" + cuenta);
									}
									else{
										if(banco.getCliente(pos).getCuenta(cuenta).getSaldo() + ((CuentaCheques)(banco.getCliente(pos).getCuenta(cuenta))).getCuentaDeAhorro().getSaldo() >= saldo)
										{
											if (banco.getCliente(pos).getCuenta(cuenta).getSaldo() >= saldo)
											{
												if(banco.getCliente(pos).getCuenta(cuenta).retirarACuenta(saldo)==true)
												{
													System.out.println("Retirado con exito de la cuenta " + cuenta);
													RetirarBilletes((int)saldo);
												}
												else
													System.out.println("No se pudo retirar de la cuenta" + cuenta);
											}
											else
											{
												saldo2 = banco.getCliente(pos).getCuenta(cuenta).getSaldo();
												if(banco.getCliente(pos).getCuenta(cuenta).retirarACuenta(saldo2)==true)
												{
													saldo -= saldo2;
													if(((CuentaCheques)(banco.getCliente(pos).getCuenta(cuenta))).getCuentaDeAhorro().retirarACuenta(saldo)==true)
													{
														System.out.println("Retirado con exito de la cuenta " + cuenta);
														RetirarBilletes((int)saldo + (int)saldo2);
													}
													else
														System.out.println("No se pudo retirar de la cuenta" + cuenta);
												}
												else
													System.out.println("No se pudo retirar de la cuenta" + cuenta);	
											}
										}
										else
											System.out.println("No se pudo retirar de la cuenta" + cuenta);
									}
								}
								else
									System.out.println("Billetes insuficientes en el cajero.");
							}
						}
						else{
							System.out.println("No tienes cuentas, favor de crear una");
						}
						break;
					case 5:
						break;
					default:
						System.out.println("Selecciona una opcion valida");
				}
			} while(opc!=6);
		}
		else{
			System.out.println("El NIP no coincide");
		}
	}
	
	/*private void mostrarProductos(){
		for(int i=0;i<getNumMaxJugos();i++){
			System.out.println((i+1) + ". Jugo de " + jugos[i].getNombreProducto() + " Precio: $" + jugos[i].getPrecioProducto() +" Cuantos: " + jugos[i].getNumProductos());
		}
	}*/
	
	/*public void despacharBilletes(double importe){
		if(jugo.getNumProductos()>0){
			double cambio;
			cambio = cajaRegistradora.aceptaEfectivoCliente(importe,jugo.getPrecioProducto());
			if(cambio == -1){
				throw new DespachadorVacio("\nImporte Insuficiente","\n\nSu cambio: $" + importe + "\n");
			}else{
				jugo.venderProducto();
				System.out.println("\nSu cambio: $" + cambio + "\n");
			}
		}
		else{
			throw new DespachadorVacio("\nLo siento el jugo de ",jugo.getNombreProducto() + " esta agotado" + "\n\nSu cambio: $" + importe + "\n");
		}
	}*/
	
	private boolean CalculaBilletes(int cantidad){
		if (this.efectivoCajero < cantidad)
			return false;
		boolean quedanBilletes = true;
		int cantidadBilletes;
		while (quedanBilletes && cantidad > 0)
		{
			for(int i=0;i<getNumMaxBilletes();i++){
				cantidadBilletes = this.billetes[i].getNumBilletes();
				if (this.billetes[i].getNombreBillete() == 50 && Math.floorMod(cantidad - 50, 20) != 0)
				{
					i++;
					cantidadBilletes = this.billetes[i].getNumBilletes();
				}
					
				while(cantidadBilletes > 0 && cantidad - this.billetes[i].getNombreBillete() >= 0)
				{
					cantidad -= this.billetes[i].getNombreBillete();
					cantidadBilletes--;
					if (cantidad == 0)
						return true;
				}
			}
			quedanBilletes = false;
		}
		return false;
	}
	
	private void RetirarBilletes(int cantidad){
		if (this.efectivoCajero < cantidad)
			return;
		int aux = cantidad;
		boolean quedanBilletes = true;
		for(int i=0;i<getNumMaxBilletes();i++){
			while(this.billetes[i].getNumBilletes() > 0 && cantidad - this.billetes[i].getNombreBillete() >= 0)
			{
				cantidad -= this.billetes[i].getNombreBillete();
				this.billetes[i].venderProducto();
				if (cantidad == 0)
				{
					this.efectivoCajero -= aux;
					return;
				}	
			}
		}
		return;
	}
	private void inicializarDespachadorDeBilletes(){
		Random numRandom = new Random();
		double nombreBilletes[][] = {{500, 10}, {200, 20}, {100, 30}, {50, 40}, {20, 50}};
		for(int i=0;i<getNumMaxBilletes();i++){
			this.billetes[i] = new DespachadorDeBilletes(nombreBilletes[i][0],(int)nombreBilletes[i][1]);
			this.efectivoCajero = this.efectivoCajero + ((int)this.billetes[i].getNombreBillete() * this.billetes[i].getNumBilletes()); 
		}
	}
}