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

        if(ltrDif != 0)
            throw new IllegalChessMoveException("Illegal move!");

        if(getColor() == Color.WHITE) {
            if(cDgt == '2') {
                if (dgtDif > 2)
                    throw new IllegalChessMoveException("White can move two!");
            } else {
                if(dgtDif > 1)
                    throw new IllegalChessMoveException("White can move one!");
            }
        }else if(getColor() == Color.BLACK) {
            if(cDgt == '7') {
                if (dgtDif > 2)
                    throw new IllegalChessMoveException("White can move two!");
            } else {
                if(dgtDif > 1)
                    throw new IllegalChessMoveException("White can move one!");
            }
        }

    }
}
