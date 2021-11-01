import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Исследование рекурсии:")
public class RecursionCountTest {

    int countIn = 0;
    int countOut = 0;
    final int zero = 0;

    @BeforeEach
    void resetCounter(){
        countIn = 0;
        countOut = 0;
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 1234, 4567})
    @DisplayName("Сколько раз начиналась рекурсия (эмп.):")
    void checkCountsInEmpirically(int length){
        int expected = switch(length){
            case 1 -> 1;
            case 2 -> 3;
            case 3 -> 5;
            case 4 -> 7;
            case 5 -> 9;
            case 6 -> 11;
            case 1234 -> 1234 * 2 - 1;
            case 4567 -> 4567 * 2 - 1;
            default -> throw new IllegalStateException("Unexpected length: " + length);
        };
        split(new int[length], zero, length - 1);
        assertEquals(expected, countIn);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 1234, 4567})
    @DisplayName("Сколько раз закончилась рекурсия  (эмп.):")
    void checkCountsOutEmpirically(int length){
        int expected = switch(length){
            case 1 -> 0;
            case 2 -> 1;
            case 3 -> 2;
            case 4 -> 3;
            case 5 -> 4;
            case 6 -> 5;
            case 1234 -> 1234 - 1;
            case 4567 -> 4567 - 1;
            default -> throw new IllegalStateException("Unexpected length: " + length);
        };
        split(new int[length], zero, length - 1);
        assertEquals(expected, countOut);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 5, 6, 1234, 4567})
    @DisplayName("Сколько раз начиналась рекурсия (алг.):")
    void checkCountsOutAlgorithmically(int length){
        split(new int[length], zero, length - 1);
        assertEquals(length - 1, countOut);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 5, 6, 1234, 4567})
    @DisplayName("Сколько раз закончилась рекурсия (алг.):")
    void checkCountsInAlgorithmically(int length){
        split(new int[length], zero, length - 1);
        assertEquals(length * 2 - 1, countIn);
    }

    private void split(int[] array, int from, int to){
        countIn++;
        if(!(from < to)) return;
        int middle = Main.getRoundDownMiddle(from, to);
        split(array, from, middle);
        split(array, middle + 1, to);
        countOut++;
    }
}
