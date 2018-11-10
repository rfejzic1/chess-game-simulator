package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {

    @Test
    void validatePosition() {
        Queen q = new Queen("D1", ChessPiece.Color.WHITE);
        assertThrows( IllegalChessMoveException.class,
                () -> q.move("E5")
        );
    }

    @Test
    void illegalArgumentLengthTest() {
        assertThrows( IllegalArgumentException.class,
                () -> new Queen("NoPos", ChessPiece.Color.WHITE)
        );
    }

    @Test
    void illegalArgumentFormatTest() {
        assertThrows( IllegalArgumentException.class,
                () -> new Queen("4A", ChessPiece.Color.WHITE)
        );
    }

    @Test
    void illegalArgumentFormatTest2() {
        assertThrows( IllegalArgumentException.class,
                () -> new Queen("Z0", ChessPiece.Color.WHITE)
        );
    }
}