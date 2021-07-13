package shape;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

public class Own_Ellipse implements IShape{
    private double[] coordinates;
    private Color color;
    private String shape;
    //TODO add each created Instance to the list -> To add something to an ArrayList: INSTANCES.add();
    public static ArrayList<Own_Ellipse> INSTANCES = new ArrayList<Own_Ellipse>();


    public Own_Ellipse(double x, double y, double w, double h) {
        this.coordinates = new double[] {x,y,w,h};
        this.color = Color.WHITE;
        this.shape = "ellipse";
        INSTANCES.add(this);
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public Color getColor(){
        return this.color;
    }
    public String getShape(){
        return this.shape;
    }

    @Override
    public Point getCenter() {
        return new Point((int)coordinates[0],(int)coordinates[1]);
    }

    public double[] getCoordinates(){
        return this.coordinates;
    }

    public boolean equal(Own_Ellipse c) {
        AtomicBoolean result = new AtomicBoolean(true);
        IntStream.range(0,this.coordinates.length).forEach(n -> {if(this.coordinates[n] != c.getCoordinates()[n]) result.set(false);});
        return result.get() && this.getColor() == c.getColor();
    }
}
