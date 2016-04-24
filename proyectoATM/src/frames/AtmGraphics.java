package frames;
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
	private FormatoCliente formato_cliente;
	private CuentaNueva cuenta_nueva;
	private Banco banco = new Banco();
	
	
	public AtmGraphics(){
		super("ATM");
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

		formato_cliente = new FormatoCliente();
		cuenta_nueva = new CuentaNueva();

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
					label_1.setText("Cambio de NIP");
					label_2.setText("Consulta de saldo");
					label_3.setText("Abono de cuenta");
					label_4.setText("Retiro de cuenta");
					label_5.setText("Pago de servicios");
					label_6.setText("Consulta de información");
					label_7.setText("Consulta de movimientos");
					label_8.setText("Regresar");	
					label_10.setText("NAME"); //LIGAR CON NOMBRE EN ARCHIVO
					pack();
					button_1.setActionCommand("Cambio_NIP");
					button_2.setActionCommand("Consulta_saldo");
					button_3.setActionCommand("Abono_cuenta");
					button_4.setActionCommand("Retiro_cuenta");
					button_5.setActionCommand("Pago_servicios");
					button_6.setActionCommand("Consulta_info");
					button_7.setActionCommand("Consulta_mov");
					button_8.setActionCommand("Regresar_principal");
				}break;
				case "Regresar_principal":{
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
				}break;
				case "Alta_cliente":{
					formato_cliente.presentFrame(banco);
				}break;
				case "Alta_cuenta":{
					cuenta_nueva.presentarFrame(banco);
				}break;
				case "Retiro_cuenta":{
					label_9.setText("RETIRO");
					label_1.setText("$50.00");
					label_2.setText("$100.00");
					label_3.setText("$200.00");
					label_4.setText("$300.00");
					label_5.setText("$400.00");
					label_6.setText("$500.00");
					label_7.setText("$1000.00");
					label_8.setText("Otra cantidad");	
					label_10.setText("NAME"); //LIGAR CON NOMBRE EN ARCHIVO
					pack();
					button_1.setActionCommand("50");
					button_2.setActionCommand("100");
					button_3.setActionCommand("200");
					button_4.setActionCommand("300");
					button_5.setActionCommand("400");
					button_6.setActionCommand("500");
					button_7.setActionCommand("1000");
					button_8.setActionCommand("Otra_cantidad");
				}break;
				case "Pago_servicios":{
					label_9.setText("PAGO DE SERVICIOS");
					label_1.setText(" ");
					label_2.setText("Luz");
					label_3.setText("Agua");
					label_4.setText("Teléfono");
					label_5.setText(" ");
					label_6.setText("Celular");
					label_7.setText("Predial");
					label_8.setText("Tenencia");	
					label_10.setText("NAME"); //LIGAR CON NOMBRE EN ARCHIVO
					pack();
					button_1.setActionCommand("B1");
					button_2.setActionCommand("Luz");
					button_3.setActionCommand("Agua");
					button_4.setActionCommand("Telefono");
					button_5.setActionCommand("B5");
					button_6.setActionCommand("Celular");
					button_7.setActionCommand("Predial");
					button_8.setActionCommand("Tenencia");
				}break;
				case "50":{

				}break;
				case "100":{

				}break;
				case "200":{

				}break;
				case "300":{

				}break;
				case "400":{

				}break;
				case "500":{

				}break;
				case "1000":{

				}break;
				case "Otra_cantidad":{

				}break;
				case "Luz":{

				}break;
				case "Agua":{

				}break;
				case "Telefono":{

				}break;
				case "Celular":{

				}break;
				case "Predial":{

				}break;
				case "Tenencia":{

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
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
}
