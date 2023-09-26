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
        String courseName;
        String date;
        int length;
        short x;
        short y;
        int cost;
        String desc;
        while (sc.hasNextLine()) {
            String[] tags;
            command = sc.nextLine().trim().split("\\s+");
            if (command.length < 2) {
                continue;
            }
            switch (command[0]) {
                case "insert":// Execute to insert new seminar
                    id = Integer.parseInt(command[1]);
                    courseName = sc.nextLine();
                    date = sc.next();
                    length = sc.nextInt();
                    x = (short)sc.nextInt();
                    y = (short)sc.nextInt();
                    cost = sc.nextInt();
                    sc.nextLine();
                    tags = sc.nextLine().trim().split("\\s+");
                    desc = sc.nextLine().trim();
                    if (handler.searchId(CommandHandler.idBST, id) == null) {
                        Seminar sem = new Seminar(id, courseName, date, length,
                            x, y, cost, tags, desc);
                        try {
                            handler.insert(sem);
                            System.out.printf(
                                "Successfully inserted record with ID %d\n"
                                    + sem.toString(), id);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        System.out.printf(
                            "Insert FAILED - There is already a record with"
                                + " ID %d\n", id);
                    }
                    break;
                case "print":
                    // print implementation
                    break;
                case "search":
                    // all possible second arguments for search command
                    switch (command[1]) {
                        case "id":
                            handler.searchId(CommandHandler.idBST, Integer
                                .parseInt(command[2]));
                            break;
                        case "cost":
                            handler.searchCost(CommandHandler.costBST,
                                command[2], command[3]);
                            break;
                        case "date":
                            handler.searchDate(CommandHandler.dateBST,
                                command[2], command[3]);
                            break;
                        case "keyword":
                            handler.searchKeyword(CommandHandler.keywordBST,
                                command[2]);
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
