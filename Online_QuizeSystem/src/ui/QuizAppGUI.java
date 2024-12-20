package ui;

import models.QuestionManager;
import models.Question;
import patterns.QuestionBuilder;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;

public class QuizAppGUI {
    private JFrame frame;
    private JPanel panel;
    private QuestionManager questionManager;
    private int currentQuestionIndex = 0;
    private int score = 0;

    public QuizAppGUI() {
        questionManager = QuestionManager.getInstance();
        setupInitialScreen();
    }

    private void setupInitialScreen() {
        frame = new JFrame("Online Quiz");
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(255, 240, 245));

        // عنوان الترحيب
        JLabel welcomeLabel = new JLabel("Welcome to Online Quiz!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 32));
        welcomeLabel.setForeground(new Color(255, 105, 180));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0)); // لرفع العنوان قليلاً
        panel.add(welcomeLabel, BorderLayout.NORTH);

        // لوحة الأزرار
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(new Color(255, 240, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 15, 20, 15);

        // زر المعلم
        JButton teacherButton = createCurvedButton("I'm a Teacher", new Color(255, 182, 193), 20);
        teacherButton.setPreferredSize(new Dimension(250, 80));
        teacherButton.addActionListener(e -> openTeacherPanel());
        gbc.gridy = 0;
        buttonPanel.add(teacherButton, gbc);

        // زر الطالب
        JButton studentButton = createCurvedButton("I'm a Student", new Color(255, 192, 203), 20);
        studentButton.setPreferredSize(new Dimension(250, 80));
        studentButton.addActionListener(e -> startQuiz());
        gbc.gridy = 1;
        buttonPanel.add(studentButton, gbc);

        panel.add(buttonPanel, BorderLayout.CENTER);

        frame.add(panel);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private JButton createCurvedButton(String text, Color background, int fontSize) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(background);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Color.BLACK);
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
            }
        };
        button.setFont(new Font("Verdana", Font.PLAIN, fontSize));
        button.setForeground(Color.BLACK);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        return button;
    }

    private void openTeacherPanel() {
        panel.removeAll();
        panel.setLayout(new GridLayout(0, 1, 10, 10));
        panel.setBackground(new Color(255, 240, 245));

        JLabel addQuestionLabel = new JLabel("Add a New Question", SwingConstants.CENTER);
        addQuestionLabel.setFont(new Font("Arial", Font.BOLD, 24));
        addQuestionLabel.setForeground(new Color(219, 112, 147));

        JTextField questionField = new JTextField();
        JTextField option1Field = new JTextField("Option 1");
        JTextField option2Field = new JTextField("Option 2");
        JTextField option3Field = new JTextField("Option 3");
        JTextField option4Field = new JTextField("Option 4");
        JTextField correctAnswerField = new JTextField("Correct Answer");

        JButton addQuestionButton = createCurvedButton("Add Question", new Color(255, 182, 193), 18);
        addQuestionButton.addActionListener(e -> {
            String text = questionField.getText();
            String[] options = new String[]{
                    option1Field.getText(),
                    option2Field.getText(),
                    option3Field.getText(),
                    option4Field.getText()
            };
            String correctAnswer = correctAnswerField.getText();

            // إنشاء السؤال باستخدام QuestionBuilder
            Question question = new QuestionBuilder()
                    .setText(text)
                    .setOptions(options)
                    .setCorrectAnswer(correctAnswer)
                    .buildMultipleChoice();  // بناء السؤال

            // إضافة السؤال إلى QuestionManager
            QuestionManager.getInstance().addQuestion(question);

            JOptionPane.showMessageDialog(frame, "Question added successfully!");
        });

        JButton backButton = createCurvedButton("Back", new Color(240, 128, 128), 18);
        backButton.addActionListener(e -> setupInitialScreen());

        panel.add(addQuestionLabel);
        panel.add(questionField);
        panel.add(option1Field);
        panel.add(option2Field);
        panel.add(option3Field);
        panel.add(option4Field);
        panel.add(correctAnswerField);
        panel.add(addQuestionButton);
        panel.add(backButton);

        panel.revalidate();
        panel.repaint();
    }

    private void startQuiz() {
        if (currentQuestionIndex >= questionManager.getQuestions().size()) {
            showScore();
            return;
        }

        panel.removeAll();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(255, 240, 245));

        Question question = questionManager.getQuestions().get(currentQuestionIndex);

        JLabel questionLabel = new JLabel(question.getText(), SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 24));
        questionLabel.setForeground(new Color(199, 21, 133));
        panel.add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        ButtonGroup buttonGroup = new ButtonGroup();
        for (String option : question.getOptions()) {
            JRadioButton optionButton = new JRadioButton(option);
            optionButton.setBackground(new Color(255, 240, 245));
            optionButton.setFont(new Font("Arial", Font.PLAIN, 18));
            buttonGroup.add(optionButton);
            optionsPanel.add(optionButton);
        }
        panel.add(optionsPanel, BorderLayout.CENTER);

        JButton submitButton = createCurvedButton("Submit", new Color(255, 182, 193), 18);
        submitButton.setBorder(BorderFactory.createEmptyBorder(10, 0, 40, 0)); 
        submitButton.addActionListener(e -> {
            for (AbstractButton button : Collections.list(buttonGroup.getElements())) {
                if (button.isSelected() && question.checkAnswer(button.getText())) {
                    score++;
                }
            }
            currentQuestionIndex++;
            startQuiz();
        });

        panel.add(submitButton, BorderLayout.SOUTH);
        panel.revalidate();
        panel.repaint();
    }

    private void showScore() {
        panel.removeAll();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(255, 240, 245));

        JLabel scoreLabel = new JLabel("Your Score: " + score, SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 28));
        scoreLabel.setForeground(new Color(219, 112, 147));

        JButton backButton = createCurvedButton("Back to Main Menu", new Color(240, 128, 128), 18);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 0, 30, 0)); 
        backButton.addActionListener(e -> {
            currentQuestionIndex = 0;
            score = 0;
            setupInitialScreen();
        });

        panel.add(scoreLabel, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        panel.revalidate();
        panel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QuizAppGUI());
    }
}
