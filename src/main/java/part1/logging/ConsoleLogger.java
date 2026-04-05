package part1.logging; // package that this class belongs

import java.time.Instant;

public class ConsoleLogger implements Logger { // now this class is a subtype of Logger

    @Override // notation to shows that is implementing an interface method || not mandatory, but good habits
    public void log(String message, LogLevel level) { // because this method is inside a subtype class, this method need to be implemented
        /*
            formats the message using the default method from Logger interface,
            then print the final result to the console
         */
        System.out.println(formatMessage(message, level));
    }
}