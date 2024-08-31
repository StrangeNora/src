package panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.Doctor;
import model.Nurse;
import model.StaffMember;

import java.awt.*;

public class ProfilePage extends JPanel {
    private static final long serialVersionUID = 1L;

    public ProfilePage(StaffMember staffMember) {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#D4DEE8"));
        initializeProfile(staffMember);
    }

    private void initializeProfile(StaffMember staffMember) {
        // Load and scale the background image
        ImageIcon hBgIcon = new ImageIcon("C:/Users/layla/OneDrive/Desktop/java programming/LaylaIsGay/hanamal2_with_exceptions/src/view/norahospital.jpg");
        
        // Create a BackgroundImagePanel with the scaled image
        BackgroundImagePanel backgroundPanel = new BackgroundImagePanel(hBgIcon);
        add(backgroundPanel, BorderLayout.NORTH);

        // Create and configure the profile panel
        JPanel profilePanel = new JPanel(new BorderLayout());
        profilePanel.setBackground(Color.decode("#D4DEE8"));
        profilePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        profilePanel.setPreferredSize(new Dimension(800, 700));

        // Create the new panel "laylapic" with GridBagLayout
        JPanel laylapicPanel = new JPanel(new BorderLayout());
        laylapicPanel.setBackground(Color.decode("#D4DEE8")); // Set background color or other customization
        laylapicPanel.setPreferredSize(new Dimension(400, 700)); // Adjust size as needed

        // Profile Picture
        JLabel profilePictureLabel = new JLabel();
        profilePictureLabel.setPreferredSize(new Dimension(300, 300)); // Adjust size as needed
        profilePictureLabel.setHorizontalAlignment(JLabel.CENTER);

        // Add profile picture to the top of laylapicPanel
        laylapicPanel.add(profilePictureLabel, BorderLayout.NORTH);

        // Create a filler panel to push the profile picture to the top
        JPanel fillerPanel = new JPanel();
        fillerPanel.setBackground(Color.decode("#D4DEE8"));
        
        laylapicPanel.add(fillerPanel, BorderLayout.CENTER);

        // Create table model and JTable
        DefaultTableModel model = new DefaultTableModel();
        JTable profileTable = new JTable(model);

        // Define columns and data
        String[] columns = {"Label", "Value"};
        model.setColumnIdentifiers(columns);

        // Add profile info to the table
        addProfileInfo(model);

        // Remove the first and last rows
        if (model.getRowCount() > 0) {
            model.removeRow(0); // Remove the first row
        }
        if (model.getRowCount() > 0) {
            model.removeRow(model.getRowCount() - 1); // Remove the last row
        }

        // Customize table
        profileTable.setPreferredScrollableViewportSize(new Dimension(600, 400));
        profileTable.setFillsViewportHeight(true);
        profileTable.setRowHeight(30); // Adjust row height as needed

        // Set the background color of the JTable to match the profile panel
        profileTable.setBackground(Color.decode("#D4DEE8"));

        // Add table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(profileTable);
        profilePanel.add(scrollPane, BorderLayout.CENTER);

        add(profilePanel, BorderLayout.CENTER);

        // Add the "laylapic" panel to the east side of ProfilePage
        add(laylapicPanel, BorderLayout.EAST);

        // Initialize the specific profile based on staffMember type
        if (staffMember instanceof Doctor) {
            initializeDoctorProfile((Doctor) staffMember, profilePictureLabel);
        } else if (staffMember instanceof Nurse) {
            initializeNurseProfile((Nurse) staffMember, profilePictureLabel);
        }
    }

    private void addProfileInfo(DefaultTableModel model) {
        // Array of labels and sample values
        String[] labels = {"Welcome Back", "First Name", "Last Name", "Departments", "ID", "Gender",
                           "Address", "Phone Number", "Email", "Date of Birth", "Work Start Date",
                           "Finished Internship", "Salary", "Specialization", "License Number"};

        // Add rows to the table model
        for (String label : labels) {
            model.addRow(new Object[]{label + ":", ""});
        }
    }

    private void initializeDoctorProfile(Doctor doctor, JLabel profilePictureLabel) {
        // Initialization logic specific to Doctor
        profilePictureLabel.setIcon(loadScaledImage(doctor.getProfilePicture()));
    }

    private void initializeNurseProfile(Nurse nurse, JLabel profilePictureLabel) {
        // Initialization logic specific to Nurse
        profilePictureLabel.setIcon(loadScaledImage(nurse.getProfilePicture()));
    }

    private ImageIcon loadScaledImage(String imagePath) {
        if (imagePath != null && !imagePath.isEmpty()) {
            ImageIcon profileImage = new ImageIcon(imagePath);
            Image scaledImage = profileImage.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH); // Adjust size as needed
            return new ImageIcon(scaledImage);
        }
        return null;
    }
}
