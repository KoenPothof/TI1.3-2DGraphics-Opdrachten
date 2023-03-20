import org.jfree.fx.FXGraphics2D;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Particle {

    private Point2D position;
    private Point2D lastPosition;
    private double size;

    public Particle(Point2D position) {
        this.position = position;
        this.lastPosition = position;
    }

    public void update(int width, int height) {
        Point2D previousPosition = this.position;
        this.position = new Point2D.Double(
                this.position.getX() + (this.position.getX() - this.lastPosition.getX()),
                this.position.getY() + (this.position.getY() - this.lastPosition.getY() + 0.1)
        );

        this.position = new Point2D.Double(
                Math.min(Math.max(0, this.position.getX()), width),
                Math.min(Math.max(0, this.position.getY()), height));

        this.lastPosition = previousPosition;
    }

    public void draw(FXGraphics2D g2d) {
        g2d.setColor(Color.blue);
        g2d.fill(new Ellipse2D.Double(this.position.getX()-10, this.position.getY()-10, 20, 20));
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }
}
