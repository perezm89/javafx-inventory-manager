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
        if(value == null) {
            this.value = new SimpleStringProperty("");
        }
        return value.get();
    }

    public void setValue(String value) throws IllegalArgumentException {
        Matcher matchString = VALUE_FORMAT.matcher(value);

        if(matchString.matches()) {
            double doubleValue =  Double.parseDouble(matchString.group(1));
            DecimalFormat decFormat = new DecimalFormat("$0.00");

            this.value = new SimpleStringProperty(decFormat.format(doubleValue));
        }
        else {
            throw new IllegalArgumentException("Value must be expressed as a whole number or decimal rounded to two decimal places");
        }
    }

    public String getName() {
        if(name == null) {
            this.name = new SimpleStringProperty("");
        }
        return name.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public void setSerialNumber(String serialNumber) throws IllegalArgumentException {
        Matcher matchString = SERIAL_FORMAT.matcher(serialNumber);
        if(matchString.matches()) {
            this.serialNumber = new SimpleStringProperty(serialNumber);
        }
        else {
            throw new IllegalArgumentException("serial number must be in the format: A-XXX-XXX-XXX");
        }
    }

    public String getSerialNumber() {
        if(serialNumber == null) {
            this.serialNumber= new SimpleStringProperty("");
        }
        return serialNumber.get();
    }
}
