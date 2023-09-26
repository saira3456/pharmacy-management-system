package com.mycompany.oopprojectpharmacy;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Order implements Serializable {

    private List<Order> orders;
    private String medicineName;
    private int orderNumber;
    private int packets;
    private double totalPrice;

    public Order() {
        orders = new ArrayList();
    }

    private Order(String medicineName, int orderNumber, int packets, double totalPrice) {
        this.medicineName = medicineName;
        this.orderNumber = orderNumber;
        this.packets = packets;
        this.totalPrice = totalPrice;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public int getPackets() {
        return packets;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setPackets(int packets) {
        this.packets = packets;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Order addOrder() {
        Medicine med = new Medicine();
        med.showMedStock();

        Scanner input = new Scanner(System.in);
        System.out.println("Enter the name of the medicine: ");
        String medicineName = input.nextLine();

        // Check if the medicine is present in the medical stock
        if (med.availability(medicineName)) {
            System.out.println("Enter the number of packets of tablets: ");
            int packets = input.nextInt();
            input.nextLine(); // Consume the newline character

            // Check if the stock is sufficient
            if (med.checkStock(medicineName, packets)) {
                int orderNumber = generateOrderNumber();
                double totalPrice = med.getPrice(medicineName, packets) * packets;

                Order order = new Order(medicineName, orderNumber, packets, totalPrice);
                orders.add(order);
                System.out.println("Order added successfully: " + order);
                return order; // Return the created Order object
            } else {
                System.out.println("Insufficient stock for the requested quantity.");
            }
        } else {
            System.out.println("Medicine not available in stock.");
        }

        return null; // Return null if the order couldn't be added
    }

    private int generateOrderNumber() {
        Random random = new Random();
        return random.nextInt(1000) + 1;
    }

    public void addMedicineToOrder(String medicineName) {
        Medicine med = new Medicine();

        if (med.availability(medicineName)) {
            System.out.println("Enter the number of packets of tablets: ");
            Scanner input = new Scanner(System.in);
            int packets = input.nextInt();
            input.nextLine();
            if (med.checkStock(medicineName, packets)) {
                double totalPrice = med.getPrice(medicineName, packets) * packets;

                this.medicineName = medicineName;
                this.packets = packets;
                this.totalPrice = totalPrice;
            } else {
                System.out.println("Insufficient stock for the requested quantity.");
            }
        } else {
            System.out.println("Medicine not available in stock.");
        }
    }

    public void deleteMedicineFromOrder(String medicineName) {
        // Check if the medicine is present in the order
        if (this.medicineName.equalsIgnoreCase(medicineName)) {
            this.medicineName = null;
            this.packets = 0;
            this.totalPrice = 0;
            System.out.println("Medicine deleted from the order.");
            writeOrdersToFile(); // Update the file after deleting the medicine
        } else {
            System.out.println("Medicine not found in the order.");
        }
    }

    @Override
    public String toString() {
        return "<======================Order===========================>"
                + "\nmedicineName='" + medicineName
                + "\norderNumber=" + orderNumber
                + "\npackets=" + packets
                + "\ntotalPrice=" + totalPrice
                + "\n<====================================================>";
    }

    public void deleteOrder() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the order number to delete: ");
        int orderNumber = input.nextInt();
        input.nextLine(); // Consume the newline character

        Iterator<Order> iterator = orders.iterator();
        boolean found = false;
        while (iterator.hasNext()) {
            Order order = iterator.next();
            if (order.getOrderNumber() == orderNumber) {
                iterator.remove();
                System.out.println("Order deleted successfully: " + order);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Order not found with the given order number.");
        }

        // Write the updated orders to the file
        writeOrdersToFile();
    }

    public Order updateOrder() {
        Scanner input = new Scanner(System.in);
        int orderNumber = 0;
        boolean validOrderNumber = false;

        do {
            System.out.println("Enter the order number to update: ");
            try {
                orderNumber = input.nextInt();
                input.nextLine(); // Consume the newline character
                validOrderNumber = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid order number! Please enter a numeric value.");
                input.nextLine(); // Clear the invalid input from the scanner
            }
        } while (!validOrderNumber);

        boolean found = false;
        Order updatedOrder = null;

        for (Order order : orders) {
            if (order.getOrderNumber() == orderNumber) {
                System.out.println("Order found: " + order);
                System.out.println("1. Add medicine");
                System.out.println("2. Delete medicine");

                int choice = 0;
                boolean validChoice = false;

                do {
                    System.out.println("Enter your choice: ");

                    try {
                        choice = input.nextInt();
                        input.nextLine(); // Consume the newline character
                        validChoice = true;
                    } catch (InputMismatchException ex) {
                        System.out.println("Invalid choice! Please enter a numeric value.");
                        input.nextLine(); // Clear the invalid input from the scanner
                    }
                } while (!validChoice);

                switch (choice) {
                    case 1:
                        System.out.println("Enter the name of the medicine to add: ");
                        String medicineName = input.nextLine();
                        order.addMedicineToOrder(medicineName);
                        System.out.println("Medicine added to the order.");
                        updatedOrder = order;
                        break;
                    case 2:
                        System.out.println("Enter the name of the medicine to delete: ");
                        medicineName = input.nextLine();
                        order.deleteMedicineFromOrder(medicineName);
                        System.out.println("Medicine deleted from the order.");
                        updatedOrder = order;
                        break;
                    default:
                        System.out.println("Invalid choice!");
                        break;
                }

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Order not found with the given order number.");
        }

        writeOrdersToFile();

        return updatedOrder;
    }

    public void writeOrdersToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("orders.txt"))) {
            for (Order order : orders) {
                String orderString = order.toString();
                if (!orderString.startsWith("<======================Order===========================>")) {
                    writer.println(orderString);
                }
            }
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred while writing to file: " + e.getMessage());
        }
    }

    public void readOrdersFromFile() {
        try (Scanner fileScanner = new Scanner(new File("orders.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                Order order = parseOrderFromString(line);
                orders.add(order);
            }
            System.out.println("Orders data read from file successfully.");
        } catch (IOException e) {
            System.out.println("Error reading orders data from file: " + e.getMessage());
        }
    }

    private Order parseOrderFromString(String line) {
        String[] parts = line.split("~");
        int orderNumber = Integer.parseInt(parts[1]);
        String medicineName = parts[2];
        int packets = Integer.parseInt(parts[3]);
        double totalPrice = Double.parseDouble(parts[4]);

        return new Order(medicineName, orderNumber, packets, totalPrice);
    }
}