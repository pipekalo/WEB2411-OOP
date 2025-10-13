package edu.chdtu.web2411.poliakov.math;

import java.util.ArrayList;
import java.util.HashSet;

public class TasksMath {

    public int sumArray(ArrayList<Double> nums) {
        int result = 0;
        for (int i = 0; i < nums.size(); i++) {
            result += nums.get(i);
        }
        return result;
    }

    public int arithmeticMean(ArrayList<Double> nums) {
        int sum = this.sumArray(nums);
        return sum / nums.size();
    }

    public ArrayList<Integer> foundMinAndMax(ArrayList<Integer> nums) {
        ArrayList<Integer> result = new ArrayList<>();
        int min = nums.get(0), max = 0;

        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) > max) {
                max = nums.get(i);
            } else if (min > nums.get(i)) {
                min = nums.get(i);
            }
        }

        result.add(max);
        result.add(min);

        return result;
    }

    public ArrayList<Integer> isPrimeNumbers(ArrayList<Integer> nums) {
        ArrayList<Integer> result = new ArrayList<>();
        boolean isPrime = false;
        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) < 2) continue;
            isPrime = true;
            for (int j = 2; j <= Math.sqrt(nums.get(i)); j++) {
                if (nums.get(i) % j == 0) {
                    isPrime = false;
                    break;
                }
                ;
            }
            if (isPrime) {
                result.add(nums.get(i));
            }
        }
        return result;
    }

    public int factorial(int a) {
        if (a == 0) {
            return 1;
        }
        if (a == 1) {
            return a;
        }
        return a * factorial(--a);
    }


    public ArrayList<Integer> fibonacciList(int limit) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            result.add(fibonacci(i));
        }
        return result;
    }

    public int fibonacci(int m) {
        if (m == 0) {
            return 0;
        }
        if (m == 1) {
            return 1;
        }
        return fibonacci(m - 1) + fibonacci(m - 2);
    }

    public HashSet<Integer> duplicateResolution(ArrayList<Integer> nums) {
        HashSet<Integer> result = new HashSet<>();
        for (int i = 0; i < nums.size(); i++) {
            int prev = nums.get(i);
            for (int j = 0; j < nums.size(); j++) {
                if (prev == nums.get(j) || result.contains(nums.get(j))) continue;
                result.add(nums.get(j));
            }
        }
        return result;
    }

    public int[] bubbleSort(int[] nums) {
        int length = nums.length;
        int countIterations = 0;
        while (length != 0) {
            int index = 0;
            for (int i = 1; i < length; i++) {
                if (nums[i - 1] > nums[i]) {
                    int term = nums[i - 1];
                    nums[i - 1] = nums[i];
                    nums[i] = term;
                    index = i;
                }
                countIterations++;
            }
            length = index;
        }
        System.out.println(countIterations);
        return nums;
    }

    public ArrayList<Integer> findEvenAndOddNums(ArrayList<Integer> nums) {
        int even = 0, odd = 0;
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.size(); i++) {
            if(nums.get(i) % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }

        result.add(even);
        result.add(odd);

        return result;
    }
}
