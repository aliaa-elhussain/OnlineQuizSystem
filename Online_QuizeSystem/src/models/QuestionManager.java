package models;

import java.util.ArrayList;
import java.util.List;

public class QuestionManager {
    private static QuestionManager instance; // النسخة الوحيدة
    private List<Question> questions; // قائمة الأسئلة

    // Constructor خاص (Private)
    private QuestionManager() {
        questions = new ArrayList<>();
    }

    // دالة Singleton للحصول على النسخة الوحيدة
    public static QuestionManager getInstance() {
        if (instance == null) {
            instance = new QuestionManager();
        }
        return instance;
    }

    // إضافة سؤال
    public void addQuestion(Question question) {
        questions.add(question);  // التأكد من إضافة السؤال بشكل صحيح
    }

    // إرجاع قائمة الأسئلة
    public List<Question> getQuestions() {
        return questions;
    }
}
