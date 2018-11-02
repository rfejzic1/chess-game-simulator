package ba.unsa.etf.rpr;

import java.util.ArrayList;

public class Board {
    private ArrayList<ChessPiece> board = new ArrayList<>();

    public Board() {
        //Populate White pieces
        for(int i = 0; i < 8; i++) {
            String nPos = "";
            nPos += (char)(i+'A') + '2';
            board.add(new Pawn( nPos, ChessPiece.Color.WHITE));
        }
        board.add(new Rook("A1", ChessPiece.Color.WHITE));
        board.add(new Knight("B1", ChessPiece.Color.WHITE));
        board.add(new Bishop("C1", ChessPiece.Color.WHITE));
        board.add(new Queen("D1", ChessPiece.Color.WHITE));
        board.add(new King("E1", ChessPiece.Color.WHITE));
        board.add(new Rook("F1", ChessPiece.Color.WHITE));
        board.add(new Knight("G1", ChessPiece.Color.WHITE));
        board.add(new Bishop("H1", ChessPiece.Color.WHITE));

        //Populate Black pieces
        for(int i = 0; i < 8; i++) {
            String nPos = "";
            nPos += (char)(i+'A') + '7';
            board.add(new Pawn( nPos, ChessPiece.Color.BLACK));
        }
        board.add(new Rook("A8", ChessPiece.Color.BLACK));
        board.add(new Knight("B8", ChessPiece.Color.BLACK));
        board.add(new Bishop("C8", ChessPiece.Color.BLACK));
        board.add(new Queen("D8", ChessPiece.Color.BLACK));
        board.add(new King("E8", ChessPiece.Color.BLACK));
        board.add(new Rook("F8", ChessPiece.Color.BLACK));
        board.add(new Knight("G8", ChessPiece.Color.BLACK));
        board.add(new Bishop("H8", ChessPiece.Color.BLACK));
    }

    public void move(Class type, ChessPiece.Color color, String position) {

    }

    public void move(String oldPosition, String newPosition) {

    }

    public boolean isCheck(ChessPiece.Color color) {
        return false;
    }
}
