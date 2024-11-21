import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class DeleteContact {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Delete Contact");

        JLabel idLabel = new JLabel("Contact ID:");
        JTextField idField = new JTextField();
        JButton deleteButton = new JButton("Delete");
        JButton backButton = new JButton("Back to Main Menu");

        idLabel.setBounds(30, 30, 100, 30);
        idField.setBounds(140, 30, 150, 30);
        deleteButton.setBounds(60, 80, 100, 30);
        backButton.setBounds(170, 80, 150, 30);

        deleteButton.addActionListener(e -> {
            int id = Integer.parseInt(idField.getText());

            try (Connection con = DatabaseConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement("DELETE FROM contacts WHERE contact_id=?")) {
                ps.setInt(1, id);

                int rowsDeleted = ps.executeUpdate();
                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(frame, "Contact deleted successfully!");
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
        frame.add(deleteButton);
        frame.add(backButton);

        frame.setSize(350, 200);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
