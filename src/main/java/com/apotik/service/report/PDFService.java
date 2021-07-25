package com.apotik.service.report;

import com.apotik.repository.ReportStrukRepository;
import lombok.extern.slf4j.Slf4j;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

@Service
@Slf4j
public class PDFService {

    private static final String PDF_RESOURCES = "/pdf-resources/";

    @Autowired private ReportStrukServices reportStrukServices;

    @Autowired private SpringTemplateEngine templateEngine;

    public File generatePdf(Long id) throws IOException, DocumentException {
        Context context = getContext(id);
        String html = loadAndFillTemplate(context);
        return renderPdf(html);
    }

    private File renderPdf(String html) throws IOException, DocumentException {
        File file = File.createTempFile("struk_pembelian_"+ UUID.randomUUID().toString(), ".pdf");
        OutputStream outputStream = new FileOutputStream(file);
        ITextRenderer renderer = new ITextRenderer(20f * 4f / 3f, 20);
        renderer.setDocumentFromString(html,
                new ClassPathResource(PDF_RESOURCES).getURL().toExternalForm());
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();
        file.deleteOnExit();
        return file;
    }

    private Context getContext(Long id) {
        Context context = new Context();
        context.setVariable("reportStrukDto", reportStrukServices.cetakReportStruk(id));
        return context;
    }

    private String loadAndFillTemplate(Context context) {
        return templateEngine.process("struk_kasir", context);
    }

}
