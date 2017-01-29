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
import datenbank.Update;


import api_client.*;
import gui.GlobaleVariablen;

public class BuchungenController implements Observer, ActionListener, ListSelectionListener {

	private Boolean debug = true;

	private BuchungenModel buchungenModel;
	private BuchungenView buchungenView;

	public BuchungenController(BuchungenModel modelGUI, BuchungenView viewGUI) {
		buchungenModel = modelGUI;
		buchungenView = viewGUI;
		buchungenModel.addObserver(this);
		// ActionListener
		buchungenView.getTextBenoetigtePlaete().addActionListener(this); 
		buchungenView.getTextName().addActionListener(this);
		buchungenView.getTextNameAgent().addActionListener(this);
		buchungenView.getButtonBuchungBestaetigung().addActionListener(this);
		buchungenView.getButtonBuchungAbbrechen().addActionListener(this);
		buchungenView.getButtonDownload().addActionListener(this);
		buchungenView.getButtonUpload().addActionListener(this);

		
		// Selection Listener
		buchungenView.getListTour().addListSelectionListener(this); 
		buchungenView.getListDatum().addListSelectionListener(this); 
		buchungenView.getListUhrzeiten().addListSelectionListener(this);
		
		// Voreinstellungen
		buchungenView.getButtonBuchungBestaetigung().setEnabled(false);
		buchungenView.getTextNameAgent().setText("1");
		
		if(new Select().selectIdFromLookButtton() == 1) {
			buchungenView.getButtonDownload().setEnabled(false);
		}
		
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
			case GlobaleVariablen.EVENT_BUTTONBUCHUNGBESTAETIGUNG:
			handleActionEventButtonBestaetigung();
			break;
			case GlobaleVariablen.EVENT_BUTTONBUCHUNGABBRECHEN:
			handleActionEventButtonAbbrechen();
			break;
			case GlobaleVariablen.EVENT_BUTTONDOWNLOAD:
			handleActionEventButtonSynchronisieren();
			break;
			case GlobaleVariablen.EVENT_BUTTONUPLOAD:
			handleActionEventButtonUpload();
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
		buchungenView.getListDatum().setListData(new Select().selectRouteDatumFromTour(
			buchungenModel.getPlaetze(), buchungenModel.getTourName(), buchungenModel.getAgentNr()));
		
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
		String[] datumunformatiert = buchungenView.getListDatum().getSelectedValue().split(java.util.regex.Pattern.quote("."));
		
		buchungenModel.setDatum(datumunformatiert[2] +  "-" + datumunformatiert[1] + "-" + datumunformatiert[0]);
		
		buchungenView.getListUhrzeiten().setListData(new Select().selectRouteZeitFromTour(
			buchungenModel.getPlaetze(), buchungenModel.getTourName(), buchungenModel.getDatum(), buchungenModel.getAgentNr()));
		
		// Button zum Buchen auf Enabled(false) setzen = nicht anklickbar
		buchungenView.getButtonBuchungBestaetigung().setEnabled(false);
	}

	private void handleListSelectionEventListUhrzeit() {
		// Überprüfung, ob ein Element selektiert ist.
		if(buchungenView.getListUhrzeiten().isSelectionEmpty() == true) {
			return;
		}
		
		// Die Uhrzeit im Model speichern.
		buchungenModel.setUhrzeit(buchungenView.getListUhrzeiten().getSelectedValue() + ":00");
		
		buchungenView.getTextUhrzeitBestaetigung().setText(buchungenView.getListUhrzeiten().getSelectedValue());
		
		String[] ship = new Select().selectRouteSchiffFromShip(
			buchungenModel.getPlaetze(), buchungenModel.getTourName(), buchungenModel.getDatum(), 
			buchungenModel.getUhrzeit(), buchungenModel.getAgentNr());
		
		buchungenView.getTextSchiffBestaetigung().setText(ship[0]);
		
		buchungenModel.setSchiffId((Integer.parseInt(ship[1])));
		
		buchungenModel.setTourId(new Select().selectTourIDfromTour(
			buchungenModel.getTourName(), buchungenModel.getDatum()
			, buchungenModel.getUhrzeit(), buchungenModel.getSchiffId()));
		
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
		
		buchungenModel.setAgentNr(Integer.parseInt(buchungenView.getTextNameAgent().getText()));
		
		
		// Die Werte an der Oberfläche darstellen
		buchungenView.getListTour().setListData(new Select().selectRouteNameFromTour(buchungenModel.getPlaetze(),
			buchungenModel.getAgentNr()));
		
		// legt den Fokus auf das nächste Textfeld
		buchungenView.getTextName().requestFocus();
		
		// Button zum Buchen auf Enabled(false) setzen = nicht anklickbar
		buchungenView.getButtonBuchungBestaetigung().setEnabled(false);
	}

	private void handleActionEventEnterName() {
		buchungenView.getListTour().requestFocus();
		System.out.println(buchungenView.getTextName().getText());
		
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

		
		// unlook Button Download
		new Update().UpdatelookButton(0);
	}

	private void handleActionEventButtonUpload() {
		// TODO Auto-generated method stub
		
		//unlook Button Download
		buchungenView.getButtonDownload().setEnabled(true);
		new Update().UpdatelookButton(0);
	}


	
	/**
	 * Der Button ist für die Buchungsbestätigung. Die Buchung wird in einer Datenbank 
	 * gespeichert. Anschließend wird die Methode des Abbrechen-Button aufgerufen.
	 */
	private void handleActionEventButtonBestaetigung() {
		
		if (debug) System.out.println("Plätze:" + buchungenModel.getPlaetze());
		if (debug) System.out.println("Tour ID:" + buchungenModel.getTourId());
		if (debug) System.out.println("Customer Name: " + buchungenView.getTextName().getText());


		new Insert().insertOfflineBooking(buchungenModel.getPlaetze(),
			buchungenModel.getTourId(), buchungenView.getTextName().getText());
		
		
		
		// 
		handleActionEventButtonAbbrechen();
		
		// Look Button until Upload
		buchungenView.getButtonDownload().setEnabled(false);
		new Update().UpdatelookButton(1);
		
	}
	
}
