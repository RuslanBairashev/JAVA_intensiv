package edu.school21.printer.logic;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class DrawImage {
    private BufferedImage image;
    private String white;
    private String black;

    public DrawImage(BufferedImage image, String white, String black) {
        this.image = image;
        this.white = white;
        this.black = black;
    }

    public void drawNewImage() {
        WritableRaster wr = image.getRaster() ;
        int count = 0;
        for (int y=0 ; y < image.getHeight() ; y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int pixel = wr.getSample(x, y , 0);
                if (pixel == 1) {
                    System.out.print(white);
                    count++;
                } else {
                    System.out.print(black);
                    count++;
                }
                if (count == 16) {
                    System.out.println();
                    count = 0;
                }
            }
        }
    }
}
