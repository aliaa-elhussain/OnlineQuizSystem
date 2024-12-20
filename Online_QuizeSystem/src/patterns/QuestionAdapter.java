package patterns;

import models.Question;

public class QuestionAdapter {
    private Question question;

    public QuestionAdapter(Question question) {
        this.question = question;
    }

    public String getQuestionText() {
        return question.getText(); // استخدام getter للوصول إلى text
    }

    public String[] getOptions() {
        return question.getOptions(); // استخدام getter للوصول إلى options
    }
}
