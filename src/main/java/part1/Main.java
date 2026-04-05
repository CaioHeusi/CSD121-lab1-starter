package part1;

import part1.logging.*;
import part1.logging.LogLevel;
import part1.util.Messages;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

public class Main {

    static void main(String[] args) {

        try {

            /* === Easy loggers === */
            // TODO: Implementnt AT LEAST one of these
//            Logger logger = new ColorLogger();
//            Logger logger = new InDenialLogger();
//            Logger logger = new LoudLogger(); // i will pick this one

            /* === Medium loggers === */
            // TODO: Implement at least one of these
//            Logger logger = new CensoriousLogger(List.of("hungrish", "drabblex", "clorphed", "snorvish", "grumblet", "flonky", "blarfish"));
//            Logger logger = new MultiLogger(List.of(new ConsoleLogger(), new StreamLogger(new FileOutputStream("log.txt"))));
//            Logger logger = new StreamLogger(System.out);                      // Using System.out as the destination || i will pick this one StreamLogger
//            Logger logger = new StreamLogger(new FileOutputStream("log.txt")); // Using a file as the destination

            /*
            For the final test i used MemoryLogger
            This stores messages in memory first instead of printing them right away
             */
            // TODO: Implement MemoryLogger
            MemoryLogger logger = new MemoryLogger();

            // TODO: (Optional) Implement a JDBC logger
//            Logger logger = new JdbcLogger("jdbc:sqlite:log.db");

            /*
                I changed ConsoleLogger to LoudLogger to see how messages will appear differently
                run the logger 10 times using random log levels and random messages
             */
            // TODO: you can change the first argument here to one of the loggers above,
            //       or to a different constructor call based on the examples above.
            run(logger, 10);

            /*
                Export saved log messages from memory into a file
                MemoryLogger can do this because it implements Exportable too
             */
            // TODO: uncomment this while you are trying out your MemoryLogger
             export(logger, new FileOutputStream("logs.txt"));  // OR try System.out as the second parameter!

        } catch (Exception e) {
            /*
                If something goes wrong while creating the file or exporting,
                print the error message and show the stack trace for debugging
             */
            IO.println("Could not create log file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /*
        This method runs the logging process 'n' times
        It accepts Logger instead of ConsoleLogger so any logger implementation can be used here
     */
    public static void run(Logger logger, int n) { // Changed ConsoleLogger to Logger type to implement interface
        for ( int i = 0; i < n; i++ ) { // repeating logging process 'n' times
            var randomLevel = LogLevel.values()[(int) (Math.random() * LogLevel.values().length)]; // pick a random log level from enum values
            var message = Messages.getRandomMessage(); // get a random message from Messages helper class
            logger.log(message, randomLevel); // send the message and level to whichever logger object was passed in
        }
    }

    // this method exports contest using any object that implements Exportable
    public static void export(Exportable exporter, OutputStream stream) {
        exporter.exportTo(stream); // call the exportTo method od the exporter object and send the output to the given stream
    }
}
