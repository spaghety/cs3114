// On my honor:
// - I have not used source code obtained from another current or
// former student, or any other unauthorized source, either
// modified or unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

/**
 * This program serves the sole function of reading the command file and passing
 * instructions to the database manager.
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.09.10
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Parses the input file into Seminar objects
 */
public class SemParser {
    /**
     * Parses the input file and store the data in a Seminar object
     * 
     * @param fname
     *            File name
     * @param db
     *            The hash table to be passed in
     * @throws FileNotFoundException
     */
    public SemParser(String fname, HashTable db) throws FileNotFoundException {
        File infile = new File(fname);
        Scanner sc = new Scanner(infile);
        String command;
        int id;
        String courseName;
        String date;
        int length;
        short x;
        short y;
        int cost;
        String desc;
        while (sc.hasNextLine()) {
            String[] tags;
            command = sc.next();
            switch (command) {
                case "insert": // Execute to insert new seminar
                    id = sc.nextInt();
                    sc.nextLine();
                    courseName = sc.nextLine();
                    date = sc.next();
                    length = sc.nextInt();
                    x = (short)sc.nextInt();
                    y = (short)sc.nextInt();
                    cost = sc.nextInt();
                    sc.nextLine();
                    tags = sc.nextLine().split(" ");
                    desc = sc.nextLine();
                    try {
                        byte[] sem = (new Seminar(id, courseName, date, length,
                            x, y, cost, tags, desc)).serialize();
                        //db.insert(id, sem);
                        System.out.printf(
                            "Successfully inserted record with ID %d\n"
                                + "ID: %d, Title: %s\n"
                                + "Date: %s, Length: %d, X: %d, Y: %d, Cost: %d\n"
                                + "Description: %s\n" + "Keywords:", id, id,
                            courseName, date, length, x, y, cost, desc);
                        for (int i = 0; i < tags.length; i++) {
                            if (!tags[i].equals("")) {
                                System.out.printf(" %s", tags[i]);
                                if (tags.length - 1 != i) {
                                    System.out.print(",");
                                }
                            }
                        }
                        System.out.printf("\nSize: %d\n", sem.length);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "search": // Execute to search for existing seminar
                    id = sc.nextInt();
                    sc.nextLine();
                    break;
                case "print": // Execute to print either hashtable data or
                              // freeblock data
                    courseName = sc.nextLine();
                    db.printout((courseName.equals("hashtable")));
                    break;
                case "delete": // Execute to delete object from the hash table
                    id = sc.nextInt();
                    sc.nextLine();
                    break;
            }
        }
        sc.close();
    }
}
