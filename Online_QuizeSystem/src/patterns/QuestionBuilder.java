package patterns;

import models.MultipleChoiceQuestion;
import models.Question;
import models.TrueFalseQuestion;

public class QuestionBuilder {
    private String text;
    private String[] options;
    private String correctAnswer;

    public QuestionBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public QuestionBuilder setOptions(String[] options) {
        this.options = options;
        return this;
    }

    public QuestionBuilder setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
        return this;
    }

    public Question buildMultipleChoice() {
        return new MultipleChoiceQuestion(text, options, correctAnswer);
    }

    public Question buildTrueFalse() {
        return new TrueFalseQuestion(text, correctAnswer);
    }
}
