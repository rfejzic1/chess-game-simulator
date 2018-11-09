package ba.unsa.etf.rpr;

import static java.lang.Math.abs;

public class Pawn extends ChessPiece {
    private int _canEat = 0;
    private boolean canRet = false;

    public Pawn(String position, Color color) {
        super(position, color);
    }

    public void canEat(int canEat) {
        this._canEat = canEat;
    }
    public void canRet(boolean canRet) {
        this.canRet = canRet;
    }

    @Override
    public void validatePosition(String position) {
        char cLtr = getPosition().charAt(0);
        char cDgt = getPosition().charAt(1);
        char ltr = position.toUpperCase().charAt(0);
        char dgt = position.charAt(1);

        int ltrDif = ltr - cLtr;
        int dgtDif = dgt - cDgt;

        if(getColor() == Color.WHITE) {
            if(cDgt == '2') {
                if(dgtDif == 2) {
                    if(ltrDif != 0)
                        throw new IllegalChessMoveException("Tu mač bijelog!");
                }else{
                    stepWhite(ltrDif, dgtDif);
                }
            } else {
                stepWhite(ltrDif, dgtDif);
            }
        }else {
            if(cDgt == '7') {
                if(dgtDif == -2 ) {
                    if(ltrDif != 0)
                        throw new IllegalChessMoveException("Tu mač crnog!");
                }else {
                    stepBlack(ltrDif, dgtDif);
                }
            } else {
                stepBlack(ltrDif, dgtDif);
            }
        }
    }

    private void stepBlack(int ltrDif, int dgtDif) {
        if(dgtDif == -1){
            step(ltrDif);
        }else {
            throw new IllegalChessMoveException("Nemere crni u rikvertz!");
        }
    }

    private void step(int ltrDif) {
        if(_canEat == -1) {
            if(ltrDif != -1 && ltrDif != 0)
                throw new IllegalChessMoveException("Nemere desno jest");
        }else if(_canEat == 1) {
            if(ltrDif != 1 && ltrDif != 0)
                throw new IllegalChessMoveException("Nemere ljevo jest");
        }else if(_canEat == 2){
            if(ltrDif != -1 && ltrDif != 0 && ltrDif != 1)
                throw new IllegalChessMoveException("Mogo jest a ne valja polje");
        }else {
            if(ltrDif != 0)
                throw new IllegalChessMoveException("Nemere jest");
        }
    }

    private void stepWhite(int ltrDif, int dgtDif) {
        if(dgtDif == 1){
            step(ltrDif);
        }else {
            throw new IllegalChessMoveException("Nemere bjeli u rikvertz!");
        }
    }
}
