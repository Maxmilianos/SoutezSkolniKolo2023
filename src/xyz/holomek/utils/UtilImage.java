package xyz.holomek.utils;

import javax.swing.*;
import java.awt.*;

public class UtilImage {

    // metoda pro zmenseni/zvetseni daneho obrazku do urcite vysky, sirky
    public static ImageIcon getScaledImage(ImageIcon icon, int width, int height) {
        int nw = icon.getIconWidth();
        int nh = icon.getIconHeight();

        if(icon.getIconWidth() > width)
        {
            nw = width;
            nh = (nw * icon.getIconHeight()) / icon.getIconWidth();
        }

        if(nh > height)
        {
            nh = height;
            nw = (icon.getIconWidth() * nh) / icon.getIconHeight();
        }

        return new ImageIcon(icon.getImage().getScaledInstance(nw, nh, Image.SCALE_DEFAULT));
    }

}