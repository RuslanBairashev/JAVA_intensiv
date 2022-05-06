package edu.school21.printer.app;

import com.beust.jcommander.JCommander;
import edu.school21.printer.logic.DrawImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class Application {

    public static void main(String[] args) throws IOException {

        String path = "/resources/it.bmp";

        BufferedImage image = ImageIO.read(Application.class.getResourceAsStream(path));
        DrawImage drawImage = new DrawImage(image);
        JCommander.newBuilder().addObject(drawImage).build().parse(args);
        drawImage.drawNewImage();
    }
}
