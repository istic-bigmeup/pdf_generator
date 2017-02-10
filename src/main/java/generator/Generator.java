package generator;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.awt.*;

/**
 * Created by jérémy on 10/02/2017.
 */
public class Generator {
    protected static String CREATOR = "BigMeUp";

    protected String title;
    protected String subject;
    protected PDDocument document;

    /**
     * The constructor
     */
    public Generator(String title, String subject, PDDocument document){
        this.title      = title;
        this.subject    = subject;
        this.document   = document;
    }
    /**
     * Generates the PDF file
     */
    public void generate(){
    }
}
