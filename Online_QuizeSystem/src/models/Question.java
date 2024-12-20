package models;

public abstract class Question {
    protected String text; // النص الأساسي للسؤال
    protected String[] options; // الخيارات المتاحة للسؤال
    protected String correctAnswer; // الإجابة الصحيحة

    public Question(String text, String[] options, String correctAnswer) {
        this.text = text;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public abstract void displayQuestion();

    public boolean checkAnswer(String answer) {
        return answer.equalsIgnoreCase(correctAnswer);
    }

    public String getText() {
        return text;
    }

    public String[] getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
