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
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }
    public static void main (String[] args) {
        launch(args);
        Board board = new Board(8);
        Chess chess = new Chess(board);

        //Filip test===================================u666
//        chess.printBoard();
//
//        System.out.println("==========================================================");
//
//        BoardField boardField = board.field[0][2];
//        UniversalFigure figure = board.field[0][1].get();
//        //chess.move(figure, boardField);
//
//        //chess.printBoard();
//
//        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
//        //chess.restart();
//        //chess.print();
//
//        chess.manualMove(figure,boardField);
//        chess.printBoard();
//        System.out.println("Notace");
        //chess.printNotation();
        //
        //testik
        //
        ///
        //
        //

        if(args.length != 1) {
            System.err.println("Dej si do Run->Edit Configuration->Program arguments -> ./input.txt");
            System.exit(1);
        }

        //System.out.println(args[0]);

        chess.printBoardReadable();

        chess.parseNotations(args[0]);
        //chess.debugNotation();
        //chess.clearListFrom(1);
        //chess.debugNotation();
        chess.printNotation();

        //chess.debugNotation();

        //chess.next();

        //chess.board.board[0][0].setFigure(null);

        //chess.printBoardReadable();

        for(int i = 0; i < chess.getMoves().size(); i ++){
            chess.nextMove();
        }
        //chess.next();
        //chess.printBoardReadable();
        //chess.next();
        //chess.printBoardReadable();
        //chess.next();
        chess.printBoardReadable();
        //==============================================


    }
}
