
public class Sudoku {

    

    // call NewGameCreator class
    NewGameCreator nGC = new NewGameCreator();
    private Number[][] puzzle;
    private Number[][] answer;
    private NumberTable nt;
    private String difficulty = "Hard";

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void newGame() {
        // Set game seed
        nGC.setRandomGameSeed();
        // Read sudoku csv files
        nGC.setPuzzleS();
        nGC.setAnswerS();
        String puzzleS = nGC.getPuzzleS();
        String answerS = nGC.getAnswerS();
        nt = new NumberTable(puzzleS, answerS);
        puzzle = nt.getPuzzle();
        answer = nt.getAnswer();
    }

    public Number[][] getAnswer() {
        return answer;
    }

    public Number[][] getPuzzle() {
        return puzzle;
    }
    public NumberTable getNt() {
        return nt;
    }

    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku();
        sudoku.newGame();
    }

}
