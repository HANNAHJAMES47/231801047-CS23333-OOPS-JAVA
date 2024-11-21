import javax.swing.*;

public class MainMenuPage {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Main Menu");

        JButton addButton = new JButton("Add Contact");
        JButton updateButton = new JButton("Update Contact");
        JButton deleteButton = new JButton("Delete Contact");
        JButton viewButton = new JButton("View Contacts");

        addButton.setBounds(50, 50, 200, 30);
        updateButton.setBounds(50, 100, 200, 30);
        deleteButton.setBounds(50, 150, 200, 30);
        viewButton.setBounds(50, 200, 200, 30);

        addButton.addActionListener(e -> AddContact.main(null));
        updateButton.addActionListener(e -> UpdateContact.main(null));
        deleteButton.addActionListener(e -> DeleteContact.main(null));
        viewButton.addActionListener(e -> ViewContacts.main(null));

        frame.add(addButton);
        frame.add(updateButton);
        frame.add(deleteButton);
        frame.add(viewButton);

        frame.setSize(300, 350);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
