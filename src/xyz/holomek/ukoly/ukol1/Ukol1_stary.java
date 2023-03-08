package xyz.holomek.ukoly.ukol1;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Ukol1_stary {


    // menu
    private JFrame frame;

    // sirka a vyska menu
    private int sizeX, sizeY;

    public Ukol1_stary() {
        this.sizeX = 600;
        this.sizeY = 800;
    }

    // Zakladni metoda pro volani
    public void initializace() {
        frame = new JFrame();
        frame.setTitle("Ukol 1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds(screenSize.width / 2 - sizeX / 2, screenSize.height / 2 - sizeY / 2, sizeX, sizeY);
//        frame.getContentPane().setLayout(null);

        File file = new File("C:\\Users\\maxmi\\IdeaProjects\\SoutezSkolniKolo2023\\data\\data.csv");

//        JFileChooser fileChooser = new JFileChooser(".");
//        int result = fileChooser.showOpenDialog(frame);
//        if (result == JFileChooser.APPROVE_OPTION) {
//            File selectedFile = fileChooser.getSelectedFile();
//            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
//        }

        ArrayList<String[]> list = new ArrayList<>();
        String[] names = null;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            int lineId = 0;
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                if (lineId == 0) {
                    names = values;
                } else {
                    list.add(values);
                }
                lineId += 1;
            }

        } catch (Exception e) {
            System.out.println("Sefe mame chybu " + e.getMessage());
        }

        String[][] records = new String[list.size()][];
        int index = 0;
        for (String[] a : list) {
            records[index] = a;
            index += 1;
        }

        {
            JButton button = new JButton("");
        }

//        JTable table = new JTable(records, names);
//        frame.add(table.getTableHeader(), BorderLayout.PAGE_START);
//        frame.add(table, BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 300, 600);
        frame.add(scrollPane);

        JTable table = new JTable(records, names);
        scrollPane.setViewportView(table);

        frame.setVisible(true);
    }


}
