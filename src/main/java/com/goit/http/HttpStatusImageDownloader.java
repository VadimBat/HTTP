package com.goit.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class HttpStatusImageDownloader {
    private static final Logger log = LoggerFactory.getLogger(HttpStatusImageDownloader.class);
    private final HttpStatusChecker getImagePathChecker = new HttpStatusChecker();

    public void downloadStatusImage(int code) {
        String imageUrl = getImagePathChecker.getStatusImage(code);
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            try (InputStream input = connection.getInputStream()) {
                File file = new File("cats");
                if (!file.exists()) {
                    file.mkdir();
                }
                Path path = Paths.get("cats", code + ".jpg");
                try (FileOutputStream output = new FileOutputStream(path.toString())) {
                    byte[] buffer = new byte[1024 * 5];
                    int bytesRead;
                    while ((bytesRead = input.read(buffer)) > 0) {
                        if (bytesRead < buffer.length) {
                            buffer = Arrays.copyOf(buffer, bytesRead);
                        }
                        output.write(buffer, 0, bytesRead);
                    }
                    log.info("Image downloaded successfully: {}", path.toAbsolutePath());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
