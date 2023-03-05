package xyz.holomek.utils;

import java.io.*;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class UtilFiles {

    // metoda na kopirovani slozek
    public static void copyDir(File source, File target) throws IOException {
        if(source.isDirectory()) {
            if(!target.exists())
                target.mkdir();
            String files[] = source.list();
            for (String file : files) {
                File srcFile = new File(source, file);
                File destFile = new File(target, file);
                copyDir(srcFile, destFile);
            }
        } else {
            InputStream in = new FileInputStream(source);
            OutputStream out = new FileOutputStream(target);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0)
                out.write(buffer, 0, length);
            in.close();
            out.close();
        }
    }

    // metoda pro zapsani do souboru
    public static void appendToFile(File file, String text) throws IOException {

        if (!file.exists())
           file.createNewFile();

        Files.write(Paths.get(file.getAbsolutePath()), text.getBytes(), StandardOpenOption.APPEND);
    }

    // metoda pro zapsani do souboru, odkazuje na metodu s File
    public static void appendToFile(String filePath, String text) throws IOException {
        appendToFile(new File(filePath), text);
    }

    // metoda pro cteni z souboru radky, vraci je v listu, odkazuje na metodu se stringem
    public static ArrayList<String> readFromFile(File file) {
        return readFromFile(file.getAbsolutePath());
    }


    // metoda pro cteni z souboru radky, vraci je v listu
    public static ArrayList<String> readFromFile(String filePath) {
        ArrayList<String> lines = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String row = null;

            while ((row = bufferedReader.readLine()) != null) {
                lines.add(row);
            }

            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    // metoda pro prejmenovani slozky ci souboru
    public static boolean rename(File rename, File renamed) {
        // kontrola zda cilovy nazev neexistuje jiz, zda ano pokusi se ho odstranit
        if (renamed.exists())
            if (!renamed.delete())
                return false;

        // kontrola zda puvodni file existuje
        if (!rename.exists())
            return false;

        return rename.renameTo(renamed);
    }

}
