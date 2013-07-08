package models;

import java.io.InputStream;

/**
 * @author adericbourg
 */
public class DownloadStream {

    public DownloadStream() {
        super();
    }

    public DownloadStream(String sourceUrl) {
        this();
        this.sourceUrl = sourceUrl;
    }

    public InputStream stream;
    public String sourceUrl;
    public String baseName;
    public String extension;
    public DownloadStatus status = DownloadStatus.NEW;
    public String message;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DownloadStream{");
        sb.append("stream: ").append(stream);
        sb.append(", baseName: '").append(baseName).append('\'');
        sb.append(", extension: '").append(extension).append('\'');
        sb.append(", status: '").append(status).append('\'');
        sb.append(", message: '").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
