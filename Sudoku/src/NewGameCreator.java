import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;


public class NewGameCreator {
	
	// gameSeed is the random number between 1-1000 to random choose a game from the csv file
	private int gameSeed;
	// read number gameSeed line from puzzle.csv
	private String puzzleS;
	// read number gameSeed line from answer.csv
	private String answerS;
	
	
	// class to random a seed
	public void setRandomGameSeed() {
		Random r = new Random();
		gameSeed = r.nextInt(1000);
	}
	
	// class to set a seed you want, this is for test use only
	public void setGameSeed(int i) {
		gameSeed = i;
	}
	
	// this will set puzzleS to the number of gameSeed line of puzzle.csv
	public void setPuzzleS() {
		File f = new File("puzzle.csv");
		try {
			Scanner sc = new Scanner(f);
			for (int i = 0; i < gameSeed; i++) {
				sc.next();
			}
			puzzleS = sc.next();
			// remove double quotes
			puzzleS = puzzleS.replaceAll("\"", "");
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("Can't find puzzle.csv");
		}
	}
	
	// this will set puzzleS to the number of gameSeed line of answer.csv
	public void setAnswerS() {
		File f = new File("answer.csv");
		try {
			Scanner sc = new Scanner(f);
			for (int i = 0; i < gameSeed; i++) {
				sc.next();
			}
			answerS = sc.next();
			// remove double quotes
			answerS = answerS.replaceAll("\"", "");
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("Can't find answer.csv");
		}
	}
	
	
	
	public int getGameSeed() {
		return gameSeed;
	}
	
	public String getPuzzleS() {
		return puzzleS;
	}
	
	public String getAnswerS() {
		return answerS;
	}
    
	public static void main(String[] args) {
//		NewGameCreator nGC = new NewGameCreator();
//		nGC.setRandomGameSeed();
//		nGC.setPuzzleS();
//		nGC.setAnswerS();
//		System.out.println(nGC.getPuzzleS());
//		System.out.println(nGC.getPuzzleS().substring(80, 81));
//		System.out.println(nGC.getAnswerS());
	}
	
}