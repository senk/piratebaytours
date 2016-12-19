package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class UPanel extends JPanel{
	private static final long serialVersionUID = 4079921006609457364L;
	
	public UPanel() {
		super();
		setLayout(new GridBagLayout());
	}
	public void setTitledBorder(String title) {
		TitledBorder b =  new TitledBorder(title);
		b.setTitleFont(new Font("Arial", Font.LAYOUT_RIGHT_TO_LEFT, 14));
		b.setTitleColor(Color.BLACK);
		this.setBorder(b);
	}

}
