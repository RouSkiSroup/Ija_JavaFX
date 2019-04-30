package FinalProject;

import FinalProject.common.FigureType;
import FinalProject.common.SpecialState;
import FinalProject.figures.*;
import FinalProject.game.*;

import java.util.*;

public class Chess {
    Board board;
    private List<OneMove> moves;
    private Queue<UniversalFigure> figureQueue = new LinkedList<>();
    private int counter;
    private OneMove manualMove;
    private boolean checkmate_try = false;

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    Chess(Board board){
        this.moves = new ArrayList<>();
        this.board = board;
        this.counter = 0;
        manualMove = new OneMove(false,null,-1, -1, false,
                -1, -1, null, null);
        this.board.getBoard()[0][0].setFigure(new Rook(this.board.getBoard()[0][0], true));
        this.board.getBoard()[7][0].setFigure(new Rook(this.board.getBoard()[7][0], true));
        this.board.getBoard()[0][7].setFigure(new Rook(this.board.getBoard()[0][7], false));
        this.board.getBoard()[7][7].setFigure(new Rook(this.board.getBoard()[7][7], false));

        this.board.getBoard()[1][0].setFigure(new Knight(this.board.getBoard()[1][0], true));
        this.board.getBoard()[6][0].setFigure(new Knight(this.board.getBoard()[6][0], true));
        this.board.getBoard()[1][7].setFigure(new Knight(this.board.getBoard()[1][7], false));
        this.board.getBoard()[6][7].setFigure(new Knight(this.board.getBoard()[6][7], false));

        this.board.getBoard()[2][0].setFigure(new Bishop(this.board.getBoard()[2][0], true));
        this.board.getBoard()[5][0].setFigure(new Bishop(this.board.getBoard()[5][0], true));
        this.board.getBoard()[2][7].setFigure(new Bishop(this.board.getBoard()[2][7], false));
        this.board.getBoard()[5][7].setFigure(new Bishop(this.board.getBoard()[5][7], false));

        this.board.getBoard()[3][0].setFigure(new Queen(this.board.getBoard()[3][0], true));
        this.board.getBoard()[3][7].setFigure(new Queen(this.board.getBoard()[3][7], false));

        this.board.getBoard()[4][0].setFigure(new King(this.board.getBoard()[4][0], true));
        this.board.getBoard()[4][7].setFigure(new King(this.board.getBoard()[4][7], false));

        for(int i = 0; i < this.board.getSize(); i++){
            this.board.getBoard()[i][1].setFigure(new Pawn(this.board.getBoard()[i][1], true));
            this.board.getBoard()[i][6].setFigure(new Pawn(this.board.getBoard()[i][6], false));
        }

        for(int i = 0; i < this.board.getSize(); i++){
            for(int j = 0; j < this.board.getSize(); j++){
                this.figureQueue.add(this.board.getBoard()[i][j].getFigure());
            }
        }
    }

    /**
     * @return  Returns moves performed or to be performed in the game.
     */
    List<OneMove> getMoves() {
        return moves;
    }

    /**
     * @return Value of counter.
     */
    int getCounter(){
        return this.counter;
    }

    /**
     * @param counter New value of counter.
     */
    void setCounter(int counter){
        this.counter = counter;
    }

    /**
     * Loads given moves.
     * @param file  Name of file from which the moves should be loaded.
     */
    void loadFile(String file) {
        ParseNotations parser = new ParseNotations();
        this.moves = parser.parseFile(file);
    }

    /**
     * Performs a move of a figure.
     * @return  Result of this operation:
     *          0 if performed or couldn't be performed;
     *          1 if all moves were performed;
     *          2 if game ended with checkmate;
     *          3 if game ended with draw.
     */
    int performMove() {
        if(counter < getMoves().size()){
            System.out.println("Tah cislo " + (counter + 1));

            UniversalFigure figure = getFigureFromNotation(this.moves.get(counter));

            BoardField field = this.board.getField(
                    this.moves.get(this.counter).getDestinationCol(),
                    this.moves.get(this.counter).getDestinationRow());
            if(figure == null){
                return 0;
            }

            moveFigure(figure, field, null);

            if(checkCheck(this.counter % 2 != 0)) {
                System.out.println("Nepovoleny tah - zpusobi sach od soupere.");
                positionMove(this.counter);
                return 0;
            }

            UniversalFigure newFigure = promote(figure, this.moves.get(counter).getPromotion());
            if(newFigure != null) {
                field.setFigure(newFigure);
            }

            if(checkCheck(this.counter % 2 == 0)) {
                System.out.println("Sach.");
                this.moves.get(this.counter).setSpecial(SpecialState.CHECK);
                if(checkCheckmate(this.counter % 2 != 0)) {
                    this.moves.get(this.counter).setSpecial(SpecialState.CHECKMATE);
                    System.out.println("Mat.");
                    return 2;
                }
            }
            else {
                if(checkCheckmate(this.counter % 2 != 0)) {
                    this.moves.get(this.counter).setSpecial(SpecialState.DRAW);
                    System.out.println("Pat.");
                    return 3;
                }
            }
            counter += 1;
            return 0;
        }
        else {
            return 1;
        }
    }

    /**
     * Restarts the game from given position.
     * @param pos Number of moves to be performed from beginning.
     */
    void positionMove(int pos){
        this.restartGame();
        this.counter = 0;
        while(this.counter < pos){
            performMove();
        }
    }

    /**
     * Gets the right figure mentioned in notation of moves.
     * @param move  Move it is getting the figure from.
     * @return      Right figure from notation.
     */
    private UniversalFigure getFigureFromNotation(OneMove move) {
        ArrayList<UniversalFigure> figures = board.getFiguresOfTypeAndPlayer(move.getFigure(), move.getWhitePlayer());
        for(UniversalFigure figure: figures) {
            if(figure.canMove(board.getField(move.getDestinationCol(), move.getDestinationRow()))) {
                if((move.getSourceCol() == -1 || move.getSourceCol() == figure.getBoardField().getCol()) &&
                        (move.getSourceRow() == -1 || move.getSourceRow() == figure.getBoardField().getRow())) {
                    return figure;
                }
            }
        }
        System.out.println("Figura se nemuze pohnout na zadane misto.");
        return null;
    }

    /**
     * Moves a figure to a new field.
     * @param figure    Figure that should be moved.
     * @param field     Field the figure should be moved to.
     * @param set       Which value should be the original field set to.
     */
    private void moveFigure(UniversalFigure figure, BoardField field, UniversalFigure set) {
        // Don't write capture, when move is performed only for checkmate check.
        if(!checkmate_try) {
            if(field.getFigure() != null) {
                this.moves.get(this.counter).setCapture(true);
            }
        }

        figure.getBoardField().setFigure(set);
        figure.setBoardField(field);
        field.setFigure(figure);
    }

    /**
     * Takes care of possible promotion of pawn to other figure.
     * @param figure    Figure that was moved and is checked if can be promoted.
     * @param type      Type of new figure the pawn is promoted to.
     * @return          New figure that replaces the original pawn.
     */
    private UniversalFigure promote(UniversalFigure figure, FigureType type) {
        if(figure.getType() == FigureType.p &&
                (figure.getBoardField().getRow() == 0 || figure.getBoardField().getRow() == 7)) {
            switch (type) {
                case D: {
                    return new Queen(figure.getBoardField(), figure.isWhite());
                }
                case V: {
                    return new Rook(figure.getBoardField(), figure.isWhite());
                }
                case S: {
                    return new Bishop(figure.getBoardField(), figure.isWhite());
                }
                case J: {
                    return new Knight(figure.getBoardField(), figure.isWhite());
                }
            }
        }
        return null;
    }

    /**
     * Checks if opponent of player in argument is in check.
     * @param isWhitesMove  Attacking player.
     * @return  True if player's opponent is in check.
     */
    private boolean checkCheck(boolean isWhitesMove) {
        ArrayList<UniversalFigure> figures = this.board.getFiguresOfPlayer(isWhitesMove);
        UniversalFigure king = this.board.getKingOfPlayer(!isWhitesMove);
        BoardField kings_field = king.getBoardField();

        for(UniversalFigure figure: figures) {
            if(figure.canMove(kings_field)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if player in argument can make any moves.
     * @param isWhitesMove  Defending player.
     * @return  True if player cannot make any move, false otherwise.
     */
    private boolean checkCheckmate(boolean isWhitesMove) {
        ArrayList<UniversalFigure> defending_figures = this.board.getFiguresOfPlayer(isWhitesMove);

        for(UniversalFigure defending_figure: defending_figures) {
            ArrayList<BoardField> available_fields = new ArrayList<>();
            for(int col = 0; col < 8; col++) {
                for(int row = 0; row < 8; row++) {
                    if(defending_figure.canMove(this.board.getField(col, row))) {
                        available_fields.add(this.board.getField(col, row));
                    }
                }
            }

            for(BoardField available_field: available_fields) {
                if(willUncheck(defending_figure, available_field, isWhitesMove)) {
                    /*
                    System.out.println("Unchecks " + defending_figure.getType().name() +
                            defending_figure.getBoardField().getCol() + ":" +
                            defending_figure.getBoardField().getRow());
                     */
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Check if given figure can be moved in order to not leave his king checked.
     * @param figure        Figure that should be moved.
     * @param field         Field the figure should be moved to.
     * @param isWhitesMove  Player who does this move.
     * @return  True if can be moved, false otherwise.
     */
    private boolean willUncheck(UniversalFigure figure, BoardField field, boolean isWhitesMove) {
        boolean uncheck = true;
        BoardField undo_field = figure.getBoardField();
        UniversalFigure undo_figure = figure;
        UniversalFigure undo_capture = field.getFigure();
        checkmate_try = true;

        moveFigure(figure, field, null);
        if(checkCheck(!isWhitesMove)) {
            uncheck = false;
        }
        moveFigure(undo_figure, undo_field, undo_capture);

        checkmate_try = false;
        return uncheck;
    }

    /**
     * Builds a move class according to user input.
     * @param col   Column of field given by the user.
     * @param row   Row of field given by the user.
     * @param promotion_type    Type of figure in case of promotion.
     * @return  Number according to success, see chess.performMove().
     */
    int buildMove(int col, int row, FigureType promotion_type) {
        col -= 1;
        row -= 1;
        int retval = 0;

        if(manualMove.getSourceCol() == -1 && manualMove.getSourceRow() == -1) {
            if(this.board.getField(col, row).getFigure() == null) {
                return retval;
            }
            manualMove.setWhitePlayer(this.counter % 2 == 0);
            manualMove.setFigure(this.board.getField(col, row).getFigure().getType());
            manualMove.setSourceCol(col);
            manualMove.setSourceRow(row);
        }
        else if(manualMove.getDestinationCol() == -1 && manualMove.getDestinationRow() == -1) {
            manualMove.setCapture(this.board.getField(col, row).getFigure() != null);
            manualMove.setDestinationCol(col);
            manualMove.setDestinationRow(row);
            if(manualMove.getFigure() == FigureType.p &&
                    (manualMove.getDestinationRow() == 0 || manualMove.getDestinationRow() == 7)) {
                manualMove.setPromotion(promotion_type);
            }

            OneMove new_move = new OneMove(
                    manualMove.getWhitePlayer(),
                    manualMove.getFigure(),
                    manualMove.getSourceCol(),
                    manualMove.getSourceRow(),
                    manualMove.getCapture(),
                    manualMove.getDestinationCol(),
                    manualMove.getDestinationRow(),
                    manualMove.getPromotion(),
                    manualMove.getSpecial());

            manualMove.clearMove();

            while(this.counter < this.moves.size()) {  // Rest of the array is always moved, so the index stays same.
                this.moves.remove(this.counter);
            }
            this.moves.add(new_move);
            retval = performMove();
        }
        else {
            System.err.println("ERROR: Vnitrni chyba vytvareni pohybu buildMove()!");
            System.exit(1);
        }
        return retval;
    }

    /**
     * Restarts the game to original layout.
     */
    void restartGame() {
        Queue<UniversalFigure> tmp = new LinkedList<>(this.figureQueue);
        this.counter = 0;
        for(int i = 0; i < this.board.getSize(); i++){
            for(int j = 0; j < this.board.getSize(); j++){
                this.board.getBoard()[i][j].setFigure(tmp.remove());
                if (this.board.getBoard()[i][j].getFigure() != null){
                    this.board.getBoard()[i][j].getFigure().setBoardField(this.board.getBoard()[i][j]);
                }
            }
        }
    }

    /**
     * Prints debug board to stdout.
     */
    public void printBoard() {
        System.out.println();
        for(int i = 0; i < this.board.getSize(); i++){
            System.out.print(ANSI_GREEN + (8-i) + ANSI_RESET + " ");
            for(int j = 0; j < this.board.getSize(); j++){
                if (this.board.getBoard()[j][7-i].getFigure() == null){
                    System.out.print("_");
                }
                else if (this.board.getBoard()[j][7-i].getFigure().isWhite()) {
                    System.out.print(ANSI_WHITE + this.board.getBoard()[j][7-i].getFigure().getType() + ANSI_RESET);
                }
                else{
                    System.out.print(ANSI_CYAN + this.board.getBoard()[j][7-i].getFigure().getType() + ANSI_RESET);
                }
                System.out.print(" ");
            }
            System.out.println("");
        }
        System.out.print("  ");
        for(int i = 0; i < 8; i++){
            System.out.print(ANSI_GREEN + (char)(i+65) + " " + ANSI_RESET);
        }
        System.out.println();
        System.out.println();
    }

    /**
     * Prints debug list of moves.
     */
    public void printNotation() {
        int index = 0;
        while(index < this.moves.size()) {
            String output = Integer.toString(index / 2 + 1);
            output += ". ";
            output += this.moves.get(index).printOnRow();
            index += 1;
            if(index < this.moves.size()) {
                output += " ";
                output += this.moves.get(index).printOnRow();
                index += 1;
            }
            System.out.println(output);
        }
    }
}
