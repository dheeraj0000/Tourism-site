import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class LoginApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginApp::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200); // Set the initial window size

        JPanel panel = new JPanel(new GridBagLayout());
        frame.add(panel);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5); // Add padding

        Font labelFont = new Font("Arial", Font.PLAIN, 28); // Specify your desired font size
        Font fieldFont = new Font("Arial", Font.PLAIN, 28); // Specify your desired font size

        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setFont(labelFont);
        JTextField usernameField = new JTextField(15);
        usernameField.setFont(fieldFont);

        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setFont(labelFont);
        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setFont(fieldFont);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 20)); // Specify your desired font size and style

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(usernameLabel, constraints);

        constraints.gridx = 1;
        panel.add(usernameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(passwordLabel, constraints);

        constraints.gridx = 1;
        panel.add(passwordField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2; // Allow the button to span two columns
        constraints.fill = GridBagConstraints.CENTER; // Center-align the button
        panel.add(loginButton, constraints);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredUsername = usernameField.getText();
                char[] enteredPassword = passwordField.getPassword();

                if (isValidLogin(enteredUsername, String.valueOf(enteredPassword))) {
                    JOptionPane.showMessageDialog(frame, "Login successful!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Login failed. Please try again.");
                }
            }
        });

        frame.pack(); // Adjusts the window size to fit its contents
        frame.setLocationRelativeTo(null); // Center the window on the screen
        frame.setVisible(true);
    }

    private static boolean isValidLogin(String username, String password) {
        // In this example, any non-empty username and password are considered valid.
        return !username.isEmpty() && !password.isEmpty();
    }
}
