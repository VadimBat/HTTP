package com.goit.http;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class HttpStatusImageDownloaderTest {

    @Test
    void downloadStatusImageDirectoryMadeTest() {
        //Given
        File expectedFile = new File("cats");
        new HttpStatusImageDownloader().downloadStatusImage(200);
        //When
        boolean result = expectedFile.isDirectory();
        //Then
        assertTrue(result);
    }

    @Test
    void downloadStatusImageIsDownloadedTest() {
        //Given
        File expectedFile = new File("cats/200.jpg");
        new HttpStatusImageDownloader().downloadStatusImage(599);
        //When
        boolean result = expectedFile.exists();
        //Then
        assertTrue(result);
    }
}