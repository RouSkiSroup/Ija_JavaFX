package FinalProject.figures;

import FinalProject.game.BoardField;

/**
 * Class represents king figure.
 * @author Filip Jerabek (xjerab24), Daniel Konecny (xkonec75)
 * @version 2.0
 */
public class King extends UniversalFigure {
    public King(BoardField boardField, boolean white) {
        super(boardField, white, FigureType.K);
    }

    public boolean canMove(BoardField destination) {
        if(!destination.isEmpty()){
            if(destination.getFigure().isWhite() == this.isWhite()){
                return false;
            }
        }
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
