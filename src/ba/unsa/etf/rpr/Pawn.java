package ba.unsa.etf.rpr;

import static java.lang.Math.abs;

public class Pawn extends ChessPiece {
    public Pawn(String position, Color color) {
        super(position, color);
    }

    @Override
    public void validatePosition(String position) {
        char cLtr = getPosition().charAt(0);
        char cDgt = getPosition().charAt(1);
        char ltr = position.toUpperCase().charAt(0);
        char dgt = position.charAt(1);

        int ltrDif = abs(cLtr - ltr);
        int dgtDif = abs(cDgt - dgt);

        //ltrDif == 0 && (cDgt <= 4 && dgtDif <= 2 || cDgt > 4 && dgtDif <= 1), moze na istu poziciju?
        //Pawn ide jedno polje nakon pola table!
        if(!(ltrDif == 0 && (cDgt <= 4 && dgtDif <= 2 || cDgt > 4 && dgtDif <= 1)))
            throw new IllegalChessMoveException("Illegal move!");
    }
}
