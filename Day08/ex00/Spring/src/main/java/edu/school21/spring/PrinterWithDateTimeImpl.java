package edu.school21.spring;

import java.time.LocalDateTime;

public class PrinterWithDateTimeImpl implements Printer{
    private Renderer renderer;
    private String prefix = LocalDateTime.now().toString();

    public PrinterWithDateTimeImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void print(String str) {
        renderer.printRendered(prefix + " " + str);
    }
}
