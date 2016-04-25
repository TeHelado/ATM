import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ConsultaInfo extends JFrame{
	
	private JLabel label_Name, label_App, label_Apm, label_Dn, label_Mn, label_An, label_Calle, label_Num, label_Col, label_Del, label_Cd, label_Pais, label_Edad, label_clave, label_RFC, label_cuentas;
	private JPanel panel_info;
	private GridBagConstraints grid;

	public ConsultaInfo(){
		super("Información de Cliente");
		label_Name = new JLabel("Nombre");
		label_App = new JLabel("Apellido Paterno");
		label_Apm = new JLabel("Apellido Materno");
		label_Dn = new JLabel("Día de Nacimiento");
		label_Mn = new JLabel("Mes de Nacimiento");
		label_An = new JLabel("Año de Nacimiento");
		label_Calle = new JLabel("Calle");
		label_Num = new JLabel("Número");
		label_Col = new JLabel("Colonia");
		label_Del = new JLabel("Delegación");
		label_Cd = new JLabel("Ciudad");
		label_Pais = new JLabel("País");
		label_Edad = new JLabel("Edad");
		label_clave = new JLabel("Clave Cliente");
		label_RFC = new JLabel("RFC");
		label_cuentas = new JLabel("Cuentas");
		pack();

		panel_info = new JPanel(new GridBagLayout());
		grid = new GridBagConstraints();
		grid.anchor = GridBagConstraints.WEST;

		//panel_info.setLayout(new GridLayout(3,1));
		
		panel_info.add(label_Calle);
		panel_info.add(label_Num);
		panel_info.add(label_Col);
		panel_info.add(label_Del);
		panel_info.add(label_Cd);
		panel_info.add(label_Pais);

		add(panel_info);
		setSize(500,400);
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){	
				setVisible(false);
			}
		});
	}

	public void presentFrame(){

		grid.gridx = 0;
		grid.gridy = 0;
		panel_info.add(label_Name,grid);
		grid.gridx = 1;
		grid.gridy = 0;
		panel_info.add(label_App,grid);
		grid.gridx = 2;
		grid.gridy = 0;
		panel_info.add(label_Apm,grid);

		grid.gridx = 0;
		grid.gridy = 1;
		panel_info.add(label_Dn,grid);
		grid.gridx = 1;
		grid.gridy = 1;
		panel_info.add(label_Mn,grid);
		grid.gridx = 2;
		grid.gridy = 1;
		panel_info.add(label_An,grid);

		grid.gridx = 0;
		grid.gridy = 2;
		panel_info.add(label_Edad,grid);

		grid.gridx = 0;
		grid.gridy = 3;
		panel_info.add(label_Calle,grid);
		grid.gridx = 1;
		grid.gridy = 3;
		panel_info.add(label_Num,grid);
		grid.gridx = 2;
		grid.gridy = 3;
		panel_info.add(label_Col,grid);
		grid.gridx = 3;
		grid.gridy = 3;
		panel_info.add(label_Del,grid);
		grid.gridx = 4;
		grid.gridy = 3;
		panel_info.add(label_Cd,grid);
		grid.gridx = 5;
		grid.gridy = 3;
		panel_info.add(label_Pais,grid);

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
