package com.github.trfiles;

import com.github.jsf.color.Color;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public interface Image {

    //void resize(int width, int height);
    //void removeBackground();
    //void rotate(int degrees);
    //void gaussianBlur(int sigma);
    //void toGrayScale();
    //void replace(Color before, Color after);
    //void drawShape(Shape shape);
    //void addText(int x, int y, String text);
    //void convert(Class<? extends Image> to);
    //void flip(Direction direction);
    //void crop(int width, int height);
    //Color getAverageColor();
    //MemoryImage convert(Class<? extends MemoryImage> reference);

    /**
     * Retrieve the 2D array of image pixels.
     *
     * @return the image pixels.
     */
    int[][] getPixels();

    /**
     * Get the pixel at the provided coordinate.
     *
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @return the pixel at the provided coordinate.
     * @throws ArrayIndexOutOfBoundsException if one of the coordinate is bigger than image size.
     */
    int getPixel(int x, int y) throws ArrayIndexOutOfBoundsException;

    /**
     * Get the pixel at the provided coordinate.
     *
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @return the pixel at the provided coordinate, or {@code -1} if one of the coordinate is bigger than image size.
     */
    default int getPixelOrMinusOne(int x, int y) {
        try {
            return getPixel(x, y);
        } catch (ArrayIndexOutOfBoundsException ignored) {
            return -1;
        }
    }

    /**
     * Set the pixel color at the provided coordinate.
     *
     * @param x     the x coordinate.
     * @param y     the y coordinate.
     * @param color the new pixel color.
     */
    void setPixel(int x, int y, Color color);

    /**
     * Set the pixel color at the provided coordinate.
     *
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @return the pixel color at the provided coordinate.
     * @throws ArrayIndexOutOfBoundsException if one of the coordinate is bigger than image size.
     */
    Color getColor(int x, int y) throws ArrayIndexOutOfBoundsException;

    /**
     * Set the pixel color at the provided coordinate.
     *
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @return the pixel color at the provided coordinate, or {@code null} if one of the coordinate is bigger than image size.
     */
    default Color getColorOrNull(int x, int y) {
        try {
            return getColor(x, y);
        } catch (ArrayIndexOutOfBoundsException ignored) {
            return null;
        }
    }

    /**
     * @return the image width. (or {@code getPixels().length}).
     */
    int getWidth();


    /**
     * @return the image height. (or {@code getPixels()[0].length})
     */
    int getHeight();


    /**
     * Write the current image to file.
     *
     * @param file the file to write to.
     */
    void save(File file) throws IOException;

    /**
     * Reload the image from image.
     *
     * @param image the image to reload from.
     */
    void reload(BufferedImage image);

    /**
     * Reload the image from file.
     *
     * @param file the file to reload from.
     */
    default void reload(File file) throws IOException {
        reload(ImageIO.read(file));
    }
}

