import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class GradientPaintExercise extends Application {
    private ResizableCanvas canvas;
    private Point2D position;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        position = new Point2D.Double(canvas.getWidth()/2,canvas.getHeight()/2);
        canvas.setOnMouseDragged(e ->
        {
            position = new Point2D.Float((int) e.getX(), (int) e.getY());
            draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        });

        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("GradientPaint");
        primaryStage.show();
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
    }


    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        float[] dist = {0.1f, 0.2f, 1.0f};
        Color[] colors = {Color.RED, Color.WHITE, Color.BLUE};
        float radius = 100;

        RadialGradientPaint p = new RadialGradientPaint(position, radius, dist, colors);

        graphics.setPaint(p);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

    }


    public static void main(String[] args)
    {
        launch(GradientPaintExercise.class);
    }

}
