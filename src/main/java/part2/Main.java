package part2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

public class Main {


    static void main(String[] args) { // It loads the data from the CSV file and then performs each required stream calculation
        var bigMacs = loadData(); // load all BigMac record from CSV file into a list

        // TODO: Find the BigMac entry for Canada in the year 2022
        var canada2022 = bigMacs.stream() // start with the full list of BigMac data
                .filter(bigMac -> bigMac.country().equals("Canada")) // keep only the records where the country is Canada
                .filter(bigMac -> bigMac.year() == 2022) // from those, keep only the records from the year 2022
                .findFirst() // findFirst() returns the first matching result
                .orElse(null); // orElse(null) gives null if no matching entry exists

        System.out.println("Canada in 2022:"); // print a label to be clear what result is being shown
        IO.println(canada2022); // print the BigMac entry that was found, or null if nothing matched

        // TODO: Create a list containing only the data for Canada
        var canadaOnly = bigMacs.stream() // start with the full list of BigMac records
                .filter(bigMac -> bigMac.country().equals("Canada")) // keep only the records where the country is Canada
                .toList(); // convert the filtered stream back into a list

        System.out.println("All Canada data:"); // print a label to separate lists visually
        canadaOnly.forEach(IO::println); // prints each BigMac object in the list

        // TODO: Create a list containing strings of the format "<country>: <currency>" (e.g. "Canada: CAD")
        //       There must be no duplicates, and the items must be sorted alphabetically
        var countryCurrencies = bigMacs.stream() // start with the full list of BigMac records
                .map(bigMac -> bigMac.country() + ": " + bigMac.currency()) // converts each BigMac object into a String in the format "country: currency"
                .distinct() // removes repeated country: currency pairs
                .sorted() // puts the strings in alphabetical order
                .toList(); // converts the final stream result into a List<String>

        System.out.println("Country and currency list:"); // prints a label before show country and currency list
        countryCurrencies.forEach(IO::println); // prints each string in the list

        // TODO: Print the most recent 5 years of data for Canada
        // first, find the 5 most recent years that appear in Canada's data
        var recentCanadaYears = bigMacs.stream() // start with the full list of BigMac records
                .filter(bigMac -> bigMac.country().equals("Canada")) // keep only the records for Canada
                .map(BigMac::year) // changes each BigMac object into just its year value
                .distinct() // removes repeated years because there may be more than one entry for the same year
                .sorted((first, second) -> second - first) // puts the years from newest to oldest
                .limit(5) // keeps only the 5 most recent years
                .toList(); // stores those 5 years in a list so we can use the min the next stream

        System.out.println("Most recent 5 years of Canada data:"); // label before display data for those years

        bigMacs.stream() // start with the ful list again
                .filter(bigMac -> bigMac.country().equals("Canada")) // keep only record for Canada
                .filter(bigMac -> recentCanadaYears.contains(bigMac.year())) // keep only the records whose year is one of the 5 most recent years found above
                .sorted((first, second) -> second.year() - first.year()) // sort the matching Canada records from the newest year to the oldest year
                .forEach(IO::println); // print each remaining record

//          I'm having a problem with that snippet because its not returning satisfying results I expect
//        bigMacs.stream() // start with the full list of BigMac records
//                .filter(bigMac -> bigMac.country().equals("Canada")) // keep only the rows for Canada
//                .sorted((first, second) -> (second.year() - first.year())) // sort the Canada records by year from newest to oldest
//                .limit(5) // keeps only the 5 most recent entries
//                .forEach(IO::println); // prints each remaining record

        // TODO: Print the data for countries with a 2022 BigMac price less than $2 USD
        System.out.println("2022 entries with USD price under $2:"); // prints a label before show matching entries

        bigMacs.stream() // start with the full list of BigMac records
                .filter(bigMac -> bigMac.year() == 2022) // keep only entries from the year 2022
                .filter(bigMac -> bigMac.usdPrice() < 2.0) // from those, keep only the entries where the USD price is less than 2
                .forEach(IO::println); // print each matching record

        // TODO: Calculate the average USD price of BigMacs in 2022 over all countries
        var averageUsdPrice2022 = bigMacs.stream() // start with full list of BigMac records
                .filter(bigMac -> bigMac.year() == 2022) // keep only the entries from 2022
//                .mapToDouble(BigMac::usdPrice) // mapToDouble gets only the USD price from each matching record
                .mapToDouble(BigMac::usdPrice) // mapToDouble gets only the USD price from each matching record
                .average() // calculates the mean of those prices
                .orElse(0.0); // gives a default value if no matching entries exist

        System.out.println("Average USD price in 2022:"); // prints a label before show the average
        IO.println(averageUsdPrice2022); // print the average USD price
    }

    public static List<BigMac> loadData() { // This method loads the CSV file and converts each line into a BigMac object
        try(var lines = Files.lines(Path.of("BigMacPrices.csv"))) { // open the file as stream of lines

            return lines.map( // map() applies parseCsvLine to each line of the file
                    // TODO: replace this ENTIRE lambda expression with a function reference that parses the line from the file
                    //       (see the parseCsvLine method below)
                    Main::parseCsvLine) // is a method reference that converts one String line into one
                    .toList(); // toList() collects all parsed BigMac objects into a list

        } catch (Exception e) { // If the file cannot be read
            throw new RuntimeException(e); // throw a runtime exception to stop the program
        }
    }

    /**
     * Parse a CSV line into a BigMac record. The expected format is:
     * year,currency,country,localPrice,exchangeRate,usdPrice
     * @param line the CSV line to parse
     * @return a BigMac record containing the data from the CSV line
     */
    public static BigMac parseCsvLine(String line) {
        var values = line.split(","); // split the CSV line by commas and store each value in an array

        /*
            Create and return a new BigMac record using the values from the CSV line
            substring(0,4) is used because the year column may contain a longer date string,
            but we only need the first 4 characters for the year
         */
        return new BigMac(
                Integer.parseInt(values[0].substring(0,4)),
                values[1],
                values[2],
                Double.parseDouble(values[3]),
                Double.parseDouble(values[4]),
                Double.parseDouble(values[5]));
    }
}
