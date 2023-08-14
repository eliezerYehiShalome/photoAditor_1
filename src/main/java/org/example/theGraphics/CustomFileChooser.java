package org.example.theGraphics;

import javax.swing.*;
import java.awt.*;

import static org.example.theGraphics.Window.*;

public class CustomFileChooser extends JFileChooser {

    public CustomFileChooser() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(color); // קביעת צבע הרקע לאפור

        this.setAccessory(panel);

        // שינוי צבע הכפתורים לאפור
        UIManager.put("Button.background", color3);
        UIManager.put("Button.foreground", new Color(230,230,230));



        // שינוי צבע הרקע של JXFileChooser לאפור
        UIManager.put("Panel.background", color);

        UIManager.put("List.background", new Color(210, 200, 215));

        UIManager.put("TextField.background", new Color(210, 200, 215));
        UIManager.put("TextField.foreground", Color.BLACK);

        UIManager.put("ComboBox.background",new Color(210, 200, 215));

        // שינוי צבע הטקסט ללבן
        UIManager.put("Label.foreground", Color.WHITE);

        // שינוי קורות הגלילה לצבע אפור כהה
//        UIManager.put("ScrollBar.thumb", new Color(0, 100, 0));
//        UIManager.put("ScrollBar.track", new Color(100, 0, 0));



        SwingUtilities.updateComponentTreeUI(this);
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            JFileChooser fileChooser = new CustomFileChooser();
//            int result = fileChooser.showOpenDialog(null);
//            if (result == JFileChooser.APPROVE_OPTION) {
//                // טיפול בבחירת הקובץ
//                System.out.println("Selected file: " + fileChooser.getSelectedFile());
//            }
//        });
//    }

}