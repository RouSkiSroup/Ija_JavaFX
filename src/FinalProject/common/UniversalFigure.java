package FinalProject.common;

import FinalProject.game.Board;
import FinalProject.game.BoardField;

public abstract class UniversalFigure {
    private BoardField boardField;
    private FigureType type;
    private boolean white;

    public UniversalFigure(BoardField boardField, boolean white, FigureType type) {
        this.boardField = boardField;
        this.white = white;
        this.type = type;
    }

    public BoardField getBoardField() {
        return this.boardField;
    }

    public void setBoardField(BoardField boardField){
        this.boardField = boardField;
    }

    public FigureType getType() {
        return type;
    }

    public boolean isWhite() {
        return this.white;
    }

    public void printState() {
        String state = getType().name();
        if(isWhite()) {
            state += "[W]";
        }
        else {
            state += "[B]";
        }
        state += getBoardField().getCol() + ":" + getBoardField().getRow();
        System.out.println(state);
    }

    protected boolean checkWay(int start_col, int start_row,
                               int end_col, int end_row,
                               int change_col, int change_row) {
        Board board = this.getBoardField().getBoard();
        for(int col = start_col + change_col, row = start_row + change_row;
            col <= end_col && row <= end_row;
            col += change_col, row += change_row) {
            if(!board.getField(col, row).isEmpty() && !(col != end_col && row != end_row)) {
                return false;
            }
        }
        return true;
    }

    public abstract boolean canMove(BoardField boardField);
}