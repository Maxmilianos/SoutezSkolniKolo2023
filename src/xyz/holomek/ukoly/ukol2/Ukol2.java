package xyz.holomek.ukoly.ukol2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import xyz.holomek.utils.UtilFiles;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Ukol2 {

    // vytvoreni klasickych promenych
    private JFrame frame;

    private int sizeX, sizeY;

    private File file;

    // konstruktor
    public Ukol2() {
        this.sizeX = 800;
        this.sizeY = 800;
    }

    // hlavni metoda pro zavolani
    public void inicializace() {
        // vytvoreni frame
        frame = new JFrame();
        frame.setTitle("Ukolnicek zepry");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // dimenze
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds(screenSize.width / 2 - sizeX / 2, screenSize.height / 2 - sizeY / 2, sizeX, sizeY);
        frame.getContentPane().setLayout(null);

        // Pokud chceme pokazde vybirat json, odstranime
        file = new File("C:\\Users\\maxmi\\IdeaProjects\\SoutezSkolniKolo2023\\data\\ukolnicek.json");

        /*
        KOD pokud to bude file
         */
        if (file == null) {
            JFileChooser fileChooser = new JFileChooser(".");
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
                System.out.println("Selected file: " + file.getAbsolutePath());
            }
        }
        /*
        KONEC KODU
         */

        // Cteni z jsonu
        Ukol[] ukoly = new Ukol[0];
        try {
            ukoly = new Gson().fromJson(Files.newBufferedReader(Paths.get(file.getAbsolutePath())), Ukol[].class);
        } catch (IOException e) {
            System.out.println("Zadne data nebyly nalezena");
//            throw new RuntimeException(e);
        }

        // Scroll pane
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 600, 600);
        frame.add(scrollPane);

        // Tabulka
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2 ? false : true;
            }
        };
        //
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // zjistime kazdy radek
                int row = table.rowAtPoint(e.getPoint()), col = table.columnAtPoint(e.getPoint());
                if (row >= 0 && col >= 0) {
                    System.out.println("Row: " + row);
                }
            }
        });
        scrollPane.setViewportView(table);

        // sorting
        table.setAutoCreateRowSorter(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Nadpisy
        model.addColumn("Název");
        model.addColumn("Popis");
        model.addColumn("Datum dokončení");
        model.addColumn("Priorita");

        // Projedeme vsechny ukoly a zapiseme do tabulky
        if (ukoly != null) {
            for (Ukol ukol : ukoly) {
                model.addRow(new String[]{ukol.name, ukol.description, ukol.completion_date, ukol.priority});
            }
        }

        // Tlacitka
        {
            JButton button = new JButton("Uložit");

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArrayList<Ukol> ukoly = new ArrayList<>();
                    // zapiseme vsechny radky do ukolu
                    for (int x = 0; x < table.getRowCount(); x ++) {
                        Ukol ukol = new Ukol((String) table.getValueAt(x, 0), (String) table.getValueAt(x, 1), (String) table.getValueAt(x, 2), (String) table.getValueAt(x, 3));
//                        System.out.println(ukol.toString());
                        ukoly.add(ukol);
                    }
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    JsonElement tree = gson.toJsonTree(ukoly);

                    String save = gson.toJson(tree);
//                    System.out.println(save);
                    file.delete();
                    try {
                        UtilFiles.appendToFile(file, save);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            button.setBounds(600, 10,150, 20);
            frame.add(button);
        }

        { // tlacitko - pridat
            JButton button = new JButton("Přidat řádek");

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // prida novy radek
                    ((DefaultTableModel) table.getModel()).addRow(new String[] {"", "", "", ""});
                }
            });
            button.setBounds(600, 40,150, 20);
            frame.add(button);
        }
        { // tlacitko - odstranit
            JButton button = new JButton("Odstranit řádek");

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // odstrani aktualni radek
                    model.removeRow(table.getSelectedRow());
                }
            });
            button.setBounds(600, 70,150, 20);
            frame.add(button);
        }
        { // tlacitko - hotovo
            JButton button = new JButton("Označit jako hotové");

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // zapíše že to je hotové
                    model.setValueAt(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDateTime.now()), table.getSelectedRow(), 2);
                }
            });
            button.setBounds(600, 100,150, 20);
            frame.add(button);
        }
        { // tlacitko - hledat
            JButton button = new JButton("Hledat");

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // otevřeme okno
                    String text = JOptionPane.showInputDialog("Napište hledaný výraz:");
                    if (text != null && text.length() != 0) {
                        // projedeme cely list
                        for (int x = 0; x < table.getRowCount(); x ++) {
                            for (int y = 0; y < table.getColumnCount(); y ++) {
                                Object value = table.getValueAt(x, y);
                                // zkontrolujeme zda sedi
                                if (value != null && ((String) value).contains(text)) {
                                    // oznacime
                                    table.setRowSelectionInterval(x, x);
                                    return;
                                }
                            }
                        }
                    }
                }
            });
            button.setBounds(600, 130,150, 20);
            frame.add(button);
        }
        {
            JButton button = new JButton("Import");

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    // zobrazime vyberove okno
                    JFileChooser fileChooser = new JFileChooser(".");
                    int result = fileChooser.showOpenDialog(frame);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        try {
                            Ukol[] noveUkoly = new Gson().fromJson(Files.newBufferedReader(Paths.get(selectedFile.getAbsolutePath())), Ukol[].class);
                            for (Ukol ukol : noveUkoly) {
                                model.addRow(new String[] {ukol.name, ukol.description, ukol.completion_date, ukol.priority});
                            }
                        } catch (IOException ex) {
                            System.out.println("Zadne data nebyly nalezena");
                        }

                    }
                }
            });
            button.setBounds(600, 160,150, 20);
            frame.add(button);
        }

        frame.setVisible(true);
    }

    // pomocna trida pro ukoly
    public class Ukol {
        // klasicke stringy
        public String name, description, completion_date, priority;

        // konstruktor
        public Ukol(String name, String description, String completion_date, String priority) {
            this.name = name;
            this.description = description;
            this.completion_date = completion_date;
            this.priority = priority;
        }

        // metoda pro vypsani, rychlejsi
        @Override
        public String toString() {
            return "Ukol{" +
                    "name='" + name + '\'' +
                    ", description='" + description + '\'' +
                    ", completion_date='" + completion_date + '\'' +
                    ", priority='" + priority + '\'' +
                    '}';
        }
    }
}
