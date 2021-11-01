import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@DisplayName("Демонстрация фрагментов массива.")
public class SplitTest {
    static final int[] array = new int[] {100, 111, 122, 133, 144, 155, 166, 177, 188, 199};

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8})
    @DisplayName("Подмассивы слева.")
    void testFromLeftExamples(int to){
        int from = 0;
        int[] test = switch(to){
            case 1 -> new int[] {100};
            case 2, 3 -> new int[] {100, 111};
            case 4, 5 -> new int[] {100, 111, 122};
            case 6, 7 -> new int[] {100, 111, 122, 133};
            case 8, 9 -> new int[] {100, 111, 122, 133, 144};
            default -> throw new IllegalStateException("Unexpected value: " + to);
        };
        assertArrayEquals(test, splitLeft(from, to));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    @DisplayName("Подмассивы справа.")
    void testFromRightExamples(int to){
        int from = 0;
        int[] test = switch(to){
            case 1 -> new int[] {111};
            case 2 -> new int[] {122};
            case 3 -> new int[] {122, 133};
            case 4 -> new int[] {133, 144};
            case 5 -> new int[] {133, 144, 155};
            case 6 -> new int[] {144, 155, 166};
            case 7 -> new int[] {144, 155, 166, 177};
            case 8 -> new int[] {155, 166, 177, 188};
            case 9 -> new int[] {155, 166, 177, 188, 199};
            default -> throw new IllegalStateException("Unexpected value: " + to);
        };
        assertArrayEquals(test, splitRight(from, to));
    }

    private static int[] splitLeft(int from, int to){
        return split(from, to, false);
    }

    private static int[] splitRight(int from, int to){
        return split(from, to, true);
    }

    private static int[] split(int from, int  to, boolean right){
        int middle = Main.getRoundDownMiddle(from, to);
        int first = right ? middle + 1 : from;
        int last = right ? to : middle;
        int[] result = new int[right ? to - middle : middle - from + 1];
        if (last + 1 - first >= 0) System.arraycopy(array, first, result, 0, result.length);
        return result;
    }

}
