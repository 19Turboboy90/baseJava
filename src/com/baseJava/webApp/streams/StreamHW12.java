package com.baseJava.webApp.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamHW12 {
//1-реализовать метод через стрим int minValue(int[] values).
//Метод принимает массив цифр от 1 до 9, надо выбрать уникальные и вернуть минимально возможное число, составленное из этих
// уникальных цифр. Не использовать преобразование в строку и обратно. Например {1,2,3,3,2,3} вернет 123, а {9,8} вернет 89

//2-реализовать метод List<Integer> oddOrEven(List<Integer> integers) если сумма всех чисел нечетная - удалить все нечетные,
// если четная - удалить все четные. Сложность алгоритма должна быть O(N). Optional - решение в один стрим.

    private static int[] values_1 = {1, 2, 3, 3, 2, 3};
    private static int[] values_2 = {9, 8};

    private static List<Integer> listNumber_1 = Arrays.asList(1, 2, 3, 3, 2, 3);
    private static List<Integer> listNumber_2 = Arrays.asList(1, 2, 3, 3, 2, 2);

    public static void main(String[] args) {
        System.out.println("Min values " + minValue(values_1));
        System.out.println("Min values " + minValue(values_2));
        System.out.println("List of odd numbers" + oddOrEven(listNumber_1));
        System.out.println("List of even numbers" + oddOrEven(listNumber_2));
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values)
                .sorted()
                .distinct()
                .reduce(0, (a, e) -> a * 10 + e);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        int sumNumbers = integers.stream()
                .mapToInt(n -> n)
                .sum();
        if (sumNumbers % 2 == 0) {
            List<Integer> sumOfEvenNumbers = integers.stream().filter(number -> number % 2 == 1).collect(Collectors.toList());
            return sumOfEvenNumbers;
        } else {
            List<Integer> sumOfOddNumbers = integers.stream().filter(number -> number % 2 == 0).collect(Collectors.toList());
            return sumOfOddNumbers;
        }
    }
}
