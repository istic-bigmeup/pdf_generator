package app;

import generator.DevisGenerator;
import generator.FactureBigMeUpGenerator;
import generator.FactureGenerator;
import generator.Generator;

/**
 * Created by jérémy on 10/02/2017.
 *
 */
public class App {
    public static void main(String[] args) {
        try {
            if(args.length >= 2){
                Generator file;

                if(args[0].equals("devis")) {
                    file = new DevisGenerator(args[1]);
                } else if(args[0].equals("facture")){
                    file = new FactureGenerator(args[1]);
                } else {
                	file = new FactureBigMeUpGenerator(args[1]);
                }

                file.generate();
            } else {
                System.out.println("Usage : pdfgenerator devis|facture|bigmeup id");
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
