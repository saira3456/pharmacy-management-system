package com.mycompany.oopprojectpharmacy;

import java.io.*;
/**
 *
 * @author HP
 */
import java.util.Date;

public class Receipt implements Serializable {

    private Customer customer;
    private Order order;
    private Date date;

    public Receipt() {

    }

    public Receipt(Customer customer, Order order) {
        this.customer = customer;
        this.order = order;
        this.date = new Date();
    }

    public void printReceipt() {
        System.out.println();
        System.out.println("<===============Order Receipt================>");
        System.out.println("Customer Name: " + customer.name);
        System.out.println("Customer Age: " + customer.age);
        System.out.println("Customer Phone No: " + customer.phoneNo);
        System.out.println("Customer Gender: " + customer.gender);
        System.out.println("Customer CNIC: " + customer.CNIC);
        System.out.println("Customer Credit Card No: " + customer.getcreditCardNo());
        System.out.println("Customer Address: " + customer.getaddress());
        System.out.println("Order Number: " + order.getOrderNumber());
        System.out.println("Medicine Name: " + order.getMedicineName());
        System.out.println("Number of Packets: " + order.getPackets());
        System.out.println("Total Price: " + order.getTotalPrice());
        System.out.println("Date: " + date);
        System.out.println("<=====================================================>");
        writeToFile();
    }

    public void writeToFile() {
        try {
            ObjectOutputStream ops = new ObjectOutputStream(new FileOutputStream("ReceiptData.txt", true));
            ops.writeObject(this);
            System.out.println("receipt is stored in the file");
            ops.close();
        } catch (IOException e) {
            System.out.println("Error writing receipt to file: " + e.getMessage());
        }
    }

    public static Receipt readFromFile(String filePath) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("ReceiptData.txt"));
            Receipt receipt = (Receipt) ois.readObject();
            ois.close();
            return receipt;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading receipt from file: " + e.getMessage());
        }
        return null;
    }
}
