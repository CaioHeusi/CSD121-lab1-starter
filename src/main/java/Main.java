import javax.imageio.ImageIO; // Useful class to work with images format (like JPEG, PNG, GIF) reading and writing from streams, turning bytes into images
import javax.swing.*; // Provides a GUI toolkit like window, labels, icons, pop-up messages, it's a quick way to pop up some stuff for user see
import java.awt.*; // Provide graphics and UI support like image objects, colors, and layout
import java.io.IOException; // is a checked exception that is thrown when IO fail reading streams, decoding image
import java.io.InputStream; // A stream of bytes. It's literally how the PNG data come from the internet, like downloading it
import java.net.URI; // Immutable object that represent a URL using URI target, so the request uses a real URI object instead of a raw String
import java.net.http.HttpClient; // standard HTTP client API for Java, '.HttpClient' is object class that can send requests and get responses
import java.net.http.HttpRequest; // It's used to request HTTP objects using immutable builder methods
import java.net.http.HttpResponse; // and it will hold the response of an 'HttpRequest'.


void main() { // the main control of the flow from stream bytes to display handling with errors gracefully

    try { // try/catch block to handle with errors
        var avatarStream = getRandomAvatarStream();
        // avatarStream -> is a variable storing an InputStream reference, basically the download PNG bytes coming from the HTTP response body
        // getRandomAvatarStream() -> instance method that will basically download the PNG bytes for avatar

        showAvatar(avatarStream);
        // showAvatar -> is an instance method call that return void with purpose of display the image in a window
        // The argument passed 'avatarStream' is a reference to an InputStream object

    } catch (IOException | InterruptedException e) { // This is a catch is for handles if the request fails for any circumstance, so we will land here
        // 'e' -> is a variable that has a reference type, because every exception is an object, and 'IOException' object that contains details about what fails (like a message explaining the problems)

        JOptionPane.showMessageDialog( // class with a static method to pop up a dialog box containing a message, returning void to just show de dialog
                // 'showMessageDialog' -> is a static class method called on the class name, not on an object
                null, // null beacause we might fail before creating any JFrame, so Swing will show a centralized dialog
                "Failed to load avatar: " + e.getMessage(), // getMessage() -> it's an instance method that return a String. It is called on the exception object 'e'. It returns a String with error message to user read trying to explain the error
                "Error", JOptionPane.ERROR_MESSAGE); // static int constant because '.showMessageDialog' expects an int message type code with an error, warning
                         // JOptionPane (javax.swing component) -> it's for pop up dialogs, It's useful to tell errors without build an entire custom UI
                        // 'ERROR_MESSAGE' -> ia a static int variable that will represent an error using numeric codes
    }

}


InputStream getRandomAvatarStream() throws IOException, InterruptedException {     // This is the networking part, here will be built the random avatar URL, send request, the return of body streams response
    // getRandomAvatarStream() -> it's for download the random avatar image and return its bytes into a stream
    // throws -> send HTTP and read the body can fail or be interrupted
    //      IOException (java.io component) -> thrown when read and write fail for some stream issue, invalid image
    //      InterruptedException -> If the thread gets interrupted while wait for an HTTP request

    // Pick a random style
    String[] styles = { // This list of strings is just avatar theme styles for program randomly pick one
            "adventurer", "adventurer-neutral", "avataaars", "big-ears", "big-ears-neutral", "big-smile", "bottts", "croodles", "croodles-neutral", "fun-emoji", "icons", "identicon", "initials", "lorelei", "micah", "miniavs", "open-peeps", "personas", "pixel-art", "pixel-art-neutral" };
                        // String[] styles is a refence type because arrays are objects in Java, and they hold references to a Strings

    var style = styles[(int)(Math.random() * styles.length)]; // In this part will be picked randomly index to styles array
        // style -> String that will store the chosen theme name from array reference
        // Math.random() -> Math is a utility class that contains methods to perform numeric operations and '.random()' is a static class method. This is try to generate a random double (primitive) number between 0.0 and < 1.0
        // styles.length -> styles is the array reference and '.length' (primitive int) is an instance variable field on the array object

    // Generate a random seed
    var seed = (int)(Math.random() * 10000);
    // seed is a variable to store integers (primitive) that will store a random int number to be the seed for avatar

    // Create an HTTP request for a random avatar
    var uri = URI.create("https://api.dicebear.com/9.x/%s/png?seed=%d".formatted(style, seed)); // Here is turned a formatted URL text into a URI object
    // uri -> variable that will be a reference for the URI object
    // URI (java.net component) -> Class that provide methods for build URI from text, used as request target
    // '.create' -> Is a static class method that will return the new URI
    // '.formatted' -> is an instance method on String, used to format strings. The arguments were the style from randomly picked seed

    var request = HttpRequest.newBuilder(uri).build(); // This is a builder, start creating a builder then build an immutable request object
    // request -> is an HttpRequest, it stores the target URI with request settings built by builder
    // HttpRequest (java.net.http component) -> the request sent
    // '.newBuilder(uri)' -> is a static class method to create an HTTP request for a given URI
    // '.build()' -> it's a instance method on the builder that will finalize the request returning a new HttpRequest

    // Send the request
    try (var client = HttpClient.newHttpClient()) {
            // client -> a variable to store the network operation
            // HttpClient (java.net.http component) -> send HTTP requests
            // '.newHttpClient()' -> is a static class method to create a default HttpClient that return a new HttpClient
        var response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
        // response -> variable to store the request sent and the response got
        // 'client.send' -> is an instance method that belongs to this specific client instance returning this response
        // HttpResponse (java.net.http component) -> is the response from an HttpRequest.
        // '.BodyHandlers' -> is a static class member nested used like a namespace. It will group body handlers
        // '.ofInputStream()' -> static class method that will return a handler object asking Java to give the response body as InputStream

        return response.body();
        // response.body -> is an instance method that returns the response body as an InputStream
    }
}

void showAvatar(InputStream imageStream) { // This is the UI part, where will be created window, decode the PNG stream into a image, and show it using JLabel
    // imageStream is parameter and InputStream the reference to the PNG bytes for Image.IOread
    // this method return void because it just displays a window

    JFrame frame = new JFrame("PNG Viewer");
    // JFrame (javax.swing component) -> It holds components and controls window behavior
    // frame -> variable that will be the window object to be configured
    // 'new JFrame("PNG Viewer")' -> constructor call to create a new JFrame object with a new window title

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // '.setDefaultCloseOperation()' -> instance method on frame that decide what happens when the user closes the window, return void
    // JFrame.EXIT_ON_CLOSE -> is a static int (primitive) class variable to close app window when exit

    frame.setResizable(false);
    // '.setResizable()' -> is a instance method with a bool (primitive) decision to allow or disallow resizing, return void

    frame.setSize(200, 200);
    // '.setSize()' -> instance method to set width and height, return void
    // arguments passed '200' are primitive int

    frame.getContentPane().setBackground(Color.BLACK);
    // '.getContentPane()' -> is and instance method on frame to get the container that holds components inside the window, returns the container
    // '.setBackgroud()' -> is an instance method in this container to set the background color, return void
    // color (java.awt component) -> represent a color object
    // '.BLACK' -> is the static class variable that stores color data, in this case, black is the shared color object

    try {
        // Load the PNG image
        Image image = ImageIO.read(imageStream);
        // Image (java.awt component) -> Image data that will be drawn on screen
        // image -> variable to store Image reference
        // ImageIO (javax.imageio component) -> class containing static methods for read and write images. In this code is being used to read 'InputStream' data to decode PNG bytes
        // '.read' -> static class method on ImageIO to decode image bytes from a stream into an image object, return the value 'Image'
        // this looks like the decode step, if it has some problem with the stream, it will fail here

        // Create a JLabel to display the image
        JLabel imageLabel = new JLabel(new ImageIcon(image)); // these are real constructors because they use the 'new' keyword
        // ImageIcon (swing component) -> wraps an image to swing components display it
        // JLabel (swing component) -> It is a component to show text or an icon, in this case, to show the avatar
        // new ImageIcon(image) -> constructor creates an ImageIcon object to wrap image for swing components
        // new JLabel(icon) -> constructor creates a JLabel object that can display the avatar
        // imageLabel -> variable that JLabel type and image as argument to store the label to display the image

        frame.add(imageLabel, BorderLayout.CENTER);
        // BorderLayout (java.awt component) ->  layout manager like '.CENTER' for placing components
        // '.add' -> is an instance method on frame to attach a component to the frame layout
        // '.CENTER' -> is a static class variable that represent a layout region, because it is represented by objects used as keys, like CENTER, NORTH, SOUTH

    } catch (IOException e) {
        // 'e' -> variable that receive IOException type to catches reading or decoding errors

        JOptionPane.showMessageDialog(frame,
        // '.showMessageDialog' -> static class method to show dialog messages, return void
                // frame -> JFrame is used as parent so dialog will be positioned relative to the window

                "Failed to load image: " + e.getMessage(), // 'e.getMessage()' -> is a instance method to get readable messages, return String
                "Error", JOptionPane.ERROR_MESSAGE); // 'JOptionPane.ERROR_MESSAGE)' -> static class variable int, because swing expects an int message type code
                                                    // ERROR_MESSAGE -> primitive int code that tell swing the error
    }

    frame.setVisible(true);
    // '.setVisible' -> is an instance method on frame to show the window on screen, return void
}   // 'true' is a primitive bool



// that was a long leaning journey