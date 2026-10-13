package com.nutritics.labelprinter;

import android.content.Context;

import java.io.File;

// Scanner-validation fixture mirroring the printdrivermob scan. See EXPECTED_FINDINGS.md.
public class Brother {

    private final Context context;

    public Brother(Context context) {
        this.context = context;
    }

    // Template name arrives from the print request and is joined onto the files dir unchecked.
    public File templateFile(String templateName) {
        File templates = context.getFilesDir();
        return new File(templates, templateName);
    }

    public boolean hasTemplate(String templateName) {
        return templateFile(templateName).exists();
    }
}
