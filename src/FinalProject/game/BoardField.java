package FinalProject.game;

import FinalProject.common.UniversalFigure;

/**
 * Class representing one field on the board.
 */
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

    /**
     * @return Board this field is on.
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * @return Figure that is on this field.
     */
    public UniversalFigure getFigure(){
        return this.figure;
    }

    /**
     * @param figure Figure that is put on this field.
     */
    public void setFigure(UniversalFigure figure){
        this.figure = figure;
    }

    /**
     * @return Column of this field.
     */
    public int getCol() {
        return this.col;
    }

    /**
     * @return Row of this field.
     */
    public int getRow() {
        return this.row;
    }

    /**
     * @return True if field is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.figure == null;
    }
}


