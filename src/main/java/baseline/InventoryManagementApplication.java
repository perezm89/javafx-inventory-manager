/**
 * Entry point for the JavaFX Inventory
 * Management application.
 *
 * Initializes and launches the primary UI.
 *
 * Author: Bryan Perez
 */
package baseline;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class InventoryManagementApplication extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        Parent root =
                FXMLLoader.load(Objects.requireNonNull(getClass().getResource("InventoryManagementApplication.fxml")));

        Scene scene = new Scene(root); // attach scene graph to scene
        stage.setTitle("InventoryManagementApplication"); // displayed in window's title bar

        stage.setResizable(true);
        stage.setScene(scene); // attach scene to stage
        stage.show(); // display the stage
    }


    public static void main(String[] args) {
        // create a TodoList object and call its start method
        launch(args);
    }
}