package Annotation;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Exception.ApplicationException;

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
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testInitializeFailed() {
        try {
            var logicDirectory = "/home/bin/Logics";
            logicDrive.initialize(logicDirectory);
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testCallFunction() {
        // TODO:途中
        logicDrive.callFunction(1, 1);
    }
}
