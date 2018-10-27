package ba.unsa.etf.rpr;

public class Board {
    private ChessPiece[][] board = new ChessPiece[8][8];

    public Board() {
        //board[digit][letter]
        //Black 8 - 7
        //White 1 - 2

        //Populate White pieces
        for(int i = 0; i < 8; i++) {
            String nPos = "";
            nPos += (char)(i+'A') + '2';
            board[1][i] = new Pawn( nPos, ChessPiece.Color.WHITE);
        }
        board[0][0] = new Rook("A1", ChessPiece.Color.WHITE);
        board[0][1] = new Knight("B1", ChessPiece.Color.WHITE);
        board[0][2] = new Bishop("C1", ChessPiece.Color.WHITE);
        board[0][3] = new Queen("D1", ChessPiece.Color.WHITE);
        board[0][4] = new King("E1", ChessPiece.Color.WHITE);
        board[0][5] = new Rook("F1", ChessPiece.Color.WHITE);
        board[0][6] = new Knight("G1", ChessPiece.Color.WHITE);
        board[0][7] = new Bishop("H1", ChessPiece.Color.WHITE);

        //Populate Black pieces
        for(int i = 0; i < 8; i++) {
            String nPos = "";
            nPos += (char)(i+'A') + '7';
            board[6][i] = new Pawn( nPos, ChessPiece.Color.BLACK);
        }
        board[7][0] = new Rook("A8", ChessPiece.Color.BLACK);
        board[7][1] = new Knight("B8", ChessPiece.Color.BLACK);
        board[7][2] = new Bishop("C8", ChessPiece.Color.BLACK);
        board[7][3] = new Queen("D8", ChessPiece.Color.BLACK);
        board[7][4] = new King("E8", ChessPiece.Color.BLACK);
        board[7][5] = new Rook("F8", ChessPiece.Color.BLACK);
        board[7][6] = new Knight("G8", ChessPiece.Color.BLACK);
        board[7][7] = new Bishop("H8", ChessPiece.Color.BLACK);
    }

    public void move(Class type, ChessPiece.Color color, String position) {

    }

    public void move(String oldPosition, String newPosition) {

    }

    public boolean isCheck(ChessPiece.Color color) {
        return false;
    }
}
