package FinalProject.game;

import FinalProject.figures.FigureType;

/**
 * Class representing one move with all the needed information.
 * @author Filip Jerabek (xjerab24), Daniel Konecny (xkonec75)
 * @version 1.0
 */
public class OneMove {
    private boolean white_player;
    private FigureType figure;
    private int source_col;         // In range 0-7.
    private int source_row;         // In range 0-7.
    private boolean capture;
    private int destination_col;    // In range 0-7.
    private int destination_row;    // In range 0-7.
    private FigureType promotion;
    private SpecialState special;

    public OneMove(boolean white_player, FigureType figure, int source_col, int source_row, boolean capture,
                   int destination_col, int destination_row, FigureType change, SpecialState special) {
        this.white_player = white_player;
        this.figure = figure;
        this.source_col = source_col;
        this.source_row = source_row;
        this.capture = capture;
        this.destination_col = destination_col;
        this.destination_row = destination_row;
        this.promotion = change;
        this.special = special;
    }

    /**
     * @return  If player in move is white or not.
     */
    public boolean getWhitePlayer() {
        return white_player;
    }

    /**
     * @param white_player If player in move is white or not.
     */
    public void setWhitePlayer(boolean white_player) {
        this.white_player = white_player;
    }

    /**
     * @return Type of figure that is moved.
     */
    public FigureType getFigure() {
        return figure;
    }

    /**
     * @param figure Type of figure that is moved.
     */
    public void setFigure(FigureType figure) {
        this.figure = figure;
    }

    /**
     * @return Number of column from which the figure is moved.
     */
    public int getSourceCol() {
        return source_col;
    }

    /**
     * @param source_col Number of column from which the figure is moved.
     */
    public void setSourceCol(int source_col) {
        if(source_col >= 0 && source_col <= 7) {
            this.source_col = source_col;
        }
        else {
            System.err.println("Nepovolene souradnice sloupce!");
            System.exit(1);
        }
    }

    /**
     * @return Number of row from which the figure is moved.
     */
    public int getSourceRow() {
        return source_row;
    }

    /**
     * @param source_row Number of row from which the figure is moved.
     */
    public void setSourceRow(int source_row) {
        if(source_row >= 0 && source_row <= 7) {
            this.source_row = source_row;
        }
        else {
            System.err.println("Nepovolene souradnice radku!");
            System.exit(1);
        }
    }

    /**
     * @return If figure captured any opponent's figure during this move.
     */
    public boolean getCapture() {
        return this.capture;
    }

    /**
     * @param capture If figure captured any opponent's figure during this move.
     */
    public void setCapture(boolean capture) {
        this.capture = capture;
    }

    /**
     * @return Number of column to which the figure is moved.
     */
    public int getDestinationCol() {
        return destination_col;
    }

    /**
     * @param destination_col Number of column to which the figure is moved.
     */
    public void setDestinationCol(int destination_col) {
        if(destination_col >= 0 && destination_col <= 7) {
            this.destination_col = destination_col;
        }
        else {
            System.err.println("Nepovolene souradnice sloupce!");
            System.exit(1);
        }
    }

    /**
     * @return Number of row to which the figure is moved.
     */
    public int getDestinationRow() {
        return destination_row;
    }

    /**
     * @param destination_row Number of row to which the figure is moved.
     */
    public void setDestinationRow(int destination_row) {
        if(destination_row >= 0 && destination_row <= 7) {
            this.destination_row = destination_row;
        }
        else {
            System.err.println("Nepovolene souradnice radku!");
            System.exit(1);
        }
    }

    /**
     * @return To which type of figure is pawn promoted.
     */
    public FigureType getPromotion(){
        return this.promotion;
    }

    /**
     * @param promotion To which type of figure is pawn promoted.
     */
    public void setPromotion(FigureType promotion) {
        this.promotion = promotion;
    }

    /**
     * @return Special state which happens after move (check(mate), draw).
     */
    public SpecialState getSpecial() {
        return special;
    }

    /**
     * @param special Special state which happens after move (check(mate), draw).
     */
    public void setSpecial(SpecialState special) {
        this.special = special;
    }

    /**
     * Sets move to default values.
     */
    public void clearMove() {
        this.white_player = false;
        this.figure = null;
        this.source_col = -1;
        this.source_row = -1;
        this.capture = false;
        this.destination_col = -1;
        this.destination_row = -1;
        this.promotion = null;
        this.special = null;
    }

    /**
     * @return Representation of move as specified in notation.
     */
    public String printOnRow() {
        String ret = "";
        if(this.figure != null && this.figure != FigureType.p) {
            ret += this.figure.name();
        }
        if(this.source_row != -1) {
            ret += getRowAsString(this.source_row);
        }
        if(this.destination_col != -1) {
            ret += getColAsString(this.destination_col);
        }
        if(this.capture) {
            ret += "x";
        }
        if(this.source_col != -1) {
            ret += getColAsString(this.source_col);
        }
        if(this.destination_row != -1) {
            ret += getRowAsString(this.destination_row);
        }
        if(this.promotion != null) {
            ret += this.promotion.name();
        }
        if(this.special != null) {
            if(this.special == SpecialState.CHECK) {
                ret += "+";
            }
            else if(this.special == SpecialState.CHECKMATE) {
                ret += "#";
            }
            else if(this.special == SpecialState.DRAW) {
                ret += "1/2-1/2";
            }
        }
        return ret;
    }

    /**
     * @param col   Given number of column.
     * @return      Letter representing given column.
     */
    private String getColAsString(int col) {
        return Character.toString((char)(col + 97));
    }

    /**
     * @param row   Given number of row.
     * @return      Number representing given row.
     */
    private String getRowAsString(int row) {
        return Integer.toString(row + 1);
    }

    /**
     * Prints notation in readable form in case of debugging.
     */
    public void debugPrint() {
        System.out.println("White player: " + this.white_player);
        if(this.figure != null) {
            System.out.println("Figure: " + this.figure.name());
        }
        if(this.source_col != -1) {
            System.out.println("From col: " + getColAsString(this.source_col) + " " + this.source_col);
        }
        if(this.source_row != -1) {
            System.out.println("From row: " + getRowAsString(this.source_row) + " " + this.source_row);
        }
        if(this.destination_col != -1) {
            System.out.println("To col: " + getColAsString(this.destination_col) + " " + this.destination_col);
        }
        if(this.destination_row != -1) {
            System.out.println("To row: " + getRowAsString(this.destination_row) + " " + this.destination_row);
        }
        if(this.promotion != null) {
            System.out.println("Change: " + this.promotion.name());
        }
        if(this.special != null) {
            System.out.println("Special: " + this.special);
        }
        System.out.println();
    }
}
