// -------------------------------------------------------------------------

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Main for Graph project (CS3114/CS5040 Fall 2023 Project 4).
 * Usage: java GraphProject <init-hash-size> <command-file>
 *
 * @author Phillip Jordan (alexj14)
 * @author David (Ta-Jung) Lin (davidsmile)
 * @version 2023.11.23
 *
 */

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

public class GraphProject {
    /**
     * @param args
     *            Command line parameters
     */
    public static void main(String[] args) {
        int initsize = Integer.parseInt(args[0]);
        String fname = args[1];
        Scanner sc = null;
        try {
            File fin = new File(fname);
            sc = new Scanner(fin);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        Controller ct = new Controller(initsize);
        while (sc.hasNextLine()) {
            String command = sc.next();
            switch (command) {
                case "insert":
                    String line = sc.nextLine().substring(1);
                    String[] linesplit = line.split("<SEP>", 2);
                    int result = ct.insert(linesplit[0], linesplit[1]);
                    if (result >= 20) {
                        System.out.println("Song hash table size doubled.");
                        result -= 20;
                    }
                    if (result >= 10) {
                        System.out.println("Artist hash table size doubled.");
                    }
                    switch (result % 10) {
                        case 4:
                            System.out.printf(
                                "|%s| duplicates a record already in the "
                                    + "database.\n", line);
                            break;
                        case 3:
                            System.out.printf(
                                "|%s| is added to the Artist database.\n|%s| "
                                    + "is added to the Song database.\n",
                                linesplit[0], linesplit[1]);
                            break;
                        case 1:
                            System.out.printf(
                                "|%s| is added to the Artist database.\n",
                                linesplit[0]);
                            break;
                        case 2:
                            System.out.printf(
                                "|%s| is added to the Song database.\n",
                                linesplit[1]);
                            break;
                    }
                    break;
                case "remove":
                    String arg = sc.next();
                    String txt = sc.nextLine().substring(1);
                    if (arg.equals("song")) {
                        if (ct.removeSong(txt)) {
                            System.out.printf(
                                "|%s| is removed from the Song database.\n",
                                txt);
                        }
                        else {
                            System.out.printf(
                                "|%s| does not exist in the Song database.\n",
                                txt);
                        }
                    }
                    else {
                        if (ct.removeArtist(txt)) {
                            System.out.printf(
                                "|%s| is removed from the Artist database.\n",
                                txt);
                        }
                        else {
                            System.out.printf(
                                "|%s| does not exist in the Artist database.\n",
                                txt);
                        }
                    }
                    break;
                case "print":
                    String argm = sc.nextLine();
                    switch (argm) {
                        case " artist":
                            System.out.println(ct.printArtists());
                            break;
                        case " song":
                            System.out.println(ct.printSongs());
                            break;
                        case " graph":
                            ct.printGraph();
                            break;
                    }
                    break;

            }
        }
    }
}
