/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Bryan Perez
 */
package baseline;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FileHandler {

    private FileHandler() {
    }

    public static void saveList(List<Item> listItems, File file, List<String> extensions) throws IOException {
        //create a switch statement verifies the file extension used
    }

    public static List<Item> loadList(File file, List<String> extensions) throws  ParseException, IOException {
        //create a List of Item
        //create a switch statement which assigned to the created List the correct file extension used

        //return the list
        return null;
    }


    public static void saveTsvFile(List<Item> listItems, File file)  {
        //create a try and catch block
        //In the try parameter instantiate and a fileWriter and pass a file to it
        //hard code the header format
        //loop through the items in the listItems a new list
        //call the get functions and add to the new created list

    }

    public static void saveHtmlList(List<Item> listItems, File file) throws IOException {
        //create a file instance and pass the path to the created html template
        //create a list of String set it equal to file's method readAllLines method
        //create a List of String
        //loop through items in listItems
        //store in a string variable the rows
        //then add to the created list of Strings
        //then use the replace method in String class and replace the placeholder
    }

    public static void saveJsonList(List<Item> listItems, File file) throws IOException {
        //create a List of Map of Strings and Strings
        //loop through items in ListItems
        //call all three get methods and match with the string literal
    }


    private static String generateHtmlRow(Item item) {
        //return a modifiable skeleton of what is actually changing in the file
        return null;
    }

    public static List<Item> loadTsvList(File file) throws FileNotFoundException, ParseException {
        //create a counter
        // create a List of Item
        //create a scanner
        //increase the counter
        //check if the first line is equal to the header
        //else trow exception
        //loop while there is a next line

        //return newList

        return null;
    }

    private static List<Item> loadHtmlList(File file) throws IOException {
        //create a list of String set it equal to file's method readAllLines method
        //use Jsoup to parse
        //loop through and edit as necessary

       // return newList
        return null;
    }

    private static List<Item> loadJsonList(File file) {
        //create a List of item
        //create Object array
        //create a try and catch block
        //instantiate a new gson
        //parse using gson and save in object array
        //loop through each object and save and cast into Map

        //return items
        return null;
    }

}
