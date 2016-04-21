import javax.swing.JOptionPane;
import javax.swing.*;
public class test2{
 public static void main(String args[]){
 String NIP;
 String entrada;
 String outputStr;
 MiFrame f = new MiFrame();
 try{
	entrada = JOptionPane.showInputDialog("Inserte su NIP: ");
	NIP = entrada;

	if (!NIP.equals("1234"))
		JOptionPane.showMessageDialog(null, "No se encontro el NIP.", "Error", JOptionPane.ERROR_MESSAGE);
	else
		f.presentarFrame();
 }
 catch (Exception e){
	JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
  }
 }
}