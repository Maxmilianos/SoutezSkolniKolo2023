package xyz.holomek.ukoly.ukol1;

import xyz.holomek.utils.UtilFiles;
import xyz.holomek.utils.UtilMath;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Ukol1 {

    // zakladni soubor
    public File file;

    // Aktivní filtry
    public ArrayList<String> filters = new ArrayList<>();

    // Radky
    public ArrayList<Radek> radky = new ArrayList<>();

    public Ukol1() {

    }

    // Zakladni metoda pro zavolani
    public void inicializace() {

        // Pokud nechceme vybírat soubor
        file = new File("C:\\Users\\maxmi\\IdeaProjects\\SoutezSkolniKolo2023\\data\\data.csv");

        // Pokud chceme vybírat soubor
        if (false == true) {
            JFileChooser fileChooser = new JFileChooser(".");
            int result = fileChooser.showOpenDialog(new JFrame());
            if (result == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
                System.out.println("Selected file: " + file.getAbsolutePath());
            }
        }

        // Výpis všech řádků ze souboru
        ArrayList<String> lines = UtilFiles.readFromFile(file);
        for (String s : lines) {
            // Získání hodnot
            String[] values = s.split(";");
            if (UtilMath.isInteger(values[1])) {
                radky.add(new Radek(values[0], Integer.parseInt(values[1]), Integer.parseInt(values[2])));
            }
        }

        // Vypis
        vypis();

        // Odposlouchávání konzole
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = null;
            while ((line = br.readLine()) != null) {
                if (line.equalsIgnoreCase("1")) { // Vypis vsech polozek
                    // Kontrola zda soubor existuje
                    if (file.exists()) {
                        ArrayList<Radek> radky = ziskat();
                        for (Radek radek : radky) {
                            System.out.println(radek.toString());
                        }
                        System.out.println("Celkový počet položek: " + radky.size());
                        vypis();
                    } else {
                        System.out.println("Soubor, který byl vybrán již neexistuje.");
                    }
                } else if (line.equalsIgnoreCase("2")) { // Pouziti filtru

                    System.out.println("Napište Váš filtr:");
                    String newLine = null;
                    boolean zvolenFiltr = false;
                    while ((newLine = br.readLine()) != null && !zvolenFiltr) {

                        // Kontrola pro cenu vyssi nizsi stejnu
                        if (newLine.startsWith("=") || newLine.startsWith("<") || newLine.startsWith(">") || newLine.startsWith("<=") || newLine.startsWith(">=")) {
                            String a = newLine.replace("<", "").replace(">", "").replace("=", "");
                            if (UtilMath.isInteger(a)) {
                                filters.add(newLine);
                                System.out.println("Byl přidán filtr: " + newLine);
                            } else {
                                System.out.println("Zvolený filtr není číslo. Nepřidávám: " + newLine);
                            }
                        } else {
                            filters.add(newLine);
                            System.out.println("Byl přidán filtr: " + newLine);
                        }
                        // Pokracovani textu
                        System.out.println();
                        System.out.println("Aktivní filtry:");
                        for (String filter : filters) {
                            System.out.println(" - " + filter);
                        }
                        System.out.println();
                        System.out.print("Pro pokračování napište stiskněte 2x enter.");
                        zvolenFiltr = true;
                    }
                } else if (line.equalsIgnoreCase("3")) { // Prumerna cena
                    System.out.println("Počítám průměrnou cenu");
                    ArrayList<Radek> radky = ziskat();
                    int cenaVsech = 0;
                    int cenaVsechBez = 0;
                    for (Radek radek : radky) {
                        cenaVsech += radek.price;
                        cenaVsechBez += radek.priceWithout;
                    }
                    double prumernaCena = cenaVsech / radky.size();
                    double prumernaCenaBez = cenaVsechBez / radky.size();
                    System.out.println("Průměrná cena:" + prumernaCena);
                    System.out.println("Průměrná cena bez DPH:" + prumernaCenaBez);
                    System.out.println();
                    vypis();
                } else if (line.equalsIgnoreCase("4")) { // Celkova cena

                    System.out.println("Počítám průměrnou cenu");
                    ArrayList<Radek> radky = ziskat();
                    int cenaVsech = 0;
                    int cenaVsechBez = 0;
                    for (Radek radek : radky) {
                        cenaVsech += radek.price;
                        cenaVsechBez += radek.priceWithout;
                    }
                    System.out.println("Celková cena:" + cenaVsech);
                    System.out.println("Celková cena bez DPH:" + cenaVsechBez);
                    System.out.println();
                    vypis();
                } else if (line.equalsIgnoreCase("5")) { // Konec
                    System.exit(0);
                } else { // kdyby ten idiot napsal neco jineho
                    vypis();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void vypis() {
        System.out.println();
        System.out.println("Možnosti:");
        System.out.println("1) Vypsat položky");
        System.out.println("2) Použít filtr");
        System.out.println("3) Vypočítat průměrnou cenu");
        System.out.println("4) Vypočítat celkovou cenu");
        System.out.println("5) Konec");
    }

    // Metoda pro pouziti filtru a nasledne vraceni listu
    private ArrayList<Radek> ziskat() {
        // vysledny list
        ArrayList<Radek> vysledek = new ArrayList<>();
        // vypis vsech radku
        for (Radek radek : radky) {
            boolean add = true;
            for (String filter : filters) {
                // filtr pro cenu
                if (filter.startsWith("=") || filter.startsWith("<") || filter.startsWith(">") || filter.startsWith("<=") || filter.startsWith(">=")) { // filtr pro cenu

                    // !! nevedel jsem zda pouzit cenu bez dph nebo s =)

                    // ziskame si pouze cislo, nemusime delat ochranu protoze je jiz pri zadani filtru
                    int cena = Integer.parseInt(filter.replace("=", "").replace("<", "").replace(">", ""));

                    // a ted listujeme
                    if (filter.startsWith("<=")) {
                        if (radek.priceWithout > cena) {
                            add = false;
                        }
                    } else if (filter.startsWith(">=")) {
                        if (radek.priceWithout < cena) {
                            add = false;
                        }
                    } else if (filter.startsWith(">")) {
                        if (radek.priceWithout <= cena) {
                            add = false;
                        }
                    } else if (filter.startsWith("<")) {
                        if (radek.priceWithout >= cena) {
                            add = false;
                        }

                    } else if (filter.startsWith("=")) {
                        if (radek.priceWithout != cena) {
                            add = false;
                        }
                    } else {
                        System.out.println("Nastala chyba - nebyl rozpoznan filtr - " + filter);
                    }
                } else if (filter.equalsIgnoreCase("10%") || filter.equalsIgnoreCase("15%") || filter.equalsIgnoreCase("21%")) { // filtr pro dph
                    if (radek.dph != Integer.parseInt(filter.replace("%", ""))) {
                        add = false;
                    }
                } else {
                    if (!radek.name.contains(filter)) { // filtr pro texty - nevim zda nazev musi byt presny nebo obsahovat zadane slovo
                        add = false;
                    }
                }
            }
            // pokud muzeme pridat, pridame
            if (add) {
                vysledek.add(radek);
            }
        }
        return vysledek;
    }


    // Pomocna trida
    public class Radek {
        // Nazev
        public String name;
        // Cena bez dph, cena s dph, dph
        public int price, priceWithout, dph;

        // konstruktor
        public Radek(String name, int priceWithout, int dph) {
            this.name = name;
            this.price = (int) (priceWithout * (double) (1 + (double) (dph/100.0)));
            this.priceWithout = priceWithout;
            this.dph = dph;
        }

        // rychlejsi nez to psat pokazde
        @Override
        public String toString() {
            return name + " - " + priceWithout + " - " + dph;
        }
    }
}
