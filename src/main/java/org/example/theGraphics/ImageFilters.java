package org.example.theGraphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.example.theGraphics.ImagePanel.*;
import static org.example.theGraphics.Window.high;
import static org.example.theGraphics.Window.whidth;

public class ImageFilters {


    public static void blackAndWhite()  {

        int width = newIMG.getWidth();
        int height = newIMG.getHeight();
        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                Color color = new Color(newIMG.getRGB(i, j));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                int avg = (red + green + blue) / 3;
                Color newColor = new Color(avg, avg, avg);
                output.setRGB(i, j, newColor.getRGB());
            }
        }
        newIMG = output;
        addToHistory(newIMG);

        imageLabel.setIcon(new ImageIcon(newIMG)); // Update the displayed image
        imageLabel.revalidate();
        imageLabel.repaint();
    }

    public static void miror() {
        int width = newIMG.getWidth();
        int height = newIMG.getHeight();
        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int srcX = width - i - 1;
                int srcRGB = newIMG.getRGB(srcX, j);
                output.setRGB(i, j, srcRGB);
            }
        }
        updateFilter(output);
    }

    public static void swapColors(Color targetColor, Color replacementColor) {
        int width = newIMG.getWidth();
        int height = newIMG.getHeight();
        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color color = new Color(newIMG.getRGB(i, j));

                int threshold = 100; // הגבלת הדימוי הצבעי
                int diff = Math.abs(color.getRed() - targetColor.getRed()) +
                        Math.abs(color.getGreen() - targetColor.getGreen()) +
                        Math.abs(color.getBlue() - targetColor.getBlue());

                if (diff < threshold) {
                    output.setRGB(i, j, replacementColor.getRGB());
                } else {
                    output.setRGB(i, j, color.getRGB());
                }
            }
        }

        updateFilter(output);
    }

    public static void applyNegative() {
        int width = newIMG.getWidth();
        int height = newIMG.getHeight();
        BufferedImage negativeImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color color = new Color(newIMG.getRGB(i, j));
                Color negativeColor = new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
                negativeImage.setRGB(i, j, negativeColor.getRGB());
            }
        }

        updateFilter(negativeImage);
    }

    public static void applyFrame(Color frameColor, int frameWidth) {
        int width = newIMG.getWidth();
        int height = newIMG.getHeight();
        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (i < frameWidth || i >= width - frameWidth ||
                        j < frameWidth || j >= height - frameWidth) {
                    output.setRGB(i, j, frameColor.getRGB());
                } else {
                    output.setRGB(i, j, newIMG.getRGB(i, j));
                }
            }
        }

        updateFilter(output);
    }
    public static void applyPixelate(int blockSize) {
        int width = newIMG.getWidth();
        int height = newIMG.getHeight();
        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < width; i += blockSize) {
            for (int j = 0; j < height; j += blockSize) {
                int avgRed = 0, avgGreen = 0, avgBlue = 0;
                int count = 0;
                for (int x = i; x < i + blockSize && x < width; x++) {
                    for (int y = j; y < j + blockSize && y < height; y++) {
                        Color color = new Color(newIMG.getRGB(x, y));
                        avgRed += color.getRed();
                        avgGreen += color.getGreen();
                        avgBlue += color.getBlue();
                        count++;
                    }
                }
                avgRed /= count;
                avgGreen /= count;
                avgBlue /= count;
                Color avgColor = new Color(avgRed, avgGreen, avgBlue);
                for (int x = i; x < i + blockSize && x < width; x++) {
                    for (int y = j; y < j + blockSize && y < height; y++) {
                        output.setRGB(x, y, avgColor.getRGB());
                    }
                }
            }
        }

        updateFilter(output);
    }

    public static void adjustBrightness(int value) {
        int width = newIMG.getWidth();
        int height = newIMG.getHeight();
        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color color = new Color(newIMG.getRGB(i, j));
                int red = clamp(color.getRed() + value, 0, 255);
                int green = clamp(color.getGreen() + value, 0, 255);
                int blue = clamp(color.getBlue() + value, 0, 255);
                Color newColor = new Color(red, green, blue);
                output.setRGB(i, j, newColor.getRGB());
            }
        }

        updateFilter(output);
    }

    private static int clamp(int value, int min, int max) {
        return Math.min(Math.max(value, min), max);
    }
    public static void rotateImage90Deg() {
        if (newIMG != null) {
            int width = newIMG.getWidth();
            int height = newIMG.getHeight();
            BufferedImage rotatedImage = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    rotatedImage.setRGB(height - 1 - j, i, newIMG.getRGB(i, j));
                }
            }

            updateFilter(rotatedImage);
        }
    }

    public static void changeColors(BufferedImage image,String swapName) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color color = new Color(image.getRGB(i, j));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                Color newColor = null;
                if (swapName.equals("Green to Red")) {
                    newColor = new Color(green,red, blue);

                } else if (swapName.equals("Green to Blue")) {
                    newColor = new Color(red, blue, green);

                } else if (swapName.equals("Blue to Red")) {

                    newColor = new Color(blue, green, red);
                } else {
                    newColor = color; // אם לא תקף אף תנאי, השאר את הצבע כמו שהוא
                }

                output.setRGB(i, j, newColor.getRGB());
            }
        }

        updateFilter(output);
    }




    public static void addToHistory(BufferedImage image) {
        if (currentIndex + 1 < imageHistory.length) {

            currentIndex++;
            imageHistory[currentIndex] = image;
        } else {
            // מעבר למערך חדש עם מעלות יותר
            BufferedImage[] newHistory = new BufferedImage[imageHistory.length * 2];
            System.arraycopy(imageHistory, 0, newHistory, 0, imageHistory.length);
            imageHistory = newHistory;

            currentIndex++;
            imageHistory[currentIndex] = image;
        }
    }
    public static void applySaturationFilter(BufferedImage image,double saturationFactor) {
        int width = image.getWidth();
        int height = image.getHeight();

        // Create a copy of the original image to store the modified pixels
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(image.getRGB(x, y));
                float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
                hsb[1] = (float) Math.min(1.0, hsb[1] * saturationFactor);
                Color newColor = new Color(Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]));
                newImage.setRGB(x, y, newColor.getRGB());
            }
        }

       updateFilter(newImage);
    }
    public static void undo() {
        if (currentIndex > 0) {
            currentIndex--;
            BufferedImage previousImage = imageHistory[currentIndex];
            newIMG = previousImage;
            imageLabel.setIcon(new ImageIcon(newIMG));
            imageLabel.revalidate();
            imageLabel.repaint();
        }
    }



    public static   void applyBlurFilter(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int i = 1; i < width - 1; i++) {
            for (int j = 1; j < height - 1; j++) {
                int avgRed = 0, avgGreen = 0, avgBlue = 0;
                for (int x = i - 1; x <= i + 1; x++) {
                    for (int y = j - 1; y <= j + 1; y++) {
                        Color color = new Color(image.getRGB(x, y));
                        avgRed += color.getRed();
                        avgGreen += color.getGreen();
                        avgBlue += color.getBlue();
                    }
                }
                avgRed /= 9;
                avgGreen /= 9;
                avgBlue /= 9;
                Color newColor = new Color(avgRed, avgGreen, avgBlue);
                output.setRGB(i, j, newColor.getRGB());
            }

        }
        updateFilter(output);

    }

    public static   void applyEdgeDetectionFilter(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        int[][] filter = {
                {-1, -1, -1},
                {-1, 8, -1},
                {-1, -1, -1}
        };

        for (int i = 1; i < width - 1; i++) {
            for (int j = 1; j < height - 1; j++) {
                int sumRed = 0, sumGreen = 0, sumBlue = 0;
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        Color color = new Color(image.getRGB(i + x - 1, j + y - 1));
                        sumRed += filter[x][y] * color.getRed();
                        sumGreen += filter[x][y] * color.getGreen();
                        sumBlue += filter[x][y] * color.getBlue();
                    }
                }
                int newRed = clamp(sumRed, 0, 255);
                int newGreen = clamp(sumGreen, 0, 255);
                int newBlue = clamp(sumBlue, 0, 255);
                Color newColor = new Color(newRed, newGreen, newBlue);
                output.setRGB(i, j, newColor.getRGB());
            }
        }
        updateFilter(output);



    }

    public static void applyOilPaintingFilter(BufferedImage image, int brushSize) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int[] redValues = new int[256];
                int[] greenValues = new int[256];
                int[] blueValues = new int[256];

                for (int x = Math.max(0, i - brushSize); x < Math.min(width, i + brushSize); x++) {
                    for (int y = Math.max(0, j - brushSize); y < Math.min(height, j + brushSize); y++) {
                        Color color = new Color(image.getRGB(x, y));
                        redValues[color.getRed()]++;
                        greenValues[color.getGreen()]++;
                        blueValues[color.getBlue()]++;
                    }
                }

                int maxRed = findMaxIndex(redValues);
                int maxGreen = findMaxIndex(greenValues);
                int maxBlue = findMaxIndex(blueValues);
                Color newColor = new Color(maxRed, maxGreen, maxBlue);
                output.setRGB(i, j, newColor.getRGB());
            }
        }
        updateFilter(output);

    }
    private static int findMaxIndex(int[] values) {
        int maxIndex = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > values[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public static void updateFilter(BufferedImage output){
        newIMG = output;
        addToHistory(newIMG);
        imageLabel.setIcon(new ImageIcon(newIMG));
        imageLabel.revalidate();
        imageLabel.repaint();
    }

   public static BufferedImage resizeImage(BufferedImage originalImage, int newWidth, int newHeight) {
        if (newWidth != 0 && newHeight != 0) {
            Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);

            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = resizedImage.createGraphics();
            g2d.drawImage(scaledImage, 0, 0, null);
            g2d.dispose();
            return resizedImage;

        }
        System.out.println("eqals-0 ");
        return originalImage;

    }



}



