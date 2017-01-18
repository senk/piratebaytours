package buchungenMVC;

public class BuchungenMain {
	public static void main(String[] args) {
		BuchungenModel modelGUI = new BuchungenModel();
		BuchungenView viewGUI = new BuchungenView();
		
		
		@SuppressWarnings("unused") 
		BuchungenController controllerGUI = new BuchungenController(modelGUI, viewGUI);
	
	}
}
