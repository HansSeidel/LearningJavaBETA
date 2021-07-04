package shape;

import java.awt.*;
import java.util.ArrayList;

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
}
