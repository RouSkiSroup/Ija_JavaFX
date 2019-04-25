package FinalProject.game;

import FinalProject.common.FigureType;
import FinalProject.common.UniversalFigure;

public class Rook extends UniversalFigure {
    public Rook(BoardField boardField, boolean white) {
        super(boardField, white, FigureType.V);
    }

    public boolean canMove(BoardField destination) {
        BoardField source = this.getBoardField();

        // Checks if the destination is achievable and if there are any figures in the way.
        if(destination.getCol() == source.getCol() && destination.getRow() > source.getRow()) {
            return checkWay(source.getCol(), source.getRow(),
                    destination.getCol(), destination.getRow(),
                    0, 1);
        }
        else if(destination.getCol() > source.getCol() && destination.getRow() == source.getRow()) {
            return checkWay(source.getCol(), source.getRow(),
                    destination.getCol(), destination.getRow(),
                    1, 0);
        }
        else if(destination.getCol() == source.getCol() && destination.getRow() < source.getRow()) {
            return checkWay(source.getCol(), destination.getRow()-1,
                    destination.getCol(), source.getRow()-1,
                    0, 1);
        }
        else if(destination.getCol() < source.getCol() && destination.getRow() == source.getRow()) {
            return checkWay(destination.getCol()-1, source.getRow(),
                    source.getCol()-1, destination.getRow(),
                    1, 0);
        }
        else {
            return false;
        }
    }
}
