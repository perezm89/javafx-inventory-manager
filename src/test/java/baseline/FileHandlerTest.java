/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Bryan Perez
 */
package baseline;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {





    boolean fileCompare(File file1, File file2)  throws IOException{
        Scanner scanner1 = new Scanner(file1);
        Scanner scanner2 = new Scanner(file2);

        while(scanner1.hasNextLine() && scanner2.hasNextLine()) {
            String line1 = scanner1.nextLine();
            String line2 = scanner2.nextLine();
            if(!line1.contentEquals(line2)) {
                return false;
            }
        }
        return !scanner1.hasNextLine() && !scanner2.hasNextLine();

    }

    @Test
    void saveTsvFile() {
        List<Item> list = new ArrayList<>();

        Item data = new Item();
        data.setSerialNumber("a-sfd-sdf-42d");
        data.setName("car");
        data.setValue("$656.00");
        list.add(data);

        data = new Item();
        data.setSerialNumber("s-dfs-kkj-dlk");
        data.setName("wrench");
        data.setValue("$77.00");
        list.add(data);

        data = new Item();
        data.setSerialNumber("a-xxx-ccc-ddd");
        data.setName("phone");
        data.setValue("$1200.00");
        list.add(data);

        String errorMessage = null;
        boolean compare = false;
        File file = new File("docs/Test.txt");
        File sample = new File("docs/Sample.txt");
        try{
            FileHandler.saveList(list, file, "*.txt");
           compare = fileCompare(file, sample);
        }catch (Exception e) {
            errorMessage = e.getMessage();
        }
        assertNull(errorMessage);
        assertTrue(compare);

    }
    @Test
    void saveHtmlList() {
        List<Item> list = new ArrayList<>();

        Item data = new Item();
        data.setSerialNumber("A-xxx-xxd-xdf");
        data.setName("apple");
        data.setValue("$526.00");
        list.add(data);

        data = new Item();
        data.setSerialNumber("d-fg4-455-gdf");
        data.setName("carrot");
        data.setValue("$34.34");
        list.add(data);

        String errorMessage = null;
        boolean compare = false;
        File file = new File("docs/Test.html");
        File sample = new File("docs/Sample.html");
        try{
            FileHandler.saveList(list, file, "*.html");
            compare = fileCompare(file, sample);
        }catch (Exception e) {
            errorMessage = e.getMessage();
        }
        assertNull(errorMessage);
        assertTrue(compare);


    }

    @Test
    void saveJsonList() {
        List<Item> list = new ArrayList<>();

        Item data = new Item();
        data.setSerialNumber("a-sfd-sdf-42d");
        data.setName("car");
        data.setValue("$656.00");
        list.add(data);

        data = new Item();
        data.setSerialNumber("s-dfs-kkj-dlk");
        data.setName("wrench");
        data.setValue("$77.00");
        list.add(data);

        data = new Item();
        data.setSerialNumber("a-xxx-ccc-ddd");
        data.setName("phone");
        data.setValue("$1200.00");
        list.add(data);

        String errorMessage = null;
        boolean compare = false;
        File file = new File("docs/Test.json");
        File sample = new File("docs/Sample.json");
        try{
            FileHandler.saveList(list, file, "*.json");
            compare = fileCompare(file, sample);
        }catch (Exception e) {
            errorMessage = e.getMessage();
        }
        assertNull(errorMessage);
        assertTrue(compare);

    }

    @Test
    void loadTsvList() {
        File file = new File("docs/Sample.txt");
        String errorMessage = null;

        try {
            FileHandler.loadList(file, "*.txt");
        }catch(Exception e) {
            errorMessage = e.getMessage();
        }

        assertNull(errorMessage);

    }

    @Test
    void loadHtmlList() {
        File file = new File("docs/Sample.html");
        String errorMessage = null;

        try {
            FileHandler.loadList(file, "*.html");
        }catch(Exception e) {
            errorMessage = e.getMessage();
        }

        assertNull(errorMessage);

    }

    @Test
    void loadJsonList() {
        File file = new File("docs/Sample.json");
        String errorMessage = null;

        try {
            FileHandler.loadList(file, "*.json");
        }catch(Exception e) {
            errorMessage = e.getMessage();
        }

        assertNull(errorMessage);
    }
}