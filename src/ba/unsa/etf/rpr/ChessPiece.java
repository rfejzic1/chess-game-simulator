package ba.unsa.etf.rpr;

public abstract class ChessPiece {
    public static enum Color {BLACK, WHITE}

    private Color color;
    private String position;

    public ChessPiece(String pos, Color clr) {
        validateArg(pos);
        color = clr;
        position = "" + pos.toUpperCase().charAt(0) + pos.charAt(1);
    }

    public String getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    public void move(String position) {
        validateArg(position);
        validatePosition(position);
        this.position = "" + position.toUpperCase().charAt(0) + position.charAt(1);
    }

    public static void validateArg(String pos) {
        if(pos.length() != 2)
            throw new IllegalArgumentException("Illegal move");

        char ltr = pos.toUpperCase().charAt(0);
        char dgt = pos.charAt(1);

        if(ltr < 'A' || ltr > 'H' || !Character.isDigit(dgt) || dgt == '0' || dgt == '9')
            throw new IllegalArgumentException("Illegal move");
    }

    public abstract void validatePosition(String position);
}
