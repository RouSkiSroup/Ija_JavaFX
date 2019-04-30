package FinalProject.game;

import FinalProject.common.FigureType;
import FinalProject.common.UniversalFigure;

public class Queen extends UniversalFigure {
    public Queen(BoardField boardField, boolean white) {
        super(boardField, white, FigureType.D);
    }

    public boolean canMove(BoardField destination) {
        if(!destination.isEmpty()){
            if(destination.getFigure().isWhite() == this.isWhite()){
                return false;
            }
        }
        BoardField source = this.getBoardField();

        // Checks if the destination is achievable.
        if(Math.abs(destination.getCol() - source.getCol()) != Math.abs(destination.getRow() - source.getRow()) &&
                destination.getCol() != source.getCol() &&
                destination.getRow() != source.getRow()) {
            return false;
        }

        // Checks if there are any figures in the way.
        if(destination.getCol() == source.getCol() && destination.getRow() > source.getRow()) {
            return checkWayU(source.getCol(), source.getRow(), destination.getRow());

        }
        else if(destination.getCol() > source.getCol() && destination.getRow() > source.getRow()) {
            return checkWayUR(source.getCol(), source.getRow(), destination.getCol(), destination.getRow());
        }
        else if(destination.getCol() > source.getCol() && destination.getRow() == source.getRow()) {
            return checkWayR(source.getCol(), destination.getCol(), destination.getRow());
        }
        else if(destination.getCol() > source.getCol() && destination.getRow() < source.getRow()) {
            return checkWayDR(source.getCol(), source.getRow(), destination.getCol(), destination.getRow());
        }
        else if(destination.getCol() == source.getCol() && destination.getRow() < source.getRow()) {
            return checkWayD(source.getCol(), source.getRow(), destination.getRow());
        }
        else if(destination.getCol() < source.getCol() && destination.getRow() < source.getRow()) {
            return checkWayDL(source.getCol(), source.getRow(), destination.getCol(), destination.getRow());
        }
        else if(destination.getCol() < source.getCol() && destination.getRow() == source.getRow()) {
            return checkWayL(source.getCol(), destination.getCol(), destination.getRow());
        }
        else if(destination.getCol() < source.getCol() && destination.getRow() > source.getRow()) {
            return checkWayUL(source.getCol(), source.getRow(), destination.getCol(), destination.getRow());
        }
        else {
            return false;
        }
    }
}
