package com.mycompany.oopprojectpharmacy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Medicine implements Serializable {

    private String medName;
    private int medID;
    private int stockAvailable;
    private String expDate;
    private double medPrice;

    private static List<Medicine> medList = new ArrayList<>();
    private static int nextID = 1;

    public Medicine() {
        medName = "null";
        medID = nextID++;
        stockAvailable = 0;
        expDate = "null";
        medPrice = 0;
    }

    public Medicine(String medName, int stockAvailable, String expDate, double medPrice) {
        this.medName = medName;
        this.medID = nextID++;
        this.stockAvailable = stockAvailable;
        this.expDate = expDate;
        this.medPrice = medPrice;
    }

    public String getName() {
        return medName;
    }

    public int getID() {
        return medID;
    }

    public int getStock() {
        return stockAvailable;
    }

    public boolean availability(String med) {
        for (Medicine medicine : medList) {
            if (medicine.getName().equals(med)) {
                if (medicine.getStock() == 0) {
                    System.out.println("Stock: " + medicine.getStock());
                    return false;
                } else {
                    System.out.println("Stock: " + medicine.getStock());
                    return true;
                }
            }
        }
        return false;
    }

    public double getPrice() {
        return medPrice;
    }

    public static void medStock() {
        medList.add(new Medicine("Aspirin", 18, "12/3/2004", 10.99));
        medList.add(new Medicine("Tylenol", 5, "5/6/2008", 7.99));
        medList.add(new Medicine("Advil", 7, "9/01/2002", 9.99));
        medList.add(new Medicine("Benadryl", 5, "3/4/2006", 6.99));
        medList.add(new Medicine("Zyrtec", 3, "7/9/2000", 15.99));
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("MedicineData.txt"))) {
            outputStream.writeObject(medList);
            System.out.println("Medicine stock data has been written to the file.");
        } catch (IOException e) {
            System.out.println("Error occurred while writing medicine stock data to the file: " + e.getMessage());
        }
    }

    public void addMed() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter name of Medicine: ");
        medName = input.nextLine();

        // Validate medicine name
        while (!isValidMedicineName(medName)) {
            System.out.println("Invalid medicine name. Please enter a valid name: ");
            medName = input.nextLine();
        }

        System.out.println("Price: ");
        while (!input.hasNextDouble()) {
            System.out.println("Invalid price. Please enter a valid price: ");
            input.next();
        }
        medPrice = input.nextDouble();
        input.nextLine(); // Consume the newline character

        String expDate;
        expDate = "";
        boolean validExpiryDate = false;
        while (!validExpiryDate) {
            System.out.println("Expiry Date (yyyy-MM-dd): ");
            expDate = input.nextLine();

            // Validate expiry date format
            if (expDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
                try {
                    // Parse the expiry date and check if it is a valid date
                    LocalDate parsedDate = LocalDate.parse(expDate);
                    if (parsedDate.isAfter(LocalDate.now())) {
                        validExpiryDate = true;
                    } else {
                        System.out.println("Invalid expiry date. Please enter a future date.");
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid expiry date. Please enter a valid date.");
                }
            } else {
                System.out.println("Invalid expiry date format. Please enter a date in the format yyyy-MM-dd.");
            }
        }

        System.out.println("Stock: ");
        while (!input.hasNextInt()) {
            System.out.println("Invalid stock. Please enter a valid stock: ");
            input.next();
        }
        stockAvailable = input.nextInt();

        // Check if a medicine with the same name already exists
        boolean medicineExists = false;
        for (Medicine medicine : medList) {
            if (medicine.getName().equalsIgnoreCase(medName)) {
                // Update the existing medicine
                medicine.medPrice = medPrice;
                medicine.expDate = expDate;
                medicine.stockAvailable = stockAvailable;
                medicineExists = true;
                break;
            }
        }

        if (!medicineExists) {
            // Add the new medicine
            medList.add(new Medicine(medName, stockAvailable, expDate, medPrice));
        }

        System.out.println("Medicine added successfully.");

        // Write the updated list to the file
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("MedicineData.txt"))) {
            outputStream.writeObject(medList);
            System.out.println("Medicine stock data has been updated in the file.");
        } catch (IOException e) {
            System.out.println("Error occurred while updating medicine stock data in the file: " + e.getMessage());
        }
    }

    public void delMed() {
        Scanner input = new Scanner(System.in);
        boolean validInput = false;
        int idToDelete = -1;

        while (!validInput) {
            System.out.println("Enter the ID of the medicine to delete: ");

            if (input.hasNextInt()) {
                idToDelete = input.nextInt();
                validInput = true;
            } else {
                System.out.println("Invalid input. Please enter a valid ID.");
                input.nextLine(); // Consume the invalid input
            }
        }

        int indexToDelete = -1;
        for (int i = 0; i < medList.size(); i++) {
            if (medList.get(i).getID() == idToDelete) {
                indexToDelete = i;
                break;
            }
        }

        if (indexToDelete != -1) {
            medList.remove(indexToDelete);
            stockAvailable--;
            System.out.println("Medicine with ID " + idToDelete + " has been deleted.");

            // Write the updated list to the file
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("MedicineData.txt"))) {
                outputStream.writeObject(medList);
                System.out.println("Medicine stock data has been updated in the file.");
            } catch (IOException e) {
                System.out.println("Error occurred while updating medicine stock data in the file: " + e.getMessage());
            }
        } else {
            System.out.println("Medicine with ID " + idToDelete + " not found.");
        }
    }

    public void manageStock() {
        Scanner input = new Scanner(System.in);
        int count = 0;
        while (count <= 3) {
            System.out.println("1. Add Medicine");
            System.out.println("2. Delete Medicine");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    addMed();
                    break;
                case 2:
                    delMed();
                    break;
                default:
                    System.out.println("Invalid choice! Choose again.");
                    count++;
            }
        }
    }

    public static void showMedStock() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("MedicineData.txt"))) {
            medList = (List<Medicine>) inputStream.readObject();

            System.out.println("Available Medicines:");
            for (Medicine med : medList) {
                System.out.println("Medicine ID: " + med.getID());
                System.out.println("Medicine Name: " + med.getName());
                System.out.println("Quantity Available: " + med.stockAvailable);
                System.out.println("Price: " + med.getPrice());
                System.out.println("-----------------------------");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error occurred while reading medicine stock data from the file: " + e.getMessage());
        }
    }

    public static boolean isValidMedicineName(String medName) {
        return medName.matches("[A-Za-z]+");
    }

    public Medicine searchMedicineByID(int id) {
        int count = 0;
        while (count <= 3) {
            for (Medicine med : medList) {
                if (med.getID() == id) {
                    return med;
                }
            }
            System.out.println("Medicine not found.");
            count++;
        }
        return null;
    }

    public void display() {
        System.out.println("Medicine Name: " + medName + "\nMedicine ID: " + medID + "\nStock Available: " + stockAvailable + "\nExpired Date: " + expDate + "\nMedicine Price: " + medPrice);
    }

    public boolean checkStock(String medName, int packets) {
        for (Medicine med : medList) {
            if (med.getName().equals(medName)) {
                return med.getStock() >= packets;
            }
        }
        return false;
    }

//    public static void main(String[] args) {
//        medStock();
//        showMedStock();
//    }
    public double getPrice(String medName, int packets) {

        for (Medicine medicine : medList) {
            if (medicine.getName().equals(medName)) {
                return medicine.medPrice * packets;
            }
        }
        return 0.0; // Return 0.0 if medicine with the given name is not found
    }

    public boolean stockUpdate(String medName, int packets) {
        for (Medicine medicine : medList) {
            if (medicine.getName().equals(medName)) {
                int newStock = medicine.stockAvailable - packets;
                if (newStock >= 0) {
                    medicine.stockAvailable = newStock;

                    // Write the updated list to the file
                    try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("MedicineData.txt"))) {
                        outputStream.writeObject(medList);
//                        System.out.println("Medicine stock data has been updated in the file.");
                    } catch (IOException e) {
                        System.out.println("Error occurred while updating medicine stock data in the file: " + e.getMessage());
                    }

                    return true;
                } else {
                    System.out.println("Insufficient stock for " + medName);
                    return false;
                }
            }
        }
        return false;
    }
}