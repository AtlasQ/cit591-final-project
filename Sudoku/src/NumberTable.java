/**
 * NumberTable
 */
public class NumberTable {
	
	

    private Number[][] puzzle = new Number[9][9];
	private Number[][] answer = new Number[9][9];
	private Number[][] originalCopyPuzzle = new Number[9][9];
	/**
	 * Constructor to save puzzle string and answer string read from .csv files
	 * to 2-dimensional array
	 * 
	 * @param puzzleStr
	 * @param answerStr
	 */
	public NumberTable(String puzzleStr, String answerStr) {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				int box = HelperFunction.calculateBoxID(row, col);
				// current substring for puzzleStr
				String subP = puzzleStr.substring(row * 9 + col, row * 9 + col + 1);

				// boolean to say if current number is original or user input.
				boolean orig = false;
				if (!subP.equals("0")) {
					orig = true;
				}

				// Number class for current puzzle number
				Number puzzleN = new Number(Integer.parseInt(subP), orig, row, col, box);
				Number puzzleN_copy = new Number(Integer.parseInt(subP), orig, row, col, box);
				// Save current puzzle Number to our 2-dimensional array puzzle
				this.puzzle[row][col] = puzzleN;
				this.originalCopyPuzzle[row][col] = puzzleN_copy;
				// current substring for answerStr
				String subA = answerStr.substring(row * 9 + col, row * 9 + col + 1);

				// Number class for current answer number
				Number answerN = new Number(Integer.parseInt(subA), orig, row, col, box);

				// Save current puzzle Number to our 2-dimensional array puzzle
				this.answer[row][col] = answerN;
			}
		}
	}

	public void copyOriginalPuzzle() {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				Number referenceNumber = originalCopyPuzzle[row][col];
				Number number = new Number(referenceNumber.getValue(), referenceNumber.getOrig(), referenceNumber.getRowID(), referenceNumber.getColID(), referenceNumber.getBoxID());
				this.puzzle[row][col] = number;
			}
		}
	}

	// getter for puzzle matrix
	
	public Number[][] getPuzzle() {
		return puzzle;
	}
	
	/**
	 * Getter for OriginalCopyPuzzle
	 * @return
	 */
	public Number[][] getOriginalCopyPuzzle() {
	        return originalCopyPuzzle;
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
		int res = 0;
		// right number from answer table
		int reference = this.answer[number.getRowID()][number.getColID()].getValue();
		if ((number.getValue() != reference) && (number.getValue() != 0)) {
			res++;
		}
		number.setIfCorrect(res);
		setPuzzle(number);
	}

	public void undoIfMistake(Number number) {
		if (number.getValue() != 0) {
			number.setIfCorrect(0);
			setPuzzle(number);
		}
	}

	public void ifValid(Number number) {
		int rowInvalid = 0;
		int colInvalid = 0;
		int boxInvalid = 0;
		if (number.getValue() != 0) {
			for (int i = 0; i < 9; i++) {
				Number rowValue = this.puzzle[number.getRowID()][i];
				Number colValue = this.puzzle[i][number.getColID()];
				if ((number.getValue() == rowValue.getValue()) && (!number.sameLocation(rowValue))) {
					number.setRowComplience(number.getRowComplience() + 1);
					rowValue.setRowComplience(rowValue.getRowComplience() + 1);
					setPuzzle(number);
					setPuzzle(rowValue);
					rowInvalid++;
				}
				if ((number.getValue() == colValue.getValue()) && (!number.sameLocation(colValue))) {
					number.setColComplience(number.getColComplience() + 1);
					colValue.setColComplience(colValue.getColComplience() + 1);
					setPuzzle(number);
					setPuzzle(colValue);
					colInvalid++;
				}
				for (int j = 0; j < 9; j++) {
					Number boxValue = this.puzzle[i][j];
					if ((!number.sameLocation(boxValue)) && (number.getBoxID() == boxValue.getBoxID())
							&& (number.getValue() == boxValue.getValue())) {
						number.setBoxComplience(number.getBoxComplience() + 1);
						boxValue.setBoxComplience(boxValue.getBoxComplience() + 1);
						setPuzzle(number);
						setPuzzle(boxValue);
						boxInvalid++;
					}
				}
			}
			if (rowInvalid == 0) {
				number.setRowComplience(0);
				setPuzzle(number);
			}
			if (colInvalid == 0) {
				number.setColComplience(0);
				setPuzzle(number);
			}
			if (boxInvalid == 0) {
				number.setBoxComplience(0);
				setPuzzle(number);
			}
		}
		
	}

	public void undoIfValid(Number number) {
		int rowInvalid = 0;
		int colInvalid = 0;
		int boxInvalid = 0;
		if (number.getValue() != 0) {
			for (int i = 0; i < 9; i++) {
				Number rowValue = this.puzzle[number.getRowID()][i];
				Number colValue = this.puzzle[i][number.getColID()];
				if ((number.getValue() == rowValue.getValue()) && (!number.sameLocation(rowValue))) {
					number.setRowComplience(number.getRowComplience() - 1);
					rowValue.setRowComplience(rowValue.getRowComplience() - 1);
					setPuzzle(number);
					setPuzzle(rowValue);
					rowInvalid++;
				}
				if ((number.getValue() == colValue.getValue()) && (!number.sameLocation(colValue))) {
					number.setColComplience(number.getColComplience() - 1);
					colValue.setColComplience(colValue.getColComplience() - 1);
					setPuzzle(number);
					setPuzzle(colValue);
					colInvalid++;
				}
				for (int j = 0; j < 9; j++) {
					Number boxValue = this.puzzle[i][j];
					if ((!number.sameLocation(boxValue)) && (number.getBoxID() == boxValue.getBoxID())
							&& (number.getValue() == boxValue.getValue())) {
						number.setBoxComplience(number.getBoxComplience() - 1);
						boxValue.setBoxComplience(boxValue.getBoxComplience() - 1);
						setPuzzle(number);
						setPuzzle(boxValue);
						boxInvalid++;
					}
				}
			}
			if (rowInvalid == 0) {
				number.setRowComplience(0);
				setPuzzle(number);
			}
			if (colInvalid == 0) {
				number.setColComplience(0);
				setPuzzle(number);
			}
			if (boxInvalid == 0) {
				number.setBoxComplience(0);
				setPuzzle(number);
			}
		}
	}
}