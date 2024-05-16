import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ATMGUI extends JFrame {
    private JTextField withdrawField;
    private JTextField depositField;
    private JTextField transferField;
    private JLabel balanceLabel;
    private double checkingBalance = 1000; // Initial balances
    private double savingsBalance = 2000;
    private double currentBalance = 1500;
    private double creditBalance = 500;

    public ATMGUI() {
        setTitle("ATM Machine");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JLabel withdrawLabel = new JLabel("Withdraw:");
        panel.add(withdrawLabel);
        withdrawField = new JTextField();
        panel.add(withdrawField);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(withdrawField.getText());
                withdraw("Checking", amount);
            }
        });
        panel.add(withdrawButton);

        JLabel depositLabel = new JLabel("Deposit:");
        panel.add(depositLabel);
        depositField = new JTextField();
        panel.add(depositField);

        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(depositField.getText());
                deposit("Checking", amount);
            }
        });
        panel.add(depositButton);

        JLabel transferLabel = new JLabel("Transfer:");
        panel.add(transferLabel);
        transferField = new JTextField();
        panel.add(transferField);

        JButton transferButton = new JButton("Transfer");
        transferButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(transferField.getText());
                transfer("Savings", "Checking", amount);
            }
        });
        panel.add(transferButton);

        JLabel balanceTitleLabel = new JLabel("Account Balances:");
        panel.add(balanceTitleLabel);
        balanceLabel = new JLabel("Checking: $" + checkingBalance + ", Savings: $" + savingsBalance + ", Current: $"
                + currentBalance + ", Credit: $" + creditBalance);
        panel.add(balanceLabel);

        add(panel);
        setVisible(true);
    }

    private void withdraw(String account, double amount) {
        double fee = 0.0;
        if (account.equalsIgnoreCase("Checking")) {
            fee = amount * 0.02; // 2% fee for checking withdrawal
            if (amount + fee > checkingBalance) {
                JOptionPane.showMessageDialog(this, "Insufficient balance in Checking account.");
                return;
            }
            checkingBalance -= (amount + fee);
        }
        if (account.equalsIgnoreCase("Savings")) {
            fee = amount * 0.01; // 1% fee for savings withdrawal
            if (amount + fee > savingsBalance) {
                JOptionPane.showMessageDialog(this, "Insufficient balance in Savings account.");
                return;
            }
            savingsBalance -= (amount + fee);
        }
        if (account.equalsIgnoreCase("Credit")) {
            fee = amount * 0.03; // 3% fee for credit withdrawal
            if (amount + fee > creditBalance) {
                JOptionPane.showMessageDialog(this, "Insufficient balance in Credit account.");
                return;
            }
            creditBalance -= (amount + fee);
        }
        updateBalanceLabel();
        JOptionPane.showMessageDialog(this, "Withdrawn $" + amount + " from " + account + " account.");
    }

    private void deposit(String account, double amount) {
        if (account.equalsIgnoreCase("Checking")) {
            checkingBalance += amount;
        }

        if (account.equalsIgnoreCase("Savings")) {
            checkingBalance += amount;
        }

        if (account.equalsIgnoreCase("Credit")) {
            checkingBalance += amount;
        }

        updateBalanceLabel();
        JOptionPane.showMessageDialog(this, "Deposited $" + amount + " to " + account + " account.");
    }

    private void transfer(String fromAccount, String toAccount, double amount) {
        double fee = 0.0;
        if (fromAccount.equalsIgnoreCase("Checking")) {
            fee = amount * 0.02; // 2% fee for checking transfer
            if (amount + fee > checkingBalance) {
                JOptionPane.showMessageDialog(this, "Insufficient balance in Checking account.");
                return;
            }
            checkingBalance -= (amount + fee);
        }

        if (toAccount.equalsIgnoreCase("Savings")) {
            if (amount > checkingBalance) {
                JOptionPane.showMessageDialog(this, "Insufficient balance in Savings account.");
                return;
            }
            savingsBalance -= amount;
        }
        if (toAccount.equalsIgnoreCase("Credit")) {
            fee = amount * 0.03; // 3% fee for credit transfer

            if (amount + fee > checkingBalance) {
                JOptionPane.showMessageDialog(this, "Insufficient balance in Credit account.");
                return;
            }
            creditBalance -= amount;
        }
        updateBalanceLabel();
        JOptionPane.showMessageDialog(this,
                "Transferred $" + amount + " from " + fromAccount + " to " + toAccount + " account.");
    }

    private void updateBalanceLabel() {
        balanceLabel.setText("Checking: $" + checkingBalance + ", Savings: $" + savingsBalance + ", Current: $"
                + currentBalance + ", Credit: $" + creditBalance);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ATMGUI();
            }
        });
    }
}
