package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent fxml = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Database Inspector");
        Scene scene = new Scene(fxml);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
        primaryStage.setMinWidth(1100);
        primaryStage.setMinHeight(600);
    }
    public static void main(String[] args) {
    	DBConnect.runDateBase();
    	ComunnicationDB.init(DBConnect.getConnection());
        launch(args);
        ComunnicationDB.closeStatement();
        DBConnect.closeConnect();
    }
}
