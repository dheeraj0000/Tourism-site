import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.LineBorder;

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
    private JTextArea resultArea;

    public TouristGUI() {
        frame = new JFrame("Tourist Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.YELLOW);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(7,7,7,7);   //spacing blw lines

        // Add a heading label
        JLabel headingLabel = new JLabel("Hello!! Jobers, Here you get the weekend plan ");
        Font headingFont = new Font("Arial", Font.BOLD,40);
        headingLabel.setFont(headingFont);
		
		//Font daysFont = new Font("Arial", Font.PLAIN, 20);
		//daysField.setFont(daysFont);


        String[] choices = {"1 Day", "2 Days", "3 Days"};
		//Font comboBoxFont = new Font("Arial", Font.PLAIN, 40);
        //choiceComboBox.setFont(comboBoxFont);
        choiceComboBox = new JComboBox<>(choices);

        daysField = new JTextField(10);
        budgetField = new JTextField(10);
        locationField = new JTextField(10);
        membersField = new JTextField(10);
        calculateButton = new JButton("Calculate");
		calculateButton.setBackground(Color.red);
        resultArea = new JTextArea(6,21);
        resultArea.setEditable(false);
		
		Font resultFont = new Font("Arial", Font.PLAIN, 24);
        resultArea.setFont(resultFont);
		
		
		//Font inputFieldFont = new Font("Arial", Font.PLAIN, 40);
        //daysField.setFont(inputFieldFont);
        //budgetField.setFont(inputFieldFont);
        //membersField.setFont(inputFieldFont);

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2; // Span two columns for the heading
        c.anchor = GridBagConstraints.CENTER;
        panel.add(headingLabel, c);
		
		        c.gridy = 1;
        panel.add(Box.createVerticalStrut(10), c); // Add 20 pixels of vertical spacing

        // Add the components below the heading
        c.gridy = 2;
        c.gridwidth = 1;

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1; // Reset gridwidth
        panel.add(new JLabel("Choose Duration:"), c);
        c.gridx = 1;
        panel.add(choiceComboBox, c);

        // Rest of your code...
		
        c.gridx = 0;
        c.gridy = 2;
        panel.add(new JLabel("Enter Days (Sunday Monday Saturday):"), c);
		//createSquareLabel("Days:", c.gridx, c.gridy, panel);
        c.gridx = 1;
        panel.add(daysField, c);
        daysField.setFont(headingFont);
        c.gridx = 0;
        c.gridy = 3;
        panel.add(new JLabel("Enter Budget:"), c);
        c.gridx = 1;
        panel.add(budgetField, c);
		budgetField.setFont(headingFont);

        //c.gridx = 0;
        //c.gridy = 3;
        //panel.add(new JLabel("Enter Location (kerala, Tamil Nadu, Varanasi, Jammu, Delhi):"), c);
        //c.gridx = 1;
        //panel.add(locationField, c);

        c.gridx = 0;
        c.gridy = 4;
        panel.add(new JLabel("Enter Number of Members:"), c);
        c.gridx = 1;
        panel.add(membersField, c);
        membersField.setFont(headingFont);
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 2;
		//c.gridheight=2;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(calculateButton, c);

        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(new JLabel("Results:"), c);

        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(new JScrollPane(resultArea), c);

        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    calculate();
                } catch (NoBudgetException ex) {
                    resultArea.setText(ex.getMessage());
                }
            }
        });

        frame.add(panel);
        //frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        //frame.setVisible(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Open in full screen
        //frame.setUndecorated(true); // Remove window decorations
        frame.setVisible(true);
    }




   private void calculate() throws NoBudgetException {
        String choiceString = (String) choiceComboBox.getSelectedItem();
        int choice = Integer.parseInt(choiceString.split(" ")[0]);
        String days = daysField.getText().toLowerCase();
        int budget = Integer.parseInt(budgetField.getText());
        String location = locationField.getText().toLowerCase();
        int numberOfMembers = Integer.parseInt(membersField.getText());

        if (choice < 1 || choice > 3) {
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

         resultArea.setText("Places to visit: " + placeToVisit + "\nTotal amount: " + totalAmount + "\nEnjoy your Trip :) Visit our Page Again");
	}

    // Rest of your code...

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TouristGUI());
    }
}
