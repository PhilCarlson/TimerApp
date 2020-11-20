package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import java.io.IOException;
import java.time.Clock;
import java.util.Date;

import static javafx.scene.control.SelectionMode.MULTIPLE;

public class XCell extends ListCell<String> {
        HBox hbox = new HBox();
        Pane pane = new Pane();
        Button removeButton = new Button("remove");
        Button startButton = new Button("start");
        Button stopButton = new Button("stop");
        Label lbl = new Label();

        String lastItem;
        ObservableList<String> selectionList = FXCollections.observableArrayList();

        public XCell(ObservableList listA, ObservableList listB, ObservableList listC) throws IOException {
            ListCell thisCell = this;
            lbl.setText(lastItem);
            hbox.getChildren().addAll(lbl, pane, startButton, stopButton, removeButton);
            HBox.setHgrow(pane, Priority.ALWAYS);
            hbox.setSpacing(15);
            hbox.setAlignment(Pos.CENTER_LEFT);

            removeButton.setOnAction(event -> {
                listA.remove(lastItem);
                ArrayToFile.writeToFile(Main.file1, Main.list1);
                ArrayToFile.writeToFile(Main.file2, Main.list2);
                ArrayToFile.writeToFile(Main.file3, Main.list3);
            });
            startButton.setOnAction(event -> {
                Date date = new Date();
                Main.clockEvent.setText("Currently clocked into " + lastItem);
                ClockEvent.startSaver = date.getTime();
            });
            stopButton.setOnAction(event -> {
                Date date = new Date();
                ClockEvent clockEvent = new ClockEvent(lastItem, ClockEvent.startSaver, date.getTime(), date.getTime()-ClockEvent.startSaver, ClockEvent.dateParser(date.getTime()-ClockEvent.startSaver) + " seconds");
                ClockEventList.ClockEvents.add(clockEvent);
                ClockEventList.clockEventParser(ClockEventList.ClockEvents, Main.ClockEventsStrings);
            });

            setOnDragDetected(event -> {

                getListView().getSelectionModel().setSelectionMode(MULTIPLE);
                if (getItem() == null) {
                    return;
                }
                if (listA.isEmpty()){
                    listA.add("Drop here");
                }
                if (listB.isEmpty()){
                    listB.add("Drop here");
                }
                Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();


                //I'll eventually make a custom DataFormat to put the clipboard in for multiple selections.

                selectionList.addAll(getListView().getSelectionModel().getSelectedItems());

                content.putString(getItem());

                dragboard.setDragView((this.snapshot(null, null)));

                dragboard.setContent(content);

                event.consume();
            });

            setOnDragOver(event -> {

                if (event.getGestureSource() != thisCell &&
                        event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.MOVE);

                    event.consume();
                }});



            setOnDragEntered(event -> {
                Dragboard db = event.getDragboard();
                listA.remove(db.getString());
                listB.remove(db.getString());
                listC.remove(db.getString());

                if (db.hasString()) {
                    ObservableList<String> items = getListView().getItems();
                    int thisIdx = items.indexOf(getItem());

                    if (!listA.contains(db.getString())) {
                        if (thisIdx == -1 && listA.size()==0) {
//                            listA.addAll(0, selectionList);
                            listA.add(0, db.getString());
                        }else if (thisIdx == -1){
//                            listA.addAll(listA.size(), selectionList);
                            listA.add(listA.size(), db.getString());
                        }else if(listA.size() == 0){
//                            listA.addAll(0, selectionList);
                            listA.add(0, db.getString());
                        }else
//                            listA.addAll(thisIdx, selectionList);
                            listA.add(thisIdx, db.getString());
                    }

                    if (thisIdx == -1){
                        getListView().getSelectionModel().select(listA.size());
                    }else{
                        getListView().getSelectionModel().select(thisIdx);
                    }
                }
                if (listA.contains("Drop here")){
                    int a = listA.indexOf("Drop here");
                    listA.remove(a);
                }

//                if (listB.contains("Drop here")){
//                    int a = listB.indexOf("Drop here");
//                    listB.remove(a);
//                }
            });

            setOnDragExited(event -> {
//                Dragboard db = event.getDragboard();
//                if (!listA.contains(db.getString())&& event.getDragboard().hasString()) {
//                    listA.add(listA.indexOf(getItem()), db.getString());
//                }
                getListView().getSelectionModel().clearSelection();
                if (listA.isEmpty()){
                    listA.add("Drop here");
                }
                if (listB.isEmpty()){
                    listB.add("Drop here");
                }
                if (listC.isEmpty()){
                    listC.add("Drop here");
                }
            });

            setOnDragDropped(event -> {
//                FileClass.writeToFile(getListView().getItems());

                boolean success = false;
//                Dragboard db = event.getDragboard();
                ArrayToFile.writeToFile(Main.file1, Main.list1);
                ArrayToFile.writeToFile(Main.file2, Main.list2);
                ArrayToFile.writeToFile(Main.file3, Main.list3);
                success = true;
//                if (!list.contains(db.getString())&& event.getDragboard().hasString()) {
//                    list.add(list.indexOf(getItem()), db.getString());
//                }

                event.setDropCompleted(success);

                event.consume();

            });

            setOnDragDone(DragEvent::consume);
        }





        @Override
        protected void updateItem(String item, boolean empty) {
            getListView().getSelectionModel().setSelectionMode(MULTIPLE);
            super.updateItem(item, empty);
            setText(null);  // No text in label of super class

            if(empty) {
                lastItem = null;
                setGraphic(null);

            }else if (item.contains("Drop here")) {
                lastItem = item;
                lbl.setText(item != null ? item : "<null>");
                setGraphic(hbox);
                hbox.setVisible(false);
            }else{
                lastItem = item;
                lbl.setText(item != null ? item : "<null>");
                setGraphic(hbox);
                hbox.setVisible(true);
            }
        }
    }
