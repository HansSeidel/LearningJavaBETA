package shape;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

public class Own_Rect implements IShape{
    private double[] coordinates;
    private Color color;
    private String shape;

    public Own_Rect(double x, double y, double w, double h) {
        this.coordinates = new double[]{x, y, w, h};
        this.color = Color.WHITE;
        this.shape = "rect";
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

    @Override
    public double getDistanceBetween(Object o) {
        Point o_point;
        try {
            o_point = (Point) o.getClass().getMethod("getCenter").invoke(o);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return -2;
        }
        return Math.sqrt(Math.pow(this.getCenter().getX()-o_point.getX(),2)+Math.pow(this.getCenter().getY()-o_point.getY(),2)); //SQRT((400-49.5)^2+(200-49.5)^2)
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
