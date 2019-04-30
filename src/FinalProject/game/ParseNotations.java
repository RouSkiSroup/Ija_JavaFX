package FinalProject.game;

import FinalProject.figures.FigureType;
import FinalProject.common.ReadFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used for parsing the file with moves.
 * @author Filip Jerabek (xjerab24), Daniel Konecny (xkonec75)
 * @version 3.0
 */
public class ParseNotations {
    /**
     * Loads a file and parses it line by line.
     * @param file  Name of the file that contains moves.
     * @return  List of moves loaded in special class.
     */
    public List<OneMove> parseFile(String file) {
        List<OneMove> notation = new ArrayList<>();
        ReadFile reader = new ReadFile(file);
        String line = reader.getLine();
        while(line != null){
            //System.out.println(line);
            String[] parsed_line = line.split(" ");
            if(parsed_line.length < 2) {
                System.err.println("Spatny zapis notace.");
                System.exit(1);
            }
            notation.add(parseMove(parsed_line[1], true));
            notation.add(parseMove(parsed_line[2], false));
            line = reader.getLine();
        }
        return notation;
    }

    /**
     * Processes one move in the notation character by character.
     * @param moveAsString  Move in chess notation.
     * @param white_player  Which players turn it is.
     * @return  One move processed to special class.
     */
    private OneMove parseMove(String moveAsString, boolean white_player) {
        OneMove move = new OneMove(white_player,null,-1, -1, false,
                -1, -1, null, null);
        int res = parseFigureType(moveAsString, move);
        moveAsString = moveAsString.substring(res);
        res = parseDistinguish(moveAsString, move);
        moveAsString = moveAsString.substring(res);
        res = parseCapture(moveAsString, move);
        moveAsString = moveAsString.substring(res);
        res = parseLocations(moveAsString, move);
        moveAsString = moveAsString.substring(res);
        res = parsePromotion(moveAsString, move);
        moveAsString = moveAsString.substring(res);
        res = parseSpecial(moveAsString, move);
        if(moveAsString.substring(res).length() > 0) {
            System.err.println("Notace tahu prilis dlouha!");
            System.exit(1);
        }
        return move;
    }

    /**
     * Processes the type of figure in move.
     * @param moveAsString  Move in chess notation.
     * @param move          Class of move used inside the program.
     * @return  How many characters were processed.
     */
    private int parseFigureType(String moveAsString, OneMove move) {
        if(Character.isLetter(moveAsString.charAt(0)) && Character.isUpperCase(moveAsString.charAt(0))) {
            move.setFigure(FigureType.valueOf(Character.toString(moveAsString.charAt(0))));
            return 1;
        }
        else {
            move.setFigure(FigureType.p);
            return 0;
        }
    }

    /**
     * Processes specified source used if short notation wouldn't be enough.
     * @param moveAsString  Move in chess notation.
     * @param move          Class of move used inside the program.
     * @return  How many characters were processed.
     */
    private int parseDistinguish(String moveAsString, OneMove move) {
        if(Character.isLetter(moveAsString.charAt(0)) && Character.isUpperCase(moveAsString.charAt(0)) &&
                Character.isLetter(moveAsString.charAt(1)) && Character.isUpperCase(moveAsString.charAt(1))) {
            move.setSourceCol((int)moveAsString.charAt(0) - 97);
            return 1;
        }
        else if(Character.isDigit(moveAsString.charAt(0)) &&
                Character.isLetter(moveAsString.charAt(1)) && Character.isLowerCase(moveAsString.charAt(1))) {
            move.setSourceRow(Character.getNumericValue(moveAsString.charAt(0)) - 1);
            return 1;
        }
        else {
            return 0;
        }
    }

    /**
     * Processes if any figure is captured during this move.
     * @param moveAsString  Move in chess notation.
     * @param move          Class of move used inside the program.
     * @return  How many characters were processed.
     */
    private int parseCapture(String moveAsString, OneMove move) {
        if(moveAsString.charAt(0) == 'x') {
            move.setCapture(true);
            return 1;
        }
        else {
            return 0;
        }
    }

    /**
     * Processes source and destination location of figure in move.
     * @param moveAsString  Move in chess notation.
     * @param move          Class of move used inside the program.
     * @return  How many characters were processed.
     */
    private int parseLocations(String moveAsString, OneMove move) {
        // Loading source and destination
        if(moveAsString.length() > 3 &&
                Character.isLetter(moveAsString.charAt(0)) && Character.isLowerCase(moveAsString.charAt(0)) &&
                Character.isDigit(moveAsString.charAt(1)) &&
                Character.isLetter(moveAsString.charAt(2)) && Character.isLowerCase(moveAsString.charAt(2)) &&
                Character.isDigit(moveAsString.charAt(3))) {
            move.setSourceCol((int)moveAsString.charAt(0) - 97);
            move.setSourceRow(Character.getNumericValue(moveAsString.charAt(1)) - 1);
            move.setDestinationCol((int)moveAsString.charAt(2) - 97);
            move.setDestinationRow(Character.getNumericValue(moveAsString.charAt(3)) - 1);;
            return 4;
        }
        // Loading only destination
        else if(moveAsString.length() > 1  &&
                Character.isLetter(moveAsString.charAt(0)) && Character.isLowerCase(moveAsString.charAt(0)) &&
                Character.isDigit(moveAsString.charAt(1))) {
            move.setDestinationCol((int)moveAsString.charAt(0) - 97);
            move.setDestinationRow(Character.getNumericValue(moveAsString.charAt(1)) - 1);
            return 2;
        }
        else {
            System.err.println("Chybi urceni lokace v notaci!");
            System.exit(1);
            return 0;
        }
    }

    /**
     * Processes the type of figure used in case of promotion.
     * @param moveAsString  Move in chess notation.
     * @param move          Class of move used inside the program.
     * @return  How many characters were processed.
     */
    private int parsePromotion(String moveAsString, OneMove move) {
        if(moveAsString.length() > 0 && Character.isLetter(moveAsString.charAt(0)) && Character.isUpperCase(moveAsString.charAt(0))) {
            if(move.getFigure() == FigureType.p &&
                    (move.getDestinationRow() == 0 || move.getDestinationRow() == 7)) {
                switch(moveAsString.charAt(0)) {
                    case 'D':
                        move.setPromotion(FigureType.D);
                        return 1;
                    case 'V':
                        move.setPromotion(FigureType.V);
                        return 1;
                    case 'S':
                        move.setPromotion(FigureType.S);
                        return 1;
                    case 'J':
                        move.setPromotion(FigureType.J);
                        return 1;
                    default:
                        System.err.println("Chybna vymena figurky v notaci!");
                        System.exit(1);
                        return 1;
                }
            }
            else {
                System.err.println("Neni povolene vymenit figurku, i kdyz to notace pozaduje.");
                System.exit(1);
                return 1;
            }
        }
        else {
            return 0;
        }
    }

    /**
     * Processes the special character in move.
     * @param moveAsString  Move in chess notation.
     * @param move          Class of move used inside the program.
     * @return  How many characters were processed.
     */
    private int parseSpecial(String moveAsString, OneMove move) {
        if(moveAsString.length() > 0){
            if(moveAsString.charAt(0) == '+') {
                move.setSpecial(SpecialState.CHECK);
            }
            else if(moveAsString.charAt(0) == '#') {
                move.setSpecial(SpecialState.CHECKMATE);
            }
            else {
                System.err.println("Neznamy specialni znak v notaci");
                System.exit(1);
            }
            return 1;
        }
        else {
            return 0;
        }
    }
}
