package models;

import play.data.validation.Constraints;

/**
 * @author adericbourg
 */
public class DownloadSpecification {

    @Constraints.Required
    public String url;

    public String targetFilename;
    public String targetExtension;

}
