package org.example.theGraphics;import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.plaf.ColorUIResource;
import javax.swing.text.JTextComponent;
import java.awt.*;

public class CustomColorChooser extends JColorChooser {

    public CustomColorChooser() {
        UIManager.put("Button.background", new Color(155, 150, 160));
        UIManager.put("ComboBox.background", new Color(200, 200, 220));
        UIManager.put("Panel.background", new Color(93, 80, 99));
        UIManager.put("Label.foreground", Color.WHITE);
        UIManager.put("List.background", new Color(230, 230, 230));
        UIManager.put("TextField.background", new Color(220, 220, 220));
        UIManager.put("TextField.foreground", Color.BLACK);
        UIManager.put("ScrollBar.thumb", new Color(40, 40, 40));
        UIManager.put("ScrollBar.track", new Color(50, 50, 50));
        UIManager.put("Slider.background", new Color(50, 50, 50));
        UIManager.put("Slider.foreground", new Color(40, 40, 40));
        UIManager.put("CheckBox.background", new Color(40, 40, 40));
        UIManager.put("CheckBox.foreground", new  Color(40, 40, 40));
        UIManager.put("TabbedPane.selected", new Color(100, 100, 100));
        UIManager.put("TabbedPane.unselectedBackground", new Color(155, 150, 160));
        UIManager.put("TabbedPane.unselectedForeground", Color.WHITE);


        setChooserPanels(new AbstractColorChooserPanel[]{getChooserPanels()[0]});
        setPreviewPanel(new JPanel());


        removeListeners(this);
    }

    private void removeListeners(Component comp) {
        if (comp instanceof AbstractButton) {
            ((AbstractButton) comp).setOpaque(true);
        } else if (comp instanceof JComboBox) {
            ((JComboBox<?>) comp).setEditable(true);
            ((JComboBox<?>) comp).setOpaque(true);
        } else if (comp instanceof JTextComponent) {
            ((JTextComponent) comp).setEditable(true);
            ((JTextComponent) comp).setOpaque(true);
        }

        if (comp instanceof Container) {
            Component[] comps = ((Container) comp).getComponents();
            for (Component c : comps) {
                removeListeners(c);
            }
        }
    }

}
