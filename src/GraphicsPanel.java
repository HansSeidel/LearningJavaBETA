import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GraphicsPanel extends JPanel {
    private Graphics2D g2d;

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
        this.g2d = (Graphics2D) g;
        //This line will smooth the drawings
        this.g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        //Start drawing
        //TODO create the "shape" package inside the "src" folder. Add the following classes inside this package
        //TODO implement the Own_Rect class
        Own_Rect rect = new Own_Rect(Main.WIDTH/2-50,Main.HEIGHT/2-25,100,50);
        drawDoubleShape(rect);

        //TODO implement the Own_Triangle class
        Own_Triangle triangle = new Own_Triangle(1,1,1,100,100,1);
        triangle.setColor(Color.CYAN);
        drawDoubleShape(triangle);

        //TODO implement the Own_Ellipse class
        Own_Ellipse ellipse = new Own_Ellipse(Main.WIDTH-100,Main.HEIGHT-100,70,50);
        ellipse.setColor(Color.ORANGE);
        drawDoubleShape(ellipse);

        /*TODO each implementation must have the following Methods:
            public Color getColor();        -> Returns a java.awt.color Color (Default color is white)
            public double[] getCoords();    -> Returns a double[] Array which stores the coordinates
            public String getShape();       -> Returns the name of the Shape ("rect"|"triangle"|"ellipse")

            Example for the result is inside the TaskImages-Folder
         */
    }

    /**
     * Draws a specific Shape with the given Coordinates Array
     * @param shape -> String shape name
     * @param coords -> double[] Coordinates Array
     */
    private void drawDoubleShape(Object obj){
        Color color;
        String shape;
        int[] coords;
        try {
            color = (Color) obj.getClass().getMethod("getColor").invoke(obj);
            shape = (String) obj.getClass().getMethod("getShape").invoke(obj);
            coords = Arrays.stream((double[])obj.getClass().getMethod("getCoordinates").invoke(obj)).mapToInt(coord -> (int)coord).toArray();
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return;
        }


        g2d.setColor(color);
        switch (shape.toLowerCase()){
            case "ellipse":
            case "rect":
                if(coords.length > 4) break;
                if(shape.toLowerCase().equals("rect")){
                    this.g2d.fillRect(coords[0],coords[1],coords[2],coords[3]);
                }else {
                    this.g2d.fillOval(coords[0],coords[1],coords[2],coords[3]);
                }
                return;
            case "triangle":
                if(coords.length > 6) break;
                Path2D triangle = new Path2D.Double();
                triangle.moveTo(coords[0],coords[1]);
                triangle.lineTo(coords[2],coords[3]);
                triangle.lineTo(coords[4],coords[5]);
                triangle.closePath();
                this.g2d.fill(triangle);
                return;
            default:
                System.err.printf("Unknown shape\n");
                return;
        }
        System.err.printf("Wrong amount of coordinates for type %s.\n",shape);
    }
}
