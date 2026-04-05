package part1.logging; // this class belongs to this package

import java.time.Instant;

public class LoudLogger implements Logger { // LoudLogger receive Logger behavior

    @Override // required because Logger defines log() as an abstract method
    public void log(String message, LogLevel level) { // mandatory class because it is the interface method
        /*
            change the message to upper case to make it look louder
            also replace '.' with '!!!'
         */
        message = message.toUpperCase().replace(".", "!!!"); // formatting string to let it upper case and replacing "." for "!!!"
        System.out.println(formatMessage(message, level)); // use shared format from Logger interface and print the result to the console
    }
}