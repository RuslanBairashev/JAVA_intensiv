package edu.school21.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

        Printer printer = context.getBean("printerWithPrefixErrUpper", Printer.class);
        printer.print("Hello!");
        Printer printer2 = context.getBean("printerWithDateTimeStandardLower", Printer.class);
        printer2.print("Hello!");

        PreProcessor preProcessor = new PreProcessorToUpperImpl();
        Renderer renderer = new RendererErrImpl(preProcessor);
        PrinterWithPrefixImpl printer3 = new PrinterWithPrefixImpl(renderer);
        printer3.setPrefix("Prefix");
        printer.print("Hello!");

        preProcessor = new PreProcessorToLowerImpl();
        renderer = new RendererStandardImpl(preProcessor);
        PrinterWithDateTimeImpl printer4 = new PrinterWithDateTimeImpl(renderer);
        printer4.print("Hello!");
    }
}
