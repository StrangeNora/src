package staffMember;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import enums.Specialization;
import exceptions.FutureDateException;
import exceptions.InvalidUserDetails;

public class UpdateStaffMember extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPanel inputPanel;
    private JComboBox<String> staffTypeComboBox;
    private JComboBox<String> attributeComboBox;
    private JTextField textField;
    private JDateChooser dateChooser;
    private JPasswordField passwordField;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JRadioButton otherRadioButton;
    private ButtonGroup genderGroup;
    private JButton updateButton;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static final Date LIMIT_DATE;
    private ButtonGroup trueOrFalseGroup;
    private JRadioButton trueRadioButton;
    private JRadioButton falseRadioButton;
    private JComboBox<Specialization> specializationComboBox;
    private StaffMembers s;

    static {
        try {
            LIMIT_DATE = sdf.parse("30/04/2024");
        } catch (ParseException e) {
            throw new RuntimeException("Error initializing date limit", e);
        }
    }

    public UpdateStaffMember(StaffMembers s) {
        this.setBackground(new Color(0xA9BED2));
        setLayout(new BorderLayout());

        JLabel chooseLabel = new JLabel("Choose:");
        chooseLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));

        String[] staffOptions = {"", "Update Doctor", "Update Nurse"};
        staffTypeComboBox = new JComboBox<>(staffOptions);

        inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(new Color(0xA9BED2));

        attributeComboBox = new JComboBox<>();

        specializationComboBox = new JComboBox<>(Specialization.values());
        specializationComboBox.setVisible(false);

        GridBagConstraints gbc_specializationComboBox = new GridBagConstraints();
        gbc_specializationComboBox.gridwidth = 3;
        gbc_specializationComboBox.insets = new Insets(0, 0, 5, 5);
        gbc_specializationComboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_specializationComboBox.gridx = 2;
        gbc_specializationComboBox.gridy = 10;
        inputPanel.add(specializationComboBox, gbc_specializationComboBox);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(new Color(0xA9BED2));
        topPanel.add(chooseLabel);
        topPanel.add(staffTypeComboBox);
        topPanel.add(attributeComboBox);

        add(topPanel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);

        textField = new JTextField(10);
        dateChooser = new JDateChooser();
        passwordField = new JPasswordField(10);
        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");
        otherRadioButton = new JRadioButton("Other");
        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        genderGroup.add(otherRadioButton);
        trueOrFalseGroup = new ButtonGroup();
        trueRadioButton = new JRadioButton();
        falseRadioButton = new JRadioButton();
        trueOrFalseGroup.add(trueRadioButton);
        trueOrFalseGroup.add(falseRadioButton);

        textField.setBackground(new Color(0x698DB0));
        textField.setForeground(Color.WHITE);
        dateChooser.setBackground(new Color(0x698DB0));
        JTextField dateTextField = (JTextField) dateChooser.getDateEditor().getUiComponent();
        dateTextField.setBackground((new Color(0x698DB0)));
        passwordField.setBackground(new Color(0x698DB0));
        passwordField.setForeground(Color.WHITE);

        staffTypeComboBox.addActionListener(e -> updateAttributeComboBox());

        attributeComboBox.addActionListener(e -> updateInputPanel());

        updateButton = new JButton("Update");
        updateButton.addActionListener(e -> handleUpdateButtonClick());

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(new Color(0xA9BED2));
        bottomPanel.add(updateButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void updateAttributeComboBox() {
        attributeComboBox.removeAllItems();

        String staffType = (String) staffTypeComboBox.getSelectedItem();
        String[] doctorAttributes = {"", "First Name", "Last Name", "Birth Date", "Address", "Phone Number", "Email", "Gender", "Work Start Date", "Salary", "User Name", "Password", "License Number", "Is Finished Internship", "Specialization"};
        String[] nurseAttributes = {"", "First Name", "Last Name", "Birth Date", "Address", "Phone Number", "Email", "Gender", "Work Start Date", "Salary", "User Name", "Password", "License Number", "Works in Intensive Care"};

        if ("Update Doctor".equals(staffType)) {
            for (String attribute : doctorAttributes) {
                attributeComboBox.addItem(attribute);
            }
        } else if ("Update Nurse".equals(staffType)) {
            for (String attribute : nurseAttributes) {
                attributeComboBox.addItem(attribute);
            }
        }
    }

    private void updateInputPanel() {
        inputPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        String selectedAttribute = (String) attributeComboBox.getSelectedItem();

        if (selectedAttribute == null || selectedAttribute.isEmpty()) {
            return;
        }

        try {
            switch (selectedAttribute) {
                case "First Name":
                case "Last Name":
                case "Address":
                case "Phone Number":
                case "Email":
                case "Salary":
                case "User Name":
                case "License Number":
                    addLabelAndTextField(selectedAttribute + ":", textField, gbc, 0);
                    break;
                case "Birth Date":
                case "Work Start Date":
                    addLabelAndDateChooser(selectedAttribute + ":", dateChooser, gbc, 0);
                    break;
                case "Gender":
                    addGenderOptions(gbc, 0);
                    break;
                case "Password":
                    addLabelAndPasswordField("Password:", passwordField, gbc, 0);
                    break;
                case "Is Finished Internship":
                    addLabelAndRadioButtons("Is Finished Internship:", new String[]{"True", "False"}, trueOrFalseGroup, gbc, 0);
                    break;
                case "Specialization":
                    addSpecializationOption(gbc, 0);
                    specializationComboBox.setVisible(true);
                    break;
                case "Works in Intensive Care":
                    addLabelAndRadioButtons("Works in Intensive Care:", new String[]{"True", "False"}, trueOrFalseGroup, gbc, 0);
                    break;
                default:
                    specializationComboBox.setVisible(false);
                    break;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error updating input panel: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        inputPanel.revalidate();
        inputPanel.repaint();
    }

    private void handleUpdateButtonClick() {
        try {
            if (staffTypeComboBox.getSelectedIndex() == 0) {
                throw new Exception("Please select a staff type.");
            }

            String selectedAttribute = (String) attributeComboBox.getSelectedItem();
            if (selectedAttribute == null || selectedAttribute.isEmpty()) {
                throw new Exception("Please select an attribute to update.");
            }

            validateInput(selectedAttribute);

            JOptionPane.showMessageDialog(this, "Update successful!", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void validateInput(String selectedAttribute) throws Exception {
       
            if (selectedAttribute.equals("Birth Date") || selectedAttribute.equals("Work Start Date")) {
                if (dateChooser.isVisible()) {
                    Date selectedDate = dateChooser.getDate();
                    if (selectedDate == null) {
                        throw new NullPointerException("Date field must be filled.");
                    }
                    if (selectedDate.after(LIMIT_DATE)) {
                        throw new FutureDateException(LIMIT_DATE);
                    }
                }
            }

            if (selectedAttribute.equals("Is Finished Internship") || selectedAttribute.equals("Works in Intensive Care")) {
                if (trueOrFalseGroup.getSelection().equals( null)){
                    throw new NullPointerException("Field must be filled.");
                }
            }

            if (textField.isVisible()) {
                if (selectedAttribute.equals("Salary") && textField.getText().isEmpty()) {
                    throw new NullPointerException("Salary Field must be filled.");
                }
                if (selectedAttribute.equals("First Name") && textField.getText().isEmpty()) {
                    throw new NullPointerException("First Name Field must be filled.");
                }
                if (selectedAttribute.equals("Last Name") && textField.getText().isEmpty()) {
                    throw new NullPointerException("Last Name Field must be filled.");
                }
                if (selectedAttribute.equals("Phone Number") && textField.getText().isEmpty()) {
                    throw new NullPointerException("Phone Number Field must be filled.");
                }
                if (selectedAttribute.equals("Address") && textField.getText().isEmpty()) {
                    throw new NullPointerException("Address Field must be filled.");
                }
                if (selectedAttribute.equals("Email") && textField.getText().isEmpty()) {
                    throw new NullPointerException("Email Field must be filled.");
                }
                if (selectedAttribute.equals("User Name") && textField.getText().isEmpty()) {
                    throw new NullPointerException("User Name Field must be filled.");
                }
                if (selectedAttribute.equals("License Number") && textField.getText().isEmpty()) {
                    throw new NullPointerException("License Number Field must be filled.");
                }
                if (selectedAttribute.equals("Password") && passwordField.isVisible()) {
                    if (passwordField.getPassword().length == 0) {
                        throw new NullPointerException("Password field must be filled.");
                    }
                }
                if (selectedAttribute.equals("License Number") && !textField.getText().matches("\\d+")) {
                    throw new InvalidUserDetails("License Number Field must Only Contain Numbers.");
                }
                if (selectedAttribute.equals("Phone Number") && !textField.getText().matches("\\d+")) {
                    throw new InvalidUserDetails("Phone Number Field must Only Contain Numbers.");
                }
                if (selectedAttribute.equals("Salary") && !textField.getText().matches("\\d+")) {
                    throw new InvalidUserDetails("Salary Field must Only Contain Numbers.");
                }
            }

            if (selectedAttribute.equals("Gender") && genderGroup.getSelection() == null) {
                throw new NullPointerException("Gender must be selected.");
            }

        }
    
    

    private void addLabelAndTextField(String labelText, JTextField textField, GridBagConstraints gbc, int gridY) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = gridY;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(label, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(textField, gbc);
    }

    private void addLabelAndDateChooser(String labelText, JDateChooser dateChooser, GridBagConstraints gbc, int gridY) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = gridY;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(label, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(dateChooser, gbc);
    }

    private void addLabelAndPasswordField(String labelText, JPasswordField passwordField, GridBagConstraints gbc, int gridY) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = gridY;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(label, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(passwordField, gbc);
    }

    private void addGenderOptions(GridBagConstraints gbc, int gridY) {
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = gridY;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(genderLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 1;
        inputPanel.add(maleRadioButton, gbc);

        gbc.gridx = 2;
        inputPanel.add(femaleRadioButton, gbc);

        gbc.gridx = 3;
        inputPanel.add(otherRadioButton, gbc);
    }

    private void addLabelAndRadioButtons(String labelText, String[] options, ButtonGroup group, GridBagConstraints gbc, int gridY) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = gridY;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(label, gbc);

        JRadioButton firstOption = new JRadioButton(options[0]);
        JRadioButton secondOption = new JRadioButton(options[1]);
        group.add(firstOption);
        group.add(secondOption);

        gbc.gridx = 1;
        inputPanel.add(firstOption, gbc);

        gbc.gridx = 2;
        inputPanel.add(secondOption, gbc);
    }

    private void addSpecializationOption(GridBagConstraints gbc, int gridY) {
        JLabel specializationLabel = new JLabel("Specialization:");
        specializationLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = gridY;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(specializationLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        inputPanel.add(specializationComboBox, gbc);
    }
   
}
