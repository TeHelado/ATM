import javax.swing.*;   
import javax.swing.event.*;
import java.awt.*;
public class MiFrame extends JFrame{
	private ButtonGroup group;
	private JRadioButton ahorro, cheques;
	private JButton button;
	private JPanel panel;
		public MiFrame(){
			super("Cuenta Nueva");
			ahorro = new JRadioButton("Ahorros");
			cheques = new JRadioButton("Cheques");
			group = new ButtonGroup();
			button = new JButton("Ok");
			panel = new JPanel(new FlowLayout()); 
		}
		
		public void presentarFrame(){
			setSize(200,100);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			//pack();
			setVisible(true);
		}
}