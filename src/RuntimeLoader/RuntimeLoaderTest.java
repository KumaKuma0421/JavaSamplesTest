package RuntimeLoader;

import static org.junit.Assert.assertEquals;

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
    void testAdd() {
        int addCount = 0;

        try {
            myLoader.add("Sample01.class", "bin/RuntimeLoader/Sample01.class");
            addCount++;
            myLoader.add("Sample02.class", "bin/RuntimeLoader/Sample02.class");
            addCount++;
            myLoader.add("Sample01.class", "bin/RuntimeLoader/Sample01.class");
            addCount++;
            myLoader.add("Sample02.class", "bin/RuntimeLoader/Sample02.class");
            addCount++;
        } catch (IllegalArgumentException e) {
            assertEquals("多重登録になりました。", e.getMessage());
            assertEquals(2, addCount);
        }
    }

    @Test
    void testGetInstance1() {
        try {
            myLoader.add("Sample01.class", "bin/RuntimeLoader/Sample01.class");
            myLoader.add("Sample02.class", "bin/RuntimeLoader/Sample02.class");

            var response = myLoader.getInstance("Sample01.class", null, null);
            assertEquals("class RuntimeLoader.Sample01", response.instance.getClass().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetInstance2() {
        try {
            myLoader.add("Sample01.class", "bin/RuntimeLoader/Sample01.class");
            myLoader.add("Sample02.class", "bin/RuntimeLoader/Sample02.class");

            Class<?>[] typesConstructor = { int.class };
            Object[] argsConstructor = { 1, };

            var response = myLoader.getInstance("Sample02.class", typesConstructor, argsConstructor);
            assertEquals("class RuntimeLoader.Sample02", response.instance.getClass().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testInvoke1() {
        try {
            myLoader.add("Sample01.class", "bin/RuntimeLoader/Sample01.class");
            myLoader.add("Sample02.class", "bin/RuntimeLoader/Sample02.class");

            var runtimeResponse = myLoader.getInstance("Sample01.class", null, null);
            assertEquals("class RuntimeLoader.Sample01", runtimeResponse.instance.getClass().toString());

            Class<?>[] typesMethod = { int.class };

            for (int i = 0; i < 100; i++) {
                Object[] argsMethod = { i };
                var response = myLoader.invoke(runtimeResponse, "Sample01.class", "Test01", typesMethod, argsMethod);
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
            myLoader.add("Sample01.class", "bin/RuntimeLoader/Sample01.class");
            myLoader.add("Sample02.class", "bin/RuntimeLoader/Sample02.class");

            var runtimeResponse = myLoader.getInstance("Sample02.class", null, null);
            assertEquals("class RuntimeLoader.Sample02", runtimeResponse.instance.getClass().toString());

            Class<?>[] typesMethod = { int.class, String.class };
            for (int i = 0; i < 100; i++) {
                String message = String.format("Call %d", i);
                Object[] argsMethod = { i, message };
                var response = myLoader.invoke(runtimeResponse, "Sample02.class", "Test02", typesMethod, argsMethod);
                assertEquals(i + 1, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testInvokeSelf01() {
        try {
            myLoader.add("Sample01.class", "bin/RuntimeLoader/Sample01.class");
            myLoader.add("Sample02.class", "bin/RuntimeLoader/Sample02.class");

            var myClass = myLoader.findClass("Sample01.class");
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
            myLoader.add("Sample01.class", "bin/RuntimeLoader/Sample01.class");
            myLoader.add("Sample02.class", "bin/RuntimeLoader/Sample02.class");

            var myClass = myLoader.findClass("Sample02.class");
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
