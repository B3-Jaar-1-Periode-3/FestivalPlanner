package io;

import agenda.DrawEventBox;
import data.Festival;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import javax.swing.*;
import java.io.*;

public class FileHandler {

    public static void saveToFile(File location, Festival data){
        try (FileOutputStream fileOutput = new FileOutputStream(location)) { //Creates path for saving
            ObjectOutputStream output = new ObjectOutputStream(fileOutput);
            output.writeObject(data);
            output.close();
            System.out.println("File is saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFromFile(File location){

        Festival data = null;
        try(FileInputStream fileInput = new FileInputStream(location)){ //Selects path for loading
            ObjectInputStream input = new ObjectInputStream(fileInput);
            data = (Festival) input.readObject();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File not found");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Class not found");
        }

        assert data != null;

        Festival.getInstance().clearAll(); //Deletes all current Festival info
        Festival.getInstance().setFestival(data); //Writes read data to Festival
        DrawEventBox.drawAllBoxes();

        //Read and print data into console
        System.out.println(data.getArtistList());
        System.out.println(data.getGenreList());
        System.out.println(data.getPodiumList());
        System.out.println(data.getEventList());
    }

    public static void fileSaver(Window window){
        try {

            FileChooser fileSaver = new FileChooser();
            fileSaver.setTitle("Save File");
            File file = fileSaver.showSaveDialog(window);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void fileOpener(ActionEvent e) {

        final JFileChooser fileOpener = new JFileChooser();
        fileOpener.setDialogTitle("Open File");



    }
}
