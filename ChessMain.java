
import javax.swing.JFrame;

import java.awt.EventQueue;

/**
 * Chess Game fuer Berufsschulelele
 * 
 * @author Julian Thiele
 * @version 0.15
 */
public class ChessMain extends JFrame {

    private static final long serialVersionUID = 1L;

    public ChessMain() {
        add(new ChessGame());

        // JFrame settings
        setResizable(false);
        pack();

        setTitle("Chess Game fuer Berufsschule");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        ChessMain cm = new ChessMain();

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                cm.setVisible(true);
            }
        });

    }
}