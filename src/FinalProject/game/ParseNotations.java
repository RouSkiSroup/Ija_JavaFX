package FinalProject.game;

import FinalProject.common.FigureType;
// import FinalProject.common.NotationType;
import FinalProject.common.SpecialState;

import java.util.ArrayList;
import java.util.List;

public class ParseNotations {
    public ParseNotations() {

    }

    public List<OneMove> parse(String file) {
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

    private OneMove parseMove(String moveAsString, boolean white_player) {
        OneMove move = new OneMove(white_player,null,-1, -1,
                -1, -1, null, null);
        int res = parseFigureType(moveAsString, move);
        moveAsString = moveAsString.substring(res);
        res = parseDistinguish(moveAsString, move);
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

    private int parseFigureType(String line, OneMove move) {
        if(isUppercaseLetter(line.charAt(0))) {
            move.setFigure(FigureType.valueOf(Character.toString(line.charAt(0))));
            return 1;
        }
        else {
            move.setFigure(FigureType.p);
            return 0;
        }
    }

    private int parseDistinguish(String line, OneMove move) {
        if(isLowercaseLetter(line.charAt(0)) && isLowercaseLetter(line.charAt(1))) {
            move.setSourceCol((int)line.charAt(0) - 97);
            return 1;
        }
        else if(isDigit(line.charAt(0)) && isLowercaseLetter(line.charAt(1))) {
            move.setSourceRow(Character.getNumericValue(line.charAt(0)) - 1);
            return 1;
        }
        else {
            return 0;
        }
    }

    private boolean isUppercaseLetter(char letter) {
        return (Character.isLetter(letter) && Character.isUpperCase(letter));
    }

    private boolean isLowercaseLetter(char letter) {
        return (Character.isLetter(letter) && Character.isLowerCase(letter));
    }

    private boolean isDigit(char letter){
        return (Character.isDigit(letter));
    }

    private int parseLocations(String line, OneMove move) {
        // Loading source and destination
        if(line.length() > 3 && isLowercaseLetter(line.charAt(0)) && isDigit(line.charAt(1)) &&
                isLowercaseLetter(line.charAt(2)) && isDigit(line.charAt(3))) {
            move.setSourceCol((int)line.charAt(0) - 97);
            move.setSourceRow(Character.getNumericValue(line.charAt(1)) - 1);
            move.setDestinationCol((int)line.charAt(2) - 97);
            move.setDestinationRow(Character.getNumericValue(line.charAt(3)) - 1);
            // move.type = NotationType.Long;
            return 4;
        }
        // Loading only destination
        else if(line.length() > 1  && isLowercaseLetter(line.charAt(0)) && isDigit(line.charAt(1))) {
            move.setDestinationCol((int)line.charAt(0) - 97);
            move.setDestinationRow(Character.getNumericValue(line.charAt(1)) - 1);
            // move.type = NotationType.Short;
            return 2;
        }
        else {
            System.err.println("Chybi urceni lokace v notaci!");
            System.exit(1);
            return 0;
        }
    }

    private int parsePromotion(String line, OneMove move) {
        if(line.length() > 0 && isUppercaseLetter(line.charAt(0))) {
            switch(line.charAt(0)) {
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
            return 0;
        }
    }

    private int parseSpecial(String line, OneMove move) {
        if(line.length() > 0){
            if(line.charAt(0) == '+') {
                move.setSpecial(SpecialState.CHECK);
            }
            else if(line.charAt(0) == '#') {
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
