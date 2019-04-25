package FinalProject.game;

import FinalProject.common.FigureType;
import FinalProject.common.UniversalFigure;

import java.util.ArrayList;

public class Board {
    public BoardField[][] board;

    public Board(int size){
        this.board = new BoardField[size][size];
        for(int col = 0; col < getSize(); col++) {
            for(int row = 0; row < getSize(); row++) {
                this.board[col][row] = new BoardField(this, col, row);
            }
        }
    }

    public BoardField[][] getBoard() {
        return this.board;
    }

    public BoardField getField(int col, int row) {
        if(col >= 0 && col < getSize() && row >=0 && row < getSize()) {
            return this.board[col][row];
        }
        return null;
    }

    public int getSize() {
        if(board != null && board[0] != null) {
            return board[0].length;
        }
        return 0;
    }

    public ArrayList<UniversalFigure> getFiguresOfType(FigureType type, boolean white_player) {
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
}
