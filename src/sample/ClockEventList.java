package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.awt.datatransfer.StringSelection;


public class ClockEventList {
    static ObservableList<ClockEvent> ClockEvents = FXCollections.observableArrayList();


public static ObservableList<String> clockEventParser(ObservableList<ClockEvent> parseFrom, ObservableList<String> parseTo){
    ObservableList<String> strList = FXCollections.observableArrayList();
    if(!Main.ClockEventsStrings.isEmpty()){
        Main.ClockEventsStrings.clear();
    };
    for (ClockEvent clEv : parseFrom){
        parseTo.add(clEv.topic + " session: " + clEv.durationFormatted);
    }
    return strList;
}
}
