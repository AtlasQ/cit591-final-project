
public class Sudoku {

    // call NewGameCreator class
    NewGameCreator nGC = new NewGameCreator();
    private Number[][] puzzle;
    private Number[][] answer;

    public void newGame() {
        // Set game seed
        nGC.setRandomGameSeed();
        // Read sudoku csv files
        nGC.setPuzzleS();
        nGC.setAnswerS();
        String puzzleS = nGC.getPuzzleS();
        String answerS = nGC.getAnswerS();
        NumberTable nt = new NumberTable(puzzleS, answerS);
        puzzle = nt.getPuzzle();
        answer = nt.getAnswer();
    }

    public Number[][] getAnswer() {
        return answer;
    }

    public Number[][] getPuzzle() {
        return puzzle;
    }

}
