package com.aoc.day.one;

import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.IntStream.range;

public class Trebuchet {

    private final Map<String, Integer> nums = Map.of("one", 1, "two", 2, "three", 3,
            "four", 4, "five", 5, "six", 6,"seven", 7, "eight", 8,
            "nine", 9);

    public int findCalibrationValue(String input) {
        int res = 0;
        int l = 0;
        int r = 0;

        for (char curr: input.toCharArray()) {
            if (curr == '\n') {
                res += l * 10 + r;

                l = 0;
                r = 0;
                continue;
            }

            if (Character.isDigit(curr)) {
                int num = Character.getNumericValue(curr);
                if (l == 0) {
                    l = num;
                }
                r = num;
            }
        }

        return res;
    }

    public int findCalibrationInLine(String line) {
        return findDigit(line, range(0, line.length()), nums) * 10
                + findDigit(line, IntStream.iterate(line.length() - 1, i -> i >= 0, i -> i - 1), nums);
    }

    /**
     * Find first digit
     * @param line string value
     * @param range to iterate over the value (forward and backward)
     * @param nums map for spelled digits
     */
    private static int findDigit(String line, IntStream range, Map<String, Integer> nums) {
        for (int i : range.toArray()) {
            char curr = line.charAt(i);
            if (Character.isDigit(curr)) {
                return Character.getNumericValue(curr);
            }

            for (Map.Entry<String, Integer> num : nums.entrySet()) {
                if (line.startsWith(num.getKey(), i)) {
                    return num.getValue();
                }
            }
        }
        return 0;
    }
}
