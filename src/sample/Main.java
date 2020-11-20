package sample;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.util.Date;

import static javafx.scene.control.SelectionMode.MULTIPLE;

public class Main extends Application {

    private int counter = 0 ;
    
    static ObservableList<String> list1 = FXCollections.observableArrayList();
    static ObservableList<String> list2 = FXCollections.observableArrayList();
    static ObservableList<String> list3 = FXCollections.observableArrayList();
    static File file1 = new File("list1File");
    static File file2 = new File("list2File");
    static File file3 = new File("list3File");
    static Label clockEvent = new Label();
    static ObservableList<String> ClockEventsStrings = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws IOException {
        FileClass.createFile(file1);
        FileClass.createFile(file2);
        FileClass.createFile(file3);
        list1 = FileToArray.arrayMaker(file1);
        list2 = FileToArray.arrayMaker(file2);
        list3 = FileToArray.arrayMaker(file3);

        primaryStage.show();

        BorderPane paneOne = new BorderPane();
        BorderPane paneTwo = new BorderPane();
        BorderPane paneThree = new BorderPane();

        Label list1Header = new Label("Desk");
        Label list2Header = new Label("Shelf");
        Label list3Header = new Label("Trash");
        Label list4Header = new Label("Activity");

        Button addBtn = new Button("Add");
        TextField addTxt = new TextField();
        addTxt.setOnAction(e -> {
            if (!addTxt.getText().isEmpty()){
                list1.add(addTxt.getText());
                addTxt.clear();
                ArrayToFile.writeToFile(file1, list1);
            }
        });
//This lambda handles any press of the "add" button.
        addBtn.addEventHandler(ActionEvent.ACTION, (e) -> {
            if (!addTxt.getText().isEmpty()){
                list1.add(addTxt.getText());
                addTxt.clear();
                ArrayToFile.writeToFile(file1, list1);
            }
        });

        GridPane grdPane = new GridPane();

        BorderPane deskPane = new BorderPane(PanePopulater.populatePane(paneOne, list1, list2, list3));
        BorderPane shelfPane = new BorderPane(PanePopulater.populatePane(paneTwo, list2, list1, list3));
        BorderPane trashPane = new BorderPane(PanePopulater.populatePane(paneThree, list3, list1, list2));
        ListView<String> activityView = new ListView<String>(ClockEventsStrings);

        grdPane.add(deskPane, 0,1);
        grdPane.add(shelfPane, 1,1);
        grdPane.add(trashPane, 2,1);
        grdPane.add(activityView, 3,1);
//        grdPane.add(activityPane, 3,1);
        grdPane.add(list1Header, 0,0);
        grdPane.add(list2Header,1,0);
        grdPane.add(list3Header,2,0);
        grdPane.add(list4Header,3,0);
        grdPane.add(addTxt, 0,2);
        grdPane.add(addBtn, 0,3);
        grdPane.add(clockEvent, 4,0);

        for (int i = 0; i < 4; i++) {
            ColumnConstraints column = new ColumnConstraints(280);
            grdPane.getColumnConstraints().add(column);
        }
        Scene scene = new Scene(grdPane);
        primaryStage.setScene(scene);

    }

    public static void main(String[] args) {
        launch(args);

    }
}