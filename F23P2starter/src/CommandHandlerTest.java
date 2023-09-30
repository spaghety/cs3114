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
        assertNull(handler.idBST);
        assertNull(handler.costBST);
        assertNull(handler.dateBST);
        assertNull(handler.keywordBST);
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

        assertEquals(s1, handler.idBST.getSem());
        assertEquals(s4, handler.idBST.getLeft().getSem());
        assertEquals(s5, handler.idBST.getLeft().getRight().getSem());
        assertEquals(s2, handler.idBST.getRight().getSem());
        assertEquals(s3, handler.idBST.getRight().getRight().getSem());

        assertEquals(s1, handler.costBST.getSem());
        assertEquals(s2, handler.costBST.getRight().getSem());
        assertEquals(s3, handler.costBST.getLeft().getSem());
        assertEquals(s4, handler.costBST.getLeft().getLeft().getSem());
        assertEquals(s5, handler.costBST.getRight().getRight().getSem());

        assertEquals(s1, handler.dateBST.getSem());
        assertEquals(s2, handler.dateBST.getRight().getSem());
        assertEquals(s3, handler.dateBST.getRight().getRight().getSem());
        assertEquals(s4, handler.dateBST.getLeft().getSem());
        assertEquals(s5, handler.dateBST.getLeft().getRight().getSem());
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
     * Tests searchCost(CostBST, low, high)
     */
    public void testSearch2() {
        String[] tags = new String[] { "tag1", "tag2", "tag3" };
        Seminar s1 = new Seminar(5, "test", "0309251600", 13, (short)4,
            (short)9, 15, tags, "test description");
        handler.insert(s1);
        Seminar s2 = new Seminar(6, "test", "0309261600", 13, (short)4,
            (short)9, 21, tags, "test description");
        handler.insert(s2);
        Seminar s3 = new Seminar(5, "test", "0309251600", 13, (short)4,
            (short)9, 5, tags, "test description");
        handler.insert(s3);
        assertTrue((s1.toString()+"\n").equals(handler.searchCost(handler.costBST,10 , 20)));
        //System.out.println(handler.searchCost(handler.costBST, 10, 20));
    }


    /**
     * Tests searchDate(DateBST, low, high)
     */
    public void testSearch3() {
        String[] tags = new String[] { "tag1", "tag2", "tag3" };
        Seminar s1 = new Seminar(5, "test", "0312251600", 13, (short)4,
            (short)9, 15, tags, "test description"); //high date
        handler.insert(s1);
        Seminar s2 = new Seminar(6, "test", "0309282000", 13, (short)4,
            (short)9, 21, tags, "test description"); //middle date
        handler.insert(s2);
        Seminar s3 = new Seminar(5, "test", "0309250500", 13, (short)4,
            (short)9, 5, tags, "test description"); //low date
        handler.insert(s3);
        //System.out.println(handler.searchDate(handler.dateBST, "0", "1"));
    }


    /**
     * Tests searchKeyword
     */
    public void testSearchKeyword() {
        String[] tags = new String[] { "tag1", "tag2", "tag3" };
        Seminar s1 = new Seminar(5, "test", "0312251600", 13, (short)4,
            (short)9, 15, tags, "test description"); //high date
        handler.insert(s1);
        String[] tags1 = new String[] { "tag4", "tag5", "tag6" };
        Seminar s2 = new Seminar(6, "test", "0309282000", 13, (short)4,
            (short)9, 21, tags1, "test description"); //middle date
        handler.insert(s2);
        String[] tags2 = new String[] { "tag1", "tag10", "tag3" };
        Seminar s3 = new Seminar(7, "test", "0309250500", 13, (short)4,
            (short)9, 5, tags2, "test description"); //low date
        handler.insert(s3);
        System.out.println(handler.searchKeyword(handler.keywordBST, "tag10"));
    }
}
