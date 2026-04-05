### 1.12. Use the terms “subtype”, “polymorphism” and “dynamic method dispatch” to explain why Main.run works correctly for ANY of your Logger implementations.

ConsoleLogger, LoudLogger, StreamLogger, and MemoryLogger are *subtypes* of Logger, because they implement Logger interface

This allows Main.run to accept Logger instead of one specific class

That is *polymorphism*, because the same method can work with different concrete Logger objects through one common type

When run calls Logger.log(), Java uses *dynamic method dispatch* to decide which log() method to run based on real object that was passed in

### 1.13. Why can the Logger.formatMessage method have a default implementation? Why must the Logger.log method be abstract?

Logger.formatMessage can have default implementation because the logic is shared by all logger types

and it does not depend on instance variables from a specific class

It only uses the method parameters and returns a formatted string

Logger.log must stay abstract because each logger does something different with the formatted message,

some print to the console, some write to a stream, and MemoryLogger stores the messages in a List<String>

### 1.14.3. Among all the Loggers you have implemented, ONLY MemoryLogger objects can be passed to the Main.export method.  Why?  Why MUST the declaration for the MemoryLogger use ‘MemoryLogger’ instead of just ‘Logger’ as its type?  (Try changing the declaration line to ‘Logger’ and see what happens.)
Only MemoryLogger can be passed to Main.export because it expects an Exportable object

Between my logger classes, MemoryLogger is the only that I implement Exportable interface

If I declare the variable as Logger, variable type will guarantee the log() method, not exportTo()

So, I must declare it as MemoryLogger if I want to use as both Logger and Exportable objects