import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.*;
import java.util.Random;
import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class Eindopdracht extends Application {

    private TextField f1;
    private ResizableCanvas canvas;
    private Point2D position;
    private Random random = new Random();
    private int size = 50;
    private int posX = 125;
    private int posY = 0;
    private int sizeCounter = 0;
    private String word = "Hello World";

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

        canvas.setOnMouseClicked(e ->
        {
            if(e.getButton()== MouseButton.SECONDARY){
                sizeCounter++;
                size+=20;
                posX+=45;
                if(sizeCounter>=5){
                    word = "super big ( ͡° ͜ʖ ͡°)";
                }
                draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
            }
        });

        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("GradientPaint");
        primaryStage.show();
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
    }


    public void draw(FXGraphics2D graphics) {
        graphics.setTransform(new AffineTransform());
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        Point2D focus = new Point2D.Float((float) (canvas.getWidth()/2), (float) (canvas.getHeight()/2));
        float[] dist = {0.0f, 0.2f, 1.0f};
        float random1 = random.nextFloat();
        float random2 = random.nextFloat();
        Color color = Color.getHSBColor(random1, 0.5f, 1f);
        Color color2 = Color.getHSBColor(random2, 1f, 2f);
        Color[] colors = {color, Color.BLACK, color2};
        float radius = 50;
        Point2D center = new Point2D.Float((float) (position.getX()), (float) (position.getY()));

        Font font = new Font("Arial", Font.BOLD, size);
        Shape shape = font.createGlyphVector(graphics.getFontRenderContext(), word).getOutline();
        AffineTransform transform = new AffineTransform();
        transform = graphics.getTransform();
        transform.translate(position.getX()-posX, position.getY()+posY);
        graphics.transform(transform);

        RadialGradientPaint p = new RadialGradientPaint(center, radius, focus, dist, colors, MultipleGradientPaint.CycleMethod.REFLECT);
        graphics.setPaint(p);
        graphics.fill(shape);

        graphics.setClip(null);
        graphics.setPaint(Color.BLACK);
        graphics.draw(shape);
    }


    public static void main(String[] args)
    {
        launch(Eindopdracht.class);
    }

}
