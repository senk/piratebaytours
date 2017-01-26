package buchungenMVC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import datenbank.Insert;
import datenbank.Select;
import datenbank.Update;
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
		
		// Selection Listener
		buchungenView.getListRoute().addListSelectionListener(this); 
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
		}
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg0 == this.buchungenModel) {
			
		}

	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getSource() == buchungenView.getListRoute()) {
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
		if(buchungenView.getListRoute().isSelectionEmpty() == true) {
			return;
		}
		// l�schen der anderen Listen und Textfelder
		buchungenView.getListDatum().setListData(new String[0]);
		buchungenView.getListUhrzeiten().setListData(new String[0]);
		buchungenView.getTextDatumBestaetigung().setText("");
		buchungenView.getTextSchiffBestaetigung().setText("");
		
		// RoutenID und RoutenName im Model speichern.
		int selectedIndex = buchungenView.getListRoute().getSelectedIndex();
		int[] listeIDRoute = buchungenModel.getRoutenVO().getRouten_id();
		
		buchungenModel.setRouteId(listeIDRoute[selectedIndex]);
		buchungenModel.setRouteName(buchungenView.getListRoute().getSelectedValue());
		
		// Ausgabe des RoutenName (RoutenDauer) 
		buchungenView.getTextRouteBestaetigung().setText(buchungenView.getListRoute().getSelectedValue());
		
		// Datum aus der Tabelle Tour lesen und im model speichern
//		buchungenModel.getTourVO().setDatum(
//				new Select().selectRouteDatumFromTour(buchungenModel.getPlaetze(), buchungenModel.getRouteId()));
		
		// Datum in der Liste auf der Oberfläche ausgeben
//		buchungenView.getListDatum().setListData(buchungenModel.getTourVO().getDatum());
		
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
		buchungenView.getTextSchiffBestaetigung().setText("");
		
		// Datum aus der Liste in das entsprechende Textfeld schreiben
		buchungenView.getTextDatumBestaetigung().setText(buchungenView.getListDatum().getSelectedValue());
		
		// Datum im Model übernehmen.
		buchungenModel.setDatum(buchungenView.getListDatum().getSelectedValue());
		
		// startzeiten aus Tabelle Tour laden und im Model speichern.
//		buchungenModel.getTourVO().setStartzeit(
//				new Select().selectRoutenStartzeitFromTour(buchungenModel.getPlaetze(), buchungenModel.getRouteId(),
//						buchungenModel.getDatum()));
		
		// Startzeiten auf der Oberfläche ausgeben.
		buchungenView.getListUhrzeiten().setListData(buchungenModel.getTourVO().getStartzeit());
		
		// Button zum Buchen auf Enabled(false) setzen = nicht anklickbar
		buchungenView.getButtonBuchungBestaetigung().setEnabled(false);
	}

	private void handleListSelectionEventListUhrzeit() {
		// Überprüfung, ob ein Element selektiert ist.
		if(buchungenView.getListUhrzeiten().isSelectionEmpty() == true) {
			return;
		}
		
		// Variablen
//		int schiffId = new Select().selectSchiffIdFromTourWHERE(buchungenModel.getRouteId(), 
//				buchungenModel.getDatum(), buchungenModel.getUhrzeit());
//		String schiffName = new Select().selectSchiffNameFromSchiffWHERE(schiffId);
		String uhrzeit = buchungenView.getListUhrzeiten().getSelectedValue();
		
		// Die Uhrzeit im Model speichern.
		buchungenModel.setUhrzeit(uhrzeit);
		
		// SchiffsId und Schiffsname im Model speichern
//		buchungenModel.setSchiffId(schiffId);
//		buchungenModel.setSchiffName(schiffName);
		
		// Schiffsname speichern.
//		buchungenView.getTextSchiffBestaetigung().setText(schiffName);
		
		// Button anklickbar, weil alle Felder einen Wert besitzen.
		buchungenView.getButtonBuchungBestaetigung().setEnabled(true);
	}
	
	private void handleListSelectionEventListDauer() {
		// TODO Auto-generated method stub
		
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
		
		// Die "verfügbaren" Routen werden aus der "Touren"-Tabelle geladen  und im Model speichern
		buchungenModel.getRoutenVO().setRouten_name(new Select().selectRouteNameFromTour(buchungenModel.getPlaetze()));

		// Die Werte an der Oberfläche darstellen
		buchungenView.getTextPlaetzeBestaetigung().setText("" + buchungenModel.getPlaetze());
		buchungenView.getListRoute().setListData(new Select().selectRouteNameFromTour(buchungenModel.getPlaetze()));
		
		for (String a : this.buchungenModel.getRoutenVO().getRouten_name()) {
			System.out.println(a);
		}
		
		
		// legt den Fokus auf das n�chste Textfeld
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
		buchungenView.getListRoute().requestFocus();
	}
	
	/**
	 * löscht alles auf der Oberfläche und setzt den Fokus
	 * auf das Textfeld für die Eingabe der Plätze
	 */
	private void handleActionEventButtonAbbrechen() {
		// Leerzeichen für die Textfelder
		buchungenView.getTextBenoetigtePlaete().setText("");
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
		buchungenView.getListRoute().setListData(new String[0]);
		
		// "Buchen"-Button als nicht anklickbar
		buchungenView.getButtonBuchungBestaetigung().setEnabled(false);
		
		// Fokus auf das Textfeld für die eingabe der Daten
		buchungenView.getTextBenoetigtePlaete().requestFocus();
	}

	/**
	 * Der Button ist für die Buchungsbestätigung. Die Buchung wird in einer Datenbank 
	 * gespeichert. Anschließend wird die Methode des Abbrechen-Button aufgerufen.
	 */
	private void handleActionEventButtonBestaetigung() {
		int routenId = buchungenModel.getRouteId();
		String tourDatum = buchungenModel.getDatum();
		String tourStartzeit = buchungenModel.getUhrzeit();
		int plaetze = buchungenModel.getPlaetze();
		
		new Insert().insertIntoBuchungen(plaetze, routenId, 
				tourDatum, tourStartzeit , buchungenModel.getSchiffId());
		
//		int plaetze_vorhanden = new Select().selectPlaetzeFromTour(routenId, tourDatum, tourStartzeit);
		
//		new Update().UpdateTourSetplaetze(plaetze_vorhanden - plaetze , routenId, tourDatum, tourStartzeit);
		
		handleActionEventButtonAbbrechen();
		
	}
	
}
