/**
 * Unit tests for inventory management
 * application logic and validation.
 *
 * Author: Bryan Perez
 */
package baseline;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryManagementApplicationControllerTest {

    InventoryManagementApplicationController app = new InventoryManagementApplicationController();

    @Test
    void inventoryItemsControlCountHelper() {
        int validSize = 25;
        int inValidSize = 1025;

        String result1 = app.inventoryItemsControlCountHelper(validSize);
        String expected1 = "true";

        String result2 = app.inventoryItemsControlCountHelper(inValidSize);
        String expected2 = "Items must not exceed 1024";

        assertEquals(expected1, result1);
        assertEquals(expected2, result2);

    }

    @Test
    void validSerialNumberEntryHelper() {

        String valid = "a-xxx-xxx-xxx";
        String inValid = "A-sd@-dff-ggg";

        String result1 = app.validSerialNumberEntryHelper(valid);

        String expected1 = "true";
        assertEquals(expected1, result1);

        String result2 = null;

        try {
            app.validSerialNumberEntryHelper(inValid);
        } catch (IllegalArgumentException ignored) {
            result2 = "errorMessage";
        }

        String expected2 = "errorMessage";
        assertEquals(expected2,result2);

    }

    @Test
    void validNameEntryHelper() {
        int inValid = 1025;
        int valid = 25;

        assertFalse(app.validNameEntryHelper(inValid));
        assertTrue(app.validNameEntryHelper(valid));
    }

    @Test
    void validValueEntryHelper() {
        String decimalPlaces = "12345.00";
        String currencyFormat = "$100.00";
        String negativeNumbers = "-262.23";

        String result1 = app.validValueEntryHelper(decimalPlaces);
        String expected1 = "true";

        String result2 = app.validValueEntryHelper(currencyFormat);
        String expected2 = "true";

        String result3 = app.validValueEntryHelper(negativeNumbers);
        String expected3 = "Value must be number greater than or equal to 0";

        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
        assertEquals(expected3, result3);

    }
}