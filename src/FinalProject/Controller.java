package FinalProject;

import FinalProject.game.Board;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {

    public TextArea NotationList;
    public Button first;
    public ImageView testovaci;

    Board board = new Board(8);
    Chess chess = new Chess(board);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Scanner s = new Scanner(new File("./input.txt")).useDelimiter("\\n+");
            while (s.hasNext()) {
                NotationList.appendText(s.next() + "\n"); // else read the next token
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }

        File file = new File("src/images/pb.png");
        Image image = new Image(file.toURI().toString());
        testovaci.setImage(image);

    }

    public void startTest(ActionEvent actionEvent) {

    }
}

