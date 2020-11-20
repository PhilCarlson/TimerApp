package sample;

import javafx.collections.ObservableList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileClass {


    public static File createFile(File file) {

//        File sourceFileA = new File(name + ".txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;

}}