# Dev Journal

Following lab instructions, first thing here is change Player from record to abstract.
I need to change this because in this lab I will create AI that will inherit player actions.

The method getNextMove() works good with one type of player, but not to inheritance. So I will comment it for now...

How lab want at least 2 types of players (basic and advanced), what they have in common? name and token.
I mean, they need to have a name and positioning a token, also need to know chose next move.

Player should hold what is common
Player need to be abstract
getNextMove() may be abstract
each subtype implements their own strategy

Now Player is abstract class instead of record. Now he is not just an object, he is the base of the family.
It means, that I don't want to do new Player(), I want to cast new HumanPlayer(), new Optimus(), new Linus(), etc.
Player will be a general idea, abstract, and not a concrete player

Name and token should keep existing, because they are common to every player, even in NPC's.
What I'm trying to say is what is common to players, Linus, Optimus, etc.? name and token
This tell's me that name and token should be on superclass

        This is interesting, because it is the ideia of generalization, like move common details to an broader abstraction

I will create a normal constructor for subclasses call this constructor using 'super(name, token)'

Also, I will do 2 methods (name() and token()) returning them, because before record was producing that,
but now Player is a normal class and I need to pass these methods manually

getNextMove() should be abstract too, because every player needs to have this method, but each one implements it in a different way
I mean, human ask input in console, Linus get first empty cell, Omola looks one move ahead...
So it's make sense that superclass say that every subclass needs to have this method, but don't tell how it works

        This is interesting, because it is exatcly the role of an abstract method.
        Lecture 5 tells that an abstract method don't have implementation, forcing to subclass to provide it.

Ok, now I'm experiencing the error from Question 2 after i update getNextMove(). Let's break it
In Console.java, I can see an error coming from a Player instance,
In the description have this message ''Player' is abstract; cannot be instantiated'
This tells me that I made a right step, but now Player can't be instanced directly anymore
I Think like this is exactly what I expect from an abstract class

***REFLECTION***

Player can't be record anymore because he needs to work as a superclass of many type of players,
holding just what is common and keeping the specific behave for the superclasses

getNextMove() should be in Player abstract class because the rest of the game works with variables from Player type,
It's tells me that compiles need to see those methods in general type.

***END REFLECTION***

new Player(input, whichPlayer); works before because was a concrete type,
and now is an abstract class, so it cannot be instanced directly. It exists to be the base for other classes.

So now, I will create the concrete type for human player, HumanPlayer.java

Concrete type is a class that can be instanced directly using 'new',
different from abstract that cannot be instanced directly, it exists to be the base for other classes, just to fix.

HumanPlayer is also a Player, this is inheritance essence

Inside it, I will call superclass constructor, because name and token belongs to every player common part,
they are initialized in Player, not in HumanPlayer

I moved getNextMove() function from Player.java to HumanPlayer.java to implement the abstract method that superclass required
I mean, before every player in Player need to have getNextMove(),
now HumanPlayer pick which move he wants just typing in console

But now let's fix that problem in Console.java, changing Player to HumanPlayer, also import HumanPlayer
This solves my problem, because now when user type a normal name, and the program will not try to create a generic Player,
he will create a concrete object HumanPlayer. This make all sense because a human player is a specific type of player

promptForPlayer() method now can return a HumanPlayer, this is allowed because HumanPlayer is a subtype of Player.

Why can a HumanPlayer be returned when the return type is Player? Because a subtype object can be treated as supertype object

No more errors, now I think I finish lab instructions to prepare my workspace, it's time to start work in my computer players

For the first computer player I will pick a basic one, Linus, because he is simpler

***REFLECTION BEFORE CODE***

I will do the Linus compute player.

I already have an inheritance
*     Player = general type;
*     HumanPlayer = human player;
  I need to prove that inheritance really works, creating a new subtype with another behavior

The idea here is the game keeps calling getNextMove(board), but each type of player responds in different ways (the beginning of polymorphisms)

Ok but now, how Linus think? Looks dumb but previsible, because he always picks next empty cell, in this order:
1.     top left
2.     top middle
3.     top right
4.     middle left
5.     middle middle
6.     middle right
7.     bottom left
8.     bottom middle
9.     bottom right

This tells me that he is reading the board like read a text, from left to right, from top to bottom.

I will do this because look simpler, previsible, easy to test and easy to explain

Before I start to code, I will analyze a little bit the code

One important thing to note is that Board already made a good work so far,
the idea of this method 'board.getEmptyCells()' is give back the empty cells at the board.

As he already gives back those positions in normal reader order, Linus just need to ask for the first empty position

***END OF REFLECTION***

I create the Linus.java file and extends it from Player.
It's tells me that Linus inherits from Player, so Linus also has name() and token(),
also is needed to implement getNextMove()

He receives the general structure from the superclass and I just need to give the specific behavior

I do a constructor with a fixed name "Linus", because it will ensure that every object from this type has the name "Linus", passing also token to superclass.

This is a requirement from lab(3.2), that's asking every compute player has a fixed named corresponding to their type,
it just means that Linus can be called just be their name, and no other.

Now I will do a @Override notation as IntelliJ suggested, to call getNextMove() method, and determine his next move.
As Linus is dumb, he will pick next empty cell, so he will return board.getEmptyCells().get(0);

This works because Linus don't think too much, he just follows one rule

Ok, so far I already have 2 different type of 'Players':
HumanPlayer → Chose next move asking input to user
Linus → Chose next move automatically at first empty cell

Both has the exactly same method getNextMove(Board board), and this is very important.
Now the rest of the game don't need to know how each one chose, just need to know that every Player know how chose.

As I was coding Linus, it was appearing some red underlines, but IntelliJ already fixed it, it was just because I forget to do imports.

Now I want to see Linus working, and tell program to create a Linus when user ask for a computer player

I have Linus ready now, but the game doesn't know that it exists, I still can't play...

It was created but not connected yet, let's do it!

Let's update Console.promptForPlayer() to accept Linus, not just HumanPlayer, until now he just knows HumanPlayer,
this means that if I type 'Linus' or '@Linus' he will treat as a human name, because the code don't know how to interpret this command yet

The behavior that I want is if user type a normal name, like 'Caio', he will create a HumanPlayer
But if user type '@Linus', he may create a Linus

    Personally I think this is a very interesting part of the subject because shows a bealtiful thing from design:
    The method keeps returning Player, but now he can return objects from different types:
    1 - HumanPlayer
    2 - Linus
    This works because they are subclasses from Player

It means that the method don't need to change. It is the idea of using a more general type, and more specific types in real objects

First I will update the helpMessage, for when user type and invalid command, to tell how to use correctly

input will read what user enter with .trim() to remove extra spaces

A small conditional check to verify if starts with "@", @ is the signal that user want a computer player
with @ → computer player
without @ → human player

to remove '@', I will a substring(1).trim().toLowerCase();

substring(1) because it will pick to remove the first character, that means @linus now is just linus
trim() to remove extra spaces
toLowerCase() will keep everything in lowercase

This means that @linus, @Linus, @LINUS, everything will work

inside switch, the program decides which computer player create,
for now I just have Linus

So, if the input was "linus" → will create 'new Linus(whichPlayer)', otherwise will show helpMessage

If the input didn't start with '@', will be treated as human name, like Caio, Vinicius, Santiago, so it will create a HumanPlayer

    This method show the difference between:
    Player -> return type of the method
    HumanPlayer, Linus -> Actual type of returned item

IT'S WORKIGN!!!

***REFLECTION***

The if condition is interesting because show that HumanPlayer and AI's should be treated separately, if it has @ or not.

But more than that, the if condition is inside a method, that returns an object from Player type.

I don't need to change the type, because Player is the base for their subclasses.

This means that the program may recognize, HumanPlayer and Linus as Players.

Generalization and Polymorphism in practice is awesome.

***END REFLECTION***

Now I have ready one basic AI and the human player... I still need to do an advanced AI and the test, long road

It's so exciting see an algorithm running by itself

Before I continue, I need to tell user that @Linus exists and modify a little bit the messages at game start

I just modify the helpMessage, now I need to modify the welcome message that is inside Main.java

I just added two more prints to console telling user how to use the program when its starts

***REFLECTION***

Until now, the game just was accepting humans, now accept humans and compute players (just Linus until now)

But one important thing here, that I didn't change the game logic, I just change how user will choose the type of Player

Main is like the brain

Console decides which object creates

Player and subclasses the behavior

This tells me that, Main don't need to know how human ou Linus play

***END REFLECTION***

I tested playing against Linus, and I win, obviously.
Programs runs nicely accepted my name and Linus was a bad player, previsible like always

Second test Linus vs Linus runs nicely too

Quick reflection is that even both players are AI's, the game still call the same method 'getNextMove(board)',
I mean, this is polymorphism, HumanPlayer and Linus calling the same method, because it can come from different subclasses

I was thinking to do the AI first and then test it, but I change my mind and I want o @test it now

***REFLECTION BEFORE IMPLEMENTING TESTS***

I need to say to myself again and again, testing is one of the most important things at OPP.

Because the class don't need to look good, the class need to proof that works correctly.

About Linus, what I want to proof that he works?
When empty table, he will pick the first pos (1, 1)
He respects the reader order, like left to right, top to bottom
Because of the reader order, he just jumps the occupied cells
Constructor defines name and token correctly?

If I give a certain board to Linus what position he will pick?

What the game is?
create the player, that is picked at start
create the board, at game start
call getNextMove(board), to make a move
compare with expected position

***END REFLECTION***

I will create LinusTest.java inside test folder to hold junit tests code

To start I will make some important imports, like junit api tests to tell which methods are tests

I also will import asserEquals, that will help me to compare expected value with real value

test 1, I will test about the first pick, like if the board is empty, he should pick the first position which is rt, cl

test 2, this type of board should be read like a sequency of 9 cells, in reading order
this means that the first position in "XX.O....." is row top, col right

Test 3, if Linus is X, in "XOXOXO..." the first 6 cells are already occupied, now the first empty is row bottom, col left
He will pick the real next empty cell? Or not?

Test 4, I will test 2 important things
1 - If the name was defined correctly
2 - If the token passed by constructor was right

***REFLECTION***

My tests are not testing just if the code runs, my tests are going furthermore, I'm testing the logic of Linus

Linus rule is just pick next empty cell in reading order

So I think my test already cover some parts of this

***END REFLECTION***

My test 3 was producing an error, because I write "XOXOXO..." while I was trying to compare with "XOXOX...." using rb, cl
So I identified the error and already corrected it, now everything is working nicely

Now I need to pick one of the advanced compute players to implement

I will go for Omola

***REFLECTION***

Omola means: One MOve Look-Ahead, he looks one move ahead, like predicting.

Omola logic is:
If he can win now, he wins
but, if he can't win now, but the opponent could win at next move, he blocks
If no of those both situations happens, he picks any empty cell

This will be hard... I mean, he is smarter than Linus, but I think shouldn't be hard to test

One thing that I was thinking is Omola need to predict next move but can not touch on the board to test possibilities.
What I should do?

Omola should first pick an empty position and test it, simulating this move in a copy of the board to check results

I think in that way because Omola need to test his possibilities and opponent possibilities before make his move,
and without make changes at board

So, I will work with 'new Board(board)'

before implementing Omola, I need to make some changes

Omola needs to know the opponent, like if he is X, opponent is O, and vice versa

***END REFLECTION***

That means that I need to edit a little Token.java

I made a nice conditional that I learned in last class X ? O : X;

It means that if actual token is X, will return O, else return X

This will allow me to write token().opponent(); in the future

***REFLECTION***

THe superclass defines the common interface, each subclass provides their own implementation of getNextMove();

The programs keeps using the general class, but at the runtime, Java will call the most specific override

Abstract classes hold common behavior which subclasses can overwrite methods with the same name

Omola thinks like:
If I can win, I will do
If I can't win now, but the opponent can win at next move, I block
If nothing that happens, I will pick the first empty cell

Ok, but the important thing that I need to think here is,
he can't test moves at the table, he needs to do a copy of the board and simulate those moves there

***END REFLECTION***

To start, I will create a new file in player Omola.java and extends to be a subclass from Player

To start I will do a constructor, same as Linus, to fix a name, receive a token in constructor using super

Now I will work with getNextMove(), first I will create a variable to store empty cells,
this will help me to understand what moves left that is possible to be due, I don't need to see occupied cells

To that I will build the first for loop to check if Omola can win at next move,
he needs to think like, if I play this, I win?

So I will do a variable to store a copy from the board and positioning the token there,
now with a conditional I can check each empty cell if he can win

This works because for each empty cell of the board, will do a new board, play the token in this copy, and ask who wins.
If the answer was Omola, that means that is the best option to be played now, and should return this position

To block the opponent win, first I will create a variable to store opponent token

The thing here is ask: if the opponent pick this cell at next move, he could win?

so I will implement a loop that will go through every position in every empty cell,
create a new board of actual board to test opponent chance to win, to don't affect the original table,
then I simulate a play from the opponent on that position, this is the same as 'table-copy[pos] = opponent'

But I made a complex if implementation on both for loops, that I will break it here with very calm

if (boardCopy.getWinner().filter(winner -> winner == opponent).isPresent()) { } -> If the winner exists, and it is the opponent

boardCopy.getWinner() will return Optional<Player>, this means that if someone wins, Optional<Player>, else, Optional.empty()

Optional[X]
Optional[O]
Optional.empty

.filter(winner -> winner == opponent), filter works like if true, keeps the value, if false, change to Optional.empty()

winner -> winner == opponent: lambda. Means if the winner is the opponent

.isPresent(): verify if the has value inside
return true if exists, and false if not

return pos; to show what position opponent could immediately win, and now Omola can block it

For each empty cell
copy the table
simulate the opponent move in this cell
if this move make the opponent win
return this cell

to finish, if Omola can't win or don't need to block, just get next empty cell with emptyCells.get(0)

***REFLECTION***

Interesting thing here is that how my subclasses have different implementations but same method,
which is exactly the main point about overriding and polymorphism

***END REFLECTION***

Now I have Omola ready, I need to introduce it into the game to use with @Omola

To do that I will do quick changes in Console.java, first I will import Omola

I will do a quick change in helpMessage and add Omola

I need also to add an Omola case to switch

Now I reach an interesting point here, method still return Player, but he can give HumanPlayer, Linus and Omola.
This is exactly what I expect from inheritance that I'm building

Now I will do a quick change at Main.java to ask user which option he wants, presenting them all

Now Omola should pick winner move, block or just pick next empty cell

test 1, I will force a condition for I win and see if he will block

Caio's turn (X). Enter your move (row column): 33
Caio plays X at Bottom Right
XO.
...
..X

Omola plays O at Middle Middle
XO.
.O.
..X

Crazy, someday the bots will domain the earth. Just kidding.

test 2, I will test a battle between Linus and Omola (so exciting to see that)

Omola plays better than Linus obviously, is like a Pro player and a newbie, but always has draw

test 3, omola vs omola shold work

If it's everything working as should, he blocks, works against human, bot, accept any player match

I will start to write JUnit test for Omola

This will be different because I will not test just order, I will test decisions

I need to prove Omola behavior, that means
if he can win, he will win
if he need to block, he will
if didn't has victory chance of need of block, play at next empty cell

I will also test name and token, obviously

So, I will create OmolaTest.Java in test folder

First test, I will test the winner move. With the table "XX.O.....", Omola is X, he should pick row top, column right,
because this move will make he wins

This proofs that Omola prioritize wins than anything

Second test, if the board is "OO.X.....", Omola is X, he can't win, but opponent yes.
This means that Omola need to play at row top, col right position to block this cell and avoid opponent to win

This proofs that Omola looks ahead his opponent move

Third test, If it has a scenario that can't win or don't need to block like "X....O...",
he will do his plan B, get next empty cell, which this example is row top, col middle

This proofs that his fallback works nicely

Fourth test, to test the name and token

One thing that I need to take care while doing boards, that I already saw at LinusTest, but is good to remember:
One test can fail, not because code is wrong, but because the testing scenario was build wrong.

I need to always confirm who is X, who is O, which cell is empty, really has victory ot threat

With that I think I finish my lab.

### ANSWERS

Now lets answers the questions

1 - Why *can* you change the type of the returned **value** in `promptForPlayer` without changing the return **type** in the function signature?

Why a HumanPlayer can be retuned when the return type is Player?

I'm sure this is about subtype polymorphism...

This happens when values from subtypes can be treated as supertypes values.
Is possible to use more general superclass interface and still getting the subclasses behavior at runtime.

Console.promptForPlayer() return Player, but inside I can return new HumanPlayer()

This is allowed because HumanPlayer is a Player, it is subclass of Player.
So, the HumanPlayer object also can be treated as Player object

2 - Explain why the call to `getNextMove` initially causes an error until you add the abstract method to the `Player` class. Your answer should involve a discussion of static (compile-time) vs dynamic (run-time) types. (HINT: What is the compile-time vs run-time type of the `player` variable in `TicTacToeGame.doNextTurn`?)

Why getNextMove() must be an abstract method in Player and not just in Player subclasses?

Here I think is the difference about static and dynamic type...

Lecture 5 explains that variable static type determine which methods can be called, 
dynamic type determine which implementation really runs, this is dynamic method dispatch.

It is like in TicTacToeGame, the variable used was from Player type. Like;
    var player = whoseTurn;
    var pos = player.getNextMove(board);

The compiler will look to the variable type and think like:
    This variable is from Player type. Player type has this method?

If 'Player' didn't declare getNextMove(), the compiler will not let call this method, even if in runtime the real object was HumanPlayer or Linus or Omola.

So, getNextMove(), need to exist in Player, to compile allow calling

Then, in runtime, Java uses the real type object to pick witch version of method executes. Lecture calls it dynamic method dispatch,
which the most specific override is used at runtime.

3 - Explain in detail how it is possible that neither our main game loop nor our TicTacToeGame class need change at all when adding new Player types to our game.  Your discussion must include an explanation of how the single call to getNextMove in TicTacToeGame.doNextTurn works correctly no matter whose turn it is or which types the players are. Your answer should involve discussion of polymorphism and dynamic method dispatch.

Why it is not necessary to change Main or TicTacToeGame in order to add new Player types to the game?

Yes professor, I understand the benefit of generalization, inheritance and polymorphism, let's break it

Lecture says that generalization shares common details in a broader abstraction, and a subtype polymorphism allow to use this general abstraction while subclasses
provides specific behaves. 

Also has a part that says that subclasses can provide different implementations than methods in supertype, and at runtime the most specific implementation will be used

I mean Main and TicTacToeGame don't need to know details about HumanPlayer, Linus, Omola. They need to just know that Player exist with a name, token and getNextMove(board).

The game just do player.getNextMove(board), and done.

If the real objective is Human player, will use human input.
If the real objective is Linus, will pick first empty cell in reading order
If the real objective is Omola, will try to win, block or pick next empty cell

The code didn't change because of the general abstraction, not because of the concrete details of subclasses.