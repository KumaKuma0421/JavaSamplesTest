package RuntimeLoader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RuntimeLoaderTest {
    RuntimeLoader myLoader = null;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
        myLoader = new RuntimeLoader();
    }

    @AfterEach
    void tearDown() throws Exception {
        myLoader = null;
    }

    @Test
    void testAdd0() {
        try {
            myLoader.add("RuntimeLoader.Sample00", "bin/RuntimeLoader/Sample00.class");
        } catch (FileNotFoundException e) {
            // OK
        } catch (Exception e) {
            fail("見つかるはずがありません。");
        }
    }

    @Test
    void testAdd1() {
        int addCount = 0;

        try {
            myLoader.add("RuntimeLoader.Sample01", "bin/RuntimeLoader/Sample01.class");
            addCount++;
            myLoader.add("RuntimeLoader.Sample02", "bin/RuntimeLoader/Sample02.class");
            addCount++;
            myLoader.add("RuntimeLoader.Sample01", "bin/RuntimeLoader/Sample01.class");
            addCount++;
            myLoader.add("RuntimeLoader.Sample02", "bin/RuntimeLoader/Sample02.class");
            addCount++;
        } catch (IllegalArgumentException e) {
            assertEquals("多重登録になりました。", e.getMessage());
            assertEquals(2, addCount);
        } catch (IOException e) {

        }
    }

    @Test
    void testGetInstance1() {
        try {
            myLoader.add("RuntimeLoader.Sample01", "bin/RuntimeLoader/Sample01.class");
            myLoader.add("RuntimeLoader.Sample02", "bin/RuntimeLoader/Sample02.class");

            var response = myLoader.getInstance("RuntimeLoader.Sample01", null, null);
            assertEquals("class RuntimeLoader.Sample01", response.newInstance.getClass().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetInstance2() {
        try {
            myLoader.add("RuntimeLoader.Sample01", "bin/RuntimeLoader/Sample01.class");
            myLoader.add("RuntimeLoader.Sample02", "bin/RuntimeLoader/Sample02.class");

            Class<?>[] typesConstructor = { int.class };
            Object[] argsConstructor = { 1, };

            var response = myLoader.getInstance("RuntimeLoader.Sample02", typesConstructor, argsConstructor);
            assertEquals("class RuntimeLoader.Sample02", response.newInstance.getClass().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testInvoke1() {
        try {
            myLoader.add("RuntimeLoader.Sample01", "bin/RuntimeLoader/Sample01.class");
            myLoader.add("RuntimeLoader.Sample02", "bin/RuntimeLoader/Sample02.class");

            var runtimeResponse = myLoader.getInstance("RuntimeLoader.Sample01", null, null);
            assertEquals("class RuntimeLoader.Sample01", runtimeResponse.newInstance.getClass().toString());

            Class<?>[] typesMethod = { int.class };

            for (int i = 0; i < 100; i++) {
                Object[] argsMethod = { i };
                var response = myLoader.invoke(runtimeResponse, "Test01", typesMethod, argsMethod);
                if ((i % 2) == 0) {
                    assertEquals(true, response);
                } else {
                    assertEquals(false, response);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testInvoke2() {
        try {
            myLoader.add("RuntimeLoader.Sample01", "bin/RuntimeLoader/Sample01.class");
            myLoader.add("RuntimeLoader.Sample02", "bin/RuntimeLoader/Sample02.class");

            var runtimeResponse = myLoader.getInstance("RuntimeLoader.Sample02", null, null);
            assertEquals("class RuntimeLoader.Sample02", runtimeResponse.newInstance.getClass().toString());

            Class<?>[] typesMethod = { int.class, String.class };
            for (int i = 0; i < 100; i++) {
                String message = String.format("Call %d", i);
                Object[] argsMethod = { i, message };
                var response = myLoader.invoke(runtimeResponse, "Test02", typesMethod, argsMethod);
                assertEquals(i + 1, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testFindClass0() {
        Class<?> response1 = null;
        Class<?> response2 = null;
        try {
            myLoader.add("RuntimeLoader.Sample01", "bin/RuntimeLoader/Sample01.class");
            response1 = myLoader.findClass("RuntimeLoader.Sample01");
            response2 = myLoader.findClass("RuntimeLoader.Sample01");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(response1.getTypeName(), "RuntimeLoader.Sample01");
        assertEquals(response2.getTypeName(), "RuntimeLoader.Sample01");
        assertEquals(response1, response2);
    }

    @Test
    void testFindClass1() {
        Class<?> response1 = null;
        Class<?> response2 = null;
        try {
            myLoader.add("RuntimeLoader.Sample01", "bin/RuntimeLoader/Sample01.class");
            myLoader.add("RuntimeLoader.Sample02", "bin/RuntimeLoader/Sample02.class");
            response1 = myLoader.findClass("RuntimeLoader.Sample01");
            response2 = myLoader.findClass("RuntimeLoader.Sample02");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(response1.getTypeName(), "RuntimeLoader.Sample01");
        assertEquals(response2.getTypeName(), "RuntimeLoader.Sample02");
    }

    @Test
    void testFindClass2() {
        Class<?> response = null;
        try {
            myLoader.add("RuntimeLoader.Sample01", "bin/RuntimeLoader/Sample01.class");
            myLoader.add("RuntimeLoader.Sample02", "bin/RuntimeLoader/Sample02.class");
            response = myLoader.findClass("RuntimeLoader.Sample00");
            fail("見つかるはずがありません。");
        } catch (Exception e) {
            assertEquals(null, response);
        }
    }

    @Test
    void testFindByAnnotation() {
        Class<?> response = null;
        try {
            myLoader.add("Annotation.Logics.GetSystemInformation",
                    "/home/user01/workspace1/JavaSamples/bin/Annotation/Logics/GetSystemInformation.class");
            myLoader.add("Annotation.Logics.GetUserInformation",
                    "/home/user01/workspace1/JavaSamples/bin/Annotation/Logics/GetUserInformation.class");
            response = myLoader.findByAnnotation(1, 1);
            assertEquals(null, response);

            var runtime1 = myLoader.getInstance("Annotation.Logics.GetSystemInformation", null, null);
            assertEquals("class Annotation.Logics.GetSystemInformation", runtime1.newInstance.getClass().toString());
            var runtime2 = myLoader.getInstance("Annotation.Logics.GetUserInformation", null, null);
            assertEquals("class Annotation.Logics.GetUserInformation", runtime2.newInstance.getClass().toString());

            var result = myLoader.findByAnnotation(1, 1);
            assertEquals("class Annotation.Logics.GetSystemInformation", result.toString());

            var result2 = myLoader.findByAnnotation(1, 2);
            assertEquals("class Annotation.Logics.GetUserInformation", result2.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testInvokeSelf01() {
        try {
            myLoader.add("RuntimeLoader.Sample01", "bin/RuntimeLoader/Sample01.class");
            myLoader.add("RuntimeLoader.Sample02", "bin/RuntimeLoader/Sample02.class");

            var myClass = myLoader.findClass("RuntimeLoader.Sample01");
            var myMethod = myClass.getMethod("Test01", int.class);
            var myConstructor = myClass.getConstructor();
            var instance = myConstructor.newInstance();

            Object[] argsMethod = { 1 };
            var response = (boolean) myMethod.invoke(instance, argsMethod);
            assertEquals(false, response);

            System.out.println("response=" + response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testInvokeSelf02() {
        try {
            myLoader.add("RuntimeLoader.Sample01", "bin/RuntimeLoader/Sample01.class");
            myLoader.add("RuntimeLoader.Sample02", "bin/RuntimeLoader/Sample02.class");

            var myClass = myLoader.findClass("RuntimeLoader.Sample02");
            Class<?>[] typesMethod = { int.class, String.class };
            var myMethod = myClass.getMethod("Test02", typesMethod);
            Class<?>[] typesConstructor = { int.class };
            var myConstructor = myClass.getConstructor(typesConstructor);
            Object[] argsConstructor = { 1 };
            var instance = myConstructor.newInstance(argsConstructor);

            Object[] argsMethod = { 1, "Call 1" };
            var response = (int) myMethod.invoke(instance, argsMethod);
            assertEquals(2, response);

            System.out.println("response=" + response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
