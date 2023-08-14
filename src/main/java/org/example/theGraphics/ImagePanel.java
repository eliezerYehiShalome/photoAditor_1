package org.example.theGraphics;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.example.theGraphics.ImageFilters.*;
import static org.example.theGraphics.Window.*;

public class ImagePanel extends JPanel {
    static File file;

    private FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");

    private String selectedOption;

    public static BufferedImage[] imageHistory = new BufferedImage[30]; // מערך שמכיל עד 10 תמונות
    public static int currentIndex = -1; // אינדקס של התמונה הנוכחית במערך

    private BufferedImage resizedImage;

    public static JLabel imageLabel; // New JLabel to display the selected image

    static BufferedImage bufferedImage;
    public static BufferedImage newIMG;

    public CustomFileChooser fileChooser = new CustomFileChooser();

    private int hight;
    private int wid;
    private final int WIDTH = 1200;
    private final int HIGH = 700;


    public ImagePanel() {
        this.setBounds(30, 30, WIDTH, HIGH);
        Color color = new Color(30, 31, 34);
        this.setBackground(color);

        this.setLayout(null);
        Font font = new Font("a", Font.BOLD, 50);

        mirorButton.addActionListener((e -> miror()));

        applyPixelateButton.addActionListener((e -> {
            String sizeInput = StyledMessageDialog.showInputDialog(this, "Enter the new size:");
            try {
                if (sizeInput != null)
                    applyPixelate(Integer.parseInt(sizeInput));
            } catch (NumberFormatException w) {
                StyledMessageDialog.showMessageDialog(this, "אנא הכנס מספרים בלבד");
            } catch (ArithmeticException e1) {
                StyledMessageDialog.showMessageDialog(this, "לא ניתן לחלק באפס");
            }


        }));


        brightnessSlider.addChangeListener(e -> {
            int brightnessValue = darknesSlider.getValue();
            applySaturationFilter(newIMG, (double) brightnessValue);
        });


        darknesSlider.addChangeListener(e -> {
            int brightnessValue = darknesSlider.getValue();
            adjustBrightness(brightnessValue);
        });


        applyNegativeButton.addActionListener((e -> updateFilter(newIMG)));


        changColors.addActionListener((e -> {
            CustomColorChooser colorChooser = new CustomColorChooser();
            Color color1 = colorChooser.showDialog(this, "Choose Color 1", Color.BLACK);
            Color color2 = colorChooser.showDialog(this, "Choose Color 2", Color.WHITE);
            swapColors(color1, color2);
        }));

        colorSwapComboBox.addActionListener(a -> {
            selectedOption = (String) colorSwapComboBox.getSelectedItem();
            System.out.println(selectedOption);
            changeColors(newIMG, selectedOption);

        });
        blackAndWhiteButton.addActionListener((e -> {
            blackAndWhite();
        }));
        rotateImage90DegButton.addActionListener((e -> rotateImage90Deg()));

        openMenuItem.addActionListener((e -> openImage()));

        openImageButton.addActionListener((e) -> openImage());

        blurButton.addActionListener((e -> applyBlurFilter(newIMG)));

        sketchButton.addActionListener((e -> {
            String sizeInput = StyledMessageDialog.showInputDialog(null, "Enter the new size:");
            applySaturationFilter(newIMG, Integer.parseInt(sizeInput));

        }));

        Label label=new Label("לא");
        label.setBounds(0,0,400,400);




        applyFrameButton.addActionListener((e) -> {
            CustomColorChooser colorChooser = new CustomColorChooser();
            Color color1 = colorChooser.showDialog(this, "Choose Color 1", Color.BLACK);
            String sizeInput = StyledMessageDialog.showInputDialog(null, "Enter the new size:");
            applyFrame(color1, Integer.parseInt(sizeInput));
        });
        resizeImageB.addActionListener((e) -> ResizeImageAct());

        resizeImage.addActionListener((e) -> ResizeImageAct());

        saveMenuItem.addActionListener((e) -> saveImage(newIMG));

        backButton.addActionListener((e -> undo()));

        edgeButton.addActionListener((e -> applyEdgeDetectionFilter(newIMG)));

        OilButton.addActionListener((e -> {
            String sizeInput = StyledMessageDialog.showInputDialog(null, "Enter brush size:");

            applyOilPaintingFilter(newIMG,Integer.parseInt(sizeInput));
        }));


    }

    public void ResizeImageAct() {
        if (file != null) {
            try {
                bufferedImage = ImageIO.read(file);

                // שאל את המשתמש לקבוע את הגובה והרוחב החדשים
                int newWidth = Integer.parseInt(whidth.getText());
                int newHeight = Integer.parseInt(high.getText());

                setPreferredSize(new Dimension(newWidth, newHeight));

                // שמירת התמונה המשונתת במשתנה
                BufferedImage resizedImage = resizeImage(newIMG, newWidth, newHeight);

                // Remove the previous image label if exists
                if (imageLabel != null) {
                    remove(imageLabel);
                }
                // Create a new image label and set its properties
                newIMG = resizedImage;
                addToHistory(newIMG);
                imageLabel = new JLabel(new ImageIcon(newIMG));
                imageLabel.setBounds(0, 0, newIMG.getWidth(), newIMG.getHeight());
                // Add the image label to the panel
                add(imageLabel);
                revalidate();
                repaint();
            } catch (IOException r) {
                r.printStackTrace();
            }
        }
    }

    void openImage() {
        fileChooser.setFileFilter(filter);
        int res = fileChooser.showOpenDialog(null);
        System.out.println(res);
        if (res == 0) {
            file = fileChooser.getSelectedFile();
        }
        if (file != null) {
            try {
                BufferedImage originalImage = ImageIO.read(file);

                imageHistory[0]=originalImage;

                // שמירת התמונה המשונתת במשתנה


                newIMG = resizeImage(originalImage, originalImage.getWidth(), originalImage.getHeight());

                // Remove the previous image label if exists
                if (imageLabel != null) {
                    remove(imageLabel);
                }

                // Create a new image label and set its properties
                imageLabel = new JLabel(new ImageIcon(newIMG));

                imageLabel.setBounds(0, 0, newIMG.getWidth(), newIMG.getHeight());

                // Add the image label to the panel
                add(imageLabel);
                revalidate();
                repaint();
            } catch (IOException r) {
                r.printStackTrace();
            }
        }
    }

    private void saveImage(BufferedImage image) {
        CustomFileChooser fileChooser = new CustomFileChooser();
        fileChooser.setDialogTitle("Save Image");
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File outputFile = fileChooser.getSelectedFile();

            try {
                ImageIO.write(image, "png", outputFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}