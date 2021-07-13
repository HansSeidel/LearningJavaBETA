package shape;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

public class Own_Rect implements IShape{
    private double[] coordinates;
    private Color color;
    private String shape;
    //TODO add each created Instance to the list -> To add something to an ArrayList: INSTANCES.add();
    public static ArrayList<Own_Rect> INSTANCES = new ArrayList<Own_Rect>();

    public Own_Rect(double x, double y, double w, double h) {
        this.coordinates = new double[]{x, y, w, h};
        this.color = Color.WHITE;
        this.shape = "rect";
        INSTANCES.add(this);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public String getShape() {
        return this.shape;
    }

    @Override
    public Point getCenter() {
        return new Point((int)(coordinates[0]+coordinates[2]/2),(int)(coordinates[1]+coordinates[3]/2));
    }


    public double[] getCoordinates() {
        return this.coordinates;
    }

    public Own_Rect createClone() {
        Own_Rect result = new Own_Rect(this.coordinates[0],this.coordinates[1],this.coordinates[2],this.coordinates[3]);
        result.setColor(this.getColor());
        return result;
    }

    public void changeCoords(double x, double y, double w, double h) {
        this.coordinates = new double[]{x, y, w, h};
    }

    public boolean equal(Own_Rect c) {
        AtomicBoolean result = new AtomicBoolean(true);
        IntStream.range(0,this.coordinates.length).forEach(n -> {if(this.coordinates[n] != c.getCoordinates()[n]) result.set(false);});
        return result.get() && this.getColor() == c.getColor();
    }
}
