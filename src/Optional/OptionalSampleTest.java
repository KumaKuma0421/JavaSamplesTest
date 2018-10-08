package Optional;

import java.util.function.BiConsumer;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OptionalSampleTest extends OptionalSample {

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
        this.generateData();
    }

    @AfterEach
    void tearDown() throws Exception {
        numStrings = null;
    }

    @Test
    void testTest1() {
        BiConsumer<Integer, String> action = (i, s) -> {
            // 利用者はOptionalを介してnullの存在の対処を行います。
            var response = this.find(i).orElse("Default");
            System.out.println(response);
        };
        numStrings.forEach(action);
    }

}
