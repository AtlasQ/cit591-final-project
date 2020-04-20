import java.util.Arrays;

public class PrintNumberTable {
	public void printPuzzle(NumberTable nt) {
		int[][] value = new int[9][9];
		for (int i = 0; i <= 8; i++) {
			for (int j = 0; j <= 8; j++) {
				value[i][j] = nt.getPuzzle()[i][j].getValue();
			}
		}
		System.out.println(Arrays.deepToString(value).replace("], ", "\n").replace("[[", "").replace("]]", "").replace("[", "").replace(",", ""));
	}
	
	public void printAnswer(NumberTable nt) {
		int[][] value = new int[9][9];
		for (int i = 0; i <= 8; i++) {
			for (int j = 0; j <= 8; j++) {
				value[i][j] = nt.getAnswer()[i][j].getValue();
			}
		}
		System.out.println(Arrays.deepToString(value).replace("], ", "\n").replace("[[", "").replace("]]", "").replace("[", "").replace(",", ""));
	}

	public static void main(String[] args) {
//		int[][] value = new int[9][9];
//		for (int i = 0; i <= 8; i++) {
//			for (int j = 0; j <= 8; j++) {
//				value[i][j] = 1;
//			}
//		}
//		System.out.println(Arrays.deepToString(value).replace("], ", "\n").replace("[[", "").replace("]]", "").replace("[", "").replace(",", ""));
    	
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
	}
}
