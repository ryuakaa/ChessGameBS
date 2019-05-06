import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * ChessPiece
 */
public class ChessPiece {
	private int posX;
	private int posY;
	private boolean alive;
	private String team;
	private String name;
	private Image pawnb;
	private Image pawnw;
	private Image bishopb;
	private Image bishopw;
	private Image kingb;
	private Image kingw;
	private Image knightb;
	private Image knightw;
	private Image queenb;
	private Image queenw;
	private Image rookb;
	private Image rookw;

	public ChessPiece(int posX, int posY, boolean alive) {
		this.posX = posX;
		this.posY = posY;
		this.alive = alive;
		// 65 x 65 px
		ImageIcon pb = new ImageIcon("assets/pawnb.png");
		ImageIcon pw = new ImageIcon("assets/pawnw.png");
		ImageIcon bb = new ImageIcon("assets/bishopb.png");
		ImageIcon bw = new ImageIcon("assets/bishopw.png");
		ImageIcon kb = new ImageIcon("assets/kingb.png");
		ImageIcon kw = new ImageIcon("assets/kingw.png");
		ImageIcon knb = new ImageIcon("assets/knightb.png");
		ImageIcon knw = new ImageIcon("assets/knightw.png");
		ImageIcon qb = new ImageIcon("assets/queenb.png");
		ImageIcon qw = new ImageIcon("assets/queenw.png");
		ImageIcon rb = new ImageIcon("assets/rookb.png");
		ImageIcon rw = new ImageIcon("assets/rookw.png");

		pawnb = pb.getImage();
		pawnw = pw.getImage();
		bishopb = bb.getImage();
		bishopw = bw.getImage();
		kingb = kb.getImage();
		kingw = kw.getImage();
		knightb = knb.getImage();
		knightw = knw.getImage();
		queenb = qb.getImage();
		queenw = qw.getImage();
		rookb = rb.getImage();
		rookw = rw.getImage();
	}

	public Image getImage() {
		if (team == "black") {
			switch (name) {
			case "pawn":
				return pawnb;
			case "bishop":
				return bishopb;
			case "king":
				return kingb;
			case "knight":
				return knightb;
			case "queen":
				return queenb;
			case "rook":
				return rookb;
			default:
				return pawnb;
			}
		} else {
			switch (name) {
			case "pawn":
				return pawnw;
			case "bishop":
				return bishopw;
			case "king":
				return kingw;
			case "knight":
				return knightw;
			case "queen":
				return queenw;
			case "rook":
				return rookw;
			default:
				return pawnw;
			}
		}
	}
	
	public String getFirstTeamChar() {
		return Character.toString(team.charAt(0));
	}

	public String getFirstPieceChar() {
		return Character.toString(name.charAt(2));
	}

	public void setPos(int x, int y) {
		posX = x;
		posY = y;
	}

	public void setAlive(boolean al) {
		alive = al;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setTeam(String te) {
		team = te;
	}

	public void setName(String na) {
		name = na;
	}

	public void setData(String te, String na) {
		setTeam(te);
		setName(na);
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public String getName() {
		return name;
	}

	public String getTeam() {
		return team;
	}

	public String toString() {
		if (alive) {
			return posX + "/" + posY + " " + team + " " + name + " is alive";
		} else {
			return posX + "/" + posY + " " + team + " " + name + " is dead";
		}
	}

}