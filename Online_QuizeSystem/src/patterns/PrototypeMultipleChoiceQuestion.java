package patterns;

import models.MultipleChoiceQuestion;
import models.Question;

public class PrototypeMultipleChoiceQuestion extends MultipleChoiceQuestion {
    public PrototypeMultipleChoiceQuestion(String text, String[] options, String correctAnswer) {
        super(text, options, correctAnswer);
    }

    @Override
    public Question clone() {
        return new MultipleChoiceQuestion(this.text, this.options, this.correctAnswer);
    }
}
