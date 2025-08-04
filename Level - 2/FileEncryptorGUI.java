import java.awt.*;
import java.io.*;
import java.nio.file.*;
import javax.swing.*;

public class FileEncryptorGUI extends JFrame {

    private JTextField filePathField;
    private JRadioButton encryptButton, decryptButton;

    public FileEncryptorGUI() {
        setTitle("File Encryption/Decryption");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set custom title color
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(0, 102, 128));
        JLabel titleLabel = new JLabel("File Encryption/Decryption");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);

        // Main content panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(255, 245, 230)); // soft cream color

        JLabel fileLabel = new JLabel("Input File:");
        fileLabel.setBounds(40, 30, 100, 30);
        fileLabel.setFont(new Font("Arial", Font.BOLD, 14));
        mainPanel.add(fileLabel);

        filePathField = new JTextField();
        filePathField.setBounds(130, 30, 200, 30);
        mainPanel.add(filePathField);

        JButton browseButton = new JButton("Browse..");
        browseButton.setBounds(340, 30, 90, 30);
        mainPanel.add(browseButton);

        encryptButton = new JRadioButton("Encrypt");
        decryptButton = new JRadioButton("Decrypt");
        encryptButton.setBounds(130, 80, 80, 30);
        decryptButton.setBounds(220, 80, 80, 30);
        encryptButton.setBackground(mainPanel.getBackground());
        decryptButton.setBackground(mainPanel.getBackground());

        ButtonGroup group = new ButtonGroup();
        group.add(encryptButton);
        group.add(decryptButton);
        encryptButton.setSelected(true);
        mainPanel.add(encryptButton);
        mainPanel.add(decryptButton);

        JButton processButton = new JButton("Process");
        processButton.setBounds(150, 140, 150, 40);
        processButton.setBackground(new Color(0, 102, 128));
        processButton.setForeground(Color.WHITE);
        processButton.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(processButton);

        // Browse button action
        browseButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                filePathField.setText(selectedFile.getAbsolutePath());
            }
        });

        // Process button action
        processButton.addActionListener(e -> {
            String path = filePathField.getText();
            if (path.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select a file.");
                return;
            }

            try {
                String content = new String(Files.readAllBytes(Paths.get(path)));
                String result = encryptButton.isSelected() ? encrypt(content) : decrypt(content);
                String outputPath = path.replace(".txt", encryptButton.isSelected() ? "_encrypted.txt" : "_decrypted.txt");
                Files.write(Paths.get(outputPath), result.getBytes());
                JOptionPane.showMessageDialog(this, "Output saved to: " + outputPath);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        add(titlePanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private String encrypt(String text) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            sb.append((char) (c + 3)); // Caesar Cipher
        }
        return sb.toString();
    }

    private String decrypt(String text) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            sb.append((char) (c - 3));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FileEncryptorGUI::new);
    }
}

