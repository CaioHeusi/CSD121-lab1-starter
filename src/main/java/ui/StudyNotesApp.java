package ui; // this package contains user interface classes

import controllers.StudyNotesController; // controller that handles the button actions
import io.StudyFileStore; // file store used to save and load notes
import javafx.application.Application; // JavaFX Application base class
import javafx.geometry.Insets; // Insets to add padding around the layout
import javafx.scene.Scene; // Scene which holds all visible nodes
import javafx.scene.control.Button; // Button control
import javafx.scene.control.Label; // Label control
import javafx.scene.control.TextArea; // TextArea control
import javafx.scene.control.TextField; // TextField control
import javafx.scene.layout.HBox; // HBox to place buttons side by side
import javafx.scene.layout.VBox; // VBox to stack controls vertically
import javafx.stage.Stage; // Stage which is the main window

    // this class is the main JavaFX application class
public class StudyNotesApp extends Application {
    public static void main() { // this main method launches the JavaFX program
        launch(); // start JavaFX application lifecycle
    }

        // JavaFX calls this method after the app is ready to create the window
    @Override
    public void start(Stage stage) {
        var titleLabel = new Label("Study Notes"); // create the main title label
        var topicLabel = new Label("Topics:"); // create a label for the topic field
        var notesLabel = new Label("Notes:"); // create a label for the notes area
        var savedLabel = new Label("Saved Notes:"); // create a label for the saved notes section

        var topicField = new TextField(); // create the text field where the user types the topic
        topicField.setPromptText("JavaFX EventHandler"); // show a small example inside the topic field before the user types

        var notesArea = new TextArea(); // create the text area where the user writes the note details
        notesArea.setPromptText("EventHandler is essential in JavaFX for..."); // small helper message inside notes are before user type
        notesArea.setPrefRowCount(4); // height in rows for the notes area

        var saveButton = new Button("Save Note"); // create the button that saves one note
        var clearAllButton = new Button("Delete All Notes"); // create the button that deletes all saved notes
        var buttonRow = new HBox(10, saveButton, clearAllButton); // put the two buttons side by side with a gap of 10 pixels

        var statusLabel = new Label("Ready."); // create the label that shows status messages

        var savedNotesArea = new TextArea(); // create the label that shows status messages
        savedNotesArea.setEditable(false); // make the saved notes area read only
        savedNotesArea.setPrefRowCount(10); // height in rows for the saved notes area

            // create the root layout and place all controls in vertical order
        var root = new VBox(
                10, // set the spacing between children to 10 pixels
                titleLabel, // add title label
                topicLabel, // add topic label
                topicField, // add topic field
                notesLabel, // add notes label
                notesArea, // add notes area
                buttonRow, // add row of buttons
                statusLabel, // add status label
                savedLabel, // add saved notes label
                savedNotesArea); // add read only area with the saved notes

        root.setPadding(new Insets(15)); // add padding around the inside edges of the root layout

        var store = new StudyFileStore("study-notes.txt"); // create the file store that will use this text file
            // create controller and five it all the objects it needs
        var controller = new StudyNotesController(store, topicField, notesArea, savedNotesArea, statusLabel, saveButton, clearAllButton);

        saveButton.setOnAction(controller); // register the controller to handle save button clicks
        clearAllButton.setOnAction(controller); // register the controller to handle delete all button clicks
        controller.refreshSavedNotes(); // load and show any notes that were already saved before

        var scene = new Scene(root, 500, 500); // create the scene using root layout and a window size fo 500 by 500
        stage.setTitle("Study Notes"); // set window title
        stage.setScene(scene); // put the scene inside the stage
        stage.show(); // show the window on screen
    }
}
