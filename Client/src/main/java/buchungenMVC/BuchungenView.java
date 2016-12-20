package buchungenMVC;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import gui.UPanel;
import gui.UTextField;
import gui.GlobaleVariablen;
import gui.UButton;
import gui.UConstraints;
import gui.ULabel;

public class BuchungenView extends JFrame{
	private static final long serialVersionUID = 6038622921625628983L;
	// Textfelder
	private UTextField textBenoetigtePlaetze;
	private UTextField textName;
	private UTextField textVorname;
	private UTextField textMail;
	private UTextField textTelefon;
	private UTextField textPlaetzeBestaetigung;
	private UTextField textRouteBestaetigung;
	private UTextField textSchiffBestaetigung;
	private UTextField textUhrzeitBestaetigung;
	private UTextField textDatumBestaetigung;
	
	// Button
	private UButton buttonBuchungBestaetigung;
	private UButton buttonBuchungAbbrechen;
	
	// Listen
	private JList<String> listRoute;
	private JList<String> listUhrzeiten;
	private JList<String> listDatum;
	private JList<Float> listDauer;
	
	private JScrollPane scrollListRoute;
	private JScrollPane scrollListUhrzeiten;
	private JScrollPane scrollListDatum;
	private JScrollPane scrollListDauer;

	// Panel
	private UPanel rootPanel;
	private UPanel panelUeberschrift;
	private UPanel panelKundenInfo;
	private UPanel panelBuchungsauswahl;
	private UPanel panelBuchungsbestaetigung;
	private UPanel panelButtonBestaetigung;

	// Label
	private ULabel labelUeberschrift;
	private ULabel labelBenoetigtePlaete;
	private ULabel labelName;
	private ULabel labelVorname;
	private ULabel labelMail;
	private ULabel labelTelefon;
	private ULabel labelRoute;
	private ULabel labelUhrzeiten;
	private ULabel labelDatum;
	private ULabel labelPlaetzeBestaetigung;
	private ULabel labelRouteBestaetigung;
	private ULabel labelSchiffBestaetigung;
	private ULabel labelUhrzeitBestaetigung;
	private ULabel labelDatumBestaetigung;
	private ULabel labelDauer;
	
	public BuchungenView() {
		// Definition des Frames
		this.setTitle("PricatBayTours-Buchungen");
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Root Panel, welches am Ende übergeben wird. Alles auf der Oberfläche
		// muss am Ende dem rootPanel übergeben werden.
		{
			rootPanel = new UPanel();
			rootPanel.setTitledBorder("Buchungsauswahl");
			this.add(rootPanel);
		}
		
		// Panel für die Überschrift
		{
			panelUeberschrift = new UPanel();
			rootPanel.add(panelUeberschrift, new UConstraints(0, 0));

			labelUeberschrift = new ULabel("<html><div align='right'>PirateBayTours<br/>Buchungen</div></html>");
			labelUeberschrift.setForeground(Color.blue);
			labelUeberschrift.setFont(new Font("Arial", Font.PLAIN, 30));
			panelUeberschrift.add(labelUeberschrift, new UConstraints(0, 0));
		}
		
		// Panel Informationen Kunde
		{
			panelKundenInfo = new UPanel();
			panelKundenInfo.setTitledBorder("Kundeninformationen");
			rootPanel.add(panelKundenInfo, new UConstraints(0, 1));
			
			labelBenoetigtePlaete = new ULabel("Benötigte Plätze *");
			panelKundenInfo.add(labelBenoetigtePlaete, new UConstraints(0, 0));
			
			labelName = new ULabel("Name");
			panelKundenInfo.add(labelName, new UConstraints(0, 1));
			
			labelVorname = new ULabel("Vorname");
			panelKundenInfo.add(labelVorname, new UConstraints(0, 2));
			
			labelMail = new ULabel("Mail-Adresse");
			panelKundenInfo.add(labelMail, new UConstraints(0, 3));
			
			labelTelefon = new ULabel("Telefon-Nr.");
			panelKundenInfo.add(labelTelefon, new UConstraints(0, 4));
			
			textBenoetigtePlaetze = new UTextField(20);
			textBenoetigtePlaetze.setActionCommand(GlobaleVariablen.EVENT_ENTER_PLAETZE);
			panelKundenInfo.add(textBenoetigtePlaetze, new UConstraints(1, 0));
			
			textName = new UTextField(20);
			textName.setActionCommand(GlobaleVariablen.EVENT_ENTER_NAME);
			panelKundenInfo.add(textName, new UConstraints(1, 1));
			
			textVorname = new UTextField(20);
			textVorname.setActionCommand(GlobaleVariablen.EVENT_ENTER_VORNAME);
			panelKundenInfo.add(textVorname, new UConstraints(1, 2));
			
			textMail = new UTextField(20);
			textMail.setActionCommand(GlobaleVariablen.EVENT_ENTER_MAIL);
			panelKundenInfo.add(textMail, new UConstraints(1, 3));
			
			textTelefon = new UTextField(20);
			textTelefon.setActionCommand(GlobaleVariablen.EVENT_ENTER_TELEFON);
			panelKundenInfo.add(textTelefon, new UConstraints(1, 4));
		}
		
		// Panel für die Buchungen
		{
			panelBuchungsauswahl = new UPanel();
			panelBuchungsauswahl.setTitledBorder("Buchungsauswahl");
			rootPanel.add(panelBuchungsauswahl, new UConstraints(0, 2));
			
			labelRoute = new ULabel("Routen");
			panelBuchungsauswahl.add(labelRoute, new UConstraints(0, 0));
			
			labelDauer = new ULabel("Dauer");
			panelBuchungsauswahl.add(labelDauer, new UConstraints(1, 0));
			
			labelDatum = new ULabel("Datum");
			panelBuchungsauswahl.add(labelDatum, new UConstraints(2, 0));
			
			labelUhrzeiten = new ULabel("Uhrzeiten");
			panelBuchungsauswahl.add(labelUhrzeiten, new UConstraints(3, 0));
			
			listRoute = new JList<String>();
			listRoute.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			scrollListRoute = new JScrollPane();
			scrollListRoute.setPreferredSize(new Dimension(200, 200));
			scrollListRoute.setViewportView(listRoute);
			panelBuchungsauswahl.add(scrollListRoute, new UConstraints(0, 1));
			
			listDauer = new JList<Float>();
			listDauer.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			scrollListDauer = new JScrollPane();
			scrollListDauer.setPreferredSize(new Dimension(30, 200));
			scrollListDauer.setViewportView(listDauer);
			panelBuchungsauswahl.add(scrollListDauer, new UConstraints(1, 1));

			listDatum = new JList<String>();
			listDatum.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			scrollListDatum = new JScrollPane();
			scrollListDatum.setPreferredSize(new Dimension(200, 200));
			scrollListDatum.setViewportView(listDatum);
			panelBuchungsauswahl.add(scrollListDatum, new UConstraints(2, 1));
			
			listUhrzeiten = new JList<String>();
			listUhrzeiten.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			scrollListUhrzeiten = new JScrollPane();
			scrollListUhrzeiten.setPreferredSize(new Dimension(200, 200));
			scrollListUhrzeiten.setViewportView(listUhrzeiten);
			panelBuchungsauswahl.add(scrollListUhrzeiten, new UConstraints(3, 1));
			
		}
		
		// Panel für die Buchungsbestätigung
		{
			panelBuchungsbestaetigung = new UPanel();
			panelBuchungsbestaetigung.setTitledBorder("Buchungsbestätigung");
			rootPanel.add(panelBuchungsbestaetigung, new UConstraints(0, 3));
			
			labelPlaetzeBestaetigung = new ULabel("Plätze");
			panelBuchungsbestaetigung.add(labelPlaetzeBestaetigung, new UConstraints(0, 0));
			
			labelRouteBestaetigung = new ULabel("Route");
			panelBuchungsbestaetigung.add(labelRouteBestaetigung, new UConstraints(1, 0));
			
			labelDatumBestaetigung = new ULabel("Datum");
			panelBuchungsbestaetigung.add(labelDatumBestaetigung, new UConstraints(2, 0));
			
			labelUhrzeitBestaetigung = new ULabel("Uhrzeit");
			panelBuchungsbestaetigung.add(labelUhrzeitBestaetigung, new UConstraints(3, 0));
			
			labelSchiffBestaetigung = new ULabel("Schiff");
			panelBuchungsbestaetigung.add(labelSchiffBestaetigung, new UConstraints(4, 0));
			
			textPlaetzeBestaetigung = new UTextField(2);
			textPlaetzeBestaetigung.setEditable(false);
			panelBuchungsbestaetigung.add(textPlaetzeBestaetigung, new UConstraints(0, 1, GridBagConstraints.BOTH));
			
			textRouteBestaetigung = new UTextField(20);
			textRouteBestaetigung.setEditable(false);
			panelBuchungsbestaetigung.add(textRouteBestaetigung, new UConstraints(1, 1));
			
			textDatumBestaetigung = new UTextField(10);
			textDatumBestaetigung.setEditable(false);
			panelBuchungsbestaetigung.add(textDatumBestaetigung, new UConstraints(2, 1));
			
			textUhrzeitBestaetigung = new UTextField(10);
			textUhrzeitBestaetigung.setEditable(false);
			panelBuchungsbestaetigung.add(textUhrzeitBestaetigung, new UConstraints(3, 1));
			
			textSchiffBestaetigung = new UTextField(20);
			textSchiffBestaetigung.setEditable(false);
			panelBuchungsbestaetigung.add(textSchiffBestaetigung, new UConstraints(4, 1));
			
			//Panel für die Button
			{
				panelButtonBestaetigung = new UPanel();
				panelBuchungsbestaetigung.add(panelButtonBestaetigung, new UConstraints(4, 2, 0, 2));
				
				buttonBuchungBestaetigung = new UButton();
				buttonBuchungBestaetigung.setText("Kaufen");
				buttonBuchungBestaetigung.setActionCommand(GlobaleVariablen.EVENT_BUTTONBUCHUNGBESTAETIGUNG);
				buttonBuchungBestaetigung.setBackground(Color.GREEN);
				panelButtonBestaetigung.add(buttonBuchungBestaetigung, new UConstraints(0, 2));
				
				buttonBuchungAbbrechen = new UButton();
				buttonBuchungAbbrechen.setText("Abbrechen");
				buttonBuchungAbbrechen.setActionCommand(GlobaleVariablen.EVENT_BUTTONBUCHUNGABBRECHEN);
				buttonBuchungAbbrechen.setBackground(Color.RED);
				panelButtonBestaetigung.add(buttonBuchungAbbrechen, new UConstraints(1, 2));
			}
		}
		
		// Packen
		this.pack();
	}

	// Getter für die Wertebelegung
	public JList<String> getListRoute() {
		return listRoute;
	}
	
	public JList<Float> getListDauer() {
		return listDauer;
	}

	public JList<String> getListUhrzeiten() {
		return listUhrzeiten;
	}
	
	public UTextField getTextBenoetigtePlaete() {
		return textBenoetigtePlaetze;
	}
	public UTextField getTextPlaetzeBestaetigung() {
		return textPlaetzeBestaetigung;
	}
	public UButton getButtonBuchungBestaetigung() {
		return buttonBuchungBestaetigung;
	}

	public JList<String> getListDatum() {
		return listDatum;
	}

	public UButton getButtonBuchungAbbrechen() {
		return buttonBuchungAbbrechen;
	}

	public UTextField getTextRouteBestaetigung() {
		return textRouteBestaetigung;
	}

	public UTextField getTextSchiffBestaetigung() {
		return textSchiffBestaetigung;
	}

	public UTextField getTextUhrzeitBestaetigung() {
		return textUhrzeitBestaetigung;
	}

	public UTextField getTextDatumBestaetigung() {
		return textDatumBestaetigung;
	}
	
	public UTextField getTextName() {
		return textName;
	}

	public UTextField getTextVorname() {
		return textVorname;
	}

	public UTextField getTextMail() {
		return textMail;
	}

	public UTextField getTextTelefon() {
		return textTelefon;
	}
}
