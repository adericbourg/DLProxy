package controllers;

import static play.data.Form.form;
import models.DownloadSpecification;
import models.DownloadStatus;
import models.DownloadStream;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {

    private static final Form<DownloadSpecification> DOWNLOAD_FORM = form(DownloadSpecification.class);

    public static Result index() {
        return ok(index.render("Download file", DOWNLOAD_FORM));
    }

    public static Result download() {
        Form<DownloadSpecification> downloadForm = DOWNLOAD_FORM.bindFromRequest();

        if (downloadForm.hasErrors()) {
            return badRequest(index.render("Download file", downloadForm));
        }

        DownloadSpecification downloadSpecification = downloadForm.get();
        DownloadStream downloadStream = Downloader.download(downloadSpecification);

        return stream(downloadStream);
    }

    private static Result stream(DownloadStream downloadStream) {
        if (!DownloadStatus.VALID.equals(downloadStream.status)) {
            return badRequest(downloadStream.toString());
        }
        response().setContentType("application/x-download");
        response().setHeader("Content-disposition",
                String.format("attachment; filename=%s.%s", downloadStream.baseName, downloadStream.extension));
        return ok(downloadStream.stream); // Stream content
    }
}
