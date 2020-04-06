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
		for (int i = 0; i <= 8; i++) {
			for (int j = 0; j <= 8; j++) {

				// current substring for puzzleStr
				String subP = puzzleStr.substring(i * 9 + j, i * 9 + j + 1);

				// boolean to say if current number is original or user input.
				boolean orig = false;
				if (!subP.equals("0")) {
					orig = true;
				}

				// Number class for current puzzle number
				Number puzzleN = new Number(Integer.parseInt(subP), orig);

				// Save current puzzle Number to our 2-dimensional array puzzle
				this.puzzle[i][j] = puzzleN;

				// current substring for answerStr
				String subA = answerStr.substring(i * 9 + j, i * 9 + j + 1);

				// Number class for current answer number
				Number answerN = new Number(Integer.parseInt(subA), orig);

				// Save current puzzle Number to our 2-dimensional array puzzle
				this.answer[i][j] = answerN;
			}
		}
	}

	// getter for puzzle matrix
	public Number[][] getPuzzle() {
		return puzzle;
	}

	// getter for answer matrix
	public Number[][] getAnswer() {
		return answer;
	}

	public static void main(String[] args) {
//		NewGameCreator nGC = new NewGameCreator();
//		nGC.setGameSeed();
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