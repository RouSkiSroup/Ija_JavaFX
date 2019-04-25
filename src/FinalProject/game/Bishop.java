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
            return checkWay(source.getCol(), source.getRow(),
                    destination.getCol(), destination.getRow(),
                    1, 1);
        }
        else if(destination.getCol() > source.getCol() && destination.getRow() < source.getRow()) {
            return checkWay(source.getCol(), destination.getRow()-1,
                    destination.getCol(), source.getRow()-1,
                    1, 1);
        }
        else if(destination.getCol() < source.getCol() && destination.getRow() < source.getRow()) {
            return checkWay(destination.getCol()-1, destination.getRow()-1,
                    source.getCol()-1, source.getRow()-1,
                    1, 1);
        }
        else if(destination.getCol() < source.getCol() && destination.getRow() > source.getRow()) {
            return checkWay(destination.getCol()-1, source.getRow(),
                    source.getCol()-1, destination.getRow(),
                    1, 1);
        }
        else {
            return false;
        }
    }
}
