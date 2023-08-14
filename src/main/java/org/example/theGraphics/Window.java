package org.example.theGraphics;

import javax.swing.*;
import javax.swing.text.Caret;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
//import com.formdev.flatlaf.FlatLightLaf; // הוסף את השורה הזו



public class Window extends JFrame {






    private Font font=new Font("ariel",Font.BOLD,30);


    public static JMenuItem   openMenuItem = new JMenuItem("Open"); // יצירת פריט תפריט בשם "Open"
    public static JMenuItem resizeImage = new JMenuItem("resize image");
    public static JMenuItem saveMenuItem = new JMenuItem("Save"); // יצירת פריט תפריט בשם "Save"

    public static JTextField high = new JTextField("height");
    public static JTextField whidth = new JTextField("whidth");
    public static JButton sketchButton;

    public static JButton resizeImageB;

    public static JButton openImageButton;
    public static JButton blackAndWhiteButton;

    public static JButton applyNegativeButton;
    public static JButton mirorButton;
    public static JButton changColors;
    public static JButton applyFrameButton;
    public static JButton applyPixelateButton;
    public static JButton OilButton;


    public static  String[] buttonLabels = {
            "b&w", "↔", "swap", "☯",
            "Frame", "Pixelate", "↻", "↪","blur","edge","Oil","Sketch"
    };
    public static JButton backButton;


    public static JButton blurButton ;

    public static JButton edgeButton ;






    public static  JSlider brightnessSlider;
    public static  JSlider darknesSlider;

    public static JComboBox<String> colorSwapComboBox;
    public static JButton swapColorsButton;

    public static JButton  rotateImage90DegButton;
    public static    JButton[] buttons = new JButton[buttonLabels.length];


    public static Color color=new Color(45,40,50);
   public static Color color1=new Color(65,60,70);
   public static Color color3=new Color(75,70,80);


    public static functions[] נ;

    public final int X=100;
    public final int Y=1300;
    public final int WIDTH=100;
    public final int HEIGHT=100;





    private Image imageIcon;

    public Window() {
//        try {
//            UIManager.setLookAndFeel( new FlatLightLaf() );
//        } catch( Exception ex ) {
//            System.err.println( "Failed to initialize LaF" );
//        }



        Font font1=new Font("Gulim",Font.PLAIN,13);
        Font font2=new Font("Monospaced",Font.BOLD,30);



        this.setLayout(null);
        this.setIconImage(imageIcon);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Color backgroundColor = new Color(70,70,76); // אפור בהיר
        this.getContentPane().setBackground(backgroundColor);

        colorSwapComboBox = new JComboBox<>(new String[]{"Green to Red", "Green to Blue", "Blue to Red"});
        colorSwapComboBox.setBounds(1280,400,180,30);
        colorSwapComboBox.setBackground(color3);
        colorSwapComboBox.setFont(font1);
        colorSwapComboBox.setForeground(Color.LIGHT_GRAY);
        add(colorSwapComboBox);

        brightnessSlider = new JSlider(JSlider.HORIZONTAL, 0, 20, 0);
        brightnessSlider.setBounds(1265, 500, 200, 40);
        brightnessSlider.setBackground(backgroundColor);
        add(brightnessSlider);


        darknesSlider = new JSlider(JSlider.HORIZONTAL, -20, 0, 0);
        darknesSlider.setBounds(1265, 550, 200, 40);
        darknesSlider.setBackground(backgroundColor);

        add(darknesSlider);


        openImageButton = new JButton("➕");
        openImageButton.setBackground(color1);
        openImageButton.setForeground(color);
        openImageButton.setFont(font);
        openImageButton.setBounds(40,300, 80, 80);
        add(openImageButton);





        int buttonX = 1280;
        int buttonY = 30;

        for (int i = 0; i < buttonLabels.length; i++) {
            JButton button = new JButton(buttonLabels[i]);
            button.setBounds(buttonX, buttonY, 80, 40);
            button.setBackground(color);
            button.setForeground(Color.lightGray);

            if (buttonLabels[i].length()==1) {
                button.setFont(font2);
            }
            add(button);
            buttons[i] = button;
            if (i % 2 == 0) {
                buttonX += 100; // כאשר i זוגי, תזיז את הכפתור בציר ה-X
            } else {
                buttonX = 1280; // כאשר i אי-זוגי, תחזור לציר ה-X המקורי
                buttonY += 60; // תזיז את הכפתור בציר ה-Y
            }
        };

        blackAndWhiteButton=buttons[0];
        mirorButton=buttons[1];
        changColors=buttons[2];
        applyNegativeButton=buttons[3];
        applyFrameButton=buttons[4];
        applyPixelateButton=buttons[5];
        rotateImage90DegButton=buttons[6];
        backButton=buttons[7];
        blurButton=buttons[8];
        edgeButton=buttons[9];
        OilButton=buttons[10];
        sketchButton=buttons[11];





        resizeImageB = new JButton("RESIZE");
        resizeImageB.setBounds(200, 740, 100, 20);
        resizeImageB.setBackground(Color.gray);
        resizeImage.setForeground(Color.lightGray);
        resizeImage.setFont(font1);
        add(resizeImageB);


        high.setBounds(30, 740, 70, 20);
        high.setBackground(Color.lightGray);
        high.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (high.getText().equals("height")) {
                    high.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (high.getText().isEmpty()) {
                    high.setText("height");
                    high.setFont(font1);
                }
            }
        });
        add(high);

        whidth.setBounds(110, 740, 70, 20);
        whidth.setBackground(Color.lightGray);
        whidth.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (whidth.getText().equals("whidth")) {
                    whidth.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (whidth.getText().isEmpty()) {
                    whidth.setText("whidth");
                    whidth.setFont(font1);
                }
            }
        });
        add(whidth);



        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(color3);
        menuBar.setForeground(Color.WHITE);
        JMenu fileMenu = new JMenu("File");
        fileMenu.setForeground(Color.white);
        fileMenu.setFont(font1);
        JMenu Menu = new JMenu("manu");
        Menu.setForeground(Color.white);
        Menu.setFont(font1);

        JMenu help = new JMenu("help");
        help.setForeground(Color.white);
        help.setFont(font1);


        openMenuItem.setBackground(color3);
        openMenuItem.setForeground(Color.white);
        openMenuItem.setFont(font1);
        saveMenuItem.setBackground(color3);
        saveMenuItem.setForeground(Color.white);
        saveMenuItem.setFont(font1);
        resizeImage.setBackground(color3);
        resizeImage.setForeground(Color.white);
        resizeImage.setFont(font1);



        fileMenu.add(openMenuItem); // הוספת הפריט "Open" לתפריט "File"
        fileMenu.add(saveMenuItem); // הוספת הפריט "Save" לתפריט "File"
        fileMenu.add(resizeImage); // הוספת הפריט "Save" לתפריט "File"

        menuBar.add(fileMenu); // הוספת התפריט "File" ל־JMenuBar
        menuBar.add(Menu); // הוספת התפריט "File" ל־JMenuBar
        menuBar.add(help); // הוספת התפריט "File" ל־JMenuBar
        menuBar.setSize(getWidth(),50);

        this.setJMenuBar(menuBar);


        // Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Set the window size to the screen size
        this.setSize(screenSize.width, screenSize.height);

        ImagePanel imagePanel = new ImagePanel();
        add(imagePanel);

        this.setVisible(true);
    }



}
