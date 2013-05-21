package controllers;

import static play.data.Form.form;
import models.DownloadSpecification;
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

        return ok(Downloader.download(downloadSpecification)); // Stream content
    }
}
