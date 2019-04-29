package FinalProject.game;

import FinalProject.common.FigureType;
import FinalProject.common.UniversalFigure;

public class Bishop extends UniversalFigure {
    public Bishop(BoardField boardField, boolean white) {
        super(boardField, white, FigureType.S);
    }

    public boolean canMove(BoardField destination) {
        BoardField source = this.getBoardField();

        // Checks if the destination is achievable.
        if(Math.abs(destination.getCol() - source.getCol()) != Math.abs(destination.getRow() - source.getRow())) {
            return false;
        }

        // Checks if there are any figures in the way.
        if(destination.getCol() > source.getCol() && destination.getRow() > source.getRow()) {
            return checkWayUR(source.getCol(), source.getRow(), destination.getCol(), destination.getRow());
        }
        else if(destination.getCol() > source.getCol() && destination.getRow() < source.getRow()) {
            return checkWayDR(source.getCol(), source.getRow(), destination.getCol(), destination.getRow());
        }
        else if(destination.getCol() < source.getCol() && destination.getRow() < source.getRow()) {
            return checkWayDL(source.getCol(), source.getRow(), destination.getCol(), destination.getRow());
        }
        else if(destination.getCol() < source.getCol() && destination.getRow() > source.getRow()) {
            return checkWayUL(source.getCol(), source.getRow(), destination.getCol(), destination.getRow());
        }
        else {
            return false;
        }
    }
}
