package edu.school21.spring;

public class PreProcessorToUpperImpl implements PreProcessor{

    public PreProcessorToUpperImpl() {
    }

    @Override
    public String changeCase(String text) {
        return text.toUpperCase();
    }
}
