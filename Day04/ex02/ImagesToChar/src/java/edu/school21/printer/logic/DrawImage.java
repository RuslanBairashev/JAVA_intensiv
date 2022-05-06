package edu.school21.printer.logic;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.BACK_COLOR;

@Parameters(separators = "=")
public class DrawImage {
    private BufferedImage image;
    @Parameter(names = "--white" , required = true)
    private String white;
    @Parameter(names = "--black", required = true)
    private String black;

    private int backWhite = 15;
    private int backBlack = 0;

    public DrawImage(BufferedImage image) {
        this.image = image;
    }

    public void drawNewImage() {
        setColors();
        WritableRaster wr = image.getRaster() ;
        int count = 0;
        int pixel = 0;
        for (int y = 0 ; y < image.getHeight() ; y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                pixel = wr.getSample(x, y , 0);
                if (pixel == 1) {
                    System.out.print(colorize(" ", BACK_COLOR(backWhite)));
                    count++;
                } else {
                    System.out.print(colorize(" ", BACK_COLOR(backBlack)));
                    count++;
                }
                if (count == 16) {
                    System.out.println();
                    count = 0;
                }
            }
        }
    }

    public int findColorByName(String color) {
        int res;
        switch (color) {
            case "BLACK":
                res = 0;
                break;
            case "MAROON":
                res = 1;
                break;
            case "GREEN":
                res = 2;
                break;
            case "OLIVE":
                res = 3;
                break;
            case "NAVY":
                res = 4;
                break;
            case "PURPLE":
                res = 5;
                break;
            case "TEAL":
                res = 6;
                break;
            case "SILVER":
                res = 7;
                break;
            case "GREY":
                res = 8;
                break;
            case "RED":
                res = 9;
                break;
            case "LIME":
                res = 10;
                break;
            case "YELLOW":
                res = 11;
                break;
            case "BLUE":
                res = 12;
                break;
            case "FUCHSIA":
                res = 13;
                break;
            case "AQUA":
                res = 14;
                break;
            case "WHITE":
                res = 15;
                break;
            default:
                System.out.println("Color not found! Default color used.");
                res = -1;
                break;
        }
        return res;
    }

    public void setColors() {
        int res = findColorByName(white);
        if (res >= 0) {
            backWhite = res;
        }
        res = findColorByName(black);
        if (res >= 0) {
            backBlack = res;
        }
    }
}
