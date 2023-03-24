import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Particle {
    private Point2D position;
    private Point2D oldPosition;

    public Particle(Point2D position) {
        this.position = position;
        this.oldPosition = position;
    }

    public void update(double width, double height)
    {
        Point2D previousPosition = this.position;
        this.position = new Point2D.Double(
                this.position.getX() + (this.position.getX() - this.oldPosition.getX()),
                this.position.getY() + (this.position.getY() - this.oldPosition.getY() + 0.1)
        );

        this.position = new Point2D.Double(
                Math.min(Math.max(0, this.position.getX()), width),
                Math.min(Math.max(0, this.position.getY()), height));

        this.oldPosition = previousPosition;
    }

    public void draw(Graphics2D g2d)
    {
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
