/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Bryan Perez
 */
package baseline;

import javafx.beans.property.SimpleStringProperty;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Item {
    private static final Pattern SERIAL_FORMAT = Pattern.compile("[a-zA-Z](-[0-9a-zA-Z]{3}){3}");
    private static final Pattern VALUE_FORMAT = Pattern.compile("\\$?([0-9]+(\\.[0-9]{2})?)");

    private SimpleStringProperty name;
    private SimpleStringProperty value;
    private SimpleStringProperty serialNumber;

    public String getValue() {
       //check if value is null
        //if so initialize to new SimpleStringProperty empty quotes

        //return value
        return null;
    }

    public void setValue(String value) throws IllegalArgumentException {
        //create a Matcher variable
        //call the matcher function within VALUE_FORMAT and pass value
        //set this equal to matcher type created

        //check if matcher matches
        //if so parse to double
        //create a DecimalFormat instance and format it appropriately
        //then set value to a new instances of SimpleStringProperty while passing the parsed value to it
        //else throw IllegalArgumentException
    }

    public String getName() {
        //check if value is null
        //if so initialize to new SimpleStringProperty empty quotes

        //return name
        return null;
    }

    public void setName(String name) {
        //set name to SimpleStringProperty while passing name variable to it
    }

    public void setSerialNumber(String serialNumber) throws IllegalArgumentException {
        //create a Matcher variable
        //call the matcher function within SERIAL_FORMAT and pass value
        //set this equal to matcher type created

        //check if matcher matches
        //then set SerialNumber to a new instances of SimpleStringProperty serialNumber variable to it
        //else throw IllegalArgumentException

    }

    public String getSerialNumber() {
        //check if value is null
        //if so initialize to new SimpleStringProperty empty quotes

        //return serialNumber
        return null;
    }
}
