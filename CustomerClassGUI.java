package com.mycompany.oopprojectpharmacy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.*;

public class CustomerClassGUI extends JFrame {

    private ArrayList<Customer> customers;

    private JTextField nameTextField;
    private JTextField ageTextField;
    private JTextField phoneTextField;
    private JTextField genderTextField;
    private JTextField cnicTextField;
    private JTextField creditCardTextField;
    private JTextField addressTextField;

    public CustomerClassGUI() {
        customers = new ArrayList<>();

        setTitle("Customer Access");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(9, 2));

        JLabel nameLabel = new JLabel("Name:");
        nameTextField = new JTextField();
        JLabel ageLabel = new JLabel("Age:");
        ageTextField = new JTextField();
        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneTextField = new JTextField();
        JLabel genderLabel = new JLabel("Gender (Male/Female):");
        genderTextField = new JTextField();
        JLabel cnicLabel = new JLabel("CNIC Number:");
        cnicTextField = new JTextField();
        JLabel creditCardLabel = new JLabel("Credit Card Number:");
        creditCardTextField = new JTextField();
        JLabel addressLabel = new JLabel("Address:");
        addressTextField = new JTextField();

        JButton nextButton = new JButton("Next");
        nextButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
        nextButton.setBounds(120, 120, 80, 30);
        nextButton.setForeground(Color.BLACK);
        nextButton.setBackground(Color.orange);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                int age = Integer.parseInt(ageTextField.getText());
                String phoneNo = phoneTextField.getText();
                String gender = genderTextField.getText();
                String cnic = cnicTextField.getText();
                String creditCardNo = creditCardTextField.getText();
                String address = addressTextField.getText();

                Customer customer = new Customer(name, age, phoneNo, gender, cnic, creditCardNo, address);
                customers.add(customer);

                createOrderPanel();
            }
        });

        add(nameLabel);
        add(nameTextField);
        add(ageLabel);
        add(ageTextField);
        add(phoneLabel);
        add(phoneTextField);
        add(genderLabel);
        add(genderTextField);
        add(cnicLabel);
        add(cnicTextField);
        add(creditCardLabel);
        add(creditCardTextField);
        add(addressLabel);
        add(addressTextField);
        add(nextButton);

        setVisible(true);
    }

    private void createOrderPanel() {
        JFrame orderFrame = new JFrame("Order Management");
        orderFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        orderFrame.setSize(400, 400);
        orderFrame.setLocationRelativeTo(null);
        orderFrame.setLayout(new GridLayout(6, 1));

        JButton addButton = new JButton("Add Order");
        addButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
        addButton.setBounds(30, 30, 240, 30);
        addButton.setForeground(Color.BLACK);
        addButton.setBackground(Color.orange);

        JButton deleteButton = new JButton("Delete Order");
        deleteButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
        deleteButton.setBounds(30, 80, 240, 30);
        deleteButton.setForeground(Color.BLACK);
        deleteButton.setBackground(Color.orange);

        JButton updateButton = new JButton("Update Order");
        updateButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
        updateButton.setBounds(30, 130, 240, 30);
        updateButton.setForeground(Color.BLACK);
        updateButton.setBackground(Color.orange);

        JButton displayButton = new JButton("View Receipt");
        displayButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
        displayButton.setBounds(30, 180, 240, 30);
        displayButton.setForeground(Color.BLACK);
        displayButton.setBackground(Color.orange);

        JButton paymentButton = new JButton("Proceed Payment");
        paymentButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
        paymentButton.setBounds(30, 230, 240, 30);
        paymentButton.setForeground(Color.BLACK);
        paymentButton.setBackground(Color.orange);

        JButton shiftButton = new JButton("Shift to console");
        shiftButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
        shiftButton.setBounds(30, 280, 240, 30);
        shiftButton.setForeground(Color.BLACK);
        shiftButton.setBackground(Color.orange);

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
        exitButton.setBounds(30, 330, 240, 30);
        exitButton.setForeground(Color.BLACK);
        exitButton.setBackground(Color.orange);

        shiftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Scanner input = new Scanner(System.in);
                System.out.println("welcome to admin access");
                Customer c = new Customer();
                Person P = new Person();
                Medicine m = new Medicine();
                Order o = new Order();
                Order order = new Order();
                Receipt rec = new Receipt();
                c.customerDetails(input);
                ArrayList<Customer> customerList = new ArrayList<>();
                customerList.add(c);
                Customer.writeToFile(customerList);
                System.out.println("Customer data saved successfully.");
                System.out.println();
                System.out.println();

                int temp = 0;
                boolean invalidInput = false;
                do {
                    try {
                        System.out.println("1. Add order");
                        System.out.println("2. Delete order");
                        System.out.println("3. Update order");
                        System.out.println("4. View receipt");
                        System.out.println("5. Exit");
                        System.out.println("Enter your choice: ");
                        temp = input.nextInt();
                        switch (temp) {
                            case 1:
                                m.medStock();
                                order = o.addOrder();
                                o.writeOrdersToFile();
                                break;
                            case 2:
                                o.readOrdersFromFile();
                                o.toString();
                                o.deleteOrder();
                                break;
                            case 3:
                                o.readOrdersFromFile();
                                o.updateOrder();
                                o.writeOrdersToFile();
                                break;
                            case 4:
                                rec = new Receipt(c, order);
                                rec.printReceipt();
                                rec.writeToFile();
                                Payment pay = new Payment();
                                pay.selectMode(order);
                                pay.proceedPayment(order);
                                break;
                            case 5:
                                System.out.println("Order placed!");
                                break;
                            default:
                                System.out.println("Invalid choice!");
                                break;
                        }
                        invalidInput = false;
                    } catch (InputMismatchException ie) {
                        System.out.println("Invalid input! Please try again.");
                        invalidInput = true;
                        input.nextLine(); // Clear the invalid input from the scanner buffer
                    }
                } while (temp != 5 || invalidInput);
            }
        }

            );

            exitButton.addActionListener ( 
                new ActionListener() {
            @Override
                public void actionPerformed
                (ActionEvent e
                
                    ) {
                System.exit(0);
                }
            }

            );

            orderFrame.add (addButton);

            orderFrame.add (deleteButton);

            orderFrame.add (updateButton);

            orderFrame.add (displayButton);

            orderFrame.add (paymentButton);

            orderFrame.add (shiftButton);

            orderFrame.add (exitButton);

            orderFrame.setVisible (
        
    

    true);
    }

    public static void writeToFile(ArrayList<Customer> customers) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("CustomerData.txt", true));
            outputStream.writeObject(customers);
            outputStream.close();
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred while writing to file: " + e.getMessage());
        }
    }

    public static ArrayList<Customer> readFromFile() {
        ArrayList<Customer> customers = new ArrayList<>();

        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("CustomerData.txt"));

            try {
                while (true) {
                    customers = (ArrayList<Customer>) inputStream.readObject();
                    for (Customer customer : customers) {
                        System.out.println("---- Customer ----");
                        System.out.println("Name: " + customer.getName());
                        System.out.println("Age: " + customer.getAge());
                        System.out.println("Phone No: " + customer.getPhoneNo());
                        System.out.println("Gender: " + customer.getGender());
                        System.out.println("CNIC No: " + customer.getCNIC());
                        System.out.println("Credit Card No: " + customer.getcreditCardNo());
                        System.out.println("Address: " + customer.getaddress());
                    }
                }
            } catch (EOFException e) {
                System.out.println("Reached the end of file");
            }

            inputStream.close();

            if (customers.isEmpty()) {
                System.out.println("No customers in the record.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error occurred while reading from file: " + e.getMessage());
        }

        return customers;
    }

    public void deleteCustomerData(String name) {
        ArrayList<Customer> customers = readFromFile();
        boolean found = false;

        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            if (customer.getName().equalsIgnoreCase(name)) {
                customers.remove(i);
                writeToFile(customers);
                JOptionPane.showMessageDialog(null, "Customer data deleted successfully!");
                found = true;
                break;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(null, "No customer found with the given name.");
        }
    }

    public void displayCustomers() {
        ArrayList<Customer> customers = readFromFile();
        StringBuilder sb = new StringBuilder();

        for (Customer customer : customers) {
            sb.append("---- Customer ----\n");
            sb.append("Name: ").append(customer.getName()).append("\n");
            sb.append("Age: ").append(customer.getAge()).append("\n");
            sb.append("Phone No: ").append(customer.getPhoneNo()).append("\n");
            sb.append("Gender: ").append(customer.getGender()).append("\n");
            sb.append("CNIC No: ").append(customer.getCNIC()).append("\n");
            sb.append("Credit Card No: ").append(customer.getcreditCardNo()).append("\n");
            sb.append("Address: ").append(customer.getaddress()).append("\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString());
    }
}