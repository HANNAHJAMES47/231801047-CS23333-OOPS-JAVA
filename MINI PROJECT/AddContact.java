import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class AddContact {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Add Contact");

        JLabel nameLabel = new JLabel("Name:");
        JLabel phoneLabel = new JLabel("Phone:");
        JLabel emailLabel = new JLabel("Email:");

        JTextField nameField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField emailField = new JTextField();

        JButton addButton = new JButton("Add");
        JButton backButton = new JButton("Back to Main Menu");

        nameLabel.setBounds(30, 30, 100, 30);
        nameField.setBounds(140, 30, 150, 30);
        phoneLabel.setBounds(30, 80, 100, 30);
        phoneField.setBounds(140, 80, 150, 30);
        emailLabel.setBounds(30, 130, 100, 30);
        emailField.setBounds(140, 130, 150, 30);
        addButton.setBounds(60, 180, 100, 30);
        backButton.setBounds(170, 180, 150, 30);

        addButton.addActionListener(e -> {
            String name = nameField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();

            try (Connection con = DatabaseConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(
                         "INSERT INTO contacts (name, phone, email) VALUES (?, ?, ?)")) {
                ps.setString(1, name);
                ps.setString(2, phone);
                ps.setString(3, email);

                ps.executeUpdate();
                JOptionPane.showMessageDialog(frame, "Contact added successfully!");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        backButton.addActionListener((ActionEvent e) -> {
            frame.dispose();
            MainMenuPage.main(null);
        });

        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(phoneLabel);
        frame.add(phoneField);
        frame.add(emailLabel);
        frame.add(emailField);
        frame.add(addButton);
        frame.add(backButton);

        frame.setSize(350, 300);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
