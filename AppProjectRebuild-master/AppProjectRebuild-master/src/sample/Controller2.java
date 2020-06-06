package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.Font;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;


public class Controller2 implements Initializable {



    //TableView konfiguracja
    @FXML private TableView<Person> tableView;
    @FXML private TableColumn<Person, String> firstNameColumn;
    @FXML private TableColumn<Person, String> lastNameColumn;
    @FXML private TableColumn<Person, String> birthdayColumn;
    @FXML private TableColumn<Person, String> statementColumn;
    @FXML private TableColumn<Person, String> salaryColumn;

    //Zmienne do tworzenia nowego obiektu Person
    @FXML private TextField firtNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField birthdayDatePicker;
    @FXML private TextField statementTextField;
    @FXML private TextField salaryTextField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //listaPracownikow.setItems(items);

        //TableView
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("birthday"));
        statementColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("statement"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("salary"));

        //laduje dane do metody
        loadDB();
        
        //Zaznaczanie wielu wierszy w tabeli
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    /**
     * Usuwa zaznaczone elementy z tabeli
     */
    public void deleteButtonPushed() {
        ObservableList<Person> selectedRows, allPeople;
        allPeople = tableView.getItems();

        //zaznaczone wiersze
        selectedRows = tableView.getSelectionModel().getSelectedItems();

        for(Person person: selectedRows) {
           ComunnicationDB.deletePerson(person);
        }
        loadDB();
        //petla do zaznaczonych wierszy
        /*for(Person person: selectedRows) {
            allPeople.remove(person);
        }*/
    }

    //Zwraca liste obiektu Person
    public void loadDB() {
        ObservableList<Person> people = FXCollections.observableArrayList();
        
        try {
			for(Person person: ComunnicationDB.getAllData()) {
				people.add(new Person(person.getFirstName(), person.getLastName(), person.getBirthday(), person.getStatement(), person.getSalary()));
			}
			tableView.setItems(people);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}   
    }

    // Tworzy nowy obiekt person w tabeli
    public void newPersonButtonPushed() {
        Person newPerson = new Person(firtNameTextField.getText(),
                                      lastNameTextField.getText(),
                                      birthdayDatePicker.getText(),
                                      statementTextField.getText(),
                                      salaryTextField.getText());
        if(validationAdd()) {
        	ComunnicationDB.addNewPerson(newPerson);
        	loadDB();	
        }else {
        	showWarning();
        }
        //Pobiera dane z tablicy jako lista, dodaje do new Person
        //tableView.getItems().add(newPerson);
    }

	private void showWarning() {
		if(firtNameTextField.getText().isEmpty()) {
			firtNameTextField.setText("Enter Value");
		    firtNameTextField.setStyle("-fx-text-inner-color: red;");
		    firtNameTextField.setOnMouseClicked(e -> {
		    	firtNameTextField.setText("");
			    firtNameTextField.setStyle("-fx-text-inner-color: gray;");
		    });
		    firtNameTextField.setOnKeyPressed(e -> {
		    	firtNameTextField.setText("");
		    	firtNameTextField.setStyle("-fx-text-inner-color: gray;");
		    });
		}
		if(lastNameTextField.getText().isEmpty()) {
			lastNameTextField.setText("Enter Value");
			lastNameTextField.setStyle("-fx-text-inner-color: red;");
			lastNameTextField.setOnMouseClicked(e -> {
				lastNameTextField.setText("");
				lastNameTextField.setStyle("-fx-text-inner-color: gray;");
		    });
			lastNameTextField.setOnKeyPressed(e -> {
				lastNameTextField.setText("");
				lastNameTextField.setStyle("-fx-text-inner-color: gray;");
		    });
		}
		if(birthdayDatePicker.getText().isEmpty()) {
			birthdayDatePicker.setText("Enter Value");
			birthdayDatePicker.setStyle("-fx-text-inner-color: red;");
			birthdayDatePicker.setOnMouseClicked(e -> {
				birthdayDatePicker.setText("");
				birthdayDatePicker.setStyle("-fx-text-inner-color: gray;");
		    });
			birthdayDatePicker.setOnKeyPressed(e -> {
				birthdayDatePicker.setText("");
				birthdayDatePicker.setStyle("-fx-text-inner-color: gray;");
		    });
		}
	}

	private boolean validationAdd() {
		if(firtNameTextField.getText().isEmpty() || lastNameTextField.getText().isEmpty() || birthdayDatePicker.getText().isEmpty()
				|| firtNameTextField.getText().equals("Enter Value") || lastNameTextField.getText().equals("Enter Value") || birthdayDatePicker.getText().equals("Enter Value"))
			return false;
		else {
			setDefaultTextField();
			return true;
		}
	}

	private void setDefaultTextField() {
		firtNameTextField.setPromptText("Name");
		firtNameTextField.setStyle("-fx-text-inner-color: gray;");
		
		lastNameTextField.setPromptText("Surname");
		lastNameTextField.setStyle("-fx-text-inner-color: gray;");
	
		birthdayDatePicker.setPromptText("Birthday");
		birthdayDatePicker.setStyle("-fx-text-inner-color: gray;");
	}
}
