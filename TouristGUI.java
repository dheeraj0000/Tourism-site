import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class NoBudgetException extends Exception {
    public NoBudgetException() {
        super("Budget not sufficient! Not available");
    }
}

public class TouristGUI {
    private JFrame frame;
    private JPanel panel;
    private JComboBox<String> choiceComboBox;
    private JTextField daysField;
    private JTextField budgetField;
    private JTextField locationField;
    private JTextField membersField;
    private JButton calculateButton;

    public TouristGUI() {
        frame = new JFrame("Tourist Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setBackground(Color.YELLOW); // Change the color as per your preference
        panel.setLayout(new GridLayout(7, 2));

        String[] choices = {"1 Day", "2 Days", "3 Days"};
        choiceComboBox = new JComboBox<>(choices);

        daysField = new JTextField();
        budgetField = new JTextField();
        locationField = new JTextField();
        membersField = new JTextField();
        calculateButton = new JButton("Calculate");

        panel.add(new JLabel("Choose Duration:"));
        panel.add(choiceComboBox);
        panel.add(new JLabel("Enter Days (Sunday Monday Saturday):"));
        panel.add(daysField);
        panel.add(new JLabel("Enter Budget:"));
        panel.add(budgetField);
        panel.add(new JLabel("Enter Location (kerala,Tamil Nadu,Varanasi,Jammu,Delhi):"));
        panel.add(locationField);
        panel.add(new JLabel("Enter Number of Members:"));
        panel.add(membersField);
        panel.add(new JLabel("")); // Empty label for spacing
        panel.add(calculateButton);

        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    calculate();
                } catch (NoBudgetException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.add(panel);
        frame.setSize(1500, 900); // Increased the height for a more spacious look
        frame.pack(); // Automatically size the frame
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }

        private void calculate() throws NoBudgetException {
        String choiceString = (String) choiceComboBox.getSelectedItem();
        int choice = Integer.parseInt(choiceString.split(" ")[0]);
        String days = daysField.getText().toLowerCase();
        int budget = Integer.parseInt(budgetField.getText());
        String location = locationField.getText().toLowerCase();
        int numberOfMembers = Integer.parseInt(membersField.getText());

        if (choice < 1 || choice > 3) { // Adjusted the choice range
            throw new IllegalArgumentException("Invalid choice");
        }

        String placeToVisit = "";
        int totalAmount = 0;

        switch (choice) {
            case 1:
                if (budget < 1000 || budget > 2000) {
                    throw new NoBudgetException();
                }

                if (days.equals("sunday")) {
                    placeToVisit = "Bhogatha Waterfalls";
                    totalAmount = budget * numberOfMembers;
                } else if (days.equals("saturday")) {
                    placeToVisit = "Pakal";
                    totalAmount = budget * numberOfMembers;
                } else if (days.equals("monday")) {
                    placeToVisit = "Bhimneni Waterfalls";
                    totalAmount = budget * numberOfMembers;
                } else {
                    throw new IllegalArgumentException("Invalid day input");
                }
                break;

            case 2:
                String[] dayArray = days.split(" ");
                if (dayArray.length != 2) {
                    throw new IllegalArgumentException("Invalid days input");
                }
                if ((dayArray[0].equals("saturday") && dayArray[1].equals("sunday")) ||
                        (dayArray[1].equals("saturday") && dayArray[0].equals("sunday"))) {
                    if (budget >= 3000 && budget <= 5000) {
                        placeToVisit = "Vizag";
                        totalAmount = budget * numberOfMembers;
                    } else {
                        throw new NoBudgetException();
                    }
                } else if ((dayArray[0].equals("sunday") && dayArray[1].equals("monday")) ||
                        (dayArray[1].equals("sunday") && dayArray[0].equals("monday"))) {
                    if (budget >= 3000 && budget <= 5000) { // Use && for logical AND
                        placeToVisit = "Nagarjuna Sagar";
                        totalAmount = budget * numberOfMembers;
                    } else {
                        throw new NoBudgetException();
                    }
                } else {
                    throw new IllegalArgumentException("Invalid days input");
                }
                break;

            case 3:
                String[] dayArray3 = days.split(" ");
                if (dayArray3.length != 3) {
                    throw new IllegalArgumentException("Invalid days input");
                }
                if ((dayArray3[0].equals("saturday") || dayArray3[0].equals("sunday") || dayArray3[0].equals("monday")) &&
                        (dayArray3[1].equals("saturday") || dayArray3[1].equals("sunday") || dayArray3[1].equals("monday")) &&
                        (dayArray3[2].equals("saturday") || dayArray3[2].equals("sunday") || dayArray3[2].equals("monday"))) {
                    if (budget >= 4000 && budget <= 7000) {
                        placeToVisit = "Ooty, Shirdi, or Tirupati";
                        totalAmount = budget * numberOfMembers;
                    } else {
                        throw new NoBudgetException();
                    }
                } else {
                    throw new IllegalArgumentException("Invalid days input");
                }
                break;

            default:
                throw new IllegalArgumentException("Invalid choice");
        }

        // Display results
        System.out.println("Places to visit: " + placeToVisit);
        System.out.println("Total amount: " + totalAmount);
        System.out.println("Enjoy your Trip :) Visit our Page Again");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TouristGUI());
    }
}
