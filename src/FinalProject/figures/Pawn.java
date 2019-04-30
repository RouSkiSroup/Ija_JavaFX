package FinalProject.figures;

import FinalProject.game.BoardField;

/**
 * Class represents pawn figure.
 * @author Filip Jerabek (xjerab24), Daniel Konecny (xkonec75)
 * @version 2.0
 */
public class Pawn extends UniversalFigure {
    public Pawn(BoardField boardField, boolean white) {
        super(boardField, white, FigureType.p);
    }

    public boolean canMove(BoardField destination) {
        if(!destination.isEmpty()){
            if(destination.getFigure().isWhite() == this.isWhite()){
                return false;
            }
        }
        BoardField source = this.getBoardField();
        // Move figure forward.
        if(destination.isEmpty()) {
            // White can move only up.
            if(this.isWhite()) {
                return destination.getCol() == source.getCol() &&
                        (destination.getRow() == source.getRow()+1 ||
                                // Can move two squares from starting position.
                                source.getRow() == 1 && destination.getRow() == source.getRow()+2);
            }
            // Black can move only down.
            else {
                return destination.getCol() == source.getCol() &&
                        (destination.getRow() == source.getRow()-1 ||
                                // Can move two squares from starting position.
                                source.getRow() == 6 && destination.getRow() == source.getRow()-2);
            }
        }
        // Capture opponent's figure.
        else {
            // White can move only up.
            if(this.isWhite()) {
                return  destination.getCol() == source.getCol()+1 && destination.getRow() == source.getRow()+1 ||
                        destination.getCol() == source.getCol()-1 && destination.getRow() == source.getRow()+1;
            }
            // Black can move only down.
            else {
                return  destination.getCol() == source.getCol()+1 && destination.getRow() == source.getRow()-1 ||
                        destination.getCol() == source.getCol()-1 && destination.getRow() == source.getRow()-1;
            }
        }
    }
}
