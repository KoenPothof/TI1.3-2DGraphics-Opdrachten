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

public class Colors extends Application {
    private ResizableCanvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("Colors");
        primaryStage.show();
    }


    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());


        for (int i = 0; i < 13; i++) {
            Rectangle2D rec = new Rectangle2D.Double(i*100, 10, 50, 50);
            graphics.setColor(Color.BLACK);
            graphics.draw(rec);
            if(i==0) {
                graphics.setColor(Color.BLACK);
            } else if (i==1) {
                graphics.setColor(Color.BLUE);
            } else if (i==2) {
                graphics.setColor(Color.CYAN);
            } else if (i==3) {
                graphics.setColor(Color.DARK_GRAY);
            }else if (i==4){
                graphics.setColor(Color.GRAY);
            } else if (i==5) {
                graphics.setColor(Color.GREEN);
            } else if (i==6) {
                graphics.setColor(Color.LIGHT_GRAY);
            } else if (i==7) {
                graphics.setColor(Color.MAGENTA);
            } else if (i==8) {
                graphics.setColor(Color.ORANGE);
            } else if (i==9) {
                graphics.setColor(Color.pink);
            } else if (i==10) {
                graphics.setColor(Color.red);
            } else if (i==11) {
                graphics.setColor(Color.white);
            } else if (i==12) {
                graphics.setColor(Color.yellow);
            }

            graphics.fill(rec);
        }



    }


    public static void main(String[] args)
    {
        launch(Colors.class);
    }

}
