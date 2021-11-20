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
    public Label errorMessage;

    @FXML
    private TableColumn<Item, String> serialNumberColumn;

    @FXML
    private TableColumn<Item, String> valueColumn;

    @FXML
    private TableColumn<Item, String> nameColumn;

    ObservableList<Item> myList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        searchSerialNumberListInput.textProperty().addListener((observable, oldValue, newValue) -> searchSerialNumbersColumn(newValue, oldValue));
        searchNameListInput.textProperty().addListener((observable, oldValue, newValue) -> searchNameColumn(newValue, oldValue));

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        valueColumn.setCellValueFactory(new PropertyValueFactory<>("Value"));
        valueColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        serialNumberColumn.setCellValueFactory(new PropertyValueFactory<>("SerialNumber"));
        serialNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        todoTable.setEditable(true);
        todoTable.setItems(myList);
    }

    public String validSerialNumberEntryHelper(String value) throws IllegalArgumentException{
        //This function was created to separate testable logic from JavaFX controller object errorMessage
        if(value.isEmpty()) {
            return "Must enter a serial number";
        }
        Item item = new Item();

        item.setSerialNumber(value);

        if(isSerialNumberDuplicate(value)) {
            return "Serial number already exists inventory";
        }
       return "true";
    }

    public boolean validSerialNumberEntry(String value) {

        try {
           String message = validSerialNumberEntryHelper(value);
           if(!message.equalsIgnoreCase("")) {
               displayErrorMessage(message);
               return false;
           }
        } catch (IllegalArgumentException e) {
            displayErrorMessage(e.getMessage());
            return false;
        }

        return true;
    }

    private boolean isSerialNumberDuplicate(String text) {

        if(!myList.isEmpty()) {
            for (Item value : myList) {
                if (text.equalsIgnoreCase(value.getSerialNumber())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean validNameEntryHelper(int length ) {
        //This function was created to separate testable logic from JavaFX controller object errorMessage
        return length >= 2 && length <= 256;
    }
    public boolean validNameEntry(int length) {
        if (validNameEntryHelper(length)) {
            return true;
        }
        else {
            displayErrorMessage("Name must be between 2 and 256 characters in length");
            return false;
        }
    }
    public String validValueEntryHelper(String value) {
        //This function was created to separate testable logic from JavaFX controller object errorMessage
        if(value.isEmpty()) {
            return "Must enter a value";
        }
        double testParsedDouble;
        String result = parseToDoubleCheck(value);

        if(result.contentEquals("true")) {
            testParsedDouble  =  Double.parseDouble(value);
            if (testParsedDouble < 0) {
                return "Value must be number greater than or equal to 0";
            }
        }

        return "true";
    }

    public boolean validValueEntry(String value) {
        String result = validValueEntryHelper(value);

        if(!result.contentEquals("true")) {
            displayErrorMessage(result);
            return false;
        }
        return true;
    }

    private String parseToDoubleCheck(String value) {

        try {
            Double.parseDouble(value);

        }catch(IllegalArgumentException ex) {
            return "Value must be expressed as a whole number or decimal rounded to two decimal places";
        }

        return "true";
    }

    public void displayErrorMessage(String message)
    {
        errorMessage.setText(message);
        errorMessage.setTextFill(Color.RED);
    }

    @FXML
    void addItemClicked(ActionEvent event) {

        try {
            Item newItem = null;
            String value = valueText.getText().trim();
            String serialNumber = serialNumberText.getText().trim();
            //make sure user can create up-to 1024 items
            //1 List is already created prior to entering function
            if (myList.size() < 1024
                    && validNameEntry(nameText.getText().trim().length())
                    && validValueEntry(value)
                    && validSerialNumberEntry(serialNumber)) {
                newItem = new Item();
                newItem.setSerialNumber(serialNumber);
                newItem.setName(nameText.getText().trim());
                newItem.setValue((value));
            }
            if (newItem != null) {
                myList.add(newItem);
                todoTable.setItems(myList);

                //clean up input controls
                serialNumberText.setText("");
                nameText.setText("");
                valueText.setText("");
                errorMessage.setText("");
            }
        }
        catch (Exception ex) {
            displayErrorMessage(ex.getMessage());
        }
    }

    @FXML
    void loadClicked(ActionEvent event) {
        errorMessage.setText("");

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Tab separated Files", "*.txt"),
                new FileChooser.ExtensionFilter("HTML Files", "*.html"),
                new FileChooser.ExtensionFilter("JSON Files", "*.json"));

        Stage thisStage = (Stage) todoTable.getScene().getWindow();

        File file = fileChooser.showOpenDialog(thisStage);
        if (file != null) {
            try {
                List<String> extensions = fileChooser.getSelectedExtensionFilter().getExtensions();
                List<Item> newList = FileHandler.loadList(file, extensions.get(0));
                myList = FXCollections.observableArrayList(newList);
                todoTable.setItems(myList);

            }catch(FileNotFoundException e) {
                displayErrorMessage("File not found");
            }catch (ParseException e) {
                displayErrorMessage(e.getMessage());
            }catch (IOException ex) {
                displayErrorMessage("IOException error");
            }catch (IllegalArgumentException ex) {
                displayErrorMessage("File is in wrong format");
            }catch(IllegalStateException ie) {
                displayErrorMessage("wrong file extension selected");
            }catch(com.google.gson.JsonSyntaxException js) {
                displayErrorMessage("File not in Json format");
            }
        }
    }

    @FXML
    void saveClicked(ActionEvent event) {
        errorMessage.setText("");

        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Tab separated Files", "*.txt"),
                new FileChooser.ExtensionFilter("HTML Files", "*.html"),
                new FileChooser.ExtensionFilter("JSON Files", "*.json"));

        Stage thisStage = (Stage) todoTable.getScene().getWindow();

        File file = fileChooser.showSaveDialog(thisStage);
        if (file != null ) {
            try {
                List<String> extensions = fileChooser.getSelectedExtensionFilter().getExtensions();
                FileHandler.saveList(myList, file, extensions.get(0));

            } catch (IOException e) {
                displayErrorMessage("File not saved properly");
            }

        }
    }

    @FXML
    void removeItemClicked(ActionEvent event) {
        myList.remove(todoTable.getSelectionModel().getSelectedItem());
        todoTable.refresh();
    }

    @FXML
    public void clearAllClicked(ActionEvent actionEvent) {
        myList.clear();
        errorMessage.setText("");
    }

    @FXML
    public void nameEditTable(TableColumn.CellEditEvent<Item, String> itemStringCellEditEvent) {
        errorMessage.setText("");
        Item item = todoTable.getSelectionModel().getSelectedItem();
        int length = itemStringCellEditEvent.getNewValue().length();

        if(validNameEntry(length) ) {
            item.setName(itemStringCellEditEvent.getNewValue());
        }

        todoTable.refresh();
    }

    @FXML
    public void valueEditTable(TableColumn.CellEditEvent<Item, String> itemStringCellEditEvent) {
        errorMessage.setText("");
        Item item = todoTable.getSelectionModel().getSelectedItem();
        String value = itemStringCellEditEvent.getNewValue();

        try{
            item.setValue(value);
        }
        catch(IllegalArgumentException e){
            displayErrorMessage(e.getMessage());
        }

        todoTable.refresh();
    }

    @FXML
    public void serialNumberEditTable(TableColumn.CellEditEvent<Item, String> itemStringCellEditEvent) {
        errorMessage.setText("");
        Item item = todoTable.getSelectionModel().getSelectedItem();
        String value = itemStringCellEditEvent.getNewValue();

        if(validSerialNumberEntry(value)) {
            item.setSerialNumber(value);
        }

        todoTable.refresh();
    }

    public void searchSerialNumbersColumn(String newValue, String oldValue) {
        ObservableList<Item>  sortedList = FXCollections.observableArrayList();
        if(searchSerialNumberListInput == null || (newValue.length() < oldValue.length())) {
            todoTable.setItems(myList);
        }
        else{
            newValue = newValue.toUpperCase();
            for (Item item: todoTable.getItems()) {
                String searchSerialNumber = item.getSerialNumber();
                if(searchSerialNumber.toUpperCase().contains(newValue)) {
                    sortedList.add(item);
                }
            }
            todoTable.setItems(sortedList);
        }

    }

    public void searchNameColumn(String newValue, String oldValue) {
        ObservableList<Item> sortedList = FXCollections.observableArrayList();
        if (searchNameListInput == null || (newValue.length() < oldValue.length())) {
            todoTable.setItems(myList);
        } else {
            newValue = newValue.toUpperCase();
            for (Item item : todoTable.getItems()) {
                String searchName = item.getName();
                if (searchName.toUpperCase().contains(newValue)) {
                    sortedList.add(item);
                }
            }
            todoTable.setItems(sortedList);
        }

    }

}
