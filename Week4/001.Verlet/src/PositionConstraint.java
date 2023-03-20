import org.jfree.fx.FXGraphics2D;
import java.awt.geom.Point2D;

public class PositionConstraint implements Constraint {

    private Particle particle;
    private Point2D fixedPosition;

    public PositionConstraint(Particle particle) {
        this.particle = particle;
        if (this.particle != null) {
            fixedPosition = particle.getPosition();
        }
    }

    @Override
    public void satisfy() {
        if (particle != null) {
            particle.setPosition(fixedPosition);
        }
    }

    @Override
    public void draw(FXGraphics2D g2d) {

    }

    public Particle getParticle() {
        return particle;
    }

    public void setParticle(Particle particle) {
        this.particle = particle;
        if (particle != null) {
            fixedPosition = particle.getPosition();
        }
    }

    public void setFixedPosition(Point2D fixedPosition) {
        this.fixedPosition = fixedPosition;
    }

    public Point2D getPosition() {
        return fixedPosition;
    }

}
