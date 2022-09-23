package pl.cinema.objects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class CinemaSeatTest {

    @ParameterizedTest
    @DisplayName("testGetPrice()")
    @CsvSource({"1, 2, 10", "2, 9, 10", "4, 1, 10", "5, 5, 8", "6, 9, 8", "9, 9, 8"})
    public void testGetPrice(int row, int column, int expected) {
        CinemaSeat cinemaSeat = new CinemaSeat(row, column);
        int result = cinemaSeat.getPrice();
        assertEquals(result, expected);
    }
}