/**
 * This class handles all of the commands called by the parser
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.09.25
 */
public class CommandHandler {

    private int count;
    /**
     * The IdBST
     */
    public static IdBST idBST;
    /**
     * The CostBST
     */
    public static CostBST costBST;
    /**
     * The DateBST
     */
    public static DateBST dateBST;
    /**
     * The KeywordBST
     */
    public static KeywordBST keywordBST;

    /**
     * Empty constructor
     */
    public CommandHandler() {
        idBST = null;
        costBST = null;
        dateBST = null;
        keywordBST = null;
// location
    }


    /**
     * Inserts a new Seminar into all BSTs
     * 
     * @param sem
     *            The Seminar object to be inserted
     */
    public void insert(Seminar sem) {
        if (count <= 0) {
            idBST = new IdBST(sem);
            costBST = new CostBST(sem);
            dateBST = new DateBST(sem);
            keywordBST = new KeywordBST(null, sem);
            count++;
            return;
        }

        if (sem.id() > idBST.getId()) {
            idBST.setRight(new IdBST(sem));
        }
        else {
            idBST.setLeft(new IdBST(sem));
        }

        if (sem.cost() > costBST.getCost()) {
            costBST.setRight(new CostBST(sem));
        }
        else {
            costBST.setLeft(new CostBST(sem));
        }

        if (Integer.parseInt(sem.date()) > Integer.parseInt(dateBST
            .getDate())) {
            dateBST.setRight(new DateBST(sem));
        }
        else {
            dateBST.setLeft(new DateBST(sem));
        }
        
        count++;
    }


    /**
     * ID search overloaded with all other search methods
     * 
     * @param root
     *            always takes the public static IdBST as an argument from the
     *            parser but is also recursive
     * @param id
     *            id being searched for
     * @return the seminar object being searched for or null if not found
     */
    public Seminar searchId(IdBST root, int id) {
        if (root == null) {
            return null;
        }
        if (root.getId() == id) {
            return root.getSem();
        }
        Seminar leftSearch = searchId(root.getLeft(), id);
        if (leftSearch == null) {
            return searchId(root.getRight(), id);
        }
        return leftSearch;
    }


    /**
     * Cost search overloaded with all other search methods
     * 
     * @param root
     *            always takes the public static DateBST when called from
     *            parser but not when called recursively
     * @param low
     *            lower bound of date being searched for
     * @param high
     *            upper bound of date being searched for
     * @return the seminar object found
     */
    public Seminar searchCost(CostBST root, String low, String high) {
        return null; // IMPLEMENT ALONG WITH ALL OTHER OVERLOADED SEARCH
                     // METHODS
    }


    /**
     * Date search overloaded with all other search methods
     * 
     * @param root
     *            always takes the public static DateBST when called from
     *            parser but not when called recursively
     * @param low
     *            lower bound of date being searched for
     * @param high
     *            upper bound of date being searched for
     * @return the seminar object found
     */
    public Seminar searchDate(DateBST root, String low, String high) {
        return null; // IMPLEMENT ALONG WITH ALL OTHER OVERLOADED SEARCH
                     // METHODS
    }


    /**
     * Keyword search overloaded with all other search methods
     * 
     * @param root
     *            always takes the public static KeywordBST as an argument from
     *            the
     *            parser but is also recursive
     * @param id
     *            id being searched for
     * @return the seminar object being searched for or null if not found
     */
    public Seminar searchKeyword(KeywordBST root, String keyword) {
        return null; // IMPLEMENT ALONG WITH ALL OTHER OVERLOADED SEARCH
        // METHODS
    }
}
