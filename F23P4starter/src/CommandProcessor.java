import java.io.File;
import java.util.Scanner;

/**
 * The class that processes commands from the main method
 * 
 * @author Phillip Jordan (alexj14)
 * @author David (Ta-Jung) Lin (davidsmile)
 * @version 2023.12.10
 */
public class CommandProcessor {

    /**
     * Empty constructor
     */
    public CommandProcessor() {
        // Empty constructor
    }


    /**
     * Processes commands from the main method
     * 
     * @param args
     */
    public static void processCommand(String[] args) {
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
            String command = sc.next().trim();
            switch (command) {
                case "insert":
                    String line = sc.nextLine().substring(1);
                    String[] linesplit = line.split("<SEP>", 2);
                    String artistName = linesplit[0].trim();
                    String songName = linesplit[1].trim();
                    int result = ct.insert(artistName, songName);
                    boolean songDoubled = false;
                    if (result >= 20) {
                        songDoubled = true;
                        result -= 20;
                    }
                    boolean artistDoubled = false;
                    if (result >= 10) {
                        artistDoubled = true;
                    }
                    switch (result % 10) {
                        case 4:
                            System.out.printf(
                                "|%s| duplicates a record already in the "
                                    + "database.\n", line);
                            break;
                        case 3:
                            if (artistDoubled) {
                                System.out.println(
                                    "Artist hash table size doubled.");
                            }
                            System.out.printf(
                                "|%s| is added to the Artist database.\n",
                                artistName);
                            if (songDoubled) {
                                System.out.println(
                                    "Song hash table size doubled.");
                            }
                            System.out.printf(
                                "|%s| is added to the Song database.\n",
                                songName);
                            break;
                        case 1:
                            if (artistDoubled) {
                                System.out.println(
                                    "Artist hash table size doubled.");
                            }
                            System.out.printf(
                                "|%s| is added to the Artist database.\n",
                                artistName);
                            break;
                        case 2:
                            if (songDoubled) {
                                System.out.println(
                                    "Song hash table size doubled.");
                            }
                            System.out.printf(
                                "|%s| is added to the Song database.\n",
                                songName);
                            break;
                        default:
                            break;
                    }
                    break;
                case "remove":
                    String arg = sc.next();
                    String txt = sc.nextLine().substring(1).trim();
                    System.out.printf("|%s| ", txt);
                    switch (arg) {
                        case "song":
                            if (ct.removeSong(txt)) {
                                System.out.printf(
                                    "is removed from the Song database.\n");
                            }
                            else {
                                System.out.printf(
                                    "does not exist in the Song database.\n");
                            }
                            break;
                        case "artist":
                            if (ct.removeArtist(txt)) {
                                System.out.printf(
                                    "is removed from the Artist database.\n");
                            }
                            else {
                                System.out.printf(
                                    "does not exist in the Artist database.\n");
                            }
                            break;
                        default:
                            break;
                    }
                    break;
                case "print":
                    String argm = sc.nextLine().trim();
                    switch (argm) {
                        case "artist":
                            System.out.println(ct.printArtists());
                            break;
                        case "song":
                            System.out.println(ct.printSongs());
                            break;
                        case "graph":
                            System.out.println(ct.printGraph());
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
    }

}
