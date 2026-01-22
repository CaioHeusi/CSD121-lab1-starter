void main() {  // Main method from my program
    IO.println("College To-Do"); // print program title, instead of hello world

    String[] tasks = new String[3]; // create an array(String) with 3 tasks
    int i = 0; // index counter

    while (i < tasks.length) {  // loop to go until array is finished
        String t = IO.readln("Task " + (i + 1) + ": "); // Read tasks from user while show 1, 2, 3

        if (t == null || t.isBlank()) {  // conditional checking if what user enter is null or blank
            t = "";  // set to an empty string
        }

        tasks[i] = t; // Store actual task in array
        i = i + 1; // increment index to go to next position
    }

    String out = ""; // This string will store the final text

    for (int n = 0; n < tasks.length; n++) { // loop for to go to all array tasks
        out = out + finalText(n + 1, tasks[n]) + "\n"; // Do a numbered line and add it to 'out' var
    }

    IO.println("\nYour tasks:");  // print a "title" before list
    IO.println(out);  // print all tasks

    try {   // try/catch block to handle errors
        Files.writeString(Paths.get("tasks.txt"), out); // write the text in 'out' var to the tasks.txt file
        IO.println("Saved to tasks.txt"); // If something was wrong, will tell to user in terminal
    } catch (Exception e) { // If an error happens, will be handled here
        IO.println("Sorry, could not write file.");  // simple error message
    }
}
/**
 * Formats one task line for printing/saving.  // Explain what function does
 *
 * @param number task number (1..n) // parameter -> number of task
 * @param task the task text // Parameter: text of task
 * @return formatted line like "1) Quiz" // return: formated line
 */
String finalText(int number, String task) { // function that receive parameters and return String
    return number + ") " + task;  // return formatted line -> 1) Quiz
}