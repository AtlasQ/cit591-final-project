import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.lang.*;

public class GUI implements ActionListener {
    
        
        
        JButton submit = new JButton("Submit");
        JLabel CorrectOrWrong = new JLabel("---");
        private static Number[][] sudoku;
        private static NumberTable sample;
        private static JTextField[][] index = new JTextField[9][9];
        
        public GUI (){

                JFrame frame = new JFrame("Sudoku");
                // JFrame aFrame = new JFrame("2");
                frame.setSize(500,500);
                submit.addActionListener(this);
                JPanel board = new JPanel();
                board.setLayout(new GridLayout (9,9));
                for (int i =0;i<9;i++)
                        for (int j=0;j<9;j++){
                                index[i][j]=new JTextField(1);
                                index[i][j].setText(""+sudoku[i][j].getValue());
                                if (sudoku[i][j].getValue()!=0)
                                        index[i][j].setEditable(false);
                                board.add(index[i][j]);
                        }
                frame.getContentPane().add(board);
                // aFrame.getContentPane().add(board);
                frame.getContentPane().add(submit,"South");
                frame.getContentPane().add(CorrectOrWrong, "North");
                frame.setVisible(true);
        }
        
//       
        public void actionPerformed(ActionEvent e){
                if (e.getSource() == submit)
                {
                        int[][] temp = submit();
                        write(temp);
                        try {
                                Thread.sleep(2000);
                        } catch (InterruptedException r) {
                                r.printStackTrace();
                        }
                        
                        if(testSudoku(sample, temp)) CorrectOrWrong.setText("Correct!");
                        else CorrectOrWrong.setText("Wrong!");

                }
        }
  
        public boolean testSudoku(NumberTable J, int[][] Result) {
            Validator K = new Validator();
            boolean flag = false;
            for (int i =0;i<9;i++)
                for (int j=0;j<9;j++){
                    if(K.isCorrect(Result[i][j], i, j, J )) {
                        flag = true;
                    }
                    else { 
                        flag = false;
                    }
                    
                }
            return flag;
        }
        
//        Submit the text field (User input)
        
        public static int[][] submit(){
                int[][]result = new int[9][9];
                for (int i =0;i<9;i++)
                        for (int j=0;j<9;j++){
                                try {
                                result[i][j]=Integer.parseInt(index[i][j].getText());
                                }catch (Exception e){
                                        result[i][j]=-1;
                                }
                        }
                return result;
        }
    
        public static void write(int[][] array){
                try
              {
                 PrintWriter pw = new PrintWriter(new FileWriter("output.txt"));
                 for (int i=0;i<9;i++)
                 {
                        String s="";
                        for (int j=0;j<9;j++)
                                s+=array[i][j]+" ";
                    pw.println(s);
                 }
                 pw.close();
              } catch (IOException e)
                 {
                    System.out.println("The following error occurred " + e);
                 }
        }
        

        public static void main(String[] args) {
//                Create the sudoku
                Sudoku J = new Sudoku();
                J.newGame();
                sudoku = J.getPuzzle();
                sample = J.getNt();

                new GUI();
        }

}