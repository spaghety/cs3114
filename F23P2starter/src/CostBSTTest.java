import student.TestCase;

/**
 * Tests the CostBST class
 * 
 * @author Phillip Jordan (alexj14)
 * @author Ta-Jung (David) Lin (davidsmile)
 * @version 2023.09.24
 */
public class CostBSTTest extends TestCase {
    private CostBST root;
    private CostBST second;
    private String[] tags;
    private Seminar sem;
    private Seminar secondSem;

    /**
     * Set up variables for test cases
     */
    public void setUp() {
        tags = new String[] { "tag1", "tag2", "tag3" };
        sem = new Seminar(1, "example", "Oct30", 90, (short)2, (short)14, 23,
            tags, "Test description");
        root = new CostBST(sem);
        secondSem = new Seminar(1, "example", "Oct30", 90, (short)2, (short)14,
            23, tags, "Test description");
        second = new CostBST(secondSem);
    }


    /**
     * Test getCost method
     */
    public void testGetCost() {
        assertEquals(23, root.getCost());
    }


    /**
     * Test getSem method
     */
    public void testGetSem() {
        assertTrue(sem.equals(root.getSem()));
    }


    /**
     * Test left child setter and getter
     */
    public void testSetGetLeft() {
        root.setLeft(second);
        assertTrue(secondSem.equals(root.getLeft().getSem()));
    }


    public void testSetGetRight() {
        root.setRight(second);
        assertTrue(secondSem.equals(root.getRight().getSem()));
    }

}
