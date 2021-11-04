package util;

import static util.Defaults.setMaxBound;

public class Array {

    public static int getMiddle(int from, int to) throws ArithmeticException {
        checkFromTo(from, to);
        double both = to + from;
        return (int) Math.floor(both / 2);
    }

    public static void strictSwap(int[] array, int from, int middle, int to) throws ArithmeticException {
        setMaxBound(array);
        checkFromMiddleTo(from, middle, to);
        instantSwap(array, from, middle, to);
    }

    public static boolean instantSwap(int[] array, int from, int middle, int to){
        int maxOfLeft = array[from];
        int count = from;
        while(count <= middle ||  count <= to  && array[count] >= maxOfLeft){
            if(count <= middle) maxOfLeft = Math.max(maxOfLeft, array[count]);
            count++;
        }
        if(count > to) return false;
        int tmp = array[middle];
        array[middle] = array[count];
        array[count] = tmp;
        return true;
    }

    public static int[] getLeftPart(int[] array, int from, int middle, int to){
        return getPart(array, from, middle, to, false);
    }

    public static int[] getRightPart(int[] array, int from, int middle, int to){
        return getPart(array, from, middle, to, true);
    }

    private static int[] getPart(int[] array, int from, int middle, int to, boolean right){
        int first = right ? middle + 1 : from;
        int last = right ? to : middle;
        int length = last - first + 1;
        int[] result = new int[length];
        System.arraycopy(array, first, result, 0, length);
        return result;
    }

    private static void checkFromTo(int from, int to) throws ArithmeticException {
        if(!(from < to)) throw new ArithmeticException("Начальный индекс должен быть меньше конечного.");
        if(from < Defaults.MIN_BOUND) throw new ArithmeticException("Индекс массива не может выходить за пределы границы.");
        int max = Defaults.getMaxBound();
        if(to > max) throw new ArithmeticException("Индекс массива не может быть больше %s.".formatted(max));
    }

    private static void checkFromMiddleTo(int from, int middle, int to) throws ArithmeticException {
        checkFromTo(from, to);
        int calculatedMiddle = getMiddle(from, to);
        if(calculatedMiddle != middle) throw new ArithmeticException("Некорректно вычислен серединный индекс.");
    }
}
