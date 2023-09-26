import student.TestCase;

/**
 * Tests the command handler
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.09.25
 */
public class CommandHandlerTest extends TestCase {
    private CommandHandler handler;
    private Seminar sem;
    private IdBST root;
    private Seminar sem2;
    private Seminar sem3;
    private IdBST left;
    private IdBST right;

    /**
     * Sets up the tests
     */
    public void setUp() {
        String[] tags = new String[] { "tag1", "tag2", "tag3" };
        handler = new CommandHandler();
        sem = new Seminar(0, "test", "Jan132002", 13, (short)4, (short)9, 15,
            tags, "test description");
        root = new IdBST(sem);
        sem2 = new Seminar(1, "second test name", "Jun222010", 90, (short)2,
            (short)13, 42, tags, "test description two");
        left = new IdBST(sem2);
        sem3 = new Seminar(1, "second test name", "Jun222010", 90, (short)2,
            (short)13, 42, tags, "test description two");
        right = new IdBST(sem3);
    }


    /**
     * Tests the constructor
     */
    public void testConstructor() {
        assertNull(CommandHandler.idBST);
        assertNull(CommandHandler.costBST);
        assertNull(CommandHandler.dateBST);
        assertNull(CommandHandler.keywordBST);
    }


    /**
     * Tests search(IdBST, int)
     */
    public void testSearch1() {
        assertNull(handler.searchId(null, 0));
        assertEquals(sem, handler.searchId(root, 0));
        root.setRight(right);
        assertEquals(sem3, handler.searchId(root, 1));
        root.setLeft(left);
        assertEquals(sem2, handler.searchId(root, 1));
    }
    
    /**
     * Tests searchCost
     */
    public void testSearch2() {
        assertNull(handler.searchCost(null, "1", "10"));
    }
    
    /**
     * Tests searchDate
     */
    public void testSearch3() {
        assertNull(handler.searchDate(null, "1", "10"));
    }
    
    /**
     * Tests searchKeyword
     */
    public void testSearchKeyword() {
        assertNull(handler.searchKeyword(null, "key1"));
    }
}
