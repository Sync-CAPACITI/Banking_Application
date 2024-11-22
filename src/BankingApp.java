import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankingApp {
    private double balance = 0.0; // Initial balance
    private JLabel balanceLabel;
    private JTextField amountField;

    public BankingApp() {
        // Create main frame
        JFrame frame = new JFrame("Banking App");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 1));

        // Create components
        JLabel welcomeLabel = new JLabel("Welcome to Your Banking App!", SwingConstants.CENTER);
        balanceLabel = new JLabel("Current Balance: R0.00", SwingConstants.CENTER);
        amountField = new JTextField();
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");

        // Add components to the frame
        frame.add(welcomeLabel);
        frame.add(balanceLabel);
        frame.add(new JLabel("Enter Amount:", SwingConstants.CENTER));
        frame.add(amountField);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);
        frame.add(buttonPanel);

        // Add button actions
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDeposit();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleWithdraw();
            }
        });

        // Display the frame
        frame.setVisible(true);
    }

    private void handleDeposit() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount <= 0) {
                JOptionPane.showMessageDialog(null, "Please enter a positive amount!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            balance += amount;
            updateBalance();
            JOptionPane.showMessageDialog(null, "Successfully deposited R" + amount + "!", "Deposit Successful", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleWithdraw() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount <= 0) {
                JOptionPane.showMessageDialog(null, "Please enter a positive amount!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (amount < 10) {
                JOptionPane.showMessageDialog(null, "You cannot withdraw less than R10!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (amount > balance) {
                JOptionPane.showMessageDialog(null, "Insufficient funds! Your balance is R" + balance, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            balance -= amount;
            updateBalance();
            JOptionPane.showMessageDialog(null, "Successfully withdrew R" + amount + "!", "Withdrawal Successful", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateBalance() {
        balanceLabel.setText("Current Balance: R" + String.format("%.2f", balance));
        amountField.setText(""); // Clear input field
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BankingApp());
    }
}
