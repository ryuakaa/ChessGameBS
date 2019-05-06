/**
 * ChessFields
 */
public class ChessFields {

    private int posX;
    private int posY;
    private ChessPiece p;
    private boolean empty;

    public ChessFields(int x, int y, ChessPiece p) {
        this.posX = x;
        this.posY = y;
        this.p = p;

        p.setPos(posX, posY);
    }

    public ChessFields(int x, int y, boolean em) {
        this.posX = x;
        this.posY = y;
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
            return "---";
        } else {
            return p.getTeam().charAt(0)+"/"+p.getName().charAt(0);
        }

    }

}