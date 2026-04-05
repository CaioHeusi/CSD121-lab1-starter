package part1.logging; // package that this class belongs

import java.io.OutputStream;
import java.io.PrintWriter;

public class StreamLogger implements Logger { // StreamLogger is another Logger implementation
    private final PrintWriter writer; // holds an object that know how to write text | I prefer PrintWriter because it is a simpler way to write text in a OutputStream

    public StreamLogger(OutputStream out) { // the constructor can receive the destination
        writer = new PrintWriter(out); // here I get raw stream and transform into something easy to use with println
    }

    @Override
    public void log(String message, LogLevel level) {
        writer.println(formatMessage(message, level)); // use standard message format to write at destination

        /*
            force the writer to send the text immediately
            this is important because the output appears right away in the file or console
         */
        writer.flush(); // forces to write | very important because without flush() stored texts sometimes can get time to show in file or console
    }
}