package util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class C_SwapTest {

    int[] array;

    @BeforeEach
    void resetArray(){
        array = new int[]{99, 88, 77, 66, 55, 44, 33, 22, 11, 10};
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    void testMergeExceptions(int to){
        int from = 0;
        int[][] results = switch (to){
            case 1 -> new int[][]{{88}, {99}};
            case 2 -> new int[][]{{99, 77}, {88}};
            case 3 -> new int[][]{{99, 77}, {88, 66}};
            case 4 -> new int[][]{{99, 88, 66}, {77, 55}};
            case 5 -> new int[][]{{99, 88, 66}, {77, 55, 44}};
            case 6 -> new int[][]{{99, 88, 77, 55}, {66, 44, 33}};
            case 7 -> new int[][]{{99, 88, 77, 55}, {66, 44, 33, 22}};
            case 8 -> new int[][]{{99, 88, 77, 66, 44}, {55, 33, 22, 11}};
            case 9 -> new int[][]{{99, 88, 77, 66, 44}, {55, 33, 22, 11, 10}};
            default -> throw new IllegalStateException("Unexpected last: " + to);
        };
        int middle = Array.getMiddle(from, to);
        Array.strictSwap(array, from, middle, to);
        int[] actualLeft = Array.getLeftPart(array, from, middle, to);
        int[] actualRight = Array.getRightPart(array, from, middle, to);
        int[] expectedLeft = results[0];
        int[] expectedRight = results[1];
        assertArrayEquals(expectedLeft, actualLeft);
        assertArrayEquals(expectedRight, actualRight);
    }
}
