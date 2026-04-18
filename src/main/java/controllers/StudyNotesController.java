package controllers; // package that contains controller classes

import core.StudyEntry; // model class that represent one study note
import io.StudyFileStore; // class that handles file saving and loading
import javafx.event.ActionEvent; // event type used when a button is clicked
import javafx.event.EventHandler; // interface used to react to events
import javafx.scene.control.Button; // button control type
import javafx.scene.control.Label; // label control type
import javafx.scene.control.TextArea; // TextArea control type
import javafx.scene.control.TextField; // TextField control type

import java.io.FileNotFoundException; // exception used when a file cannot be found
import java.io.IOException; // exception used for general input/output problems


    // This controller handles the button click events
public class StudyNotesController implements EventHandler<ActionEvent> {
    private final StudyFileStore store; // store object to save and load notes from the file
    private final TextField topicField; // text field where the user types the topic
    private final TextArea notesArea; // text area where user types the note details
    private final TextArea savedNotesArea; // text area used to show all saved notes
    private final Label statusLabel; // label used to show status messages to the user
    private final Button saveButton; // reference to the save button
    private final Button clearAllButton; // reference to the button that deletes all notes

            // constructor that receives everything this controller needs to work
    public StudyNotesController(StudyFileStore store, TextField topicField, TextArea notesArea, TextArea savedNotesArea,  Label satusLabel, Button saveButton, Button clearAllButton) {
        this.store = store; // save file store into this object
        this.topicField = topicField; // save topic field into this object
        this.notesArea = notesArea; // save notes area into this object
        this.savedNotesArea = savedNotesArea; // save display area into this object
        this.statusLabel = satusLabel; // save status label into this object
        this.saveButton = saveButton; // save the save button reference into this object
        this.clearAllButton = clearAllButton; // save 'delete all' button into this object
    }


        // This method is called automatically by JavaFX when a registered action happens
    @Override
    public void handle(ActionEvent event) {
        var source = event.getSource(); // get object that fired the event

        if (source == saveButton) { // if the save button was clicked
            saveNote(); // save the current note
        } else if (source == clearAllButton) { // otherwise, if delete all was clicked
            deleteAllNotes(); // remove all notes
        }
    }


        // this method reloads the saved notes from the file and show them on screen
    public void refreshSavedNotes() {
        try {
            var entries = store.loadAll(); // load all notes from the file

            if (entries.isEmpty()) { // if there are no saved notes
                savedNotesArea.setText("No saved notes yet."); // show a simple message
                return; // stop here
            }

            var output = new StringBuilder(); // use StringBuilder to build one long text efficiently

            for (var entry : entries) { // go through every saved entry
                output.append(entry); // add formatted entry text
                output.append("\n\n"); // add an empty line between notes to make the display easier to read
            }

            savedNotesArea.setText(output.toString()); // put the final text into the display area

        } catch (FileNotFoundException e) { // if the file does not exist yet
            savedNotesArea.setText("No saved notes yet."); // show to user that there are no saved notes
        }
    }


        // this method will validate input and save one note
    private void saveNote() {
        var topic = topicField.getText().trim(); // get topic text and remove extra spaces from beginning and end
        var notes = notesArea.getText().trim(); // get notes text and remove extra spaces from the beginning and end

        if (topic.isEmpty() || notes.isEmpty()) { // if either field is empty
            statusLabel.setText("Please fill in both fields."); // show an error message
            return; // and stop
        }

        var entry = new StudyEntry(topic, notes); // create a new study entry object with user input

        try {
            store.save(entry); // save new entry in the file
            topicField.clear(); // clear the topic field after saving
            notesArea.clear(); // clear the notes area after saving
            refreshSavedNotes(); // reload the saved notes so the screen shows the new data
            statusLabel.setText("Note saved successfully."); // tell user that the note was saved

        } catch (IOException e) { // if saving fails
            statusLabel.setText("Could not save the note."); // show an error message
        }
    }


        // this method deletes all saved notes from the file
    private void deleteAllNotes() {
        try {
            store.clearAll(); // clear the whole file
            refreshSavedNotes(); // refresh display area so it shows that there are no notes left
            statusLabel.setText("All notes were deleted."); // tell user that everything was deletes

        } catch (IOException e) { // if deleting fails
            statusLabel.setText("Could not delete the notes."); // show an error message
        }
    }
}