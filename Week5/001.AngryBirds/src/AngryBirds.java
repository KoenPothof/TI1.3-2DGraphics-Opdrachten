
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Vector2;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

public class AngryBirds extends Application {

    private ResizableCanvas canvas;
    private World world;
    private MousePicker mousePicker;
    private Camera camera;
    private boolean debugSelected = false;
    private ArrayList<GameObject> gameObjects = new ArrayList<>();
    private Body piggeh = new Body();
    private Body blue = new Body();
    private Body obstacle1 = new Body();
    private Body obstacle2 = new Body();
    private Body obstacle3 = new Body();
    private Body cloud1 = new Body();
    private Body cloud2 = new Body();
    private Body ground = new Body();
    private Convex groundConvex;
    private Convex cloudConvex1;
    private Convex cloudConvex2;

    @Override
    public void start(Stage stage) throws Exception {

        BorderPane mainPane = new BorderPane();

        // Add debug button
        javafx.scene.control.CheckBox showDebug = new CheckBox("Show debug");
        showDebug.setOnAction(e -> {
            debugSelected = showDebug.isSelected();
        });
        mainPane.setTop(showDebug);

        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());

        camera = new Camera(canvas, g -> draw(g), g2d);
        mousePicker = new MousePicker(canvas);

        cloudConvex1 = Geometry.createRectangle(50, 1.1);
        cloud1.getTransform().setTranslation(-3, 6);
        cloud1.setMass(MassType.INFINITE);
        cloud1.addFixture(cloudConvex1);
        world.addBody(cloud1);

        gameObjects.add(new GameObject("/images/cloud.png", cloud1, new Vector2(0,0), 1.3));

        cloudConvex2 = Geometry.createRectangle(50, 1.1);
        cloud2.getTransform().setTranslation(10, 5.6);
        cloud2.setMass(MassType.INFINITE);
        cloud2.addFixture(cloudConvex1);
        world.addBody(cloud2);

        gameObjects.add(new GameObject("/images/cloud.png", cloud2, new Vector2(0,0), 1.3));

        groundConvex = Geometry.createRectangle(100, 1.1);
        ground.getTransform().setTranslation(0, -4.3);
        ground.setMass(MassType.INFINITE);
        ground.addFixture(groundConvex);
        world.addBody(ground);

        gameObjects.add(new GameObject("/images/newGround.png", ground, new Vector2(0,0), 1.3));

        blue.addFixture(Geometry.createCircle(0.4));
        blue.getTransform().setTranslation(0, -3);
        blue.setMass(MassType.NORMAL);
        blue.getFixture(0).setRestitution(0.3);
        world.addBody(blue);

        gameObjects.add(new GameObject("/images/blue.png", blue, new Vector2(0,0), 0.1));


        piggeh.addFixture(Geometry.createCircle(0.4));
        piggeh.getTransform().setTranslation(5, 5);
        piggeh.setMass(MassType.NORMAL);
        piggeh.getFixture(0).setRestitution(0.3);
        world.addBody(piggeh);

        gameObjects.add(new GameObject("/images/piggeh.png", piggeh, new Vector2(0,0), 0.23));

        obstacle1.addFixture(Geometry.createRectangle(2.5, 0.22));
        obstacle1.getTransform().setTranslation(3, -6);
        obstacle1.setMass(MassType.NORMAL);
        obstacle1.rotate(Math.toRadians(90.0f));
        obstacle1.getFixture(0).setRestitution(0.3);
        world.addBody(obstacle1);

        gameObjects.add(new GameObject("/images/obstacle.png", obstacle1, new Vector2(0,0), 1));

        obstacle2.addFixture(Geometry.createRectangle(2.5, 0.22));
        obstacle2.getTransform().setTranslation(3, -4);
        obstacle2.setMass(MassType.NORMAL);
        obstacle2.rotate(Math.toRadians(90.0f));
        obstacle2.getFixture(0).setRestitution(0.3);
        world.addBody(obstacle2);

        gameObjects.add(new GameObject("/images/obstacle.png", obstacle2, new Vector2(0,0), 1));

        obstacle3.addFixture(Geometry.createRectangle(2.5, 0.22));
        obstacle3.getTransform().setTranslation(5, 4);
        obstacle3.setMass(MassType.NORMAL);
        obstacle3.getFixture(0).setRestitution(0.3);
        world.addBody(obstacle3);

        gameObjects.add(new GameObject("/images/obstacle.png", obstacle3, new Vector2(0,0), 1));
        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now) {
                if (last == -1) {
                    last = now;
                }
                update((now - last) / 1000000000.0);
                last = now;
                draw(g2d);
            }
        }.start();

        stage.setScene(new Scene(mainPane, 1920, 1080));
        stage.setTitle("Angry Birds");
        stage.show();
        draw(g2d);
    }

    public void init() {
        world = new World();
        world.setGravity(new Vector2(0, -9.8));
    }

    public void draw(FXGraphics2D graphics) {
        Color skyColor = new Color(51,204,255);
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(skyColor);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        graphics.translate(canvas.getWidth()/2, canvas.getHeight()/2);
        graphics.scale( 1, -1);

        AffineTransform originalTransform = graphics.getTransform();

        graphics.setTransform(camera.getTransform((int) canvas.getWidth(), (int) canvas.getHeight()));
        graphics.scale(1, -1);

        for (GameObject go : gameObjects) {
            go.draw(graphics);
        }

        if (debugSelected) {
            graphics.setColor(Color.blue);
            DebugDraw.draw(graphics, world, 100);
        }

        graphics.setTransform(originalTransform);
    }

    public void update(double deltaTime) {
        mousePicker.update(world, camera.getTransform((int) canvas.getWidth(), (int) canvas.getHeight()), 100);
        world.update(deltaTime);
    }

    public static void main(String[] args) {
        launch(AngryBirds.class);
    }

}
