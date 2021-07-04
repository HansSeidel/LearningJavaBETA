import shape.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.lang.reflect.InvocationTargetException;
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
    protected void paintComponent(Graphics g) {
        //run the super class paintComponent with the given Graphics g again, so it runs proberly.
        //Otherwise the background Color won't be displayed
        super.paintComponent(g);
        this.setBackground(Color.BLACK);
        //Cast the Graphics object to an 2D Object.
        this.g2d = (Graphics2D) g;
        //This line will smooth the drawings
        this.g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Start drawing
        /*TODO implement the getClone functions for rect and triangle
                These getClone() methods return a new Object with the exact same settings as the
                object which is calling the method
         */
        Own_Rect rect1 = new Own_Rect(Main.WIDTH / 2 - 50, Main.HEIGHT / 2 - 25, 100, 50);
        Own_Rect rect2 = rect1.createClone();
        Own_Rect rect3 = rect1.createClone();
        Own_Rect rect4 = rect1.createClone();
        //TODO implement the following method:
        rect3.changeCoords(1, 1, 100, 100);
        System.out.println("after coords should have changed: " + Arrays.toString(rect1.getCoordinates()));
        rect4.setColor(Color.GREEN);

        Own_Triangle triangle1 = new Own_Triangle(1,1,4,4,10,10);
        Own_Triangle triangle2 = triangle1.createClone();
        Own_Triangle triangle3 = triangle1.createClone();
        Own_Triangle triangle4 = triangle1.createClone();
        //TODO implement the following method:
        triangle3.changeCoords(1, 1, 100, 100,100,400);
        triangle4.setColor(Color.YELLOW);

        //TODO implement "equal" for the rect and triangle class.
        //This method is called "equal", because their is an existing "equals" Method for each Object in Java.
        //In order, Java is not using the equals method, we'll use the name "equal" for now
        System.out.println(rect1.equal(rect2)); //Expected result: True
        System.out.println(rect2.equal(rect1)); //Expected result: True
        System.out.println(rect1.equal(rect3)); //Expected result: False
        System.out.println(rect1.equal(rect4)); //Expected result: False
        System.out.println(rect3.equal(rect4)); //Expected result: False
        System.out.println(rect2.equal(rect4)); //Expected result: False

        System.out.println(triangle1.equal(triangle2)); //Expected result: True
        System.out.println(triangle2.equal(triangle1)); //Expected result: True
        System.out.println(triangle1.equal(triangle3)); //Expected result: False
        System.out.println(triangle1.equal(triangle4)); //Expected result: False
        System.out.println(triangle3.equal(triangle4)); //Expected result: False
        System.out.println(triangle2.equal(triangle4)); //Expected result: False

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
