package com.goit.http;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class HttpImageStatusCliTest {
    @Test
    void askStatusOnCorrectCodeTest(){

        //Given
        HttpImageStatusCli statusCli =new HttpImageStatusCli();
        String input = "200\n";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setIn(is);
        System.setOut(new PrintStream(baos));

        //When
        statusCli.askStatus();
        String out = baos.toString().trim();

        //Then
        assertTrue(out.contains("Image downloaded successfully"));

    }

    @Test
    void askStatusOnInCorrectCodeTest(){

        //Given
        HttpImageStatusCli statusCli =new HttpImageStatusCli();
        String input = "100500\n";
        InputStream is = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setIn(is);
        System.setOut(new PrintStream(baos));

        //When
        statusCli.askStatus();
        String out = baos.toString().trim();

        //Then
        assertTrue(out.contains("There is not image for HTTP status 100500"));

    }
}