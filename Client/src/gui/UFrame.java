package gui;

import java.awt.Panel;

import javax.swing.JFrame;

public class UFrame extends JFrame {
	
	
	public UFrame() {
		super();
		setVisible(true);
	}
	
	
	public void fehlermeldung() {
		setSize(400, 100);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Fehlermeldung");
		
		// Panel
		UPanel panel  = new UPanel();
		ULabel label1 = new ULabel("<html>Die gew�nsche Tour konnte nicht erstellt werden,"
				+ "<br/> weil das Schiff an diesem Datum belegt ist. </html>");
		panel.add(label1);
		
		add(panel);
	}

}
