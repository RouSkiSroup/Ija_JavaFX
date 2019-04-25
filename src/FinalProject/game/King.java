package FinalProject.game;

import FinalProject.common.FigureType;
import FinalProject.common.UniversalFigure;

public class King extends UniversalFigure {
    public King(BoardField boardField, boolean white) {
        super(boardField, white, FigureType.K);
    }

    public boolean canMove(BoardField destination) {
        BoardField source = this.getBoardField();
        return  destination.getCol() == source.getCol()   && destination.getRow() == source.getRow()+1 ||
                destination.getCol() == source.getCol()+1 && destination.getRow() == source.getRow()+1 ||
                destination.getCol() == source.getCol()+1 && destination.getRow() == source.getRow()   ||
                destination.getCol() == source.getCol()+1 && destination.getRow() == source.getRow()-1 ||
                destination.getCol() == source.getCol()   && destination.getRow() == source.getRow()-1 ||
                destination.getCol() == source.getCol()-1 && destination.getRow() == source.getRow()-1 ||
                destination.getCol() == source.getCol()-1 && destination.getRow() == source.getRow()   ||
                destination.getCol() == source.getCol()-1 && destination.getRow() == source.getRow()+1;
    }
}
