import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Emmanuel_Salcedo_VerticesDistances extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a pane
        Pane pane = new Pane();

        // Create two circles in a random position on the board of radius 10
        Circle circle1 = new Circle(Math.random()*500, Math.random()*500, 10);
        setProperties(circle1);
        Circle circle2 = new Circle(Math.random()*500, Math.random()*500, 10);
        setProperties(circle2);

        // Place nodes in pane and add their position
        pane.getChildren().addAll(getLine(circle1, circle2), circle1,
                circle2, getText(circle1, circle2), getPos(circle1),getPos(circle2));

        // Create and handle mouse clicks and drags
        pane.setOnMouseDragged(mouse -> {
            if (circle1.contains(mouse.getX(), mouse.getY())) {
                //clear the current circles from the screen
                pane.getChildren().clear();
                //set the new circle to wherever the mouse is currently and redraw
                circle1.setCenterX(mouse.getX());
                circle1.setCenterY(mouse.getY());
                pane.getChildren().addAll(getLine(circle1, circle2), circle1,
                        circle2, getText(circle1, circle2), getPos(circle1), getPos(circle2));
            } //do the same if mouse is on the second circle
            else if (circle2.contains(mouse.getX(), mouse.getY())) {
                pane.getChildren().clear();
                circle2.setCenterX(mouse.getX());
                circle2.setCenterY(mouse.getY());
                pane.getChildren().addAll(getLine(circle1, circle2), circle1,
                        circle2, getText(circle1, circle2),  getPos(circle1),getPos(circle2));
            }
        });

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setTitle("Vertices and Distances"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    // Return a text of the distance between two circles
    private Text getText(Circle circle1, Circle circle2) {
        return new Text((circle1.getCenterX() + circle2.getCenterX()) / 2,
                (circle1.getCenterY() + circle2.getCenterY()) / 2,
                distance(circle1, circle2));
    }
    //get the position of the circle and diplay its current position
    private Text getPos(Circle circle){
        Text pos =  new Text(circle.getCenterX()- circle.getRadius(),(circle.getCenterY()-circle.getRadius()-2),"("+(int)circle.getCenterX()
                +", "+(int)circle.getCenterY()+")" );
        pos.setFont(new Font(5));
        return pos;


    }

    // Return a line drawn between two circles
    private Line getLine(Circle circle1, Circle circle2) {
        return new Line(circle1.getCenterX(), circle1.getCenterY(),
                circle2.getCenterX(), circle2.getCenterY());
    }

    // Set specified properties in a circle
    private void setProperties(Circle c) {
        c.setFill(Color.WHITE);
        c.setStroke(Color.BLACK);
    }

    // Return distance between two circles
    private String distance(Circle c1, Circle c2) {
        double distance = Math.sqrt(Math.pow(c2.getCenterX() - c1.getCenterX(), 2)
                + Math.pow(c2.getCenterY() - c1.getCenterY(), 2)) - 20;
        return String.format("%.0f", distance);
    }
}
