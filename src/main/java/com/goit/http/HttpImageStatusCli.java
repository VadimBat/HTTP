package com.goit.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class HttpImageStatusCli {
    private static final Logger log = LoggerFactory.getLogger(HttpImageStatusCli.class);
    private final HttpStatusChecker getImagePathChecker = new HttpStatusChecker();

    public void askStatus() {
        try (Scanner scanner = new Scanner(System.in)) {
            log.info("Enter HTTP status code");
            while (true) {
                if (scanner.hasNextInt()) {
                    int code = scanner.nextInt();

                    String imageUrl = getImagePathChecker.getURL(Constant.LINK, code);
                    URL url = new URL(imageUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    if (connection.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
                        log.info("There is not image for HTTP status {}", code);
                        break;
                    } else {
                        new HttpStatusImageDownloader().downloadStatusImage(code);
                        break;
                    }
                } else {
                    log.info("Please enter a valid number.");
                    scanner.next();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
