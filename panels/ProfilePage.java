package panels;

import javax.swing.*;

import enums.Specialization;
import model.Department;
import model.Doctor;
import model.Nurse;
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

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public ProfilePage(Doctor doctor) {
        this();
        if (doctor != null) {
            initializeDoctorProfile(doctor);
        }
    }

    public ProfilePage(Nurse nurse) {
        this();
        if (nurse != null) {
            initializeNurseProfile(nurse);
        }
    }

    private ProfilePage() {
        setLayout(new GridBagLayout());
        setBackground(new Color(0xA9BED2));
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
    }

    private void setProfileInfo(String welcomeMessage, String firstName, String lastName, HashSet<Department> hashSet,
                                int i, String gender, String address, String phoneNumber, String email,
                                Date birthDate, Date workStartDate, String internshipStatus,
                                double salary, Specialization specialization, int j) {
        // Create and configure labels
        JLabel welcomeLabel = new JLabel(welcomeMessage);
        welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        welcomeLabel.setForeground(Color.WHITE);

        firstNameLabel = new JLabel("First Name: " + firstName);
        lastNameLabel = new JLabel("Last Name: " + lastName);
        departmentLabel = new JLabel("Department: " + hashSet);
        idLabel = new JLabel("ID: " + i);
        genderLabel = new JLabel("Gender: " + gender);
        addressLabel = new JLabel("Address: " + address);
        phoneNumberLabel = new JLabel("Phone Number: " + phoneNumber);
        emailLabel = new JLabel("Email: " + email);
        dateOfBirthLabel = new JLabel("Date of Birth: " + formatDate(birthDate));
        workStartDateLabel = new JLabel("Work Start Date: " + formatDate(workStartDate));
        internshipStatusLabel = internshipStatus != null ? new JLabel("Finished Internship: " + internshipStatus) : null;
        salaryLabel = new JLabel("Salary: $" + salary);
        specializationLabel = specialization != null ? new JLabel("Specialization: " + specialization) : null;
        licenseNumberLabel = new JLabel("License Number: " + j);

        // Add components to the panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(welcomeLabel, gbc);

        gbc.gridy++;
        add(firstNameLabel, gbc);

        gbc.gridy++;
        add(lastNameLabel, gbc);

        gbc.gridy++;
        add(departmentLabel, gbc);

        gbc.gridy++;
        add(idLabel, gbc);

        gbc.gridy++;
        add(genderLabel, gbc);

        gbc.gridy++;
        add(addressLabel, gbc);

        gbc.gridy++;
        add(phoneNumberLabel, gbc);

        gbc.gridy++;
        add(emailLabel, gbc);

        gbc.gridy++;
        add(dateOfBirthLabel, gbc);

        gbc.gridy++;
        add(workStartDateLabel, gbc);

        if (internshipStatusLabel != null) {
            gbc.gridy++;
            add(internshipStatusLabel, gbc);
        }

        gbc.gridy++;
        add(salaryLabel, gbc);

        if (specializationLabel != null) {
            gbc.gridy++;
            add(specializationLabel, gbc);
        }

        gbc.gridy++;
        add(licenseNumberLabel, gbc);
    }

    private String formatDate(Date date) {
        return DATE_FORMAT.format(date);
    }

   
}

   