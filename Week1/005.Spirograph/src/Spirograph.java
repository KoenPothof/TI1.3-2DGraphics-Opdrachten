import java.awt.*;
import java.awt.geom.*;
import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

public class Spirograph extends Application {
    private TextField v1;
    private TextField v2;
    private TextField v3;
    private TextField v4;
    private Canvas canvas = new Canvas(1920, 1080);
    boolean buttonState = false;

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox vBox = new VBox();
        HBox hBox = new HBox();
        vBox.getChildren().add(hBox);
        vBox.getChildren().add(new Group(canvas));
        Button button= new Button("Spinala");
        Button clearButton= new Button("Clear");

        hBox.getChildren().add(v1 = new TextField("300"));
        hBox.getChildren().add(v2 = new TextField("1"));
        hBox.getChildren().add(v3 = new TextField("300"));
        hBox.getChildren().add(v4 = new TextField("10"));
        hBox.getChildren().add(button);
        hBox.getChildren().add(clearButton);

        canvas.getGraphicsContext2D().translate(canvas.getWidth()/2, canvas.getHeight()/2);
        canvas.getGraphicsContext2D().scale(1,-1);

        button.setOnAction(event -> {
            if(buttonState){
                new Alert(Alert.AlertType.NONE, "Clear before *Spinala*", ButtonType.OK).show();
            }else {
                draw(new FXGraphics2D(canvas.getGraphicsContext2D()));
                buttonState = true;
        }});

        clearButton.setOnAction(event -> {
            canvas.getGraphicsContext2D().clearRect(-5000, -5000 , 10000, 10000);
            buttonState = false;
        });

        primaryStage.setScene(new Scene(vBox));
        primaryStage.setTitle("Spirograph");

        primaryStage.show();
    }
    
    public void draw(FXGraphics2D graphics) {
        double resolution = 0.0001;
        double scale = 0.5;

        float x =0;
        float y =0;
        float x2 =0;
        float y2 =0;
        float set1 =(float)Double.parseDouble((v1.getText()));
        float set2 =(float)Double.parseDouble((v2.getText()));
        float set3 =(float)Double.parseDouble((v3.getText()));
        float set4 =(float)Double.parseDouble((v4.getText()));

        for (double i = 0; i < 2* Math.PI ; i+= resolution) {
            graphics.setColor(Color.getHSBColor((float) (i/Math.PI), 1, 1));

            graphics.draw(new Line2D.Double(x*scale, y*scale, x2*scale, y2*scale));
            x2 = set1*(float) Math.cos(set2 * i) + set3*(float) Math.cos(set4*i);
            y2 = set1*(float) Math.sin(set2 * i) + set3*(float) Math.sin(set4*i);
            x = x2;
            y = y2;
        }
    }

    public static void main(String[] args) {
        launch(Spirograph.class);
    }

}
