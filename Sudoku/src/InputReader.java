import java.util.Scanner;

/**
 * InputReader
 */
public class InputReader {
    private int input;

    public int Input() {

        boolean flag = false;
        while (!flag) {
            Scanner in = new Scanner(System.in);
            if (in.hasNextInt()) {
                input = in.nextInt();

                if (input >= 0 && input < 10) {

                    flag = true;
                } else {
                    System.out.println("Please enter a valid number!");
                }

            } else {
                System.out.println("Please enter a valid number!");
            }
        }
        return input;
    }

    public static void main(String[] args) {
        InputReader J = new InputReader();
        J.Input();

    }
}