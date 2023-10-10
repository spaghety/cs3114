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
 * @version 2023.10.09
 */

public class SemSearch {
	/**
	 * @param args Command line parameters
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		int worldSize = Integer.parseInt(args[0]);
		String commandFile = args[1];
		File infile = new File(commandFile);
		Scanner sc = new Scanner(infile);
		// BinTree implementation
		CommandHandler h2 = new CommandHandler();
		String[] command;
		CoordBTree locBT = new CoordBTree(worldSize);
		while (sc.hasNextLine()) {
			command = sc.nextLine().trim().split("\\s+");
			if (command.length < 2) // Invalid command
				continue;
			switch (command[0].toLowerCase()) {
			case "insert":// Execute to insert new seminar
				int id = Integer.parseInt(command[1]);
				String courseName = sc.nextLine();
				String date = sc.next();
				int length = sc.nextInt();
				short x = (short) sc.nextInt();
				short y = (short) sc.nextInt();
				// Bad x, y coordinates
				if (x < 0 || y < 0 || x >= worldSize || y >= worldSize) {
					System.out.printf("Insert FAILED - Bad x, y coordinates" + ": %d, %d\n", x, y);
					break;
				}
				int cost = sc.nextInt();
				sc.nextLine();
				String[] tags = sc.nextLine().trim().split("\\s+");
				String desc = sc.nextLine().trim();
				if (h2.searchId(id) == null) {
					Seminar sem = new Seminar(id, courseName, date, length, x, y, cost, tags, desc);
					try {
// }
						h2.insert(sem);
						locBT.insert(sem);
						System.out.printf("Successfully inserted record with ID %d\n" + sem.toString() + "\n", id);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					System.out.printf("Insert FAILED - There is already a " + "record with ID %d\n", id);
				}
				break;
			case "print":
				switch (command[1].toLowerCase()) {
				case "location":
					System.out.println("Location Tree:");
					System.out.print(locBT.toString());
					break;
				default:
					System.out.print(h2.print(command[1]));
				}
				break;
			case "search":
				// all possible second arguments for search command
				switch (command[1].toLowerCase()) {
				case "id":
					int num = Integer.parseInt(command[2]);

					Seminar result = h2.searchId(num);
					if (result == null) {
						System.out.printf("Search FAILED -- There is no record with " + "ID %d\n", num);
					} else {
						System.out.printf("Found record with ID %d:\n", num);
						System.out.println(result.toString());
					}
					break;
				case "cost":
					int low = Integer.parseInt(command[2]);
					int high = Integer.parseInt(command[3]);
					String costResult = h2.searchCost(low, high);
					System.out.printf("Seminars with costs in range %d to %d:\n" + costResult, low, high);
					break;
				case "date":
					String l = command[2];
					String h = command[3];
					String dateResult = h2.searchDate(l, h);
					System.out.printf("Seminars with dates in range %s to %s:\n" + dateResult, l, h);
					break;
				case "keyword":
					System.out.printf("Seminars matching keyword %s:\n", command[2]);
					System.out.print(h2.searchKeyword(command[2]));
					break;
				case "location":
					// pardon the variable names since we're running out
					int radius = Integer.parseInt(command[4]);
					int ex = Integer.parseInt(command[2]);
					int why = Integer.parseInt(command[3]);
					System.out.printf("Seminars within %d units of %d, %d:\n", radius, ex, why);
					System.out.print(locBT.search(ex, why, radius));
					break;
				}
				break;
			case "delete":
				int did = Integer.parseInt(command[1]);
				Seminar temp = h2.searchId(did);
				if (temp == null) {
					System.out.printf("Delete FAILED -- There is no record " + "with ID %d\n", did);
					break;
				}
				h2.delete(temp, did);
				locBT.remove(temp.x(), temp.y(), did);
				System.out.printf("Record with ID %d successfully deleted " + "from the database\n", did);
				break;
			}
		}
		sc.close();

	}
}
