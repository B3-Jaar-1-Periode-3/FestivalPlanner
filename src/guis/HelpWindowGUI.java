package guis;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.Serializable;

public class HelpWindowGUI extends Stage implements Serializable {


        public HelpWindowGUI() {

            BorderPane mainpane = new BorderPane();
            TextArea help = new TextArea();



            help.setText("Creating a new event:  \n" +
                    "Go to Create –> Event \n" +
                    "Select an artist from the list or add an artist if the name is not on the list. \nSelect one of the four podiums. And select a genre. \nUse the slider to select the expected popularity for the event. \n" +
                    "Fill in the Begin Time and End Time like this: \n" +
                    "Begin Time: 9:00\tEnd Time: 11:00 \n" +
                    "\n" +
                    "Not like this: \n" +
                    "Begin Time: 09.00\tEnd Time: 11.00 \n" +
                    "\n" +
                    "\n" +
                    "Add a new artist: \n" +
                    "Go to Create –> Artist \n" +
                    "Enter the artist into the field and press ‘save’ to add the artist to the list. \n" +
                    "\n" +
                    "\n" +
                    "Add a new Genre: \n" +
                    "Go to Create –> Genre \n" +
                    "Enter the genre into the field and press ‘save’ to add the genre to the list. \n" +
                    "\n" +
                    "\n" +
                    "Edit or remove an event: \n" +
                    "Go to Edit –> Event \n" +
                    "Select the event and select edit to change the name. \nSelect an event and click remove to delete the event from the list. \n" +
                    "\n" +
                    "\n" +
                    "Edit or remove an artist: \n" +
                    "Go to Edit –> Artist \n" +
                    "Select the artist and select edit to change the name. \nSelect an artist and click remove to delete the artist from the list. \n" +
                    "\n" +
                    "\n" +
                    "Edit or remove a genre: \n" +
                    "Go to Edit –> Genre \n" +
                    "Select the genre and select edit to change the genre. \nSelect a genre and click remove to delete the genre from the list. ");


            help.setEditable(false);

            mainpane.setLeft(help);
            mainpane.setMinSize(500, 500);

            Scene scene = new Scene(mainpane);


            setTitle("Help");
            setScene(scene);

        }
}
