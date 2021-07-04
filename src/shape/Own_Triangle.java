package shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

public class Own_Triangle {
    private double[] coordinates;
    private Color color;
    private String shape;

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
