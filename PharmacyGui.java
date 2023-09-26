package com.mycompany.oopprojectpharmacy;

import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class PharmacyGui extends JFrame {
    JLabel l1, l2, l3, l4,l5;

    PharmacyGui() {
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        l1 = new JLabel("GREEN PHARMACY");
        l1.setFont(new Font("Times New Roman", Font.BOLD, 34));
        l1.setForeground(Color.BLACK);
        l1.setBounds(130, 10, 500, 40);
        add(l1);
        l2= new JLabel("ID: 1775");
        l2.setFont(new Font("Times New Roman", Font.BOLD, 18));
        l2.setForeground(Color.BLACK);
        l2.setBounds(130, 60, 300, 40);
        add(l2);
        l3 = new JLabel("ADDRESS: Islamabad");
        l3.setFont(new Font("Times New Roman", Font.BOLD, 18));
        l3.setForeground(Color.BLACK);
        l3.setBounds(130, 110, 300, 40);
        add(l3);
        l4 = new JLabel("PHONE NO: 03455992638");
        l4.setFont(new Font("Times New Roman", Font.BOLD, 18));
        l4.setForeground(Color.BLACK);
        l4.setBounds(130, 160, 300, 40);
        add(l4);
        l5 = new JLabel("                   03135365527 ");
        l5.setFont(new Font("Times New Roman", Font.BOLD, 18));
        l5.setForeground(Color.BLACK);
        l5.setBounds(130, 210, 300, 40);
        add(l5);
        
        
        setSize(1000, 500);
        setVisible(true);
    }

    private void savePharmacyData(String pharmacyName, String location, String phoneNumber, String email, String password) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("PharmacyData.txt", true);
            fileOutputStream.write(("Pharmacy Name: " + pharmacyName + "\n").getBytes());
            fileOutputStream.write(("Location: " + location + "\n").getBytes());
            fileOutputStream.write(("Phone Number: " + phoneNumber + "\n").getBytes());
            fileOutputStream.write(("Email: " + email + "\n").getBytes());
            fileOutputStream.write(("Password: " + password + "\n").getBytes());
            fileOutputStream.close();
            JOptionPane.showMessageDialog(null, "Pharmacy Login successful!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error writing data to file.");
        }
    }
}