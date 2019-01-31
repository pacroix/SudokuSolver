import javax.swing.*;

public class SudokuGui {
    private JPanel guiBoard;

    public static void main(String[] args) {
        JFrame frame = new JFrame("SudokuGui");
        frame.setContentPane(new SudokuGui().guiBoard);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
