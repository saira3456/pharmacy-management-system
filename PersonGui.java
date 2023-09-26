package com.mycompany.oopprojectpharmacy;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class PersonGui extends JFrame {

    JLabel l1;
    JButton adminButton, customerButton;

    PersonGui() {
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        l1 = new JLabel("Main menue");
        l1.setFont(new Font("Times New Roman", Font.BOLD, 30));
        l1.setForeground(Color.BLACK);
        l1.setBounds(130, 10, 300, 40);
        add(l1);

        adminButton = new JButton("Admin");
        adminButton.setBounds(120, 340, 120, 30);
        adminButton.setBackground(Color.orange);
        add(adminButton);

        customerButton = new JButton("Customer");
        customerButton.setBounds(120, 380, 120, 30);
        customerButton.setBackground(Color.orange);
        add(customerButton);

        adminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                AdminClassGUI adminGui = new AdminClassGUI();
                setVisible(false);
                dispose();
            }
        });

        customerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                CustomerClassGUI customerGui = new CustomerClassGUI();
                setVisible(false);
                dispose();
            }
        });

        setSize(400, 480);
        setVisible(true);
    }

    public static void Runner() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PersonGui();
            }
        });
    }
}
