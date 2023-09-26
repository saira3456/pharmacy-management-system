package com.mycompany.oopprojectpharmacy;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class AdminClassGUI {

    private JFrame adminAccessWindow;
    private JFrame adminFunctionsWindow;

    public AdminClassGUI() {
        createLoginScreen();
    }

    private void createLoginScreen() {
        adminAccessWindow = new JFrame();
        adminAccessWindow.setLayout(null);
        adminAccessWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel adminLabel = new JLabel("ADMIN ASSESS");
        adminLabel.setBounds(70, 10, 200, 30);
        adminLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel passwordLabel = new JLabel("password:");
        passwordLabel.setBounds(10, 60, 200, 30);

        final JTextField passwordField = new JTextField();
        passwordField.setBounds(130, 60, 120, 30);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(120, 120, 80, 30);
        loginButton.setBackground(Color.orange);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = passwordField.getText();
                try {
                    if (isValidPassword(password)) {
                        openAdminFunctionsWindow();
                        adminAccessWindow.dispose();
                    } else {
                        JOptionPane.showMessageDialog(adminAccessWindow, "Invalid password. Please try again.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(adminAccessWindow, "An error occurred. Please try again.");
                    ex.printStackTrace();
                }
            }
        });

        adminAccessWindow.add(adminLabel);
        adminAccessWindow.add(passwordLabel);
        adminAccessWindow.add(passwordField);
        adminAccessWindow.add(loginButton);

        adminAccessWindow.setSize(300, 200);
        adminAccessWindow.setLocationRelativeTo(null);
        adminAccessWindow.setVisible(true);
    }

    private boolean isValidPassword(String password) {
        return password.equals("admin@123");
    }

    private void openAdminFunctionsWindow() {
        adminFunctionsWindow = new JFrame();
        adminFunctionsWindow.setLayout(null);
        adminFunctionsWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton addMedicineButton = new JButton("Add Medicine");
        JButton deleteMedicineButton = new JButton("Delete Medicine");
        JButton viewStockButton = new JButton("View Medicine Stock");
        JButton manageCustomerButton = new JButton("Manage Customer");
        JButton receiptRecordButton = new JButton("Receipt Record");
        JButton shiftButton = new JButton("Shift to Console");
        JButton exitButton = new JButton("Exit");

        addMedicineButton.setBounds(30, 30, 240, 30);
        addMedicineButton.setForeground(Color.BLACK);
        addMedicineButton.setBackground(Color.orange);
        deleteMedicineButton.setBounds(30, 80, 240, 30);
        deleteMedicineButton.setForeground(Color.BLACK);
        deleteMedicineButton.setBackground(Color.orange);
        viewStockButton.setBounds(30, 130, 240, 30);
        viewStockButton.setForeground(Color.BLACK);
        viewStockButton.setBackground(Color.orange);
        manageCustomerButton.setBounds(30, 180, 240, 30);
        manageCustomerButton.setForeground(Color.BLACK);
        manageCustomerButton.setBackground(Color.orange);
        receiptRecordButton.setBounds(30, 230, 240, 30);
        receiptRecordButton.setForeground(Color.BLACK);
        receiptRecordButton.setBackground(Color.orange);
        shiftButton.setBounds(30, 280, 240, 30);
        shiftButton.setForeground(Color.BLACK);
        shiftButton.setBackground(Color.orange);
        exitButton.setBounds(30, 330, 240, 30);
        exitButton.setForeground(Color.BLACK);
        exitButton.setBackground(Color.orange);

        adminFunctionsWindow.add(addMedicineButton);
        adminFunctionsWindow.add(deleteMedicineButton);
        adminFunctionsWindow.add(viewStockButton);
        adminFunctionsWindow.add(manageCustomerButton);
        adminFunctionsWindow.add(receiptRecordButton);
        adminFunctionsWindow.add(shiftButton);
        adminFunctionsWindow.add(exitButton);

        adminFunctionsWindow.setSize(300, 400);
        adminFunctionsWindow.setLocationRelativeTo(null);
        adminFunctionsWindow.setVisible(true);

        shiftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Scanner input = new Scanner(System.in);
                Medicine m = new Medicine();
                Customer c = new Customer();
                Person P = new Person();
                Order o = new Order();
                Order order = new Order();
                Receipt rec = new Receipt();
                int opp = 0;
                boolean invalidInput = false;
                do {
                    try {
                        System.out.println();
                        System.out.println("<==================================================>");
                        System.out.println("1. Add medicine");
                        System.out.println("2. Delete medicine");
                        System.out.println("3. View medicine stock");
                        System.out.println("4. Receipt Records");
                        System.out.println("5. Exit");
                        System.out.println("Enter your choice: ");
                        opp = input.nextInt();
                        switch (opp) {
                            case 1: {
                                m.medStock();
                                m.addMed();
                                break;
                            }
                            case 2: {
                                m.showMedStock();
                                m.delMed();
                                break;
                            }
                            case 3: {
                                m.medStock();
                                m.showMedStock();
                                break;
                            }
                            case 4: {
                                Receipt r = Receipt.readFromFile("ReceiptData.txt");
                                if (r != null) {
                                    r.printReceipt();
                                }
                                break;
                            }
                            case 5: {
                                break;
                            }
                            default: {
                                System.out.println("Invalid choice!");
                                break;
                            }
                        }
                    }catch (Exception ie) {
                        System.out.println("An error occurred: " + ie.getMessage());
                    }
                } while (opp != 5 || invalidInput);
            }

        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminFunctionsWindow.dispose();
            }
        });
    }


}