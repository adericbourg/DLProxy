package controllers;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import models.DownloadSpecification;
import models.DownloadStatus;
import models.DownloadStream;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @author adericbourg
 */
final class Downloader {

    private Downloader() {

    }

    static DownloadStream download(DownloadSpecification downloadSpecification) {
        DownloadStream result = new DownloadStream(downloadSpecification.url);
        result.baseName = getBaseName(downloadSpecification);
        result.extension = getExtension(downloadSpecification);

        try {
            URLConnection urlConnection = new URL(downloadSpecification.url).openConnection();
            result.stream = urlConnection.getInputStream();
            result.status = DownloadStatus.VALID;
            return result;
        } catch (IOException ioe) {
            result.status = DownloadStatus.ERROR;
            result.message = ioe.getMessage();
            return result;
        }
    }

    private static String getBaseName(DownloadSpecification downloadSpecification) {
        return StringUtils.isEmpty(downloadSpecification.targetFilename) ? FilenameUtils.getBaseName(downloadSpecification.url)
                : downloadSpecification.targetFilename;
    }

    private static String getExtension(DownloadSpecification downloadSpecification) {
        String rawExtension = StringUtils.isEmpty(downloadSpecification.targetExtension) ? FilenameUtils
                .getExtension(downloadSpecification.url) : downloadSpecification.targetExtension;
        if (rawExtension != null) {
            rawExtension = rawExtension.split("\\?")[0];
        }
        return rawExtension;
    }
}
