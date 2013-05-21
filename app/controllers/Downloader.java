package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import models.DownloadSpecification;

/**
 * @author adericbourg
 */
final class Downloader {

    private Downloader() {

    }

    static InputStream download(DownloadSpecification downloadSpecification) {
        // TODO Many things...
        try {
            URL url = new URL(downloadSpecification.url);
            URLConnection urlConnection = url.openConnection();
            return urlConnection.getInputStream();
        } catch (IOException ioe) {
            return null;
        }
    }
}
