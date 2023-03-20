import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class Screensaver extends Application {
    private ResizableCanvas canvas;
    private Line lined1;
    private Line lined2;
    private Line lined3;
    private Line lined4;
    private static double lineSpeed = 0.5;
    private ArrayList<Line> lines = new ArrayList<>();
    private ArrayList<Line> previousLines = new ArrayList<>();

    @Override
    public void start(Stage stage) throws Exception
    {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());
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
        stage.setTitle("Screensaver");
        stage.show();
        g2d.setColor(Color.GREEN);
        draw(g2d);
    }


    public void draw(FXGraphics2D graphics)
    {
        graphics.setTransform(new AffineTransform());
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        graphics.setColor(Color.GREEN);

        for (Line line : lines) {
            line.draw(graphics);
        }

        for (Line previousLine : previousLines) {
            previousLine.draw(graphics);
        }
    }

    public void init()
    {
        lined1 = new Line(100, 100, 500, 100, -1, -1, -1, 1, lineSpeed);
        lined2 = new Line(100, 400, 500, 400, 1, 1, 1, -1, lineSpeed);
        lined3 = new Line(500, 400, 500, 100, 1, -1, -1, 1, lineSpeed);
        lined4 = new Line(100, 400,100, 100,  1, 1, -1, -1, lineSpeed);
        lines.add(lined1);
        lines.add(lined2);
        lines.add(lined3);
        lines.add(lined4);
    }

    public void update(double deltaTime)
    {
        double xBorderMin = canvas.getLayoutBounds().getMinX();
        double xBorderMax = canvas.getLayoutBounds().getMaxX();
        double yBorderMin = canvas.getLayoutBounds().getMinY();
        double yBorderMax = canvas.getLayoutBounds().getMaxY();

        for (Line line : lines) {
            line.setStartX(line.getStartX() + line.xStartDir);
            line.setStartY(line.getStartY() + line.yStartDir);
            line.setEndX(line.getEndX() + line.xEndDir);
            line.setEndY(line.getEndY() + line.yEndDir);

            if(line.getStartX() < xBorderMin || line.getStartX() > xBorderMax)
                line.xStartDir = -line.xStartDir;
            if(line.getStartY() < yBorderMin || line.getStartY() > yBorderMax)
                line.yStartDir = -line.yStartDir;
            if((line.getEndX() < xBorderMin) || (line.getEndX() > xBorderMax))
                line.xEndDir = -line.xEndDir;
            if ((line.getEndY() < yBorderMin || line.getEndY() > yBorderMax))
                line.yEndDir = -line.yEndDir;
        }

        previousLines.add(new Line(lined1.getStartX(), lined1.getStartY(), lined1.getEndX(), lined1.getEndY(),
                lined1.xStartDir, lined1.yStartDir, lined1.xEndDir, lined1.yEndDir, lineSpeed));
        previousLines.add(new Line(lined2.getStartX(), lined2.getStartY(), lined2.getEndX(), lined2.getEndY(),
                lined2.xStartDir, lined2.yStartDir, lined2.xEndDir, lined2.yEndDir, lineSpeed));
        previousLines.add(new Line(lined3.getStartX(), lined3.getStartY(), lined3.getEndX(), lined3.getEndY(),
                lined3.xStartDir, lined3.yStartDir, lined3.xEndDir, lined3.yEndDir, lineSpeed));
        previousLines.add(new Line(lined4.getStartX(), lined4.getStartY(), lined4.getEndX(), lined4.getEndY(),
                lined4.xStartDir, lined4.yStartDir, lined4.xEndDir, lined4.yEndDir, lineSpeed));

        if (previousLines.size() > 400) {
            for (int i = 0; i < 6; i++) {
                previousLines.remove(i);
            }
        }
    }

    public static void main(String[] args)
    {
        launch(Screensaver.class);
    }

}