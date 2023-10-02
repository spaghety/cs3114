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
    private IdBST idRoot;
    private DateBST dateRoot;
    private CostBST costRoot;
    private KeywordBST kwRoot;
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
        idRoot = null;
        dateRoot = null;
        costRoot = null;
        kwRoot = null;
        sem2 = new Seminar(1, "second test name", "Jun222010", 90, (short)2,
            (short)13, 42, tags, "test description two");
        left = new IdBST(sem2);
        sem3 = new Seminar(1, "second test name", "Jun222010", 90, (short)2,
            (short)13, 42, tags, "test description two");
        right = new IdBST(sem3);
    }


    /**
     * Tests insertId
     */
    public void testInsertId() {
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
        idRoot = handler.insertId(idRoot, s1);
        idRoot = handler.insertId(idRoot, s2);
        idRoot = handler.insertId(idRoot, s3);
        idRoot = handler.insertId(idRoot, s4);
        idRoot = handler.insertId(idRoot, s5);
        assertEquals(s1, idRoot.getSem());
        assertEquals(s4, idRoot.getLeft().getSem());
        assertEquals(s5, idRoot.getLeft().getRight().getSem());
        assertEquals(s2, idRoot.getRight().getSem());
        assertEquals(s3, idRoot.getRight().getRight().getSem());
    }


    /**
     * Test insertCost
     */
    public void testInsertCost() {
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
        costRoot = handler.insertCost(costRoot, s1);
        costRoot = handler.insertCost(costRoot, s2);
        costRoot = handler.insertCost(costRoot, s3);
        costRoot = handler.insertCost(costRoot, s4);
        costRoot = handler.insertCost(costRoot, s5);
        assertEquals(s1, costRoot.getSem());
        assertEquals(s2, costRoot.getRight().getSem());
        assertEquals(s3, costRoot.getLeft().getSem());
        assertEquals(s4, costRoot.getLeft().getLeft().getSem());
        assertEquals(s5, costRoot.getRight().getRight().getSem());
    }


    /**
     * Test insertDate
     */
    public void testInsertDate() {
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
        dateRoot = handler.insertDate(dateRoot, s1);
        dateRoot = handler.insertDate(dateRoot, s2);
        dateRoot = handler.insertDate(dateRoot, s3);
        dateRoot = handler.insertDate(dateRoot, s4);
        dateRoot = handler.insertDate(dateRoot, s5);
        assertEquals(s1, dateRoot.getSem());
        assertEquals(s2, dateRoot.getRight().getSem());
        assertEquals(s3, dateRoot.getRight().getRight().getSem());
        assertEquals(s4, dateRoot.getLeft().getSem());
        assertEquals(s5, dateRoot.getLeft().getRight().getSem());
    }


    public void testInsertKeyword() {
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
        kwRoot = handler.insertKeyword(kwRoot, "tag1", s1);
        kwRoot = handler.insertKeyword(kwRoot, "tag2", s2);
        kwRoot = handler.insertKeyword(kwRoot, "tag3", s3);
        kwRoot = handler.insertKeyword(kwRoot, "biscuit", s4);
        kwRoot = handler.insertKeyword(kwRoot, "tag1", s5);
        kwRoot = handler.insertKeyword(kwRoot, "tag4", s5);

        assertFuzzyEquals(s1.toString(), kwRoot.printSem());
        assertFuzzyEquals(s2.toString(), kwRoot.getRight().printSem());
        /*
         * assertFuzzyEquals(s2.toString() + "\n",
         * kwRoot.getRight().printSem());
         * assertFuzzyEquals(s3.toString() + "\n", kwRoot.getRight().getRight()
         * .printSem());
         * assertFuzzyEquals(s4.toString() + "\n", kwRoot.getLeft().printSem());
         * assertFuzzyEquals(s5.toString() + "\n", kwRoot.getRight().getRight()
         * .getRight().printSem());
         */
    }


    /**
     * Tests searchID(IdBST, int)
     */
    public void testSearchID() {
        idRoot = new IdBST(sem);
        assertNull(handler.searchId(null, 0));
        assertEquals(sem, handler.searchId(idRoot, 0));
        idRoot.setRight(right);
        assertEquals(sem3, handler.searchId(idRoot, 1));
        idRoot.setLeft(left);
        assertEquals(sem2.id(), handler.searchId(idRoot, 1).id());
    }


    /**
     * Tests searchCost(CostBST, low, high)
     */
    public void testSearchCost() {
        String[] tags = new String[] { "tag1", "tag2", "tag3" };
        Seminar s1 = new Seminar(5, "test", "0309251600", 13, (short)4,
            (short)9, 15, tags, "test description");
        costRoot = handler.insertCost(costRoot, s1);
        Seminar s2 = new Seminar(6, "test", "0309261600", 13, (short)4,
            (short)9, 21, tags, "test description");
        costRoot = handler.insertCost(costRoot, s2);
        Seminar s3 = new Seminar(5, "test", "0309251600", 13, (short)4,
            (short)9, 5, tags, "test description");
        costRoot = handler.insertCost(costRoot, s3);
        assertEquals((s1.toString() + "\n"), (handler.searchCost(costRoot, 10,
            20)));
        assertEquals((s1.toString() + "\n"), (handler.searchCost(costRoot, 15,
            15)));
        assertEquals((s2.toString() + "\n"), (handler.searchCost(costRoot, 20,
            21)));
        assertEquals((s3.toString() + "\n"), (handler.searchCost(costRoot, 5,
            10)));
        assertEquals((s3.toString() + "\n" + s1.toString() + "\n"), (handler
            .searchCost(costRoot, 5, 15)));
        assertEquals((s1.toString() + "\n" + s2.toString() + "\n"), (handler
            .searchCost(costRoot, 15, 21)));
        assertEquals((s3.toString() + "\n" + s1.toString() + "\n" + s2
            .toString() + "\n"), (handler.searchCost(costRoot, 5, 21)));
    }


    /**
     * Tests searchDate(DateBST, low, high)
     */
    public void testSearchDate() {
        String[] tags = new String[] { "tag1", "tag2", "tag3" };
        Seminar s1 = new Seminar(5, "test", "0312251600", 13, (short)4,
            (short)9, 15, tags, "test description"); // high date
        dateRoot = handler.insertDate(dateRoot, s1);
        Seminar s2 = new Seminar(6, "test", "0309282000", 13, (short)4,
            (short)9, 21, tags, "test description"); // middle date
        dateRoot = handler.insertDate(dateRoot, s2);
        Seminar s3 = new Seminar(5, "test", "0309250500", 13, (short)4,
            (short)9, 5, tags, "test description"); // low date
        dateRoot = handler.insertDate(dateRoot, s3);
        assertEquals("", (handler.searchDate(dateRoot, "04", "1")));
        assertFuzzyEquals((s1.toString() + "\n"), (handler.searchDate(dateRoot,
            "0312251600", "0312251600")));
        assertFuzzyEquals((s1.toString() + "\n"), (handler.searchDate(dateRoot,
            "031", "032")));
        assertFuzzyEquals((s2.toString() + "\n"), (handler.searchDate(dateRoot,
            "030928", "030929")));
        assertFuzzyEquals((s3.toString() + "\n"), (handler.searchDate(dateRoot,
            "030925", "030927")));
        assertFuzzyEquals((s2.toString() + "\n" + s1.toString() + "\n"),
            (handler.searchDate(dateRoot, "030928", "032")));
        assertFuzzyEquals((s3.toString() + "\n" + s2.toString() + "\n"),
            (handler.searchDate(dateRoot, "030", "031")));
        assertFuzzyEquals((s3.toString() + "\n" + s2.toString() + "\n" + s1
            .toString() + "\n"), (handler.searchDate(dateRoot, "0", "1")));
    }


    /**
     * Tests searchKeyword
     */

    public void testSearchKeyword() {
        String[] tags1 = new String[] { "tag1", "tag2", "tag3" };
        Seminar s1 = new Seminar(5, "test", "0312251600", 13, (short)4,
            (short)9, 15, tags1, "test description"); // high date
        kwRoot = handler.insertKeyword(kwRoot, "tag1", s1);
        kwRoot = handler.insertKeyword(kwRoot, "tag2", s1);
        kwRoot = handler.insertKeyword(kwRoot, "tag3", s1);
        String[] tags2 = new String[] { "tag4", "tag5", "tag6" };
        Seminar s2 = new Seminar(6, "test", "0309282000", 13, (short)4,
            (short)9, 21, tags2, "test description"); // middle date
        kwRoot = handler.insertKeyword(kwRoot, "tag4", s2);
        kwRoot = handler.insertKeyword(kwRoot, "tag5", s2);
        kwRoot = handler.insertKeyword(kwRoot, "tag6", s2);
        String[] tags3 = new String[] { "tag1", "tag10", "tag3" };
        Seminar s3 = new Seminar(7, "test", "0309250500", 13, (short)4,
            (short)9, 5, tags3, "test description"); // low date
        kwRoot = handler.insertKeyword(kwRoot, "tag1", s3);
        kwRoot = handler.insertKeyword(kwRoot, "tag10", s3);
        kwRoot = handler.insertKeyword(kwRoot, "tag3", s3);
        assertFuzzyEquals(s3.toString() + "\n" + s1.toString() + "\n", handler
            .searchKeyword(kwRoot, "tag1"));
        assertFuzzyEquals(s1.toString() + "\n", handler.searchKeyword(kwRoot,
            "tag2"));
        assertFuzzyEquals(s3.toString() + "\n" + s1.toString() + "\n", handler
            .searchKeyword(kwRoot, "tag3"));
        assertFuzzyEquals(s2.toString() + "\n", handler.searchKeyword(kwRoot,
            "tag4"));
        assertFuzzyEquals(s2.toString() + "\n", handler.searchKeyword(kwRoot,
            "tag5"));
        assertFuzzyEquals(s2.toString() + "\n", handler.searchKeyword(kwRoot,
            "tag6"));
        assertFuzzyEquals("", handler.searchKeyword(kwRoot, "tag7"));
        assertFuzzyEquals(s3.toString() + "\n", handler.searchKeyword(kwRoot,
            "tag10"));

    }


    /**
     * Tests the printID method
     */
    public void testPrintID() {
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
        idRoot = handler.insertId(idRoot, s1);
        idRoot = handler.insertId(idRoot, s2);
        idRoot = handler.insertId(idRoot, s3);
        idRoot = handler.insertId(idRoot, s4);
        idRoot = handler.insertId(idRoot, s5);
        assertFuzzyEquals("      null\n" + "    7\n" + "      null\n" + "  6\n"
            + "    null\n" + "5\n" + "      null\n" + "    2\n" + "      null\n"
            + "  0\n" + "    null\n", handler.printID(idRoot, ""));
    }


    /**
     * Tests the printKeyword method
     */
    public void testPrintKeyword() {
        String[] tags1 = new String[] { "tag1", "tag2", "tag3" };
        Seminar s1 = new Seminar(5, "test", "0312251600", 13, (short)4,
            (short)9, 15, tags1, "test description"); // high date
        kwRoot = handler.insertKeyword(kwRoot, "tag1", s1);
        kwRoot = handler.insertKeyword(kwRoot, "tag2", s1);
        kwRoot = handler.insertKeyword(kwRoot, "tag3", s1);
        String[] tags2 = new String[] { "tag4", "tag5", "tag6" };
        Seminar s2 = new Seminar(6, "test", "0309282000", 13, (short)4,
            (short)9, 21, tags2, "test description"); // middle date
        kwRoot = handler.insertKeyword(kwRoot, "tag4", s2);
        kwRoot = handler.insertKeyword(kwRoot, "tag5", s2);
        kwRoot = handler.insertKeyword(kwRoot, "tag6", s2);
        String[] tags3 = new String[] { "tag1", "tag10", "tag3" };
        Seminar s3 = new Seminar(7, "test", "0309250500", 13, (short)4,
            (short)9, 5, tags3, "test description"); // low date
        kwRoot = handler.insertKeyword(kwRoot, "tag10", s3);
        kwRoot = handler.insertKeyword(kwRoot, "tag3", s3);
        kwRoot = handler.insertKeyword(kwRoot, "tag1", s3);
        assertFuzzyEquals("            null\n" + "          tag6\n"
            + "            null\n" + "        tag5\n" + "          null\n"
            + "      tag4\n" + "        null\n" + "    tag3\n        null\n"
            + "      tag3\n" + "        null\n" + "  tag2\n" + "      null\n"
            + "    tag10\n" + "      null\n" + "tag1\n    null\n" + "  tag1\n"
            + "    null", handler.printKeyword(kwRoot, ""));
    }


    /**
     * Test prntDate method
     */
    public void testPrintDate() {
        String[] tags = new String[] { "tag1", "tag2", "tag3" };
        Seminar s1 = new Seminar(5, "test", "0312251600", 13, (short)4,
            (short)9, 15, tags, "test description"); // high date
        dateRoot = handler.insertDate(dateRoot, s1);
        Seminar s2 = new Seminar(6, "test", "0309282000", 13, (short)4,
            (short)9, 21, tags, "test description"); // middle date
        dateRoot = handler.insertDate(dateRoot, s2);
        Seminar s3 = new Seminar(5, "test", "0309250500", 13, (short)4,
            (short)9, 5, tags, "test description"); // low date
        dateRoot = handler.insertDate(dateRoot, s3);
        assertFuzzyEquals(
            "  null\n0312251600\n    null\n  0309282000\n      null\n    0309250500\n      null",
            handler.printDate(dateRoot, ""));
    }


    public void testPrintCost() {
        String[] tags = new String[] { "tag1", "tag2", "tag3" };
        Seminar s1 = new Seminar(5, "test", "0309251600", 13, (short)4,
            (short)9, 15, tags, "test description");
        costRoot = handler.insertCost(costRoot, s1);
        Seminar s2 = new Seminar(6, "test", "0309261600", 13, (short)4,
            (short)9, 21, tags, "test description");
        costRoot = handler.insertCost(costRoot, s2);
        Seminar s3 = new Seminar(5, "test", "0309251600", 13, (short)4,
            (short)9, 5, tags, "test description");
        costRoot = handler.insertCost(costRoot, s3);
        assertFuzzyEquals(
            "    null\n  21\n    null\n15\n    null\n  5\n    null", handler
                .printCost(costRoot, ""));
    }


    public void testDeleteId() {
        assertNull(handler.deleteId(null, 0));
        String[] tags = new String[] { "tag1", "tag2", "tag3" };
        Seminar s1 = new Seminar(3, "test", "0309251600", 13, (short)4,
            (short)9, 15, tags, "test description");
        Seminar s2 = new Seminar(5, "test", "0309251600", 13, (short)4,
            (short)9, 15, tags, "test description");
        Seminar s3 = new Seminar(9, "test", "0309251600", 13, (short)4,
            (short)9, 15, tags, "test description");
        Seminar s4 = new Seminar(15, "test", "0309251600", 13, (short)4,
            (short)9, 15, tags, "test description");
        Seminar s5 = new Seminar(18, "test", "0309251600", 13, (short)4,
            (short)9, 15, tags, "test description");
        Seminar s6 = new Seminar(4, "test", "0309251600", 13, (short)4,
            (short)9, 15, tags, "test description");
        idRoot = handler.insertId(idRoot, s3);
        idRoot = handler.insertId(idRoot, s4);
        idRoot = handler.insertId(idRoot, s1);
        idRoot = handler.insertId(idRoot, s2);
        idRoot = handler.insertId(idRoot, s5);
        idRoot = handler.insertId(idRoot, s6);
        idRoot = handler.deleteId(idRoot, 5);
        assertEquals(3, idRoot.getLeft().getId());
        idRoot = handler.deleteId(idRoot, 3);
        assertEquals(4, idRoot.getLeft().getId());
        idRoot = handler.insertId(idRoot, s1);
        idRoot = handler.insertId(idRoot, s2);
        idRoot = handler.deleteId(idRoot, 4);
        assertEquals(3, idRoot.getLeft().getId());
        idRoot = handler.deleteId(idRoot, 9);
        assertEquals(5, idRoot.getId());
        idRoot = handler.deleteId(idRoot, 5);
        assertEquals(3, idRoot.getId());
        idRoot = handler.deleteId(idRoot, 3);
        assertEquals(15, idRoot.getId());
        idRoot = handler.insertId(idRoot, s3);
        idRoot = handler.insertId(idRoot, s2);
        idRoot = handler.insertId(idRoot, s1);
        idRoot = handler.deleteId(idRoot, 5);
        assertEquals(3, idRoot.getLeft().getLeft().getId());
    }


    /**
     * Test delete from cost BST
     */
    public void testDeleteCost() {
        String[] tags = new String[] { "tag1", "tag2", "tag3" };
        Seminar s1 = new Seminar(5, "test", "0309251600", 13, (short)4,
            (short)9, 15, tags, "test description");
        costRoot = handler.insertCost(costRoot, s1);
        Seminar s2 = new Seminar(6, "test", "0309261600", 13, (short)4,
            (short)9, 21, tags, "test description");
        costRoot = handler.insertCost(costRoot, s2);
        Seminar s3 = new Seminar(2, "test", "0309251600", 13, (short)4,
            (short)9, 5, tags, "test description");
        Seminar s4 = new Seminar(3, "test", "0309251600", 13, (short)4,
            (short)9, 12, tags, "test description");
        costRoot = handler.insertCost(costRoot, s3);
        costRoot = handler.deleteCost(costRoot, 15, 5);
        assertEquals(5, costRoot.getCost());
        costRoot = handler.insertCost(costRoot, s1);
        costRoot = handler.deleteCost(costRoot, 21, 6);
        assertEquals(15, costRoot.getRight().getCost());
        costRoot = handler.insertCost(costRoot, s4);
        costRoot = handler.deleteCost(costRoot, 12, 3);
        assertNull(costRoot.getRight().getLeft());
        costRoot = handler.insertCost(costRoot, s2);
        costRoot = handler.deleteCost(costRoot, 5, 2);
        assertEquals(15, costRoot.getCost());
        costRoot = handler.insertCost(costRoot, s3);
        costRoot = handler.insertCost(costRoot, s4);
        costRoot = handler.deleteCost(costRoot, 15, 5);
        assertEquals(12, costRoot.getCost());
        assertEquals("    null\n  21\n    null\n12\n    null\n  5\n    null",
            handler.printCost(costRoot, ""));

    }


    public void testDeleteDate() {
        String[] tags = new String[] { "tag1", "tag2", "tag3" };
        Seminar s1 = new Seminar(3, "test", "0", 13, (short)4, (short)9, 15,
            tags, "test description");
        Seminar s2 = new Seminar(5, "test", "1", 13, (short)4, (short)9, 15,
            tags, "test description");
        Seminar s3 = new Seminar(9, "test", "2", 13, (short)4, (short)9, 15,
            tags, "test description");
        Seminar s4 = new Seminar(15, "test", "3", 13, (short)4, (short)9, 15,
            tags, "test description");
        Seminar s5 = new Seminar(18, "test", "4", 13, (short)4, (short)9, 15,
            tags, "test description");
        Seminar s6 = new Seminar(4, "test", "5", 13, (short)4, (short)9, 15,
            tags, "test description");
        dateRoot = handler.insertDate(dateRoot, s4);
        dateRoot = handler.insertDate(dateRoot, s5);
        dateRoot = handler.insertDate(dateRoot, s6);
        dateRoot = handler.insertDate(dateRoot, s1);
        dateRoot = handler.insertDate(dateRoot, s2);
        dateRoot = handler.insertDate(dateRoot, s3);
        dateRoot = handler.deleteDate(dateRoot, "1", 5);
        dateRoot = handler.deleteDate(dateRoot, "4", 18);
        dateRoot = handler.deleteDate(dateRoot, "3", 15);
        dateRoot = handler.insertDate(dateRoot, s5);
        dateRoot = handler.deleteDate(dateRoot, "5", 4);
        assertFuzzyEquals("    null\r\n" + "  4\r\n" + "    null\r\n" + "2\r\n"
            + "    null\r\n" + "  0\r\n" + "    null", handler.printDate(
                dateRoot, ""));
    }


    public void testDeleteKeyword() {
        Seminar s1 = new Seminar(3, "test", "0", 13, (short)4, (short)9, 15,
            new String[] { "tag0" }, "test description");
        Seminar s2 = new Seminar(5, "test", "1", 13, (short)4, (short)9, 15,
            new String[] { "tag1" }, "test description");
        Seminar s3 = new Seminar(9, "test", "2", 13, (short)4, (short)9, 15,
            new String[] { "tag2" }, "test description");
        Seminar s4 = new Seminar(15, "test", "3", 13, (short)4, (short)9, 15,
            new String[] { "tag3" }, "test description");
        Seminar s5 = new Seminar(18, "test", "4", 13, (short)4, (short)9, 15,
            new String[] { "tag4" }, "test description");
        Seminar s6 = new Seminar(4, "test", "5", 13, (short)4, (short)9, 15,
            new String[] { "tag5" }, "test description");
        kwRoot = handler.insertKeyword(kwRoot, "tag3", s4);
        kwRoot = handler.insertKeyword(kwRoot, "tag4", s5);
        kwRoot = handler.insertKeyword(kwRoot, "tag5", s6);
        kwRoot = handler.insertKeyword(kwRoot, "tag0", s1);
        kwRoot = handler.insertKeyword(kwRoot, "tag1", s2);
        kwRoot = handler.insertKeyword(kwRoot, "tag2", s3);
        System.out.println(handler.printKeyword(kwRoot, ""));
        kwRoot = handler.deleteKeyword(kwRoot, "tag1", 5);
        System.out.println(handler.printKeyword(kwRoot, ""));
        kwRoot = handler.deleteKeyword(kwRoot, "tag4", 18);
        kwRoot = handler.deleteKeyword(kwRoot, "tag3", 15);
        kwRoot = handler.insertKeyword(kwRoot, "tag4", s5);
        kwRoot = handler.deleteKeyword(kwRoot, "tag5", 4);
        assertFuzzyEquals("    null\r\n" + "  tag4\r\n" + "    null\r\n"
            + "tag2\r\n" + "    null\r\n" + "  tag0\r\n" + "    null", handler
                .printKeyword(kwRoot, ""));
    }


    public void testCounter() {
        handler.resetCount();
        assertEquals(0, handler.getCount());
        Seminar s1 = new Seminar(5, "test", "1", 13, (short)4, (short)9, 1,
            new String[] { "tag1" }, "test description");
        Seminar s2 = new Seminar(6, "test", "2", 13, (short)4, (short)9, 2,
            new String[] { "tag2" }, "test description");
        Seminar s3 = new Seminar(7, "test", "3", 13, (short)4, (short)9, 3,
            new String[] { "tag3" }, "test description");
        Seminar s4 = new Seminar(0, "test", "4", 13, (short)4, (short)9, 0,
            new String[] { "biscuit" }, "test description");
        Seminar s5 = new Seminar(2, "test", "5", 13, (short)4, (short)9, 25,
            new String[] { "tag1", "tag4" }, "test description");
        idRoot = handler.insertId(idRoot, s1); // TESTING ID
        idRoot = handler.insertId(idRoot, s2);
        idRoot = handler.insertId(idRoot, s3);
        idRoot = handler.insertId(idRoot, s4);
        idRoot = handler.insertId(idRoot, s5);
        assertEquals(s4, handler.searchId(idRoot, 0));
        assertEquals(2, handler.getCount());
        handler.resetCount();
        assertEquals(s3, handler.searchId(idRoot, 7));
        assertEquals(3, handler.getCount());

        costRoot = handler.insertCost(costRoot, s1); // TEST COST
        costRoot = handler.insertCost(costRoot, s2);
        costRoot = handler.insertCost(costRoot, s3);
        handler.resetCount();
        assertFuzzyEquals(s2.toString(), handler.searchCost(costRoot,2, 2));
        assertEquals(3, handler.getCount());

        dateRoot = handler.insertDate(dateRoot, s2); // TEST DATE
        dateRoot = handler.insertDate(dateRoot, s1);
        dateRoot = handler.insertDate(dateRoot, s3);
        handler.resetCount();
        assertFuzzyEquals(s1.toString(), handler.searchDate(dateRoot, "1",
            "1"));
        assertEquals(2, handler.getCount());
        kwRoot = handler.insertKeyword(kwRoot, "tag1", s1); // TESTING KEYWORDS
        kwRoot = handler.insertKeyword(kwRoot, "tag2", s2);
        kwRoot = handler.insertKeyword(kwRoot, "tag3", s3);
        handler.resetCount();
        assertFuzzyEquals(s2.toString(), handler.searchKeyword(kwRoot, "tag2"));
        assertEquals(2, handler.getCount());
        assertNull(handler.deleteId(null, 0));
        assertNull(handler.deleteCost(null, 0, 0));
        assertNull(handler.deleteDate(null, "", 0));
        assertNull(handler.deleteKeyword(null, "", 0));
    }
}
