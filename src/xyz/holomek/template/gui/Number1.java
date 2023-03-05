package xyz.holomek.template.gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Number1 {

    private JFrame frame;

    private int sizeX, sizeY;

    public Number1() {
        this.sizeX = 600;
        this.sizeY = 600;
    }

    public void initializace() {
        frame = new JFrame();
        frame.setTitle("Number 1 style");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds(screenSize.width / 2 - sizeX / 2, screenSize.height / 2 - sizeY / 2, sizeX, sizeY);
        frame.getContentPane().setLayout(null);

        /*
        KOD pokud to bude file
         */
        JFileChooser fileChooser = new JFileChooser(".");
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        }

        /*
        KONEC KODU
         */

        frame.setVisible(true);
    }

}
/*

        JLabel mins = new JLabel("Počet min");
        mins.setFont(new Font("Dialog", Font.BOLD, 18));
        mins.setBounds(140, 308, 100, 20);
        frame.getContentPane().add(mins);

        JTextField minsText = new JTextField("10");
        mins.setLabelFor(minsText);
        minsText.setBounds(280, 308, 100, 20);
        minsText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent event) {
                try {
                    Integer.parseInt(event.getKeyChar() + "");
                } catch (NumberFormatException e) {
                    event.consume();
                }
            }
        });
        frame.getContentPane().add(minsText);

        JLabel fields = new JLabel("Počet polí");
        fields.setFont(new Font("Dialog", Font.BOLD, 18));
        fields.setBounds(140, 338, 100, 20);
        frame.getContentPane().add(fields);

        JTextField fieldsText = new JTextField("10");
        fields.setLabelFor(fieldsText);
        fieldsText.setBounds(280, 338, 100, 20);
        fieldsText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent event) {
                try {
                    Integer.parseInt(event.getKeyChar() + "");
                } catch (NumberFormatException e) {
                    event.consume();
                }
            }
        });
        frame.getContentPane().add(fieldsText);

        JButton button = new JButton("Začít hru");
        button.setFont(new Font("Dialog", Font.BOLD, 18));
        button.setBounds(215, 428, 120, 35);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (minsText.getText().length() == 0) {
                    return;
                }
                try {
                    new MenuPlay(Integer.parseInt(minsText.getText()), Integer.parseInt(fieldsText.getText()), frame.getBounds());
                    frame.dispose();
                } catch (NumberFormatException ex) {

                }
            }
        });
        frame.getContentPane().add(button);
 */
