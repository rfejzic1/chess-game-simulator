package ba.unsa.etf.rpr;

import static java.lang.Math.abs;

public class Pawn extends ChessPiece {
        private boolean _canEat = false;

    public Pawn(String position, Color color) {
        super(position, color);
    }

    public void canEat(boolean canEat) {
        this._canEat = canEat;
    }

    @Override
    public void validatePosition(String position) {
        char cLtr = getPosition().charAt(0);
        char cDgt = getPosition().charAt(1);
        char ltr = position.toUpperCase().charAt(0);
        char dgt = position.charAt(1);

        int ltrDif = abs(cLtr - ltr);
        int dgtDif = dgt - cDgt;

        if(getColor() == Color.WHITE) {
            if(cDgt == '2') {
                if (dgtDif > 2 || dgtDif <= 0)
                    throw new IllegalChessMoveException("Illegal move!");
                if(ltrDif != 0) {
                    throw new IllegalChessMoveException("Illegal move!");
                }
            } else {
                if(dgtDif > 1 || dgtDif <= 0)
                    throw new IllegalChessMoveException("Illegal move!");

                if(_canEat) {
                    if((ltrDif != 0 && ltrDif != 1)) {
                        throw new IllegalChessMoveException("Illegal move!");
                    }
                }else {
                    if(ltrDif != 0) {
                        throw new IllegalChessMoveException("Illegal move!");
                    }
                }
            }
        }else if(getColor() == Color.BLACK) {
            if(cDgt == '7') {
                if (dgtDif < -2 || dgtDif >= 0)
                    throw new IllegalChessMoveException("Illegal move!");
                if(ltrDif != 0) {
                    throw new IllegalChessMoveException("Illegal move!");
                }
            } else {
                if(dgtDif < -1 || dgtDif >= 0)
                    throw new IllegalChessMoveException("Illegal move!");

                if(_canEat) {
                    if((ltrDif != 0 && ltrDif != 1)) {
                        throw new IllegalChessMoveException("Illegal move!");
                    }
                }else {
                    if(ltrDif != 0) {
                        throw new IllegalChessMoveException("Illegal move!");
                    }
                }
            }
        }

    }
}
