package Annotation;

import static org.junit.Assert.assertEquals;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Annotation.Logics.GetSystemInformation;
import Annotation.Logics.LogicBase;

class AnnotationTest {

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
    public void testLogicBase() {
        assertEquals(1, 1);
        try {
            Class<LogicBase> theClass = LogicBase.class;

            Method[] methods = theClass.getMethods();
            for (Method method : methods) {
                System.out.println("Method Name:" + method.getName());
                for (Annotation annotation : method.getDeclaredAnnotations()) {
                    System.out.println(" annotation:" + annotation);
                }
            }

            Object[] params = new String[] { "First", "Second", "Third" };
            LogicBase instance = theClass.getConstructor().newInstance();
            instance.doAction(params);

        } catch (SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetSystemInformation() {
        try {
            Class<? extends LogicBase> theClass = GetSystemInformation.class;

            Method[] methods = theClass.getMethods();
            for (Method method : methods) {
                System.out.println("Method Name:" + method.getName());
                for (Annotation annotation : method.getDeclaredAnnotations()) {
                    System.out.println(" annotation:" + annotation);
                }
            }

            Object[] params = new String[] { "First", "Second", "Third" };
            LogicBase instance = theClass.getConstructor().newInstance();
            instance.doAction(params);
        } catch (SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
