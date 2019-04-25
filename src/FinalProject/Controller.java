package FinalProject;

import FinalProject.game.Board;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {

    public TextArea Text;
    public Button first;

    Board board = new Board(8);
    Chess chess = new Chess(board);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Scanner s = new Scanner(new File("./input.txt")).useDelimiter("\\n+");
            while (s.hasNext()) {
                Text.appendText(s.next() + "\n"); // else read the next token
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
    }

    public void startTest(ActionEvent actionEvent) {

    }
}

