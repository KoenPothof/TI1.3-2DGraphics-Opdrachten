import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.geom.Line2D;

public class House extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Canvas canvas = new Canvas(1920, 1080);
        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
        primaryStage.setScene(new Scene(new Group(canvas)));
        primaryStage.setTitle("House");
        primaryStage.show();
    }


    public void draw(FXGraphics2D graphics) {
        //foundatian
        graphics.draw(new Line2D.Double(200, 1000, 800, 1000));
        graphics.draw(new Line2D.Double(200, 1000, 200, 600));
        graphics.draw(new Line2D.Double(800, 1000, 800, 600));
        //door
        graphics.draw(new Line2D.Double(250, 1000, 250, 725));
        graphics.draw(new Line2D.Double(375, 1000, 375, 725));
        graphics.draw(new Line2D.Double(250, 725, 375, 725));
        //window
        graphics.draw(new Line2D.Double(450, 675, 725, 675));
        graphics.draw(new Line2D.Double(450, 850, 725, 850));
        graphics.draw(new Line2D.Double(450, 850, 450, 675));
        graphics.draw(new Line2D.Double(725, 850, 725, 675));
        //roof
        graphics.draw(new Line2D.Double(200, 600, 500,200 ));
        graphics.draw(new Line2D.Double(500, 200, 800,600 ));
    }



    public static void main(String[] args) {
        launch(House.class);
    }
}
