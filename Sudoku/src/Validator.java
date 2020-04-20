import java.util.Arrays;

/**
 * Validator
 */
public class Validator {
	
	// create 3 boolean to save whether row, col or sq is valid
	private boolean rowValid = false;
	private boolean colValid = false;
	private boolean sqValid = false;
	
	// This is to method to tell if user input right number, this is comparing user's input to answer
    public boolean isCorrect(int n, int rowNum, int colNum, NumberTable nt){
    	// initialize boolean variable
    	boolean res = false;
    	// right number from answer table
    	int reference = nt.getAnswer()[rowNum][colNum].getValue();
    	if (n == reference) {
    		res = true;
    	} else {
    		res = false;
    	}
    	return res;
    }
    
    
    public boolean isValid(int n, int rowNum, int colNum, NumberTable nt) {
    	// initialize boolean variable for result
    	boolean res = false;
    	
    	// temp variance to store number of trues, if 0, 
    	int rowSum = 0;
    	int colSum = 0;
    	int sqSum = 0;
    	
    	// row and col can be tested together
    	for (int i = 0; i <= 8; i ++) {
    		int rowValue = nt.getPuzzle()[rowNum][i].getValue();
    		int colValue = nt.getPuzzle()[i][colNum].getValue();
    		if (n == rowValue){
    			rowSum ++;
    		}
    		if (n == colValue){
    			colSum ++;
    		}
    	}
    	
    	// now square
    	// we divide the table into 9 chunks, first let's find out which row chunk are we in
    	// for example, rowNum = 2, then colChunk = 0, which means the number is in the top row chunk
    	int rowChunk = rowNum / 3;
    	// for example, if colNum = 8, then colChunk = 2, which means the number is in the right col chunk
    	int colChunk = colNum / 3;
    	
    	// chunk num time 3 will be the the start of the index, time 3 plus 2 will be the end of the index
    	for (int i = rowChunk * 3; i <= rowChunk * 3 + 2; i++){
    		for (int j = colChunk * 3; j <= colChunk * 3 + 2; j++){
    			int sqValue = nt.getPuzzle()[i][j].getValue();
    			if (n == sqValue){
    				sqSum ++;
    			}
    		}
    	}
    	
    	if (rowSum == 0) {
    		rowValid = true;
    	}
    	
    	if (colSum == 0) {
    		colValid = true;
    	}

    	if (sqSum == 0) {
    		sqValid = true;
    	}
    	
    	// if satisfy all 3 conditions, then true
    	if (rowSum + colSum + sqSum == 0) {
    		res = true;
    	}
    	
    	return res;
    }
    
	public boolean isRowValid() {
		return rowValid;
	}


	public boolean isColValid() {
		return colValid;
	}


	public boolean isSqValid() {
		return sqValid;
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
//		Validator v = new Validator();
//		
//		System.out.println(v.isCorrect(8, 4, 3, nt));
//		System.out.println(v.isCorrect(8, 1, 3, nt));
//		
//		System.out.println(v.isValid(9, 3, 4, nt));
//		System.out.println(v.isRowValid());
//		System.out.println(v.isColValid());
//		System.out.println(v.isSqValid());
//		
//		System.out.println(v.isValid(9, 6, 7, nt));
//		System.out.println(v.isRowValid());
//		System.out.println(v.isColValid());
//		System.out.println(v.isSqValid());
	}


}