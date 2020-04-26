import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;

/**
 * InputReader
 */
public class InputReader extends JFrame implements KeyListener,ActionListener {
    private int input;
    private Scanner in = new Scanner(System.in);

    private HashMap<String, Integer> getInput() {
        boolean flag = false;
        HashMap<String, Integer> userInput = new HashMap<String, Integer>();
        ArrayList<String> keys = new ArrayList<String>(Arrays.asList("row", "col", "num"));
        for (String key: keys) {
            while (!flag) {
                if (in.hasNextInt()) {
                    input = in.nextInt();
                    if (input >= 0 && input < 10) {
                        flag = true;
                        userInput.put(key, input);
                    } else {
                        System.out.println("Please enter a valid number!");
                    }
                } else {
                    System.out.println("Please enter a valid number!");
                }
            }

        }   
        return userInput;
    }

    public void inputIngestion(NumberTable nt) {
        
    }

    public static void main(String[] args) {
        InputReader J = new InputReader();
        J.Input();

    }
}