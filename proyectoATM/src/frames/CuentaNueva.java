package frames;
import javax.swing.*;   
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import banco.*;

public class CuentaNueva extends JFrame{
	private ButtonGroup group;
	private JRadioButton ahorro, cheques;
	private JButton button;
	private JPanel panel;
	private ButtonHandler handler;
	private int pos;
	private String NIP, entrada;
	private Banco banco;
		public CuentaNueva(){
			super("Cuenta Nueva");
			ahorro = new JRadioButton("Ahorros");
			cheques = new JRadioButton("Cheques");
			group = new ButtonGroup();
			button = new JButton("Ok");
			panel = new JPanel(new FlowLayout()); 
			setSize(200,100);
			panel.setPreferredSize(new Dimension(200, 30));
			
			ahorro.setSelected(true);
			ahorro.setActionCommand("ahorro");
			cheques.setActionCommand("cheques");
			
			group.add(ahorro);
			group.add(cheques);
			
			panel.add(ahorro);
			panel.add(cheques);
			
			add(BorderLayout.NORTH, panel);
			add(BorderLayout.SOUTH, button);
			
			addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e){	
					setVisible(false);
				}
			});
			
			handler = new ButtonHandler();
			button.setActionCommand("ok");
			button.addActionListener(handler);
		}
		
		
		private class ButtonHandler implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				int tipo = 1, saldo;
				if (ahorro.isSelected())
					tipo = 1;
				else
					tipo = 2;
				
				switch(e.getActionCommand())
				{
					case "ok":
					try{
						entrada = JOptionPane.showInputDialog("Deposita un saldo inicial (minimo $50): ");
						saldo = Integer.parseInt(entrada);
						if (saldo >= 50){
							banco.addCuenta(tipo, pos, Double.parseDouble(entrada));
							setVisible(false);
						}
							
						else
							JOptionPane.showMessageDialog(null, "La cantidad minima son $50", "Error", JOptionPane.ERROR_MESSAGE);
					}
					catch(Exception k){
						JOptionPane.showMessageDialog(null, k.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
						
					break;
					default:
				}
			}
		}
		
		public void presentarFrame(Banco banco, int pos){
			this.banco = banco;
			this.pos = pos;
			setVisible(true);
		}
}
