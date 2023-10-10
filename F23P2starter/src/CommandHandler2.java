/**
 * This class handles all of the commands called by the parser
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.10.09
 */
public class CommandHandler2 {

    private BSTree idRoot;
    private BSTree costRoot;
    private BSTree kwRoot;
    private BSTree dateRoot;
    private int visitCount;
    private int nodeCount;
    private int keywordCount;

    /**
     * Constructor
     */
    public CommandHandler2() {
        idRoot = null;
        costRoot = null;
        kwRoot = null;
        dateRoot = null;
        visitCount = 0;
        nodeCount = 0;
        keywordCount = 0;
    }


    /**
     * Gets current count of visited nodes
     * 
     * @return current count
     */
    public int getCount() {
        return visitCount;
    }


    /**
     * Gets current count of nodes in ID, Cost, Date BSTs
     * 
     * Important note: This counter only increments when adding to IdBST, but
     * the number of nodes is the same across the 3 BSTs, and the add methods
     * operate simultaneously in the CommandHandler.
     * 
     * @return current count
     */
    public int getNodeCount() {
        return nodeCount;
    }


    /**
     * Gets current count of nodes in KeywordBST
     * 
     * @return current count
     */
    public int getKeywordCount() {
        return keywordCount;
    }


    /**
     * Resets count of visited nodes
     */
    public void resetCount() {
        visitCount = 0;
    }


    /**
     * Insert into each BST
     * 
     * @param sem
     *            The new Seminar object to be inserted
     */
    public void insert(Seminar sem) {
        idRoot = insertBST(idRoot, sem, sem.id(), null);
        // if (idRoot != null)
        nodeCount++;
        costRoot = insertBST(costRoot, sem, sem.cost(), null);
        dateRoot = insertBST(dateRoot, sem, Integer.MIN_VALUE, sem.date());
        for (String key : sem.keywords()) {
            kwRoot = insertBST(kwRoot, sem, Integer.MIN_VALUE, key);
            // if (kwRoot != null)
            keywordCount++;
        }
    }


    /**
     * Recursive helper method for inserting into a BSTree
     * 
     * @param rt
     *            root node
     * @param newSem
     *            seminar object being inserted
     * @param n
     *            compare element
     * @return the new node object after insertion
     */
    public BSTree insertBST(BSTree rt, Seminar newSem, int n, String s) {
        if (rt == null) {
            return new BSTree(newSem, n, s);
        }
        boolean compare = s == null
            ? n <= rt.value()
            : s.compareTo(rt.getString()) <= 0;
        if (compare)
            rt.setLeft(insertBST(rt.getLeft(), newSem, n, s));
        else
            rt.setRight(insertBST(rt.getRight(), newSem, n, s));
        return rt;
    }


    /**
     * ID search method
     * 
     * @param id
     *            id being searched for
     * @return the seminar object being searched for or null if not found
     */
    public Seminar searchId(int id) {
        return searchNumber(idRoot, id);
    }


    /**
     * Single number search method
     * 
     * @param root
     *            always takes the public static IdBST as an argument from the
     *            parser but is also recursive
     * @param id
     *            id being searched for
     * @return the seminar object being searched for or null if not found
     */
    public Seminar searchNumber(BSTree root, int n) {
        if (root == null)
            return null;
        if (root.value() == n)
            return root.getSem();
        else if (n < root.value())
            return searchNumber(root.getLeft(), n);
        else
            return searchNumber(root.getRight(), n);
    }


    /**
     * Cost search method
     * 
     * @param low
     *            lower bound of cost being searched for
     * @param high
     *            upper bound of cost being searched for
     * @return the string to print
     */
    public String searchCost(int low, int high) {
        String result = searchNumRange(costRoot, low, high);
        result += visitCount;
        result += " nodes visited in this search\n";
        resetCount();
        return result;
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
    public String searchNumRange(BSTree root, int low, int high) {
        visitCount++;
        if (root == null)
            return "";
        if (root.value() > high)
            return searchNumRange(root.getLeft(), low, high);
        else if (root.value() < low)
            return searchNumRange(root.getRight(), low, high);
        else {
            String result = searchNumRange(root.getLeft(), low, high);
            result += root.getSem().toString() + "\n";
            result += searchNumRange(root.getRight(), low, high);
            return result;
        }
    }


    /**
     * Date search method
     * 
     * @param low
     *            lower bound of date being searched for
     * @param high
     *            upper bound of date being searched for
     * @return the string to print
     */
    public String searchDate(String low, String high) {
        String result = searchStringRange(dateRoot, low, high);
        result += visitCount;
        result += " nodes visited in this search\n";
        resetCount();
        return result;
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
    public String searchStringRange(BSTree root, String low, String high) {
        visitCount++;
        if (root == null)
            return "";
        int lowComp = low.compareTo(root.getString());
        int highComp = high.compareTo(root.getString());
        if (lowComp > 0)
            return searchStringRange(root.getRight(), low, high);
        else if (highComp < 0)
            return searchStringRange(root.getLeft(), low, high);
        else {
            String result = searchStringRange(root.getLeft(), low, high);
            result += root.getSem().toString() + "\n";
            result += searchStringRange(root.getRight(), low, high);
            return result;
        }
    }


    /**
     * Keyword search method
     * 
     * @param low
     *            lower bound of date being searched for
     * @param high
     *            upper bound of date being searched for
     * @return the string to print
     */
    public String searchKeyword(String key) {
        return searchKeyword(kwRoot, key);
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
    public String searchKeyword(BSTree root, String keyword) {
        if (root == null)
            return "";
        int strComp = keyword.compareTo(root.getString());
        if (strComp < 0)
            return searchKeyword(root.getLeft(), keyword);
        else if (strComp > 0)
            return searchKeyword(root.getRight(), keyword);
        else {
            String result = searchKeyword(root.getLeft(), keyword);
            result += root.getSem().toString() + "\n";
            return result;
        }
    }


    /**
     * Delete from each BST
     * 
     * @param sem
     *            The new Seminar object to be inserted
     */
    public void delete(Seminar sem) {
        int did = sem.id();
        idRoot = deleteBST(idRoot, did, null, did);
        // if (idRoot != null)
        nodeCount--;
        costRoot = deleteBST(costRoot, sem.cost(), null, did);
        dateRoot = deleteBST(dateRoot, Integer.MIN_VALUE, sem.date(), did);
        for (String key : sem.keywords()) {
            kwRoot = deleteBST(kwRoot, Integer.MIN_VALUE, key, did);
            // if (kwRoot != null)
            keywordCount--;
        }
    }


    /**
     * Helper method finds the node with the greatest date
     * 
     * @param rt
     *            node to start search
     * @return node with greatest date
     */
    private BSTree getMaxBST(BSTree rt) {
        if (rt.getRight() == null)
            return rt;
        return getMaxBST(rt.getRight());
    }


    /**
     * Helper method removes the node with the greatest value
     * 
     * @param rt
     *            node to start search
     * @return new BST to replace node
     */
    private BSTree removeMaxBST(BSTree rt) {
        if (rt.getRight() == null)
            return rt.getLeft();
        rt.setRight(removeMaxBST(rt.getRight()));
        return rt;
    }


    /**
     * Delete node from the BSTree
     * 
     * @param rt
     *            node to begin search
     * @param n
     *            Number being searched for
     * @param s
     *            String being searched for
     * @param id
     *            ID used to verify the correct node
     * @return new BST to replace node
     */
    public BSTree deleteBST(BSTree rt, int n, String s, int id) {
        if (rt == null)
            return null;
        boolean compare = s == null
            ? n > rt.value()
            : s.compareTo(rt.getString()) > 0;
        if (compare)
            rt.setRight(deleteBST(rt.getRight(), n, s, id));
        else if (rt.getSem().id() == id) {
            if (rt.getLeft() == null)
                return rt.getRight();
            else {
                BSTree temp = getMaxBST(rt.getLeft());
                rt.setSem(temp.getSem());
                rt.setValue(temp.value());
                rt.setString(temp.getString());
                rt.setLeft(removeMaxBST(rt.getLeft()));
            }
        }
        else
            rt.setLeft(deleteBST(rt.getLeft(), n, s, id));
        return rt;
    }


    /**
     * Prints BST
     * 
     * @param type
     *            BST type
     */
    public String print(String type) {
        String treeString = "";
        String header = "";
        int count = 0;
        switch (type.toLowerCase()) {
            case "id":
                header = "ID Tree:";
                treeString = printBST(idRoot, "");
                count = getNodeCount();
                break;
            case "cost":
                header = "Cost Tree:";
                treeString = printBST(costRoot, "");
                count = getNodeCount();
                break;
            case "date":
                header = "Date Tree:";
                treeString = printBST(dateRoot, "");
                count = getNodeCount();
                break;
            case "keyword":
                header = "Keyword Tree:";
                treeString = printBST(kwRoot, "");
                count = getKeywordCount();
                break;
        }
        switch (treeString) {
            case "null":
                return header + "\nThis tree is empty\n";
            default:
                treeString = header + "\n" + treeString;
                treeString += "\nNumber of records: ";
                treeString += count;
        }
        return treeString + "\n";
    }


    /**
     * Get the BST printout
     * 
     * @param rt
     *            root node
     * @param indent
     *            indent for the next call
     * @return string to print
     */
    public String printBST(BSTree rt, String indent) {
        if (rt == null)
            return indent + "null";
        String result = "";
        String nextIndent = indent + "  ";
        result += printBST(rt.getRight(), nextIndent) + "\n";
        String value = rt.getString() == null
            ? rt.value() + "\n"
            : rt.getString() + "\n";
        result += indent + value;
        result += printBST(rt.getLeft(), nextIndent);
        return result;
    }
}
