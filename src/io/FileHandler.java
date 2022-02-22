package io;

import data.Festival;
import java.io.*;

public class FileHandler {

    public static void saveToFile(Festival data){

        try (FileOutputStream fileOutput = new FileOutputStream("Save")) {
            ObjectOutputStream output = new ObjectOutputStream(fileOutput);
            output.writeObject(data);
            output.close();
            System.out.println("File is saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFromFile(){

        Festival data = null;
        try(FileInputStream fileInput = new FileInputStream("Save")){
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
        System.out.println(data.getArtistList());
        System.out.println(data.getGenreList());
        System.out.println(data.getPodiumList());
        System.out.println(data.getEventList());
    }
}
