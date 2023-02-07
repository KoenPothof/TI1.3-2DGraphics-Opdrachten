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

public class YingYang extends Application {
    private ResizableCanvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("Ying Yang");
        primaryStage.show();
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
    }


    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        Rectangle2D rec = new Rectangle2D.Double(0, 0, 250, 500);

        Ellipse2D shape = new Ellipse2D.Double( 0, 0, 500, 500);
        Ellipse2D shape2 = new Ellipse2D.Double( 125, 0, 250, 250);
        Ellipse2D shape3 = new Ellipse2D.Double( 125, 250, 250, 250);
        Ellipse2D shape4 = new Ellipse2D.Double( 223, 113, 50, 50);
        Ellipse2D shape5 = new Ellipse2D.Double( 223, 363, 50, 50);

        graphics.setColor(Color.BLACK);
        graphics.draw(shape);
        graphics.fill(shape);
        graphics.setColor(Color.WHITE);
        graphics.draw(rec);
        graphics.fill(rec);
        graphics.setColor(Color.WHITE);
        graphics.draw(shape2);
        graphics.fill(shape2);
        graphics.setColor(Color.BLACK);
        graphics.draw(shape3);
        graphics.fill(shape3);
        graphics.setColor(Color.BLACK);
        graphics.draw(shape);
        graphics.setColor(Color.BLACK);
        graphics.draw(shape4);
        graphics.fill(shape4);
        graphics.setColor(Color.WHITE);
        graphics.draw(shape5);
        graphics.fill(shape5);
    }


    public static void main(String[] args)
    {
        launch(YingYang.class);
    }

}
