
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
 * This program serves the sole function of reading the command file and passing
 * instructions to the database manager.
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.09.12
 */
public class SemParser {
	private MemManager mm;

	/**
	 * Parses the input file and store the data in a Seminar object
	 * 
	 * @param fname  File name
	 * @param db     The hash table to be passed in
	 * @param memMgr The memory manager to be passed in
	 * @throws FileNotFoundException if file is not found
	 */
	public SemParser(String fname, HashTable db, MemManager memMgr) throws FileNotFoundException {
		File infile = new File(fname);
		Scanner sc = new Scanner(infile);
		mm = memMgr;
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
			if (!sc.hasNext()) {
				break;
			}
			command = sc.nextLine().trim().split("\\s+");
			if (command.length < 2) {
				continue;
			}
			switch (command[0]) {
			case "insert": // Execute to insert new seminar
				id = Integer.parseInt(command[1]);
// sc.nextLine();
				courseName = sc.nextLine();
				date = sc.next();
				length = sc.nextInt();
				x = (short) sc.nextInt();
				y = (short) sc.nextInt();
				cost = sc.nextInt();
				sc.nextLine();
				tags = sc.nextLine().trim().split("\\s+");
				desc = sc.nextLine().trim();
				if (db.find(id) == null) {
					try {
						Seminar seminar = new Seminar(id, courseName, date, length, x, y, cost, tags, desc);
						byte[] sem = (seminar).serialize();
						if (db.insert(mm.insert(sem, id))) {
							System.out.printf(
									"Successfully inserted record with ID %d\n" + seminar.toString() + "\nSize: %d\n",
									id, sem.length);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					System.out.printf("Insert FAILED - There is already a record with ID %d\n", id);
				}
				break;
			case "search": // Execute to search for existing seminar
				id = Integer.parseInt(command[1]);
				sc.nextLine();
				SemRecord rec = db.find(id);
				if (rec == null) {
					System.out.printf("Search FAILED -- There is no record with ID %d\n", id);
				} else {
					try {
						System.out.printf("Found record with ID %d:\n", id);
						System.out.println(mm.find(db.find(id)).toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				break;
			case "print": // Execute to print either hashtable data or
							// freeblock data
				courseName = command[1];
				if (courseName.equals("blocks")) {
					System.out.println("Freeblock List:");
					mm.printFreeBlock();
				} else {
					System.out.println("Hashtable:");
					db.printout();
				}
				break;
			case "delete": // Execute to delete object from the hash table
				id = Integer.parseInt(command[1]);
				SemRecord ref = db.remove(id);
				if (ref == null) {
					System.out.printf("Delete FAILED -- There is no record with ID %d\n", id);
				} else {
					mm.remove(ref);
					System.out.printf("Record with ID %d successfully deleted " + "from the database\n", id);
				}
				sc.nextLine();
				break;
			}
		}
	}
}
