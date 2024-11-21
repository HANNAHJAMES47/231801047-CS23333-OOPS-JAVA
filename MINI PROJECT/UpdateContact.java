import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class UpdateContact {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Update Contact");

        JLabel idLabel = new JLabel("Contact ID:");
        JLabel nameLabel = new JLabel("New Name:");
        JLabel phoneLabel = new JLabel("New Phone:");
        JLabel emailLabel = new JLabel("New Email:");

        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField emailField = new JTextField();

        JButton updateButton = new JButton("Update");
        JButton backButton = new JButton("Back to Main Menu");

        idLabel.setBounds(30, 30, 100, 30);
        idField.setBounds(140, 30, 150, 30);
        nameLabel.setBounds(30, 80, 100, 30);
        nameField.setBounds(140, 80, 150, 30);
        phoneLabel.setBounds(30, 130, 100, 30);
        phoneField.setBounds(140, 130, 150, 30);
        emailLabel.setBounds(30, 180, 100, 30);
        emailField.setBounds(140, 180, 150, 30);
        updateButton.setBounds(60, 230, 100, 30);
        backButton.setBounds(170, 230, 150, 30);

        updateButton.addActionListener(e -> {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();

            try (Connection con = DatabaseConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(
                         "UPDATE contacts SET name=?, phone=?, email=? WHERE contact_id=?")) {
                ps.setString(1, name);
                ps.setString(2, phone);
                ps.setString(3, email);
                ps.setInt(4, id);

                int rowsUpdated = ps.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(frame, "Contact updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Contact not found.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        backButton.addActionListener((ActionEvent e) -> {
            frame.dispose();
            MainMenuPage.main(null);
        });

        frame.add(idLabel);
        frame.add(idField);
        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(phoneLabel);
        frame.add(phoneField);
        frame.add(emailLabel);
        frame.add(emailField);
        frame.add(updateButton);
        frame.add(backButton);

        frame.setSize(350, 350);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
