import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.Vector;

public class ViewContacts {
    public static void main(String[] args) {
        JFrame frame = new JFrame("View Contacts");

        String[] columnNames = {"ID", "Name", "Phone", "Email"};
        JTable table = new JTable();

        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM contacts")) {

            Vector<Vector<String>> data = new Vector<>();
            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(String.valueOf(rs.getInt("contact_id")));
                row.add(rs.getString("name"));
                row.add(rs.getString("phone"));
                row.add(rs.getString("email"));
                data.add(row);
            }

            table = new JTable(data, new Vector<>(java.util.Arrays.asList(columnNames)));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 450, 300);

        JButton backButton = new JButton("Back to Main Menu");
        backButton.setBounds(200, 340, 150, 30);
        backButton.addActionListener((ActionEvent e) -> {
            frame.dispose();
            MainMenuPage.main(null);
        });

        frame.add(scrollPane);
        frame.add(backButton);
        frame.setSize(500, 450);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
