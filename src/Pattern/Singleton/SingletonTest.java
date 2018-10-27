package Pattern.Singleton;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SingletonTest {

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testInstance() {
        // var singleton = new Singleton(); コンストラクタは不可視です。
        final var singleton1 = Singleton.Instance(); // 初めてインスタンスが作成される
        final var singleton2 = Singleton.Instance(); // 同じインスタンを得るはず
        assertEquals(singleton1, singleton2);
    }
}
