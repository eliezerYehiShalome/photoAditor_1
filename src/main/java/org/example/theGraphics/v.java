//package org.example.theGraphics;
//package org.example;
//
//import javax.imageio.ImageIO;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.IntStream;
//
//public class Window extends JFrame {
//
//    public Window() {
//        try {
//            URL jetImage = getClass().getClassLoader().getResource("monkey.jpg");
//            if (jetImage != null) {
//                BufferedImage bufferedImage = changeColors(ImageIO.read(jetImage));
//                int color = bufferedImage.getRGB(100, 100);
//                Color rgb = new Color(color);
//                System.out.println("width " + bufferedImage.getWidth());
//                System.out.println("Height " + bufferedImage.getHeight());
//                System.out.println("red " + rgb.getRed());
//                System.out.println("green " + rgb.getGreen());
//                System.out.println("blue " + rgb.getBlue());
//
//                this.setSize(bufferedImage.getWidth(), bufferedImage.getHeight());
//                this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//                JLabel label = new JLabel(new ImageIcon(bufferedImage));
//                this.add(label);
//            } else {
//                System.out.println("Cannot find!");
//            }
//            this.setLocationRelativeTo(null);
//            this.setResizable(false);
//            this.setVisible(true);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public BufferedImage miror(BufferedImage image) {
//        int width = image.getWidth();
//        int height = image.getHeight();
//        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        for (int i = 0; i < width; i++) {
//            for (int j = 0; j < height; j++) {
//                int srcX = width - i - 1;
//                int srcRGB = image.getRGB(srcX, j);
//                output.setRGB(i, j, srcRGB);
//            }
//        }
//        return output;
//    }
//
//    public BufferedImage changeColors(BufferedImage image) {
//        int width = image.getWidth();
//        int height = image.getHeight();
//        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        for (int i = 0; i < width; i++) {
//            for (int j = 0; j < height; j++) {
//                Color color = new Color(image.getRGB(i, j));
//                int red = color.getRed();
//                int green = color.getGreen();
//                int blue = color.getBlue();
//                Color newColor = new Color(red,green, green );
//                output.setRGB(i, j, newColor.getRGB());
//            }
//        }
//        return output;
//    }
//    public int findMostFrequentValue(List<Integer> numbers) {
//        Map<Integer, Integer> frequencyMap = new HashMap<>();
//        for (int num : numbers) {
//            int frequency = frequencyMap.getOrDefault(num, 0);
//            frequencyMap.put(num, frequency + 1);
//        }
//
//        int mostFrequentValue = 0;
//        int maxFrequency = 0;
//        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
//            int num = entry.getKey();
//            int frequency = entry.getValue();
//            if (frequency > maxFrequency) {
//                mostFrequentValue = num;
//                maxFrequency = frequency;
//            }
//        }
//        return mostFrequentValue;
//    }
//
//    public BufferedImage findMostColor(BufferedImage image) {
//        List<Integer> Colors = new ArrayList<>();
//        int width = image.getWidth();
//        int height = image.getHeight();
//        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        for (int i = 0; i < width; i++) {
//            for (int j = 0; j < height; j++) {
//                Color color = new Color(image.getRGB(i, j));
//                int red = color.getRed();
//                int green = color.getGreen();
//                int blue = color.getBlue();
//
//                int col = image.getRGB(i, j);
//                Colors.add((Integer) col);
//            }
//        }
//
//
//        int most = findMostFrequentValue(Colors);
//        Color color = new Color(most);
//        System.out.println("red " + color.getRed());
//        System.out.println("green " + color.getGreen());
//        System.out.println("blue " + color.getBlue());
//
//        return image;
//
//
//    }
//
//    public BufferedImage blackAndWhite(BufferedImage image) {
//        int width = image.getWidth();
//        int height = image.getHeight();
//        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        for (int i = 0; i < width; i++) {
//            for (int j = 0; j < height; j++) {
//
//            }
//        }
//        return output;
//    }
//}