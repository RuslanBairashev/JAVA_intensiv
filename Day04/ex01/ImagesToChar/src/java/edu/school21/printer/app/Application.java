package edu.school21.printer.app;

import edu.school21.printer.logic.DrawImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class Application {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Error: invalid number of arguments!");
            return;
        }
        String path = "/resources/it.bmp";
        String white = args[0];
        String black = args[1];

        BufferedImage image = ImageIO.read(Application.class.getResourceAsStream(path));
        DrawImage drawImage = new DrawImage(image, white, black);
        drawImage.drawNewImage();
    }
}
