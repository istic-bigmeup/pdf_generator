package generator;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.awt.*;

/**
 * Created by jérémy on 10/02/2017.
 */
public class Generator {
    protected final String CREATOR = "BigMeUp";

    protected String title;
    protected String subject;
    protected PDDocument document;

    /**
     * The constructor
     */
    public Generator(String title, String subject){
        this.title      = title;
        this.subject    = subject;
    }

    public void setDocument(PDDocument document){
        this.document   = document;
    }

    /**
     * Generates the PDF file
     */
    public void generate(){
    }

    /**
     * To write in the document
     *
     * @param x             The X coordinate
     * @param y             The Y coordinate
     * @param text          The text to display
     * @throws Exception    The exception thrown
     */
    protected void write(int x, int y, String text) throws Exception{
        // Set the page
        PDPage page = document.getPage(0);
        PDFont font = PDType1Font.HELVETICA;

        // Set the content stream
        PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);
        contentStream.beginText();
        contentStream.setFont(font, 12);

        // Set the new line's position
        contentStream.newLineAtOffset(x, y);

        // Set the new line's text
        contentStream.showText(text);
        contentStream.endText();

        // Close the stream
        contentStream.close();
    }
}
