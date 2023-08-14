package org.example.theGraphics;import javax.swing.*;
import java.awt.*;

public class StyledMessageDialog extends JOptionPane {
    public static void showMessageDialog(Component parentComponent, Object message, String title, int messageType) {
        JOptionPane optionPane = new JOptionPane(message, messageType);
        optionPane.setMessageType(messageType);

        UIDefaults defaults = UIManager.getLookAndFeelDefaults();
        defaults.put("OptionPane.background", new Color(8, 240, 240));
        defaults.put("OptionPane.foreground", Color.WHITE); // צבע טקסט לבן

        JDialog dialog = optionPane.createDialog(parentComponent, title);
        dialog.setVisible(true);
    }
}
