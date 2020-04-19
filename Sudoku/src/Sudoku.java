
public class Sudoku {
	// call NewGameCreator class
	NewGameCreator nGC = new NewGameCreator();
		
	public void newGame(){
		// Set game seed
		nGC.setGameSeed();
		// Read sudoku csv files
		nGC.setPuzzleS();
		nGC.setAnswerS();
		String puzzleS = nGC.getPuzzleS();
		String answerS = nGC.getAnswerS();
		NumberTable nt = new NumberTable(puzzleS, answerS);
		Number[][] puzzle = nt.getPuzzle();
		Number[][] answer = nt.getAnswer();
	}
	
	
}
