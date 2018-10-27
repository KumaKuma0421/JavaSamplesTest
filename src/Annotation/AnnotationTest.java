package Annotation;

import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Annotation.Logics.GetSystemInformation;
import Annotation.Logics.GetUserInformation;
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
        try {
            var theClass = LogicBase.class;
            for (var classAnnotation : theClass.getAnnotations()) {
                System.out.println("Class Annotation:" + classAnnotation);
                if (classAnnotation.annotationType() == RuntimeDescriptor.class) {
                    var targetAnnotation = (RuntimeDescriptor) classAnnotation;
                    var value = targetAnnotation.value();
                    var description = targetAnnotation.description();
                    System.out.println(" value:" + value + " description:" + description);
                }
            }

            for (var method : theClass.getMethods()) {
                System.out.println(" Method Name:" + method.getName());
                for (var methodAnnotation : method.getDeclaredAnnotations()) {
                    System.out.println("  Annotation:" + methodAnnotation);
                    if (methodAnnotation.annotationType() == InjectionInfo.class) {
                        var targetAnnotation = (InjectionInfo) methodAnnotation;
                        var stream = targetAnnotation.function();
                        var function = targetAnnotation.function();
                        System.out.println("   stream:" + stream + " function:" + function);
                    }
                }
            }

            var params = new String[] { "First", "Second", "Third" };
            var instance = theClass.getConstructor().newInstance();
            instance.doAction(params);

        } catch (SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetSystemInformation() {
        try {
            var theClass = GetSystemInformation.class;

            var methods = theClass.getMethods();
            for (var method : methods) {
                System.out.println("Method Name:" + method.getName());
                for (var annotation : method.getDeclaredAnnotations()) {
                    System.out.println(" annotation:" + annotation);
                }
            }

            var params = new String[] { "First", "Second", "Third" };
            var instance = theClass.getConstructor().newInstance();
            instance.action(params);
        } catch (SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetUserInformation() {
        try {
            var theClass = GetUserInformation.class;

            var methods = theClass.getMethods();
            for (var method : methods) {
                System.out.println("Method Name:" + method.getName());
                for (var annotation : method.getDeclaredAnnotations()) {
                    System.out.println(" annotation:" + annotation);
                }
            }

            var params = new String[] { "First", "Second", "Third" };
            var instance = theClass.getConstructor().newInstance();
            instance.doAction(params);
        } catch (SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
