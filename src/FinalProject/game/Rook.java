package FinalProject.game;

import FinalProject.common.FigureType;
import FinalProject.common.UniversalFigure;

public class Rook extends UniversalFigure {
    public Rook(BoardField boardField, boolean white) {
        super(boardField, white, FigureType.V);
    }

    public boolean canMove(BoardField destination) {
        if(!destination.isEmpty()){
            if(destination.getFigure().isWhite() == this.isWhite()){
                return false;
            }
        }
        BoardField source = this.getBoardField();

        // Checks if the destination is achievable and if there are any figures in the way.
        if(destination.getCol() == source.getCol() && destination.getRow() > source.getRow()) {
            return checkWayU(source.getCol(), source.getRow(), destination.getRow());
        }
        else if(destination.getCol() > source.getCol() && destination.getRow() == source.getRow()) {
            return checkWayR(source.getCol(), destination.getCol(), destination.getRow());
        }
        else if(destination.getCol() == source.getCol() && destination.getRow() < source.getRow()) {
            return checkWayD(source.getCol(), source.getRow(), destination.getRow());
        }
        else if(destination.getCol() < source.getCol() && destination.getRow() == source.getRow()) {
            return checkWayL(source.getCol(), destination.getCol(), destination.getRow());
        }
        else {
            return false;
        }
    }
}
