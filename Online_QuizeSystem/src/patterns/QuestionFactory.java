package patterns;

import models.MultipleChoiceQuestion;
import models.Question;
import models.TrueFalseQuestion;

public class QuestionFactory {
    public static Question createQuestion(String type, String text, String[] options, String correctAnswer) {
        if (type.equalsIgnoreCase("MultipleChoice")) {
            return new MultipleChoiceQuestion(text, options, correctAnswer);
        } else if (type.equalsIgnoreCase("TrueFalse")) {
            return new TrueFalseQuestion(text, correctAnswer);
        }
        return null;
    }
}
