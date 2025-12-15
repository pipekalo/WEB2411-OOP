package edu.chdtu.web2411.victoria.lab1.services;

public class MathUtils {
    public static int sumArray(int[] numbers) {
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return sum;
    }
}