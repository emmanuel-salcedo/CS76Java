package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Emmanuel_Salcedo_Clock extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Init new Clock and create time label
        ClockPane clock = new ClockPane();
        String timeString = clock.getHour() + ":" + clock.getMinute() + ":" + clock.getSecond();
        Label lblCurrentTime = new Label(timeString);
        //create a bordepane
        BorderPane pane = new BorderPane();
        //center the clock on the pane and display the digital time on the bottom and align it to the center
        pane.setCenter(clock);
        pane.setBottom(lblCurrentTime);
        BorderPane.setAlignment(lblCurrentTime, Pos.TOP_CENTER);

        //create a new scene, label the GUI as Clocl and Show it
        Scene scene = new Scene(pane, 250, 250);
        primaryStage.setTitle("Clock"); // Set the stage title
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}

class ClockPane extends Pane {
    private int hour;
    private int minute;
    private int second;

    /**
     * Construct a default clock with the current time
     */
    public ClockPane() {
        setCurrentTime();
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

        // Draw circle
        Circle circle = new Circle(centerX, centerY, clockRadius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        Text t1 = new Text(centerX - 8, centerY - clockRadius + 15, "12");
        Text t2 = new Text(centerX - clockRadius + 5, centerY + 5, "9");
        Text t3 = new Text(centerX + clockRadius - 15, centerY + 3, "3");
        Text t4 = new Text(centerX - 5, centerY + clockRadius - 6, "6");


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
        getChildren().addAll(circle, sLine, mLine, hLine);

        //loop throuugh each tickmark
        for (int i = 0; i < 60; i++) {
            //if the tick mark is one of the hour marks then draw a long tick
            if(i%5==0) {
                //find the x and y coordinate at the point at the edge of the circle
                double startX = centerX + clockRadius * Math.cos(2 * i * Math.PI / 60);
                double startY = centerY + clockRadius * Math.sin(2 * i * Math.PI / 60);
                //find the x and y coordinate for the point that is inside the circle
                double endX = startX - (clockRadius * .1) * Math.cos(2 * i * Math.PI / 60);
                double endY = startY - (clockRadius * .1) * Math.sin(2 * i * Math.PI / 60);
                //draw the line
                getChildren().add(new Line(startX, startY, endX, endY));

            //if the tick mark is not one of the hour marks then draw a long tick
            }else {
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
}

