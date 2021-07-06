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

        /*TODO implement an My_Math Class inside the Math package.
            When we think about the getDistanceBetween Method we find out that the method is in each shape the same.
                At least it could be the same in each Method.
                So somehow we need to outsource the method, so we reduce redundancy.
                Not only the getDistanceBetween Method is redundant but we will start with this method in this lection.
                We will outsource the getDistanceBetween Method to the final My_Math Class.
                It may also be outsourced to the class which we will created in Task 6. This is a matter of taste.
                Keep in mind that the method getDistanceBetween must be removed from the Interface, if you remove it from the classes.
                    In this case, it has been done already.
            We will also implement the method getClosestShape(Object o, boolean same_type) to the Math Class.
                The method shell be callable with 1 or 2 parameter: getClosestShape(Object o) | getClosestShape(Object o, boolean same_type)
                    Tipp: to accomplish this use function overloading.
                For the method getClosestShape we want to keep a look at the performance -> Tipp: The square root uses a lot of performance
                We also need an Array of all Shapes, so we can iterator throw all the existing shapes.
                To accomplish this a static ArrayList of the type Own_Rect, Own_Ellipse... is added to all Own_Shapes;
                Fill the ArrayList with the each instance in each Class and use the ArrayList for the getClosestShape method.
            For this task you should keep in mind where it is the best to deal with exceptions. Do not change any lines of code in
            the GraphicsPanel class and try to cover all exceptions which will be thrown by the methods which are part of the class Object.
            Tipp: For the work with the class Object you may take a look at the following lines inside the GraphicsPanel class: 78,79,123,124
         */

        /*TODO implement the methods so the program is working
            We will color the Shapes as following:
            The shape we will measure the distances will be green.
            The shape which is the closest will be red.
            The shape of the same type which is the closest will be orange.
            An exmaple Image is placed is inside the TaskImages Folder
        */
        Own_Rect rect1 = new Own_Rect(Main.WIDTH - 300, Main.HEIGHT / 2 - 25, 100, 50);
        Own_Rect rect2 = rect1.createClone();
        rect1.setColor(Color.GREEN);
        rect2.changeCoords(1, 1, 100, 100);

        Own_Triangle triangle1 = new Own_Triangle(Main.WIDTH-1,1,Main.WIDTH-101,1,Main.WIDTH-51,51);
        Own_Triangle triangle2 = triangle1.createClone();
        triangle2.changeCoords(1,Main.HEIGHT, 101,Main.HEIGHT,51,Main.HEIGHT-51);

        //Using Object, because we don't know which Shape will be returned:
        Object o1 = My_Math.getClosestShape(rect1);
        Object o2 = My_Math.getClosestShape(rect1,true);
        try {
            o1.getClass().getMethod("setColor",Color.class).invoke(o1,Color.RED);
            o2.getClass().getMethod("setColor",Color.class).invoke(o2,Color.ORANGE);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | NullPointerException e) {
            e.printStackTrace();
        }
        //Math behind getDistance for the given Points above: SQRT((749-51)^2+(17-383)^2)
        System.out.println(My_Math.getDistanceBetween(triangle1,triangle2));    //Should return ~788.14



        //Drawing the shapes
        drawDoubleShape(rect1);
        drawDoubleShape(rect2);
        drawDoubleShape(triangle1);
        drawDoubleShape(triangle2);

        //Drawing the results above the actual shapes...
        /*
            We need to draw the Objects above the given shapes, because we can not change the color of the actual objects.
            Reason for that is the following:
                With the keyword new, we create a new pointer to a specific object.
                This pointer will be passed withing each method we'll use the object.
                The arrayList also safes the pointer to the specific object.
                But if we want to return any kind of Shape, we'll need to create a new object of type Object in order to store the specific object.
                But we can't pass the reference in Java. As soon as we use the new keyword a new pointer is created.
                The moment we assign the new object to the result of the closest method, a copy will be created which has the exact same properties.
                We might compare the resulting object with each object type and with our equal method, but this is not that easy when we are using the
                class Object.
                So for simplicity we overdraw the object with it's exact copy.
                This is possible, because the drawDoubleShape works with an instance of an Object as argument.
         */
        drawDoubleShape(o1);
        drawDoubleShape(o2);

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
        } catch (IllegalAccessException | InvocationTargetException | NullPointerException | NoSuchMethodException e) {
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
