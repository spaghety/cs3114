
/**
 * This program serves the sole function of reading the command file and passing
 * instructions to the database manager.
 */

/**
 * 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SemParser {
    public SemParser(String fname, HashTable db) throws FileNotFoundException {
        File infile = new File(fname);
        Scanner sc = new Scanner(infile);
        String command;
        int id;
        String courseName;
        String date;
        int length;
        int x;
        int y;
        int cost;
        String desc;
        while (sc.hasNextLine()) {
            String[] tags;
            command = sc.next();
            switch (command) {
                case "insert":
                    id = sc.nextInt();
                    sc.nextLine();
                    courseName = sc.nextLine();
                    date = sc.next();
                    length = sc.nextInt();
                    x = sc.nextInt();
                    y = sc.nextInt();
                    cost = sc.nextInt();
                    sc.nextLine();
                    tags = sc.nextLine().split(" ");
                    desc = sc.nextLine();

                    System.out.printf(
                        "Successfully inserted record with ID %d\n"
                            + "ID: %d, Title: %s\n"
                            + "Date: %s, Length: %d, X: %d, Y: %d, Cost: %d\n"
                            + "Description: %s\n" + "Keywords:", id, id,
                        courseName, date, length, x, y, cost, desc);
                    for (int i = 0; i < tags.length; i++) {
                        if (tags[i] != "") {
                            System.out.printf(" %s", tags[i]);
                            if (tags.length-1 != i) {
                                System.out.print(",");
                            }
                        }
                    }
                    System.out.print("\n");
                    break;
                case "search":
                    id = sc.nextInt();
                    sc.nextLine();
                    break;
                case "print":
                    courseName = sc.nextLine();
                    break;
                case "delete":
                    id = sc.nextInt();
                    sc.nextLine();
                    break;
            }
        }
    }
}
