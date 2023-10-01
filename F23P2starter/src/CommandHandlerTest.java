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
        Seminar s1 = new Seminar(5, "test", "0309251600", 13, (short)4,
            (short)9, 15, new String[] { "tag1" }, "test description");
        Seminar s2 = new Seminar(6, "test", "0309261600", 13, (short)4,
            (short)9, 20, new String[] { "tag2" }, "test description");
        Seminar s3 = new Seminar(7, "test", "0309271600", 13, (short)4,
            (short)9, 10, new String[] { "tag3" }, "test description");
        Seminar s4 = new Seminar(0, "test", "0309201600", 13, (short)4,
            (short)9, 0, new String[] { "biscuit" }, "test description");
        Seminar s5 = new Seminar(2, "test", "0309221600", 13, (short)4,
            (short)9, 25, new String[] { "tag1", "tag4" }, "test description");
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

        assertFuzzyEquals(s1.toString() + "\n" + s5.toString() + "\n",
            handler.keywordBST.printSems());
        assertFuzzyEquals(s2.toString() + "\n", handler.keywordBST.getRight()
            .printSems());
        assertFuzzyEquals(s3.toString() + "\n", handler.keywordBST.getRight()
            .getRight().printSems());
        assertFuzzyEquals(s4.toString() + "\n", handler.keywordBST.getLeft()
            .printSems());
        assertFuzzyEquals(s5.toString() + "\n", handler.keywordBST.getRight()
            .getRight().getRight().printSems());
    }


    /**
     * Tests searchID(IdBST, int)
     */
    public void testSearchID() {
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
    public void testSearchCost() {
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
        assertEquals((s1.toString() + "\n"), (handler.searchCost(
            handler.costBST, 10, 20)));
        assertEquals((s1.toString() + "\n"), (handler.searchCost(
            handler.costBST, 15, 15)));
        assertEquals((s2.toString() + "\n"), (handler.searchCost(
            handler.costBST, 20, 21)));
        assertEquals((s3.toString() + "\n"), (handler.searchCost(
            handler.costBST, 5, 10)));
        assertEquals((s3.toString() + "\n" + s1.toString() + "\n"), (handler
            .searchCost(handler.costBST, 5, 15)));
        assertEquals((s1.toString() + "\n" + s2.toString() + "\n"), (handler
            .searchCost(handler.costBST, 15, 21)));
        assertEquals((s3.toString() + "\n" + s1.toString() + "\n" + s2
            .toString() + "\n"), (handler.searchCost(handler.costBST, 5, 21)));
    }


    /**
     * Tests searchDate(DateBST, low, high)
     */
    public void testSearchDate() {
        String[] tags = new String[] { "tag1", "tag2", "tag3" };
        Seminar s1 = new Seminar(5, "test", "0312251600", 13, (short)4,
            (short)9, 15, tags, "test description"); // high date
        handler.insert(s1);
        Seminar s2 = new Seminar(6, "test", "0309282000", 13, (short)4,
            (short)9, 21, tags, "test description"); // middle date
        handler.insert(s2);
        Seminar s3 = new Seminar(5, "test", "0309250500", 13, (short)4,
            (short)9, 5, tags, "test description"); // low date
        handler.insert(s3);
        assertEquals("", (handler.searchDate(handler.dateBST, "04", "1")));
        assertFuzzyEquals((s1.toString() + "\n"), (handler.searchDate(
            handler.dateBST, "0312251600", "0312251600")));
        assertFuzzyEquals((s1.toString() + "\n"), (handler.searchDate(
            handler.dateBST, "031", "032")));
        assertFuzzyEquals((s2.toString() + "\n"), (handler.searchDate(
            handler.dateBST, "030928", "030929")));
        assertFuzzyEquals((s3.toString() + "\n"), (handler.searchDate(
            handler.dateBST, "030925", "030927")));
        assertFuzzyEquals((s2.toString() + "\n" + s1.toString() + "\n"),
            (handler.searchDate(handler.dateBST, "030928", "032")));
        assertFuzzyEquals((s3.toString() + "\n" + s2.toString() + "\n"),
            (handler.searchDate(handler.dateBST, "030", "031")));
        assertFuzzyEquals((s3.toString() + "\n" + s2.toString() + "\n" + s1
            .toString() + "\n"), (handler.searchDate(handler.dateBST, "0",
                "1")));
    }


    /**
     * Tests searchKeyword
     */
    public void testSearchKeyword() {
        String[] tags1 = new String[] { "tag1", "tag2", "tag3" };
        Seminar s1 = new Seminar(5, "test", "0312251600", 13, (short)4,
            (short)9, 15, tags1, "test description"); // high date
        handler.insert(s1);
        String[] tags2 = new String[] { "tag4", "tag5", "tag6" };
        Seminar s2 = new Seminar(6, "test", "0309282000", 13, (short)4,
            (short)9, 21, tags2, "test description"); // middle date
        handler.insert(s2);
        String[] tags3 = new String[] { "tag1", "tag10", "tag3" };
        Seminar s3 = new Seminar(7, "test", "0309250500", 13, (short)4,
            (short)9, 5, tags3, "test description"); // low date
        handler.insert(s3);
        System.out.println(handler.searchKeyword(handler.keywordBST, "tag10"));
        assertFuzzyEquals(s1.toString() + "\n" + s3.toString() + "\n", handler
            .searchKeyword(handler.keywordBST, "tag1"));
        assertFuzzyEquals(s1.toString() + "\n", handler.searchKeyword(
            handler.keywordBST, "tag2"));
        assertFuzzyEquals(s1.toString() + "\n" + s3.toString() + "\n", handler
            .searchKeyword(handler.keywordBST, "tag3"));
        assertFuzzyEquals(s2.toString() + "\n", handler.searchKeyword(
            handler.keywordBST, "tag4"));
        assertFuzzyEquals(s2.toString() + "\n", handler.searchKeyword(
            handler.keywordBST, "tag5"));
        assertFuzzyEquals(s2.toString() + "\n", handler.searchKeyword(
            handler.keywordBST, "tag6"));
        assertFuzzyEquals("", handler.searchKeyword(handler.keywordBST,
            "tag7"));
        assertFuzzyEquals(s3.toString() + "\n", handler.searchKeyword(
            handler.keywordBST, "tag10"));

    }
}
