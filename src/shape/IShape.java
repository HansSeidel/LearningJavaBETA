package shape;

import java.awt.*;

public interface IShape {
    public double[] getCoordinates();
    public String getShape();
    public Point getCenter();
    public double getDistanceBetween(Object o);
}
