import student.TestCase;

public class MemManagerTest extends TestCase {
    
    private MemManager memManager;
    /**
     * Sets up the tests that follow.
     */
    public void setUp() {
        memManager = new MemManager(16);
    }
}
