package ba.unsa.etf.rpr;

public class Program {
    public static void main(String[] args) {
        Pawn p = new Pawn("E2", ChessPiece.Color.WHITE);
        p.move("E4");
        System.out.println(p.getPosition() + " " + p.getColor());
    }
}