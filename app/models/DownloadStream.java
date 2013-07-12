package models;

import com.google.common.base.Objects;

import java.io.InputStream;

/**
 * @author adericbourg
 */
public class DownloadStream {

    public DownloadStream() {
        this(null);
    }

    public DownloadStream(String sourceUrl) {
        super();
        this.sourceUrl = sourceUrl;
    }

    public InputStream stream;
    public String baseName;
    public String extension;
    public DownloadStatus status = DownloadStatus.NEW;
    public String message;

    private final String sourceUrl;

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("stream", stream)
                .add("baseName", baseName)
                .add("extension", extension)
                .add("message", message)
                .add("sourceUrl", sourceUrl)
                .toString();
    }
}
