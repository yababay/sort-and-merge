package util;

public class Print {

    public static void printArrayAfter(int[] array){
        String message = "Массив после сортировки";
        printArray(array, message);
    }

    public static void printArrayCurrent(int[] array){
        String message = "Текущее состояние массива";
        printArray(array, message);
    }

    public static void printArrayBefore(int[] array){
        String message = "Исходное состояние массива";
        printArray(array, message);
    }
    private static void printArray(int[] array, String description){
        System.out.print(description + ": ");
        for (int i : array) System.out.print(i + " ");
        System.out.println();
    }
}
