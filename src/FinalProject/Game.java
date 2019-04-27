package FinalProject;

import FinalProject.game.Board;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Game extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1500, 1000));
        primaryStage.show();
    }
    public static void main (String[] args) { launch(args);}
//    public static void main (String[] args) {
//        Board board = new Board(8);
//        Chess chess = new Chess(board);
//
//        if(args.length != 1) {
//            System.err.println("Dej si do Run->Edit Configuration->Program arguments -> ./input.txt");
//            System.exit(1);
//        }
//
//        chess.printBoard();
//        chess.loadFile(args[0]);
//        chess.printNotation();
//        for(int i = 0; i < chess.getMoves().size(); i ++){
//            chess.performMove();
//        }
//        chess.printBoardReadable();
//    }
}
