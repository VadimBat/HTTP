package com.goit;

import com.goit.http.HttpImageStatusCli;
import com.goit.http.HttpStatusChecker;
import com.goit.http.HttpStatusImageDownloader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class App {

    private final static Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        String url = new HttpStatusChecker().getStatusImage(201);
        log.info("Image link = {}", url);
        new HttpStatusImageDownloader().downloadStatusImage(202);
        new HttpImageStatusCli().askStatus();
    }
}