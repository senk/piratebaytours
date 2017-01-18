package adminMVC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.Spliterator;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


import datenbank.Select;
import gui.GlobaleVariablen;
import gui.UFrame;
import valueObjecte.Routen;

public class AdminController implements Observer, ActionListener, ListSelectionListener {

	private AdminModel adminModel;
	private AdminView adminView;

	public AdminController(AdminModel adminModel, AdminView adminView) {
		this.adminModel = adminModel;
		this.adminView = adminView;
		adminModel.addObserver(this);
		
		// ActionListener
		adminView.getButtonAbbrechenPlaetze().addActionListener(this);
		adminView.getButtonAbbrechenTour().addActionListener(this);
		adminView.getButtonErstellenTour().addActionListener(this);
		adminView.getButtonZuweisenPlaetze().addActionListener(this);
		
		
		
		adminView.getTextFieldSchiffAenern().addActionListener(this);
		adminView.getTextFieldRouteAendern().addActionListener(this);
		adminView.getTextFieldDatumAendern().addActionListener(this);
		adminView.getTextFieldZeitAendern().addActionListener(this);
		
		adminView.getJComboBoxEingabeUhrzeit().addActionListener(this);
		adminView.getjComboBoxMitarbeiter().addActionListener(this);
		
		adminView.getDatePicker().addActionListener(this);
		
		// SelectionListener
		adminView.getListRoute().addListSelectionListener(this);
		adminView.getListSchiffe().addListSelectionListener(this);
		adminView.getListMitarbeiterBestaetigt().addListSelectionListener(this);
		adminView.getListMitarbeiterNichtBestaetigt().addListSelectionListener(this);
		
		// Routen Informationen im Model speichern
		String[] routenName = new Select().selectRoutenNameFromRoute();
		adminModel.getRoutenVO().setRouten_name(routenName);
		adminModel.getRoutenVO().setRouten_id(new Select().selectRoutenIdFromRoute());
		adminModel.getRoutenVO().setRouten_dauer(new Select().selectRoutenDauerFromRoute());
		
		// Schiff Informationen im Model speichern
		String[] schiffName = new Select().selectSchiffNameFromSchiff();
		adminModel.getSchiffVO().setSchiff_name(schiffName);
		
		// Routen/Schiff Namen auf der Oberfläche ausgaben
		adminView.getListRoute().setListData(routenName);
		adminView.getListSchiffe().setListData(schiffName);
		
		// Tabelle Touren Übersicht
		String[] spaltenNamen = {"Routen", "Schiff", "Datum", "Startzeit", "freie Plätze"};		
		adminView.getTableVorhandeneTouren().setData(spaltenNamen, new Select().selectAllesFromTour());
		
		// Voreinstellungen
		adminView.getButtonErstellenTour().setEnabled(false);
		adminView.getButtonZuweisenPlaetze().setEnabled(false);
		
		// Inhalt der JComboBoxen
		String[] jComboBoxUhrzeit = {"Uhrzeit", "10:30","12:30", "15:30", "16:00"};
		ComboBoxModel<String> modelJComboBoxUhrzeit = new DefaultComboBoxModel<String>(jComboBoxUhrzeit);
		adminView.getJComboBoxEingabeUhrzeit().setModel(modelJComboBoxUhrzeit);
		
		// Sichtbarkeit
		adminView.setVisible(true);
		
	}

	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case GlobaleVariablen.EVENT_ENTERUHRZEIT:
			// Wenn ein Uhrzeit Selectiert wurde, muss abgefragt werden, 
			// ob alle Bedingungen erfüllt sind (Route, Schiff, Datum, Zeit)
			handleEventAnklickbarButtonErstellenTour();
			break;
		case GlobaleVariablen.EVENT_BUTTONERSTELLENTOUR:
			handleEventButtonErstellenTour();
			break;
		case GlobaleVariablen.EVENT_BUTTONABBRECHENTOUR:
			handleEventButtonErstellenTourAbbrechen();
		}
		

		if(e.getSource() == adminView.getDatePanel()) {
			handleEventAnklickbarButtonErstellenTour();
		} else if(e.getSource() == adminView.getTablePlaetzeProMitarbeiter().getSelectionModel()
				&& adminView.getTablePlaetzeProMitarbeiter().getColumnSelectionAllowed()) {
			handleSelectionEventTablePlaetzeProMitarbeiter();
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg0 == this.adminModel) {
			
		}

	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(e.getSource() == adminView.getTablePlaetzeProMitarbeiter().getSelectionModel()
				&& adminView.getTablePlaetzeProMitarbeiter().getColumnSelectionAllowed()) {
			handleSelectionEventTablePlaetzeProMitarbeiter();
		} else if(e.getSource() == adminView.getTableVorhandeneTouren().getSelectionModel()
				&& adminView.getTableVorhandeneTouren().getColumnSelectionAllowed()) {
			handleSelectionEventTableVorhandeneTouren();
		} else if(e.getSource() == adminView.getListRoute()) {
			// Wenn ein Uhrzeit Selectiert wurde, muss abgefragt werden,
			// ob alle Bedingungen erfüllt sind (Route, Schiff, Datum, Zeit)
			handleEventAnklickbarButtonErstellenTour();
		} else if(e.getSource() == adminView.getListSchiffe()) {
			// Wenn ein Uhrzeit Selectiert wurde, muss abgefragt werden, 
			// ob alle Bedingungen erfüllt sind (Route, Schiff, Datum, Zeit)
			handleEventAnklickbarButtonErstellenTour();
		} 
	}

	private void handleSelectionEventTableVorhandeneTouren() {
		// TODO
	}

	private void handleSelectionEventTablePlaetzeProMitarbeiter() {
		// TODO
	}
	/**
	 * Der Button darf nur gedrückt werden, wenn eine Zeile 
	 * in Schiffe und Route selektiert ist sowie ein Datum ausgewühlt und eine Zeitausgesucht ist.
	 */
	private void handleEventAnklickbarButtonErstellenTour() {
		if(!adminView.getListRoute().isSelectionEmpty()
				&& !adminView.getListSchiffe().isSelectionEmpty() 
				&& adminView.getDatePicker().getModel().getValue() != null
				&& !adminView.getJComboBoxEingabeUhrzeit().getSelectedItem().equals("Uhrzeit")) {
			// Button anklickbar
			adminView.getButtonErstellenTour().setEnabled(true);
		}
	}
	/**
	 * Event wenn der Button gedrückt wird.
	 */
	private void handleEventButtonErstellenTour() {
		// Variablen
		ArrayList<Integer> schiffeInTour = new Select().selectSchiffIdFromTour();
		int schiff_ID = new Select().selectSchiffIDFromSchiff(adminView.getListSchiffe().getSelectedValue());
		Date tour_datum = (Date) adminView.getDatePicker().getModel().getValue();
//		String tour_startzeit = (String) adminView.getJComboBoxEingabeUhrzeit().getSelectedItem(); // TODO Datentyp
		Timestamp route_dauer = new Select().selectRoutenDauer();  // TODO Datentyp
		
		// Date umwandeln in String
		SimpleDateFormat sdfDatum = new SimpleDateFormat("yyyy-MM-d");
		SimpleDateFormat sdfZeit = new SimpleDateFormat("H:m");
		
		// Ist das Schiff in der Liste Tour.Schiffe enthalten?
		if(schiffeInTour.contains(schiff_ID)) {
			// Ist das Datum ebenfalls schon vorhanden?
			ArrayList<String> datumInTour = new Select().selectTourDatumFromTour(schiff_ID);
			if(datumInTour.contains(sdfDatum.format(tour_datum))) {
				// Fehlermeldung
				new UFrame().fehlermeldung();
				
			} else {
				// Insert in TOUR TODO
			}
		} else {
			// Insert in TOUR TODO
		}
	}

	
	/**
	 * Deselektiert Route und Schiffe
	 * Datum = Datum
	 * Zeit = Zeit
	 */
	private void handleEventButtonErstellenTourAbbrechen() {
		new UFrame().fehlermeldung();

		
		new Select().selectRouteStartzeit(0, "2016-12-12");
		
		// Datum  = null
		adminView.getDatePicker().getModel().setValue(null);
		
		// Zeit gleich Zeit:
		adminView.getJComboBoxEingabeUhrzeit().setSelectedIndex(0);
		
		// Deseletieren von Route und Schiffe
		adminView.getListRoute().clearSelection();
		adminView.getListSchiffe().clearSelection();

		// Button nicht mehr anklickbar
		adminView.getButtonErstellenTour().setEnabled(false);
		
		handleSelectionEventTablePlaetzeProMitarbeiter();
	}
	
	
	
	
	
	
	
	
	
}
