package Database;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Database.Bean.SampleTable1Bean;

class SampleTable1Test extends SampleTable1 {

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
    void testReadData() {
        try {
            List<SampleTable1Bean> response = this.readAllTable();
            // var response = this.readAllTable(); // なぜかエラー
            assertTrue(response.size() > 0);

            response.stream().forEach((row) -> {
                var message = String.format("%04d %-30s %-10s %-10s %10s %10s", row.getNumber(), row.getLoginID(),
                        row.getFamilyName(), row.getFirstName(), row.getCreateDateTime().toString(),
                        row.getUpdateDateTime().toString());
                System.out.println(message);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testWriteData() {
        try {
            this.updateSomeData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
