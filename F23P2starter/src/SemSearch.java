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
 * @version 2023.10.02
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
        File infile = new File(commandFile);
        Scanner sc = new Scanner(infile);
        // BinTree implementation
        CommandHandler handler = new CommandHandler();
        String[] command;
        IdBST idRoot = null;
        CostBST costRoot = null;
        KeywordBST kwRoot = null;
        DateBST dateRoot = null;
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
            switch (command[0].toLowerCase()) {
                case "insert":// Execute to insert new seminar
                    id = Integer.parseInt(command[1]);
                    courseName = sc.nextLine();
                    date = sc.next();
                    length = sc.nextInt();
                    x = (short)sc.nextInt();
                    y = (short)sc.nextInt();
                    // Bad x, y coordinates
                    if (x < 0 || y < 0 || x >= worldSize || y >= worldSize) {
                        System.out.printf("Insert FAILED - Bad x, y coordinates"
                            + ": %d, %d\n", x, y);
                        break;
                    }
                    cost = sc.nextInt();
                    sc.nextLine();
                    tags = sc.nextLine().trim().split("\\s+");
                    desc = sc.nextLine().trim();
                    if (handler.searchId(idRoot, id) == null) {
                        Seminar sem = new Seminar(id, courseName, date, length,
                            x, y, cost, tags, desc);
                        try {
                            idRoot = handler.insertId(idRoot, sem);
                            costRoot = handler.insertCost(costRoot, sem);
                            dateRoot = handler.insertDate(dateRoot, sem);
                            String[] kw = sem.keywords();
                            for (int i = 0; i < kw.length; i++) {
                                kwRoot = handler.insertKeyword(kwRoot, kw[i],
                                    sem);
                            }
                            System.out.printf(
                                "Successfully inserted record with ID %d\n"
                                    + sem.toString() + "\n", id);
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
                    String treeString = "";
                    switch (command[1].toLowerCase()) {
                        case "date":
                            System.out.println("Date Tree:");
                            treeString = handler.printDate(dateRoot, "");
                            switch (treeString) {
                                case "null":
                                    System.out.print("This tree is empty");
                                    break;
                                default:
                                    System.out.print(treeString);
                                    System.out.printf("\nNumber of records: %d",
                                        handler.getNodeCount());
                            }
                            System.out.println();
                            break;
                        case "keyword":
                            System.out.println("Keyword Tree:");
                            treeString = handler.printKeyword(kwRoot, "");
                            switch (treeString) {
                                case "null\n":
                                    System.out.print("This tree is empty\n");
                                    break;
                                default:
                                    System.out.print(treeString);
                                    System.out.printf("Number of records: %d\n",
                                        handler.getKeywordCount());
                            }
                            break;
                        case "location":
                            System.out.println("Location Tree:");
                            // implementation
                            System.out.println();
                            break;
                        case "cost":
                            System.out.println("Cost Tree:");
                            treeString = handler.printCost(costRoot, "");
                            switch (treeString) {
                                case "null":
                                    System.out.print("This tree is empty");
                                    break;
                                default:
                                    System.out.print(treeString);
                                    System.out.printf("\nNumber of records: %d",
                                        handler.getNodeCount());
                            }
                            System.out.println();
                            break;
                        case "id":
                            System.out.println("ID Tree:");
                            treeString = handler.printID(idRoot, "");
                            switch (treeString) {
                                case "null":
                                    System.out.print("This tree is empty");
                                    break;
                                default:
                                    System.out.print(treeString);
                                    System.out.printf("\nNumber of records: %d",
                                        handler.getNodeCount());
                            }
                            System.out.println();
                            break;
                    }
                    break;
                case "search":
                    // all possible second arguments for search command
                    switch (command[1].toLowerCase()) {
                        case "id":
                            int num = Integer.parseInt(command[2]);
                            Seminar result = handler.searchId(idRoot, num);
                            if (result == null) {
                                System.out.printf(
                                    "Search FAILED -- There is no record with "
                                        + "ID %d\n", num);
                            }
                            else {
                                System.out.printf("Found record with ID %d: \n",
                                    num);
                                System.out.println(result.toString());
                            }
                            handler.resetCount();
                            break;
                        case "cost":
                            int low = Integer.parseInt(command[2]);
                            int high = Integer.parseInt(command[3]);
                            String costResult = handler.searchCost(costRoot,
                                low, high);
                            System.out.printf(
                                "Seminars with costs in range %d to %d:\n"
                                    + costResult, low, high);
                            System.out.printf(
                                "%d nodes visited in this search\n", handler
                                    .getCount());
                            handler.resetCount();
                            break;
                        case "date":
                            String l = command[2];
                            String h = command[3];
                            String dateResult = handler.searchDate(dateRoot, l,
                                h);
                            System.out.printf(
                                "Seminars with dates in range %s to %s:\n"
                                    + dateResult, l, h);
                            System.out.printf(
                                "%d nodes visited in this search\n", handler
                                    .getCount());
                            handler.resetCount();
                            break;
                        case "keyword":
                            System.out.printf("Seminars matching keyword %s:\n",
                                command[2]);
                            System.out.print(handler.searchKeyword(kwRoot, command[2]));
                            handler.resetCount();
                            break;
                        case "location":
                            // location search implementation
                            handler.resetCount();
                            break;
                    }
                    break;
                case "delete":
                    int did = Integer.parseInt(command[1]);
                    Seminar temp = handler.searchId(idRoot, did);
                    if (temp == null) {
                        System.out.printf("Delete FAILED -- There is no record "
                            + "with ID %d\n", did);
                        break;
                    }
                    handler.deleteId(idRoot, did);
                    handler.deleteCost(costRoot, temp.cost(), did);
                    handler.deleteDate(dateRoot, temp.date(), did);
                    String[] kw = temp.keywords();
                    for (int i = 0; i < kw.length; i++) {
                        handler.deleteKeyword(kwRoot, kw[i], did);
                    }
                    System.out.printf("Record with ID %d successfully deleted "
                        + "from the database\n", did);
                    break;
            }
        }
        sc.close();

    }
}
