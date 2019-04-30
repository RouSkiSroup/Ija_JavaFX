package FinalProject;

import FinalProject.common.FigureType;
import FinalProject.common.UniversalFigure;
import FinalProject.game.*;

import java.util.*;

public class Chess {
    public Board board; //TODO make private
    private List<OneMove> moves;
    private Queue<UniversalFigure> figureQueue = new LinkedList<>();
    private int counter;
    private OneMove manualMove;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

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

    public List<OneMove> getMoves() {
        return moves;
    }

    /**
     * Performs move of figure.
     * @return  Success of this operation.
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

            moveFigure(figure, field);

            if(checkCheck(this.counter % 2 != 0)) {
                System.out.println("Nepovoleny tah - zpusobi sach od soupere.");
                positionMove(this.counter);
                return 0;
            }

            UniversalFigure newFigure = promote(figure, this.moves.get(counter));
            if(newFigure != null) {
                field.setFigure(newFigure);
            }

            if(checkCheck(this.counter % 2 == 0)) {
                System.out.println("Sach.");
                if(checkCheckmate(this.counter % 2 != 0)) {
                    System.out.println("Mat.");
                    return 2;
                }
            }
            counter += 1;
            return 0;
        }
        else {
            return 1;
        }
    }

    void loadFile(String file) {
        ParseNotations parser = new ParseNotations();
        this.moves = parser.parseFile(file);
    }

    //Performs pos moves from start on the game
    public void positionMove(int pos){
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

    private void moveFigure(UniversalFigure figure, BoardField field) {
        figure.getBoardField().setFigure(null);
        figure.setBoardField(field);
        field.setFigure(figure);
    }

    private UniversalFigure promote(UniversalFigure figure, OneMove move) {
        if(figure.getType() == FigureType.p &&
                (figure.getBoardField().getRow() == 0 || figure.getBoardField().getRow() == 7)) {
            FigureType type = move.getPromotion();
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
     * @param isWhitesMove  Opponent of which player (not opponent).
     * @return  If player opponent is in check.
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
     *
     * @param isWhitesMove
     * @return
     */
    private boolean checkCheckmate(boolean isWhitesMove) {
        ArrayList<UniversalFigure> figures = this.board.getFiguresOfPlayer(!isWhitesMove);
        ArrayList<BoardField> fields = new ArrayList<>();
        UniversalFigure king = this.board.getKingOfPlayer(isWhitesMove);

        for(int col = 0; col < 8; col++) {
            for(int row = 0; row < 8; row++) {
                if(king.canMove(this.board.getField(col, row))) {
                    fields.add(this.board.getField(col, row));
                }
            }
        }

        boolean checked_field = false;
        // Iterate through all fields that king of checked player can move to.
        for(BoardField field: fields) {
            // Iterate through all figures of player who checked the other player.
            for(UniversalFigure figure: figures) {
                // Try if all fields that king can move to are also checked.
                if(figure.canMove(field)) {
                    checked_field = true;
                }
            }
            if(checked_field) {
                checked_field = false;
                continue;
            }
            return false;
        }
        return true;
    }

    public void restartGame() {
        Queue<UniversalFigure> tmp = new LinkedList<>(this.figureQueue);
        this.counter = 0;

        /* Zkratil jsem, ale nevim, jestli funguje, prijde mi, ze to nemuze fungovat, ani v puvodnim pripade, protoze ta fronta nebude naplnena.
        Queue<UniversalFigure> tmp = new LinkedList<>();
        Iterator<UniversalFigure> it = this.figureQueue.iterator();
        while(it.hasNext())  {
            tmp.add(it.next());
        }
        */

        for(int i = 0; i < this.board.getSize(); i++){
            for(int j = 0; j < this.board.getSize(); j++){
                this.board.getBoard()[i][j].setFigure(tmp.remove());
                if (this.board.getBoard()[i][j].getFigure() != null){
                    this.board.getBoard()[i][j].getFigure().setBoardField(this.board.getBoard()[i][j]);
                }
            }
        }
    }

    public int getCounter(){
        return this.counter;
    }

    public void setCounter(int c){
        this.counter = c;
    }

    void buildMove(int col, int row, FigureType promotion_type) {
        col -= 1;
        row -= 1;

        if(manualMove.getSourceCol() == -1 && manualMove.getSourceRow() == -1) {
            if(this.board.getField(col, row).getFigure() == null) {
                return;
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
            // TODO - set special

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
            performMove();
        }
        else {
            System.err.println("ERROR: Vnitrni chyba vytvareni pohybu buildMove()!");
            System.exit(1);
        }
    }

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

    //user-friendly variant of printNotation
    void printNotation() {
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
