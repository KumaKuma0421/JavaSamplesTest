package Annotation;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LogicDriveTest {
    LogicDrive logicDrive;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
        logicDrive = new LogicDrive();
    }

    @AfterEach
    void tearDown() throws Exception {
        logicDrive = null;
    }

    @Test
    void testInitialize() {
        try {
            var logicDirectory = "/home/user01/workspace1/JavaSamples/bin/Annotation/Logics";
            logicDrive.initialize(logicDirectory);
        } catch (Exception e) {
            // TODO:future implement.
        }
    }

    @Test
    void testCallFunction() {

    }
}
