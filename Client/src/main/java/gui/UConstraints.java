package gui;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class UConstraints extends GridBagConstraints{
	private static final long serialVersionUID = -1256092282934777496L;
	
	public UConstraints(int gridx, int gridy, int gridheight, int gridwidth){
		super();
		this.gridx = gridx;
		this.gridy = gridy;
		this.gridheight = gridheight;
		this.gridwidth = gridwidth;
		this.insets = new Insets(4, 10, 4, 10);
		this.fill = GridBagConstraints.HORIZONTAL;
	}
	public UConstraints(int gridx, int gridy, int gridheight, int gridwidth, int fill){
		super();
		this.gridx = gridx;
		this.gridy = gridy;
		this.gridheight = gridheight;
		this.gridwidth = gridwidth;
		this.fill = fill;
		this.insets = new Insets(4, 10, 4, 10);
	}
	public UConstraints(int gridx, int gridy){
		super();
		this.gridx = gridx;
		this.gridy = gridy;
		this.insets = new Insets(4, 10, 4, 10);
		this.fill = GridBagConstraints.HORIZONTAL;
	}
	public UConstraints(int gridx, int gridy, int fill){
		super();
		this.gridx = gridx;
		this.gridy = gridy;
		this.fill = fill;
		this.insets = new Insets(4, 10, 4, 10);
	}
	
	/**
	 * Alle Werte werden wieder auf Null gesetzt.
	 */
	public void reset() {
		this.gridx = 0;
		this.gridy = 0;
		this.gridheight = 0;
		this.gridwidth = 0;
	}
	
	public void setContraints(int gridx, int gridy){
		this.gridx = gridx;
		this.gridy = gridy;
	}
	
}
