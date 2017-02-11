package app;

import generator.BonDeCommandeGenerator;
import generator.DevisGenerator;
import generator.FactureGenerator;
import generator.Generator;

/**
 * Created by jérémy on 10/02/2017.
 *
 */
public class App {
    public static void main(String[] args) {
        try {
            Generator devis = new DevisGenerator("Test", "Test devis");
            Generator facture = new FactureGenerator("Test", "Test facture");
            Generator bon = new BonDeCommandeGenerator("Test", "Test bon de commande");

            devis.generate();
            facture.generate();
            bon.generate();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
