# Answers to Lab Questions

Place here your answers to the reflection questions in the lab instructions.

## Question 1
Why *can* you change the type of the returned **value** in `promptForPlayer` without changing the return **type** in the function signature?
### Answer
This happens when values from subtypes can be treated as supertypes values.
Is possible to use more general superclass interface and still getting the subclasses behavior at runtime.

Console.promptForPlayer() return Player, but inside I can return new HumanPlayer()

This is allowed because HumanPlayer is a Player, it is subclass of Player.
So, the HumanPlayer object also can be treated as Player object

## Question 2
Explain why the call to `getNextMove` initially causes an error until you add the abstract method to the `Player` class. Your answer should involve a discussion of static (compile-time) vs dynamic (run-time) types. (HINT: What is the compile-time vs run-time type of the `player` variable in `TicTacToeGame.doNextTurn`?)
### Answer
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

## Question 3
Explain in detail how it is possible that neither our main game loop nor our TicTacToeGame class need change at all when adding new Player types to our game.  Your discussion must include an explanation of how the single call to getNextMove in TicTacToeGame.doNextTurn works correctly no matter whose turn it is or which types the players are. Your answer should involve discussion of polymorphism and dynamic method dispatch.
### Answer
Lecture says that generalization shares common details in a broader abstraction, and a subtype polymorphism allow to use this general abstraction while subclasses
provides specific behaves.

Also has a part that says that subclasses can provide different implementations than methods in supertype, and at runtime the most specific implementation will be used

I mean Main and TicTacToeGame don't need to know details about HumanPlayer, Linus, Omola. They need to just know that Player exist with a name, token and getNextMove(board).

The game just do player.getNextMove(board), and done.

If the real object is Human player, will use human input.
If the real object is Linus, will pick first empty cell in reading order
If the real object is Omola, will try to win, block or pick next empty cell

The code didn't change because of the general abstraction, not because of the concrete details of subclasses.   