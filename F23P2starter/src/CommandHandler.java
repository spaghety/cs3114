/**
 * This class handles all of the commands called by the parser
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.10.01
 */
public class CommandHandler {

    private int count; // Visited nodes
    /**
     * The IdBST
     */
    public IdBST idBST;
    /**
     * The CostBST
     */
    public CostBST costBST;
    /**
     * The DateBST
     */
    public DateBST dateBST;
    /**
     * The KeywordBST
     */
    public KeywordBST keywordBST;

    /**
     * Empty constructor
     */
    public CommandHandler() {
        count = 0;
        idBST = null;
        costBST = null;
        dateBST = null;
        keywordBST = null;
// location
    }


    /**
     * Gets current count of nodes
     * 
     * @return current count
     */
    public int getCount() {
        return count;
    }


    /**
     * Resets count of nodes
     */
    public void resetCount() {
        count = 0;
    }


    /**
     * Recursive helper method for inserting into the Id BST
     * 
     * @param rt
     *            root node
     * @param newSem
     *            seminar object being inserted
     * @return the new node object after insertion
     */
    private IdBST insertId(IdBST rt, Seminar newSem) {
        if (rt == null)
            return new IdBST(newSem);
        if (newSem.id() < rt.getId()) {
            rt.setLeft(insertId(rt.getLeft(), newSem));
        }
        else {
            rt.setRight(insertId(rt.getRight(), newSem));
        }
        return rt;
    }


    /**
     * Recursive helper method for inserting into the Cost BST
     * 
     * @param rt
     *            root node
     * @param newSem
     *            seminar object being inserted
     * @return the new node object after insertion
     */
    private CostBST insertCost(CostBST rt, Seminar newSem) {
        if (rt == null)
            return new CostBST(newSem);
        if (newSem.cost() <= rt.getCost()) {
            rt.setLeft(insertCost(rt.getLeft(), newSem));
        }
        else {
            rt.setRight(insertCost(rt.getRight(), newSem));
        }
        return rt;
    }


    /**
     * Recursive helper method for inserting into the IdBST
     * 
     * @param rt
     *            root node
     * @param newSem
     *            seminar object being inserted
     * @return the new node object after insertion
     */
    private DateBST insertDate(DateBST rt, Seminar newSem) {
        if (rt == null)
            return new DateBST(newSem);
        if (newSem.date().compareTo(rt.getDate()) <= 0) {
            rt.setLeft(insertDate(rt.getLeft(), newSem));
        }
        else {
            rt.setRight(insertDate(rt.getRight(), newSem));
        }
        return rt;
    }


    /**
     * Recursive helper method for inserting into the keyword BST
     * 
     * @param rt
     *            root node
     * @param kword
     *            specific keyword to insert with
     * @param newSem
     *            the Seminar object being added
     * @return the new node object after insertion
     */
    private KeywordBST insertKeyword(
        KeywordBST rt,
        String kword,
        Seminar newSem) {
        if (rt == null)
            return new KeywordBST(kword, newSem);
        if (kword.compareTo(rt.getKeyword()) < 0) {
            rt.setLeft(insertKeyword(rt.getLeft(), kword, newSem));
        }
        else if (kword.compareTo(rt.getKeyword()) > 0) {
            rt.setRight(insertKeyword(rt.getRight(), kword, newSem));
        }
        else {
            rt.add(newSem);
        }
        return rt;
    }


    /**
     * Inserts a new Seminar into all BSTs
     * 
     * @param sem
     *            The Seminar object to be inserted
     */
    public void insert(Seminar sem) {
        idBST = insertId(idBST, sem);
        costBST = insertCost(costBST, sem);
        dateBST = insertDate(dateBST, sem);
        String[] kw = sem.keywords();
        for (int i = 0; i < kw.length; i++) {
            keywordBST = insertKeyword(keywordBST, kw[i], sem);
        }
    }


    /**
     * ID search method
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
     * Cost search method
     * 
     * @param root
     *            always takes the public static DateBST when called from
     *            parser but not when called recursively
     * @param low
     *            lower bound of date being searched for
     * @param high
     *            upper bound of date being searched for
     * @return the string to print
     */
    public String searchCost(CostBST root, int low, int high) {
        count++;
        if (root == null) {
            return "";
        }
        if (root.getCost() > high) {
            return searchCost(root.getLeft(), low, high);
        }
        else if (root.getCost() < low) {
            return searchCost(root.getRight(), low, high);
        }
        else {
            String result = searchCost(root.getLeft(), low, high);
            result += root.getSem().toString() + "\n";
            result += searchCost(root.getRight(), low, high);
            return result;
        }
    }


    /**
     * Date search method
     * 
     * @param root
     *            always takes the public static DateBST when called from
     *            parser but not when called recursively
     * @param low
     *            lower bound of date being searched for
     * @param high
     *            upper bound of date being searched for
     * @return the string to print
     */
    public String searchDate(DateBST root, String low, String high) {
        count++;
        if (root == null) {
            return "";
        }
        int lowComp = low.compareTo(root.getDate());
        int highComp = high.compareTo(root.getDate());
        if (lowComp > 0) {
            return searchDate(root.getRight(), low, high);
        }
        else if (highComp < 0) {
            return searchDate(root.getLeft(), low, high);
        }
        else {
            String result = searchDate(root.getLeft(), low, high);
            result += root.getSem() + "\n";
            result += searchDate(root.getRight(), low, high);
            return result;
        }
    }


    /**
     * Keyword search method
     * 
     * @param root
     *            always takes the public static KeywordBST as an argument from
     *            the
     *            parser but is also recursive
     * @param keyword
     *            keyword being searched for
     * @return the string to print
     */
    public String searchKeyword(KeywordBST root, String keyword) {
        if (root == null) {
            return "";
        }
        int strComp = keyword.compareTo(root.getKeyword());
        if (strComp < 0) {
            return searchKeyword(root.getLeft(), keyword);
        }
        else if (strComp > 0) {
            return searchKeyword(root.getRight(), keyword);
        }
        else {
            return root.printSems();
        }
    }
}
