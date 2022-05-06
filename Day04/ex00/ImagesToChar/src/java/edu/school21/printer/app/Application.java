package edu.school21.printer.app;

import edu.school21.printer.logic.DrawImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class Application {
    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.out.println("Error: invalid number of arguments!");
            return;
        }
        String path = args[0];
        String white = args[1];
        String black = args[2];

        BufferedImage image = ImageIO.read(new File(path));
        DrawImage drawImage = new DrawImage(image, white, black);
        drawImage.drawNewImage();
    }
}
