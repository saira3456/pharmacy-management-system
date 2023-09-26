package com.mycompany.oopprojectpharmacy;

import java.util.Scanner;

public class Payment {

    private String paymentMode;
    private Order order;

    public Payment() {
        paymentMode = "";
    }

    public void selectMode(Order order) {
        Scanner input = new Scanner(System.in);
        System.out.println("Select mode of payment:");
        System.out.println("1. Pay through Cash");
        System.out.println("2. Pay through Credit Card");
        int choice = input.nextInt();
        input.nextLine(); // Consume the newline character

        switch (choice) {
            case 1:
                paymentMode = "Cash";
                proceedPayment(order);
                break;
            case 2:
                paymentMode = "Credit Card";
                break;
            default:
                System.out.println("Invalid choice. Defaulting to Cash payment.");
                paymentMode = "Cash";
                proceedPayment(order);
                break;
        }
    }

    public void proceedPayment(Order order) {
        this.order = order; // Assign the provided order to the class variable

        if (paymentMode.equals("Credit Card")) {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter your credit card number: ");
            String creditCardNumber = input.nextLine();
            // Process the credit card payment here
            System.out.println("Payment processed using credit card number: " + creditCardNumber);
            System.out.println("Enter your total balance: ");
            double balance = input.nextDouble();
            System.out.println("Remaining balance is: " + (balance - order.getTotalPrice()));
        } else {
            System.out.println("Payment processed in cash");
        }
    }
}
