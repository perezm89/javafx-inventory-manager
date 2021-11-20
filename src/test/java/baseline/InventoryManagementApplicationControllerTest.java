/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Bryan Perez
 */
package baseline;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryManagementApplicationControllerTest {

    @Test
    void validSerialNumberEntry() {
        InventoryManagementApplicationController app = new InventoryManagementApplicationController();
        String valid = "a-xxx-xxx-xxx";
        String inValid = "A-s#d-dff-ggg";

        assertFalse(app.validSerialNumberEntry(inValid));
        assertTrue(app.validSerialNumberEntry(valid));
    }

    @Test
    void validNameEntry() {
        InventoryManagementApplicationController app = new InventoryManagementApplicationController();
        int inValid = 1025;
        int valid = 25;

        assertFalse(app.validNameEntry(inValid));
        assertTrue(app.validNameEntry(valid));
    }

    @Test
    void validValueEntry() {
        InventoryManagementApplicationController app = new InventoryManagementApplicationController();
        String decimalPlaces = "12345.00";
        String currencyFormat = "$100.00";
        String negativeNumbers = "-262.23";

        assertTrue(app.validValueEntry(decimalPlaces));
        assertTrue(app.validValueEntry(currencyFormat));
        assertFalse(app.validValueEntry(negativeNumbers));

    }
}