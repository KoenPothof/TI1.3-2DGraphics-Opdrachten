import java.awt.*;
import java.awt.geom.*;

import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class Mirror extends Application {
    ResizableCanvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("Mirror");
        primaryStage.show();
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
    }


    public void draw(FXGraphics2D graphics) {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        graphics.translate(canvas.getWidth()/2, canvas.getHeight()/2);
        graphics.scale( 1, -1);

        graphics.setColor(Color.black);

        graphics.drawLine(0,1000,0, -1000);
        graphics.drawLine(1000,0,-1000, 0);
        Shape vierkant = new Rectangle2D.Double(-50,-50,100,100);
        graphics.setColor(Color.BLUE);
        double resolution = 0.1;
        double a = 2.5;

        for (double i = 0; i < 1000; i += resolution)
        {
            double x1 =  i;
            double y1 =  a*x1;
            double x2 =  i+ resolution;
            double y2 =  a*x2;
            graphics.draw(new Line2D.Double(x1, y1, x2, y2));
        }

        AffineTransform transformSquare = new AffineTransform();
        transformSquare.translate(0,150);
        graphics.draw(transformSquare.createTransformedShape(vierkant));

        AffineTransform mirror = new AffineTransform();
        mirror.concatenate(new AffineTransform((2/(1 + a * a)) - 1, (2 * a)/(1 + a * a),(2 * a)/(1 + a * a),((2 * a * a)/(1 + a * a)) - 1,0,0));
        graphics.draw(mirror.createTransformedShape(transformSquare.createTransformedShape(vierkant)));
    }

    public static void main(String[] args)
    {
        launch(Mirror.class);
    }

}