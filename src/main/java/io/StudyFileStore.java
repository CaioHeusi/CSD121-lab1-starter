package io; // this package contains the file input and output code

import core.StudyEntry; // model class used by this file store

import java.io.File; // File class used to represent a file on disk
import java.io.FileNotFoundException; // exception used when a file cannot be found
import java.io.FileWriter; // FileWriter so the program can write text into a file
import java.io.IOException; // exception used for a file input/output problems
import java.io.PrintWriter; // PrintWriter to make writing lines easier
import java.util.ArrayList; // ArrayList to store many study entries in memory
import java.util.List; // List as the general list type used in the method return value
import java.util.Scanner; // Scanner to read the file line by line

    // This class handles saving and loading study notes from a text file
public class StudyFileStore {
    private final File file; // store the file used by this object

    public StudyFileStore(String fileName) { // create a file store using the given file name
        this.file = new File(fileName); // build a file object from the given name
    }

    public void save(StudyEntry entry) throws IOException { // save one study entry at the end of the file
        var writer = new PrintWriter(new FileWriter(file, true)); // open the file in append mode so new notes are added instead of replacing olds
        writer.println(entry.toFileLine()); // write the entry as one line in the file
        writer.close(); // close writer so data is properly saved
    }

    public void clearAll() throws IOException { // remove everything from the file
        var writer = new PrintWriter(new FileWriter(file, false)); // open the file without append mode so it is cleared
        writer.close(); // close writer right away because opening it like this already empties the file
    }

    public List<StudyEntry> loadAll() throws FileNotFoundException { // load all saved study entries from file
        var entries = new ArrayList<StudyEntry>(); // create an empty list that will gold all loaded entries

        if (!file.exists()) { // if the file does not exist yet
            return entries; // return empty list
        }

        var input = new Scanner(file); // open the file for reading

        while (input.hasNextLine()) { // keep reading while there is another line available
            var line = input.nextLine(); // read one full line from the file
            var parts = line.split("\t", 2); // split the line into at most two parts using the tab as separator

            if (parts.length == 2) { // only create an object if the line has both required parts
                entries.add(new StudyEntry(parts[0], parts[1])); // build a StudyEntry from the topic and notes and add it to the list
            }
        }

        input.close(); // close the scanner after reading is finished
        return entries; // return the list with all leaded entries
    }
}
