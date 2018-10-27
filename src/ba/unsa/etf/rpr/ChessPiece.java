package ba.unsa.etf.rpr;

public abstract class ChessPiece {
    public static enum Color {BLACK, WHITE}

    private Color color;
    private String position;

    public ChessPiece(String pos, Color clr) {
        validateArg(pos);
        color = clr;
        position = pos.toUpperCase();
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
        this.position = position;
    }

    public void validateArg(String pos) {
        if(pos.length() != 2)
            throw new IllegalArgumentException("Illegal move!");

        char ltr = pos.toLowerCase().charAt(0);
        char dgt = pos.charAt(1);

        if(ltr < 'a' || ltr > 'h' || !Character.isDigit(dgt) || dgt == '0')
            throw new IllegalArgumentException("Illegal move!");
    }

    public abstract void validatePosition(String position);
}
