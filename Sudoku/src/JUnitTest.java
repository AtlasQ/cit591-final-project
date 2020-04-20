import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.BeforeClass;
import org.junit.Test;
public class JUnitTest {
    static int length;
    
   

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

    @Test 
    public void makeSureItIsEightyThree() {
        
        assertEquals(83, length, "Check that the length should be 83 for next input!");
    }

}
