/**
 * Suggester
 */
public class Suggester {

	int hintLeft;
	
	// this constructor only takes one input, how many hints do we give
	public Suggester(int n){
		hintLeft = n;
	}

	public void hint(int rowNum, int colNum, NumberTable nt) {
		// hint from the answer's table
		int ans;
		
		// these two numbers are the coordinate user will see
		int userRowNum = rowNum + 1;
		int userColNum = colNum + 1;
		if (hintLeft >= 1) {
			ans = nt.getAnswer()[rowNum][colNum].getValue();
			hintLeft--;
			System.out.println("The correct number for position (" + userRowNum + "," + userColNum + ") is " + ans + ", you have " + hintLeft + " hints left");
		}

	}

	public static void main(String[] args) {
//		NewGameCreator nGC = new NewGameCreator();
//		nGC.setGameSeed(0);
//		// Read sudoku csv files
//		nGC.setPuzzleS();
//		nGC.setAnswerS();
//		String puzzleS = nGC.getPuzzleS();
//		String answerS = nGC.getAnswerS();
//		NumberTable nt = new NumberTable(puzzleS, answerS);
//		Number[][] puzzle = nt.getPuzzle();
//		Number[][] answer = nt.getAnswer();
//		
//		PrintNumberTable pNT = new PrintNumberTable();
//		pNT.printPuzzle(nt);
//		System.out.println();
//		pNT.printAnswer(nt);
//		
//		Suggester s = new Suggester(3);
//		s.hint(0, 0, nt);
//		s.hint(1, 1, nt);
//		s.hint(2, 2, nt);

	}
}