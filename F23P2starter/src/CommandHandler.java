/**
 * This class handles all of the commands called by the parser
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.10.01
 */
public class CommandHandler {

    private int visitCount; // Visited nodes
    private int nodeCount; // Nodes in ID, Cost, Date BSTs
    private int keywordCount; // Nodes in KeywordBST

    /**
     * Empty constructor
     */
    public CommandHandler() {
        visitCount = 0;
        nodeCount = 0;
        keywordCount = 0;
// location
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
     * Recursive helper method for inserting into the Id BST
     * 
     * @param rt
     *            root node
     * @param newSem
     *            seminar object being inserted
     * @return the new node object after insertion
     */
    public IdBST insertId(IdBST rt, Seminar newSem) {
        if (rt == null) {
            nodeCount++;
            return new IdBST(newSem);
        }
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
    public CostBST insertCost(CostBST rt, Seminar newSem) {
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
    public DateBST insertDate(DateBST rt, Seminar newSem) {
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
    public KeywordBST insertKeyword(
        KeywordBST rt,
        String kword,
        Seminar newSem) {
        if (rt == null) {
            keywordCount++;
            return new KeywordBST(kword, newSem);
        }
        if (kword.compareTo(rt.getKeyword()) <= 0) {
            rt.setLeft(insertKeyword(rt.getLeft(), kword, newSem));
        }
        else {
            rt.setRight(insertKeyword(rt.getRight(), kword, newSem));
        }
        return rt;
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
        else if (id < root.getId()) {
            return searchId(root.getLeft(), id);
        }
        else {
            return searchId(root.getRight(), id);
        }
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
        visitCount++;
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
        visitCount++;
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
        visitCount++;
        int strComp = keyword.compareTo(root.getKeyword());
        if (strComp < 0) {
            return searchKeyword(root.getLeft(), keyword);
        }
        else if (strComp > 0) {
            return searchKeyword(root.getRight(), keyword);
        }
        else {
            String result = searchKeyword(root.getLeft(), keyword);
            result += root.printSem() + "\n";
            return result;
        }
    }


    /**
     * Recursive helper node finds the node with the greatest ID
     * 
     * @param rt
     *            node to begin search
     * @return node with the greatest value
     */
    private IdBST getMaxId(IdBST rt) {
        if (rt.getRight() == null)
            return rt;
        return getMaxId(rt.getRight());
    }


    /**
     * Recursive helper node removes the node with the greatest ID
     * 
     * @param rt
     *            node to begin search
     * @return object to replace the current node with
     */
    private IdBST removeMaxId(IdBST rt) {
        if (rt.getRight() == null)
            return rt.getLeft();
        rt.setRight(removeMaxId(rt.getRight()));
        return rt;
    }


    /**
     * This method deletes a node with a specific ID from the ID BST
     * 
     * @param rt
     *            node to begin search
     * @param ID
     *            id being searched for
     * @return the new BST node
     */
    public IdBST deleteId(IdBST rt, int ID) {
        if (rt == null)
            return null;
        if (ID < rt.getId()) {
            rt.setLeft(deleteId(rt.getLeft(), ID));
        }
        else if (ID > rt.getId()) {
            rt.setRight(deleteId(rt.getRight(), ID));
        }
        else {
            if (rt.getLeft() == null)
                return rt.getRight();
            else {
                IdBST temp = getMaxId(rt.getLeft());
                rt.setSem(temp.getSem());
                rt.setLeft(removeMaxId(rt.getLeft()));
            }
        }
        return rt;
    }


    /**
     * Recursive helper node finds the node with the greatest cost
     * 
     * @param rt
     *            node to begin search
     * @return node with the greatest value
     */
    private CostBST getMaxCost(CostBST rt) {
        if (rt.getRight() == null)
            return rt;
        return getMaxCost(rt.getRight());
    }


    /**
     * Recursive helper node removes the node with the greatest cost
     * 
     * @param rt
     *            node to begin search
     * @return object to replace the current node with
     */
    private CostBST removeMaxCost(CostBST rt) {
        if (rt.getRight() == null)
            return rt.getLeft();
        rt.setRight(removeMaxCost(rt.getRight()));
        return rt;
    }


    /**
     * This helper method deletes a node with the same seminar object from the
     * cost BST, using the exact cost to find it faster
     * 
     * @param rt
     *            node to begin search
     * @param cost
     *            cost being searched for
     * @param ID
     *            ID used to verify it's the correct seminar object
     * @return the new BST node
     */
    public CostBST deleteCost(CostBST rt, int cost, int ID) {
        if (rt == null)
            return null;
        if (cost > rt.getCost()) {
            rt.setRight(deleteCost(rt.getRight(), cost, ID));
        }
        else if (ID == rt.getSem().id()) {
            if (rt.getLeft() == null)
                return rt.getRight();
            else {
                CostBST temp = getMaxCost(rt.getLeft());
                rt.setSem(temp.getSem());
                rt.setLeft(removeMaxCost(rt.getLeft()));
            }
        }
        else {
            rt.setLeft(deleteCost(rt.getLeft(), cost, ID));
        }
        return rt;
    }


    /**
     * Helper method finds the node with the greatest date
     * 
     * @param rt
     *            node to s tart search
     * @return node with greatest date
     */
    private DateBST getMaxDate(DateBST rt) {
        if (rt.getRight() == null)
            return rt;
        return getMaxDate(rt.getRight());
    }


    /**
     * Helper method removes the node with the greatest date
     * 
     * @param rt
     * @return
     */
    private DateBST removeMaxDate(DateBST rt) {
        if (rt.getRight() == null)
            return rt.getLeft();
        rt.setRight(removeMaxDate(rt.getRight()));
        return rt;
    }


    /**
     * Delete node from the DateBST tree
     * 
     * @param rt
     *            node to begin search
     * @param date
     *            date used to improve search efficiency
     * @param ID
     *            ID used to verify the correct node
     * @return new BST to replace node
     */
    public DateBST deleteDate(DateBST rt, String date, int ID) {
        if (rt == null)
            return null;
        if (date.compareTo(rt.getDate()) > 0) {
            rt.setRight(deleteDate(rt.getRight(), date, ID));
        }
        else if (rt.getSem().id() == ID) {
            if (rt.getLeft() == null)
                return rt.getRight();
            else {
                DateBST temp = getMaxDate(rt.getLeft());
                rt.setSem(temp.getSem());
                rt.setLeft(removeMaxDate(rt.getLeft()));
            }
        }
        else {
            rt.setLeft(deleteDate(rt.getLeft(), date, ID));
        }
        return rt;
    }


    /**
     * Helper method finds node with greatest keyword value
     * 
     * @param rt
     *            node to begin search
     * @return node with greatest keyword value
     */
    private KeywordBST getMaxKeyword(KeywordBST rt) {
        if (rt.getRight() == null)
            return rt;
        return getMaxKeyword(rt.getRight());
    }


    /**
     * Helper method removes node with greatest keyword value
     * 
     * @param rt
     *            node to begin search
     * @return new node to replace with
     */
    private KeywordBST removeMaxKeyword(KeywordBST rt) {
        if (rt.getRight() == null)
            return rt.getLeft();
        rt.setRight(removeMaxKeyword(rt.getRight()));
        return rt;
    }


    /**
     * Deletes a keyword node from the BST
     * 
     * @param rt
     *            node to begin search
     * @param kw
     *            keyword being searched for
     * @param ID
     *            ID used to verify it's the correct seminar
     * @return new BST to replace child node with
     */
    public KeywordBST deleteKeyword(KeywordBST rt, String kw, int ID) {
        if (rt == null)
            return null;
        if (kw.compareTo(rt.getKeyword()) > 0) {
            rt.setRight(deleteKeyword(rt.getRight(), kw, ID));
        }
        else if (rt.getSem().id() == ID) {
            if (rt.getLeft() == null)
                return rt.getRight();
            else {
                KeywordBST temp = getMaxKeyword(rt.getLeft());
                rt.change(temp.getSem(), temp.getKeyword());
                rt.setLeft(removeMaxKeyword(rt.getLeft()));
            }
        }
        else {
            rt.setLeft(deleteKeyword(rt.getLeft(), kw, ID));
        }
        return rt;
    }


    /**
     * Get the BST printout for the ID tree
     * 
     * @param rt
     *            root node
     * @param indent
     *            indent for the next call
     * @return string to print
     */
    public String printID(IdBST rt, String indent) {
        if (rt == null)
            return indent + "null";
        String result = "";
        String nextIndent = indent + "  ";
        result += printID(rt.getRight(), nextIndent) + "\n";
        result += indent + rt.getId() + "\n";
        result += printID(rt.getLeft(), nextIndent);
        return result;
    }


    /**
     * Get the BST printout for the Date tree
     * 
     * @param rt
     *            root node
     * @param indent
     *            indent for the next call
     * @return string to print
     */
    public String printDate(DateBST rt, String indent) {
        if (rt == null)
            return indent + "null";
        String result = "";
        String nextIndent = indent + "  ";
        result += printDate(rt.getRight(), nextIndent) + "\n";
        result += indent + rt.getDate() + "\n";
        result += printDate(rt.getLeft(), nextIndent);
        return result;
    }


    /**
     * Get the BST printout for the Keyword tree
     * 
     * @param rt
     *            root node
     * @param indent
     *            indent for the next call
     * @return string to print
     */
    public String printKeyword(KeywordBST rt, String indent) {
        if (rt == null)
            return indent + "null\n";
        String result = "";
        String nextIndent = indent + "  ";
        result += printKeyword(rt.getRight(), nextIndent);
        result += indent + rt.getKeyword() + "\n";
        result += printKeyword(rt.getLeft(), nextIndent);
        return result;
    }


    /**
     * Get the BST printout for the cost tree
     * 
     * @param rt
     *            root node
     * @param indent
     *            indent for the next call
     * @return string to print
     */
    public String printCost(CostBST rt, String indent) {
        if (rt == null)
            return indent + "null";
        String result = "";
        String nextIndent = indent + "  ";
        result += printCost(rt.getRight(), nextIndent) + "\n";
        result += indent + rt.getCost() + "\n";
        result += printCost(rt.getLeft(), nextIndent);
        return result;
    }
}
