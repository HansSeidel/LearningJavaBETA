package shape;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class Own_Ellipse implements IShape{
    private double[] coordinates;
    private Color color;
    private String shape;

    public Own_Ellipse(double x, double y, double w, double h) {
        this.coordinates = new double[] {x,y,w,h};
        this.color = Color.WHITE;
        this.shape = "ellipse";
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

    public double[] getCoordinates(){
        return this.coordinates;
    }
}
