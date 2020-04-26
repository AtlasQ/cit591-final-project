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
	public void setPuzzle(Number number) {
		puzzle[number.getRowID()][number.getColID()] = number;
	}
	
	// getter for answer matrix
	public Number[][] getAnswer() {
		return answer;
	}

	public void ifMistake(Number number) {
		// initialize boolean variable
		boolean res = false;
		// right number from answer table
		int reference = this.answer[number.getRowID()][number.getColID()].getValue();
		if (number.getValue() == reference) {
			res = true;
		} else {
			res = false;
		}
		number.setIfCorrect(res);
		setPuzzle(number);
	}

	public void ifValid(Number number) {
		int rowInvalid = 0;
		int colInvalid = 0;
		int boxInvalid = 0;
		for (int i = 0; i < 9; i++) {
			Number rowValue = this.puzzle[number.getRowID()][i];
			Number colValue = this.puzzle[i][number.getColID()];
			if ((number.getValue() == rowValue.getValue()) && (!number.sameLocation(rowValue))) {
				number.setRowComplience(false);
				setPuzzle(number);
				rowInvalid++;
			}
			if ((number.getValue() == colValue.getValue()) && (!number.sameLocation(colValue))) {
				number.setColComplience(false);
				setPuzzle(number);
				colInvalid++;
			}
			for (int j = 0; j < 9; j++) {
				Number boxValue = this.puzzle[i][j];
				if ((!number.sameLocation(boxValue)) && (number.getBoxID() == boxValue.getBoxID()) && (number.getValue() == boxValue.getValue())) {
					number.setBoxComplience(false);
					setPuzzle(number);
					boxInvalid++;
				}
			}
		}
		if (rowInvalid == 0) {
			number.setRowComplience(true);
			setPuzzle(number);
		}
		if (colInvalid == 0) {
			number.setColComplience(true);
			setPuzzle(number);
		}
		if (boxInvalid == 0) {
			number.setBoxComplience(true);
			setPuzzle(number);
		}
	}
}