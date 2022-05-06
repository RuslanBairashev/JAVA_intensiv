package edu.school21.spring;

public class PrinterWithPrefixImpl implements Printer {
    private Renderer renderer;
    private String prefix = "";

    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void print(String str) {
        renderer.printRendered(prefix + " " + str);
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
