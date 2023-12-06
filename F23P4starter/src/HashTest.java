import student.TestCase;

/**
 * @author Phillip Jordan (alexj14)
 * @author David (Ta-Jung) Lin (davidsmile)
 * @version 2023.11.28
 */
public class HashTest extends TestCase {
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        // Nothing Here
    }


    /**
     * Check out the sfold method
     *
     * @throws Exception
     *             either a IOException or FileNotFoundException
     */
    public void testSfold() throws Exception {
        assertEquals(Hash.h("a", 10000), 97);
        assertEquals(Hash.h("b", 10000), 98);
        assertEquals(Hash.h("aaaa", 10000), 1873);
        assertEquals(Hash.h("aaab", 10000), 9089);
        assertEquals(Hash.h("baaa", 10000), 1874);
        assertEquals(Hash.h("aaaaaaa", 10000), 3794);
        assertEquals(Hash.h("Long Lonesome Blues", 10000), 4635);
        assertEquals(Hash.h("Long   Lonesome Blues", 10000), 4159);
        assertEquals(Hash.h("long Lonesome Blues", 10000), 4667);
    }
}
