package dad.javafx.enviar;


import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application{

	private EnviarController control;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		control=new  EnviarController();		
		
		primaryStage.setTitle("Enviar email");
		primaryStage.getIcons().add(new Image("source\\email-icon.png"));
		primaryStage.setScene(new Scene(control.getView()));
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
    public static void confirm(String header, String headerContent) {
		Alert alerta=new Alert(AlertType.INFORMATION);
		alerta.setTitle(header);
		alerta.setHeaderText(headerContent);
		alerta.showAndWait();
	}
    public static void reject(String titulo,String header, String content) {
  		Alert alerta=new Alert(AlertType.ERROR);
  		alerta.setTitle(titulo);
  		alerta.setHeaderText(header);
  		alerta.setContentText(content);
  		alerta.showAndWait();
  	}
}