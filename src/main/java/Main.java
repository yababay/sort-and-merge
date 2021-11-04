import static util.Array.*;
import static util.Print.*;
import static util.Defaults.*;

public class Main {

    private static final boolean DEBUG = false;
    private static final boolean VERBOSE = false;

    public static void main(String[] args) {
        try {
            int[] array = getDefaultArray(args);
            printArrayBefore(array);
            sort(array, 0, array.length - 1);
            printArrayAfter(array);
        }
        catch(ArithmeticException arEx){
            System.out.println(arEx.getMessage());
        }
    }

    public static void sort(int[] array, int from, int to) throws ArithmeticException {
        if(!(from < to)) return;
        int middle = getMiddle(from, to);
        sort(array, from, middle);
        sort(array, middle + 1, to);
        merge(array, from, middle, to);
    }

    public static void merge(int[] array, int from, int middle, int to) throws ArithmeticException {
        if(DEBUG) strictSwap(array, from, middle, to); // для тестирования, без рекурсии.
        else if(instantSwap(array, from, middle, to)){
            if(VERBOSE) printArrayCurrent(array);
            sort(array, from, to);
        }
    }

}
