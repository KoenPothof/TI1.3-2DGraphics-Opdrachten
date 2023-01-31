import java.awt.*;
import java.awt.geom.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import static javax.swing.text.StyleConstants.setBackground;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

public class Spiral extends Application {
    private Canvas canvas = new Canvas(1920, 1080);
    @Override
    public void start(Stage primaryStage) throws Exception {
        Canvas canvas = new Canvas(1920, 1080);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        primaryStage.setScene(new Scene(new Group(canvas)));
        primaryStage.setTitle("Spiral");
        primaryStage.show();
    }
    
    
    public void draw(FXGraphics2D graphics) {
        graphics.translate(canvas.getWidth()/2, canvas.getHeight()/2);
        graphics.scale( 1, -1);

        graphics.setColor(Color.black);
        double resolution = 0.01;
        double scale = 10.0;

        for (double i = 0; i < 20; i += resolution)
        {
            double x1 =  i*Math.cos(i);
            double y1 =  i*Math.sin(i);
            double x2 =  i*Math.cos(i + resolution);
            double y2 =  i*Math.sin(i + resolution);
            graphics.draw(new Line2D.Double(x1*scale, y1*scale, x2*scale, y2*scale));
        }
    }

    public static void main(String[] args) {
        launch(Spiral.class);
    }
}
