package models;

public class TrueFalseQuestion extends Question {
    public TrueFalseQuestion(String text, String correctAnswer) {
        super(text, new String[]{"True", "False"}, correctAnswer);
    }

    @Override
    public void displayQuestion() {
        System.out.println(text);
        System.out.println("1. True\n2. False");
    }
}
