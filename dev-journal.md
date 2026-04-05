/////////////////////////// PART 1 /////////////////////////////////////////////////

package already have ConsoleLogger, that is a program which will receive a one message and a level,
and then prints it in a timestamp format

Main.run that is the method inside main using ConsoleLogger

***REFLECTION***

Just one thing before i start, i want to make sure that i understand that it's very important to learn

interfaces carry the behavior that many classes can use in the same way

interface methods are public and abstract by default

a class that 'implements' an interface becomes her subtype

use an interface as type allow *polymorphism*(so aesthetic name)

because the code will depend on interface instead of depend on a specific class

I think I need to generalize ConsoleLogger, because it is not the main concept

the main concept is any object that know how to do logs

Which means that concept need to turn to be an interface

What I mean here, is that instead I have a ConsoleLogger,

I will have a logger and one of those possible implementations can be ConsoleLogger

This is like in lecture where shows that classes which implements an interface could be treated equally by interface type

***END OF REFLECTION***

With all that I will do it, create logger interface and log() method

***REFLECTION***

I was looking the lectures and the idea is that interface normally defines behavior

interface methods are by default public and abstract

class which implements interface is mandatory implements their methods too

ok... the normal behavior is String message, LogLevel level, this will go to interface logger

***END OF REFLECTION***

I created a file called Logger.java

the file need to be inside logging package because it represents the default contract of loggers

Logger interface will define the behavior of the logs

log() is the method of every logger should have

It is without body yet, because it is just the interface saying like "hey, if any class wants to be a logger, should know how do this"

has a slide in the lecture that say that interface defines a set of methods that implementers should provide

***REFLECTION***

sometimes classes don't share state, but share behavior

interface -> behavior

abstraction -> what it is

in this situation, instead of using inheritance of classes, I should implement interfaces.

Lecture tells that interfaces are useful when types share the same behavior

but not necessarily data/state in common

***END OF REFLECTION***

I will refactor ConsoleLogger to implement my new Logger interface

now ConsoleLogger is a subtype of Logger

and I add also @override notation because it indicates that method is implementing a method defined at interface

which means that if the signature is wrong, compiler will tell me ;)

***REFLECTION***

Now ConsoleLogger is a concrete class, but is also a Logger

that means the code stop to depend on just one specific implementation

and now depends on whole contract

Now is time to put my hands in Main...

one thing that I notice here is that run() method still have ConsoleLogger, and I need to change it

run is stuck in a specific class, it just receives ConsoleLogger

When the method receives an interface, he can work with ANY of their implementations, since it has the required behavior

It is the advantage of interfaces as type, allowing it treat different implementations in a uniform way,

which is the idea of subtype and polymorphism

***END OF REFLECTION***

Now I will just change the type of the logger to Logger, inside Main.run

***REFLECTION***

I don't need to change the body of run, because it don't need to know which object is,

he just needs to know that received Logger interface, which means that something has log() method

This is exactly the benefits of abstraction through interfaces like shown in lectures,

method become more flexible, less coupled, and any future implementation also works without needing to modify run method

It is still working because ConsoleLogger implements Logger

So, ConsoleLogger can be used where a Logger is expected

This is ConsoleLogger be a subtype of Logger

before the method depend on a concrete implementation (like do specifically an ConsoleLogger)

now it depends on a contract (interface) (like want any object that know log())

Now that run already have Logger,the base of interface are working

next step is proof that it run() works with other class without nearly none changes

Now I will create a new class to also implements Logger

I will pick LoudLogger because it is more simple from Appendix A, and it is easier to explain

the idea here is receive the message,
change it to uppercase
print again

He will continue to be a Logger, but change the way how the message appears

This is like in lectures when says that different classes can implement the same interface and be used as same way from main code

***END OF REFLECTION***

Now I will create a new file and class LoudLogger, and implement Logger interface

also I will add the override notation to show that is an interface method

inside the method i will do a println with the loud part (toUpperCase) also replacing "." for "!!!"

then will print as same it is in ConsoleLogger

***REFLECTION***

Now I have 2 different classes LoudLogger and ConsoleLogger and same public log() behavior

It's make sense because I have an interface representing the behavior in common, even with different implementations

***END OF REFLECTION***

Now I want to make a small test here, to run my LoudLogger, 

I will change run() inside Main to check how it will work

***REFLECTION***

Now, when I run the program, messages appear in LoudLogger way, that is working

This is polymorphism in practice, the same run() method still working because now he receives Logger, not a specific class

The lecture says that this use of interface as type to allow those changes without change the method in the object

At this moment still has some repetitions between ConsoleLogger and LoudLogger, both are build manually the println part

the lab sheet tells to observe that and move this logic to a shared place

This shared place will be the interface Logger, using a default method, as like lecture says also

My loggers repeat the same logic to build final message... what this tells me?

This logic need to be moved to a shared location

lecture also tells that an interface can have a default method when it represents a common behavior,

and it don't depend on specific instance variables of each class

In practice, Logger interface already defines main behavior

now will offer a ready message

That means log() will continue mandatory, and formatMessage() will be a shared behavior that loggers can use

***END OF REFLECTION***

Now I will change my Logger and add a default method that will return the formatted message

also i import time.instant

***REFLECTION***

formatMessage() can be default because it is the same logic for everyone, don't depend on stream

don't depend on files, and any state that needs a concrete implementation

It just receives data by a parameter and return a String

that is why is excellent to be ready in interface

like in lecture, interfaces can have concrete implementation at default methods, when this logic don't depend on internal state

log will keep abstract because each logger do different things with same ready message
    ConsoleLogger - will print in console
    LoudLogger - change message before print
    StreamLogger - will type in stream
    MemoryLogger - will hold in a list

The interface cannot decide a unique implementation of log() that will work with everyone.

this method need to continue being responsible for each concrete class

now I need to refactor both loggers to receive formatMessage method

Logger interface already know how to build the message

concrete classes don't need to repeat it

each class just care about their own specific part

***END OF REFLECTION***

I will change the println message to return formatMessage() method from files ConsoleLogger and LoudLogger

also time.Instant because is part of the string build

***REFLECTION***

Now ConsoleLogger don't need to do everything alone, he asks to interface format it and print the result

and LoudLogger still transforming the message, but instead of build the message, he calls formatMessage()

Each class has a clear responsibility

Logger.formatMessage() - holds pattern

ConsoleLogger - just print

LoudLogger - Change message to print

This let the code more clean and the behavior is shared while specific behavior continues in each implementation

***END OF REFLECTION***

Now, I will test inside Main changing run() method to receive LoudLogger or ConsoleLogger to see the difference

what I can see is that one prints normal, and Loud prints in uppercase with !!!

but both use the same base format

***REFLECTION***

Logger interface done

ConsoleLogger implements Logger done

LoudLogger implements Logger done

run() receiving Logger done

formatMessage() is the base format

now is time to go to the harder part

I will go for StreamLogger because it looks easier

Differently from ConsoleLogger, he doesn't print fix at console

He receives a output stream at constructor to write

This means that he can send logs to System.out and file with log.txt

He will build message and write it at given destination

It means, he changes destination not format

***END OF REFLECTION***

Now I will do the StreamLogger file

I will start the class implementing Logger

inside It, I will do an object that know how to write text

first I will use PrintWriter because it is the cleanest way to write a text in a OutputStream

StreamLogger constructor to receive the destination, making the class flexible to write in a file or console

then I will do inside log() method to it prints with interface default pattern to write at destination

flush() help to force to write at that moment, without that the text can be held temporarily and can get time to appears

to test I will change Main.run to StreamLogger(System.out)

It looks like ConsoleLogger because its output still System.out

Now I will test to a file, using FileOutputStream

all working, nice

***REFLECTION***

ConsoleLogger and StreamLogger looks the same but,

ConsoleLogger will always send to console

StreamLogger will always send to any OutputStream

but both are loggers because they have log()

and this is why interface is so useful

Now I need to implement MemoryLogger, this is very important because

it's a logger and it will be exportable

this class can implement more than just an interface, like lecture says

but differently from other loggers, he doesn't print instantly, he holds inside List<String>

when exportTo() were called, he will write at OutputStream

log() = store message

exportTo() = release messages

***END OF REFLECTION***

I will create MemoryLogger file and class

MemoryLogger class will implement Logger and Exportable

It means that he can be used as Logger, but also Exportable (multiple interfaces)

I will first create logger memory to store it in a List<String>, instead of print directly, it will hold here

then I will call MemoryLogger() constructor to create an empty list

I moved log() to upper side of exportTo

log() method is important because another loggers can print or write in stream

this build the message with formatMessage() and stores it string in the list

inside exportTo() method he creates a PrintWriter with OutputStream received

and I will do a for each loop to run through list and write each message

then flush() to ensure that is everything sent

***REFLECTION***

This logger is nice because show that register don't need to mean show

I can store data and then export

this also helps to understand why log() and exportTo() belongs to different interfaces

Logger interface know how to register message

Exportable interface know how to export content

Not every logger needs to export but MemoryLogger do both

***END OF REFLECTION***

Now I will test MemoryLogger, I will uncomment what professor let there

run needs to be modified to receive logger now

and export will receive logger and ia can use what professor left here that is FileOutputStream or test with just System.out

Nothing appears because MemoryLogger didn't print, he creates a file "log.txt" and this file should have formatted messages

or I can do System.out to print at console

This proofs run(logger, 10) works because it's a Logger

export(logger, Sys.out or FileOutputStream) works because MemoryLogger is also Exportable

***REFLECTION***

MemoryLogger type works nicely but Logger type are given problems to export

The variable was declared as Logger, en logger just have log(), not exportTo()

Even if the real objective behind was MemoryLogger, is the variable that is seeing as Logger

And export() method ask something Exportable

This is an important consequence of interface as type, what you want to do depends on variable type, not just concrete object

The lecture also tells about that idea of depends on abstraction and use interface as type

/////////////////////////////////////////////////////////////
1.14.3 reflection answer
Only MemoryLogger can be passed to Main.export because it expects an Exportable object

Between my logger classes, MemoryLogger is the only that I implement Exportable interface

If I declare the variable as Logger, variable type will guarantee the log() method, not exportTo()

So, I must declare it as MemoryLogger if I want to use as both Logger and Exportable objects
//////////////////////////////////////////////////////////

Interface defines behavior

classes with implements an interface turn subtype of them

Use interface as type allows polymorphism

default method make sense when logic don't depend on internal state

One class can implement more than one interface

***END OF REFLECTION***

///////////////////////////////PART 2/////////////////////////////////////////////

***REFLECTION***

In part one the lab was about how to design types using interfaces

now it will be about manipulate collections using streams, method references and lambdas

in the lecture i found some interesting things

one functional interface has just one abstract method

a method reference can replace a simple lambda when she just repasses the call

Stream operations like filter, map, distinct, sorted, limit, forEach and toList() can be chained in one expression

Now analyzing Part 2 Main,

parseCsvLine() method know how transform a line of CSV in an BigMac object, this part is already done

loadData() read file, but use a false lambda that always return the same BigMac

The Structure is ready, but content not

map() needs something that receive String and return BigMac, parceCsvLine() do that

It means that is the perfect place to use method reference

This looks like what lecture says about function references

when a lambda only forwards the argument to a compatible method, it can be replaced by method reference

***END OF REFLECTION***

I will do this correction in loadData replacing return for Main::parseCsvLine

***REFLECTION***

This works because lines is a stream of Strings

So, map() need a function that does String -> BigMac

and parceCsvLine(String line) do that

Is that why Main::parceCsvLine works nicely

This is better than fake lambda because now each line of file will be a real BigMac

Load data will really work

now bigMacs = loadData() will have correct data from CSV

Now he wants me to get the complete list of BigMac and find register corresponding to canada and 2022 year

first I will get every BigMac object

filter by Canada

Filter by year 2022

get first that remains

so i will use stream().filter().filter().findFirst().orElse()

***END OF REFLECTION***

In Main, i will start to build my first TODO which is find BicMac entry for Canada in 2022

I will start with a variable that will have

bigMacs.stream() will transform into a stream to work with that data

first filter i just keep data which name is Canada, what is not is dropped

Gets each bigmac and just keeps if country is Canada

second filter now, what remain from first will just maintain those are 2022

at the end, of both filters, stream are more specified

findFirst() will get first restant result, i'm asking for the first result

and it will give back an Optional<BigMac>, not a BigMac directly

This happens because non results can happen, so Java forces you to deal with this possibility

and orElse(null) i'm telling that to return the final BigMac, if didn't find anything return null, making result simple to print

Lab says that i need to find BigMac entry, which means that i should get the first result found

As professor as to do comments explaining i will do that

***REFLECTION***

It's like first start with all BigMacs, and stay just with Canada, and then 2022, get the first and if you don't have anyone use null

As lab instructions said that i need to find the BigMac entry for Canada in the year of 2022,

and as i can see in the CSV file, just have one, so i suppose that i should have one result

.filter(BigMac -> BigMac.country().equals("Canada") = reduce list
.filter(BigMac -> BigMac.year() == 2022) = reduce list

.findFirst() = find an item

.orElse() = deal with possibilities if it does not have results

In 2.2.2 i need now not just 1 register but all registers that have Canada

The logic is

start a complete list

maintain just Canada objects

give it back in a list

***END OF REFLECTION***

starting with a variable that will store a list that will be a result of a stream

bigMacs.stream() will start processing the list

.filter(bigMac -> bigMac.country().equals("Canada")) maintain just objects that country is Canada (same as last one)

.toList() transform the stream result back to a List<BigMac>

i will also do comments as labs instructions, to be sure that i explain each step and how it works, and how it helps to arrive final value

its working

***REFLECTION***

I notice that before as i just wished one item, i used findFirst().orElse(null)

but now as i want every Canada items, i used .toList()

what i mean is

findFirst() = catch one item

toList() = store every item got

this is de difference between find one result and create a new list

Now in 2.2.3 lab want strings with format <country>: <currency>

There must no duplicates, and the list must be sorted alphabetically

this shows that I don't want to return BigMac objects

What i need to do is transform each object into a String in format

<country>: <currency>

Canada: CAD
Brazil: BRL
Japan: JPY

the logic will be

start with BigMac

transform each BigMac into text

remove repetitions

order in alphabetically order

stores into a list

in lecture has the information that

map - transform each item

distinct - remove repetitions

sorted - order list

***END OF REFLECTION***

i will start with a variable to store complete list of objects from a stream

now i will use cap that will take each item and transform it to another value.

Lecture says that map convert each item into the value produced by a function

for example

a BigMac of Canada will be 'Canada: CAD'
a BigMac of Brazil will be 'Brazil: BRL'

after this map, the stream is not from BigMac anymore, he is a stream of String

.distinct() remove duplicate values, now this is important because has many lines of same country, in different years

without distinct(), I could see like 'Canada: CAD' repetitively times

lectures tells about distinct() exactly about an operation that remove duplicated values

.sorted() order strings alphabetically, now items are strings like 'Brazil: BRL' and 'Canada: CAD', 

and a simple .sorted() already words good

.toList() converts the final result into a list again

lecture also says that toList() are a way to convert stream back to list

countryCurrencies.forEach(System.out::println); - this 'System.out::println' is a method reference

lecture says this pattern in each 'forEach' loop, where the function only forwards the item for printing

i will also add the comments as lab instructions ask for

now i can see working nicely

in alphabetical order and without repetitions

***REFLECTION***

its not just filter, it is also convert data...

like before i had a List<BigMac> and after map, i was looking to a Stream<String>

i think this is the main idea of use map

here i did

map to transform BigMac into a String

distinct to remove repetitions

sorted to order

forEach(IO::println) as method reference

now in 2.2.4 lab want me to print the most recent 5 years of data for Canada

this means

get ONLY Canadian data

order in more recent year to older, because otherwise when i cut in 5, will be not exactly what i expect

get just first 5

prints it

the logic will be

filter

order

limit

print

***END OF REFLECTION***

this time i will not start with variable

i start directly with the bigMacs.stream() to start with all registers

first filter to keep just canada (same as i did)

.sorted((first, second) -> Integer.compare(second.year(), first.year())) very important break it

what i want is more recent to older, this means that the comparison need to be inverted

if it was like Integer.compare(first.year(), second.year()) it was from the lesser to higher, witch is older to recent

but how I did Integer.compare(second.year(), first.year()) the result is in crescent order

.limit(5) its after order, get just 5 firsts

.forEach(IO:println) prints each of 5 registers

***REFLECTION***

the reflection that i want to make here is that the order if things is IMPORTANT!

like I had a problem with the order because at first i tried with first, second, but second was the higher

the sequence I found was

filter by Canada, as same as previous

order by year, that i think that was the difficult part of this part

limit to 5

i tried first to put limit before sorted, but was returning 5 any registers,

but after that i get the right order, i got it

lecture sad that sorted() with a comparator, that is a function that receive 2 values and decides the order between them

now it shows the 5 first register from Canada, from most recent years

stream is a sequence of steps in a logic order

now in 2.2.5 lab wants me to print data for countries with a 2022 BigMac price less than $2 USD

that means that he want 2 conditions, year() == 2022 and usdPrice() < 2.0

the logic will be

first start with a stream of data

filter by year() == 2022

filter by usdPrice() < 2.0

prints

***END OF REFLECTION***

I will start with the stream that is the registers of CSV file

filter to maintain just registers that was from year() == 2022

another filter to store just those who has usdPrice() < 2.0

and a forEach to print each register that remains after my filter

i also will add comments

***REFLECTION***

I separated in 2 filters, i could do in one but is easier to read and explain

first filter by year

then filter by price

i can chain many filters, but each one let more specific set

now in 2.2.6 lab want to me calculate the average USD price of BigMacs in 2022 over all countries

lab is telling me that he want now one number that is the final average of usdPrices() that year() == 2022

the logic will be

start with a stream of data

maintain just those who are year() == 2022

get just the value usdPrice() of each one

calculate the average of those values

This looks like the lab wants to keep following the idea of chain stream operations until get a final result

in lecture has a part that show this way of think, with chained operations and terminals at ends

***END OF REFLECTION***

i will start with a variable to store the result of a stream op to prints it after

i will start with the stream of data

first filter is to maintain just registers from year() == 2022

i will use mapToDouble(BigMac::usdPrice) to get double prices values from usdPrice()

this step will convert each BigMac into a double value

i used method reference here because it looks more as IDE recommended

lecture says that this use of method reference is when lambda just forwards to a compatible method

.average() calculate the average of those numbers

.orElse(0.0) if any reason don't exist a register from 2022, he returns 0.0 instead of breaking

0.0 will not affect in average calculation

***REFLECTION***

before i was working with BigMac objects, Strings, lists

now i'm converting those data into numeric values to perform calculations

the flow is like

Stream<BigMac>

DoubleStream

final average

It's important to mention that mapToDouble().average() was the cleanest and easiest way that i found to explain

Still following the same logic

filter

convert

final operation

***END OF REFLECTION***