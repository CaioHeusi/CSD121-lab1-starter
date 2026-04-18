package core; // this package contains the core data types of the program

public class StudyEntry { // this class represents one saved study note (IntelliJ is recommending use record, but I will let as I did)
    private final String topic; // store the note topic
    private final String notes; // store the note text

    public StudyEntry(String topic, String notes) { // create new study entry with a topic and some notes
        this.topic = topic; // save given topic in this object
        this.notes = notes; // save given notes in this object
    }

    public String getTopic() { // return topic of this note
        return topic; // send the topic back to the caller
    }

    public String getNotes() { // return notes text of this note
        return notes; // send the notes back to the caller
    }

        // convert this object into one line of text that can be saved in the file
    public String toFileLine() {
        var cleanTopic = topic.replace("\t", " ").trim(); // replace tabs in the topic so the file format stay clean
        var cleanNotes = notes.replace("\t", " ").replace("\n", " ").trim(); // replace tabs and line breaks in the notes so everything fits on noe line in the file
        return cleanTopic+ "\t" +cleanNotes; // return the final line using a tab between topic and notes
    }

        // return a readable string version of this object
    @Override
    public String toString() {
        return "Topic: "+ topic +"\nNotes: "+ notes; // format the topic and notes in a way that looks good on screen
    }
}
