package models;

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
        final StringBuilder sb = new StringBuilder("DownloadStream {\n");
        sb.append("\tstream: ").append(stream).append(",\r\n");
        sb.append("\tbaseName: '").append(baseName).append('\'').append(",\r\n");
        sb.append("\textension: '").append(extension).append('\'').append(",\r\n");
        sb.append("\tstatus: '").append(status).append('\'').append(",\r\n");
        sb.append("\tmessage: '").append(message).append('\'').append(",\r\n");
        sb.append("\tsourceUrl: '").append(sourceUrl).append('\'').append("\r\n");
        sb.append('}');
        return sb.toString();
    }
}
