package FinalProject.game;

import FinalProject.common.FigureType;
import FinalProject.figures.UniversalFigure;

import java.util.ArrayList;

/**
 * Class representing board used for the game using 2D array of fields.
 */
public class Board {
    public BoardField[][] board;

    public Board(int size) {
        this.board = new BoardField[size][size];
        for(int col = 0; col < getSize(); col++) {
            for(int row = 0; row < getSize(); row++) {
                this.board[col][row] = new BoardField(this, col, row);
            }
        }
    }

    /**
     * @return 2D array representing this board.
     */
    public BoardField[][] getBoard() {
        return this.board;
    }

    /**
     * Returns field on given position.
     * @param col   Number of column of the field.
     * @param row   Number of row of the field.
     * @return  Field that is found, null if not found.
     */
    public BoardField getField(int col, int row) {
        if(col >= 0 && col < getSize() && row >=0 && row < getSize()) {
            return this.board[col][row];
        }
        return null;
    }

    /**
     * @return Size of the board.
     */
    public int getSize() {
        if(board != null && board[0] != null) {
            return board[0].length;
        }
        return 0;
    }

    /**
     * Returns figure of king of given player.
     * @param white_player  Player we are asking for.
     * @return  Figure representing players king.
     */
    public UniversalFigure getKingOfPlayer(boolean white_player) {
        for(BoardField[] col: this.board) {
            for(BoardField field: col) {
                if(field.getFigure() != null){
                    if(field.getFigure().getType() == FigureType.K && field.getFigure().isWhite() == white_player) {
                        return field.getFigure();
                    }
                }
            }
        }
        return null;
    }

    /**
     * Returns all figures of given player.
     * @param white_player  Player we are asking for.
     * @return  All players figures on the board.
     */
    public ArrayList<UniversalFigure> getFiguresOfPlayer(boolean white_player) {
        ArrayList<UniversalFigure> figures = new ArrayList<>();
        for(BoardField[] col: this.board) {
            for(BoardField field: col) {
                if(field.getFigure() != null){
                    if(field.getFigure().isWhite() == white_player) {
                        figures.add(field.getFigure());
                    }
                }
            }
        }
        return figures;
    }

    /**
     * Returns all figure of given type and player. Used when getting the right figure from source position of notation.
     * @param type          Type of figures we are looking for.
     * @param white_player  Player we are asking for.
     * @return  All players figures of given type on the board.
     */
    public ArrayList<UniversalFigure> getFiguresOfTypeAndPlayer(FigureType type, boolean white_player) {
        ArrayList<UniversalFigure> figures = new ArrayList<>();
        for(BoardField[] col: this.board) {
            for(BoardField field: col) {
                if(field.getFigure() != null){
                    if(field.getFigure().getType() == type && field.getFigure().isWhite() == white_player) {
                        figures.add(field.getFigure());
                    }
                }
            }
        }
        return figures;
    }
}
