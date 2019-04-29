import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.RoundRectangle2D;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Chess Game fuer Berufsschulelele
 * 
 * @author Julian Thiele
 * @version 0.15
 */
public class ChessGame extends JPanel implements ActionListener, MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;
	private ChessFields[][] b = new ChessFields[8][8];
	private ChessFileHandler cfh = new ChessFileHandler(b);
	private Image chessboard;
	private boolean running;
	private boolean dragging = false;
	private int width = 750;
	private int height = 600;
	private int offsetX = 41;
	private int offsetY = 44;
	public int size = 65;
	private int startmouseX;
	private int startmouseY;
	private int mouseX;
	private int mouseY;
	private String statusText = "";

	public ChessGame() {

		// window settings
		setPreferredSize(new Dimension(width, height));
		setFocusable(true);
		setBackground(Color.DARK_GRAY);

		// initialize Board
		ImageIcon cb = new ImageIcon("assets/chessboard.png");
		chessboard = cb.getImage();
		chessboard = chessboard.getScaledInstance(600, 600, Image.SCALE_SMOOTH);

		// adds listeners
		addMouseListener(this);
		addMouseMotionListener(this);

		// open dialog and ask if open file or create new
		int jop = JOptionPane.showConfirmDialog(null, "Are you the first player?", "ChessGame File for two players!",
				JOptionPane.YES_NO_OPTION);
		if (jop == JOptionPane.YES_OPTION) {
			// create file
			cfh.createNewFile();
		} else if (jop == JOptionPane.NO_OPTION) {
			// open file
			cfh.openFile();
		} else {
			System.err.println("Open file aborted! Program will close now");

		}

		// initialize Chess Pieces on Fields
		initTeams();
		// printAllFields();

		// start painting things
		running = true;

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (running) {
			// draws the ui
			drawUI(g);
			// draws chessboard
			g.drawImage(chessboard, 0, 0, this);
			// draws each chesspiece
			for (int j = 0; j < 8; j++) {
				for (int i = 0; i < 8; i++) {
					if (!b[j][i].isEmpty()) {
						// for all chess chesspieces
						g.drawImage(b[j][i].getPiece().getImage(), b[j][i].getPiece().getPosX(),
								b[j][i].getPiece().getPosY(), this);
					}
				}
			}
			Toolkit.getDefaultToolkit().sync();
		}
	}

	private void drawUI(Graphics g) {
		// button
		g.setColor(Color.LIGHT_GRAY);
		g.fillRoundRect(620, 550, 110, 30, 12, 12);
		g.setColor(Color.BLACK);
		g.drawRoundRect(620, 550, 110, 30, 12, 12);
		// text
		g.setFont(new Font("Arial", Font.BOLD, 16));
		g.drawString("Save", 654, 571);
		g.drawString(statusText, 641, 597);

	}

	private void initTeams() {
		// black team
		for (int j = 0; j < 8; j++) {
			for (int i = 0; i < 8; i++) {
				ChessPiece piece = new ChessPiece(i, j, true);
				boolean empt = false;
				// sets name of piece
				if (j == 1) { // white team
					piece.setData("black", "pawn");
				} else if (j == 0 && (i == 0 || i == 7)) {
					piece.setData("black", "rook");
				} else if (j == 0 && (i == 1 || i == 6)) {
					piece.setData("black", "knight");
				} else if (j == 0 && (i == 2 || i == 5)) {
					piece.setData("black", "bishop");
				} else if (j == 0 && i == 3) {
					piece.setData("black", "queen");
				} else if (j == 0 && i == 4) {
					piece.setData("black", "king");
				} else if (j == 6) { // white team
					piece.setData("white", "pawn");
				} else if (j == 7 && (i == 0 || i == 7)) {
					piece.setData("white", "rook");
				} else if (j == 7 && (i == 1 || i == 6)) {
					piece.setData("white", "knight");
				} else if (j == 7 && (i == 2 || i == 5)) {
					piece.setData("white", "bishop");
				} else if (j == 7 && i == 3) {
					piece.setData("white", "queen");
				} else if (j == 7 && i == 4) {
					piece.setData("white", "king");
				} else {
					empt = true;
				}
				// adds ChessPiece to ChessField
				if (empt) {
					b[j][i] = new ChessFields(i * size + offsetX, j * size + offsetY, true);
				} else {
					b[j][i] = new ChessFields(i * size + offsetX, j * size + offsetY, piece);
				}
			}
		}
		System.out.println("Teams initalized!");
	}

	private void printAllFields() {
		for (int j = 0; j < 8; j++) {
			for (int i = 0; i < 8; i++) {
				System.out.print(b[j][i].firstLetter() + " ");
			}
			System.out.println(" ");
		}
	}

	/*
	 * public static int[] something(){ int number1 = 1; int number2 = 2; return new
	 * int[] {number1, number2}; }
	 * 
	 * public static void main(String[] args) { int result[] = something();
	 * System.out.println(result[0] + result[1]); }
	 */

	private boolean isMoveValid(int oldx, int oldy, int newx, int newy) {
		// System.out.println("Old: " + oldx + "/" + oldy);
		// System.out.println("New: " + newx + "/" + newy);
		// get information about piece
		String teo = b[oldy][oldx].getPiece().getTeam();
		String nao = b[oldy][oldx].getPiece().getName();

		if (b[newy][newx].isEmpty()) {
			// TODO check for name and move like the piecewho moves something like
			// getValidmoves() which returns array; x and y offset values from old field
			return true;
		} else {
			// get information about target piece
			String ten = b[newy][newx].getPiece().getTeam();
			String nan = b[newy][newx].getPiece().getName();
			if (b[oldy][oldx] == b[newy][newx]) { // move to old position valid
				return true;
			} else if (ten == teo) { // move to another piece of same team invalid
				return false;
			} else if (ten != teo) {
				// kick enemy chesspiece
				System.out.println(teo.charAt(0) + "-" + nao + " killed " + ten.charAt(0) + "-" + nan);
				return true;
			} else {
				System.err.println("Unreachable move reached!");
				return false;
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// get new mouse pos when mouse is moved
		mouseX = (e.getPoint().x - offsetX) / size;
		mouseY = (e.getPoint().y - offsetY) / size;
		if (dragging) {
			b[startmouseY][startmouseX].getPiece().setPos(mouseX * size + offsetX, mouseY * size + offsetY);
		}
		this.repaint();
	}

	public void movePiece(int oldx, int oldy, int newx, int newy) {
		// ugly workaround but works fine
		ChessPiece temp = new ChessPiece(oldx * size + offsetX, oldy * size + offsetY, true);
		temp.setData(b[oldy][oldx].getPiece().getTeam(), b[oldy][oldx].getPiece().getName());
		ChessFields tempf = new ChessFields(newx * size + offsetX, newy * size + offsetY, temp);

		b[oldy][oldx].setEmpty(true);
		b[newy][newx].setPiece(temp);
		b[newy][newx] = tempf;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = (e.getPoint().x - offsetX) / size;
		int y = (e.getPoint().y - offsetY) / size;
		try {
			if (isUiSaveClicked(e.getPoint().x, e.getPoint().y)) {
				System.out.println("Save pressed!");
			} else if (!dragging && !b[y][x].isEmpty()) {
				startmouseX = x;
				startmouseY = y;
				dragging = true;
			} else if (dragging && isMoveValid(startmouseX, startmouseY, x, y)) {
				// move piece
				movePiece(startmouseX, startmouseY, x, y);
				dragging = false;
			} else {
				System.err.println("Invalid move!");
			}
		} catch (Exception ex) {
			System.err.println(ex);
		}
		// printAllFields();
	}

	private boolean isUiSaveClicked(int x, int y) {
		// 620, 550, 110, 30
		if (x >= 620 && x <= 740) {
			if (y >= 550 && y <= 580) {
				cfh.writeFile(b);
				statusText = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
				this.repaint();
				return true;
			}
		}
		return false;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

}