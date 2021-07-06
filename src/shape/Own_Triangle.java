package shape;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

public class Own_Triangle implements IShape{
    private double[] coordinates;
    private Color color;
    private String shape;
    //TODO add each created Instance to the list -> To add something to an ArrayList: INSTANCES.add();
    public static ArrayList<Own_Triangle> INSTANCES = new ArrayList<Own_Triangle>();

    public Own_Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.coordinates = new double[] {x1,y1,x2,y2,x3,y3};
        this.color = Color.WHITE;
        this.shape = "triangle";
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
        return new Point((int)(coordinates[0]+coordinates[2]+coordinates[4])/3,(int)(coordinates[1]+coordinates[3]+coordinates[5])/3);
    }


    public double[] getCoordinates(){
        return this.coordinates;
    }

    public Own_Triangle createClone() {
        Own_Triangle result = new Own_Triangle(this.coordinates[0],this.coordinates[1],this.coordinates[2],this.coordinates[3],this.coordinates[4],this.coordinates[5]);
        result.setColor(this.getColor());
        return result;
    }

    public void changeCoords(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.coordinates = new double[] {x1,y1,x2,y2,x3,y3};
    }

    public boolean equal(Own_Triangle c) {
        AtomicBoolean result = new AtomicBoolean(true);
        IntStream.range(0,this.coordinates.length).forEach(n -> {if(this.coordinates[n] != c.getCoordinates()[n]) result.set(false);});
        return result.get() && this.getColor() == c.getColor();
    }
}
