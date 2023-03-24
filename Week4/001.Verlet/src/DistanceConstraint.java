        import org.jfree.fx.FXGraphics2D;

        import java.awt.*;
        import java.awt.geom.Line2D;
        import java.awt.geom.Point2D;

public class DistanceConstraint implements Constraint {

    private double distance;
    private Particle a;
    private Particle b;

    public DistanceConstraint(Particle a, Particle b) {
        this(a, b, a.getPosition().distance(b.getPosition()));
    }

    public DistanceConstraint(Particle a, Particle b, double distance) {
        this.a = a;
        this.b = b;
        this.distance = distance;
    }

    @Override
    public void update() {

        double currentDistance = a.getPosition().distance(b.getPosition());
        double adjustmentDistance = (currentDistance - distance) / 2;

        Point2D pointer = new Point2D.Double(b.getPosition().getX() - a.getPosition().getX(), b.getPosition().getY() - a.getPosition().getY());
        double length = pointer.distance(0, 0);
        if (length > 0.0001)
        {
            pointer = new Point2D.Double(pointer.getX() / length, pointer.getY() / length);
        } else {
            pointer = new Point2D.Double(1, 0);
        }

        a.setPosition(new Point2D.Double(a.getPosition().getX() + pointer.getX() * adjustmentDistance,
                a.getPosition().getY() + pointer.getY() * adjustmentDistance));
        b.setPosition(new Point2D.Double(b.getPosition().getX() - pointer.getX() * adjustmentDistance,
                b.getPosition().getY() - pointer.getY() * adjustmentDistance));
    }

    @Override
    public void draw (FXGraphics2D g2d) {
        float colour = (float) -(((a.getPosition().distance(b.getPosition()) - distance) / 2) / distance) ;
        g2d.setColor(Color.getHSBColor((float) (colour/1.1 + .33f), 1f,.9f ));
        if (colour < -.4f || colour > .015){
            g2d.setColor(Color.getHSBColor(1f, 1f,.9f ));
        } else if  (colour > .0000000013f ){
            g2d.setColor(Color.getHSBColor(-20*colour + .33f, 1f,.9f ));
        }

        g2d.draw(new Line2D.Double(a.getPosition(), b.getPosition()));
    }
}