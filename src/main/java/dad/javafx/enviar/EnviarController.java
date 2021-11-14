package dad.javafx.enviar;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class EnviarController implements Initializable{
	//model
	private Email email=new SimpleEmail();
	
	StringProperty servidor=new SimpleStringProperty(); 
	StringProperty puerto=new SimpleStringProperty(); 
	StringProperty mensaje=new SimpleStringProperty(); 
	StringProperty destinatario=new SimpleStringProperty(); 
	StringProperty asunto=new SimpleStringProperty(); 
	StringProperty remitente=new SimpleStringProperty(); 
	StringProperty contrasena=new SimpleStringProperty(); 
	BooleanProperty conexion=new SimpleBooleanProperty();
	//view

	@FXML
    private GridPane gridpane;

    @FXML
    private TextField servidorText;

    @FXML
    private TextField puertoText;

    @FXML
    private CheckBox conexionCheckbox;

    @FXML
    private Button enviarButt;

    @FXML
    private Button vaciarButt;

    @FXML
    private Button cerrarButt;

    @FXML
    private TextArea mensajeTextarea;

    @FXML
    private TextField destinatarioText;

    @FXML
    private TextField asuntoText;

    @FXML
    private TextField remitenteText;

    @FXML
    private TextField contrasenaText;


 	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	servidor.bindBidirectional(servidorText.textProperty());
	puerto.bindBidirectional(puertoText.textProperty());
	conexion.bindBidirectional(conexionCheckbox.selectedProperty());
	remitente.bindBidirectional(remitenteText.textProperty());
	contrasena.bindBidirectional(contrasenaText.textProperty());
	destinatario.bindBidirectional(destinatarioText.textProperty());
	asunto.bindBidirectional(asuntoText.textProperty());
	mensaje.bindBidirectional(mensajeTextarea.textProperty());;
	}
	
 	public EnviarController() throws IOException {
 		FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/view.fxml"));
 		loader.setController(this);
 		loader.load();		
 	}
    @FXML
    void OnCerrarAction(ActionEvent event) {
    	Platform.exit();
    }

    @FXML
    void OnEnviarAction(ActionEvent event) throws EmailException {

    try {
       	email.setHostName(servidor.getValue());
    	email.setSmtpPort(Integer.parseInt(puerto.getValue()));
    	email.setAuthentication(remitente.getValue(), contrasena.getValue());
    	email.setSSLOnConnect(conexion.getValue());
    	email.setFrom(remitente.getValue());
    	email.addTo(destinatario.getValue());
    	email.setSubject(asunto.getValue());
    	email.setMsg(mensaje.getValue());
    	email.send();
    	App.confirm("Mensaje enviado","Mensaje enviado correctamente a "+ destinatario.getValue());	
	} catch (Exception e) {
		App.reject("Mensaje no enviado","No se pudo enviar el email","Invalid message supplied");	
	}
    	}

	


    @FXML
    void OnVaciarAction(ActionEvent event) {
    	servidor.setValue("");
    	puerto.setValue("");
    	conexion.setValue(false);
    	remitente.setValue("");
    	contrasena.setValue("");
    	destinatario.setValue("");
    	asunto.setValue("");
    	mensaje.setValue("");
    }
 	public GridPane getView() {
		return gridpane;
	}


}
