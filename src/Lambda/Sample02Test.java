/**
 * 
 */
package Lambda;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author user01
 *
 */
class Sample02Test extends Sample02 {

    /**
     * @throws java.lang.Exception
     */
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterEach
    void tearDown() throws Exception {
    }

    /**
     * {@link Lambda.Sample02#sortSample()} のためのテスト・メソッド。
     */
    @Test
    void testSortSample() {
        List<Integer> numbersList = Arrays.asList(numbers);
        Collections.sort(numbersList, ascending);

        assertEquals((Integer) (-3), numbersList.get(0));
        assertEquals((Integer) (-1), numbersList.get(1));
        assertEquals((Integer) 0, numbersList.get(2));
    }
}
