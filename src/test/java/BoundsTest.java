import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.PrimitiveIterator;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Проверка метода нахождения \"середины\".")
public class BoundsTest {

    @RepeatedTest(value=10)
    @DisplayName("Случайные границы:")
    void testRandomBounds(){
        int[] bounds = getRandomBounds();
        int first = bounds[0];
        int second = bounds[1];
        boolean firstIsLessThanSecond = first < second;
        boolean firstIsOk = first >= Main.MIN_BOUND_VALUE;
        boolean secondIsOk = second <= Main.MAX_BOUND_VALUE;
        int len = 2;
        assertEquals(len, bounds.length);
        assertTrue(firstIsLessThanSecond);
        assertTrue(firstIsOk);
        assertTrue(secondIsOk);
    }

    @RepeatedTest(value=10)
    @DisplayName("Середина в случайных границах:")
    void testRoundDownMiddle() {
        int[] bounds = getRandomBounds();
        int from = bounds[0];
        int to = bounds[1];
        int middle = Main.getRoundDownMiddle(from, to);
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
        int middle = Main.getRoundDownMiddle(from, to);
        assertEquals(0, middle);
        to = 2;
        middle = Main.getRoundDownMiddle(from, to);
        assertEquals(1, middle);
        to = 3;
        middle = Main.getRoundDownMiddle(from, to);
        assertEquals(1, middle);
        to = 4;
        middle = Main.getRoundDownMiddle(from, to);
        assertEquals(2, middle);
        to = 5;
        middle = Main.getRoundDownMiddle(from, to);
        assertEquals(2, middle);
    }

    @Test
    @DisplayName("Исключения:")
    void testExceptions() {
        assertThrows(ArithmeticException.class, () -> Main.getRoundDownMiddle(-1, 0));
        assertThrows(ArithmeticException.class, () -> Main.getRoundDownMiddle(1, 0));
        assertThrows(ArithmeticException.class, () -> Main.getRoundDownMiddle(0, 0));
        assertThrows(ArithmeticException.class, () -> Main.getRoundDownMiddle(1, 1));
        assertThrows(ArithmeticException.class, () -> Main.getRoundDownMiddle(Main.MIN_BOUND_VALUE - 1, Main.MAX_BOUND_VALUE));
        assertThrows(ArithmeticException.class, () -> Main.getRoundDownMiddle(Main.MIN_BOUND_VALUE, Main.MAX_BOUND_VALUE + 1));
        assertThrows(ArithmeticException.class, () -> Main.getRoundDownMiddle(Main.MAX_BOUND_VALUE, Main.MIN_BOUND_VALUE));
    }

    private static final PrimitiveIterator.OfInt randomIntsGenerator =
            new Random().ints(Main.MIN_BOUND_VALUE, Main.MAX_BOUND_VALUE).iterator();

    static int[] getRandomBounds() {
        int first = randomIntsGenerator.nextInt();
        int second = randomIntsGenerator.nextInt();
        while(first == second) second = randomIntsGenerator.nextInt();
        return new int[]{
                Math.min(first, second),
                Math.max(first, second)
        };
    }
}
