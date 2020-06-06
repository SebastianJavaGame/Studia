package sample;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class Controller {

    private String login = "";
    private String haslo = "";

    @FXML
    Button helpButton;
    @FXML
    Button startApp;
    @FXML
    TextField loginField;
    @FXML
    PasswordField password;
    @FXML
    Label notif;
    @FXML
    AnchorPane rootPane;

    public void helpButtonAction(javafx.event.ActionEvent event) throws IOException {
    	URL path = ClassLoader.getSystemResource("sample/help.txt");
    	if(path==null) {
    	     System.out.println("File doesn't exist");
    	}
        File file = new File(path.getFile());
        Desktop desktop = Desktop.getDesktop();
        desktop.open(file);
    }

    public void startButtonAction(javafx.event.ActionEvent event) throws IOException {
        if(loginField.getText().equals(login) && password.getText().equals(haslo)) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("sample2.fxml"));
            rootPane.getChildren().setAll(pane);

        }
        else notif.setText("Blad logowania");
    }

}
