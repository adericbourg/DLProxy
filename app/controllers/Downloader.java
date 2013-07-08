package controllers;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import models.DownloadSpecification;
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
        // TODO Many things...

        //response().setContentType("application/x-download");
        //response().setHeader("Content-disposition","attachment; filename=tradeLogTest.xlsx");
        //return ok(new File("/absolute/path/to/tradeLogTest.xlsx"));

        // http://stackoverflow.com/questions/11860971/streaming-large-files-with-play-framework-and-third-party-api

        DownloadStream result = new DownloadStream();
        result.baseName = StringUtils.isEmpty(downloadSpecification.targetFilename) ? FilenameUtils.getBaseName(downloadSpecification.url)
                : downloadSpecification.targetFilename;
        result.extension = StringUtils.isEmpty(downloadSpecification.targetExtension) ? org.apache.commons.io.FilenameUtils
                .getExtension(downloadSpecification.url) : downloadSpecification.targetExtension;

        try {
            URL url = new URL(downloadSpecification.url);
            URLConnection urlConnection = url.openConnection();
            result.stream = urlConnection.getInputStream();

            return result;
        } catch (IOException ioe) {
            return null;
        }
    }
}
