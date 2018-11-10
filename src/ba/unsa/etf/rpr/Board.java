package ba.unsa.etf.rpr;

import java.util.ArrayList;

public class Board {
    private ArrayList<ChessPiece> board = new ArrayList<>();

    public Board() {
        //Populate White pieces
        for(int i = 0; i < 8; i++) {
            String nPos = "";
            nPos += (char)(i+'A') + "2";
            board.add(new Pawn( nPos, ChessPiece.Color.WHITE));
        }
        board.add(new Rook("A1", ChessPiece.Color.WHITE));
        board.add(new Knight("B1", ChessPiece.Color.WHITE));
        board.add(new Bishop("C1", ChessPiece.Color.WHITE));
        board.add(new Queen("D1", ChessPiece.Color.WHITE));
        board.add(new King("E1", ChessPiece.Color.WHITE));
        board.add(new Bishop("F1", ChessPiece.Color.WHITE));
        board.add(new Knight("G1", ChessPiece.Color.WHITE));
        board.add(new Rook("H1", ChessPiece.Color.WHITE));

        //Populate Black pieces
        for(int i = 0; i < 8; i++) {
            String nPos = "";
            nPos += (char)(i+'A') + "7";

            board.add(new Pawn( nPos, ChessPiece.Color.BLACK));
        }
        board.add(new Rook("A8", ChessPiece.Color.BLACK));
        board.add(new Knight("B8", ChessPiece.Color.BLACK));
        board.add(new Bishop("C8", ChessPiece.Color.BLACK));
        board.add(new Queen("D8", ChessPiece.Color.BLACK));
        board.add(new King("E8", ChessPiece.Color.BLACK));
        board.add(new Bishop("F8", ChessPiece.Color.BLACK));
        board.add(new Knight("G8", ChessPiece.Color.BLACK));
        board.add(new Rook("H8", ChessPiece.Color.BLACK));
    }

    private ChessPiece getChessPiece(String position) {
        for(ChessPiece chsp : board) {
            if(chsp.getPosition().equals(position)) {
                return chsp;
            }
        }
        return null;
    }

    private boolean pathClear(Class type, String a, String b) {
        if(type == Knight.class || type == King.class)
            return true;

        char aLtr = a.toUpperCase().charAt(0);
        char aDgt = a.charAt(1);
        char bLtr = b.toUpperCase().charAt(0);
        char bDgt = b.charAt(1);

        int ltrDif = bLtr - aLtr;
        int dgtDif = bDgt - aDgt;

        int lChange = Integer.signum(Integer.compare(0, ltrDif));
        int dChange = Integer.signum(Integer.compare(0, dgtDif));

        //Do not check position b, a will not be checked anyways
        ltrDif += lChange;
        dgtDif += dChange;

        while(ltrDif != 0 || dgtDif != 0) {
            String currPos = "" + (char)(bLtr - ltrDif) + (char)(bDgt - dgtDif);

            if(getChessPiece(currPos) != null)
                return false;

            ltrDif += lChange;
            dgtDif += dChange;
        }

        return true;
    }

    public void move(Class type, ChessPiece.Color color, String position) {
        ChessPiece onTargetPos = getChessPiece(position);
        boolean found = false;

        for(int i = 0; i < board.size(); i++) {
            ChessPiece curr = board.get(i);

            if(curr.getColor() == color && (curr.getClass() == type)) {
                String initPos = curr.getPosition();

                try {
                    if(type == Pawn.class) {
                        checkIfCanEat((Pawn)curr);
                    }

                    curr.move(position);

                    if(onTargetPos != null && onTargetPos.getColor() == curr.getColor())
                        throw new IllegalChessMoveException("Illegal move");

                    if(!pathClear(curr.getClass(), initPos, position))
                        throw new IllegalChessMoveException("Illegal move");

                    if(onTargetPos != null){
                        board.remove(onTargetPos);
                    }

                    found = true;
                    break;
                }catch (IllegalChessMoveException err) {
                    if(!curr.getPosition().equals(initPos))
                        curr.move(initPos);
                }
            }
        }

        if(!found) {
            throw new IllegalChessMoveException("Illegal move");
        }
    }

    public void move(String oldPosition, String newPosition) {
        ChessPiece curr = getChessPiece(oldPosition);

        if(curr == null)
            throw new IllegalArgumentException("Illegal move");

        ChessPiece onTargetPos = getChessPiece(newPosition);

        String initPos = curr.getPosition();

        try {
            if(curr.getClass() == Pawn.class) {
                checkIfCanEat((Pawn)curr);
            }

            curr.move(newPosition);

            if(onTargetPos != null && onTargetPos.getColor() == curr.getColor())
                throw new IllegalChessMoveException("Illegal move");

            if(!pathClear(curr.getClass(), initPos, newPosition))
                throw new IllegalChessMoveException("Illegal move");

            if(onTargetPos != null){
                board.remove(onTargetPos);
            }
        }catch (IllegalChessMoveException err) {
            if(!curr.getPosition().equals(initPos))
                curr.move(initPos);
            throw err;
        }
    }

    private void checkIfCanEat(Pawn pawn) {
        String lDiag, rDiag;

        if(pawn.getColor() == ChessPiece.Color.WHITE) {
            lDiag = "" + (char)(pawn.getPosition().charAt(0) - 1) + (char)(pawn.getPosition().charAt(1) + 1);
            rDiag = "" + (char)(pawn.getPosition().charAt(0) + 1) + (char)(pawn.getPosition().charAt(1) + 1);
        }else {
            lDiag = "" + (char)(pawn.getPosition().charAt(0) - 1) + (char)(pawn.getPosition().charAt(1) - 1);
            rDiag = "" + (char)(pawn.getPosition().charAt(0) + 1) + (char)(pawn.getPosition().charAt(1) - 1);
        }
        int canEat = 0;

        ChessPiece lPiece = getChessPiece(lDiag);
        ChessPiece rPiece = getChessPiece(rDiag);

        if(lPiece != null){
            canEat = -1;
            if(rPiece != null)
                canEat = 2;
        }else {
            canEat = 0;
            if(rPiece != null)
                canEat = 1;
        }

        pawn.canEat(canEat);
    }

    public boolean isCheck(ChessPiece.Color color) {
        return false;
    }
}
