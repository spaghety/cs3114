/**
 * This program serves the sole function of reading the command file and passing instructions to the database manager.
 */

/**
 * 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class SemParser {
	public SemParser (String fname, HashTable db) throws FileNotFoundException {
		System.out.println(fname);
		File infile = new File(fname);
		Scanner sc = new Scanner(infile);
		String command;
		int numOfSems;
		String courseName;
		while (sc.hasNextLine()) {
			command = sc.next();
			numOfSems = sc.nextInt();
			for (int i=0;i<numOfSems;i++) {
				courseName = sc.nextLine();
			}
			
		}
	}
}
