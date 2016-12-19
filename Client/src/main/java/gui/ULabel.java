package gui;

import java.awt.Font;

import javax.swing.JLabel;

public class ULabel extends JLabel{
	private static final long serialVersionUID = -546766632893449231L;
	
	public ULabel() {
		super();
		this.setFont(new Font("Arial", Font.ITALIC, 13));
		
	}
	public ULabel(String titel) {
		super(titel);
	}

}
