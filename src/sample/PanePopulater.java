package sample;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class PanePopulater {
    static public ListView<String> populatePane(BorderPane pane, ObservableList<String> listA, ObservableList listB, ObservableList listC) throws IOException {
        ListView<String> listView = new ListView<>(listA);

        listView.setCellFactory(param -> {
            XCell xcell = null;
            try {
                xcell = new XCell(listA, listB, listC);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return xcell;
        });

        return listView;

    }
}
