package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public class FileToArray {

    public static ObservableList<String> arrayMaker(File file) throws IOException {
        ObservableList<String> list = FXCollections.observableArrayList();
        BufferedReader bufReader = new BufferedReader(new FileReader(file.getAbsolutePath()));
        String line;
        line = bufReader.readLine();

        try {
            while (line != null) {
                list.add(line);
                line = bufReader.readLine();
            }
        }catch (IOException f) {
            f.printStackTrace();
        }

        bufReader.close();
        return list;
    }
}
