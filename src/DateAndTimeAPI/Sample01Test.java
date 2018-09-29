package DateAndTimeAPI;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.stream.IntStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Sample01Test extends Sample01 {

    DateTimeFormatter basicFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

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

    void show(String message) {
        System.out.println(message);
    }

    @Test
    void testLocalDateTime() {
        var response = LocalDateTime.now().toString();
        show(response);
    }

    @Test
    void testOffsetDateTime() {
        var response = OffsetDateTime.now().toString();
        show(response);
    }

    @Test
    void testZonedDateTime() {
        var response = ZonedDateTime.now().toString();
        show(response);
    }

    @Test
    void testFormattedDateTime() {
        var loop = IntStream.rangeClosed(1, 10);
        loop.forEach((i) -> {
            try {
                var response = LocalDateTime.now();
                var formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
                var text = response.format(formatter);
                show(text);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    void testGetDayOfFirstOrLast() {
        var now = LocalDateTime.now();
        var firstDayOfMonth = now.with(TemporalAdjusters.firstDayOfMonth());
        var lastDayOfMonth = now.with(TemporalAdjusters.lastDayOfMonth());
        show(firstDayOfMonth.format(basicFormat));
        show(lastDayOfMonth.format(basicFormat));
    }

    @Test
    void testGetDuration() {
        var baseDateTime = LocalDateTime.of(1968, 3, 16, 20, 00, 00);
        var currentDateTime = LocalDateTime.of(2018, 9, 29, 21, 56, 00);
        var duration = this.getDuration(baseDateTime, currentDateTime);
        show("期間（日）：" + duration.toDays());
        show("期間（時）：" + duration.toHours());
    }

    @Test
    void testGetPeriod() {
        var baseDateTime = LocalDateTime.of(1968, 3, 16, 20, 00, 00);
        var currentDateTime = LocalDateTime.of(2018, 9, 29, 21, 56, 00);
        var period = this.getPeriod(baseDateTime, currentDateTime);
        show("期間（年）：" + period.getYears());
        show("期間（月）：" + period.getMonths());
        show("期間（日）：" + period.getDays());
    }
}
