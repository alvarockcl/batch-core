package cl.fuentes.batch.tool;

import java.awt.Point;
import java.awt.geom.Point2D;

public class DoublePoint extends Point2D {

    public double x;
    public double y;

    public DoublePoint() {
        x = 0; y = 0;
    }
    public DoublePoint(double x, double y) {
        setLocation(x, y);
    }
    
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    public void setLocation(double x, double y) {
        this.x = x; this.y = y;
    }

    public String toString() {
        return "DoublePoint[x: "+x+", y: "+y+"]";
    }
    public Point toPoint() {
        return new Point((int)Math.round(this.x), (int)Math.round(this.y));
    }
}