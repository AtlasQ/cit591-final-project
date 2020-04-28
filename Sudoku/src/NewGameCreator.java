import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class NewGameCreator {

	// gameSeed is the random number randomly choose a game from the csv file
	private int gameSeed;
	// read number gameSeed line from puzzle.csv
	private String puzzleS;
	// read number gameSeed line from answer.csv
	private String answerS;
	// difficulty
	private String difficulty;

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public void setGameSeed(int gameSeed) {
		this.gameSeed = gameSeed;
	}

	// class to random a seed
	public void setRandomGameSeed() {
		Random r = new Random();
		switch (difficulty) {
			case "Easy":
				gameSeed = r.nextInt(10000);
				break;
			case "Intermediate":
				gameSeed = r.nextInt(10000);
				break;
			case "Hard":
				gameSeed = r.nextInt(1497);
				break;
			default:
				gameSeed = r.nextInt(1497);
				break;
		}

	}

	// this will set puzzleS to the number of gameSeed line of puzzle.csv
	public void setPuzzleS() {
		File f;
		switch (difficulty) {
			case "Easy":
				f = new File("data/easy_puzzel.csv");
				break;
			case "Intermediate":
				f = new File("data/intermediate_puzzel.csv");
				break;
			case "Hard":
				f = new File("data/hard_puzzel.csv");
				break;
			default:
				f = new File("data/easy_puzzel.csv");
				break;
		}

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
		File f;
		switch (difficulty) {
			case "Easy":
				f = new File("data/easy_solution.csv");
				break;
			case "Intermediate":
				f = new File("data/intermediate_solution.csv");
				break;
			case "Hard":
				f = new File("data/hard_solution.csv");
				break;
			default:
				f = new File("data/easy_puzzel.csv");
				break;
		}
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
}