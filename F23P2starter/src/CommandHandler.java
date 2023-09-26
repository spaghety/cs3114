/**
 * 
 */

/**
 * Thic class handles all of the commands called by the parser
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.09.24
 */
public class CommandHandler {
    /**
     * javadoc for public field variable
     */
    public static IdBST ID;
    /**
     * javadoc for public field variable
     */
    public static DateBST DATE;
    //  - Store all Binary Search Trees and coordinate Bintree here

    /**
     * Empty constructor
     */
    public CommandHandler() {
        ID = null;
        DATE = null;
    }


    /**
     * ID search overloaded with all other search methods
     * 
     * @param root
     *            always takes the public static ID BST as an argument from the
     *            parser but is also recursive
     * @param id
     *            id being searched for
     * @return the seminar object being searched for or null if not found
     */
    public Seminar search(IdBST root, int id) {
        if (root == null) {
            return null;
        }
        if (root.getId() == id) {
            return root.getSem();
        }
        Seminar leftSearch = search(root.getLeft(), id);
        if (leftSearch == null) {
            return search(root.getRight(), id);
        }
        return leftSearch;
    }


    /**
     * Date search overloaded with all other search methods
     * 
     * @param root
     *            always takes the public static Date BST when called from
     *            parser but not when called recursively
     * @param low
     *            lower bound of date being searched for
     * @param high
     *            upper bound of date being searched for
     * @return the seminar object found
     */
    public Seminar search(DateBST root, String low, String high) {
        return null; //  IMPLEMENT ALONG WITH ALL OTHER OVERLOADED SEARCH
                     // METHODS
    }
}
