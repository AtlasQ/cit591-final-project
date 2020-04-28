import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.BeforeClass;
import org.junit.Test;

public class JUnitTest {
    static int length;
    static int easyHint;
    static int intermediateHint;
    static int hardHint;
    static boolean mistakeCheckOn;
    static boolean complienceCheckOn;
    static boolean mistakeCheckSetOff;
    static boolean complienceCheckSetOff;
    static int puzzleCount;
    static int answerCount;
    static int copyOriginalPuzzleCount;
    static int copyPuzzleEqualOriginalCount;
    static boolean gameSeedRandomNumberFlag;
    static boolean numberSetValueFlag;
    static boolean setGameSeedFlag;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        File inputFile = new File("/Users/linjinhuang/Documents/GitHub/cit591-final-project/Sudoku/puzzle.csv");

        // Reads the puzzle file
        try (Scanner in = new Scanner(inputFile)) {

            length = in.next().length();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Check that your file is being written properly!");
        }

    }

    public static void gameModeAndCheckBox() {
        NewGameCreator Test = new NewGameCreator();
        Test.setPuzzleS();
        Test.setAnswerS();
        String puzzleS = Test.getPuzzleS();
        String answerS = Test.getAnswerS();
        NumberTable numberTable = new NumberTable(puzzleS, answerS);
        Sudoku Jin = new Sudoku(numberTable);
        Jin.setVisible(false);

        // Test setRandomGameSeed method

        gameSeedRandomNumberFlag = false;

        Test.setRandomGameSeed();

        int gameSeedNumber = Test.getGameSeed();

        if (gameSeedNumber >= 0 && gameSeedNumber <= 1000) {
            gameSeedRandomNumberFlag = true;
        }

        // Validate Sure Difficulty mode is working.
        Jin.setCurrentDifficulty("Easy");

        easyHint = Jin.getHintChance().get(Jin.getCurrentDifficulty());

        Jin.setCurrentDifficulty("Intermediate");
        intermediateHint = Jin.getHintChance().get(Jin.getCurrentDifficulty());

        Jin.setCurrentDifficulty("Hard");

        hardHint = Jin.getHintChance().get(Jin.getCurrentDifficulty());

        // Validate CheckMistake and CheckComplience are default on and can be turn off.

        mistakeCheckOn = Jin.isCheckMistake();

        complienceCheckOn = Jin.isCheckComplience();

        Jin.getCheckForMistake().setSelected(false);

        Jin.getCheckForComplience().setSelected(false);

        mistakeCheckSetOff = Jin.getCheckForMistake().isSelected();

        complienceCheckSetOff = Jin.getCheckForComplience().isSelected();

        // Validate there is no null is puzzle template, all numbers were read in and
        // numbers were copied correctly.
        puzzleCount = 0;
        answerCount = 0;
        copyOriginalPuzzleCount = 0;
        copyPuzzleEqualOriginalCount = 0;

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (numberTable.getPuzzle()[row][col] != null) {
                    puzzleCount++;
                }
                if (numberTable.getAnswer()[row][col] != null) {
                    answerCount++;
                }
                if (numberTable.getOriginalCopyPuzzle()[row][col] != null) {
                    copyOriginalPuzzleCount++;
                }
                if (numberTable.getPuzzle()[row][col].getValue() == (numberTable.getOriginalCopyPuzzle()[row][col])
                        .getValue()) {
                    copyPuzzleEqualOriginalCount++;
                }

            }
        }

        // Test Set Value Method
        numberSetValueFlag = false;
        int temp = numberTable.getPuzzle()[2][2].getValue();
        numberTable.getPuzzle()[2][2].setValue(12);
        int tempForSet = numberTable.getPuzzle()[2][2].getValue();
        if (tempForSet == numberTable.getPuzzle()[2][2].getValue()) {
            numberSetValueFlag = true;
        }

        // Test Set Game Seed to specific one

        setGameSeedFlag = false;
        Test.setGameSeed(10);

        if (Test.getGameSeed() == 10) {
            setGameSeedFlag = true;
        }

    }

    @Test
    public void makeSureItIsEightyThree() {

        assertEquals(83, length, "Check that the length should be 83 for next input!");
    }

    @Test
    public void makeSureIntermediateGiveFiveHints() {
        JUnitTest.gameModeAndCheckBox();

        assertEquals(5, intermediateHint, "Check that the intermediate mode should provide user five hints");
    }

    @Test
    public void makeSureEasyGiveTenHints() {

        assertEquals(10, easyHint, "Check that the easy mode should provide user ten hints");
    }

    @Test
    public void makeSureHardGiveTwoHints() {

        assertEquals(2, hardHint, "Check that the hard mode should only provide user 2 hints");
    }

    @Test
    public void makeSureCheckMistakeEnable() {

        assertEquals(true, mistakeCheckOn, "Check that the check Mistake mode is default enabled");
    }

    @Test
    public void makeSureCheckComplienceEnable() {

        assertEquals(true, complienceCheckOn, "Check that the check Complience mode is default enabled");
    }

    @Test
    public void makeSureCheckMistakeCanBeDisabled() {

        assertEquals(false, mistakeCheckSetOff, "Check that the check Mistake mode can be set disabled");
    }

    @Test
    public void makeSureCheckComplienceCanBeDisabled() {

        assertEquals(false, complienceCheckSetOff, "Check that the check Complience Mode can be set disabled");
    }

    @Test
    public void makeSurePuzzleHasNoNull() {

        assertEquals(81, puzzleCount, "Check that there is no null in puzzle template");
    }

    @Test
    public void makeSureAnswerHasNoNull() {

        assertEquals(81, answerCount, "Check that there is no null in answer template");
    }

    @Test
    public void makeSureCopyOfOrignalPuzzleHasNoNull() {

        assertEquals(81, copyOriginalPuzzleCount, "Check that there is no null in answer template");
    }

    @Test
    public void makeSureCopyPuzzleEqualOriginal() {

        assertEquals(81, copyPuzzleEqualOriginalCount, "Check that copy puzzle equal original puzzle");
    }

    @Test
    public void makeSureGameSeedMethodWorks() {

        assertEquals(true, gameSeedRandomNumberFlag,
                "Check that game seed number method does generate a number between 0-1000");
    }

    @Test
    public void makeSureSetValueWorks() {

        assertEquals(true, numberSetValueFlag, "Check that Number Class Set Value Works");
    }

    @Test
    public void makeSureSetGameSeedWorks() {

        assertEquals(true, setGameSeedFlag, "Check that Set game seed method works");
    }

}
