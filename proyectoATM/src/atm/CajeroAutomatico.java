package atm;
import java.util.Scanner;
import java.util.Random;
import cuentas.*;
import banco.*;
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
			int opc, opc2;
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
						System.out.print("Inserte el nuevo NIP: ");
						banco.getCliente(pos).setNIP(teclado.next());
						System.out.println("NIP cambiado");
						break;
					case 2:
						if(banco.getCliente(pos).getNumCuentas()>0){
							ImprimeSaldo(banco, pos);
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
								System.out.print("En que cuenta deseas depositar? (1 = ahorro, 2 = cheques) Opcion: ");
								cuenta = teclado.nextInt();
							} while(cuenta<1||cuenta>banco.getCliente(pos).getNumCuentas());
							cuenta--;
							System.out.print("Inserte el dinero a depositar: ");
							if(banco.getCliente(pos).getCuenta(cuenta).depositarCuenta(teclado.nextDouble())==true)
								System.out.println("Depositado con exito en la cuenta " + (cuenta+1));
							else
								System.out.println("No se pudo depositar en la cuenta" + (cuenta+1));
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
								System.out.print("En que cuenta deseas retirar? (1 = ahorro, 2 = cheques) Opcion: ");
								cuenta = teclado.nextInt();
							} while(cuenta<1||cuenta>banco.getCliente(pos).getNumCuentas());
							cuenta--;
							System.out.print("Inserte el dinero a retirar: ");
							saldo = teclado.nextDouble();
							if (this.efectivoCajero < saldo)
								System.out.println("Fondos insuficientes en el cajero.");
							else
							{
								if (CalculaBilletes((int)saldo))
								{
									if (banco.getCliente(pos).getCuenta(cuenta) instanceof CuentaAhorro)
									{
										try{
											banco.getCliente(pos).getCuenta(cuenta).retirarACuenta(saldo);
											System.out.println("Retirado con exito de la cuenta " + (cuenta+1));
											RetirarBilletes((int)saldo);
										}	
										catch(OverdraftException e){
											System.out.println(e.getMessage() + e.getDeficit());
										}
									}
									else{
										if(banco.getCliente(pos).getCuenta(cuenta).getSaldo() + ((CuentaCheques)(banco.getCliente(pos).getCuenta(cuenta))).getCuentaDeAhorro().getSaldo() >= saldo)
										{
											if (banco.getCliente(pos).getCuenta(cuenta).getSaldo() >= saldo)
											{
												try{
													banco.getCliente(pos).getCuenta(cuenta).retirarACuenta(saldo);
													System.out.println("Retirado con exito de la cuenta " + (cuenta+1));
													RetirarBilletes((int)saldo);
												}
												catch(OverdraftException e){
													System.out.println(e.getMessage() + e.getDeficit());
												}
											}
											else
											{
												saldo2 = banco.getCliente(pos).getCuenta(cuenta).getSaldo();
												try{
													banco.getCliente(pos).getCuenta(cuenta).retirarACuenta(saldo2);
													saldo -= saldo2;
													try{
														((CuentaCheques)(banco.getCliente(pos).getCuenta(cuenta))).getCuentaDeAhorro().retirarACuenta(saldo);
														System.out.println("Retirado con exito de la cuenta " + (cuenta+1));
														RetirarBilletes((int)saldo + (int)saldo2);
													}
													catch(OverdraftException e){
														System.out.println(e.getMessage() + e.getDeficit());
													}
												}
												catch(OverdraftException e){
													System.out.println(e.getMessage() + e.getDeficit());
												}
											}
										}
										else
											System.out.println("No se pudo retirar de la cuenta" + (cuenta+1));
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
						if(banco.getCliente(pos).getNumCuentas()>0){
							int cuenta = 0;
							do{
								System.out.println("Tienes " + banco.getCliente(pos).getNumCuentas() + " cuentas");
								System.out.print("De que cuenta deseas pagar? (1 = ahorro, 2 = cheques) Opcion: ");
								cuenta = teclado.nextInt();
							} while(cuenta<1||cuenta>banco.getCliente(pos).getNumCuentas());
							cuenta--;
							do{
								System.out.print("1.- pago de luz\n2.- pago de agua\n3.- pago de gas\n4.- salir\n\nOpcion: ");
								opc2 = teclado.nextInt();
								switch(opc2)
								{
									case 1:
										pagarServicio(500, "la luz.", pos, banco, cuenta);
										break;
									case 2:
										pagarServicio(50, "el agua.", pos, banco, cuenta);
										break;
									case 3:
										pagarServicio(350, "el gas.", pos, banco, cuenta);
										break;
								}
							}
							while(opc2 != 4);
						}
						else{
							System.out.println("No tienes cuentas, favor de crear una");
						}
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
	
	private void ImprimeSaldo(Banco banco, int pos){
		if(banco.getCliente(pos).getNumCuentas() == 0)
			return;
		double miSaldo = 0;
		for(int i = 0; i < banco.getCliente(pos).getNumCuentas(); i++){
			miSaldo += banco.getCliente(pos).getCuenta(i).getSaldo();
		}
		System.out.println("Tu saldo es de: $" + miSaldo);
	}
	
	private void pagarServicio(double saldo, String servicio, int pos, Banco banco, int cuenta){
		double saldo2;
		int cantidadBilletes;
		if (banco.getCliente(pos).getCuenta(cuenta) instanceof CuentaAhorro)
		{
			try{
				banco.getCliente(pos).getCuenta(cuenta).retirarACuenta(saldo);
				System.out.println("Se pago " + servicio);
				ImprimeSaldo(banco, pos);
			}
			catch(OverdraftException e){
				System.out.println(e.getMessage() + e.getDeficit());
			}
		}
		else{
			if(banco.getCliente(pos).getCuenta(cuenta).getSaldo() + ((CuentaCheques)(banco.getCliente(pos).getCuenta(cuenta))).getCuentaDeAhorro().getSaldo() >= saldo)
			{
				if (banco.getCliente(pos).getCuenta(cuenta).getSaldo() >= saldo)
				{
					try{
						banco.getCliente(pos).getCuenta(cuenta).retirarACuenta(saldo);
						System.out.println("Se pago " + servicio);
						ImprimeSaldo(banco, pos);
					}
					catch(OverdraftException e){
						System.out.println(e.getMessage() + e.getDeficit());
					}
				}
				else
				{
					saldo2 = banco.getCliente(pos).getCuenta(cuenta).getSaldo();
					try{
						banco.getCliente(pos).getCuenta(cuenta).retirarACuenta(saldo2);
						saldo -= saldo2;
						try{
							((CuentaCheques)(banco.getCliente(pos).getCuenta(cuenta))).getCuentaDeAhorro().retirarACuenta(saldo);
							System.out.println("Se pago " + servicio);
							ImprimeSaldo(banco, pos);
						}
						catch(OverdraftException e){
							System.out.println(e.getMessage() + e.getDeficit());
						}
					}
					catch(OverdraftException e){
						System.out.println(e.getMessage() + e.getDeficit());
					}
				}
			}
			else
				System.out.println("No se pudo pagar, fondos insuficientes.");
		}
	}
	
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
				if (this.billetes[i].getNombreBillete() == 50 && Math.floorMod(cantidad - 50, 20) != 0)
				{
					i++;
				}
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