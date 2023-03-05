package xyz.holomek;

import xyz.holomek.template.gui.Number1;

import javax.swing.*;
import java.io.File;

public class Test {

    /*
    hlavni metoda volana, zahaji file chooser a pote zavola konstruktor Domino tridy
     */
    public static void main(String[] args) {
//        JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        // inicializace filechooseru
//        JFileChooser fileChooser = new JFileChooser(".");
//        int result = fileChooser.showOpenDialog(frame);
//        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//        frame.dispose();
//
//        // kontrola zda probehlo vse uspesne
//        if (result != JFileChooser.APPROVE_OPTION) {
//            System.out.println("Musíte zvolit soubor");
//            return;
//        }
//
//        File selectedFile = fileChooser.getSelectedFile();
//        // vsude se uvadi ze vstupni soubor je .bmp, tudiz davam podminku
//        if (!selectedFile.getName().endsWith(".bmp")) {
//            System.out.println("Nebyl zvolen BMP soubor.");
//            return;
//        }
       // File selectedFile = new File("C:\\Users\\soutez\\IdeaProjects\\UstredniKolo\\reseni\\priklad01.bmp");

//        System.out.println("Byl zvolen soubor: " + selectedFile.getName());
        Number1 run = new Number1();
        run.initializace();;
    }

}
