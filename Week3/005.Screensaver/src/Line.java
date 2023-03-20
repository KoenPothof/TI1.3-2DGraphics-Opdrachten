import org.jfree.fx.FXGraphics2D;
import java.awt.geom.Line2D;

public class Line {
    private double startX;
    private double endX;
    private double startY;
    private double endY;
    public double xStartDir;
    public double yStartDir;
    public double xEndDir;
    public double yEndDir;

    public Line(double startX, double startY, double endX, double endY, double xStartDir, double yStartDir, double xEndDir, double yEndDir, double lineVelocity) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.xStartDir = xStartDir * lineVelocity;
        this.xEndDir = xEndDir * lineVelocity;
        this.yStartDir = yStartDir * lineVelocity;
        this.yEndDir = yEndDir * lineVelocity;
    }

    public void draw(FXGraphics2D g2d)
    {
        g2d.draw(new Line2D.Double(getStartX(), getStartY(), getEndX(), getEndY()));
    }

    //X getters and setters
    public double getStartX() {
        return startX;
    }
    public void setStartX(double startX) {
        this.startX = startX;
    }
    public double getEndX() {
        return endX;
    }
    public void setEndX(double endX) {
        this.endX = endX;
    }

    //Y getters and setters
    public double getStartY() {
        return startY;
    }
    public void setStartY(double startY) {
        this.startY = startY;
    }
    public double getEndY() {
        return endY;
    }
    public void setEndY(double endY) {
        this.endY = endY;
    }
}