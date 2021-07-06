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
        /*TODO implement an IShape Interface inside the shape package for all the OwnShapes. Implement the following methods:
            double[] getCoordinates()
            String getShape()
            Point getCenter() -> Returns the center of the object. (java.awt.Point)
            double getDistanceBetween(Object o) -> Returns the distance to another Object of the same type (from Center to Center)

         */
        Own_Rect rect1 = new Own_Rect(Main.WIDTH / 2 - 50, Main.HEIGHT / 2 - 25, 100, 50);
        Own_Rect rect2 = rect1.createClone();
        rect2.setColor(Color.BLUE);
        rect2.changeCoords(1, 1, 100, 100);


        Own_Triangle triangle1 = new Own_Triangle(Main.WIDTH-1,1,Main.WIDTH-101,1,Main.WIDTH-51,51);
        triangle1.setColor(Color.GREEN);
        Own_Triangle triangle2 = triangle1.createClone();
        triangle2.setColor(Color.ORANGE);
        triangle2.changeCoords(1,Main.HEIGHT, 101,Main.HEIGHT,51,Main.HEIGHT-51);

        //TODO after the interface is created, implement the methods, so the correct results are returned
        System.out.println(rect1.getCenter().toString());    //Should return 400,200
        System.out.println(rect2.getCenter().toString());    //Should return 51,51
        //Math behind getDistance for the given Points above: SQRT((400-51)^2+(200-51)^2)
        System.out.println(rect1.getDistanceBetween(rect2));        //Should return ~379.48
        System.out.println(rect2.getDistanceBetween(rect1));        //Should return ~379.48

        System.out.println(triangle1.getCenter().toString());    //Should return 749,~17
        System.out.println(triangle2.getCenter().toString());    //Should return 51,383
        //Math behind getDistance for the given Points above: SQRT((749-51)^2+(17-383)^2)
        System.out.println(triangle1.getDistanceBetween(triangle2));    //Should return ~788.14

        //Drawing the shapes
        drawDoubleShape(rect1);
        drawDoubleShape(rect2);
        drawDoubleShape(triangle1);
        drawDoubleShape(triangle2);
    }

    /**
     * Draws a specific Shape with the given Coordinates Array
     * @param obj -> double[] Coordinates Array
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
