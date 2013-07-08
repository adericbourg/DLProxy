package models;

import java.io.InputStream;

/**
 * @author adericbourg
 */
public class DownloadStream {

    public InputStream stream;
    public String baseName;
    public String extension;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DownloadStream{");
        sb.append("stream: ").append(stream);
        sb.append(", baseName: '").append(baseName).append('\'');
        sb.append(", extension: '").append(extension).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
