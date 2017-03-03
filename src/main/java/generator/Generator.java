package generator;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;

import database.DataBase;

import java.io.File;
import java.util.Map;

/**
 * Created by jérémy on 10/02/2017.
 *
 */
public class Generator {
    protected final int FONT_SIZE_NORMAL    = 10;
    protected final int FONT_SIZE_TITLE     = 12;
    protected final int FONT_SIZE_BIG_TITLE = 19;

    protected final int INTER_LINE          = 12;
    
    protected final String m_ARIAL = "resources/fonts/arial.ttf";
    protected final String m_ARIAL_BOLD = "resources/fonts/arialbd.ttf";

    protected PDFont arial;
    protected PDFont arialBold;
    protected String missionId;
    protected DataBase db;
    protected Map<String, String> mission;

    private String title;
    private String subject;
    private PDDocument document;

    /**
     * The constructor
     */
    public Generator(String missionId) {
        db = new DataBase("localhost");
        mission = db.getMission(missionId);
        
        this.missionId 	= missionId;
        this. document  = null;
        this.arialBold  = null;
        this.arial      = null;
        
        this.title      = "";
        this.subject    = "";
    }

    public void setDocument(PDDocument document){
        this.document   = document;
    }

    @SuppressWarnings("deprecation")
	public void initFonts() throws Exception {
        arial       = PDTrueTypeFont.loadTTF(document, new File(m_ARIAL));
        arialBold   = PDTrueTypeFont.loadTTF(document, new File(m_ARIAL_BOLD));
    }

    /**
     * Generates the PDF file
     * 
     * @param	id	The id in the database
     */
    public void generate(){
        try {
            header();

            body();

            footer();

            document.save("resources/" + this.title + "_" + this.subject + ".pdf");

            document.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void setTitle(String title){
    	this.title = title;
    }
    
    public void setSubject(String subject){
    	this.subject = subject;
    }

    /**
     * To write in the document
     *
     * @param x             The X coordinate
     * @param y             The Y coordinate
     * @param text          The text to display
     * @param font          The font family
     * @param fontSize      The font's size
     * @throws Exception    The exception thrown
     */
    protected void write(int x, int y, String text, PDFont font, float fontSize) throws Exception{
        // Set the page
        PDPage page = document.getPage(0);

        // Set the content stream
        PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);
        contentStream.beginText();
        contentStream.setFont(font, fontSize);

        // Set the new line's position
        contentStream.newLineAtOffset(x, y);

        // Set the new line's text
        contentStream.showText(text);
        contentStream.endText();

        // Close the stream
        contentStream.close();
    }

    /**
     * Generates the coordinates in the page
     * @throws Exception    The exception thrown
     */
    protected void generate_coordinates() throws Exception{
        for(int i = 0; i < 1000; i+=50){
            for(int j = 0; j < 1000; j+=25){
                write(i, j, "(" + i + ";" + j + ")", arial, 12);
            }
        }
    }

    /**
     * Writes the header
     */
    protected void header() throws Exception{
    }

    /**
     * Writes the body
     */
    protected void body() throws Exception{
    }

    /**
     * Writes the footer
     */
    protected void footer() throws Exception{
    }
}
