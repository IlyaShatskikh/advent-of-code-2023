package com.aoc.day.one;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.stream.Stream;

class TrebuchetTest {

    @Test
    void value142() {
        String input = """
                1abc2
                pqr3stu8vwx
                a1b2c3d4e5f
                treb7uchet
                """;

        int calibration = new Trebuchet().findCalibrationValue(input);
        Assertions.assertEquals(142, calibration);
    }

    @Test
    void part1() throws IOException {
        String input = new String(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("day1/calibration.txt")).readAllBytes());
        int calibrations = new Trebuchet().findCalibrationValue(input);
        Assertions.assertEquals(56108, calibrations);
    }

    @Test
    void value281() {
        String input = """
                two1nine
                eightwothree
                abcone2threexyz
                xtwone3four
                4nineeightseven2
                zoneight234
                7pqrstsixteen
                """;

        Trebuchet trebuchet = new Trebuchet();

        int calibration = input.lines().mapToInt(trebuchet::findCalibrationInLine).sum();
        Assertions.assertEquals(281, calibration);
    }

    @Test
    void part2() throws IOException {
        Trebuchet trebuchet = new Trebuchet();

        try (Stream<String> lines = Files.lines(Path.of("src/test/resources/day1/calibration-with-words.txt"))) {
            int sum = lines.mapToInt(trebuchet::findCalibrationInLine).sum();
            Assertions.assertEquals(55652, sum);
        }
    }
}