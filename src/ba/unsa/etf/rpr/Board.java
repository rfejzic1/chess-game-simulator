package ba.unsa.etf.rpr;

import java.awt.desktop.QuitEvent;
import java.util.ArrayList;
import java.util.concurrent.BlockingDeque;

public class Board {
    private ArrayList<ChessPiece> board = new ArrayList<>();
    private String wKingPos, bKingPos;

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
        wKingPos = "E1";
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
        bKingPos = "E8";
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

    private void updateKingsPos(ChessPiece curr, String position) {
        if(curr.getClass() == King.class) {
            if(curr.getColor() == ChessPiece.Color.WHITE) {
                wKingPos = position;
            }else {
                bKingPos = position;
            }
        }
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

                    updateKingsPos(curr, position);

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

            updateKingsPos(curr, newPosition);

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
        int canEat;

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
        String currKingsPos = (color == ChessPiece.Color.WHITE)? wKingPos : bKingPos;

        //Check Knight pieces
        int[] klPos = {2, 1, -1, -2, -2, -1, 1, 2};
        int[] kdPos = {1, 2, 2, 1, -1, -2, -2, -1};

        for(int i = 0; i < 8; i++) {
            try {
                String posToCheck = posToCheck = "" + (char)(currKingsPos.charAt(0) + klPos[i]) + (char)(currKingsPos.charAt(1) + kdPos[i]);
                ChessPiece.validateArg(posToCheck);

                ChessPiece threat = getChessPiece(posToCheck);
                if(threat != null && threat.getClass() == Knight.class && threat.getColor() != color)
                    return true;

            }catch (RuntimeException err) {
                // Position not in board
            }
        }

        // Neighbour fields to the king
        int[] lPos = {1, 1, 0, -1, -1, -1, 0, 1};
        int[] dPos = {0, 1, 1, 1, 0, -1, -1, -1};

        for(int i = 0; i < 8; i++) {
            try {
                int posDist = 1;
                String posToCheck = "";

                while(posDist < 8) {
                    posToCheck = "" + (char)(currKingsPos.charAt(0) + lPos[i] * posDist) + (char)(currKingsPos.charAt(1) + dPos[i] * posDist);
                    ChessPiece.validateArg(posToCheck);
                    if(getChessPiece(posToCheck) != null) {
                        break;
                    }
                    posDist += 1;
                }

                // There is a piece in this direction
                if(posDist < 8) {
                    ChessPiece threat = getChessPiece(posToCheck);
                    Class type = threat.getClass();
                    ChessPiece.Color tColor = threat.getColor();

                    boolean[] canCheckMe = {
                            type == Rook.class,
                            type == Bishop.class || (type == Pawn.class && tColor == ChessPiece.Color.BLACK),
                            type == Rook.class,
                            type == Bishop.class || (type == Pawn.class && tColor == ChessPiece.Color.BLACK),
                            type == Rook.class,
                            type == Bishop.class || (type == Pawn.class && tColor == ChessPiece.Color.WHITE),
                            type == Rook.class,
                            type == Bishop.class || (type == Pawn.class && tColor == ChessPiece.Color.WHITE),
                    };

                    if(type == Queen.class || type == King.class || canCheckMe[i]) {
                        // If a piece can threat me and is the opposite color, it's a CHECK
                        if(tColor != color)
                            return true;
                    }
                }
            }catch (RuntimeException err) {
                // No more positions to check in this direction
            }
        }

        return false;
    }
}
