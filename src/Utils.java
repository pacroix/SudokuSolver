import java.util.Arrays;

class Utils {
	static int[] getMissingNumbers(int[] arr){
		StringBuilder mssNrs = null;
		for (int i = 1; i <= arr.length; i++){
			boolean found = false;
			for (int c: arr){
				if (c == i) {
					found = true;
					break;
				}
			}
			if(!found) {
				if(mssNrs == null) mssNrs = new StringBuilder(String.valueOf(i));
				else mssNrs.append(",").append(i);
			}
		}
		if(mssNrs == null){
			return null;
		}
		else return Arrays.stream(mssNrs.toString().split(",")).mapToInt(Integer::parseInt).toArray();
	}

	static int[] getIndexes(int nbr, int[] arr) {
		StringBuilder indexes = null;
		for(int i = 0; i < arr.length; i++){
			if (arr[i] == nbr) {
				if (indexes == null) indexes = new StringBuilder(String.valueOf(i));
				else indexes.append(",").append(i);
			}
		}
		if (indexes == null) return null;
		else return Arrays.stream(indexes.toString().split(",")).mapToInt(Integer::parseInt).toArray();
	}

	/**
	 * Returns a string in the format "{arr[0],arr[1],...,arr[arr.length]}"
	 * @param arr Int array
	 * @return String
	 */
	static String printIntArray(int[] arr){
		if (arr == null) return "null";
		StringBuilder ret = new StringBuilder("{");
		for (int i = 0; i < arr.length; i++){
			if (i == 0) ret.append(arr[i]);
			else ret.append(",").append(arr[i]);
		}
		ret.append("}");
		return ret.toString();
	}

	static int[] sToIntArr(String input, String split){
		return Arrays.stream(input.toString().split(split)).mapToInt(Integer::parseInt).toArray();
	}

	static Board copyBoard(Board board){
		int[][] ret = new int[9][9];
		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 9; j++){
				ret[i][j] = board.getCell(i,j);
			}
		}
		return new Board(ret);
	}
}
