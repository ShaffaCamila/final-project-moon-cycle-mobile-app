package com.example.final_moon_cycle;

public class DataClass2 {
    private String question;

    // Konstruktor default (diperlukan untuk Firebase)
    public DataClass2() {
    }

    // Konstruktor dengan parameter
    public DataClass2(String question) {
        this.question = question;
    }

    // Getter
    public String getQuestion() {
        return question;
    }

    // Setter (opsional, tapi disarankan untuk Firebase)
    public void setQuestion(String question) {
        this.question = question;
    }
}
