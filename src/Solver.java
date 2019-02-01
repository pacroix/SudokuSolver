class Solver {
	private static boolean debug = false;

    static void solve(Board board){
    	boolean run = true;
        while(run){
        	run = solveStepwise(board);
	    }
        //TODO Rekursiv zu Ende loesen
    }

	private static boolean isSolved(Board board){
		return board.countEmptyCells() == 0;
	}


	private static Board solveRecursive(Board board){
    	Board sudoku = Utils.copyBoard(board);
    	return sudoku;
	}

	/**
	 * Tries to solve one number with the steps in the concept.txt
	 * @return True, when one number has been solved
	 */
    private static boolean solveStepwise(Board board){
	    for(int i = 0; i < 9; i++){     //Iterate each cell
		    for (int j = 0; j < 9; j++){
			    if(board.getCell(i,j) != 0) continue;
			    if(hasSingle(board,i,j)) return true;
		    }
	    }
    	for (int i = 0; i < 9; i++){    //Iterate each row
    		if(debug) {
			    System.out.println();
			    System.out.println("Row: " + i);
			    System.out.println(Utils.printIntArray(board.getRow(i)));
		    }
    		int[] missingNumbers = Utils.getMissingNumbers(board.getRow(i));
    		if (debug) {
			    System.out.println("Missing numbers are: " + Utils.printIntArray(missingNumbers));
			    System.out.println("Empty indexes are: " + Utils.printIntArray(Utils.getIndexes(0, board.getRow(i))));
		    }
		    if (missingNumbers != null) {
			    for (int missingNumber : missingNumbers) {
					    if (hasSingleLocation(board, missingNumber, 'r', i)) {
						    return true;
					    }
			    }
		    }
	    }
	    for (int i = 0; i < 9; i++){    //Iterate each column
	    	if (debug) {
			    System.out.println();
			    System.out.println(board);
			    System.out.println("Column: " + i);
			    System.out.println(Utils.printIntArray(board.getColumn(i)));
		    }
		    int[] missingNumbers = Utils.getMissingNumbers(board.getColumn(i));
	    	if (debug) {
			    System.out.println("Missing numbers are: " + Utils.printIntArray(missingNumbers));
			    System.out.println("Empty indexes are: " + Utils.printIntArray(Utils.getIndexes(0, board.getColumn(i))));
		    }
		    if (missingNumbers != null) {
			    for (int missingNumber : missingNumbers) {
					    if (hasSingleLocation(board, missingNumber, 'c', i)) {
						    return true;
					    }
			    }
		    }
	    }
	    for (int i = 0; i < 9; i++){    //Iterate each field
		    if (debug) {
			    System.out.println();
			    System.out.println(board);
			    System.out.println("Field: " + i);
			    System.out.println(Utils.printIntArray(board.getField(i)));
		    }
		    int[] missingNumbers = Utils.getMissingNumbers(board.getField(i));
		    if (debug) {
			    System.out.println("Missing numbers are: " + Utils.printIntArray(missingNumbers));
			    System.out.println("Empty indexes are: " + Utils.printIntArray(Utils.getIndexes(0, board.getField(i))));
		    }
		    if (missingNumbers != null) {
			    for (int missingNumber : missingNumbers) {
					    if (hasSingleLocation(board, missingNumber, 'f', i)) {
						    return true;
					    }
			    }
		    }
	    }
    	return false;
    }

	/**
	 * Checks if a cell has only one solution and solves it if possible
	 * @param board Board
	 * @param row Row
	 * @param column Column
	 * @return true, when a number was found, else false
	 */
    private static boolean hasSingle(Board board, int row, int column){
    	int missing = 0;
    	for (int i = 1; i <= 9; i++){
    		if(testNumber(board, row, column, i)){
    			if (missing == 0){
    				missing = i;
			    }
    			else {
    				return false;
			    }
		    }
	    }
    	board.set(missing, row, column);
    	return true;
    }

    private static boolean hasSingleLocation(Board board, int number, char type, int index){
	    int[] emptyIndexes;
	    int arrIndex = -1;
		switch (type) {
			case 'r':   //If array is a row
				emptyIndexes = Utils.getIndexes(0, board.getRow(index)); //Get the indexes of every empty cell
				if (debug) {
					System.out.println("Testing number " + number);
				}
				if (emptyIndexes == null) throw new IllegalStateException();  //This method only gets called, when missing numbers are not empty, so there should be indexes of 0
				for (int i : emptyIndexes) {    //Iterates every empty cell
					if(testNumber(board, index, i, number)){ //If number to check can go to cell
						if (debug) {
							System.out.println("Number " + number + " can go to cell [" + index + "," + i + "]");
						}
						if (arrIndex == (-1)) {
							arrIndex = i;
							if (debug) {
								System.out.println("First possible space: " + i);
								System.out.println("Set arrayIndex to: " + arrIndex);
							}
						}
						else {
							if (debug ) {
								System.out.println("Second possible space: " + i);
							}
							return false;
						}
					}
					else {
						if (debug) {
							System.out.println("Number " + number + " can not be inserted into to cell [" + index + "," + i + "]");
						}
					}
				}
				board.set(number, index, arrIndex);
				if (debug) {
					System.out.println("Number: " + number + " can only be inserted into [" + index + "," + arrIndex + "]");
					System.out.println(board);
				}
				break;
			case 'c':   //If array is a column
				if (debug) {
					System.out.println("Testing Column " + index + " for single location");
					System.out.println("Testing number " + number);
				}
				emptyIndexes = Utils.getIndexes(0, board.getColumn(index));
				if (emptyIndexes == null) throw new IllegalStateException();
				for (int i : emptyIndexes) {
					if(testNumber(board, i, index, number)){
						if (debug) {
							System.out.println("Number " + number + " can go to cell [" + i + "," + index + "]");
						}
						if (arrIndex == -1) {
							arrIndex = i;
							if (debug) {
								System.out.println("First possible space: " + i);
								System.out.println("Set arrayIndex to: " + arrIndex);
							}
						} else {
							if (debug) {
								System.out.println("Second possible space: " + i);
							}
							return false;
						}
					}
					else {
						if (debug) {
							System.out.println("Number " + number + " can not be inserted into to cell [" + i + "," + index + "]");
						}
					}
				}
				if (debug) {
					System.out.println("Number: " + number + " can only be inserted into [" + arrIndex + "," + index + "]");
				}

				board.set(number, arrIndex, index);
				break;
			case 'f':   //If array is a field
				if (debug) {
					System.out.println("Testing Field " + index + " for single location");
					System.out.println("Testing number " + number);
				}
				emptyIndexes = Utils.getIndexes(0, board.getField(index));
				if (emptyIndexes == null) throw new IllegalStateException();
				for (int i : emptyIndexes) {
					if(testNumber(board, 3*(index/3) + (i/3), 3*(index%3) + (i%3), number)){
						if (debug) {
							System.out.println("Number " + number + " can go to cell [" + (3*(index/3) + (i/3)) + "," + (3*(index%3) + (i%3)) + "]");
						}
						if (arrIndex == -1) {
							arrIndex = i;
							if (debug) {
								System.out.println("First possible space: " + i);
								System.out.println("Set arrayIndex to: " + arrIndex);
							}
						}
						else {
							if (debug) {
								System.out.println("Second possible space: " + i + "");
							}
							return false;
						}
					}
					else {
						if (debug) {
							System.out.println("Number " + number + " can not be inserted into to cell [" + (3*(index/3) + (i/3)) + "," + (3*(index%3) + (i%3)) + "]");
						}
					}
				}
				int row = 3*(index/3) + (arrIndex/3);
				int column = 3*(index%3) + (arrIndex%3);

				if (debug) {
					System.out.println("Number: " + number + " can only be inserted into [" + row + "," + column + "]");
				}

				board.set(number, row, column);
				break;
		}
		return true;
    }

    /**
     * Gets an int Array with length of 9, where only one number is missing and solves it
     * @param arr int[9] where only one number is 0
     */
    private static void solveSingle(int[] arr){
        int missingNumber = 0;
        MainLoop: for(int i = 1; i <= 9; i ++){
            missingNumber = i;
            for(int a : arr){
                if(i == a){
                    continue MainLoop;
                }
            }
            break;
        }
        for(int i = 0; i < 9 ; i++){
            if(arr[i] == 0){
                arr[i] = missingNumber;
            }
        }
    }

    /**
     * Checks, if you can insert a number in a cell
     * @param board Board to check
     * @param row Row between 0 and 8
     * @param column Column between 0 and 8
     * @param number number between 1 and 9
     * @return true if number is allowed in the cell
     */
    private static boolean testNumber(Board board, int row, int column, int number){
        int[] arr;
        arr = board.getRow(row);
        if(iterateArr(arr, number)) return false;
        arr = board.getColumn(column);
        if(iterateArr(arr, number)) return false;
        arr = board.getField(row, column);
	    return !iterateArr(arr, number);
    }

	/**
	 * Iterates an array and returns true, when the given number is present
	 * @param arr int[9]
	 * @param number number to compare
	 * @return true, when the given number is present
	 */
    private static boolean iterateArr(int[] arr, int number){
        for(int i : arr){
            if(i == number){
                return true;
            }
        }
        return false;
    }


}
