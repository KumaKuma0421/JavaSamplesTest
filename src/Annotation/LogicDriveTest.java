package Annotation;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LogicDriveTest {

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
    void testInitialize() {
        try {
            var target = new LogicDrive();
            var logicDirectory = "/home/user01/workspace1/JavaSamples/bin/Annotation/Logics";
            target.initialize(logicDirectory);
        } catch (Exception e) {
            // TODO:future implement.
        }
    }

}
