package edu.school21.spring;

public class PreProcessorToLowerImpl implements PreProcessor{

    public PreProcessorToLowerImpl() {
    }

    @Override
    public String changeCase(String text) {
        return text.toLowerCase();
    }
}
