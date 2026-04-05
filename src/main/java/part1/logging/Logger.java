package part1.logging; // it needs to be inside same package because it represents the default contract of loggers

import java.time.Instant; // instant is used to generate the timestamp in formatted message

/*
    main abstract method of the interface
    every logger class must implement it in its own way
 */
public interface Logger { // creating Logger interface to set log behavior
    void log(String message, LogLevel level); // log method that every class should carry || abstract method

    /*
        default method builds the standard log format used by all loggers type
        it does not depend on instance variables, so it makes sense to place it in the interface
     */
    default String formatMessage(String message, LogLevel level) {
        return Instant.now().toString() + " [" + level + "] " + message; // return message in formate <timestamp> [<level>] <message>
    }
}