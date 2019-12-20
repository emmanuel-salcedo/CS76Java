package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Emmanuel_Salcedo_Clock extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Create a clock pane
        ClockPane clock = new ClockPane();

        // Create a hBox and set it to have a spacing of 20 and to be center aligned
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);

        // Create two buttons one for Stop an another for start
        Button btStop = new Button("Stop");
        Button btStart = new Button("Start");

        // Set event handlers, e is the event
        btStop.setOnAction(e -> clock.pause()); // if Stop Button is pressed then pause the clock
        btStart.setOnAction(e -> clock.play()); // if Start Button is pressed then play the clock

        hBox.getChildren().addAll(btStop, btStart); //add button to the box

        // Create a border pane and place the nodes in to it
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(clock);
        borderPane.setBottom(hBox);

        // Create a scene and place it in the stage
        Scene scene = new Scene(borderPane, 250, 270);
        primaryStage.setTitle("ClockAnimation"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }


    public static void main(String[] args) {
        launch(args);
    }

}

class ClockPane extends Pane {
    private int hour;
    private int minute;
    private int second;
    private Timeline animation;

    /**
     * Construct a default clock with the current time
     */
    public ClockPane() {
        setCurrentTime();
        animation = new Timeline(
                new KeyFrame(Duration.millis(1000), e -> timeMovement()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }

    /**
     * Construct a clock with specified hour, minute, and second
     */
    public ClockPane(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;

    }

    /**
     * Return hour
     */
    public int getHour() {
        return hour;
    }

    /**
     * Set a new hour
     */
    public void setHour(int hour) {
        this.hour = hour;
        paintClock();
    }

    /**
     * Return minute
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Set a new minute
     */
    public void setMinute(int minute) {
        this.minute = minute;
        paintClock();
    }

    /**
     * Return second
     */
    public int getSecond() {
        return second;
    }

    /**
     * Set a new second
     */
    public void setSecond(int second) {
        this.second = second;
        paintClock();
    }

    /* Set the current time for the clock */
    public void setCurrentTime() {
        // Construct a calendar for the current date and time
        Calendar calendar = new GregorianCalendar();

        // Set current hour, minute and second
        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.minute = calendar.get(Calendar.MINUTE);
        this.second = calendar.get(Calendar.SECOND);

        paintClock(); // Repaint the clock
    }

    /**
     * Paint the clock
     */
    private void paintClock() {
        // Initialize clock parameters
        double clockRadius =
                Math.min(getWidth(), getHeight()) * 0.8 * 0.5;
        double centerX = getWidth() / 2;
        double centerY = getHeight() / 2;

        // Draw circle and add the digital clock under the hands
        Circle circle = new Circle(centerX, centerY, clockRadius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        Text currenttime = new Text(centerX - clockRadius * .25, (centerY + clockRadius * .3), this.getHour() + ":" + this.getMinute() + ":" + this.getSecond());


        // Draw second hand
        double sLength = clockRadius * 0.8;
        double secondX = centerX + sLength *
                Math.sin(second * (2 * Math.PI / 60));
        double secondY = centerY - sLength *
                Math.cos(second * (2 * Math.PI / 60));
        Line sLine = new Line(centerX, centerY, secondX, secondY);
        sLine.setStroke(Color.RED);


        // Draw minute hand
        double mLength = clockRadius * 0.65;
        double xMinute = centerX + mLength *
                Math.sin(minute * (2 * Math.PI / 60));
        double minuteY = centerY - mLength *
                Math.cos(minute * (2 * Math.PI / 60));
        Line mLine = new Line(centerX, centerY, xMinute, minuteY);
        mLine.setStroke(Color.BLUE);

        // Draw hour hand
        double hLength = clockRadius * 0.5;
        double hourX = centerX + hLength *
                Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
        double hourY = centerY - hLength *
                Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
        Line hLine = new Line(centerX, centerY, hourX, hourY);
        hLine.setStroke(Color.GREEN);

        getChildren().clear();
        //getChildren().addAll(circle, t1, t2, t3, t4, sLine, mLine, hLine);
        getChildren().addAll(circle, sLine, mLine, hLine, currenttime);

        //loop throuugh each tickmark
        for (int i = 0; i < 60; i++) {
            //if the tick mark is one of the hour marks then draw a long tick
            if (i % 5 == 0) {
                //find the x and y coordinate at the point at the edge of the circle
                double startX = centerX + clockRadius * Math.cos(2 * i * Math.PI / 60);
                double startY = centerY + clockRadius * Math.sin(2 * i * Math.PI / 60);
                //find the x and y coordinate for the point that is inside the circle
                double endX = startX - (clockRadius * .1) * Math.cos(2 * i * Math.PI / 60);
                double endY = startY - (clockRadius * .1) * Math.sin(2 * i * Math.PI / 60);
                //draw the line
                getChildren().add(new Line(startX, startY, endX, endY));

                //if the tick mark is not one of the hour marks then draw a long tick
            } else {
                //find the x and y coordinate at the point at the edge of the circle
                double startX = centerX + clockRadius * Math.cos(2 * i * Math.PI / 60);
                double startY = centerY + clockRadius * Math.sin(2 * i * Math.PI / 60);
                //find the x and y coordinate for the point that is inside the circle
                double endX = startX - (clockRadius * .05) * Math.cos(2 * i * Math.PI / 60);
                double endY = startY - (clockRadius * .05) * Math.sin(2 * i * Math.PI / 60);
                //draw the line
                getChildren().add(new Line(startX, startY, endX, endY));
            }
        }
        // display each hour on the clock
        for (int i = 0; i < 12; i++) {
            int currentHour = i;
            if (currentHour + 3 > 12) {
                currentHour -= 12;
            }
            double textX = centerX + (clockRadius / 1.25) * Math.cos(2 * i * Math.PI / 12) - 5;
            double textY = centerY + (clockRadius / 1.25) * Math.sin(2 * i * Math.PI / 12) + 4;

            Text text = new Text(textX, textY, String.valueOf(currentHour + 3));
            getChildren().add(text);


        }

    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        paintClock();
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);
        paintClock();
    }

    protected void timeMovement() {
        if (minute == 60) {
            hour += 1;
        }
        if (second == 60) {
            minute += 1;
        }
        if(second < 60){
            second++;
        }else{
            second=1;
        }
        paintClock();
    }

    public void play() {
        this.setCurrentTime();
        animation.play();
    }

    public void pause() {
        animation.pause();
    }
}

