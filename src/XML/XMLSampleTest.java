package XML;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Exception.ApplicationException;

class XMLSampleTest extends XMLSample {
    private List<XmlData> data = null;

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
    void testRead() {
        try {
            data = this.read("src/XML/sample.xml");
            for (var item : data) {
                System.out.println("Name:" + item.Name);
                System.out.println("Age:" + item.Age);
                System.out.println("Interest:" + item.Interest);
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testWrite() {
        try {
            this.write(this.read("src/XML/sample.xml"));
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
}
