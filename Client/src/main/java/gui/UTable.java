package gui;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import datenbank.Select;

public class UTable extends JTable {

	public UTable() {
		super();

		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	
	public void setData(String [] spaltenNamen, String[][] spaltenData) {
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.setDataVector(spaltenData, spaltenNamen);
		
		setModel(tableModel);
		
	}

}
