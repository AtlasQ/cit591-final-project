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
        Jin.setCurrentDifficulty("Easy");
     
        easyHint = Jin.getHintChance().get(Jin.getCurrentDifficulty());

        Jin.setCurrentDifficulty("Intermediate");
        intermediateHint = Jin.getHintChance().get(Jin.getCurrentDifficulty());
        
        Jin.setCurrentDifficulty("Hard");
        
        hardHint = Jin.getHintChance().get(Jin.getCurrentDifficulty());
        
        mistakeCheckOn = Jin.isCheckMistake();
        
        complienceCheckOn = Jin.isCheckComplience();
        
        
        Jin.getCheckForMistake().setSelected(false);
        
        Jin.getCheckForComplience().setSelected(false);
        
  
        
        mistakeCheckSetOff = Jin.getCheckForMistake().isSelected();
        
       
        
        complienceCheckSetOff = Jin.getCheckForComplience().isSelected();
    }
        


      

    @Test 
    public void makeSureItIsEightyThree() {
        
        assertEquals(83, length, "Check that the length should be 83 for next input!");
    }
    @Test 
    public void makeSureEasyGiveTenHints() {
        JUnitTest.gameModeAndCheckBox();
        
        assertEquals(10, easyHint, "Check that the easy mode should provide user ten hints");
    }
    @Test 
    public void makeSureIntermediateGiveFiveHints() {
    
        JUnitTest.gameModeAndCheckBox();
        assertEquals(5, intermediateHint, "Check that the intermediate mode should provide user five hints");
    }
    @Test 
    public void makeSureHardGiveTwoHints() {
    
        JUnitTest.gameModeAndCheckBox();
        assertEquals(2, hardHint, "Check that the hard mode should only provide user 2 hints");
    }
    @Test 
    public void makeSureCheckMistakeEnable() {
    
        JUnitTest.gameModeAndCheckBox();
        assertEquals(true, mistakeCheckOn, "Check that the check Mistake mode is default enabled");
    }
    @Test 
    public void makeSureCheckComplienceEnable() {
    
        JUnitTest.gameModeAndCheckBox();
        assertEquals(true, complienceCheckOn, "Check that the check Complience mode is default enabled");
    }
    @Test 
    public void makeSureCheckMistakeCanBeDisabled() {
    
        JUnitTest.gameModeAndCheckBox();
        assertEquals(false, mistakeCheckSetOff, "Check that the check Mistake mode can be set disabled");
    }
    @Test 
    public void makeSureCheckComplienceCanBeDisabled() {
    
        JUnitTest.gameModeAndCheckBox();
        assertEquals(false, complienceCheckSetOff, "Check that the check Complience Mode can be set disabled");
    }
    

}
