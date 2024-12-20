package models;

public class MultipleChoiceQuestion extends Question {
    public MultipleChoiceQuestion(String text, String[] options, String correctAnswer) {
        super(text, options, correctAnswer);
    }

    @Override
    public void displayQuestion() {
        System.out.println(text);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }
}
