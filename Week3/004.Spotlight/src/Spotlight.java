import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class Spotlight extends Application {
    private ResizableCanvas canvas;
    private Point2D position;
    private Shape shape;
    private double x;
    private double y;
    private AffineTransform tx = new AffineTransform();
    private BufferedImage image;

    @Override
    public void start(Stage stage) throws Exception
    {

        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);

        position = new Point2D.Double(canvas.getWidth()/2,canvas.getHeight()/2);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());

        canvas.setOnMouseDragged(e -> mouseDragged(e));

        try {
            image = ImageIO.read(getClass().getResource("/images/peepee.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now)
            {
                if (last == -1)
                    last = now;
                update((now - last) / 1000000000.0);
                last = now;
                draw(g2d);
            }
        }.start();

        stage.setScene(new Scene(mainPane));
        stage.setTitle("Spotlight");
        stage.show();
        draw(g2d);
    }


    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        shape = new Ellipse2D.Double(position.getX()-100, position.getY()-100, 200, 200);
        graphics.setColor(Color.black);
        graphics.draw(shape);
        graphics.clip(shape);

        graphics.drawImage(image, 0, 0, (int) canvas.getWidth(), (int) canvas.getHeight(), null);
        graphics.setClip(null);
    }

    public void init()
    {

    }

    public void update(double deltaTime)
    {
    }

    private void mouseDragged(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();

        position.setLocation(x, y);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
    }

    public static void main(String[] args)
    {
        launch(Spotlight.class);
    }

}
