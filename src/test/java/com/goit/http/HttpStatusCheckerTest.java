package com.goit.http;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class HttpStatusCheckerTest {

    @Test
    void getStatusImageAllExistedTest() {

        //Given
        Map<Integer, String> testCases = new HashMap<>();

        testCases.put(100, "https://http.cat/100.jpg");
        testCases.put(101, "https://http.cat/101.jpg");
        testCases.put(103, "https://http.cat/103.jpg");
        testCases.put(200, "https://http.cat/200.jpg");
        testCases.put(203, "https://http.cat/203.jpg");
        testCases.put(205, "https://http.cat/205.jpg");
        testCases.put(300, "https://http.cat/300.jpg");
        testCases.put(308, "https://http.cat/308.jpg");
        testCases.put(400, "https://http.cat/400.jpg");
        testCases.put(403, "https://http.cat/403.jpg");
        testCases.put(411, "https://http.cat/411.jpg");
        testCases.put(414, "https://http.cat/414.jpg");
        testCases.put(425, "https://http.cat/425.jpg");
        testCases.put(498, "https://http.cat/498.jpg");
        testCases.put(503, "https://http.cat/503.jpg");
        testCases.put(521, "https://http.cat/521.jpg");
        testCases.put(599, "https://http.cat/599.jpg");

        //When & Then
        testCases.forEach((actual, expected) -> {
            Assertions.assertEquals(expected,
                    new HttpStatusChecker().getStatusImage(actual));
        });
    }

    @Test
    void getStatusImageUnexistedTest() {

        //Given
        List<Integer> codes = List
                .of(1, 55, 118, 256, 390, 470, 534, 3000, 10000, 36778128);

        //When & Then
        for (Integer code : codes) {
            Assertions.assertThrows(RuntimeException.class,
                    () -> new HttpStatusChecker().getStatusImage(code));
        }
    }
}