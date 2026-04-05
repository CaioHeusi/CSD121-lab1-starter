package part1.logging; // package that this class belongs

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MemoryLogger implements Logger, Exportable {
    private final List<String> messages; // this list stores all formatted log messages in memory

    public MemoryLogger() { // constructor for MemoryLogger

        messages = new ArrayList<>(); // Start with an empty list of messages
    }

    @Override // this method implement Logger.log()
    public void log(String message, LogLevel level) {

        messages.add(formatMessage(message, level)); // format the message and save it in the list instead of printing it right away
    }

    @Override // this method implements Exportable.exportTo()
    public void exportTo(OutputStream out) {
        var writer = new PrintWriter(out); // create a writer so we can send text to the output stream

        for (var savedMessages : messages) { // loop to go through each saved message in memory
            writer.println(savedMessages); // write the current saved message to the output stream
        }

        writer.flush(); // force all text to be written immediately
    }
}