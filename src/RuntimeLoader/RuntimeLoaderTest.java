package RuntimeLoader;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RuntimeLoaderTest {

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
    void testTest01Method() {
        try {
            var myLoader = new RuntimeLoader();
            myLoader.add("Sample01.class", "bin/RuntimeLoader/Sample01.class");

            var myClass = myLoader.findClass("Sample01.class");
            var myMethod = myClass.getMethod("Test01", int.class);
            var myConstructor = myClass.getConstructor();
            var instance = myConstructor.newInstance();

            // RuntimeLoader経由でメソッドを実行する
            Object[] argsMethod = { 1 };
            var response = (boolean) myMethod.invoke(instance, argsMethod);

            System.out.println("response=" + response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testTest02Method() {
        try {
            var myLoader = new RuntimeLoader();
            myLoader.add("Sample01.class", "bin/RuntimeLoader/Sample01.class");

            var myClass = myLoader.findClass("Sample01.class");
            Class<?>[] typesMethod = { int.class, String.class };
            var myMethod = myClass.getMethod("Test02", typesMethod);
            Class<?>[] typesConstructor = { int.class };
            var myConstructor = myClass.getConstructor(typesConstructor);
            Object[] argsConstructor = { 1 };
            var instance = myConstructor.newInstance(argsConstructor);

            // RuntimeLoader経由でメソッドを実行する
            Object[] argsMethod = { 1, "Call 1" };
            var response = (int) myMethod.invoke(instance, argsMethod);

            System.out.println("response=" + response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetInstance1() {
        try {
            var myLoader = new RuntimeLoader();
            myLoader.add("Sample01.class", "bin/RuntimeLoader/Sample01.class");

            var response = myLoader.getInstance("Sample01.class", null, null);
            assertEquals("class RuntimeLoader.Sample01", response.instance.getClass().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetInstance2() {
        try {
            var myLoader = new RuntimeLoader();
            myLoader.add("Sample01.class", "bin/RuntimeLoader/Sample01.class");

            Class<?>[] typesConstructor = { int.class };
            Object[] argsConstructor = { 1, };

            var response = myLoader.getInstance("Sample01.class", typesConstructor, argsConstructor);
            assertEquals("class RuntimeLoader.Sample01", response.instance.getClass().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testInvoke1() {
        try {
            var myLoader = new RuntimeLoader();
            myLoader.add("Sample01.class", "bin/RuntimeLoader/Sample01.class");

            var runtimeResponse = myLoader.getInstance("Sample01.class", null, null);
            assertEquals("class RuntimeLoader.Sample01", runtimeResponse.instance.getClass().toString());

            Class<?>[] typesMethod = { int.class };
            Object[] argsMethod = { 1 };
            var response = myLoader.invoke(runtimeResponse, "Sample01.class", "Test01", typesMethod, argsMethod);

            assertEquals(false, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testInvoke2() {
        try {
            var myLoader = new RuntimeLoader();
            myLoader.add("Sample01.class", "bin/RuntimeLoader/Sample01.class");

            var runtimeResponse = myLoader.getInstance("Sample01.class", null, null);
            assertEquals("class RuntimeLoader.Sample01", runtimeResponse.instance.getClass().toString());

            Class<?>[] typesMethod = { int.class, String.class };
            Object[] argsMethod = { 3, "Call 3" };
            var response = myLoader.invoke(runtimeResponse, "Sample01.class", "Test02", typesMethod, argsMethod);

            assertEquals(1, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
