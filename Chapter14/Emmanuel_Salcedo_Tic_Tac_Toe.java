package sample;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Emmanuel_Salcedo_Tic_Tac_Toe extends Application {
    @Override
    public void start(final Stage stage) {
        //create a grid
        GridPane grid = new GridPane();
        //Loop through each cell in a 3x3 grid
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                //generate random number and if it is divisible by 3 then put an X, if there is a remainder of 1 put an O, anything else leave empty
                int n = (int) (Math.random() * 100);
                if (n % 3 == 0) {
                    //put an X in the cell and resize to 150x150
                    ImageView Ximg = new ImageView("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRwogcrMNhfBCgLHbZD-tXHNfRp1rAWqNZELS4ddmHQoNAOvuEi7A");
                    Ximg.setPreserveRatio(true);
                    Ximg.setFitHeight(150);
                    Ximg.setFitWidth(150);
                    grid.add(Ximg, i, j);
                } else if (n % 3 == 1) {
                    //put an O in the cell and resize to 150x150
                    ImageView Oimg = new ImageView("https://steamuserimages-a.akamaihd.net/ugc/178288930001811919/F903F7344337BA9951A9B20A4FFFE0D5076FB648/");
                    Oimg.setPreserveRatio(true);
                    Oimg.setFitHeight(150);
                    Oimg.setFitWidth(150);
                    grid.add(Oimg, i, j);

                } else
                    continue;
            }
        }


        // Sets Column Constraints to make the Grid 3 column of equal size, and aligned to the center.
        ColumnConstraints Col_Third = new ColumnConstraints();
        Col_Third.setPercentWidth(100 / 3.0);
        Col_Third.setHalignment(HPos.CENTER);
        grid.getColumnConstraints().addAll(Col_Third);

        //Sets Row Constraints, to make is a 3 rows of equal height and also aligned to the center
        RowConstraints Row_Third = new RowConstraints();
        Row_Third.setPercentHeight(100 / 3.0);
        Row_Third.setValignment(VPos.CENTER);
        grid.getRowConstraints().addAll(Row_Third);

        // layout the scene in a stackpane and sets the grid lines to be visible
        //creates a scene of 450x450 and displays the scene.
        grid.setGridLinesVisible(true);
        StackPane layout = new StackPane();
        layout.getChildren().addAll(grid);
        stage.setScene(new Scene(layout, 450, 450));
        stage.setTitle("Tic-Tac-Toe"); // Set the stage title
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}