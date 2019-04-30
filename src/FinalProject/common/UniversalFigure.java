package FinalProject.common;

import FinalProject.game.Board;
import FinalProject.game.BoardField;

/**
 * Abstract class for figure.
 */
public abstract class UniversalFigure {
    private BoardField boardField;
    private FigureType type;
    private boolean white;

    /**
     * Constructor
     * @param boardField Field on which the figure stands.
     * @param white Is true if the figure is white.
     * @param type  Specific the figure type.
     */
    public UniversalFigure(BoardField boardField, boolean white, FigureType type) {
        this.boardField = boardField;
        this.white = white;
        this.type = type;
    }

    /**
     * Returns figure's field.
     * @return  Returns figure's field.
     */
    public BoardField getBoardField() {
        return this.boardField;
    }

    /**
     * Sets figure's field.
     * @param boardField    Field to set.
     */
    public void setBoardField(BoardField boardField){
        this.boardField = boardField;
    }

    /**
     * @return  Returns the type of the figure.
     */
    public FigureType getType() {
        return type;
    }

    /**
     * @return  Returns true, if the figure is white, otherwise false.
     */
    public boolean isWhite() {
        return this.white;
    }

    /**
     * Checks if no figures are in the way up.
     * @param col   Column in which is the figure moving.
     * @param start_row Starting row.
     * @param end_row   Ending row.
     * @return  Returns true if no figures are in the way, otherwise false.
     */
    protected boolean checkWayU(int col, int start_row, int end_row) {
        Board board = this.getBoardField().getBoard();
        for(int row = start_row + 1; row <= end_row; row += 1) {
            if(!board.getField(col, row).isEmpty() && row != end_row) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if no figures are in the way up-right.
     * @param start_col Starting column.
     * @param start_row Starting row.
     * @param end_col   Ending column.
     * @param end_row   Ending row.
     * @return  Returns true if no figures are in the way, otherwise false.
     */
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

    /**
     * Checks if no figures are in the way right.
     * @param start_col Starting column.
     * @param end_col   Ending column.
     * @param row   Row in which is the figure moving.
     * @return  Returns true if no figures are in the way, otherwise false.
     */
    protected boolean checkWayR(int start_col, int end_col, int row) {
        Board board = this.getBoardField().getBoard();
        for(int col = start_col + 1; col <= end_col; col += 1) {
            if(!board.getField(col, row).isEmpty() && col != end_col) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if no figures are in the way down-right.
     * @param start_col Starting column.
     * @param start_row Starting row.
     * @param end_col   Ending column.
     * @param end_row   Ending row.
     * @return  Returns true if no figures are in the way, otherwise false.
     */
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

    /**
     * Checks if no figures are in the way down.
     * @param col   Column in which is the figure moving.
     * @param start_row Starting row.
     * @param end_row   Ending row.
     * @return  Returns true if no figures are in the way, otherwise false.
     */
    protected boolean checkWayD(int col, int start_row, int end_row) {
        Board board = this.getBoardField().getBoard();
        for(int row = start_row - 1; row >= end_row; row -= 1) {
            if(!board.getField(col, row).isEmpty() && row != end_row) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if no figures are in the way down-left.
     * @param start_col Starting column.
     * @param start_row Starting row.
     * @param end_col   Ending column.
     * @param end_row   Ending row.
     * @return  Returns true if no figures are in the way, otherwise false.
     */
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

    /**
     * Checks if no figures are in the way left.
     * @param start_col Starting column.
     * @param end_col   Ending column.
     * @param row   Row in which is the figure moving.
     * @return  Returns true if no figures are in the way, otherwise false.
     */
    protected boolean checkWayL(int start_col, int end_col, int row) {
        Board board = this.getBoardField().getBoard();
        for(int col = start_col - 1; col >= end_col; col -= 1) {
            if(!board.getField(col, row).isEmpty() && col != end_col) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if no figures are in the way up-left.
     * @param start_col Starting column.
     * @param start_row Starting row.
     * @param end_col   Ending column.
     * @param end_row   Ending row.
     * @return  Returns true if no figures are in the way, otherwise false.
     */
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

    /**
     * Checks if figure can move to destination field.
     * @param boardField   Destination field.
     * @return  Returns true if this figure can be moved, Otherwise false.
     */
    public abstract boolean canMove(BoardField boardField);
}