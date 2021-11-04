package util;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.PrimitiveIterator;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Проверка метода нахождения \"середины\".")
public class B_MiddleTest {

    @RepeatedTest(value=10)
    @DisplayName("Случайные границы:")
    void testRandomBounds(){
        int[] bounds = getRandomBounds();
        assertEquals(2, bounds.length);
        int first = bounds[0];
        int second = bounds[1];
        assertTrue(first < second);
        assertTrue(first >= Defaults.MIN_BOUND);
        assertTrue(second <= MAX_BOUNDS);
    }

    @RepeatedTest(value=10)
    @DisplayName("Середина в случайных границах:")
    void testRoundDownMiddle() {
        int[] bounds = getRandomBounds();
        int from = bounds[0];
        int to = bounds[1];
        int middle = Array.getMiddle(from, to);
        int half = middle - from;
        int toViaFromAndHalf = from + half * 2;
        if((to - from) % 2 > 0) toViaFromAndHalf++; // при нечетной длине массива правая часть короче на 1.
        assertEquals(to, toViaFromAndHalf);
    }

    @Test
    @DisplayName("Частные случаи:")
    void testExamples(){
        int from = 0;
        int to = 1;
        int middle = Array.getMiddle(from, to);
        assertEquals(0, middle);
        to = 2;
        middle = Array.getMiddle(from, to);
        assertEquals(1, middle);
        to = 3;
        middle = Array.getMiddle(from, to);
        assertEquals(1, middle);
        to = 4;
        middle = Array.getMiddle(from, to);
        assertEquals(2, middle);
        to = 5;
        middle = Array.getMiddle(from, to);
        assertEquals(2, middle);
    }
    @Test
    @DisplayName("Исключения:")
    void testExceptions() {
        assertThrows(ArithmeticException.class, () -> Array.getMiddle(-1, 0));
        assertThrows(ArithmeticException.class, () -> Array.getMiddle(1, 0));
        assertThrows(ArithmeticException.class, () -> Array.getMiddle(0, 0));
        assertThrows(ArithmeticException.class, () -> Array.getMiddle(1, 1));
        assertThrows(ArithmeticException.class, () -> Array.getMiddle(Defaults.MIN_BOUND - 1, MAX_BOUNDS));
        assertThrows(ArithmeticException.class, () -> Array.getMiddle(Defaults.MIN_BOUND, MAX_BOUNDS + 1));
        assertThrows(ArithmeticException.class, () -> Array.getMiddle(MAX_BOUNDS, Defaults.MIN_BOUND));
    }

    private static final int MAX_BOUNDS = Defaults.getMaxBound();

    private static final PrimitiveIterator.OfInt randomIntsGenerator =
            new Random().ints(Defaults.MIN_BOUND, MAX_BOUNDS).iterator();

    private static int[] getRandomBounds() {
        int first = randomIntsGenerator.nextInt();
        int second = randomIntsGenerator.nextInt();
        while(first == second){
            first = randomIntsGenerator.nextInt();
            second = randomIntsGenerator.nextInt();
        }
        return new int[]{
                Math.min(first, second),
                Math.max(first, second)
        };
    }
}
