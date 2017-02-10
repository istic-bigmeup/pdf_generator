package app;

import generator.DevisGenerator;
import generator.FactureGenerator;
import generator.Generator;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;

/**
 * Created by jérémy on 10/02/2017.
 */
public class App {
    public static void main(String[] args) {
        try {
            PDDocument pdDocument = PDDocument.load(new File("resources/devis.pdf"));
            Generator generator = new DevisGenerator("Test", "Test subject", pdDocument);
            generator.generate();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
