package shape;

import java.awt.*;

public class Own_Ellipse {
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
    public double[] getCoordinates(){
        return this.coordinates;
    }
}
