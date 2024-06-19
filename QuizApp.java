import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApp extends JFrame {

    private static class Question {
        String question;
        String[] options;
        int correctAnswerIndex;

        Question(String question, String[] options, int correctAnswerIndex) {
            this.question = question;
            this.options = options;
            this.correctAnswerIndex = correctAnswerIndex;
        }
    }

    private Question[] questions = {
        new Question("who is the prime minister of India?", new String[]{"Joe Biden", "Narendra Modi", "Incumbent", "Han Duck-soo"}, 2),
        new Question("Who wrote 'Hamlet'?", new String[]{"Charles Dickens", "William Shakespeare", "Mark Twain", "Jane Austen"}, 1),
        new Question("What is the largest planet in our solar system?", new String[]{"Earth", "Jupiter", "Saturn", "Neptune"}, 1)
    };

    private int currentQuestionIndex = 0;
    private int score = 0;
    private Timer timer;
    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup optionsGroup;
    private JLabel timerLabel;
    private int timeLeft;

    public QuizApp() {
        setTitle("Quiz Application");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        questionLabel = new JLabel();
        add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
        optionButtons = new JRadioButton[4];
        optionsGroup = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            optionsGroup.add(optionButtons[i]);
            optionsPanel.add(optionButtons[i]);
        }
        add(optionsPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        timerLabel = new JLabel("Time left: 10", SwingConstants.CENTER);
        bottomPanel.add(timerLabel, BorderLayout.NORTH);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitButtonListener());
        bottomPanel.add(submitButton, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);

        showQuestion();
    }

    private void showQuestion() {
        if (currentQuestionIndex >= questions.length) {
            showResults();
            return;
        }

        Question question = questions[currentQuestionIndex];
        questionLabel.setText(question.question);
        for (int i = 0; i < question.options.length; i++) {
            optionButtons[i].setText(question.options[i]);
        }
        optionsGroup.clearSelection();

        timeLeft = 10;
        timerLabel.setText("Time left: " + timeLeft);

        if (timer != null) {
            timer.cancel();
        }

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
        
            public void run() {
                if (timeLeft > 0) {
                    timeLeft--;
                    timerLabel.setText("Time left: " + timeLeft);
                } else {
                    timer.cancel();
                    checkAnswer();
                }
            }
        }, 1000, 1000);
    }

    private void checkAnswer() {
        int selectedOptionIndex = -1;
        for (int i = 0; i < optionButtons.length; i++) {
            if (optionButtons[i].isSelected()) {
                selectedOptionIndex = i;
                break;
            }
        }

        if (selectedOptionIndex == questions[currentQuestionIndex].correctAnswerIndex) {
            score++;
        }

        currentQuestionIndex++;
        showQuestion();
    }

    private void showResults() {
        StringBuilder resultsSummary = new StringBuilder();
        resultsSummary.append("Final Score: ").append(score).append("/").append(questions.length).append("\n\n");
        for (int i = 0; i < questions.length; i++) {
            resultsSummary.append("Question ").append(i + 1).append(": ").append(questions[i].question).append("\n");
            resultsSummary.append("Correct Answer: ").append(questions[i].options[questions[i].correctAnswerIndex]).append("\n\n");
        }

        JOptionPane.showMessageDialog(this, resultsSummary.toString(), "Quiz Results", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private class SubmitButtonListener implements ActionListener {
     
        public void actionPerformed(ActionEvent e) {
            timer.cancel();
            checkAnswer();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            QuizApp quizApp = new QuizApp();
            quizApp.setVisible(true);
        });
    }
}
