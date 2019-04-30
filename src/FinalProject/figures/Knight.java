package FinalProject.figures;

import FinalProject.common.FigureType;
import FinalProject.figures.UniversalFigure;
import FinalProject.game.BoardField;

public class Knight extends UniversalFigure {
    public Knight(BoardField boardField, boolean white) {
        super(boardField, white, FigureType.J);
    }

    public boolean canMove(BoardField destination) {
        if(!destination.isEmpty()){
            if(destination.getFigure().isWhite() == this.isWhite()){
                return false;
            }
        }
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
