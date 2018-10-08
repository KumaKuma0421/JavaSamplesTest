package Lambda;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
class LambdaSampleTest extends LambdaSample {

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

    @Test
    void testInterface() {
        show("Hallo, Lambda.");
    }

    @Test
    void testLambdaSupplier() {
        show(mySupplier.get());
    }

    @Test
    void testLambdaConsumer() {
        myConsumer.accept("Lambda Consumer.");
    }

    @Test
    void testLambdaPredicate() {
        assertTrue(predicate.test("JAVA"));
        assertTrue(predicate.test("Java"));
        assertTrue(predicate.test("java"));
    }

    @Test
    void testLambdaFunction() {
        show(function.apply(120));
    }

    @Test
    void testLambdaUnaryOperator() {
        assertEquals((Integer) 4, unaryOperator.apply(2));
        assertEquals((Integer) 9, unaryOperator.apply(3));
    }

    @Test
    void testLambdaBinaryOperator() {
        assertEquals((Integer) 6, binaryOperator.apply(2, 3));
        assertEquals((Integer) 12, binaryOperator.apply(3, 4));
    }

    @Test
    void testLambdaComparator() {
        Integer[] numbers = { -1, 2, 0, -3, 8 };
        List<Integer> numbersList = Arrays.asList(numbers);
        Collections.sort(numbersList, ascending);

        assertEquals((Integer) (-3), numbersList.get(0));
        assertEquals((Integer) (-1), numbersList.get(1));
        assertEquals((Integer) 0, numbersList.get(2));
    }
}
