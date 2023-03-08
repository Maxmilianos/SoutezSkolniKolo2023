package xyz.holomek.ukoly.ukol3;

import xyz.holomek.utils.UtilImage;
import xyz.holomek.utils.UtilMath;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Ukol3 {


    // menu
    private JFrame frame;

    // sirka a vyska menu
    private int sizeX, sizeY;

    // definovani radku a sloupcu
    private int m = 0, n = 0;

    // konstruktor
    public Ukol3() {
        this.sizeX = 600;
        this.sizeY = 800;
    }

    // Zakladni metoda pro volani
    public void inicializace() {
        // nastavbeni menu
        frame = new JFrame();
        frame.setTitle("Ukol 3");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // dimenze
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds(screenSize.width / 2 - sizeX / 2, screenSize.height / 2 - sizeY / 2, sizeX + 150, sizeY + 150);
        frame.getContentPane().setLayout(null);


        // Vyber souboru
        JFileChooser fileChooser = new JFileChooser(".");
        int result = fileChooser.showOpenDialog(frame);
        if (result != JFileChooser.APPROVE_OPTION) {
            System.out.println("Nebyl vybran soubor.");
            System.exit(0);
        }
        File selectedFile = fileChooser.getSelectedFile();
//        System.out.println("Selected file: " + selectedFile.getAbsolutePath());

        // Kontrola zda je soubor png ci jpg
        if (!selectedFile.getName().toLowerCase().endsWith(".png") && !selectedFile.getName().toLowerCase().endsWith(".jpg")) {
            System.out.println("Vybirejte pouze .png ci .jpg typy.");
            System.exit(0);
        }

        // Pocet radku
        String text_m = JOptionPane.showInputDialog("Zadejte prosím počet řádků:");
        if (!UtilMath.isInteger(text_m)) {
            System.out.println("Počet řádků je neplatný.");
            return;
        }

        // Pocet sloupcu
        String text_n = JOptionPane.showInputDialog("Zadejte prosím počet sloupců:");
        if (!UtilMath.isInteger(text_n)) {
            System.out.println("Počet sloupců je neplatný.");
            return;
        }

        // Pripsani radku a sloupcu
        this.m = Integer.parseInt(text_m);
        this.n = Integer.parseInt(text_n);

        // vypocet sirky a vysky
        int width = sizeX / this.m, height = sizeY / this.n;

        // pro vsechny
        for (int x = 0; x < this.m; x++) {
            for (int y = 0; y < this.n; y++) {
                // tlacitko
                JButton button = new JButton();
                try {
                    // nastaveni fotky jako background pro tlacitko
                    ImageIcon icon = new ImageIcon(new URL("https://holomek.xyz/img/picture.jpg"));
                    button.setIcon(UtilImage.getScaledImage(icon, width, height));
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                button.setBounds(x * width, y * height, width, height);
                button.setBorder(BorderFactory.createBevelBorder(1));
                frame.add(button);
            }
        }


        frame.setVisible(true);
    }


}
