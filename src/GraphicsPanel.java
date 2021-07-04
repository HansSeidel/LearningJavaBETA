import javax.swing.*;
import java.awt.*;

public class GraphicsPanel extends JPanel {

    public GraphicsPanel(){
    }

    /**
     * Overwriting the super class paintComponent of JComponent (JPanel extends JComponent).
     * The program will automaticle detect the methode and run it.
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g){
        //run the super class paintComponent with the given Graphics g again, so it runs proberly.
        //Otherwise the background Color won't be displayed
        super.paintComponent(g);
        this.setBackground(Color.BLACK);
        //Cast the Graphics object to an 2D Object.
        Graphics2D g2d = (Graphics2D) g;

        //Start drawing
        g2d.setColor(Color.WHITE);
        g2d.fillRect(Main.WIDTH/2-50,Main.HEIGHT/2-25,100,50);
    }
}
