import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args){


        int[][] board = new int[9][9];
        board[0] = new int[]{2,0,5,3,0,8,4,0,9};
        board[1] = new int[]{0,7,0,0,0,0,0,5,0};
        board[2] = new int[]{9,0,4,0,0,0,6,0,7};
        board[3] = new int[]{5,0,0,0,4,0,0,0,2};
        board[4] = new int[]{0,0,0,5,0,7,0,0,0};
        board[5] = new int[]{6,0,0,0,3,0,0,0,8};
        board[6] = new int[]{4,0,6,0,0,0,8,0,1};
        board[7] = new int[]{0,2,0,0,0,0,0,6,0};
        board[8] = new int[]{8,0,1,2,0,9,7,0,4};

        int[][] board2 = new int[9][9];
        board2[0] = new int[]{0,0,0,7,9,0,0,0,0};
        board2[1] = new int[]{1,0,0,0,8,2,0,4,3};
        board2[2] = new int[]{0,0,0,3,0,0,2,0,0};
        board2[3] = new int[]{6,9,0,0,0,0,3,0,2};
        board2[4] = new int[]{0,0,4,0,7,0,6,0,0};
        board2[5] = new int[]{7,0,5,0,0,0,0,1,4};
        board2[6] = new int[]{0,0,6,0,0,7,0,0,0};
        board2[7] = new int[]{8,7,0,6,1,0,0,0,9};
        board2[8] = new int[]{0,0,0,0,5,9,0,0,0};

        Board sudoku = new Board(board2);
        System.out.println(sudoku);
        long startTime = System.currentTimeMillis();
        Solver.solve(sudoku);
        long endTime = System.currentTimeMillis();
        System.out.println(sudoku);
        System.out.printf("\nSolving took %d ms", endTime-startTime);

		/*String str = "0079000010008204300030002006900003020040706007050000140060070008706100009000059000";
		Board sudoku = Board.fromString(str);
		sudoku.solve();
		System.out.println(sudoku);*/
    }
}
