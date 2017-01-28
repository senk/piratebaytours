package buchungenMVC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import datenbank.Select;
import datenbank.Create;
import datenbank.Drop;
import datenbank.Insert;


import api_client.*;
import gui.GlobaleVariablen;

public class BuchungenController implements Observer, ActionListener, ListSelectionListener {

	private BuchungenModel buchungenModel;
	private BuchungenView buchungenView;

	public BuchungenController(BuchungenModel modelGUI, BuchungenView viewGUI) {
		buchungenModel = modelGUI;
		buchungenView = viewGUI;
		buchungenModel.addObserver(this);
		// ActionListener
		buchungenView.getTextBenoetigtePlaete().addActionListener(this); 
		buchungenView.getTextName().addActionListener(this);
		buchungenView.getTextVorname().addActionListener(this);
		buchungenView.getTextMail().addActionListener(this);
		buchungenView.getTextTelefon().addActionListener(this);
		buchungenView.getButtonBuchungBestaetigung().addActionListener(this);
		buchungenView.getButtonBuchungAbbrechen().addActionListener(this);
		buchungenView.getButtonBuchungSynchronisieren().addActionListener(this);

		
		// Selection Listener
		buchungenView.getListTour().addListSelectionListener(this); 
		buchungenView.getListDatum().addListSelectionListener(this); 
		buchungenView.getListUhrzeiten().addListSelectionListener(this); 
		
		// Voreinstellungen
		buchungenView.getButtonBuchungBestaetigung().setEnabled(false);
		
		// Sichtbarkeit
		buchungenView.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case GlobaleVariablen.EVENT_ENTER_PLAETZE:
			handleActionEventEnterPlaetze();
			break;
			case GlobaleVariablen.EVENT_ENTER_NAME:
			handleActionEventEnterName();
			break;
			case GlobaleVariablen.EVENT_ENTER_VORNAME:
			handleActionEventEnterVorname();
			break;
			case GlobaleVariablen.EVENT_ENTER_TELEFON:
			handleActionEventEnterTelefon();
			break;
			case GlobaleVariablen.EVENT_ENTER_MAIL:
			handleActionEventEnterMail();
			break;
			case GlobaleVariablen.EVENT_BUTTONBUCHUNGBESTAETIGUNG:
			handleActionEventButtonBestaetigung();
			break;
			case GlobaleVariablen.EVENT_BUTTONBUCHUNGABBRECHEN:
			handleActionEventButtonAbbrechen();
			break;
			case GlobaleVariablen.EVENT_BUTTONSYNCHRONISIEREN:
			handleActionEventButtonSynchronisieren();
			break;
		}
		
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg0 == this.buchungenModel) {
			
		}

	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getSource() == buchungenView.getListTour()) {
			handleListSelectionEventListRoute();
		} else if (e.getSource() == buchungenView.getListDatum()) {
			handleListSelectionEventListDatum();
		} else if (e.getSource() == buchungenView.getListUhrzeiten()) {
			handleListSelectionEventListUhrzeit();
		}
	}

	private void handleListSelectionEventListRoute() {
		// Aufruf, damit die Methode nur gestartet wird, wenn ein
		// Feld selectiert ist
		if(buchungenView.getListTour().isSelectionEmpty() == true) {
			return;
		}
		// löschen der anderen Listen und Textfelder
		buchungenView.getListDatum().setListData(new String[0]);
		buchungenView.getListUhrzeiten().setListData(new String[0]);
		buchungenView.getTextUhrzeitBestaetigung().setText("");
		buchungenView.getTextDatumBestaetigung().setText("");
		buchungenView.getTextSchiffBestaetigung().setText("");
		
		// Tour Name eintragen
		buchungenModel.setTourName(buchungenView.getListTour().getSelectedValue());
		buchungenView.getTextRouteBestaetigung().setText(buchungenView.getListTour().getSelectedValue());
		
		// Ausgabe des RoutenName
		buchungenView.getTextRouteBestaetigung().setText(buchungenModel.getTourName());
		
		//Datum eintragen
		buchungenView.getListDatum().setListData(new Select().selectRouteDatumFromTour(0, buchungenModel.getTourName()));
		
		// Button zum Buchen auf Enabled(false) setzen = nicht anklickbar
		buchungenView.getButtonBuchungBestaetigung().setEnabled(false);
	}

	private void handleListSelectionEventListDatum() {
		// Überprüfung, ob ein Element selektiert ist.
		if(buchungenView.getListDatum().isSelectionEmpty() == true) {
			return;
		}
		
		// löschen der anderen Listen und Textfelder
		buchungenView.getListUhrzeiten().setListData(new String[0]);
		buchungenView.getTextUhrzeitBestaetigung().setText("");
		buchungenView.getTextSchiffBestaetigung().setText("");
		
		// Datum aus der Liste in das entsprechende Textfeld schreiben
		buchungenView.getTextDatumBestaetigung().setText(buchungenView.getListDatum().getSelectedValue());
		
		// Datum 
		buchungenModel.setDatum(buchungenView.getListDatum().getSelectedValue());
		
		buchungenView.getListUhrzeiten().setListData(new Select().selectRouteZeitFromTour(
			buchungenModel.getPlaetze(), buchungenModel.getTourName(), buchungenModel.getDatum()));
		
		// Button zum Buchen auf Enabled(false) setzen = nicht anklickbar
		buchungenView.getButtonBuchungBestaetigung().setEnabled(false);
	}

	private void handleListSelectionEventListUhrzeit() {
		// Überprüfung, ob ein Element selektiert ist.
		if(buchungenView.getListUhrzeiten().isSelectionEmpty() == true) {
			return;
		}
		
		// Die Uhrzeit im Model speichern.
		buchungenModel.setUhrzeit(buchungenView.getListUhrzeiten().getSelectedValue());
		
		buchungenView.getTextUhrzeitBestaetigung().setText(buchungenView.getListUhrzeiten().getSelectedValue());
		
		buchungenView.getTextSchiffBestaetigung().setText(new Select().selectRouteSchiffFromShip(
			buchungenModel.getPlaetze(), buchungenModel.getTourName(), buchungenModel.getDatum(), buchungenModel.getUhrzeit()));
		
		// Button anklickbar, weil alle Felder einen Wert besitzen.
		buchungenView.getButtonBuchungBestaetigung().setEnabled(true);
	}
	
	private void handleActionEventEnterPlaetze() {
		
		// Löschen der anderen Listen und Textfelder
		buchungenView.getListDatum().setListData(new String[0]);
		buchungenView.getListUhrzeiten().setListData(new String[0]);
		buchungenView.getTextDatumBestaetigung().setText("");
		buchungenView.getTextSchiffBestaetigung().setText("");
		buchungenView.getTextRouteBestaetigung().setText("");
		buchungenView.getTextPlaetzeBestaetigung().setText("");
		
		// Die Anzahl der gesuchten Plätze wird gespeichert
		buchungenModel.setPlaetze(Integer.parseInt(buchungenView.getTextBenoetigtePlaete().getText()));
		buchungenView.getTextPlaetzeBestaetigung().setText(buchungenView.getTextBenoetigtePlaete().getText());
		
		// Die Werte an der Oberfläche darstellen
		buchungenView.getListTour().setListData(new Select().selectRouteNameFromTour(buchungenModel.getPlaetze()));
		
		// legt den Fokus auf das nächste Textfeld
		buchungenView.getTextName().requestFocus();
		
		// Button zum Buchen auf Enabled(false) setzen = nicht anklickbar
		buchungenView.getButtonBuchungBestaetigung().setEnabled(false);
	}
	
	private void handleActionEventEnterName() {
		buchungenView.getTextVorname().requestFocus();
	}
	
	private void handleActionEventEnterVorname() {
		buchungenView.getTextMail().requestFocus();
	}

	private void handleActionEventEnterMail() {
		buchungenView.getTextTelefon().requestFocus();
	}

	private void handleActionEventEnterTelefon() {
		buchungenView.getListTour().requestFocus();
	}
	
	/**
	 * löscht alles auf der Oberfläche und setzt den Fokus
	 * auf das Textfeld für die Eingabe der Plätze
	 */
	private void handleActionEventButtonAbbrechen() {
		// Leerzeichen für die Textfelder
		buchungenView.getTextBenoetigtePlaete().setText("");
		buchungenView.getTextUhrzeitBestaetigung().setText("");
		buchungenView.getTextUhrzeitBestaetigung().setText("");
		buchungenView.getTextName().setText("");
		buchungenView.getTextVorname().setText("");
		buchungenView.getTextMail().setText("");
		buchungenView.getTextTelefon().setText("");
		buchungenView.getTextPlaetzeBestaetigung().setText("");
		buchungenView.getTextRouteBestaetigung().setText("");
		buchungenView.getTextSchiffBestaetigung().setText("");
		buchungenView.getTextDatumBestaetigung().setText("");
		
		// leere Listen für die Listen
		buchungenView.getListUhrzeiten().setListData(new String[0]);
		buchungenView.getListDatum().setListData(new String[0]);
		buchungenView.getListTour().setListData(new String[0]);
		
		// "Buchen"-Button als nicht anklickbar
		buchungenView.getButtonBuchungBestaetigung().setEnabled(false);
		
		// Fokus auf das Textfeld für die eingabe der Daten
		buchungenView.getTextBenoetigtePlaete().requestFocus();
	}

	private void handleActionEventButtonSynchronisieren(){

		Drop dropper = new Drop();
		dropper.DropDB();

		Create creater = new Create();
		creater.CreatePlainDB();

		Insert inserter = new Insert();


		

		api_client http= new api_client();

		List<agent> agents=http.get_agents();
		List<ship> ships=http.get_ships();
		List<tour> tours=http.get_tours();
		List<quota> quotas=http.get_quotas();
		List<customer> customers=http.get_customers();

		for(agent tmp: agents) 
			inserter.insertAgent(tmp);

		for(ship tmp: ships) 
			inserter.insertShip(tmp);

		for(tour tmp: tours) 
			inserter.insertTour(tmp);

		for(quota tmp: quotas) 
			inserter.insertQuota(tmp);

		for(customer tmp: customers) 
			inserter.insertCustomer(tmp);

		}


	/**
	 * Der Button ist für die Buchungsbestätigung. Die Buchung wird in einer Datenbank 
	 * gespeichert. Anschließend wird die Methode des Abbrechen-Button aufgerufen.
	 */
	private void handleActionEventButtonBestaetigung() {
		handleActionEventButtonAbbrechen();		
	}
	
}
