/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Bryan Perez
 */
package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;

public class InventoryManagementApplicationController implements Initializable {

    @FXML
    private TextField searchSerialNumberListInput;

    @FXML
    private TextField nameText;

    @FXML
    private TextField searchNameListInput;

    @FXML
    private TextField serialNumberText;

    @FXML
    private TextField valueText;

    @FXML
    private TableView<Item> todoTable;

    @FXML
    private Label errorMessage;

    @FXML
    private TableColumn<Item, String> serialNumberColumn;

    @FXML
    private TableColumn<Item, String> valueColumn;

    @FXML
    private TableColumn<Item, String> nameColumn;

    ObservableList<Item> myList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public boolean validSerialNumberEntry(String value) {
        return false;
    }

    private boolean isSerialNumberDuplicate(String text) {

        return false;
    }

    public boolean validNameEntry(int length) {
        return false;
    }

    public boolean validValueEntry(String value) {
        return true;
    }

    private boolean parseToDoubleCheck(String value) {
        return false;
    }

    @FXML
    void addItemClicked(ActionEvent event) {
    }

    @FXML
    void loadClicked(ActionEvent event) {
    }

    @FXML
    void saveClicked(ActionEvent event) {
    }

    @FXML
    void removeItemClicked(ActionEvent event) {
    }

    @FXML
    public void clearAllClicked(ActionEvent actionEvent) {
    }

    @FXML
    public void nameEditTable(TableColumn.CellEditEvent<Item, String> itemStringCellEditEvent) {
    }

    @FXML
    public void valueEditTable(TableColumn.CellEditEvent<Item, String> itemStringCellEditEvent) {
    }

    @FXML
    public void serialNumberEditTable(TableColumn.CellEditEvent<Item, String> itemStringCellEditEvent) {
    }

    public void searchSerialNumbersColumn(String newValue, String oldValue) {
    }

    public void searchNameColumn(String newValue, String oldValue) {
    }
}
