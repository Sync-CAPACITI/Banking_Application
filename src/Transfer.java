import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Transfer {
    private double savingsBalance = 1000.0; // Initial Savings Balance
    private double creditBalance = 500.0;  // Initial Credit Balance
    private JLabel savingsBalanceLabel;
    private JLabel creditBalanceLabel;
    private JComboBox<String> transferFromAccount;
    private JComboBox<String> transferToAccount;
    private JTextField transferAmount;
    private JPanel actionPanel;
    private JTextField transferAmountTxtField;

    public Transfer() {

        // Create main frame
        JFrame frame = new JFrame("Banking App");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 1));

        // Welcom Section
        JLabel welcomeLabel = new JLabel("Welcome to Your Banking App!", SwingConstants.CENTER);
        JPanel balancePanel = new JPanel(new GridLayout(2, 2));
        balancePanel.add(new JLabel("Savings Balance:"));
        savingsBalanceLabel = new JLabel("R" + String.format("%.2f", savingsBalance));
        balancePanel.add(savingsBalanceLabel);

        balancePanel.add(new JLabel("Credit Balance:"));
        creditBalanceLabel = new JLabel("R" + String.format("%.2f", creditBalance));
        balancePanel.add(creditBalanceLabel);

        // Transfer Section
        //JLabel transferMoneyLabel = new JLabel("Transfer Money", SwingConstants.CENTER);
        JPanel transferPanel = new JPanel(new GridLayout(5, 2));

        transferPanel.add(new JLabel("From Account:"));
        transferFromAccount = new JComboBox<>(new String[]{"Savings", "Credit"});
        transferPanel.add(transferFromAccount);

        transferPanel.add(new JLabel("To Account:"));
        transferToAccount = new JComboBox<>(new String[]{"Savings", "Credit"});
        transferPanel.add(transferToAccount);

        transferPanel.add(new JLabel("Transfer Amount:"));
        transferAmountTxtField = new JTextField();
        transferPanel.add(transferAmountTxtField);


        JButton transferBtn = new JButton("Transfer");
        transferPanel.add(transferBtn);

        // Add components to the frame
        frame.add(welcomeLabel);
        frame.add(balancePanel);
        frame.add(transferPanel);

        transferBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleTransfer();
            }
        });

        // Display the frame
        frame.setVisible(true);


        }
   /* private void updateBalance() {
        balanceLabel.setText("Current Balance: R" + String.format("%.2f", balance));
        amountField.setText(""); // Clear input field
    }*/

    private void handleTransfer() {
        String fromAccount = (String) transferFromAccount.getSelectedItem();
        String toAccount = (String) transferToAccount.getSelectedItem();

        if (fromAccount.equals(toAccount)) {
            JOptionPane.showMessageDialog(null, "Transfer accounts must be different.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double transferAmount = Double.parseDouble(transferAmountTxtField.getText());
            if (transferAmount <= 0) {
                JOptionPane.showMessageDialog(null, "Transfer amount must be greater than zero.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (fromAccount.equals("Savings") && savingsBalance >= transferAmount) {
                savingsBalance -= transferAmount;
                if (toAccount.equals("Credit")) {
                    creditBalance += transferAmount;
                }
            } else if (fromAccount.equals("Credit") && creditBalance >= transferAmount) {
                creditBalance -= transferAmount;
                if (toAccount.equals("Savings")) {
                    savingsBalance += transferAmount;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Insufficient balance in the selected account.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Update Balances
            updateBalances();
            JOptionPane.showMessageDialog(null,
                    String.format("Transferred R%.2f from %s to %s.", transferAmount, fromAccount, toAccount),
                    "Transfer Successful",
                    JOptionPane.INFORMATION_MESSAGE);
            transferAmountTxtField.setText("");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid transfer amount.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateBalances() {
        savingsBalanceLabel.setText("R" + String.format("%.2f", savingsBalance));
        creditBalanceLabel.setText("R" + String.format("%.2f", creditBalance));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Transfer());
    }
}
