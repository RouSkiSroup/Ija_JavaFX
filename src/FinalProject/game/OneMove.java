package FinalProject.game;

import FinalProject.common.FigureType;
import FinalProject.common.SpecialState;

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

    public boolean getWhitePlayer() {
        return white_player;
    }

    public void setWhitePlayer(boolean white_player) {
        this.white_player = white_player;
    }

    public FigureType getFigure() {
        return figure;
    }

    public void setFigure(FigureType figure) {
        this.figure = figure;
    }

    public int getSourceCol() {
        return source_col;
    }

    public void setSourceCol(int source_col) {
        if(source_col >= 0 && source_col <= 7) {
            this.source_col = source_col;
        }
        else {
            System.err.println("Nepovolene souradnice sloupce!");
            System.exit(1);
        }
    }

    public int getSourceRow() {
        return source_row;
    }

    public void setSourceRow(int source_row) {
        if(source_row >= 0 && source_row <= 7) {
            this.source_row = source_row;
        }
        else {
            System.err.println("Nepovolene souradnice radku!");
            System.exit(1);
        }
    }

    public boolean getCapture() {
        return this.capture;
    }

    public void setCapture(boolean capture) {
        this.capture = capture;
    }

    public int getDestinationCol() {
        return destination_col;
    }

    public void setDestinationCol(int destination_col) {
        if(destination_col >= 0 && destination_col <= 7) {
            this.destination_col = destination_col;
        }
        else {
            System.err.println("Nepovolene souradnice sloupce!");
            System.exit(1);
        }
    }

    public int getDestinationRow() {
        return destination_row;
    }

    public void setDestinationRow(int destination_row) {
        if(destination_row >= 0 && destination_row <= 7) {
            this.destination_row = destination_row;
        }
        else {
            System.err.println("Nepovolene souradnice radku!");
            System.exit(1);
        }
    }

    public FigureType getPromotion(){
        return this.promotion;
    }

    public void setPromotion(FigureType promotion) {
        this.promotion = promotion;
    }

    public SpecialState getSpecial() {
        return special;
    }

    public void setSpecial(SpecialState special) {
        this.special = special;
    }

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

    private String getColAsString(int col) {
        return Character.toString((char)(col + 97));
    }

    private String getRowAsString(int row) {
        return Integer.toString(row + 1);
    }

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
