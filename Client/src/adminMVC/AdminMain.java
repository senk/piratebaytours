package adminMVC;

public class AdminMain {
	public static void main(String[] args) {
		AdminModel modelGUI = new AdminModel();
		AdminView viewGUI = new AdminView();
		
		
		@SuppressWarnings("unused")
		AdminController controllerGUI = new AdminController(modelGUI, viewGUI);
	
	}
}
