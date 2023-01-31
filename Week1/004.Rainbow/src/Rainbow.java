import java.awt.*;
import java.awt.geom.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

public class Rainbow extends Application {
    private Canvas canvas = new Canvas(1920, 1080);
    @Override
    public void start(Stage primaryStage) throws Exception {
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        primaryStage.setScene(new Scene(new Group(canvas)));
        primaryStage.setTitle("Rainbow");
        primaryStage.show();
    }
    
    
    public void draw(FXGraphics2D graphics) {
        graphics.translate(canvas.getWidth()/2, canvas.getHeight()/2);
        graphics.scale( 1, -1);

        double resolution = 0.001;
        double scale = 50.0;
        float radiusBinnen = 7;
        float radiusBuiten = 10;

        for (double j = 0; j < Math.PI; j+=resolution) {

            float x1 = (float) (radiusBinnen * Math.cos(j));
            float y1 = (float) (radiusBinnen * Math.sin(j));
            float x2 = (float) (radiusBuiten * Math.cos(j));
            float y2 = (float) (radiusBuiten * Math.sin(j));

            graphics.setColor(Color.getHSBColor((float) (j / Math.PI), 1, 1));
            graphics.draw(new Line2D.Float((float) (x1 * scale), (float) (y1 * scale), (float) (x2 * scale), (float) (y2 * scale)));
        }

    }

    public static void main(String[] args) {
        launch(Rainbow.class);
    }

}
