package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;
import java.awt.*;

public class HospitalManagmentSystem extends JFrame {

    public HospitalManagmentSystem() {
        // Set the title of the window
        setTitle("Hospital Management System");

        // Set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size of the window
        setSize(800, 600);

        // Create the top menu bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.decode("#2874A6"));
        JMenu homeMenu = new JMenu("Home");
        JMenu doctorsMenu = new JMenu("Doctors");
        JMenu nursesMenu = new JMenu("Nurses");
        JMenu patientsMenu = new JMenu("Patients");
        JMenu logoutMenu = new JMenu("Logout");

        menuBar.add(homeMenu);
        menuBar.add(doctorsMenu);
        menuBar.add(nursesMenu);
        menuBar.add(patientsMenu);
        menuBar.add(logoutMenu);

        // Set the menu bar
        setJMenuBar(menuBar);

        // Create the side menu panel with GridBagLayout
        JPanel sideMenu = new JPanel(new GridBagLayout());
        sideMenu.setBackground(Color.decode("#115f73"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);

        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");
        JButton button3 = new JButton("Button 3");
        JButton button4 = new JButton("Button 4");

        button1.setBackground(Color.decode("#b1d1db"));
        button2.setBackground(Color.decode("#b1d1db"));
        button3.setBackground(Color.decode("#b1d1db"));
        button4.setBackground(Color.decode("#b1d1db"));

        sideMenu.add(button1, gbc);
        sideMenu.add(button2, gbc);
        sideMenu.add(button3, gbc);
        sideMenu.add(button4, gbc);

        // Create the main content panel
        JPanel mainContent = new JPanel();
        mainContent.setLayout(new BorderLayout());
        mainContent.setBackground(Color.decode("#a3cbd4"));
        mainContent.add(new JLabel("Main Content Area"), BorderLayout.CENTER);

        // Add the side menu and main content to the frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(sideMenu, BorderLayout.WEST);
        getContentPane().add(mainContent, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        // Create and display the application window
        SwingUtilities.invokeLater(() -> {
            HospitalManagmentSystem frame = new HospitalManagmentSystem();
            frame.setVisible(true);
        });
    }
}
