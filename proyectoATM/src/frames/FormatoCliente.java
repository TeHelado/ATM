package frames;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JOptionPane;
import banco.*;

public class FormatoCliente extends JFrame{

	private JTextField text_Name, text_App, text_Apm, text_Dn, text_Mn, text_An, text_Calle, text_Num, text_Col, text_Del, text_Cd, text_Pais, text_NIP;
	private JLabel label_Name, label_App, label_Apm, label_Dn, label_Mn, label_An, label_Calle, label_Num, label_Col, label_Del, label_Cd, label_Pais, label_NIP;
	private JPanel panel_Format;
	private JButton aceptar;
	private ButtonHandler handler;
	
	private Banco banco;

	public FormatoCliente(){
		text_Name = new JTextField();
		text_App = new JTextField();
		text_Apm = new JTextField();
		text_Dn = new JTextField();
		text_Mn = new JTextField();
		text_An = new JTextField();
		text_Calle = new JTextField();
		text_Num = new JTextField();
		text_Col = new JTextField();
		text_Del = new JTextField();
		text_Cd = new JTextField();
		text_Pais = new JTextField();
		text_NIP = new JTextField();

		label_Name = new JLabel("Nombre:");
		label_App = new JLabel("Apellido Paterno:");
		label_Apm = new JLabel("Apellido Materno:");
		label_Dn = new JLabel("Día de Nacimiento:");
		label_Mn = new JLabel("Mes de Nacimiento:");
		label_An = new JLabel("Año de Nacimiento:");
		label_Calle = new JLabel("Calle:");
		label_Num = new JLabel("Número:");
		label_Col = new JLabel("Colonia:");
		label_Del = new JLabel("Delegación:");
		label_Cd = new JLabel("Ciudad:");
		label_Pais = new JLabel("País:");
		label_NIP = new JLabel("NIP:");

		panel_Format = new JPanel();
	
		aceptar = new JButton("Aceptar");
		aceptar.setActionCommand("Aceptar");

		handler = new ButtonHandler();
		
		panel_Format.setLayout(new GridLayout(13,13));
		panel_Format.add(label_Name);
		panel_Format.add(text_Name);
		panel_Format.add(label_App);
		panel_Format.add(text_App);
		panel_Format.add(label_Apm);
		panel_Format.add(text_Apm);
		panel_Format.add(label_Dn);
		panel_Format.add(text_Dn);
		panel_Format.add(label_Mn);
		panel_Format.add(text_Mn);
		panel_Format.add(label_An);
		panel_Format.add(text_An);
		panel_Format.add(label_Calle);
		panel_Format.add(text_Calle);
		panel_Format.add(label_Num);
		panel_Format.add(text_Num);
		panel_Format.add(label_Col);
		panel_Format.add(text_Col);
		panel_Format.add(label_Del);
		panel_Format.add(text_Del);
		panel_Format.add(label_Cd);
		panel_Format.add(text_Cd);
		panel_Format.add(label_Pais);
		panel_Format.add(text_Pais);
		panel_Format.add(label_NIP);
		panel_Format.add(text_NIP);
		add(panel_Format);
		add(BorderLayout.SOUTH, aceptar);

		aceptar.addActionListener(handler);

		setSize(500,400);

		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){	
				setVisible(false);
			}
		});
	}

	private class ButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			int band=0;
			if(e.getActionCommand().equals("Aceptar")){
				if((text_Name.getText()).equals("")){band = 1;}
				else if((text_App.getText()).equals("")){band = 1;}
				else if((text_Apm.getText()).equals("")){band = 1;}
				else if((text_Dn.getText()).equals("")){band = 1;}
				else if((text_Mn.getText()).equals("")){band = 1;}
				else if((text_An.getText()).equals("")){band = 1;}
				else if((text_Calle.getText()).equals("")){band = 1;}
				else if((text_Num.getText()).equals("")){band = 1;}
				else if((text_Col.getText()).equals("")){band = 1;}
				else if((text_Del.getText()).equals("")){band = 1;}
				else if((text_Cd.getText()).equals("")){band = 1;}
				else if((text_Pais.getText()).equals("")){band = 1;}
				else if((text_NIP.getText()).equals("")){band = 1;}

				if(band==1){
					JOptionPane.showMessageDialog(null, "Dejaste algun campo vacío.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else{
					banco.addClient(text_Name.getText(), text_App.getText(), text_Apm.getText(), Integer.parseInt(text_Dn.getText()), Integer.parseInt(text_Mn.getText()), Integer.parseInt(text_An.getText()), text_Calle.getText(), text_Col.getText(), Integer.parseInt(text_Num.getText()), text_Del.getText(), text_Cd.getText(), text_Pais.getText(), text_NIP.getText());
					
					text_Name.setText("");
					text_App.setText("");
					text_App.setText("");
					text_Apm.setText("");
					text_Dn.setText("");
					text_Mn.setText("");
					text_An.setText("");
					text_Calle.setText("");
					text_Num.setText("");
					text_Col.setText("");
					text_Del.setText("");
					text_Cd.setText("");
					text_Pais.setText("");
					text_NIP.setText("");
					
					setVisible(false);
				}
			}
		}
	}

	public void presentFrame(Banco banco){
		this.banco = banco;
		setVisible(true);
	}
}
