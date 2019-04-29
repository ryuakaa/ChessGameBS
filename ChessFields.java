/**
 * ChessFields
 */
public class ChessFields {

    private int posX;
    private int posY;
    private ChessPiece p;
    private boolean empty;

    public ChessFields(int posX, int posY, ChessPiece p) {
        this.posX = posX;
        this.posY = posY;
        this.p = p;

        p.setPos(posX, posY);
    }

    public ChessFields(int posX, int posY, boolean em) {
        this.posX = posX;
        this.posY = posY;
        this.empty = em;
    }

    public boolean isEmpty() {
        return empty;
    }

    public ChessPiece getPiece() {
        return p;
    }

    public void setEmpty(boolean v) {
        empty = v;
    }

    public void setPiece(ChessPiece pi) {
        this.p = pi;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getFieldX() {
        return posX / 65;
    }

    public int getFieldY() {
        return posY / 65;
    }

    public String firstLetter() {
        if (empty) {
            return "-";
        } else {
            return Character.toString(p.getName().charAt(0));
        }

    }

}