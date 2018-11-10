package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {

    @Test
    void move1() {
        Pawn p = new Pawn("E2", ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> p.move("E4")
        );
    }

    @Test
    void move2() {
        Pawn p = new Pawn("E2", ChessPiece.Color.WHITE);
        assertDoesNotThrow(
                () -> p.move("E4")
        );
        assertDoesNotThrow(
                () -> p.move("E5")
        );
        assertThrows( IllegalChessMoveException.class,
                () -> p.move("E7")
        );
    }

    @Test
    void move2B() {
        Pawn p = new Pawn("E7", ChessPiece.Color.BLACK);
        assertDoesNotThrow(
                () -> p.move("E5")
        );
        assertDoesNotThrow(
                () -> p.move("E4")
        );
        assertThrows( IllegalChessMoveException.class,
                () -> p.move("E2")
        );
    }

    @Test
    void noEatRight() {
        Pawn p = new Pawn("E2", ChessPiece.Color.WHITE);
        assertThrows( IllegalChessMoveException.class,
                () -> p.move("F3")
        );
    }

    @Test
    void noEatLeft() {
        Pawn p = new Pawn("E2", ChessPiece.Color.WHITE);
        assertThrows( IllegalChessMoveException.class,
                () -> p.move("D3")
        );
    }

    @Test
    void noEatRightB() {
        Pawn p = new Pawn("E7", ChessPiece.Color.BLACK);
        assertThrows( IllegalChessMoveException.class,
                () -> p.move("F6")
        );
    }

    @Test
    void noEatLeftB() {
        Pawn p = new Pawn("E7", ChessPiece.Color.BLACK);
        assertThrows( IllegalChessMoveException.class,
                () -> p.move("D6")
        );
    }

    @Test
    void noHorseMove1() {
        Pawn p = new Pawn("E2", ChessPiece.Color.WHITE);
        assertThrows( IllegalChessMoveException.class,
                () -> p.move("D4")
        );
    }

    @Test
    void noHorseMove2() {
        Pawn p = new Pawn("E2", ChessPiece.Color.WHITE);
        assertThrows( IllegalChessMoveException.class,
                () -> p.move("F4")
        );
    }

    @Test
    void noHorseMove1B() {
        Pawn p = new Pawn("E7", ChessPiece.Color.BLACK);
        assertThrows( IllegalChessMoveException.class,
                () -> p.move("D5")
        );
    }

    @Test
    void noHorseMove2B() {
        Pawn p = new Pawn("E7", ChessPiece.Color.BLACK);
        assertThrows( IllegalChessMoveException.class,
                () -> p.move("F5")
        );
    }

    @Test
    void neverGoBack() {
        Pawn pw = new Pawn("E4", ChessPiece.Color.WHITE);
        assertThrows( IllegalChessMoveException.class,
                () -> pw.move("E3")
        );

        Pawn pb = new Pawn("E5", ChessPiece.Color.BLACK);
        assertThrows( IllegalChessMoveException.class,
                () -> pb.move("E6")
        );
    }

}