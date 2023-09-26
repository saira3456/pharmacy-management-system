package com.mycompany.oopprojectpharmacy;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer extends Person implements Serializable {

    private String creditCardNo;
    private String address;

    public Customer() {
        super();
        creditCardNo = "null";
        address = "null";
    }

    public Customer(String name, int age, String phoneNo, String gender, String CNIC, String creditCardNo, String address) {
        super(name, age, phoneNo, gender, CNIC);
        this.creditCardNo = creditCardNo;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCNIC() {
        return CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public String getcreditCardNo() {
        return creditCardNo;
    }

    public void setcreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

    public String getaddress() {
        return address;
    }

    public void setaddress(String address) {
        this.name = address;
    }

    public boolean isValidCreditCard(String creditCardNo) {
        if (creditCardNo.length() != 16) {
            return false;
        }
        for (int i = 0; i < creditCardNo.length(); i++) {
            if (!Character.isDigit(creditCardNo.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidAddress(String address) {
        String regex = "^[A-Za-z]+(\\s[A-Za-z]+)*$";
        Pattern p = Pattern.compile(regex);
        if (address == null) {
            return false;
        }
        Matcher m = p.matcher(address);
        return m.matches();
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Credit Card No: " + creditCardNo + "\nAddress: " + address);
    }

    public void customerDetails(Scanner input) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name:");
        setName(sc.nextLine());

        while (!Person.isValidUsername(getName())) {
            System.out.println("Invalid Name! Enter your Name again:");
            setName(input.nextLine());
        }

        System.out.println("Enter your age:");

        while (!input.hasNextInt()) {
            System.out.println("Invalid input! Please enter your age as a whole number:");
            input.nextLine(); // Consume the invalid input
        }

        setAge(input.nextInt());
        input.nextLine(); // Consume the newline character

        while (!Person.isValidAge(getAge())) {
            System.out.println("Invalid Age! Enter your Age again:");

            while (!input.hasNextInt()) {
//                System.out.println("Invalid input! Please enter your age as a whole number:");
                sc.nextLine(); // Consume the invalid input
            }

            setAge(input.nextInt());
            input.nextLine(); // Consume the newline character
        }

        System.out.println("Enter your phone number:");
        setPhoneNo(input.nextLine());

        while (!Person.isValidNumber(getPhoneNo())) {
            System.out.println("Invalid Number! Enter your phone Number again:");
            setPhoneNo(input.nextLine());
        }

        System.out.println("Enter your gender (male/female):");
        setGender(input.next());
        input.nextLine(); // Consume the newline character

        while (!Person.isValidGender(getGender())) {
            System.out.println("Choose between the given options!");
            setGender(input.nextLine());
        }

        System.out.println("Enter your CNIC number:");
        setCNIC(input.nextLine());

        while (!Person.isValidCNIC(getCNIC())) {
            System.out.println("Invalid CNIC! Enter your CNIC number again:");
            setCNIC(input.nextLine());
        }

        System.out.println(
                "Enter your credit card number:");
        creditCardNo = input.nextLine();

        while (!isValidCreditCard(creditCardNo)) {
            System.out.println("Invalid credit card number. Enter your credit card number again:");
            creditCardNo = input.nextLine();
        }

        System.out.println(
                "Enter your address:");
        address = input.nextLine();

//        while (!isValidAddress(address)) {
//            System.out.println("Invalid address. Enter your address again:");
//            address = input.nextLine();
//        }
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
                    System.out.println("Phone No : " + customer.getPhoneNo());
                    System.out.println("CNIC no : " + customer.getCNIC());
                    System.out.println("Gender : " + customer.getGender());
                    System.out.println("Credit Card No : " + customer.getcreditCardNo());
                    System.out.println("Address : " + customer.getaddress());
                    System.out.println("Name : " + customer.getName());
                    System.out.println("Age : " + customer.getAge());
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
                System.out.println("Customer data deleted successfully.");
                writeToFile(customers);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("No customer found with the given name.");
        }
    }

    public void performCustomerManagement() {
        Scanner input = new Scanner(System.in);
        ArrayList<Customer> customers = new ArrayList<>();

        System.out.println("Customers Data!");

        while (true) {
            System.out.println("\n1. Add Customer\n2. Delete Customer\n3. Display All Customers\n4. Exit");
            System.out.println("Enter your choice:");

            int choice = input.nextInt();
            input.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    Customer customer = new Customer();
                    customer.customerDetails(input);
                    customers.add(customer);
                    writeToFile(customers);
                    break;
                case 2:
                    System.out.println("Enter the name of the customer to delete:");
                    String nameToDelete = input.nextLine();
                    deleteCustomerData(nameToDelete);
                    break;
                case 3:
                    customers = readFromFile();
                    break;
                case 4:
                    System.out.println("Exiting the program. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}