package util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class A_DefaultsTest {

    @Test
    void testDefaults(){
        String[] goodArgs = new String[]{"123", "456", "789"};
        int[] array = Defaults.getDefaultArray(goodArgs);
        assertEquals(3, array.length);
        assertEquals(123, array[0]);
        assertEquals(456, array[1]);
        assertEquals(789, array[2]);
        String[] badArgs = new String[]{"123", "456", "abc"};
        array = Defaults.getDefaultArray(badArgs);
        assertEquals(Defaults.DEFAULT_ARRAY_LENGTH, array.length);
        String[] shortArgs = new String[]{"123"};
        array = Defaults.getDefaultArray(shortArgs);
        assertEquals(Defaults.DEFAULT_ARRAY_LENGTH, array.length);
        String[] noArgs = new String[]{};
        array = Defaults.getDefaultArray(noArgs);
        assertEquals(Defaults.DEFAULT_ARRAY_LENGTH, array.length);
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int value : array) {
            min = Math.min(min, value);
            max = Math.max(max, value);
        }
        assertTrue(min >= Defaults.DEFAULT_MIN_VALUE);
        assertTrue(max <= Defaults.DEFAULT_MAX_VALUE);
    }
}
