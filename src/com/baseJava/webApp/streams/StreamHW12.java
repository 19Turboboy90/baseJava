package com.baseJava.webApp.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamHW12 {
//1-����������� ����� ����� ����� int minValue(int[] values).
//����� ��������� ������ ���� �� 1 �� 9, ���� ������� ���������� � ������� ���������� ��������� �����, ������������ �� ����
// ���������� ����. �� ������������ �������������� � ������ � �������. �������� {1,2,3,3,2,3} ������ 123, � {9,8} ������ 89

//2-����������� ����� List<Integer> oddOrEven(List<Integer> integers) ���� ����� ���� ����� �������� - ������� ��� ��������,
// ���� ������ - ������� ��� ������. ��������� ��������� ������ ���� O(N). Optional - ������� � ���� �����.

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
