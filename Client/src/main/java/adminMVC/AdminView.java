package adminMVC;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Properties;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import gui.UPanel;
import gui.UTable;
import gui.UTextField;
import gui.GlobaleVariablen;
import gui.UButton;
import gui.UConstraints;
import gui.ULabel;

public class AdminView extends JFrame {
	private static final long serialVersionUID = 6038622921625628983L;
	// Textfelder
	private UTextField textFieldZeitAendern;
	private UTextField textFieldDatumAendern;
	private UTextField textFieldSchiffAenern;
	private UTextField textFieldRouteAendern;
	
	// JComboBox
	private JComboBox<String> jComboBoxMitarbeiter;
	private JComboBox<String> jComboBoxEingabeUhrzeit;
	
	// Button
	private UButton buttonErstellenTour;
	private UButton buttonAbbrechenTour;
	private UButton buttonZuweisenPlaetze;
	private UButton buttonAbbrechenPlaetze;

	// Listen
	private JList<String> listRoute;
	private JList<String> listSchiffe;
	private JList<String> listMitarbeiterBestaetigt;
	private JList<String> listMitarbeiterNichtBestaetigt;

	private JScrollPane scrollListRoute;
	private JScrollPane scrollListSchiffe;
	private JScrollPane scrollTableVorhandeneTouren;
	private JScrollPane scrollTablePlaetzeProMitarbeiter;
	private JScrollPane scrollListMitarbeiterBestaetigt;
	private JScrollPane scrollListMitarbeiterNichtBestaetigt;
	private JScrollPane scrollRootPanel;
	
	// Panel
	private UPanel rootPanel;
	private UPanel panelUeberschrift;
	private UPanel panelButtonErstellenTour;
	private UPanel panelAuswahl;
	private UPanel panelDatumZeit;
	private UPanel panelvorhandeneTouren;
	private UPanel panelButtonZuweisenPlaetze;
	private UPanel panelMitarbeiterReport;
	
	// Datumsfeld
	private JDatePickerImpl datePicker;
	private JDatePanelImpl datePanel;

	// Label
	private ULabel labelUeberschrift;
	private ULabel labelRoute;
	private ULabel labelSchiffe;
	private ULabel labelDatum;
	private ULabel labelZeit;
	private ULabel labelZeitAendern;
	private ULabel labelDatumAendern;
	private ULabel labelSchiffAenern;
	private ULabel labelRouteAendern;
	private ULabel labelMitarbeiter;
	private ULabel labelTabelleMitarbeiterInfo;
	private ULabel labelListMitarbeiterBestaetigt;
	private ULabel labelListMitarbeiterNichtBestaetigt;
	
	//Tabelle
	private UTable tableVorhandeneTouren;
	private UTable tablePlaetzeProMitarbeiter;
	
	

	public AdminView() {
		// Definition des Frames
		this.setTitle("PricatBayTours-Buchungen");
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Root Panel, welches am Ende übergeben wird. Alles auf der Oberfläche
		// muss am Ende dem rootPanel übergeben werden.
		{
			rootPanel = new UPanel();
			rootPanel.setTitledBorder("Administrator");
			
			scrollRootPanel= new JScrollPane();
			scrollRootPanel.setPreferredSize(new Dimension(200, 200));
			scrollRootPanel.setViewportView(rootPanel);
			
			
			this.add(scrollRootPanel);
		}

		// Panel für die Überschrift
		{
			panelUeberschrift = new UPanel();
			rootPanel.add(panelUeberschrift, new UConstraints(0, 0));

			labelUeberschrift = new ULabel("<html><div align='right'>PirateBayTours<br/>Admin</div></html>");
			labelUeberschrift.setForeground(Color.blue);
			labelUeberschrift.setFont(new Font("Arial", Font.PLAIN, 30));
			panelUeberschrift.add(labelUeberschrift, new UConstraints(0, 0));
		}
		
		// Panel für die Erstellung einer Tour
		{
			panelAuswahl = new UPanel();
			panelAuswahl.setTitledBorder("Erstellung einer neuen Tour");
			rootPanel.add(panelAuswahl, new UConstraints(0, 1));
			
			labelRoute = new ULabel("Routen");
			panelAuswahl.add(labelRoute, new UConstraints(0, 0));
			
			labelSchiffe = new ULabel("Schiffe");
			panelAuswahl.add(labelSchiffe, new UConstraints(1, 0));
			
			listRoute = new JList<String>();
			listRoute.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			scrollListRoute = new JScrollPane();
			scrollListRoute.setPreferredSize(new Dimension(200, 200));
			scrollListRoute.setViewportView(listRoute);
			panelAuswahl.add(scrollListRoute, new UConstraints(0, 1));
			
			listSchiffe = new JList<String>();
			listSchiffe.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			scrollListSchiffe = new JScrollPane();
			scrollListSchiffe.setPreferredSize(new Dimension(200, 200));
			scrollListSchiffe.setViewportView(listSchiffe);
			panelAuswahl.add(scrollListSchiffe, new UConstraints(1, 1));
			
			// Panel für das Datum und die Zeit
			{
				panelDatumZeit = new UPanel();
				UConstraints gbc = new UConstraints(2, 0, 2, 0);
				gbc.anchor = UConstraints.PAGE_START;
				panelAuswahl.add(panelDatumZeit, gbc);
				
				labelDatum = new ULabel("Datum");
				panelDatumZeit.add(labelDatum, new UConstraints(0, 0));
				
				labelZeit = new ULabel("Zeit");
				panelDatumZeit.add(labelZeit, new UConstraints(0, 2));
				
				// Datums als Textfeld
				UtilDateModel model = new UtilDateModel();
				Properties p = new Properties();
				p.put("text.today", "Today");
				p.put("text.month", "Month");
				p.put("text.year", "Year");
				datePanel = new JDatePanelImpl(model, p);
				datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
				panelDatumZeit.add(datePicker, new UConstraints(0, 1));
				
				jComboBoxEingabeUhrzeit = new JComboBox<String>();
				jComboBoxEingabeUhrzeit.setActionCommand(GlobaleVariablen.EVENT_ENTERUHRZEIT);
				panelDatumZeit.add(jComboBoxEingabeUhrzeit, new UConstraints(0, 3));
			}

			// Panel für die Button zur Erstellung einer Tour
			{
				panelButtonErstellenTour = new UPanel();
				UConstraints uc = new UConstraints(2, 1, 0, 2);
				uc.anchor = UConstraints.PAGE_END;
				panelAuswahl.add(panelButtonErstellenTour, uc);
				
				buttonErstellenTour = new UButton();
				buttonErstellenTour.setText("Erstellen");
				buttonErstellenTour.setActionCommand(GlobaleVariablen.EVENT_BUTTONERSTELLENTOUR);
				buttonErstellenTour.setBackground(Color.GREEN);
				panelButtonErstellenTour.add(buttonErstellenTour, new UConstraints(0, 0));
				
				buttonAbbrechenTour = new UButton();
				buttonAbbrechenTour.setText("Abbrechen");
				buttonAbbrechenTour.setActionCommand(GlobaleVariablen.EVENT_BUTTONABBRECHENTOUR);
				buttonAbbrechenTour.setBackground(Color.RED);
				panelButtonErstellenTour.add(buttonAbbrechenTour, new UConstraints(1, 0));
			}
		}
		
		// Panel für die Auflistung der Touren
		{
			
			panelvorhandeneTouren = new UPanel();
			panelvorhandeneTouren.setTitledBorder("Zum Verkauf stehende Touren");
			rootPanel.add(panelvorhandeneTouren, new UConstraints(0, 3));
			
			tableVorhandeneTouren = new UTable();
			
			scrollTableVorhandeneTouren = new JScrollPane();
			scrollTableVorhandeneTouren.setPreferredSize(new Dimension(600, 200));
			scrollTableVorhandeneTouren.setViewportView(tableVorhandeneTouren);
			panelvorhandeneTouren.add(scrollTableVorhandeneTouren, new UConstraints(0, 0, 2, 0));
			
			labelSchiffAenern = new ULabel("Schiff");
			panelvorhandeneTouren.add(labelSchiffAenern, new UConstraints(0, 2));
			
			labelRouteAendern = new ULabel("Route");
			panelvorhandeneTouren.add(labelRouteAendern, new UConstraints(1, 2));
			
			labelDatumAendern = new ULabel("Datum");
			panelvorhandeneTouren.add(labelDatumAendern, new UConstraints(2, 2));
			
			labelZeitAendern = new ULabel("Startzeit");
			panelvorhandeneTouren.add(labelZeitAendern, new UConstraints(3, 2));
			
			labelMitarbeiter = new ULabel("Mitarbeiter");
			panelvorhandeneTouren.add(labelMitarbeiter, new UConstraints(4, 2));
			
			textFieldSchiffAenern = new UTextField(20);
			textFieldSchiffAenern.setEditable(false);
			panelvorhandeneTouren.add(textFieldSchiffAenern, new UConstraints(0, 3));
			
			textFieldRouteAendern = new UTextField(20);
			textFieldRouteAendern.setEditable(false);
			panelvorhandeneTouren.add(textFieldRouteAendern, new UConstraints(1, 3));
			
			textFieldDatumAendern = new UTextField(10);
			textFieldDatumAendern.setEditable(false);
			panelvorhandeneTouren.add(textFieldDatumAendern, new UConstraints(2, 3));
			
			textFieldZeitAendern = new UTextField(5);
			textFieldZeitAendern.setEditable(false);
			panelvorhandeneTouren.add(textFieldZeitAendern, new UConstraints(3, 3));
			
			jComboBoxMitarbeiter = new JComboBox<String>();
			panelvorhandeneTouren.add(jComboBoxMitarbeiter, new UConstraints(4, 3));
			
			// Panel für die Button zur Erstellung einer Tour
			{
				panelButtonZuweisenPlaetze = new UPanel();
				UConstraints uc = new UConstraints(3, 4, 0, 2);
				panelvorhandeneTouren.add(panelButtonZuweisenPlaetze, uc);
				
				buttonZuweisenPlaetze = new UButton();
				buttonZuweisenPlaetze.setText("Zuweisen");
//			buttonZuweisenPlaetze.setActionCommand(GlobaleVariablen.EVENT_BUTTONERSTELLEN); TODO Fenster wirklich?
				buttonZuweisenPlaetze.setBackground(Color.GREEN);
				panelButtonZuweisenPlaetze.add(buttonZuweisenPlaetze, new UConstraints(0, 0));
				
				buttonAbbrechenPlaetze = new UButton();
				buttonAbbrechenPlaetze.setText("Abbrechen");
//			buttonAbbrechenPlaetze.setActionCommand(GlobaleVariablen.EVENT_BUTTONBUCHUNGABBRECHEN); TODO
				buttonAbbrechenPlaetze.setBackground(Color.RED);
				panelButtonZuweisenPlaetze.add(buttonAbbrechenPlaetze, new UConstraints(1, 0));
			}
		}
		
		
		panelMitarbeiterReport = new UPanel();
		panelMitarbeiterReport.setTitledBorder("Mitarbeiter Report");
		rootPanel.add(panelMitarbeiterReport, new UConstraints(0, 4));

		labelTabelleMitarbeiterInfo = new ULabel("Mitarbeiter Informationen");
		panelMitarbeiterReport.add(labelTabelleMitarbeiterInfo, new UConstraints(0, 0));
		
		labelListMitarbeiterBestaetigt = new ULabel("R�ckmeldung");
		panelMitarbeiterReport.add(labelListMitarbeiterBestaetigt, new UConstraints(1, 0));
		
		labelListMitarbeiterNichtBestaetigt = new ULabel("Keine R�ckmeldung");
		panelMitarbeiterReport.add(labelListMitarbeiterNichtBestaetigt, new UConstraints(2, 0));
		
		tablePlaetzeProMitarbeiter = new UTable();
		
		scrollTablePlaetzeProMitarbeiter = new JScrollPane();
		scrollTablePlaetzeProMitarbeiter.setPreferredSize(new Dimension(400, 200));
		scrollTablePlaetzeProMitarbeiter.setViewportView(tablePlaetzeProMitarbeiter);
		panelMitarbeiterReport.add(scrollTablePlaetzeProMitarbeiter, new UConstraints(0, 1, UConstraints.NONE));
		
		listMitarbeiterBestaetigt = new JList<String>();
		listMitarbeiterBestaetigt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollListMitarbeiterBestaetigt= new JScrollPane();
		scrollListMitarbeiterBestaetigt.setPreferredSize(new Dimension(200, 200));
		scrollListMitarbeiterBestaetigt.setViewportView(listMitarbeiterBestaetigt);
		panelMitarbeiterReport.add(scrollListMitarbeiterBestaetigt, new UConstraints(1, 1));
		
		
		listMitarbeiterNichtBestaetigt = new JList<String>();
		listMitarbeiterNichtBestaetigt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollListMitarbeiterNichtBestaetigt= new JScrollPane();
		scrollListMitarbeiterNichtBestaetigt.setPreferredSize(new Dimension(200, 200));
		scrollListMitarbeiterNichtBestaetigt.setViewportView(listMitarbeiterNichtBestaetigt);
		panelMitarbeiterReport.add(scrollListMitarbeiterNichtBestaetigt, new UConstraints(2, 1));
		
		
		// Packen
		
		pack();
	}



	public JComboBox<String> getJComboBoxEingabeUhrzeit() {
		return jComboBoxEingabeUhrzeit;
	}



	public UTextField getTextFieldZeitAendern() {
		return textFieldZeitAendern;
	}



	public UTextField getTextFieldDatumAendern() {
		return textFieldDatumAendern;
	}



	public UTextField getTextFieldSchiffAenern() {
		return textFieldSchiffAenern;
	}



	public UTextField getTextFieldRouteAendern() {
		return textFieldRouteAendern;
	}



	public JComboBox<String> getjComboBoxMitarbeiter() {
		return jComboBoxMitarbeiter;
	}



	public UButton getButtonErstellenTour() {
		return buttonErstellenTour;
	}



	public UButton getButtonAbbrechenTour() {
		return buttonAbbrechenTour;
	}



	public UButton getButtonZuweisenPlaetze() {
		return buttonZuweisenPlaetze;
	}



	public UButton getButtonAbbrechenPlaetze() {
		return buttonAbbrechenPlaetze;
	}



	public JList<String> getListRoute() {
		return listRoute;
	}



	public JList<String> getListSchiffe() {
		return listSchiffe;
	}



	public JList<String> getListMitarbeiterBestaetigt() {
		return listMitarbeiterBestaetigt;
	}



	public JList<String> getListMitarbeiterNichtBestaetigt() {
		return listMitarbeiterNichtBestaetigt;
	}



	public UTable getTableVorhandeneTouren() {
		return tableVorhandeneTouren;
	}



	public UTable getTablePlaetzeProMitarbeiter() {
		return tablePlaetzeProMitarbeiter;
	}
	
	public JDatePickerImpl getDatePicker() {
		return datePicker;
	}

	public JDatePanelImpl getDatePanel() {
		return datePanel;
	}

}