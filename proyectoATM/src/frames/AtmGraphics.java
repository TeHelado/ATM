package frames;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import banco.*;
import atm.*;
import cuentas.*;

public class AtmGraphics extends JFrame{

	private JButton button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8;
	private JButton num_1, num_2, num_3, num_4, num_5, num_6, num_7, num_8, num_9, num_0, num_00, num_000;
	private JButton button_corregir, button_cancelar;
	private JPanel panel_teclado, panel_CoCa, panel_num, panel_west, panel_east, panel_center;
	private JLabel label_1, label_2, label_3, label_4, label_5, label_6, label_7, label_8, label_9, label_10;
	private GridBagConstraints grid;
	private ButtonHandler handler;
	private TecladoHandler handler_teclado;
	private FormatoCliente formato_cliente;
	private CuentaNueva cuenta_nueva;
	private Banco banco;
	private ConsultaInfo consulta_info;
	private boolean password = false, teclado_activo=false;
	private String data;
	private int pos=0, tipo=-1; //0=ahorros 1=cheques
	private CajeroAutomatico cajero_automatico;

	public AtmGraphics(){
		super("ATM");
		banco = new Banco();
		cajero_automatico = new CajeroAutomatico(banco);

		button_1 = new JButton();
		button_1.setActionCommand("Admn_clientes");
		button_2 = new JButton();
		button_2.setActionCommand("ATM");
		button_3 = new JButton();
		button_4 = new JButton();
		button_5 = new JButton();
		button_6 = new JButton();
		button_7 = new JButton();
		button_8 = new JButton();
		
		num_1 = new JButton("1");
		num_1.setActionCommand("N1");
		num_2 = new JButton("2");
		num_2.setActionCommand("N2");
		num_3 = new JButton("3");
		num_3.setActionCommand("N3");
		num_4 = new JButton("4");
		num_4.setActionCommand("N4");
		num_5 = new JButton("5");
		num_5.setActionCommand("N5");
		num_6 = new JButton("6");
		num_6.setActionCommand("N6");
		num_7 = new JButton("7");
		num_7.setActionCommand("N7");
		num_8 = new JButton("8");
		num_8.setActionCommand("N8");
		num_9 = new JButton("9");
		num_9.setActionCommand("N9");
		num_0 = new JButton("0");
		num_0.setActionCommand("N0");
		num_00 = new JButton("00");
		num_00.setActionCommand("N00");
		num_000 = new JButton("000");
		num_000.setActionCommand("N000");

		button_corregir = new JButton("Corregir");
		button_corregir.setActionCommand("Corregir");
		button_cancelar = new JButton("Cancelar");
		button_cancelar.setActionCommand("Cancelar");

		panel_teclado = new JPanel(new BorderLayout());
		panel_CoCa = new JPanel();
		panel_num = new JPanel();
		panel_west = new JPanel(new BorderLayout());
		panel_east = new JPanel(new BorderLayout());
		panel_center = new JPanel(new GridBagLayout());

		grid = new GridBagConstraints();
		grid.insets = new Insets(0,60,25,60);
		grid.anchor = GridBagConstraints.WEST;

		label_1 = new JLabel("Administración de clientes");
		label_2 = new JLabel("ATM");
		label_3 = new JLabel(" ");
		label_4 = new JLabel(" ");
		label_5 = new JLabel(" ");
		label_6 = new JLabel(" ");
		label_7 = new JLabel(" ");
		label_8 = new JLabel(" ");
		label_9 = new JLabel("BIENVENIDO");
		label_10 = new JLabel(" ");//Nombre de cliente

		handler = new ButtonHandler();
		handler_teclado = new TecladoHandler();

		formato_cliente = new FormatoCliente();
		cuenta_nueva = new CuentaNueva();
		
		consulta_info = new ConsultaInfo();
		pack();
	}

	public class ButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			switch(e.getActionCommand()){
				case "Admn_clientes":{
					label_9.setText("ADMINISTRACIÓN DE CLIENTES");
					label_1.setText("Alta de nuevo cliente");
					label_2.setText("Alta de nueva cuenta");
					label_3.setText("Regresar");
					button_1.setActionCommand("Alta_cliente");
					button_2.setActionCommand("Alta_cuenta");
					button_3.setActionCommand("Regresar_principal");
				}break;
				case "ATM":{
					label_9.setText("ATM");
					label_9.setText("Ingrese su NIP: ");
					data = "";
					label_1.setText(" ");
					label_2.setText(" ");
					label_3.setText(" ");
					button_1.setActionCommand("B1");
					button_2.setActionCommand("B2");
					button_3.setActionCommand("B3");
					password = true;
					teclado_activo = true;
					label_8.setText("Aceptar");
					button_8.setActionCommand("ATM_menu");
				}break;
				case "ATM_menu":{
					if (label_1.getText().length() > 2){
						 try{
							boolean NIPReconocido = false;	
							for(int i = 0; i < banco.getUltimoElemento(); i++){
								if(data.equals(banco.getCliente(i).getNIP())){
									pos = i;
									NIPReconocido = true;
								}
							}
							if(!NIPReconocido)
								JOptionPane.showMessageDialog(null, "No se encontro el NIP.", "Error", JOptionPane.ERROR_MESSAGE);
							else{
								label_9.setText("ATM");
								label_1.setText("Cambio de NIP");
								label_2.setText("Consulta de saldo");
								label_3.setText("Abono de cuenta");
								label_4.setText("Retiro de cuenta");
								label_5.setText("Pago de servicios");
								label_6.setText("Consulta de información");
								label_7.setText("Consulta de movimientos");
								label_8.setText("Regresar");	
								label_10.setText(banco.getCliente(pos).getNombre() + " " + banco.getCliente(pos).getApellidoPaterno()); 
								pack();
								button_1.setActionCommand("Cambio_NIP");
								button_2.setActionCommand("Consulta_saldo");
								button_3.setActionCommand("Abono_cuenta");
								button_4.setActionCommand("Retiro_cuenta");
								button_5.setActionCommand("Pago_servicios");
								button_6.setActionCommand("Consulta_info");
								button_7.setActionCommand("Consulta_mov");
								button_8.setActionCommand("Regresar_principal");
							}
						 }
						 catch (Exception k){
							JOptionPane.showMessageDialog(null, k.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			 			 }
					}
					else{
						JOptionPane.showMessageDialog(null, "Ingresa tu NIP", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}break;
				case "Regresar_principal":{
					label_9.setText("BIENVENIDO");
					label_1.setText("Administración de clientes");
					label_2.setText("ATM");
					label_3.setText(" ");
					label_4.setText(" ");
					label_5.setText(" ");
					label_6.setText(" ");
					label_7.setText(" ");
					label_8.setText(" ");
					label_10.setText(" ");
					button_1.setActionCommand("Admn_clientes");
					button_2.setActionCommand("ATM");
					button_3.setActionCommand("B3");
					button_4.setActionCommand("B4");
					button_5.setActionCommand("B5");
					button_6.setActionCommand("B6");
					button_7.setActionCommand("B7");
					button_8.setActionCommand("B8");
					password = false;
					teclado_activo = false;
					tipo = -1;
				}break;
				case "Alta_cliente":{
					formato_cliente.presentFrame(banco);
				}break;
				case "Alta_cuenta":{
					label_9.setText("Ingrese su NIP: ");
					data = "";
					label_1.setText(" ");
					label_2.setText(" ");
					label_3.setText(" ");
					button_1.setActionCommand("B1");
					button_2.setActionCommand("B2");
					button_3.setActionCommand("B3");
					password = true;
					teclado_activo = true;
					label_8.setText("Aceptar");
					button_8.setActionCommand("Cuenta_nueva");
					//cuenta_nueva.presentarFrame(banco);
				}break;
				case "Cuenta_nueva":{
					if (label_1.getText().length() > 2){
						 try{
							boolean NIPReconocido = false;	
							for(int i = 0; i < banco.getUltimoElemento(); i++){
								if(data.equals(banco.getCliente(i).getNIP())){
									pos = i;
									NIPReconocido = true;
								}
							}
							if(!NIPReconocido)
								JOptionPane.showMessageDialog(null, "No se encontro el NIP.", "Error", JOptionPane.ERROR_MESSAGE);
							else
								if (banco.getCliente(pos).getNumCuentas() < 2){
									label_9.setText("SELECCIONA UN TIPO DE CUENTA");
									label_1.setText("Ahorros");
									label_2.setText("Cheques");
									button_1.setActionCommand("ahorros");
									button_2.setActionCommand("cheques");
									button_8.setActionCommand("Cuenta_nueva1");
									pack();
								}
								else
									JOptionPane.showMessageDialog(null, "Este cliente ya tiene 2 cuentas.", "Error", JOptionPane.ERROR_MESSAGE);
						 }
						 catch (Exception k){
							JOptionPane.showMessageDialog(null, k.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			 			 }
					}
					else{
						JOptionPane.showMessageDialog(null, "Ingresa tu NIP", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}break;
				case "ahorros":{
					tipo = 0;
				}break;
				case "cheques":{
					tipo = 1;
				}break;
				case "Cuenta_nueva1":{
					if (tipo == -1)
						JOptionPane.showMessageDialog(null, "Selecciona un tipo de cuenta", "Error", JOptionPane.ERROR_MESSAGE);
					else{
						label_9.setText("DEPOSITA UN SALDO INICIAL (MINIMO $50): ");
						button_1.setActionCommand("B1");
						button_2.setActionCommand("B2");
						button_3.setActionCommand("B3");
						label_1.setText(" ");
						label_2.setText(" ");
						label_3.setText(" ");
						button_8.setActionCommand("Cuenta_nueva2");
						password = false;
						data = "0";
						pack();
					}
				}break;
				case "Cuenta_nueva2":{
					label_9.setText("DEPOSITA UN SALDO INICIAL (MINIMO $50): ");
						if (Integer.parseInt(data) >= 50){
							banco.addCuenta(tipo, pos, Double.parseDouble(data));
							label_9.setText("BIENVENIDO");
							label_1.setText("Administración de clientes");
							label_2.setText("ATM");
							label_3.setText(" ");
							label_4.setText(" ");
							label_5.setText(" ");
							label_6.setText(" ");
							label_7.setText(" ");
							label_8.setText(" ");
							button_1.setActionCommand("Admn_clientes");
							button_2.setActionCommand("ATM");
							button_3.setActionCommand("B3");
							button_4.setActionCommand("B4");
							button_5.setActionCommand("B5");
							button_6.setActionCommand("B6");
							button_7.setActionCommand("B7");
							button_8.setActionCommand("B8");
							data = "";
							password = false;
							teclado_activo = false;
							tipo = -1;
							pack();
						}
						else
							JOptionPane.showMessageDialog(null, "La cantidad minima son $50", "Error", JOptionPane.ERROR_MESSAGE);
				}break;
				case "Retiro_cuenta":{
					if (banco.getCliente(pos).getNumCuentas() >= 1){
						label_9.setText("SELECCIONA UN TIPO DE CUENTA");
						label_1.setText("Ahorros");
						label_8.setText("Aeptar");
						button_8.setActionCommand("Retiro_cuenta1");
						button_1.setActionCommand("ahorros");
						label_3.setText(" ");
						label_4.setText(" ");
						label_5.setText(" ");
						label_6.setText(" ");
						label_7.setText(" ");
						button_3.setActionCommand("B3");
						button_4.setActionCommand("B4");
						button_5.setActionCommand("B5");
						button_6.setActionCommand("B6");
						button_7.setActionCommand("B7");
						if(banco.getCliente(pos).getNumCuentas() > 1){
							label_2.setText("Cheques");
							button_2.setActionCommand("cheques");
						}
						else{
							label_2.setText(" ");
							button_2.setActionCommand("B2");
						}
						pack();
					}
					else
						JOptionPane.showMessageDialog(null, "No tienes cuentas.", "Error", JOptionPane.ERROR_MESSAGE);
				}break;
				case "Retiro_cuenta1":{
					if(tipo == -1)
						JOptionPane.showMessageDialog(null, "Elige una cuenta.", "Error", JOptionPane.ERROR_MESSAGE);
					else{
						label_9.setText("RETIRO");
						label_1.setText("$50.00");
						label_2.setText("$100.00");
						label_3.setText("$200.00");
						label_4.setText("$300.00");
						label_5.setText("$400.00");
						label_6.setText("$500.00");
						label_7.setText("$1000.00");
						label_8.setText("Otra cantidad");	
						label_10.setText(banco.getCliente(pos).getNombre() + " " + banco.getCliente(pos).getApellidoPaterno());
						pack();
						button_1.setActionCommand("50");
						button_2.setActionCommand("100");
						button_3.setActionCommand("200");
						button_4.setActionCommand("300");
						button_5.setActionCommand("400");
						button_6.setActionCommand("500");
						button_7.setActionCommand("1000");
						button_8.setActionCommand("Otra_cantidad");
						button_cancelar.setActionCommand("Cancelar1");
					}
				}break;
				case "Pago_servicios":{
					if (banco.getCliente(pos).getNumCuentas() >= 1){
						label_9.setText("SELECCIONA UN TIPO DE CUENTA");
						label_1.setText("Ahorros");
						label_8.setText("Aeptar");
						button_8.setActionCommand("Pago_servicios1");
						button_1.setActionCommand("ahorros");
						label_3.setText(" ");
						label_4.setText(" ");
						label_5.setText(" ");
						label_6.setText(" ");
						label_7.setText(" ");
						button_3.setActionCommand("B3");
						button_4.setActionCommand("B4");
						button_5.setActionCommand("B5");
						button_6.setActionCommand("B6");
						button_7.setActionCommand("B7");
						if(banco.getCliente(pos).getNumCuentas() > 1){
							label_2.setText("Cheques");
							button_2.setActionCommand("cheques");
						}
						else{
							label_2.setText(" ");
							button_2.setActionCommand("B2");
						}
						pack();
					}
					else
						JOptionPane.showMessageDialog(null, "No tienes cuentas.", "Error", JOptionPane.ERROR_MESSAGE);
				}break;
				case "Pago_servicios1":{
					if(tipo == -1)
						JOptionPane.showMessageDialog(null, "Elige una cuenta.", "Error", JOptionPane.ERROR_MESSAGE);
					else{
						label_9.setText("PAGO DE SERVICIOS");
						label_1.setText(" ");
						label_2.setText("Luz");
						label_3.setText("Agua");
						label_4.setText("Teléfono");
						label_5.setText(" ");
						label_6.setText("Celular");
						label_7.setText("Predial");
						label_8.setText("Tenencia");	
						label_10.setText(banco.getCliente(pos).getNombre() + " " + banco.getCliente(pos).getApellidoPaterno());
						pack();
						button_1.setActionCommand("B1");
						button_2.setActionCommand("Luz");
						button_3.setActionCommand("Agua");
						button_4.setActionCommand("Telefono");
						button_5.setActionCommand("B5");
						button_6.setActionCommand("Celular");
						button_7.setActionCommand("Predial");
						button_8.setActionCommand("Tenencia");
						button_cancelar.setActionCommand("Cancelar1");
					}
				}break;
				case "Abono_cuenta":{
					if (banco.getCliente(pos).getNumCuentas() >= 1){
						label_9.setText("SELECCIONA UN TIPO DE CUENTA");
						label_1.setText("Ahorros");
						label_8.setText("Aeptar");
						button_8.setActionCommand("Abono_cuenta1");
						button_1.setActionCommand("ahorros");
						label_3.setText(" ");
						label_4.setText(" ");
						label_5.setText(" ");
						label_6.setText(" ");
						label_7.setText(" ");
						button_3.setActionCommand("B3");
						button_4.setActionCommand("B4");
						button_5.setActionCommand("B5");
						button_6.setActionCommand("B6");
						button_7.setActionCommand("B7");
						if(banco.getCliente(pos).getNumCuentas() > 1){
							label_2.setText("Cheques");
							button_2.setActionCommand("cheques");
						}
						else{
							label_2.setText(" ");
							button_2.setActionCommand("B2");
						}
						pack();
					}
					else
						JOptionPane.showMessageDialog(null, "No tienes cuentas.", "Error", JOptionPane.ERROR_MESSAGE);
				}break;
				case "Abono_cuenta1":{
					if(tipo == -1)
						JOptionPane.showMessageDialog(null, "Elige una cuenta.", "Error", JOptionPane.ERROR_MESSAGE);
					else{
						label_9.setText("ABONO");
						label_1.setText("$50.00");
						label_2.setText("$100.00");
						label_3.setText("$200.00");
						label_4.setText("$300.00");
						label_5.setText("$400.00");
						label_6.setText("$500.00");
						label_7.setText("$1000.00");
						label_8.setText("Otra cantidad");	
						label_10.setText(banco.getCliente(pos).getNombre() + " " + banco.getCliente(pos).getApellidoPaterno());
						pack();
						button_1.setActionCommand("50");
						button_2.setActionCommand("100");
						button_3.setActionCommand("200");
						button_4.setActionCommand("300");
						button_5.setActionCommand("400");
						button_6.setActionCommand("500");
						button_7.setActionCommand("1000");
						button_8.setActionCommand("Otra_cantidad");
						button_cancelar.setActionCommand("Cancelar1");
					}
				}break;
				case "50":{
					if(label_9.getText().equals("ABONO")){
						if(banco.getCliente(pos).getCuenta(tipo).depositarCuenta(50)==true){
							JOptionPane.showMessageDialog(null, "Deposito de $50 realizado con exito", "Exito", JOptionPane.PLAIN_MESSAGE);
							banco.getCliente(pos).setMovimiento("Deposito en la cuenta " + (tipo + 1) + " : $50");
						}
					}
					else{
						cajero_automatico.retiroCuenta(50,tipo,pos);
					}
				}break;
				case "100":{
					if(label_9.getText().equals("ABONO")){
						if(banco.getCliente(pos).getCuenta(tipo).depositarCuenta(100)==true){
							JOptionPane.showMessageDialog(null, "Deposito de $100 realizado con exito.", "Exito", JOptionPane.PLAIN_MESSAGE);
							banco.getCliente(pos).setMovimiento("Deposito en la cuenta " + (tipo + 1) + " : $100");
						}
					}
					else{
						cajero_automatico.retiroCuenta(100,tipo,pos);
					}
				}break;
				case "200":{
					if(label_9.getText().equals("ABONO")){
						if(banco.getCliente(pos).getCuenta(tipo).depositarCuenta(200)==true){
							JOptionPane.showMessageDialog(null, "Deposito de $200 realizado con exito.", "Exito", JOptionPane.PLAIN_MESSAGE);
							banco.getCliente(pos).setMovimiento("Deposito en la cuenta " + (tipo + 1) + " : $200");
						}
					}
					else{
						cajero_automatico.retiroCuenta(200,tipo,pos);
					}
				}break;
				case "300":{
					if(label_9.getText().equals("ABONO")){
						if(banco.getCliente(pos).getCuenta(tipo).depositarCuenta(300)==true){
							JOptionPane.showMessageDialog(null, "Deposito de $300 realizado con exito.", "Exito", JOptionPane.PLAIN_MESSAGE);
							banco.getCliente(pos).setMovimiento("Deposito en la cuenta " + (tipo + 1) + " : $300");
						}
					}
					else{
						cajero_automatico.retiroCuenta(300,tipo,pos);
					}
				}break;
				case "400":{
					if(label_9.getText().equals("ABONO")){
						if(banco.getCliente(pos).getCuenta(tipo).depositarCuenta(400)==true){
							JOptionPane.showMessageDialog(null, "Deposito de $400 realizado con exito.", "Exito", JOptionPane.PLAIN_MESSAGE);
							banco.getCliente(pos).setMovimiento("Deposito en la cuenta " + (tipo + 1) + " : $400");
						}
					}
					else{
						cajero_automatico.retiroCuenta(400,tipo,pos);
					}
				}break;
				case "500":{
					if(label_9.getText().equals("ABONO")){
						if(banco.getCliente(pos).getCuenta(tipo).depositarCuenta(500)==true){
							JOptionPane.showMessageDialog(null, "Deposito de $500 realizado con exito.", "Exito", JOptionPane.PLAIN_MESSAGE);
							banco.getCliente(pos).setMovimiento("Deposito en la cuenta " + (tipo + 1) + " : $500");
						}
					}
					else{
						cajero_automatico.retiroCuenta(500,tipo,pos);
					}
				}break;
				case "1000":{
					if(label_9.getText().equals("ABONO")){
						if(banco.getCliente(pos).getCuenta(tipo).depositarCuenta(1000)==true){
							JOptionPane.showMessageDialog(null, "Deposito de $1000 realizado con exito.", "Exito", JOptionPane.PLAIN_MESSAGE);
							banco.getCliente(pos).setMovimiento("Deposito en la cuenta " + (tipo + 1) + " : $1000");
						}
					}
					else{
						cajero_automatico.retiroCuenta(1000,tipo,pos);
					}
				}break;
				case "Otra_cantidad":{
					label_1.setText(" ");
					label_2.setText(" ");
					label_3.setText(" ");
					label_4.setText(" ");
					label_5.setText(" ");
					label_6.setText(" ");
					label_7.setText(" ");
					label_8.setText("Aceptar");
					button_1.setActionCommand("B1");
					button_2.setActionCommand("B2");
					button_3.setActionCommand("B3");
					button_4.setActionCommand("B4");
					button_5.setActionCommand("B5");
					button_6.setActionCommand("B6");
					button_7.setActionCommand("B7");	
					button_8.setActionCommand("Otra_cantidad1");			
					data = "";
					password = false;
					teclado_activo = true;
				}break;
				case "Otra_cantidad1":{
					if (label_1.getText().length() > 2){
						if(label_9.getText().equals("ABONO")){
							if(banco.getCliente(pos).getCuenta(tipo).depositarCuenta(Double.parseDouble(data))==true){
								JOptionPane.showMessageDialog(null, "Deposito de $" + data + " realizado con exito.", "Exito", JOptionPane.PLAIN_MESSAGE);
								banco.getCliente(pos).setMovimiento("Deposito en la cuenta " + (tipo + 1) + " : $" + data);
							}
						}
						else{
							cajero_automatico.retiroCuenta(Double.parseDouble(data),tipo,pos);
						}
						label_9.setText("ATM");
						label_1.setText("Cambio de NIP");
						label_2.setText("Consulta de saldo");
						label_3.setText("Abono de cuenta");
						label_4.setText("Retiro de cuenta");
						label_5.setText("Pago de servicios");
						label_6.setText("Consulta de información");
						label_7.setText("Consulta de movimientos");
						label_8.setText("Regresar");	
						label_10.setText(banco.getCliente(pos).getNombre() + " " + banco.getCliente(pos).getApellidoPaterno()); 
						pack();
						button_1.setActionCommand("Cambio_NIP");
						button_2.setActionCommand("Consulta_saldo");
						button_3.setActionCommand("Abono_cuenta");
						button_4.setActionCommand("Retiro_cuenta");
						button_5.setActionCommand("Pago_servicios");
						button_6.setActionCommand("Consulta_info");
						button_7.setActionCommand("Consulta_mov");
						button_8.setActionCommand("Regresar_principal");
						data = "";
						password = false;
						teclado_activo = false;
					}
					else{
						JOptionPane.showMessageDialog(null, "Ingresa una cantidad.", "Error", JOptionPane.ERROR_MESSAGE);
						button_8.setActionCommand("Otra_cantidad1");	
					}
				}break;
				case "Luz":{
					cajero_automatico.pagarServicio(500, "la luz.", pos, tipo);
				}break;
				case "Agua":{
					cajero_automatico.pagarServicio(200, "el agua.", pos, tipo);
				}break;
				case "Telefono":{
					cajero_automatico.pagarServicio(50, "el teléfono.", pos, tipo);
				}break;
				case "Celular":{
					cajero_automatico.pagarServicio(600, "el celular.", pos, tipo);
				}break;
				case "Predial":{
					cajero_automatico.pagarServicio(700, "el predio.", pos, tipo);
				}break;
				case "Tenencia":{
					cajero_automatico.pagarServicio(400, "la tenencia.", pos, tipo);
				}break;
				case "Consulta_info":{
					consulta_info.presentFrame(banco.getCliente(pos));
				}break;
				case "Consulta_saldo":{
					cajero_automatico.consultaSaldo(pos);
				}break;
				case "Consulta_mov":{
					cajero_automatico.guardaMovimientos(pos);
					try{
						String nombreArchivo = banco.getCliente(pos).getNombre() + banco.getCliente(pos).getApellidoPaterno() + banco.getCliente(pos).getApellidoMaterno() + "movimientos.txt";
						String textEditor;
						if (System.getProperty("os.name").startsWith("Windows"))
							textEditor = "notepad.exe";
						else
							textEditor = "pluma";
						ProcessBuilder pb = new ProcessBuilder(textEditor, nombreArchivo);
						pb.start();
					}catch(Exception w){
						JOptionPane.showMessageDialog(null, w.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}break;
				case "Cambio_NIP":{
					label_9.setText("Ingrese su NIP antiguo: ");
					data = "";
					label_1.setText(" ");
					label_2.setText(" ");
					label_3.setText(" ");
					label_4.setText(" ");
					label_5.setText(" ");
					label_6.setText(" ");
					label_7.setText(" ");
					button_1.setActionCommand("B1");
					button_2.setActionCommand("B2");
					button_3.setActionCommand("B3");
					button_4.setActionCommand("B4");
					button_5.setActionCommand("B5");
					button_6.setActionCommand("B6");
					button_7.setActionCommand("B7");
					password = true;
					teclado_activo = true;
					label_8.setText("Aceptar");
					button_8.setActionCommand("Cambio_NIP1");
				}break;
				case"Cambio_NIP1":{
					if (label_1.getText().length() > 2){
						 try{
							boolean NIPReconocido = false;	
							for(int i = 0; i < banco.getUltimoElemento(); i++){
								if(data.equals(banco.getCliente(i).getNIP())){
									pos = i;
									NIPReconocido = true;
								}
							}
							if(!NIPReconocido)
								JOptionPane.showMessageDialog(null, "No se encontro el NIP.", "Error", JOptionPane.ERROR_MESSAGE);
							else{
								data = "";
								label_1.setText(" ");
								button_8.setActionCommand("Cambio_NIP2");
								label_9.setText("Ingrese nuevo NIP");
							}
						 }
						 catch (Exception k){
							JOptionPane.showMessageDialog(null, k.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			 			 }
					}
					else{
						JOptionPane.showMessageDialog(null, "Ingresa tu NIP", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}break;
				case "Cambio_NIP2":{
					if (label_1.getText().length() > 2){
						banco.getCliente(pos).setNIP(data);
						JOptionPane.showMessageDialog(null, "Tu NIP se cambio con exito.", "Exito", JOptionPane.PLAIN_MESSAGE);
						label_9.setText("BIENVENIDO");
						label_1.setText("Administración de clientes");
						label_2.setText("ATM");
						label_3.setText(" ");
						label_4.setText(" ");
						label_5.setText(" ");
						label_6.setText(" ");
						label_7.setText(" ");
						label_8.setText(" ");
						label_10.setText(" ");
						button_1.setActionCommand("Admn_clientes");
						button_2.setActionCommand("ATM");
						button_3.setActionCommand("B3");
						button_4.setActionCommand("B4");
						button_5.setActionCommand("B5");
						button_6.setActionCommand("B6");
						button_7.setActionCommand("B7");
						button_8.setActionCommand("B8");
						data = "";
						password = false;
						teclado_activo = false;
						tipo = -1;
					}
					else
						JOptionPane.showMessageDialog(null, "Ingresa tu NIP", "Error", JOptionPane.ERROR_MESSAGE);
				}break;
			}
		}
	}

	public class TecladoHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			switch(e.getActionCommand()){
				case "Cancelar":{
					label_9.setText("BIENVENIDO");
					label_1.setText("Administración de clientes");
					label_2.setText("ATM");
					label_3.setText(" ");
					label_4.setText(" ");
					label_5.setText(" ");
					label_6.setText(" ");
					label_7.setText(" ");
					label_8.setText(" ");
					button_1.setActionCommand("Admn_clientes");
					button_2.setActionCommand("ATM");
					button_3.setActionCommand("B3");
					button_4.setActionCommand("B4");
					button_5.setActionCommand("B5");
					button_6.setActionCommand("B6");
					button_7.setActionCommand("B7");
					button_8.setActionCommand("B8");
					data = "";
					password = false;
					teclado_activo = false;
					tipo = -1;
				}break;
				case "Cancelar1":{
					label_9.setText("ATM");
					label_1.setText("Cambio de NIP");
					label_2.setText("Consulta de saldo");
					label_3.setText("Abono de cuenta");
					label_4.setText("Retiro de cuenta");
					label_5.setText("Pago de servicios");
					label_6.setText("Consulta de información");
					label_7.setText("Consulta de movimientos");
					label_8.setText("Regresar");	
					label_10.setText(banco.getCliente(pos).getNombre() + " " + banco.getCliente(pos).getApellidoPaterno());
					pack();
					button_1.setActionCommand("Cambio_NIP");
					button_2.setActionCommand("Consulta_saldo");
					button_3.setActionCommand("Abono_cuenta");
					button_4.setActionCommand("Retiro_cuenta");
					button_5.setActionCommand("Pago_servicios");
					button_6.setActionCommand("Consulta_info");
					button_7.setActionCommand("Consulta_mov");
					button_8.setActionCommand("Regresar_principal");
					button_cancelar.setActionCommand("Cancelar");
					data = "";
					password = false;
					teclado_activo = false;
					tipo = -1;
				}break;
				case "N1":{
					if (teclado_activo){
						data += "1";
						if (password)
						label_1.setText(label_1.getText() + "*");
						else
							label_1.setText(label_1.getText() + "1");
					}
				}break;
				case "N2":{
					if (teclado_activo){
						data += "2";
						if (password)
							label_1.setText(label_1.getText() + "*");
						else
							label_1.setText(label_1.getText() + "2");
					}
				}break;
				case "N3":{
					if (teclado_activo){
						data += "3";
						if (password)
							label_1.setText(label_1.getText() + "*");
						else
							label_1.setText(label_1.getText() + "3");
					}
				}break;
				case "N4":{
					if (teclado_activo){
						data += "4";
						if (password)
							label_1.setText(label_1.getText() + "*");
						else
							label_1.setText(label_1.getText() + "4");
					}
				}break;
				case "N5":{
					if (teclado_activo){
						data += "5";
						if (password)
							label_1.setText(label_1.getText() + "*");
						else
							label_1.setText(label_1.getText() + "5");
					}
				}break;
				case "N6":{
					if (teclado_activo){
						data += "6";
						if (password)
							label_1.setText(label_1.getText() + "*");
						else
							label_1.setText(label_1.getText() + "6");
					}
				}break;
				case "N7":{
					if (teclado_activo){
						data += "7";
						if (password)
							label_1.setText(label_1.getText() + "*");
						else
							label_1.setText(label_1.getText() + "7");
					}
				}break;
				case "N8":{
					if (teclado_activo){
						data += "8";
						if (password)
							label_1.setText(label_1.getText() + "*");
						else
							label_1.setText(label_1.getText() + "8");
					}
				}break;
				case "N9":{
					if (teclado_activo){
						data += "9";
						if (password)
							label_1.setText(label_1.getText() + "*");
						else
							label_1.setText(label_1.getText() + "9");
					}
				}break;
				case "N0":{
					if (teclado_activo){
						data += "0";
						if (password)
							label_1.setText(label_1.getText() + "*");
						else
							label_1.setText(label_1.getText() + "0");
					}
				}break;
				case "N00":{
					if (teclado_activo){
						data += "00";
						if (password)
							label_1.setText(label_1.getText() + "**");
						else
							label_1.setText(label_1.getText() + "00");
					}
				}break;
				case "N000":{
					if (teclado_activo){
						data += "000";
						if (password)
							label_1.setText(label_1.getText() + "***");
						else
							label_1.setText(label_1.getText() + "000");
					}
				}break;
				case "Corregir":{
					if (teclado_activo){
						data = data.substring(0, data.length() - 1);
						if (password)
							label_1.setText(label_1.getText().substring(0, label_1.getText().length()-1));
						else
							label_1.setText(label_1.getText().substring(0, label_1.getText().length()-1));
					}
				}break;
			}
		}
	}

	public void presentFrame(){
		setSize(500,600);

		panel_num.setLayout(new GridLayout(4,3));
		panel_num.add(num_1);
		panel_num.add(num_2);
		panel_num.add(num_3);
		panel_num.add(num_4);
		panel_num.add(num_5);
		panel_num.add(num_6);
		panel_num.add(num_7);
		panel_num.add(num_8);
		panel_num.add(num_9);
		panel_num.add(num_0);
		panel_num.add(num_00);
		panel_num.add(num_000);

		panel_CoCa.setLayout(new GridLayout(2,1));
		panel_CoCa.add(button_corregir);
		panel_CoCa.add(button_cancelar);
		
		panel_teclado.setLayout(new GridLayout(1,2));
		panel_teclado.add(panel_num);
		panel_teclado.add(panel_CoCa);

		panel_west.setLayout(new GridLayout(4,1));
		panel_west.add(button_1);
		panel_west.add(button_2);
		panel_west.add(button_3);
		panel_west.add(button_4);

		panel_east.setLayout(new GridLayout(4,1));
		panel_east.add(button_5);
		panel_east.add(button_6);
		panel_east.add(button_7);
		panel_east.add(button_8);

		grid.gridx = 1;
		grid.gridy = 0;
		panel_center.add(label_10,grid);
		grid.gridx = 0;
		grid.gridy = 1;
		panel_center.add(label_9,grid);
		grid.gridy = 3;
		panel_center.add(label_1,grid);
		grid.gridy = 4;
		panel_center.add(label_2,grid);
		grid.gridy = 5;
		panel_center.add(label_3,grid);
		grid.gridy = 6;
		panel_center.add(label_4,grid);

		grid.gridx = 1;
		grid.gridy = 3;
		panel_center.add(label_5,grid);
		grid.gridy = 4;
		panel_center.add(label_6,grid);
		grid.gridy = 5;
		panel_center.add(label_7,grid);
		grid.gridy = 6;
		panel_center.add(label_8,grid);

		panel_teclado.setPreferredSize(new Dimension(300, 250));
		panel_teclado.setBorder(new EmptyBorder(0,50,0,50));
		panel_west.setPreferredSize(new Dimension(50, 20));
		panel_west.setBorder(new EmptyBorder(150,0,0,0));
		panel_east.setPreferredSize(new Dimension(50, 20));
		panel_east.setBorder(new EmptyBorder(150,0,0,0));
		panel_center.setBorder(new EmptyBorder(95,0,0,0));

		add(BorderLayout.SOUTH,panel_teclado);
		add(BorderLayout.WEST,panel_west);
		add(BorderLayout.EAST,panel_east);
		add(BorderLayout.CENTER,panel_center);

		button_1.addActionListener(handler);
		button_2.addActionListener(handler);
		button_3.addActionListener(handler);
		button_4.addActionListener(handler);
		button_5.addActionListener(handler);
		button_6.addActionListener(handler);
		button_7.addActionListener(handler);
		button_8.addActionListener(handler);
		
		button_cancelar.addActionListener(handler_teclado);
		button_corregir.addActionListener(handler_teclado);
		num_1.addActionListener(handler_teclado);
		num_2.addActionListener(handler_teclado);
		num_3.addActionListener(handler_teclado);
		num_4.addActionListener(handler_teclado);
		num_5.addActionListener(handler_teclado);
		num_6.addActionListener(handler_teclado);
		num_7.addActionListener(handler_teclado);
		num_8.addActionListener(handler_teclado);
		num_9.addActionListener(handler_teclado);
		num_0.addActionListener(handler_teclado);
		num_00.addActionListener(handler_teclado);
		num_000.addActionListener(handler_teclado);

		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){	
				try{
					FileOutputStream archivoSalida = new FileOutputStream("clientes.ser");
					ObjectOutputStream salida = new ObjectOutputStream(archivoSalida);
					salida.writeObject(banco.getBanco());
					salida.close();
					System.exit(0);
				}catch(IOException o){
					System.err.println("No se pudo guardar el archivo");
				}
			}
		});
		setVisible(true);
		
	}
}
