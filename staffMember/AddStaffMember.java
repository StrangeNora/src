package staffMember;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;

import Patient.AddPatient;
import control.Hospital;
import exceptions.ObjectAlreadyExistsException;
import model.Department;
import model.Patient;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

public class AddStaffMember extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPanel inputPanel;
    private JComboBox<String> attributeComboBox;
    private JTextField idField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField addressField;
    private JTextField phoneField;
    private JTextField userNameField;
    private JTextField salaryField;
    private JTextField licenseNumberField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JDateChooser birthDateChooser;
    private JDateChooser workStartDateChooser;
    private JRadioButton trueRadioButton;
    private JRadioButton falseRadioButton;
    private JRadioButton genderMaleRadioButton;
    private JRadioButton genderFemaleRadioButton;
    private JRadioButton otherRadioButton;
    private ButtonGroup  trueOrFalseGroup;
    private ButtonGroup genderGroup;
    private JComboBox<String> specializationComboBox;
    private JComboBox<Department> departmentsComboBox;
    
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private static final Date MAX_DATE;

    static {
        try {
            MAX_DATE = DATE_FORMAT.parse("30/04/2024");
        } catch (ParseException e) {
            throw new RuntimeException("Date parsing error", e);
        }
    }

    /**
     * Create the panel.
     */
    public AddStaffMember(StaffMembers staffMembers) {
        setLayout(new BorderLayout());
        
        this.setBackground(new Color(0xA9BED2));


        // Create the title label with large font and add it to the top (north) of the panel
        JLabel titleLabel = new JLabel("Add A Staff Member", JLabel.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        // Create the JComboBox with options
        String[] options = {"Add Doctor", "Add Nurse"};
        attributeComboBox = new JComboBox<>(options);

        // Create a panel to hold the label and combo box
        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel selectLabel = new JLabel("Select:");
        comboBoxPanel.add(selectLabel);
        comboBoxPanel.add(attributeComboBox);

        // Initialize the input panel
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());

        // Initialize components
        birthDateChooser = new JDateChooser();
        workStartDateChooser = new JDateChooser();
        passwordField = new JPasswordField();
        idField = new JTextField(10);
        firstNameField = new JTextField(10);
        lastNameField = new JTextField(10);
        addressField = new JTextField(10);
        phoneField = new JTextField(10);
        emailField = new JTextField(10);
        salaryField = new JTextField(10);
        userNameField = new JTextField(10);
        licenseNumberField = new JTextField(10);
        trueRadioButton = new JRadioButton("True");
        falseRadioButton = new JRadioButton("False");
        
        
        trueOrFalseGroup = new ButtonGroup();
        trueOrFalseGroup.add(trueRadioButton);
        trueOrFalseGroup.add(falseRadioButton);
        
        
        
        String[] SpecializationFundOptions = {"Neurology", "Cardiology", "Otolaryngology", "Orthopedics", "Surgery", "Ophthalmology", "Pulmonology", "IntensiveCare", "Other"};
        specializationComboBox = new JComboBox<>(SpecializationFundOptions);
        
        
        departmentsComboBox = new JComboBox<>();
        Collection<Department> departments = Hospital.getInstance().getDepartments().values();
        Department[] departmentArray = departments.toArray(new Department[0]);
        departmentsComboBox.setModel(new DefaultComboBoxModel<>(departmentArray));
        
     // List of components to be styled
        JComponent[] components = {
            birthDateChooser, workStartDateChooser, passwordField, idField, 
            firstNameField, lastNameField, addressField, phoneField, 
            emailField, salaryField, userNameField, licenseNumberField, 
            departmentsComboBox, specializationComboBox
        };

        // Apply background and foreground colors in a loop
        for (JComponent component : components) {
            component.setBackground(new Color(0x698DB0));
            component.setForeground(Color.WHITE);
        }
        

        // Create GridBagConstraints for departmentsComboBox
        GridBagConstraints gbc_departmentsComboBox = new GridBagConstraints();
        gbc_departmentsComboBox.gridwidth = 3;
        gbc_departmentsComboBox.insets = new Insets(5, 5, 5, 5);
        gbc_departmentsComboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_departmentsComboBox.gridx = 1;
        gbc_departmentsComboBox.gridy = 10;

        // Add departmentsComboBox to inputPanel
        inputPanel.add(departmentsComboBox, gbc_departmentsComboBox);

        genderMaleRadioButton = new JRadioButton("Male");
        genderFemaleRadioButton = new JRadioButton("Female");
        otherRadioButton = new JRadioButton("Other");
        genderGroup = new ButtonGroup();
        genderGroup.add(genderMaleRadioButton);
        genderGroup.add(genderFemaleRadioButton);
        genderGroup.add(otherRadioButton);

        

        // Add the combo box panel above the input panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(comboBoxPanel, BorderLayout.NORTH);
        bottomPanel.add(inputPanel, BorderLayout.CENTER);

        // Remove spacer panel or adjust height as needed
        // JPanel spacerPanel = new JPanel();
        // spacerPanel.setPreferredSize(new Dimension(0, 20)); // Adjust or remove
        // bottomPanel.add(spacerPanel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.CENTER);

        // Add action listener to the JComboBox
        attributeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateInputPanel();
            }
        });
        
        JButton saveButton = new JButton("Save");
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southPanel.add(saveButton);
        add(southPanel, BorderLayout.SOUTH);
//this is a note
        
        // Add action listener to the Save button
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    validateInput();
                  //  StaffMember patient = new Patient(id, firstName, lastName, selectedDate, address, phoneNumber, email, selectedGender[0], healthFund, selectedSex[0]);

                   // Hospital.getInstance().addStaffMember(staffMember);
                    staffMembers.refreshList();
                } catch (ObjectAlreadyExistsException o) {
                    JOptionPane.showMessageDialog(null, "Object Already Exists!");
                } catch (Exception ex) {
                    showErrorDialog(ex.getMessage());
                }
            }
        });


        // Initialize panel with no components
        updateInputPanel();
    }

    /**
     * Updates the input panel based on the selected option in the JComboBox.
     */
    private void updateInputPanel() {
        // Clear the existing components
        inputPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;  // Make components resize horizontally
        gbc.weighty = 1.0;  // Make components resize vertically

        // Add input fields based on the selected option
        String selectedOption = (String) attributeComboBox.getSelectedItem();
        if ("Add Doctor".equals(selectedOption)) {
            addComponent(new JLabel("ID:"), idField, gbc, 0);
            addComponent(new JLabel("First Name:"), firstNameField, gbc, 1);
            addComponent(new JLabel("Last Name:"), lastNameField, gbc, 2);
            addComponent(new JLabel("Address:"), addressField, gbc, 3);
            addComponent(new JLabel("Phone:"), phoneField, gbc, 4);
            addComponent(new JLabel("Email:"), emailField, gbc, 5);
            addComponent(new JLabel("Gender:"), createGenderPanel(), gbc, 6);
            addComponent(new JLabel("Date of Birth:"), birthDateChooser, gbc, 7);
            addComponent(new JLabel("Work Start Date:"), workStartDateChooser, gbc, 8);
            addComponent(new JLabel("Is Finished Internship:"), createTrueOrFalsePanel(), gbc, 9);
            addComponent(new JLabel("Salary:"), salaryField, gbc, 10);
            addComponent(new JLabel("Specialization:"), specializationComboBox, gbc, 11);
            addComponent(new JLabel("License Number:"), licenseNumberField, gbc, 12);
            addComponent(new JLabel("Username:"), userNameField, gbc, 13);
            addComponent(new JLabel("Password:"), passwordField, gbc, 14);
            addComponent(new JLabel("Department:"), departmentsComboBox, gbc, 15);
        } else if ("Add Nurse".equals(selectedOption)) {
            addComponent(new JLabel("ID:"), idField, gbc, 0);
            addComponent(new JLabel("First Name:"), firstNameField, gbc, 1);
            addComponent(new JLabel("Last Name:"), lastNameField, gbc, 2);
            addComponent(new JLabel("Address:"), addressField, gbc, 3);
            addComponent(new JLabel("Phone:"), phoneField, gbc, 4);
            addComponent(new JLabel("Email:"), emailField, gbc, 5);
            addComponent(new JLabel("Gender:"), createGenderPanel(), gbc, 6);
            addComponent(new JLabel("Date of Birth:"), birthDateChooser, gbc, 7);
            addComponent(new JLabel("Work Start Date:"), workStartDateChooser, gbc, 8);
            addComponent(new JLabel("Works In Intensive Care"), createTrueOrFalsePanel(), gbc, 9);
            addComponent(new JLabel("Salary:"), salaryField, gbc, 10);
            addComponent(new JLabel("License Number:"), licenseNumberField, gbc, 11);
            addComponent(new JLabel("Department:"), departmentsComboBox, gbc, 12);
            addComponent(new JLabel("Username:"), userNameField, gbc, 13);
            addComponent(new JLabel("Password:"), passwordField, gbc, 14);
            
        }

        // Revalidate and repaint the input panel to apply changes
        inputPanel.revalidate();
        inputPanel.repaint();
    }
    
    private JPanel createGenderPanel() {
        JPanel genderPanel = new JPanel();
        genderPanel.add(genderMaleRadioButton);
        genderPanel.add(genderFemaleRadioButton);
        return genderPanel;
    }
    
    private JPanel createTrueOrFalsePanel() {
        JPanel RPanel = new JPanel();
        RPanel.add(trueRadioButton);
        RPanel.add(falseRadioButton);
        return RPanel;
    }

    private void addComponent(JComponent label, JComponent field, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        inputPanel.add(label, gbc);

        gbc.gridx = 1;
        inputPanel.add(field, gbc);
    }

    private void validateInput() throws Exception {
        String selectedOption = (String) attributeComboBox.getSelectedItem();
        if (selectedOption == "Add Nurse") {
        

        if (idField.getText().trim().isEmpty() ||
            firstNameField.getText().trim().isEmpty() ||
            lastNameField.getText().trim().isEmpty() ||
            addressField.getText().trim().isEmpty() ||
            phoneField.getText().trim().isEmpty() ||
            emailField.getText().trim().isEmpty() ||
            salaryField.getText().trim().isEmpty() ||
            licenseNumberField.getText().trim().isEmpty()||
            birthDateChooser.getDate()==null||
            genderGroup.getSelection() == null||
            workStartDateChooser.getDate()==null||
            userNameField.getText().trim().isEmpty()||
            passwordField.getText().trim().isEmpty()||
            trueOrFalseGroup.getSelection()==null||
            departmentsComboBox.getSelectedItem()==null
            
 ) {
            throw new Exception("All fields must be filled.");
        }

        if (birthDateChooser.getDate() == null || workStartDateChooser.getDate() == null) {
            throw new Exception("Dates must be selected.");
        }

        if (birthDateChooser.getDate().after(MAX_DATE)) {
            throw new Exception("Invalid Birth Date Input");
        }

        if (workStartDateChooser.getDate().after(MAX_DATE)) {
            throw new Exception("Invalid Work Start Date Input");
        }

        if (!isNumeric(idField.getText())) {
            throw new Exception("ID Must Only Have Numbers");
        }

        if (!isNumeric(phoneField.getText())) {
            throw new Exception("Phone Number Must Only Have Numbers");
        }

        if (!isNumeric(salaryField.getText())) {
            throw new Exception("Salary Must Only Have Numbers");
        }

        if (!isNumeric(licenseNumberField.getText())) {
            throw new Exception("License Number Must Only Have Numbers");
        }
        }if (selectedOption == "Add Doctor") {
            

            if (idField.getText().trim().isEmpty() ||
                firstNameField.getText().trim().isEmpty() ||
                lastNameField.getText().trim().isEmpty() ||
                addressField.getText().trim().isEmpty() ||
                phoneField.getText().trim().isEmpty() ||
                emailField.getText().trim().isEmpty() ||
                salaryField.getText().trim().isEmpty() ||
                licenseNumberField.getText().trim().isEmpty()||
                birthDateChooser.getDate()==null||
                genderGroup.getSelection() == null||
                workStartDateChooser.getDate()==null||
                userNameField.getText().trim().isEmpty()||
                passwordField.getText().trim().isEmpty()||
                trueOrFalseGroup.getSelection()==null||
                departmentsComboBox.getSelectedItem()==null||
                specializationComboBox.getSelectedItem()==null
                
     ) {
                throw new Exception("All fields must be filled.");
            }

            if (birthDateChooser.getDate() == null || workStartDateChooser.getDate() == null) {
                throw new Exception("Dates must be selected.");
            }

            if (birthDateChooser.getDate().after(MAX_DATE)) {
                throw new Exception("Invalid Birth Date Input");
            }

            if (workStartDateChooser.getDate().after(MAX_DATE)) {
                throw new Exception("Invalid Work Start Date Input");
            }

            if (!isNumeric(idField.getText())) {
                throw new Exception("ID Must Only Have Numbers");
            }

            if (!isNumeric(phoneField.getText())) {
                throw new Exception("Phone Number Must Only Have Numbers");
            }

            if (!isNumeric(salaryField.getText())) {
                throw new Exception("Salary Must Only Have Numbers");
            }

            if (!isNumeric(licenseNumberField.getText())) {
                throw new Exception("License Number Must Only Have Numbers");
            }
            }
    }

    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

  
}
