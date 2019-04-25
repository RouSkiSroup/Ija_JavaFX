package FinalProject;

import FinalProject.common.FigureType;
import FinalProject.common.NotationType;
import FinalProject.common.UniversalFigure;
import FinalProject.game.*;

import java.lang.reflect.Field;
import java.util.*;

public class Chess {
    private Board board;
    private List<OneMove> moves;
    private Queue<UniversalFigure> figureQueue = new LinkedList<>();
    private int counter;

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

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    // Call this when moves are performed automatically.
    public boolean automaticMove(OneMove move) {
        UniversalFigure figure = getFigureFromNotation(move);
        BoardField field = getFieldFromNotation(move);
        if(figure != null){
            moveFigure(figure, field);
            UniversalFigure newFigure = promote(figure, move);
            if(newFigure != null){
                field.setFigure(newFigure);
            }
            checkCheck();
            return true;
        }
        else {
            return false;
        }
    }

    private UniversalFigure getFigureFromNotation(OneMove move) {
        ArrayList<UniversalFigure> figures = board.getFiguresOfType(move.getFigure(), move.getWhitePlayer());
        for(UniversalFigure figure: figures) {
            if(figure.canMove(board.getField(move.getDestinationCol(), move.getDestinationRow()))) {
                if((move.getSourceCol() == -1 || move.getSourceCol() == figure.getBoardField().getCol()) &&
                        (move.getSourceRow() == -1 || move.getSourceRow() == figure.getBoardField().getRow())) {
                    return figure;
                }
            }
            else {
                System.err.println("Figura se nemuze pohnout na zadane misto.");
                System.exit(1);
            }
        }
        return null;
    }

    private void checkCheck() {
        ArrayList<UniversalFigure> whiteKing = this.board.getFiguresOfType(FigureType.K, true);
        ArrayList<UniversalFigure> blackKing = this.board.getFiguresOfType(FigureType.K, false);
        if (canAttackKing(whiteKing.get(0))){
            System.out.println("Bilej je v sachu");
        }
        if (canAttackKing(blackKing.get(0))){
            System.out.println("Cernej je v sachu");
        }
    }

    private boolean canAttackKing(UniversalFigure king) {
        ArrayList<UniversalFigure> figures = this.board.getFiguresOfPlayer(!king.isWhite());
        BoardField kingField = king.getBoardField();
        for(UniversalFigure figure: figures) {
            if(figure.canMove(kingField)) {
                System.out.print("Kral kterej je v sachu: ");
                king.printState();
                System.out.print("Figurka, ktera ho sachuje: ");
                figure.printState();
                return true;
            }
        }
        return false;
    }

    private BoardField getFieldFromNotation(OneMove move) {
        return this.board.getField(move.getDestinationCol(), move.getDestinationRow());
    }

    // Call this when player moves figure manually.
    public boolean manualMove(UniversalFigure figure, BoardField field) {
        if(figure.canMove(field)){
            int index = this.moves.size();   // TODO - nebude se pocitat jako konec pole, ale podle aktualni pozice v pruchodu notaci
            // TODO - smazat nasledujici notaci
            OneMove move = new OneMove(index % 2 == 0, figure.getType(),
                    figure.getBoardField().getCol(), figure.getBoardField().getRow(),
                    field.getCol(), field.getRow(),null, null);
            moves.add(move);
            moveFigure(figure, field);
            return true;
        }
        else {
            return false;
        }
    }

    private void moveFigure(UniversalFigure figure, BoardField field) {
        figure.getBoardField().setFigure(null);
        figure.setBoardField(field);
        field.setFigure(figure);
    }

    public void nextMove() {
        System.out.println("Tah cislo " + (counter + 1));
        automaticMove(this.moves.get(counter));
        counter += 1;
    }

    private void manualChange(UniversalFigure actPawn) {
        if(actPawn.getBoardField().getRow() == 0 || actPawn.getBoardField().getRow() == 7){
            //otevre se nejakej formular pro volbu premeny pesce
            FigureType type = FigureType.D;
            //createNewFigure(type);

        }
    }

    private UniversalFigure promote(UniversalFigure pawn, OneMove move) {
        if(pawn.getBoardField().getRow() == 0 || pawn.getBoardField().getRow() == 7) {
            if(move.getPromotion() != null) {
                FigureType type = move.getPromotion();
                return createNewFigure(pawn, type);
            }
        }
        return null;
    }

    private UniversalFigure createNewFigure(UniversalFigure oldFigure, FigureType type) {
        UniversalFigure newFigure;
        // No need to control the type - notation is controlled and user can only choose correct option.
        switch (type) {
            case D: {
                newFigure = new Queen(oldFigure.getBoardField(), oldFigure.isWhite());
                return newFigure;
            }
            case V: {
                newFigure = new Rook(oldFigure.getBoardField(), oldFigure.isWhite());
                return newFigure;
            }
            case S: {
                newFigure = new Bishop(oldFigure.getBoardField(), oldFigure.isWhite());
                return newFigure;
            }
            case J: {
                newFigure = new Knight(oldFigure.getBoardField(), oldFigure.isWhite());
                return newFigure;
            }
        }
        return null;
    }

    public void restartGame() {
        Queue<UniversalFigure> tmp = new LinkedList<>(this.figureQueue);

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

    public void printBoard() {
        for(int i = 0; i < this.board.getSize(); i++){
            for(int j = 0; j < this.board.getSize(); j++){
                if (this.board.getBoard()[i][j].getFigure() == null){
                    System.out.println("null");
                }
                else{
                    this.board.getBoard()[i][j].getFigure().printState();
                }
            }
            System.out.println();
        }
    }

    public void printBoardReadable() {
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

    public void debugNotation() {
        for(OneMove move: moves) {
            move.print();
        }
    }

    void parseNotations(String file) {
        ParseNotations parser = new ParseNotations();
        this.moves = parser.parse(file);
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

    public void clearListFrom(int pos) {
        for(int i = pos; i < moves.size(); i++){
            moves.set(i,null);
        }
    }
}
