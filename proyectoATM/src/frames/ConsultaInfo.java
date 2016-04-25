package frames;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import banco.*;

public class ConsultaInfo extends JFrame{
	
	private JLabel label_Name, label_Fecha, label_Direccion, label_Edad, label_clave, label_RFC, label_cuentas;
	private JPanel panel_info;
	private GridBagConstraints grid;
	private Cliente cliente;

	public ConsultaInfo(){
		super("Información de Cliente");
		label_Name = new JLabel("Nombre");
		label_Fecha = new JLabel("Fecha de Nacimiento");
		label_Direccion = new JLabel("Calle");
		label_Edad = new JLabel("Edad");
		label_clave = new JLabel("Clave Cliente");
		label_RFC = new JLabel("RFC");
		label_cuentas = new JLabel("Cuentas");
		pack();

		panel_info = new JPanel(new GridBagLayout());
		grid = new GridBagConstraints();
		grid.anchor = GridBagConstraints.WEST;

		//panel_info.setLayout(new GridLayout(3,1));
		
		panel_info.add(label_Direccion);

		add(panel_info);
		setSize(500,400);
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){	
				setVisible(false);
			}
		});
	}

	public void presentFrame(Cliente cliente){
		this.cliente = cliente;
		
		label_Name.setText("Nombre: " + cliente.nombretoString());
		label_Fecha.setText("Fecha de nacimiento: " + cliente.getFechaDeNacimiento());
		label_Direccion.setText("Dirección: " + cliente.getDireccion());
		label_Edad.setText(String.valueOf("Edad: " + cliente.getEdad()));
		label_clave.setText("Clave de cliente: " + cliente.getClaveCliente());
		label_RFC.setText("RFC: " + cliente.getRFC());
		label_cuentas.setText("Numero de cuentas: " + String.valueOf(cliente.getNumCuentas()));

		grid.gridx = 0;
		grid.gridy = 0;
		panel_info.add(label_Name,grid);

		grid.gridx = 0;
		grid.gridy = 1;
		panel_info.add(label_Fecha,grid);

		grid.gridx = 0;
		grid.gridy = 2;
		panel_info.add(label_Edad,grid);

		grid.gridx = 0;
		grid.gridy = 3;
		panel_info.add(label_Direccion,grid);

		grid.gridx = 0;
		grid.gridy = 4;
		panel_info.add(label_clave,grid);

		grid.gridx = 0;
		grid.gridy = 5;
		panel_info.add(label_RFC,grid);
	
		grid.gridx = 0;
		grid.gridy = 6;
		panel_info.add(label_cuentas,grid);
		pack();
		setVisible(true);
	}
}
