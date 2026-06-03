/**
 * Handles loading and saving inventory
 * data in supported file formats.
 *
 * Author: Bryan Perez
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

    public static void saveList(List<Item> listItems, File file, String extensions) throws IOException {

        switch (extensions) {
            case "*.txt" -> saveTsvFile(listItems, file);
            case "*.html" -> saveHtmlList(listItems, file);
            case "*.json" -> saveJsonList(listItems, file);
            default -> throw new IllegalArgumentException("Invalid file extension");
        }
    }

    public static List<Item> loadList(File file, String extensions) throws  ParseException, IOException, IllegalStateException {
        List<Item> newList;
        switch (extensions) {
            case "*.txt" -> newList = loadTsvList(file);
            case "*.html" -> newList = loadHtmlList(file);
            case "*.json" -> newList = loadJsonList(file);
            default -> throw new IllegalArgumentException("Invalid file extension");
        }

        return newList;
    }


    public static void saveTsvFile(List<Item> listItems, File file)  {
        try(FileWriter output = new FileWriter(file)) {
            output.write("Serial Number\tName\tValue\n");

            for (Item myItem : listItems) {
                List<String> newList = new ArrayList<>();

                newList.add(myItem.getSerialNumber());
                newList.add(myItem.getName());
                newList.add(myItem.getValue());

                output.write(String.join("\t", newList) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveHtmlList(List<Item> listItems, File file) throws IOException {

        File template = new File("docs/Template.html");
        List<String> lines = Files.readAllLines(Paths.get(template.getAbsolutePath()), StandardCharsets.UTF_8);
        String stringContent = String.join(System.lineSeparator(), lines);

        List<String> rows = new ArrayList<>();

        for (Item item : listItems) {
            String row = generateHtmlRow(item);
            rows.add(row);
        }

        stringContent = stringContent.replace("        <!-- placeholder -->", String.join(System.lineSeparator(), rows));

        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(stringContent);


        fileWriter.close();
    }

    public static void saveJsonList(List<Item> listItems, File file) throws IOException {
        List<Map<String,String>> list = new ArrayList<>();

        for (Item item: listItems) {
            Map<String,String> tempMap = new HashMap<>();

            tempMap.put("SerialNumber", item.getSerialNumber());
            tempMap.put("Name", item.getName());
            tempMap.put("Value", item.getValue());
            list.add(tempMap);
        }

        try (FileWriter output = new FileWriter(file)) {
            Gson g = new GsonBuilder().create();
            g.toJson(list, output);
        }
    }


    private static String generateHtmlRow(Item item) {
        String td = "            <td>";
        String tdNewLine = "</td>\n";

        return "        <tr>\n" +
                td + item.getSerialNumber() +tdNewLine +
                td + item.getName() +tdNewLine +
                td + item.getValue() +tdNewLine +
                "        </tr>\n";
    }

    public static List<Item> loadTsvList(File file) throws FileNotFoundException, ParseException {
        int counter = 0;
        List<Item> newList = new ArrayList<>();

        Scanner input = new Scanner(file);
        String firstLine = input.nextLine();
        counter++;

        if(!firstLine.equals("Serial Number\tName\tValue")) {
            throw new ParseException("Invalid format", counter);

        }

        while (input.hasNextLine()) {
            String data = input.nextLine();
            counter++;
            String[] line  = data.split("\t");

            if(line.length > 0) {
                if (line.length == 3) {
                    Item myItem = new Item();
                    myItem.setName(line[1]);
                    myItem.setSerialNumber(line[0]);
                    myItem.setValue(line[2]);
                    newList.add(myItem);
                }
                else {
                    throw new ParseException("Formatting error", counter);
                }
            }
        }

        input.close();

        return newList;
    }

    private static List<Item> loadHtmlList(File file) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
        String html = String.join(System.lineSeparator(), lines);

        Document doc = Jsoup.parse(html);
        List<Item> newList = new ArrayList<>();
        Elements tables = doc.getElementsByTag("table");

        if(tables.isEmpty()) {
            throw new IllegalArgumentException("");
        }
        for (Element table: tables) {
            Elements trs = table.getElementsByTag("tr");
            for (Element tr: trs) {
                Elements tds = tr.getElementsByTag("td");
                if(tds.size() > 2) {
                    String name = tds.get(1).text();
                    String serialNumber = tds.get(0).text();
                    String value = tds.get(2).text();

                    Item item = new Item();
                    item.setName(name);
                    item.setSerialNumber(serialNumber);
                    item.setValue(value);

                    newList.add(item);
                }
            }
        }
        return newList;
    }

    private static List<Item> loadJsonList(File file) throws IllegalStateException, IOException {
        List<Item> items = new ArrayList<>();
        Object[] objects;


            Gson gson = new Gson();

            Reader reader = Files.newBufferedReader(Paths.get(file.getPath()));

            objects = gson.fromJson(reader, Object[].class);

            reader.close();

            for (Object obj : objects) {
                Map<String, String> map = (Map<String, String>) obj;
                Item item = new Item();
                item.setSerialNumber(map.get("SerialNumber"));
                item.setName(map.get("Name"));
                item.setValue(map.get("Value"));

                items.add(item);
            }

        return items;
    }

}
