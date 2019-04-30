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

    protected boolean checkWayU(int col, int start_row, int end_row) {
        Board board = this.getBoardField().getBoard();
        for(int row = start_row + 1; row <= end_row; row += 1) {
            if(!board.getField(col, row).isEmpty() && row != end_row) {
                return false;
            }
        }
        return true;
    }

    protected boolean checkWayUR(int start_col, int start_row, int end_col, int end_row) {
        Board board = this.getBoardField().getBoard();
        for(int col = start_col + 1, row = start_row + 1;
            col <= end_col && row <= end_row;
            col += 1, row += 1) {
            if(!board.getField(col, row).isEmpty() && !(col == end_col && row == end_row)) {
                return false;
            }
        }
        return true;
    }

    protected boolean checkWayR(int start_col, int end_col, int row) {
        Board board = this.getBoardField().getBoard();
        for(int col = start_col + 1; col <= end_col; col += 1) {
            if(!board.getField(col, row).isEmpty() && col != end_col) {
                return false;
            }
        }
        return true;
    }

    protected boolean checkWayDR(int start_col, int start_row, int end_col, int end_row) {
        Board board = this.getBoardField().getBoard();
        for(int col = start_col - 1, row = start_row + 1;
            col >= end_col && row <= end_row;
            col -= 1, row += 1) {
            if(!board.getField(col, row).isEmpty() && !(col == end_col && row == end_row)) {
                return false;
            }
        }
        return true;
    }

    protected boolean checkWayD(int col, int start_row, int end_row) {
        Board board = this.getBoardField().getBoard();
        for(int row = start_row - 1; row >= end_row; row -= 1) {
            if(!board.getField(col, row).isEmpty() && row != end_row) {
                return false;
            }
        }
        return true;
    }

    protected boolean checkWayDL(int start_col, int start_row, int end_col, int end_row) {
        Board board = this.getBoardField().getBoard();
        for(int col = start_col - 1, row = start_row - 1;
            col >= end_col && row >= end_row;
            col -= 1, row -= 1) {
            if(!board.getField(col, row).isEmpty() && !(col == end_col && row == end_row)) {
                return false;
            }
        }
        return true;
    }

    protected boolean checkWayL(int start_col, int end_col, int row) {
        Board board = this.getBoardField().getBoard();
        for(int col = start_col - 1; col >= end_col; col -= 1) {
            if(!board.getField(col, row).isEmpty() && col != end_col) {
                return false;
            }
        }
        return true;
    }

    protected boolean checkWayUL(int start_col, int start_row, int end_col, int end_row) {
        Board board = this.getBoardField().getBoard();
        for(int col = start_col + 1, row = start_row - 1;
            col <= end_col && row >= end_row;
            col += 1, row -= 1) {
            if(!board.getField(col, row).isEmpty() && !(col == end_col && row == end_row)) {
                return false;
            }
        }
        return true;
    }

    public abstract boolean canMove(BoardField boardField);
}