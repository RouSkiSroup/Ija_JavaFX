package FinalProject;

import FinalProject.common.UniversalFigure;
import FinalProject.game.Board;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Controller implements Initializable {

    public TextArea NotationList;
    public Button next;
    public Button previous;
    public Button auto;
    public GridPane grid;
    public ImageView fA1;
    public ImageView fB1;
    public ImageView fA2;
    public ImageView fB2;
    public ImageView fA8;
    public ImageView fB8;
    public ImageView fC8;
    public ImageView fE8;
    public ImageView fF8;
    public ImageView fG8;
    public ImageView fH8;
    public ImageView fH1;
    public ImageView fH2;
    public ImageView fH3;
    public ImageView fH4;
    public ImageView fH5;
    public ImageView fH6;
    public ImageView fH7;
    public ImageView fG1;
    public ImageView fG2;
    public ImageView fG3;
    public ImageView fG4;
    public ImageView fG5;
    public ImageView fG6;
    public ImageView fG7;
    public ImageView fF1;
    public ImageView fF2;
    public ImageView fF3;
    public ImageView fF4;
    public ImageView fF5;
    public ImageView fF6;
    public ImageView fF7;
    public ImageView fE1;
    public ImageView fC1;
    public ImageView fE2;
    public ImageView fC2;
    public ImageView fE3;
    public ImageView fD3;
    public ImageView fC3;
    public ImageView fB3;
    public ImageView fA3;
    public ImageView fE4;
    public ImageView fE5;
    public ImageView fA7;
    public ImageView fB7;
    public ImageView fC7;
    public ImageView fD7;
    public ImageView fE7;
    public ImageView fD6;
    public ImageView fC6;
    public ImageView fB6;
    public ImageView fA6;
    public ImageView fD4;
    public ImageView fC5;
    public ImageView fC4;
    public ImageView fB5;
    public ImageView fB4;
    public ImageView fA5;
    public ImageView fA4;
    public ImageView fD2;
    public ImageView fD8;
    public ImageView fD1;
    public ImageView fE6;
    public ImageView fD5;
    public TextField sleepTm;
    public ListView notationList2;

    Board board;
    Chess chess;
    boolean stop;
    int sleepTime;
    Timer timer;
    TimerTask task;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.board = new Board(8);
        this.chess = new Chess(board);
        this.stop = false;
        this.sleepTime = 0;


        

//        try {
//            Scanner s = new Scanner(new File("./input.txt")).useDelimiter("\\n+");
//            while (s.hasNext()) {
//                //NotationList.appendText(s.next() + "\n"); // else read the next token
//                notationList2.getItems().addAll(s.next());
//            }
//        } catch (FileNotFoundException ex) {
//            System.err.println(ex);
//        }
        chess.loadFile("./input.txt");

        relodNotation();


        //Node bod = getNodeFromGridPane( 1, 1, grid);
        //testlab.setText(bod.getId());

        //testlab.setText(chess.board.board[0][0].getFigure().getType().name());

        fillBoard();



    }

    public void previousMove(ActionEvent actionEvent) {
        this.chess.positionMove(this.chess.getCounter()-1);
        this.fillBoard();
        this.updateNotationList();
    }

    public void nextMove(ActionEvent actionEvent) {
        chess.performMove();
        this.fillBoard();
        this.updateNotationList();
    }

    public void updateNotationList(){
        this.notationList2.getSelectionModel().select(chess.getCounter()-1);
    }

    public void autoMove(ActionEvent actionEvent) {
        this.task = new TimerTask()
        {
            public void run()
            {
                if (chess.performMove()){
                    notationList2.getSelectionModel().select(chess.getCounter()-1);
                    ImageView view;
                    File file;
                    Image image;
                    String imagePath;
                    for (int i = 1; i < 9; i ++){
                        for (int j = 1; j < 9; j ++){
                            view = getViewByIndex(i,j);
                            imagePath = getFigureImage(chess.board.board[i-1][j-1].getFigure());
                            file = new File(imagePath);
                            image = new Image(file.toURI().toString());
                            view.setImage(image);
                        }
                    }
                }
                else{
                    timer.cancel();
                    timer.purge();
                    return;
                }

            }

        };
        this.sleepTime = Integer.parseInt(sleepTm.getText());
        this.timer = new Timer();
        this.timer.schedule(this.task, 0, this.sleepTime * 1000);
    }

    public void stopMove(ActionEvent actionEvent) {
        this.timer.cancel();
        this.timer.purge();
    }

    public void fillBoard(){
        ImageView view;
        File file;
        Image image;
        String imagePath;
        for (int i = 1; i < 9; i ++){
            for (int j = 1; j < 9; j ++){
                view = getViewByIndex(i,j);
                imagePath = getFigureImage(chess.board.board[i-1][j-1].getFigure());
                file = new File(imagePath);
                image = new Image(file.toURI().toString());
                view.setImage(image);
            }
        }
    }

    public String getFigureImage(UniversalFigure figure){
        String res = "src/images/";
        if (figure != null) {
            res = "src/images/";
            res = res + figure.getType().name();
            if (figure.isWhite()) {
                res = res + "b";
            } else {
                res = res + "c";
            }
            res = res + ".png";
        }
        else{
            res = res + "trans.png";
        }
        return res;

    }

    public ImageView getViewByIndex(int col, int row){
        String merge = Integer.toString(col) + Integer.toString(row);
        int res = Integer.parseInt(merge);
        switch (res){
            case (11):
                return fA1;
            case (12):
                return fA2;
            case (13):
                return fA3;
            case (14):
                return fA4;
            case (15):
                return fA5;
            case (16):
                return fA6;
            case (17):
                return fA7;
            case (18):
                return fA8;
            case (21):
                return fB1;
            case (22):
                return fB2;
            case (23):
                return fB3;
            case (24):
                return fB4;
            case (25):
                return fB5;
            case (26):
                return fB6;
            case (27):
                return fB7;
            case (28):
                return fB8;
            case (31):
                return fC1;
            case (32):
                return fC2;
            case (33):
                return fC3;
            case (34):
                return fC4;
            case (35):
                return fC5;
            case (36):
                return fC6;
            case (37):
                return fC7;
            case (38):
                return fC8;
            case (41):
                return fD1;
            case (42):
                return fD2;
            case (43):
                return fD3;
            case (44):
                return fD4;
            case (45):
                return fD5;
            case (46):
                return fD6;
            case (47):
                return fD7;
            case (48):
                return fD8;
            case (51):
                return fE1;
            case (52):
                return fE2;
            case (53):
                return fE3;
            case (54):
                return fE4;
            case (55):
                return fE5;
            case (56):
                return fE6;
            case (57):
                return fE7;
            case (58):
                return fE8;
            case (61):
                return fF1;
            case (62):
                return fF2;
            case (63):
                return fF3;
            case (64):
                return fF4;
            case (65):
                return fF5;
            case (66):
                return fF6;
            case (67):
                return fF7;
            case (68):
                return fF8;
            case (71):
                return fG1;
            case (72):
                return fG2;
            case (73):
                return fG3;
            case (74):
                return fG4;
            case (75):
                return fG5;
            case (76):
                return fG6;
            case (77):
                return fG7;
            case (78):
                return fG8;
            case (81):
                return fH1;
            case (82):
                return fH2;
            case (83):
                return fH3;
            case (84):
                return fH4;
            case (85):
                return fH5;
            case (86):
                return fH6;
            case (87):
                return fH7;
            case (88):
                return fH8;
        }
        return null;
    }

    private Node getNodeFromGridPane2(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    public Node getNodeFromGridPane (final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();
        System.out.println(childrens.size());
        for (Node node : childrens) {
            if(node == null){
                System.out.println("Chyba");
            }
            else{
                System.out.println("ok");
                if(gridPane.getRowIndex(node) == row) {
                    if (gridPane.getColumnIndex(node) == column){
                        result = node;
                        break;
                    }
                }
            }
        }

        return result;
    }

    public void setMove(MouseEvent mouseEvent) {
        int index = notationList2.getSelectionModel().getSelectedIndex()+1;
        this.chess.setCounter(index);
        this.chess.positionMove(this.chess.getCounter());
        this.fillBoard();
    }

    public void selectField(MouseEvent mouseEvent) {
        int col;
        int row;
        Node source = (Node)mouseEvent.getSource();
        row = grid.getColumnIndex(source.getParent());
        col = 9 - grid.getRowIndex(source.getParent());
        //have to call with swapped col and row because of different JavaFX col and row
        this.chess.buildMove(row, col);

    }

    public void relodNotation(){
        notationList2.getItems().clear();
        for (int i = 0; i < chess.getMoves().size(); i++){
            notationList2.getItems().addAll(chess.getMoves().get(i).printOnRow());
        }
    }
}

