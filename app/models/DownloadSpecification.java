package models;

import com.google.common.base.Objects;
import play.data.validation.Constraints;

/**
 * @author adericbourg
 */
public class DownloadSpecification {

    @Constraints.Required
    public String url;

    public String targetFilename;
    public String targetExtension;

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("url", url)
                .add("targetFilename", targetFilename)
                .add("targetExtension", targetExtension)
                .toString();
    }
}
