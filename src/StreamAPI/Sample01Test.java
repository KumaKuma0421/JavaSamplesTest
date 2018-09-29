package StreamAPI;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Sample01Test extends Sample01 {

    Integer[] numbers = { -1, 2, 0, -3, 8 };
    List<Integer> numbersList;
    Integer valueBefore;
    double response;
    IntStream numbers2;
    IntStream numbers3;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
        this.valueBefore = Integer.MIN_VALUE;
        this.numbersList = Arrays.asList(numbers);
        this.numbers2 = IntStream.rangeClosed(1, Integer.MAX_VALUE);
        this.numbers3 = IntStream.rangeClosed(1, 1000);
    }

    @AfterEach
    void tearDown() throws Exception {
        this.numbersList = null;
        this.numbers2 = null;
        this.numbers3 = null;
    }

    @Test
    public void testForEach() {
        this.numbersList.stream().forEach(action);
    }

    @Test
    public void testFilter() {
        var response = this.numbersList.stream().filter(filter);
        response.forEach((i) -> {
            assertTrue(i > 0);
        });
    }

    @Test
    public void testMap() {
        var response = this.numbersList.stream().map(square);
        response.forEach((i) -> {
            assertTrue(i >= 0);
        });
    }

    @Test
    public void testSort() {
        var response = this.numbersList.stream().sorted(sorter);
        response.forEach((i) -> {
            assertTrue((i - valueBefore) > 0);
            valueBefore = i;
        });
    }

    @Test
    public void test1() {
        this.numbersList.stream().filter(filter).map(square).sorted(sorter).forEach(action);
    }

    @Test
    public void test2() {
        // シーケンシャルに処理を行う
        numbers2.filter((i) -> {
            return i % 3 == 0;
        }).forEach((i) -> {
            response = i + i / 2 + i / 3 + i / 4 + i / 5;
        });
    }

    @Test
    public void test3() {
        // パラレルに処理を行う
        numbers2.parallel().filter((i) -> {
            return i % 3 == 0;
        }).forEach((i) -> {
            response = i + i / 2 + i / 3 + i / 4 + i / 5;
        });
    }

    @Test
    public void test4() {
        // 数値の合計を取得する
        var response = numbers3.filter((i) -> {
            return i % 10 == 0;
        }).sum();

        assertEquals(50500, response);
    }
}
