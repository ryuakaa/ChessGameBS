
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * ChessFileHandler
 */
public class ChessFileHandler {

	private ChessFields[][] bnew = new ChessFields[8][8];
	private int offsetX = 41;
	private int offsetY = 44;
	private int size = 65;
	private String filename = "chess.dat";
	private File file;

	public ChessFileHandler() {
	}

	public ChessFields[][] getBoard() {
		// returns new board to ChessGame
		return bnew;
	}

	public void openFile() {
		// opens existing file
		file = chooseFile();
		System.out.println("Opened file: " + file);

		bnew = readFileAndPutIn(bnew);
		
	}

	public void createNewFile() {
		// opens dialog to choose path
		JFileChooser f = new JFileChooser();
		f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		f.showSaveDialog(null);
		file = new File(f.getSelectedFile() + "\\" + filename);

		// creates new file
		try {
			if (file.createNewFile()) {
				System.out.println("File created in: " + file.getPath());
			} else {
				System.out.println("File already exists!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeFile(ChessFields[][] board) {
		try {
			// writes board to textfile
			OutputStream outstream = new FileOutputStream(file);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outstream));

			for (int j = 0; j < 8; j++) {
				for (int i = 0; i < 8; i++) {
					String str = "";
					if (board[j][i].isEmpty()) {
						str = "--- ";
					} else {
						str = board[j][i].getPiece().getFirstTeamChar() + "/"
								+ board[j][i].getPiece().getFirstPieceChar() + " ";
					}
					bw.write(str);
				}
				bw.newLine();
			}
			bw.close();
			outstream.close();

		} catch (IOException e) {
			System.err.println(e);
		}
	}

	private ChessFields[][] readFileAndPutIn(ChessFields[][] ar) {
		try {
			// reads data in chess.dat
			BufferedReader br;
			String st = ""; 
			String[][] grid = new String[8][8];
			String[] rows = new String[8];
			int counter = 0;
			// get string per row
			try {
				br = new BufferedReader(new FileReader(file));
				while ((st = br.readLine()) != null) {
					rows[counter] = st;
					counter++;
				} 
			} catch (IOException e1) {
				e1.printStackTrace();
			} 
			// split rowstring into gird 
			for (int j = 0; j < 8; j++) {
				for (int i = 0; i < 8; i++) {
					grid[j][i] = rows[j].split(" ")[i]; 
				}
			}
			// create new Chessfields with matching chesspieces
			for (int j = 0; j < 8; j++) {
				for (int i = 0; i < 8; i++) {
					// create new chessfield with chesspiece if not empty
					if(grid[j][i].contains("-")) {
						// create empty field
						ar[j][i] = new ChessFields(i * size + offsetX, j * size + offsetY, true);
					} else {
						// create new piece and place 
						ChessPiece p = getPieceFromString(grid[j][i], j, i);
						ar[j][i] = new ChessFields(i * size + offsetX, j * size + offsetY, p);
					}
				}
			}
			//printAllFields();
			System.out.println("File read successfully");
			return ar;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// returns chessPiece for readFile()
	public ChessPiece getPieceFromString(String in, int j, int i) {
		String[] a = in.split("/");
		String team = "";
		String name = "";

		// convert 
		char c = a[1].toCharArray()[0];
		char c2 = a[0].toCharArray()[0];

		if(c2 == 'b') {
			team = "black";
		} else {
			team = "white";
		}

		if(c == 'w') {
			name = "pawn";
		} else if(c == 'o') {
			name = "rook";
		} else if(c == 'i') {
			name = "knight";
		} else if(c == 's') {
			name = "bishop";
		} else if(c == 'e') {
			name = "queen";
		} else if(c == 'n') {
			name = "king";
		} 

		ChessPiece piece = new ChessPiece(i, j, true);
		piece.setData(team, name);
		return piece;
	}

	private File chooseFile() {
		// chooses valid filepath with a dialog
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Chess Game (.dat)", "dat");
		chooser.setFileFilter(filter);

		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			// success
			file = chooser.getSelectedFile();
		} else {
			// failed
			System.err.println("Open file aborted!");
		}
		return file;
	}

	public boolean isChanged(ChessFields[][] btest) {
		// get 
		bnew = readFileAndPutIn(bnew);
		for (int j = 0; j < 8; j++) {
			for (int i = 0; i < 8; i++) {
				
			}
		}
		if(btest == bnew) {
			return false;
		} else {
			return true;
		}
	}

}
