package org.example.theGraphics;

import javax.swing.*;
import java.awt.*;

public class functions extends JButton {

    public functions(String name, int x, int y, int width, int height) {
        this.setText(name);
        this.setFont(new Font("a", Font.BOLD, 14)); // ניתן לשנות את הפונט לפי הצורך
        this.setBounds(x, y, width, height);
    }
}

