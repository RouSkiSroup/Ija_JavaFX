package FinalProject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class. Runs controller.
 * @author Filip Jerabek (xjerab24), Daniel Konecny (xkonec75)
 * @version 2.0
 */
public class Game extends Application {
    /**
     * Opens GUI
     * @param primaryStage  Primary stage of GUI
     * @throws Exception    When error occurs during loading of GUI.
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("gui.fxml"));
        primaryStage.setTitle("IJA - Chess");
        primaryStage.setScene(new Scene(root, 1500, 1000));
        primaryStage.show();
    }

    /**
     * Main.
     * @param args Arguments.
     */
    public static void main (String[] args) {
        launch(args);
    }
}
