package model.components;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

public class RoundedBorder extends AbstractBorder {
    private final Color color;

    public RoundedBorder(Color color) {
        this.color = color;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);

        int arc = 10; // Adjust the arc value to control the roundness

        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(x, y, width - 1, height - 1, arc, arc);
        Area corner = new Area(new Rectangle(0, 0, width, height));
        corner.subtract(new Area(roundedRectangle));

        g2.fill(corner);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(4, 8, 4, 8); // Adjust as needed
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = insets.right = 8; // Adjust as needed
        insets.top = insets.bottom = 4; // Adjust as needed
        return insets;
    }
}
