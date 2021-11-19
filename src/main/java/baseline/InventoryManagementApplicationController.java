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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.List;
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
        //call the addListener function to searchSerialNumberListInput
        // Use lambda expression to changeListener in searchSerialNumberColumn pass the oldValue, newValue as parameters.

        //call the addListener function to searchNameListInput
        // Use lambda expression to changeListener in searchNameColumn pass the oldValue, newValue as parameters.

        //call the setCellValueFactory function from nameColumn
        //create a new PropertyValueFactory
        //call the setCellFactory function from nameColumn

        //call the setCellValueFactory function from valueColumn
        //create a new PropertyValueFactory
        //call the setCellFactory function from valueColumn

        //call the setCellValueFactory function from serialNumberColumn
        //create a new PropertyValueFactory
        //call the setCellFactory function from serialNumberColumn

        //set todotable's edit table to true.
        //pass myList as to todoTable's setItems
    }

    public boolean validSerialNumberEntry(String value) {

        //check if value is empty
        //if so display message to GUI and return false

        //create a new Item instance

        //create a try catch block to catch illArgument exception from Item class
        //If caught return false

        //call the isSerialNumberDuplicate
        //if it is duplicate return false


        //else return true
        return true;
    }

    private boolean isSerialNumberDuplicate(String text) {

        //verify myList is not empty
        //loop each item in myList
        //compare to see if any items match
        //if so return true

        //else return false
        return false;
    }

    public boolean validNameEntry(int length) {

        //check to see if length is between 2 and 256
        //if so return true
        //else return false

        return false;

    }

    public boolean validValueEntry(String value) {

        //check if value is empty
        //if so return false

        //create a double variable

        //call the parseToDoubleCheck function
        //if it is true parse the and store in variable created
        //also if true check to make sure the value is a positive number
        //if not return false

        //else return true
        return true;
    }

    private boolean parseToDoubleCheck(String value) {

        //create a try and catch block to parse a double
        //return false if caught

        //else return true
        return true;
    }

    public void displayErrorMessage(String message)
    {
        //set controller label errorMessage to message
        //set the text to color red
    }

    @FXML
    void addItemClicked(ActionEvent event) {

        //place the whole function in a try and catch block
        //create a new item instance
        //create a checker to make sure myList doesn't exceed 1024
        //call the validNameEntry,validValueEntry, and validSerialNumberEntry
        //if they all return true call the set functions for all three

    }

    @FXML
    void loadClicked(ActionEvent event) {

        //create a filChooser
        //call the function getExtensionFilters and create new ExtensionFilters for all three extensions
        //create a stage and cast todotable's getScene method to it

        //create a file and set it equal to filechooser's showOpenDialog method and pass stage to it

        //make sure file is not null
        //make sure you create a try and catch block

    }

    @FXML
    void saveClicked(ActionEvent event) {

        //create a filChooser
        //call the function getExtensionFilters and create new ExtensionFilters for all three extensions
        //create a stage and cast todotable's getScene method to it
        //create a file and set it equal to filechooser's showOpenDialog method and pass stage to it
        //make sure file is not null
        //make sure you create a try and catch block
    }

    @FXML
    void removeItemClicked(ActionEvent event) {
        //call myList's remove function
        //refresh todotable by call its function
    }

    @FXML
    public void clearAllClicked(ActionEvent actionEvent) {
        //call mylist's clear function
        //reset the errorMessage
    }

    @FXML
    public void nameEditTable(TableColumn.CellEditEvent<Item, String> itemStringCellEditEvent) {
        //create an Item
        //call todoTable get item function and set it equal to created item
        //call the length function from itemStringCellEditEvent
        //pass the length to validNameEntry
        //if true set the value
        //else refresh no matter what outcome it is
    }

    @FXML
    public void valueEditTable(TableColumn.CellEditEvent<Item, String> itemStringCellEditEvent) {
        //create an Item
        //call todoTable get item function and set it equal to created item

        //create a try and catch block
        //call the setValue from Item Class
        //refresh todoTable
    }

    @FXML
    public void serialNumberEditTable(TableColumn.CellEditEvent<Item, String> itemStringCellEditEvent) {
        //create an Item
        //call todoTable get item function and set it equal to created item

        //call the validSerialNumberEntry
        //if so set the value
        //else refresh no matter what the outcome is
    }

    public void searchSerialNumbersColumn(String newValue, String oldValue) {
        //create an ObservableList of Item
        //check to see if the controller searchSerialNumberListInput
        //or if newValue's length is less than oldValue length
        //if so pass myList to todoTable
        //else set newValue to Uppercase
        //loop through items in todoTable
        //continuously call getSerialNumber
        //store value in a variable
        //check to see if it contains newValue
        //if so add it to observableList
        //then add that observable list to todoTable

    }

    public void searchNameColumn(String newValue, String oldValue) {
        //create an ObservableList of Item
        //check to see if the controller searchNameListInput
        //or if newValue's length is less than oldValue length
        //if so pass myList to todoTable
        //else set newValue to Uppercase
        //loop through items in todoTable
        //continuously call getName
        //store value in a variable
        //check to see if it contains newValue
        //if so add it to observableList
        //then add that observable list to todoTable

    }
}
