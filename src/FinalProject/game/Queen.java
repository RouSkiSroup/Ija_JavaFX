package FinalProject.game;

import FinalProject.common.FigureType;
import FinalProject.common.UniversalFigure;

public class Queen extends UniversalFigure {
    public Queen(BoardField boardField, boolean white) {
        super(boardField, white, FigureType.D);
    }

    public boolean canMove(BoardField destination) {
        BoardField source = this.getBoardField();

        // Checks if the destination is achievable.
        if(Math.abs(destination.getCol() - source.getCol()) != Math.abs(destination.getRow() - source.getRow()) &&
                destination.getCol() != source.getCol() &&
                destination.getRow() != source.getRow()) {
            return false;
        }

        // Checks if there are any figures in the way.
        if(destination.getCol() == source.getCol() && destination.getRow() > source.getRow()) {
            return checkWay(source.getCol(), source.getRow(),
                    destination.getCol(), destination.getRow(),
                    0, 1);
        }
        else if(destination.getCol() > source.getCol() && destination.getRow() > source.getRow()) {
            return checkWay(source.getCol(), source.getRow(),
                    destination.getCol(), destination.getRow(),
                    1, 1);
        }
        else if(destination.getCol() > source.getCol() && destination.getRow() == source.getRow()) {
            return checkWay(source.getCol(), source.getRow(),
                    destination.getCol(), destination.getRow(),
                    1, 0);
        }
        else if(destination.getCol() > source.getCol() && destination.getRow() < source.getRow()) {
            return checkWay(source.getCol(), destination.getRow(),
                    destination.getCol(), source.getRow(),
                    1, 1);
        }
        else if(destination.getCol() == source.getCol() && destination.getRow() < source.getRow()) {
            return checkWay(source.getCol(), destination.getRow()-1,
                    destination.getCol(), source.getRow()-1,
                    0, 1);
        }
        else if(destination.getCol() < source.getCol() && destination.getRow() < source.getRow()) {
            return checkWay(destination.getCol(), destination.getRow(),
                    source.getCol(), source.getRow(),
                    1, 1);
        }
        else if(destination.getCol() < source.getCol() && destination.getRow() == source.getRow()) {
            return checkWay(destination.getCol()-1, source.getRow(),
                    source.getCol()-1, destination.getRow(),
                    1, 0);
        }
        else if(destination.getCol() < source.getCol() && destination.getRow() > source.getRow()) {
            return checkWay(destination.getCol(), source.getRow(),
                    source.getCol(), destination.getRow(),
                    1, 1);
        }
        else {
            return false;
        }
    }
}
