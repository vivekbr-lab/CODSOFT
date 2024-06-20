import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradeCalculatorGUI extends JFrame {

    private JTextField[] markFields;
    private JLabel totalMarksLabel;
    private JLabel averagePercentageLabel;
    private JLabel gradeLabel;

    public GradeCalculatorGUI(int numberOfSubjects) {
        setTitle("Grade Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(numberOfSubjects + 4, 2));

        markFields = new JTextField[numberOfSubjects];
        
        for (int i = 0; i < numberOfSubjects; i++) {
            add(new JLabel("Enter marks for subject " + (i + 1) + ":"));
            markFields[i] = new JTextField();
            add(markFields[i]);
        }

        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new CalculateButtonListener());
        add(calculateButton);

        totalMarksLabel = new JLabel("Total Marks: ");
        add(totalMarksLabel);

        averagePercentageLabel = new JLabel("Average Percentage: ");
        add(averagePercentageLabel);

        gradeLabel = new JLabel("Grade: ");
        add(gradeLabel);

        setVisible(true);
    }

    private class CalculateButtonListener implements ActionListener {
    
        public void actionPerformed(ActionEvent e) {
            int totalMarks = 0;
            int numberOfSubjects = markFields.length;

            try {
                for (JTextField markField : markFields) {
                    totalMarks += Integer.parseInt(markField.getText());
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid marks for all subjects.");
                return;
            }

            double averagePercentage = (double) totalMarks / numberOfSubjects;
            char grade;

            if (averagePercentage >= 90) {
                grade = 'A';
            } else if (averagePercentage >= 80) {
                grade = 'B';
            } else if (averagePercentage >= 70) {
                grade = 'C';
            } else if (averagePercentage >= 60) {
                grade = 'D';
            } else {
                grade = 'F';
            }

            totalMarksLabel.setText("Total Marks: " + totalMarks);
            averagePercentageLabel.setText("Average Percentage: " + averagePercentage + "%");
            gradeLabel.setText("Grade: " + grade);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
     
            public void run() {
             
                String input = JOptionPane.showInputDialog(null, "Enter the number of subjects:");
                try {
                    int numberOfSubjects = Integer.parseInt(input);
                    new GradeCalculatorGUI(numberOfSubjects);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number of subjects.");
                }
            }
        });
    }
}
