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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The class containing the main method.
 *
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.09.24
 */

public class SemSearch {
    /**
     * @param args
     *            Command line parameters
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        int worldSize = Integer.parseInt(args[0]);
        String commandFile = args[1];
        System.out.printf("FileName: %s\nWorld Size: %d\n", commandFile,
            worldSize);
        File infile = new File(commandFile);
        Scanner sc = new Scanner(infile);
        // BinTree implementation
        CommandHandler handler = new CommandHandler();
        String[] command;
        int id;
        String coursename;
        String date;
        int length;
        short x;
        short y;
        int cost;
        String desc;
        while (!sc.hasNextLine()) {
            String[] tags;
            command = sc.nextLine().trim().split("\\s+");
            if (command.length < 2) {
                continue;
            }
            switch (command[0]) {
                case "insert":
                    // insert command implementation
                    break;
                case "print":
                    // print implementation
                    break;
                case "search":
                    // all possible second arguments for search command
                    switch (command[1]) {
                        case "id":
                            handler.search(handler.ID, Integer.parseInt(
                                command[2]));
                            break;
                        case "cost":
                            // cost search implementation
                            break;
                        case "date":
                            handler.search(handler.DATE, command[2],
                                command[3]);
                            break;
                        case "keyword":
                            // keyword search implementation
                            break;
                        case "location":
                            // location search implementation
                            break;
                    }
                    break;
                case "delete":
                    // delete implementation
            }
        }
    }
}
