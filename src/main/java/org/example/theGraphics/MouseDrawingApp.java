package org.example.theGraphics;import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseDrawingApp extends JFrame {

    private JPanel drawingPanel;

    public MouseDrawingApp() {
        drawingPanel = new JPanel() {
            private Point lastPoint;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (lastPoint != null) {
                    g.setColor(Color.BLACK);
                    g.fillOval(lastPoint.x - 5, lastPoint.y - 5, 10, 10);
                }
            }
        };

        drawingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                drawingPanel.getGraphics().setColor(Color.BLACK);
                drawingPanel.getGraphics().fillOval(e.getX() - 5, e.getY() - 5, 10, 10);
                drawingPanel.repaint();
            }
        });

        setLayout(null);
        add(drawingPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MouseDrawingApp();
        });
    }
}
