package ba.unsa.etf.rpr;

import static java.lang.Math.abs;

public class King extends ChessPiece {
    public King(String position, Color color) {
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


        //ltrDif <= 1 || dgtDiff <= 1 su ispravne pozicije, moze na istu poziciju?
        if(ltrDif > 1 || dgtDif > 1)
            throw new IllegalChessMoveException("Illegal move!");
    }
}
