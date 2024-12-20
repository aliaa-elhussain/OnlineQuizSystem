package models;

public class ScoreManager {
    private static ScoreManager instance; // النسخة الوحيدة
    private int score;

    // Constructor خاص (Private)
    private ScoreManager() {
        score = 0;
    }

    // دالة Singleton للحصول على النسخة الوحيدة
    public static ScoreManager getInstance() {
        if (instance == null) {
            instance = new ScoreManager();
        }
        return instance;
    }

    // إضافة درجة
    public void addScore(int points) {
        score += points;
    }

    // الحصول على الدرجة
    public int getScore() {
        return score;
    }

    // إعادة تعيين الدرجة
    public void resetScore() {
        score = 0;
    }
}
