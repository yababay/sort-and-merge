public class Main {

    static final int MIN_BOUND_VALUE = 0;
    static final int MAX_BOUND_VALUE = 1 + Integer.MAX_VALUE / 2; // Because exclusive.

    public static void main(String[] args) {
        int f = 0;
        int t = 1;
        int m = getRoundDownMiddle(f, t);
        String debugOutput = "%s %s %s".formatted(f, m, t);
        System.out.println(debugOutput);
    }

    static int getRoundDownMiddle(int from, int to) throws ArithmeticException {
        if(!(from < to)) throw new ArithmeticException("Начальный индекс должен быть меньше конечного.");
        if(from < MIN_BOUND_VALUE) throw new ArithmeticException("Индекс массива не может быть меньше нуля.");
        if(to > MAX_BOUND_VALUE) throw new ArithmeticException("Давайте будем реалистами.");
        double both = to + from;
        return (int) Math.floor(both / 2);
    }
}
