package edu.school21.spring;

public class RendererStandardImpl implements Renderer {
    public PreProcessor preProcessor;

    public RendererStandardImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void printRendered(String text) {
        System.out.println(preProcessor.changeCase(text));
    }
}
