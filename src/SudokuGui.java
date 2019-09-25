import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuGui extends JFrame{
    private static final long serialVersionUID = 0;
    private JTextField[][] f;
    private JButton btnSolve;

    public SudokuGui(){
        super("Sudoku");

        f = new JTextField[9][9];
        btnSolve = new JButton("Solve");
        btnSolve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Board board = new Board(getBoard());
                board.solve();
                setBoard(board.getBoard());
            }
        });

        for(int x = 0; x<9; x++) {
            for(int y=0; y<9; y++) {
                f[x][y] = new JTextField(1);
            }
        }

        JPanel[][] p = new JPanel[3][3];
        for(int x = 0; x<3; x++) {
            for(int y=0; y<3; y++) {
                p[x][y] = new JPanel(new GridLayout(3,3));
            }
        }

        setLayout(new GridLayout(4,3,5,5));

        for(int u=0; u<3; u++) {
            for(int i=0; i<3; i++) {
                for (int x=0; x<3; x++) {
                    for (int y=0; y<3; y++) {
                        p[u][i].add(f[y+u*3][x+i*3]);
                    }
                }
                add(p[u][i]);
            }
        }
        add(btnSolve);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(400,400));
        this.setResizable(true);
        this.pack();
        this.setVisible(true);
    }

    public void setBoard(int[][] board) {
        for(int x=0; x<9; x++) {
            for (int y=0; y<9; y++) {
                this.f[x][y].setText(String.valueOf(board[x][y]));
            }
        }
    }

    public int[][] getBoard() {
        int[][] ret = new int[9][9];
        for(int x=0; x<9; x++) {
            for (int y=0; y<9; y++) {
                ret[x][y] = Integer.parseInt(f[x][y].getText());
            }
        }
        return ret;
    }


}
