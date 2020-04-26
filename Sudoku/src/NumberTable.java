/**
 * NumberTable
 */
public class NumberTable {

	private Number[][] puzzle = new Number[9][9];
	private Number[][] answer = new Number[9][9];

	/**
	 * Constructor to save puzzle string and answer string read from .csv files
	 * to 2-dimensional array
	 * 
	 * @param puzzleStr
	 * @param answerStr
	 */
	public NumberTable(String puzzleStr, String answerStr) {
		for (int row = 0; row <= 8; row++) {
			for (int col = 0; col <= 8; col++) {
				int rowCompensation = 0;
				switch (row / 3) {
					case 0:
						rowCompensation = 0;
						break;
					case 1:
						rowCompensation = 2;
						break;
					case 2:
						rowCompensation = 4;
						break;
				}
				// box id
				int box = (row / 3) + (col / 3) + rowCompensation;
				// current substring for puzzleStr
				String subP = puzzleStr.substring(row * 9 + col, row * 9 + col + 1);

				// boolean to say if current number is original or user input.
				boolean orig = false;
				if (!subP.equals("0")) {
					orig = true;
				}

				// Number class for current puzzle number
				Number puzzleN = new Number(Integer.parseInt(subP), orig, row, col, box);

				// Save current puzzle Number to our 2-dimensional array puzzle
				this.puzzle[row][col] = puzzleN;

				// current substring for answerStr
				String subA = answerStr.substring(row * 9 + col, row * 9 + col + 1);

				// Number class for current answer number
				Number answerN = new Number(Integer.parseInt(subA), orig, row, col, box);

				// Save current puzzle Number to our 2-dimensional array puzzle
				this.answer[row][col] = answerN;
			}
		}
	}

	// getter for puzzle matrix
	public Number[][] getPuzzle() {
		return puzzle;
	}

	// setter to put user's input into puzzle matrix
	public void setPuzzle(int value, int rowNum, int colNum) {
		int rowCompensation = 0;
		switch (rowNum / 3) {
			case 0:
				rowCompensation = 0;
				break;
			case 1:
				rowCompensation = 2;
				break;
			case 2:
				rowCompensation = 4;
				break;
		}
		// box id
		int boxNum = (rowNum / 3) + (colNum / 3) + rowCompensation;

		// user must input a valid number and position, we won't add the feature here, but we'll create a try-catch when asking for input
		Number n = new Number(value, false, rowNum, colNum, boxNum);
		puzzle[rowNum][colNum] = n;
	}
	
	// getter for answer matrix
	public Number[][] getAnswer() {
		return answer;
	}

	public static void main(String[] args) {
//		NewGameCreator nGC = new NewGameCreator();
//		nGC.setPuzzleS();
//		nGC.setAnswerS();
//		String puzzleS = nGC.getPuzzleS();
//		String answerS = nGC.getAnswerS();
//
//		NumberTable nt = new NumberTable(puzzleS, answerS);
//
//		Number[][] puzzle = nt.getPuzzle();
//		Number[][] answer = nt.getAnswer();
//
//		System.out.println(puzzleS);
//		for (int i = 0; i <= 8; i++) {
//			for (int j = 0; j <= 8; j++) {
//				System.out.println(puzzle[i][j].getValue());
//				System.out.println(puzzle[i][j].getOrig());
//			}
//		}
//
//		System.out.println(answerS);
//		for (int i = 0; i <= 8; i++) {
//			for (int j = 0; j <= 8; j++) {
//				System.out.println(answer[i][j].getValue());
//				System.out.println(answer[i][j].getOrig());
//			}
//		}
	}
}