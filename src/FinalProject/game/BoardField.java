package FinalProject.game;

import FinalProject.common.UniversalFigure;

public class BoardField {
    private Board board;
    private UniversalFigure figure;
    private int col;
    private int row;

    BoardField(Board board, int col, int row){
        this.board = board;
        this.col = col;
        this.row = row;
    }

    public Board getBoard() {
        return this.board;
    }

    public UniversalFigure getFigure(){
        return this.figure;
    }

    public void setFigure(UniversalFigure figure){
        this.figure = figure;
    }

    public int getCol() {
        return this.col;
    }

    public int getRow() {
        return this.row;
    }

    public boolean isEmpty() {
        return this.figure == null;
    }

    public String getLocation(){
        String ret = String.valueOf((char)(row + 64));
        ret = ret + (col + 1);
        return ret;
    }
}


