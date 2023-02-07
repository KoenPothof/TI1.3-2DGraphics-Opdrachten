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

public class Moon extends Application {
    private ResizableCanvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("Moon");
        primaryStage.show();
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
    }


    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());


        GeneralPath path = new GeneralPath();
        path.moveTo(0, 0);
        path.curveTo( 280, 0, 370, 300, 0, 350);
        path.closePath();

        Ellipse2D shape = new Ellipse2D.Double( 40, 45, 300, 300);
        graphics.setColor(Color.BLACK);
        graphics.draw(shape);
        graphics.fill(shape);
        graphics.setColor(Color.WHITE);
        graphics.draw(path);
        graphics.fill(path);
    }


    public static void main(String[] args)
    {
        launch(Moon.class);
    }

}
