package ba.unsa.etf.rpr;

import java.util.Scanner;

public class Program {

    private static void start() {
        Board b = new Board();
        ChessPiece.Color turn = ChessPiece.Color.WHITE;
        Scanner sc = new Scanner(System.in);

        String input;
        char piece, ltr, dgt;

        while(true) {
            try {
                if(turn == ChessPiece.Color.WHITE) {
                    System.out.print("White move: ");
                }else {
                    System.out.print("Black move: ");
                }

                input = sc.nextLine();

                if(input.length() > 3 || input.length() == 0)
                    throw new IllegalArgumentException("Invalid command");

                if(input.length() == 1) {
                    if(input.charAt(0) == 'X') {
                        break;
                    }else {
                        throw new IllegalArgumentException("Invalid command");
                    }
                }

                if(input.length() == 2) {
                    piece = 'P';
                    ltr = input.charAt(0);
                    dgt = input.charAt(1);
                }else {
                    piece = input.charAt(0);
                    ltr = input.charAt(1);
                    dgt = input.charAt(2);
                }

                b.move(getType(piece), turn, "" + ltr + dgt);

                if(b.isCheck(turn))
                    System.out.println("CHECK!");

                if(turn == ChessPiece.Color.WHITE) {
                    turn = ChessPiece.Color.BLACK;
                }else {
                    turn = ChessPiece.Color.WHITE;
                }

            }catch (Exception err) {
                System.out.println(err.getMessage());
                System.out.println("Sass");
            }
        }

        System.out.println("End");
    }

    public static void main(String[] args) {
//        start();

        Board b = new Board();

        b.move(Pawn.class, ChessPiece.Color.WHITE, "E4");
        b.move(Bishop.class, ChessPiece.Color.WHITE, "A6");
        b.move(Knight.class, ChessPiece.Color.WHITE, "C3");
        b.move(King.class, ChessPiece.Color.WHITE, "E2");
        b.move(King.class, ChessPiece.Color.WHITE, "E3");


    }

    private static Class getType(char piece) {
        Class toRet = null;
        switch (piece) {
            case 'K':
                toRet = King.class;
                break;
            case 'Q':
                toRet = Queen.class;
                break;
            case 'B':
                toRet = Bishop.class;
                break;
            case 'N':
                toRet = Knight.class;
                break;
            case 'R':
                toRet = Rook.class;
            case 'P':
                toRet = Pawn.class;
                break;
        }

        if(toRet == null)
            throw new IllegalArgumentException("No such chess piece.");

        return toRet;
    }
}