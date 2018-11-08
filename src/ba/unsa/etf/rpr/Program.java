package ba.unsa.etf.rpr;

public class Program {
    public static void main(String[] args) {
        Board board = new Board();

        board.move(Rook.class, ChessPiece.Color.BLACK, "H6");

    }
}