package shape;

import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

public class Own_Rect {
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
