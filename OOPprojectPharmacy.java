/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.oopprojectpharmacy;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class OOPprojectPharmacy {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean repeat = true;
        Pharmacy p = new Pharmacy("green health", 1557, "logo", "islamabad");
        p.saveToFile();
        System.out.println();
        p.loadFromFile();
        Customer c = new Customer();
        Person P = new Person();
        Medicine m = new Medicine();
        Order o = new Order();
        Order order = new Order();
        Receipt rec = new Receipt();
        do {
            try {
                while (true) {
                    System.out.println("<==============================>");
                    System.out.println();
                    P.showChoice();
                    int count = 0;
                    int choice = input.nextInt();
                    do {
                        try {
                            switch (choice) {
                                case 1: {
                                    Admin ad = new Admin();
                                    ad.display();
                                    int access = 0;
                                    System.out.println("Welcome to Admin Access");
                                    System.out.println("------------------------");
                                    System.out.println("Enter the password to proceed:");
                                    input.nextLine(); // Clear the newline character from the previous input
                                    String enteredPassword = input.nextLine();
                                    if (ad.isValidPassword(enteredPassword)) {
                                        System.out.println("Password accepted. Admin access granted.");
                                        ad.writeToFile();
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
                                            } catch (Exception e) {
                                                System.out.println("An error occurred: " + e.getMessage());
                                            }
                                        } while (opp != 5 || invalidInput);
                                    } else {
                                        System.out.println("Enter password again ");
                                    }
                                    break;
                                }
                                case 2: {
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
                                        } catch (InputMismatchException e) {
                                            System.out.println("Invalid input! Please try again.");
                                            invalidInput = true;
                                            input.nextLine(); // Clear the invalid input from the scanner buffer
                                        } catch (Exception e) {
                                            System.out.println("An error occurred: " + e.getMessage());
                                        }
                                    } while (temp != 5 || invalidInput);

                                    break;
                                }
                                default:
                                    System.out.println("Invalid choice!");
                                    count++;
                                    break;
                            }
                            P.showChoice();
                            choice = input.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter an integer value.");
                            repeat = false;
                            input.nextLine();
                        }
                    } while (repeat);
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please try again!");
                repeat = false;
                input.nextLine();
            }
        } while (true);
    }
}
