package FinalProject;

import FinalProject.game.Board;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class. Runs controller.
 */
public class Game extends Application {
    /**
     * Opens GUI
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1500, 1000));
        primaryStage.show();
    }

    /**
     * Main.
     * @param args Arguments.
     */
    public static void main (String[] args) { launch(args);}
}
