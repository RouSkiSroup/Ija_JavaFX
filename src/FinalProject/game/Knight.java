package FinalProject.game;

import FinalProject.common.FigureType;
import FinalProject.common.UniversalFigure;

public class Knight extends UniversalFigure {
    public Knight(BoardField boardField, boolean white) {
        super(boardField, white, FigureType.J);
    }

    public boolean canMove(BoardField destination) {
        BoardField source = this.getBoardField();
        return  destination.getCol() == source.getCol()+1 && destination.getRow() == source.getRow()+2 ||
                destination.getCol() == source.getCol()+2 && destination.getRow() == source.getRow()+1 ||
                destination.getCol() == source.getCol()+2 && destination.getRow() == source.getRow()-1 ||
                destination.getCol() == source.getCol()+1 && destination.getRow() == source.getRow()-2 ||
                destination.getCol() == source.getCol()-1 && destination.getRow() == source.getRow()-2 ||
                destination.getCol() == source.getCol()-2 && destination.getRow() == source.getRow()-1 ||
                destination.getCol() == source.getCol()-2 && destination.getRow() == source.getRow()+1 ||
                destination.getCol() == source.getCol()-1 && destination.getRow() == source.getRow()+2;
    }
}
