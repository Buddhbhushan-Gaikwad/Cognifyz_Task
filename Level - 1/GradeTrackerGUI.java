import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

class Student {
    private String name;
    private double grade;

    public Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public double getGrade() {
        return grade;
    }
}

public class GradeTrackerGUI extends JFrame {
    private JTextField nameField;
    private JTextField gradeField;
    private JTextArea reportArea;
    private ArrayList<Student> students;

    public GradeTrackerGUI() {
        setTitle("Student Grade Tracker");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setResizable(true);
        setLocationRelativeTo(null); // center window

        students = new ArrayList<>();

        // === Components ===
        nameField = new JTextField(15);
        gradeField = new JTextField(6);
        JButton addButton = new JButton("Add Student");
        JButton reportButton = new JButton("Show Summary Report");
        reportArea = new JTextArea(10, 40);
        reportArea.setEditable(false);

        // === Main Panel with padding ===
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);

        // === Input Panel ===
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBackground(Color.WHITE);
        inputPanel.add(new JLabel("Student Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Grade:"));
        inputPanel.add(gradeField);
        mainPanel.add(inputPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // === Button Panel ===
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        buttonPanel.setBackground(Color.WHITE);

        // Style buttons
        addButton.setBackground(new Color(66, 133, 244));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setFont(new Font("Arial", Font.BOLD, 13));

        reportButton.setBackground(new Color(52, 168, 83));
        reportButton.setForeground(Color.WHITE);
        reportButton.setFocusPainted(false);
        reportButton.setFont(new Font("Arial", Font.BOLD, 13));

        buttonPanel.add(addButton);
        buttonPanel.add(reportButton);
        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // === Report Area (TextArea + Scroll) ===
        reportArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        reportArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        reportArea.setBackground(new Color(255, 255, 224)); // light yellow

        JScrollPane scrollPane = new JScrollPane(reportArea);
        scrollPane.setPreferredSize(new Dimension(450, 220));
        mainPanel.add(scrollPane);

        // === Footer Label (optional) ===
        JLabel footer = new JLabel("© 2025 Grade Tracker App", SwingConstants.CENTER);
        footer.setForeground(Color.GRAY);
        footer.setFont(new Font("SansSerif", Font.ITALIC, 11));
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(footer);

        // Add everything to frame
        add(mainPanel);

        // === Button Listeners ===
        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String gradeText = gradeField.getText().trim();

            if (name.isEmpty() || gradeText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both name and grade.");
                return;
            }

            try {
                double grade = Double.parseDouble(gradeText);
                students.add(new Student(name, grade));
                reportArea.setText("✅ Student added: " + name + " (Grade: " + grade + ")\n");
                nameField.setText("");
                gradeField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Grade must be a valid number.");
            }
        });

        reportButton.addActionListener(e -> showSummaryReport());
    }

    private void showSummaryReport() {
        if (students.isEmpty()) {
            reportArea.setText("No student records available.");
            return;
        }

        StringBuilder report = new StringBuilder("--- Summary Report ---\n");
        double total = 0, highest = Double.MIN_VALUE, lowest = Double.MAX_VALUE;

        for (Student s : students) {
            double grade = s.getGrade();
            report.append("Name: ").append(s.getName())
                  .append("     Grade: ").append(grade).append("\n");

            total += grade;
            if (grade > highest) highest = grade;
            if (grade < lowest) lowest = grade;
        }

        double average = total / students.size();

        report.append("\nAverage: ").append(String.format("%.2f", average))
              .append("\nHighest: ").append(highest)
              .append("\nLowest: ").append(lowest);

        reportArea.setText(report.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GradeTrackerGUI().setVisible(true);
        });
    }
}
