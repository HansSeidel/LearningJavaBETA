import javax.swing.*;
import java.awt.*;

public class Main {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 400;

    public static void main(String args[]){
        //Initiate the frame
        JFrame frame = new JFrame();

        frame.setSize(WIDTH,HEIGHT+50);
        frame.setTitle("MyFrame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Little update to exclude the borders from the dimensions
        frame.getContentPane().setPreferredSize(new Dimension(WIDTH,HEIGHT));
        frame.pack();
        //frame.setBackground(Color.BLACK);
        //Define the window to be position in the center of the screen
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //Add a panel to the frame
        GraphicsPanel panel = new GraphicsPanel();
        frame.add(panel,BorderLayout.CENTER);


    }
}
