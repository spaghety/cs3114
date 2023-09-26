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
     * Tests insert
     */
    public void testInsert() {
        String[] tags = new String[] { "tag1", "tag2", "tag3" };
        Seminar s1 = new Seminar(5, "test", "0309251600", 13, (short)4,
            (short)9, 15, tags, "test description");
        Seminar s2 = new Seminar(6, "test", "0309261600", 13, (short)4,
            (short)9, 20, tags, "test description");
        Seminar s3 = new Seminar(7, "test", "0309271600", 13, (short)4,
            (short)9, 10, tags, "test description");
        Seminar s4 = new Seminar(0, "test", "0309201600", 13, (short)4,
            (short)9, 0, tags, "test description");
        Seminar s5 = new Seminar(2, "test", "0309221600", 13, (short)4,
            (short)9, 25, tags, "test description");
        handler.insert(s1);
        handler.insert(s2);
        handler.insert(s3);
        handler.insert(s4);
        handler.insert(s5);
        assertEquals(5, handler.getCount());

        assertEquals(s1, CommandHandler.idBST.getSem());
        assertEquals(s4, CommandHandler.idBST.getLeft().getSem());
        assertEquals(s5, CommandHandler.idBST.getLeft().getRight().getSem());
        assertEquals(s2, CommandHandler.idBST.getRight().getSem());
        assertEquals(s3, CommandHandler.idBST.getRight().getRight().getSem());

        assertEquals(s1, CommandHandler.costBST.getSem());
        assertEquals(s2, CommandHandler.costBST.getRight().getSem());
        assertEquals(s3, CommandHandler.costBST.getLeft().getSem());
        assertEquals(s4, CommandHandler.costBST.getLeft().getLeft().getSem());
        assertEquals(s5, CommandHandler.costBST.getRight().getRight().getSem());

        assertEquals(s1, CommandHandler.dateBST.getSem());
        assertEquals(s2, CommandHandler.dateBST.getRight().getSem());
        assertEquals(s3, CommandHandler.dateBST.getRight().getRight().getSem());
        assertEquals(s4, CommandHandler.dateBST.getLeft().getSem());
        assertEquals(s5, CommandHandler.dateBST.getLeft().getRight().getSem());
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
