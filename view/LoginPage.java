package view;

import java.awt.EventQueue;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Button;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import exceptions.*;
import control.*;
import model.*;


public class LoginPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPasswordField passwordField;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginPage frame = new LoginPage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public LoginPage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 725, 532);
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("C:\\Users\\daddysatan\\Desktop\\hanamal2_with_exceptions\\hanamal2_with_exceptions\\src\\view\\hospital_bg.gif");
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        contentPane.setForeground(new Color(64, 128, 128));
        contentPane.setBackground(new Color(64, 128, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(64, 128, 128));
        panel_1.setBounds(0, 404, 709, 89);
        contentPane.add(panel_1);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 0, true), new LineBorder(new Color(0, 0, 0), 0, true)));
        panel.setBackground(new Color(128, 128, 128, 120));
        panel.setBounds(28, 38, 238, 202);
        contentPane.add(panel);

        passwordField = new JPasswordField();
        passwordField.setBounds(10, 103, 201, 20);
        panel.add(passwordField);

        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(10, 37, 201, 20);
        panel.add(textField);

        JLabel lblNewLabel = new JLabel("Enter UserName:");
        lblNewLabel.setBounds(10, 12, 201, 14);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Enter Password:");
        lblNewLabel_1.setBounds(10, 82, 201, 14);
        panel.add(lblNewLabel_1);

        Button button = new Button("Login");
        button.setBounds(42, 150, 141, 22);
        panel.add(button);

        // Add action listener for the login button
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });
    }

    /**
     * Handle the login logic.
     */
    private void handleLogin() {
        String username = textField.getText();
        String password = new String(passwordField.getPassword());

        try {
            // Check if the username or password is empty
            if (username.isEmpty() || password.isEmpty()) {
                throw new IllegalArgumentException("Username or Password cannot be empty.");
            }

            // Here you would typically query the database or data structure for user authentication

            // If user is null, throw ObjectDoesNotExist exception
            int userID=Hospital.getInstance().authenticate(username,password);
            // Check the type of user and redirect to the appropriate page
            if(userID==-1) {
            	 throw new InvalidUserDetails("The Entered Username Or password is invalid, Please Try Again");
            }
            if (userID==1) {
                // Redirect to AdminPage
                new AdminPage().setVisible(true);
                dispose();

            } else if (Hospital.getInstance().getStaffMember(userID)instanceof Doctor) {
                
                new DoctorPage().setVisible(true);
                dispose();
            } else if (Hospital.getInstance().getStaffMember(userID)instanceof Nurse) {
                // Redirect to NursePage
                new NursePage().setVisible(true);
                dispose();
           
        }
        }
         catch (IllegalArgumentException ex) {
            // Show a message dialog for empty fields
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        } catch (InvalidUserDetails ex) {
            // Show a message dialog for invalid user details
            javax.swing.JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    } catch (Exception ex) {
        // Catch any other exceptions
        javax.swing.JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + ex.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    }
}

    
