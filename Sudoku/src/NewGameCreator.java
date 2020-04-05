import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;


public class NewGameCreator {
	
	// gameSeed is the random number between 1-1000 to random choose a game from the csv file
	private int gameSeed;
	// read number gameSeed line from puzzle.csv
	private String puzzleC;
	// read number gameSeed line from answer.csv
	private String answerC;
	
	
	public void setGameSeed() {
		Random r = new Random();
		gameSeed = r.nextInt(1000);
	}
	

	
	// this will set puzzleC to the number of gameSeed line of puzzle.csv
	public void setPuzzleC() {
		File f = new File("puzzle.csv");
		try {
			Scanner sc = new Scanner(f);
			for (int i = 0; i < gameSeed; i++) {
				sc.next();
			}
			puzzleC = sc.next();
		} catch (FileNotFoundException e) {
			System.out.println("Can't find puzzle.csv");
		}
	}
	
	// this will set puzzleC to the number of gameSeed line of answer.csv
	public void setAnswerC() {
		File f = new File("answer.csv");
		try {
			Scanner sc = new Scanner(f);
			for (int i = 0; i < gameSeed; i++) {
				sc.next();
			}
			answerC = sc.next();
		} catch (FileNotFoundException e) {
			System.out.println("Can't find answer.csv");
		}
	}
	
	public int getGameSeed() {
		return gameSeed;
	}
	
	public String getPuzzleC() {
		return puzzleC;
	}
	
	public String getAnswerC() {
		return answerC;
	}
    
	public static void main(String[] args) {
		NewGameCreator nGC = new NewGameCreator();
		nGC.setGameSeed();
		nGC.setPuzzleC();
		nGC.setAnswerC();
		System.out.println(nGC.getPuzzleC());
		System.out.println(nGC.getAnswerC());
	}
	
}