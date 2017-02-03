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
	private UTextField textNameAgent;
	private UTextField textBenoetigtePlaetze;
	private UTextField textName;
	private UTextField textPlaetzeBestaetigung;
	private UTextField textRouteBestaetigung;
	private UTextField textSchiffBestaetigung;
	private UTextField textUhrzeitBestaetigung;
	private UTextField textDatumBestaetigung;
	private UTextField textExistingCount;

	// Button
	private UButton buttonBuchungBestaetigung;
	private UButton buttonBuchungAbbrechen;
	private UButton buttonDownload;
	private UButton buttonUpload;
	
	// Listen
	private JList<String> listTour;
	private JList<String> listUhrzeiten;
	private JList<String> listDatum;

	private JScrollPane scrollListRoute;
	private JScrollPane scrollListUhrzeiten;
	private JScrollPane scrollListDatum;

	// Panel
	private UPanel rootPanel;
	private UPanel panelUeberschrift;
	private UPanel panelKundenInfo;
	private UPanel panelBuchungsauswahl;
	private UPanel panelBuchungsbestaetigung;
	private UPanel panelButtonBestaetigung;

	// Label
	private ULabel labelUeberschrift;
	private ULabel labelNameAgent;
	private ULabel labelBenoetigtePlaete;
	private ULabel labelName;
	private ULabel labelRoute;
	private ULabel labelExistingCount;
	private ULabel labelUhrzeiten;
	private ULabel labelDatum;
	private ULabel labelPlaetzeBestaetigung;
	private ULabel labelRouteBestaetigung;
	private ULabel labelSchiffBestaetigung;
	private ULabel labelUhrzeitBestaetigung;
	private ULabel labelDatumBestaetigung;
	
	public BuchungenView() {
		// Definition des Frames
		this.setTitle("PirateBayTours-Buchungen");
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
			
			labelNameAgent = new ULabel("Agent");
			panelKundenInfo.add(labelNameAgent, new UConstraints(2, 0));
			
			labelExistingCount = new ULabel("Verbleibende Plätze");
			panelKundenInfo.add(labelExistingCount, new UConstraints(2, 1));
			
			textBenoetigtePlaetze = new UTextField(20);
			textBenoetigtePlaetze.setActionCommand(GlobaleVariablen.EVENT_ENTER_PLAETZE);
			panelKundenInfo.add(textBenoetigtePlaetze, new UConstraints(1, 0));
			
			textName = new UTextField(20);
			textName.setActionCommand(GlobaleVariablen.EVENT_ENTER_NAME);
			panelKundenInfo.add(textName, new UConstraints(1, 1));
			
			textNameAgent = new UTextField(20);
			panelKundenInfo.add(textNameAgent, new UConstraints(3, 0));
			
			textExistingCount = new UTextField(20);
			textExistingCount.setEditable(false);
			panelKundenInfo.add(textExistingCount, new UConstraints(3, 1));
			
		}
		
		// Panel für die Buchungen
		{
			panelBuchungsauswahl = new UPanel();
			panelBuchungsauswahl.setTitledBorder("Buchungsauswahl");
			rootPanel.add(panelBuchungsauswahl, new UConstraints(0, 2));
			
			labelRoute = new ULabel("Touren");
			panelBuchungsauswahl.add(labelRoute, new UConstraints(0, 0));
			
			labelDatum = new ULabel("Datum");
			panelBuchungsauswahl.add(labelDatum, new UConstraints(2, 0));
			
			labelUhrzeiten = new ULabel("Uhrzeiten");
			panelBuchungsauswahl.add(labelUhrzeiten, new UConstraints(3, 0));
			
			listTour = new JList<String>();
			listTour.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			scrollListRoute = new JScrollPane();
			scrollListRoute.setPreferredSize(new Dimension(200, 200));
			scrollListRoute.setViewportView(listTour);
			panelBuchungsauswahl.add(scrollListRoute, new UConstraints(0, 1));

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
			
			textRouteBestaetigung = new UTextField(15);
			textRouteBestaetigung.setEditable(false);
			panelBuchungsbestaetigung.add(textRouteBestaetigung, new UConstraints(1, 1));
			
			textDatumBestaetigung = new UTextField(10);
			textDatumBestaetigung.setEditable(false);
			panelBuchungsbestaetigung.add(textDatumBestaetigung, new UConstraints(2, 1));
			
			textUhrzeitBestaetigung = new UTextField(10);
			textUhrzeitBestaetigung.setEditable(false);
			panelBuchungsbestaetigung.add(textUhrzeitBestaetigung, new UConstraints(3, 1));
			
			textSchiffBestaetigung = new UTextField(15);
			textSchiffBestaetigung.setEditable(false);
			panelBuchungsbestaetigung.add(textSchiffBestaetigung, new UConstraints(4, 1));
			
			//Panel für die Button
			{
				panelButtonBestaetigung = new UPanel();
				panelBuchungsbestaetigung.add(panelButtonBestaetigung, new UConstraints(2, 2, 0, 4));
				
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
				
				buttonDownload = new UButton();
				buttonDownload.setText("Download");
				buttonDownload.setActionCommand(GlobaleVariablen.EVENT_BUTTONDOWNLOAD);
				buttonDownload.setBackground(Color.darkGray);
				buttonDownload.setForeground(Color.white);
				panelButtonBestaetigung.add(buttonDownload, new UConstraints(2, 2));
				
				buttonUpload = new UButton();
				buttonUpload.setText("Upload");
				buttonUpload.setActionCommand(GlobaleVariablen.EVENT_BUTTONUPLOAD);
				buttonUpload.setBackground(Color.darkGray);
				buttonUpload.setForeground(Color.white);
				panelButtonBestaetigung.add(buttonUpload, new UConstraints(3, 2));
			}
		}
		
		// Packen
		this.pack();
	}

	// Getter für die Wertebelegung
	public JList<String> getListTour() {
		return listTour;
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

	public UButton getButtonDownload() {
		return buttonDownload;
	}
	public UButton getButtonUpload() {
		return buttonUpload;
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

	public UTextField getTextNameAgent() {
		return textNameAgent;
	}
	public UTextField getTextExistingCount() {
		return textExistingCount;
	}

}
