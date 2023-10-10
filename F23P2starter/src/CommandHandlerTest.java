import student.TestCase;

/**
 * Tests the CommandHandler
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.10.09
 */
public class CommandHandlerTest extends TestCase {
    private CommandHandler ch;

    private BSTree idRoot;
    private BSTree dateRoot;
    private BSTree costRoot;
    private BSTree kwRoot;
    private Seminar sem;
    private Seminar sem2;
    private Seminar sem3;
    private Seminar s1;
    private Seminar s2;
    private Seminar s3;
    private Seminar s4;
    private Seminar s5;
    private BSTree left;
    private BSTree right;

    /**
     * Sets up the tests
     */
    public void setUp() {
        String[] tags = new String[] { "tag1", "tag2", "tag3" };
        ch = new CommandHandler();

        idRoot = null;
        dateRoot = null;
        costRoot = null;
        kwRoot = null;
        sem = new Seminar(2, "test", "Jan132002", 13, (short) 4, (short) 9, 15, tags, "test description");
        sem2 = new Seminar(1, "second test name", "Jun222010", 90, (short) 2, (short) 13, 42, tags,
                "test description two");
        left = new BSTree(sem2, sem2.id(), null);
        sem3 = new Seminar(3, "second test name", "Jun222010", 90, (short) 2, (short) 13, 42, tags,
                "test description two");
        right = new BSTree(sem3, sem3.id(), null);

        s1 = new Seminar(5, "test", "0309251600", 13, (short) 4, (short) 9, 15, new String[] { "tag1" },
                "test description");
        s2 = new Seminar(6, "test", "0309261600", 13, (short) 4, (short) 9, 20, new String[] { "tag2" },
                "test description");
        s3 = new Seminar(7, "test", "0309271600", 13, (short) 4, (short) 9, 10, new String[] { "tag3" },
                "test description");
        s4 = new Seminar(0, "test", "0309201600", 13, (short) 4, (short) 9, 0, new String[] { "biscuit" },
                "test description");
        s5 = new Seminar(2, "test", "0309221600", 13, (short) 4, (short) 9, 25, new String[] { "tag1", "tag4" },
                "test description");

    }

    /**
     * Tests insert and delete
     */
    public void testInsertDelete() {
        ch.insert(s1);
        ch.insert(s2);
        ch.insert(s3);
        ch.insert(s4);
        assertEquals(4, ch.getNodeCount());
        assertEquals(4, ch.getKeywordCount());
        ch.insert(s5);
        assertEquals(5, ch.getNodeCount());
        assertEquals(6, ch.getKeywordCount());

        ch.delete(s1, s1.id());
        assertEquals(4, ch.getNodeCount());
        assertEquals(5, ch.getKeywordCount());
        ch.delete(s5, s5.id());
        assertEquals(3, ch.getNodeCount());
        assertEquals(3, ch.getKeywordCount());
    }

    /**
     * Tests insertId
     */
    public void testInsertId() {
        idRoot = ch.insertBST(idRoot, s1, s1.id(), null);
        idRoot = ch.insertBST(idRoot, s2, s2.id(), null);
        idRoot = ch.insertBST(idRoot, s3, s3.id(), null);
        idRoot = ch.insertBST(idRoot, s4, s4.id(), null);
        idRoot = ch.insertBST(idRoot, s5, s5.id(), null);
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
        costRoot = ch.insertBST(costRoot, s1, s1.cost(), null);
        costRoot = ch.insertBST(costRoot, s2, s2.cost(), null);
        costRoot = ch.insertBST(costRoot, s3, s3.cost(), null);
        costRoot = ch.insertBST(costRoot, s4, s4.cost(), null);
        costRoot = ch.insertBST(costRoot, s5, s5.cost(), null);
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
        dateRoot = ch.insertBST(dateRoot, s1, Integer.MIN_VALUE, s1.date());
        dateRoot = ch.insertBST(dateRoot, s2, Integer.MIN_VALUE, s2.date());
        dateRoot = ch.insertBST(dateRoot, s3, Integer.MIN_VALUE, s3.date());
        dateRoot = ch.insertBST(dateRoot, s4, Integer.MIN_VALUE, s4.date());
        dateRoot = ch.insertBST(dateRoot, s5, Integer.MIN_VALUE, s5.date());
        assertEquals(s1, dateRoot.getSem());
        assertEquals(s2, dateRoot.getRight().getSem());
        assertEquals(s3, dateRoot.getRight().getRight().getSem());
        assertEquals(s4, dateRoot.getLeft().getSem());
        assertEquals(s5, dateRoot.getLeft().getRight().getSem());
    }

    /**
     * Tests insertKeyword
     */
    public void testInsertKeyword() {
        kwRoot = ch.insertBST(kwRoot, s1, Integer.MIN_VALUE, "tag1");
        kwRoot = ch.insertBST(kwRoot, s2, Integer.MIN_VALUE, "tag2");
        kwRoot = ch.insertBST(kwRoot, s3, Integer.MIN_VALUE, "tag3");
        kwRoot = ch.insertBST(kwRoot, s4, Integer.MIN_VALUE, "biscuit");
        kwRoot = ch.insertBST(kwRoot, s5, Integer.MIN_VALUE, "tag1");
        kwRoot = ch.insertBST(kwRoot, s5, Integer.MIN_VALUE, "tag4");
        assertEquals(s1, kwRoot.getSem());
        assertEquals(s2, kwRoot.getRight().getSem());
        assertEquals(s3, kwRoot.getRight().getRight().getSem());
        assertEquals(s4, kwRoot.getLeft().getSem());
        assertEquals(s5, kwRoot.getLeft().getRight().getSem());
        assertEquals(s5, kwRoot.getRight().getRight().getRight().getSem());
    }

    /**
     * Tests searchId(int) and searchNumber(BSTree, int) indirectly
     */
    public void testSearchID() {
        assertNull(ch.searchId(0));
        ch.insert(sem);
        assertEquals(sem, ch.searchId(2));
        ch.insert(sem3);
        assertEquals(sem3, ch.searchId(3));
        ch.insert(sem2);
        assertEquals(sem2.id(), ch.searchId(1).id());
    }

    /**
     * Tests searchCost(int, int)
     */
    public void testSearchCost() {
        String[] tags = new String[] { "tag1", "tag2", "tag3" };
        s1 = new Seminar(5, "test", "0309251600", 13, (short) 4, (short) 9, 15, tags, "test description");
        s2 = new Seminar(6, "test", "0309261600", 13, (short) 4, (short) 9, 21, tags, "test description");
        s3 = new Seminar(5, "test", "0309251600", 13, (short) 4, (short) 9, 5, tags, "test description");
        ch.insert(s1);
        ch.insert(s2);
        ch.insert(s3);
        assertEquals(s1.toString() + "\n5 nodes visited in this search\n", ch.searchCost(10, 20));
        assertEquals(s1.toString() + "\n5 nodes visited in this search\n", ch.searchCost(15, 15));
        assertEquals(s2.toString() + "\n4 nodes visited in this search\n", ch.searchCost(20, 21));
        assertEquals(s3.toString() + "\n4 nodes visited in this search\n", ch.searchCost(5, 10));
        assertEquals((s3.toString() + "\n" + s1.toString() + "\n6 nodes visited in this search\n"),
                (ch.searchCost(5, 15)));
        assertEquals((s1.toString() + "\n" + s2.toString() + "\n6 nodes visited in this search\n"),
                (ch.searchCost(15, 21)));
        assertEquals(
                (s3.toString() + "\n" + s1.toString() + "\n" + s2.toString() + "\n7 nodes visited in this search\n"),
                (ch.searchCost(5, 21)));
    }

    /**
     * Tests searchDate and searchStringRange
     */
    public void testSearchDate() {
        String[] tags = new String[] { "tag1", "tag2", "tag3" };
        s1 = new Seminar(5, "test", "0312251600", 13, (short) 4, (short) 9, 15, tags, "test description"); // high date
        s2 = new Seminar(6, "test", "0309282000", 13, (short) 4, (short) 9, 21, tags, "test description"); // middle
                                                                                                           // date
        s3 = new Seminar(5, "test", "0309250500", 13, (short) 4, (short) 9, 5, tags, "test description"); // low date
        ch.insert(s1);
        ch.insert(s2);
        ch.insert(s3);
        assertEquals("2 nodes visited in this search\n", (ch.searchDate("04", "1")));
        assertFuzzyEquals((s1.toString() + "\n4 nodes visited in this search\n"),
                (ch.searchDate("0312251600", "0312251600")));
        assertFuzzyEquals((s1.toString() + "\n4 nodes visited in this search\n"), (ch.searchDate("031", "032")));
        assertFuzzyEquals((s2.toString() + "\n5 nodes visited in this search\n"), (ch.searchDate("030928", "030929")));
        assertFuzzyEquals((s3.toString() + "\n5 nodes visited in this search\n"), (ch.searchDate("030925", "030927")));
        assertFuzzyEquals((s2.toString() + "\n" + s1.toString() + "\n6 nodes visited in this search\n"),
                (ch.searchDate("030928", "032")));
        assertFuzzyEquals((s3.toString() + "\n" + s2.toString() + "\n6 nodes visited in this search\n"),
                (ch.searchDate("030", "031")));
        assertFuzzyEquals(
                (s3.toString() + "\n" + s2.toString() + "\n" + s1.toString() + "\n7 nodes visited in this search\n"),
                (ch.searchDate("0", "1")));
    }

    /**
     * Tests searchKeyword, both overloaded methods
     */

    public void testSearchKeyword() {
        String[] tags1 = new String[] { "tag1", "tag2", "tag3" };
        s1 = new Seminar(5, "test", "0312251600", 13, (short) 4, (short) 9, 15, tags1, "test description"); // high date
        String[] tags2 = new String[] { "tag4", "tag5", "tag6" };
        s2 = new Seminar(6, "test", "0309282000", 13, (short) 4, (short) 9, 21, tags2, "test description"); // middle
                                                                                                            // date
        String[] tags3 = new String[] { "tag1", "tag10", "tag3" };
        s3 = new Seminar(7, "test", "0309250500", 13, (short) 4, (short) 9, 5, tags3, "test description"); // low date
        ch.insert(s1);
        ch.insert(s2);
        ch.insert(s3);
        assertFuzzyEquals(s3.toString() + "\n" + s1.toString() + "\n", ch.searchKeyword("tag1"));
        assertFuzzyEquals(s1.toString() + "\n", ch.searchKeyword("tag2"));
        assertFuzzyEquals(s3.toString() + "\n" + s1.toString() + "\n", ch.searchKeyword("tag3"));
        assertFuzzyEquals(s2.toString() + "\n", ch.searchKeyword("tag4"));
        assertFuzzyEquals(s2.toString() + "\n", ch.searchKeyword("tag5"));
        assertFuzzyEquals(s2.toString() + "\n", ch.searchKeyword("tag6"));
        assertFuzzyEquals(s3.toString() + "\n", ch.searchKeyword("tag10"));

    }

    /**
     * Tests the print method
     */
    public void testPrint() {
        assertFuzzyEquals("ID Tree:\nThis tree is empty\n", ch.print("ID"));
        ch.insert(s1);
        ch.insert(s2);
        ch.insert(s3);
        ch.insert(s4);
        ch.insert(s5);
        assertFuzzyEquals(
                "ID Tree:\n      null\n" + "    7\n" + "      null\n" + "  6\n" + "    null\n" + "5\n" + "      null\n"
                        + "    2\n" + "      null\n" + "  0\n" + "    null\n" + "Number of records: 5\n",
                ch.print("ID"));
    }

    /**
     * Tests the print method
     */
    public void testPrint2() {
        String[] tags1 = new String[] { "tag1", "tag2", "tag3" };
        s1 = new Seminar(5, "test", "0312251600", 13, (short) 4, (short) 9, 15, tags1, "test description"); // high date
        String[] tags2 = new String[] { "tag4", "tag5", "tag6" };
        s2 = new Seminar(6, "test", "0309282000", 13, (short) 4, (short) 9, 21, tags2, "test description"); // middle
                                                                                                            // date
        String[] tags3 = new String[] { "tag1", "tag10", "tag3" };
        s3 = new Seminar(7, "test", "0309250500", 13, (short) 4, (short) 9, 5, tags3, "test description"); // low date
        ch.insert(s1);
        ch.insert(s2);
        ch.insert(s3);
        assertFuzzyEquals(
                "Cost Tree:\n" + "    null\n  21\n    null\n15\n    null\n  5\n    null\n" + "Number of records: 3\n",
                ch.print("Cost"));
        assertFuzzyEquals("Date Tree:\n" + "  null\n0312251600\n    null\n  0309282000\n      null\n"
                + "    0309250500\n      null\n" + "Number of records: 3\n", ch.print("Date"));
        assertFuzzyEquals(
                "Keyword Tree:\n" + "            null\n" + "          tag6\n" + "            null\n" + "        tag5\n"
                        + "          null\n" + "      tag4\n" + "        null\n" + "    tag3\n        null\n"
                        + "      tag3\n" + "        null\n" + "  tag2\n" + "      null\n" + "    tag10\n"
                        + "      null\n" + "tag1\n    null\n" + "  tag1\n" + "    null\n" + "Number of records: 9\n",
                ch.print("Keyword"));
    }

    /**
     * Tests deleteBST method
     */
    public void testDeleteBST() {
        assertNull(ch.deleteBST(null, 0, null, 0));
        String[] tags = new String[] { "tag1", "tag2", "tag3" };
        s1 = new Seminar(3, "test", "0309251600", 13, (short) 4, (short) 9, 15, tags, "test description");
        s2 = new Seminar(5, "test", "0309251600", 13, (short) 4, (short) 9, 15, tags, "test description");
        s3 = new Seminar(9, "test", "0309251600", 13, (short) 4, (short) 9, 15, tags, "test description");
        s4 = new Seminar(15, "test", "0309251600", 13, (short) 4, (short) 9, 15, tags, "test description");
        s5 = new Seminar(18, "test", "0309251600", 13, (short) 4, (short) 9, 15, tags, "test description");
        Seminar s6 = new Seminar(4, "test", "0309251600", 13, (short) 4, (short) 9, 15, tags, "test description");
        idRoot = ch.insertBST(idRoot, s3, s3.id(), null);
        idRoot = ch.insertBST(idRoot, s4, s4.id(), null);
        idRoot = ch.insertBST(idRoot, s1, s1.id(), null);
        idRoot = ch.insertBST(idRoot, s2, s2.id(), null);
        idRoot = ch.insertBST(idRoot, s5, s5.id(), null);
        idRoot = ch.insertBST(idRoot, s6, s6.id(), null);
        idRoot = ch.deleteBST(idRoot, 5, null, 5);
        assertEquals(3, idRoot.getLeft().value());
        assertEquals(4, idRoot.getLeft().getRight().value());
        idRoot = ch.deleteBST(idRoot, 3, null, 3);
        assertEquals(4, idRoot.getLeft().value());
        idRoot = ch.insertBST(idRoot, s1, s1.id(), null);
        idRoot = ch.insertBST(idRoot, s2, s2.id(), null);
        idRoot = ch.deleteBST(idRoot, 4, null, 4);
        assertEquals(3, idRoot.getLeft().value());
        idRoot = ch.deleteBST(idRoot, 9, null, 9);
        assertEquals(5, idRoot.value());
        idRoot = ch.deleteBST(idRoot, 5, null, 5);
        assertEquals(3, idRoot.value());
        idRoot = ch.deleteBST(idRoot, 3, null, 3);
        assertEquals(15, idRoot.value());
        idRoot = ch.insertBST(idRoot, s3, s3.id(), null);
        idRoot = ch.insertBST(idRoot, s2, s2.id(), null);
        idRoot = ch.insertBST(idRoot, s1, s1.id(), null);
        idRoot = ch.deleteBST(idRoot, 5, null, 5);
        assertEquals(3, idRoot.getLeft().getLeft().value());
    }
}
