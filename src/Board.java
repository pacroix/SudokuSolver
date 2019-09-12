import java.util.Arrays;

public class Board {
    private int[][] board = new int[9][9];

    public Board(){
        //Empty constructor
    }

    public Board(int[][] ints) {
        if(ints.length != 9 || ints[1].length != 9) {
            throw new NumberFormatException();
        }
        board = ints;
    }

    /**
     * Creates a Board out of a String
     * @param input String, Format:
     *              "1,2,3,4,5,..."
     *              "12345..."
     * @return Two dimensional Array as Board
     */
    public static Board fromString(String input){
        char limiter = input.charAt(1);
        int[] arr;
        if (limiter >= 48 && limiter <= 57) {
            arr = Utils.sToIntArr(input, "");
        } else { //input String is separated by any (special) character
            arr = Utils.sToIntArr(input, String.valueOf(limiter));
        }
        int[][] board = new int[9][9];
        for (int i = 0; i < 9; i++){
            assert arr != null;
            board[i] = Arrays.copyOfRange(arr, i*9, i*9+9);
        }

        //TODO: Erstelle Sudoku aus einem String
        return new Board(board);
    }

    @Override
    public String toString(){
        StringBuilder ret = new StringBuilder();
        for(int[] columns : board){
            for(int cell : columns){
                ret.append(cell).append(',');
            }
            ret.deleteCharAt(ret.length()-1);
            ret.append('\n');
        }
        return ret.toString();
    }

    int getCell(int row, int column){
        return board[row][column];
    }

    int[] getRow(int row){
        return board[row];
    }

    int[] getColumn(int column){
        int[] ret = new int[9];
        for(int i = 0; i < 9; i++){
            ret[i] = board[i][column];
        }
        return ret;
    }

    int[] getNextMissing() {
        for (int i = 0; i<9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.board[i][j] == 0) return new int[]{i,j};
            }
        }
        return null;
    }

    /**
     * Returns an int Array of the given field. Numeration:<br>
     * 0 1 2<br>
     * 3 4 5<br>
     * 6 7 8<br>
     * The return Array has the same format.
     * @param field Int of the field to return
     * @return Int array if 0 <= field <= 9, else null
     */
    /*
       0 1 2 3 4 5 6 7 8
       _________________
    0| 0 0 0 1 1 1 2 2 2
    1| 0 0 0 1 1 1 2 2 2
    2| 0 0 0 1 1 1 2 2 2
    3| 3 3 3 4 4 4 5 5 5
    4| 3 3 3 4 4 4 5 5 5
    5| 3 3 3 4 4 4 5 5 5
    6| 6 6 6 7 7 7 8 8 8
    7| 6 6 6 7 7 7 8 8 8
    8| 6 6 6 7 7 7 8 8 8
     */
    int[] getField(int field){
        if(field >= 0 && field <= 8) {
            int[] ret = new int[9];
            for (int i = 0; i < 9; i++) {
                ret[i] = board[((field/3)*3) + (i / 3)][((field % 3) * 3) + (i % 3)];
            }
            return ret;
        }
        return null;
    }

    int[] getField(int row, int column){
        int field = 3*(row/3) + (column/3);
        return getField(field);
    }

    /**
     * Counts empty cells
     * @return Amount of empty cells
     */
    int countEmptyCells(){
        int emptyCells = 0;
        for(int i = 0; i < 9; i++){
            for(int c : getRow(i)){
                if(c == 0) emptyCells++;
            }
        }
        return emptyCells;
    }

    /**
     * Sets the cell at [row][column] to n
     * @param n Value, that has to be inserted into [row][column]
     * @param row Row
     * @param column Column
     */
    void set(int n, int row, int column){
        this.board[row][column] = n;
    }

    public void updateBoard(int[][] board){
        this.board = board;
    }

    public void updateBoard(Board board){
        this.board = board.board;
    }

    public void solve(){
        Solver.solve(this);
    }

}
