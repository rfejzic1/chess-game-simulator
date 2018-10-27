package ba.unsa.etf.rpr;

import static java.lang.Math.abs;

public class Rock extends ChessPiece {
    public Rock(String position, Color color) {
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

        if(ltrDif != 0 && dgtDif != 0)
            throw new IllegalChessMoveException("Illegal move!");
    }
}
