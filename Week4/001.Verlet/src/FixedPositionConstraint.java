import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.Point2D;

public class FixedPositionConstraint implements Constraint {
    private Particle particle;
    private Point2D position;

    public FixedPositionConstraint(Particle particle) {
        this.particle = particle;
        if(this.particle != null)
            this.position = particle.getPosition();
    }

    @Override
    public void update() {
        if (this.particle != null) {
            this.particle.setPosition(this.position);
        }
    }

    @Override
    public void draw(FXGraphics2D graphics) {

    }

    public Particle getParticle() {
        return particle;
    }

    public void setParticle(Particle particle) {
        this.particle = particle;
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }
}
