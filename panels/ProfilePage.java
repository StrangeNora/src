package panels;

import javax.swing.*;
import enums.Specialization;
import model.Department;
import model.Doctor;
import model.Nurse;
import model.StaffMember;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

public class ProfilePage extends JPanel {

    private static final long serialVersionUID = 1L;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel departmentLabel;
    private JLabel idLabel;
    private JLabel genderLabel;
    private JLabel addressLabel;
    private JLabel phoneNumberLabel;
    private JLabel emailLabel;
    private JLabel dateOfBirthLabel;
    private JLabel workStartDateLabel;
    private JLabel internshipStatusLabel;
    private JLabel salaryLabel;
    private JLabel specializationLabel;
    private JLabel licenseNumberLabel;
    private JLabel profilePictureLabel;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public ProfilePage(StaffMember staffMember) {
        setLayout(new BorderLayout());
        setBackground(new Color(0xA9BED2));
        initializeProfile(staffMember);
    }

    private void initializeProfile(StaffMember staffMember) {
        if (staffMember instanceof Doctor) {
            initializeDoctorProfile((Doctor) staffMember);
        } else if (staffMember instanceof Nurse) {
            initializeNurseProfile((Nurse) staffMember);
        }
    }

    private void initializeDoctorProfile(Doctor doctor) {
        setProfileInfo("Welcome Back, " + doctor.getFirstName(),
                       doctor.getFirstName(), doctor.getLastName(),
                       doctor.getDepartments(), doctor.getId(), doctor.getGender(),
                       doctor.getAddress(), doctor.getPhoneNumber(), doctor.getEmail(),
                       doctor.getBirthDate(), doctor.getWorkStartDate(),
                       doctor.isFinishInternship() ? "Yes" : "No",
                       doctor.getSalary(), doctor.getSpecialization(),
                       doctor.getLicenseNumber());
        loadExistingProfilePicture(doctor);
    }

    private void initializeNurseProfile(Nurse nurse) {
        setProfileInfo("Welcome Back, " + nurse.getFirstName(),
                       nurse.getFirstName(), nurse.getLastName(),
                       nurse.getDepartments(), nurse.getId(), nurse.getGender(),
                       nurse.getAddress(), nurse.getPhoneNumber(), nurse.getEmail(),
                       nurse.getBirthDate(), nurse.getWorkStartDate(),
                       null, // Internship status not applicable for Nurse
                       nurse.getSalary(), null, // Specialization not applicable for Nurse
                       nurse.getLicenseNumber());
        loadExistingProfilePicture(nurse);
    }

    private void setProfileInfo(String welcomeMessage, String firstName, String lastName, HashSet<Department> departments,
                                int id, String gender, String address, String phoneNumber, String email,
                                Date birthDate, Date workStartDate, String internshipStatus,
                                double salary, Specialization specialization, int licenseNumber) {
        // Create and configure labels
        JLabel welcomeLabel = new JLabel(welcomeMessage);
        welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.WHITE);

        firstNameLabel = new JLabel("First Name: " + firstName);
        lastNameLabel = new JLabel("Last Name: " + lastName);
        departmentLabel = new JLabel("Department: " + departments);
        idLabel = new JLabel("ID: " + id);
        genderLabel = new JLabel("Gender: " + gender);
        addressLabel = new JLabel("Address: " + address);
        phoneNumberLabel = new JLabel("Phone Number: " + phoneNumber);
        emailLabel = new JLabel("Email: " + email);
        dateOfBirthLabel = new JLabel("Date of Birth: " + formatDate(birthDate));
        workStartDateLabel = new JLabel("Work Start Date: " + formatDate(workStartDate));
        internshipStatusLabel = internshipStatus != null ? new JLabel("Finished Internship: " + internshipStatus) : null;
        salaryLabel = new JLabel("Salary: $" + salary);
        specializationLabel = specialization != null ? new JLabel("Specialization: " + specialization) : null;
        licenseNumberLabel = new JLabel("License Number: " + licenseNumber);
        profilePictureLabel = new JLabel();

        // Create a panel for the profile picture and labels
        JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setBackground(new Color(0x8A9BA8));
        infoPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        infoPanel.setPreferredSize(new Dimension(600, 600)); // Set preferred size

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        infoPanel.add(welcomeLabel, gbc);
        gbc.gridy++;
        infoPanel.add(profilePictureLabel, gbc);
        gbc.gridy++;
        infoPanel.add(firstNameLabel, gbc);
        gbc.gridy++;
        infoPanel.add(lastNameLabel, gbc);
        gbc.gridy++;
        infoPanel.add(departmentLabel, gbc);
        gbc.gridy++;
        infoPanel.add(idLabel, gbc);
        gbc.gridy++;
        infoPanel.add(genderLabel, gbc);
        gbc.gridy++;
        infoPanel.add(addressLabel, gbc);
        gbc.gridy++;
        infoPanel.add(phoneNumberLabel, gbc);
        gbc.gridy++;
        infoPanel.add(emailLabel, gbc);
        gbc.gridy++;
        infoPanel.add(dateOfBirthLabel, gbc);
        gbc.gridy++;
        infoPanel.add(workStartDateLabel, gbc);

        if (internshipStatusLabel != null) {
            gbc.gridy++;
            infoPanel.add(internshipStatusLabel, gbc);
        }

        gbc.gridy++;
        infoPanel.add(salaryLabel, gbc);

        if (specializationLabel != null) {
            gbc.gridy++;
            infoPanel.add(specializationLabel, gbc);
        }

        gbc.gridy++;
        infoPanel.add(licenseNumberLabel, gbc);

        // Create an outer panel with reduced space
        JPanel outerPanel = new JPanel(new GridBagLayout());
        outerPanel.setBackground(new Color(0xA9BED2));
        outerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        outerPanel.setPreferredSize(new Dimension(450, 650)); // Set preferred size

        GridBagConstraints outerGbc = new GridBagConstraints();
        outerGbc.gridx = 0;
        outerGbc.gridy = 0;
        outerGbc.anchor = GridBagConstraints.CENTER;
        outerPanel.add(infoPanel, outerGbc);

        // Add the outer panel to the main panel
        add(outerPanel, BorderLayout.CENTER);
    }

    private String formatDate(Date date) {
        return DATE_FORMAT.format(date);
    }

    private void loadExistingProfilePicture(StaffMember staffMember) {
        String profilePicPath = staffMember.getProfilePicture();
        if (profilePicPath != null && !profilePicPath.isEmpty()) {
            ImageIcon profileImage = new ImageIcon(profilePicPath);
            Image scaledImage = profileImage.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            profilePictureLabel.setIcon(new ImageIcon(scaledImage));
        }
    }
}
