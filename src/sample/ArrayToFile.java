package sample;

import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ArrayToFile {
    public static void writeToFile(File file, ObservableList<String> list){
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);

        for (int i = 0; i < list.size(); i++) {
            pw.write(list.get(i));
            pw.write("\n");
        }

        pw.close();
    }
}
