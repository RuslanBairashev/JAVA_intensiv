package edu.school21.spring;

public class RendererErrImpl implements Renderer {
    public PreProcessor preProcessor;

    public RendererErrImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void printRendered(String text) {
        System.err.println(preProcessor.changeCase(text));
    }
}
