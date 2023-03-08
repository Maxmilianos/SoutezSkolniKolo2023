package xyz.holomek;

import xyz.holomek.ukoly.ukol1.Ukol1;
import xyz.holomek.ukoly.ukol2.Ukol2;
import xyz.holomek.ukoly.ukol3.Ukol3;

public class Test {

    /*
    hlavni metoda volana, zavola konstruktor tridy
     */
    public static void main(String[] args) {

        // Pro kazdy ukol staci odkomentovat jeho blbost
//        Ukol1 run = new Ukol1();

//        Ukol2 run = new Ukol2();

        Ukol3 run = new Ukol3();

        run.inicializace();
    }

}
