
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * ChessFileHandler
 */
public class ChessFileHandler {

    private ChessFields[][] b = new ChessFields[8][8];
    private ChessFields[][] bnew = new ChessFields[8][8];
    private String filename = "chess.dat";
    private File file;

    public ChessFileHandler(ChessFields[][] board) {
        // get current board (with all pieces)
        this.b = board;
    }

    public ChessFields[][] getBoard() {
        // TODO return valid array
        return bnew;
    }

    public void openFile() {
        // opens existing file
        file = chooseFile();
        System.out.println("Opened file: " + file);
        // TODO read file
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
            }

            bw.close();
            outstream.close();

        } catch (IOException e) {
            System.err.println(e);
        }

    }

    private void readFile() {

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

}
