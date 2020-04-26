import javax.swing.*;
import java.awt.event.*;



class Tracker extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = -8634819679938133860L;
    boolean tc1 = false, tc2 = false, tc3 = false, tc4 = false;

    public static void main(String[] args) {
        new Tracker();
    }

    public Tracker() {
        JPanel panel = new JPanel();
        
        JButton t1 = new JButton(" ");
        panel.add(t1);
        t1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tc1 = true;
            System.out.println(tc1);
            }
        });
        this.setVisible(true);
        
        // remaining part of the code...
        // here i need to use the value of tc1 for further process..

    }
}