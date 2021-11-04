package util;

import java.util.PrimitiveIterator;
import java.util.Random;

public class Defaults {

    public static final int MIN_BOUND = 0;
    private static int MAX_BOUND = Integer.MAX_VALUE / 2; // Будет установлен при первом вызове метода sort.
    private static boolean isMaxBoundSet = false;

    public static void setMaxBound(int[] array){
        if(isMaxBoundSet) return;
        MAX_BOUND = array.length - 1;
        isMaxBoundSet = true;
    }

    public static int getMaxBound(){
        return MAX_BOUND;
    }

    public static final int DEFAULT_MIN_VALUE = -100;
    public static final int DEFAULT_MAX_VALUE =  100;
    public static final int DEFAULT_ARRAY_LENGTH = 10;

    private static final PrimitiveIterator.OfInt randomIntsGenerator =
            new Random().ints(DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE).iterator();

    public static int[] getDefaultArray(String[] args) {
        if(args.length < 2) return getDefaultArray();
        int[] array = new int[args.length];
        int count = 0;
        try{
            for(String s : args) array[count++] = Integer.parseInt(s);
            return array;
        }
        catch (NumberFormatException nfEx) {
            System.out.println("Не все аргументы удалось преобразовать в числа.");
            return getDefaultArray();
        }
    }

    private static int[] getDefaultArray() {
        int[] array = new int[DEFAULT_ARRAY_LENGTH];
        for (int i = 0; i < array.length; i++) array[i] = randomIntsGenerator.nextInt();
        return array;
    }
}
