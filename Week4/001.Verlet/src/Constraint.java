import org.jfree.fx.FXGraphics2D;

import java.awt.*;

public interface Constraint {
    void update();
    void draw( FXGraphics2D graphics);
}
