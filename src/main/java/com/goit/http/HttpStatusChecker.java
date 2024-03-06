package com.goit.http;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.goit.http.Constant.LINK;

public class HttpStatusChecker {

    public String getStatusImage(int code) {

        String url = getURL(LINK, code);

        try {
            HttpRequest getRequest = HttpRequest
                    .newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();

            HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

            if (getResponse.statusCode() == 404) {
                throw new RuntimeException("Image not found. Status code 404");
            }
        } catch (URISyntaxException | InterruptedException | IOException ex) {
            ex.printStackTrace();
        }

        return url;
    }

    public String getURL(String link, int code) {
        return new StringBuilder()
                .append(link)
                .append("/")
                .append(code)
                .append(".jpg")
                .toString();
    }
}
