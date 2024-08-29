package staffMember;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.toedter.calendar.JDateChooser;

import enums.Specialization;
import exceptions.FutureDateException;

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

    /**
     * Create the panel.
     */
    public UpdateStaffMember(StaffMembers s) {
        this.setBackground(new Color(0xA9BED2));
        setLayout(new BorderLayout());

        // Create the "Choose" label
        JLabel chooseLabel = new JLabel("Choose:");
        chooseLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));

        // Create the JComboBox with options for staff type
        String[] staffOptions = {"", "Update Doctor", "Update Nurse"};
        staffTypeComboBox = new JComboBox<>(staffOptions);

        // Initialize the input panel
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        inputPanel.setBackground(new Color(0xA9BED2));

        // Create the JComboBox for attributes, initially empty
        attributeComboBox = new JComboBox<>();

        // Create and configure specializationComboBox
        specializationComboBox = new JComboBox<>(Specialization.values());
        specializationComboBox.setVisible(false); // Hide initially

        GridBagConstraints gbc_specializationComboBox = new GridBagConstraints();
        gbc_specializationComboBox.gridwidth = 3;
        gbc_specializationComboBox.insets = new Insets(0, 0, 5, 5);
        gbc_specializationComboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_specializationComboBox.gridx = 2;
        gbc_specializationComboBox.gridy = 10;
        inputPanel.add(specializationComboBox, gbc_specializationComboBox);

        // Create a panel to hold the "Choose" label and combo boxes
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(new Color(0xA9BED2));
        topPanel.add(chooseLabel);
        topPanel.add(staffTypeComboBox);
        topPanel.add(attributeComboBox);

        // Add the topPanel to the NORTH of the BorderLayout
        add(topPanel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);

        // Initialize input fields
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

        // Add action listener to the staff type JComboBox
        staffTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAttributeComboBox();
            }
        });

        // Add action listener to the attribute JComboBox
        attributeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateInputPanel();
            }
        });

        // Create and add the Update button
        updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleUpdateButtonClick();
            }
        });
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(new Color(0xA9BED2));
        bottomPanel.add(updateButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Updates the attribute combo box based on the selected staff type.
     */
    private void updateAttributeComboBox() {
        attributeComboBox.removeAllItems();

        String staffType = (String) staffTypeComboBox.getSelectedItem();
        String[] doctorAttributes = {"", "ID", "First Name", "Last Name", "Birth Date", "Address", "Phone Number", "Email", "Gender", "Work Start Date", "Salary", "User Name", "Password", "License Number", "Is Finished Internship", "Specialization"};
        String[] nurseAttributes = {"", "ID", "First Name", "Last Name", "Birth Date", "Address", "Phone Number", "Email", "Gender", "Work Start Date", "Salary", "User Name", "Password", "License Number", "Works in Intensive Care"};

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

    /**
     * Updates the input panel based on the selected attribute in the JComboBox.
     */
    private void updateInputPanel() {
        inputPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        String selectedAttribute = (String) attributeComboBox.getSelectedItem();

        if (selectedAttribute == null || selectedAttribute.isEmpty()) {
            return; // Early return if no attribute is selected
        }

        switch (selectedAttribute) {
            case "ID":
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
                specializationComboBox.setVisible(true); // Show specialization combo box
                break;
            case "Works in Intensive Care":
                addLabelAndRadioButtons("Works in Intensive Care:", new String[]{"True", "False"}, trueOrFalseGroup, gbc, 0);
                break;
            default:
                specializationComboBox.setVisible(false); // Hide specialization combo box for other attributes
                break;
        }

        inputPanel.revalidate();
        inputPanel.repaint();
    }
    
    private void handleUpdateButtonClick() {
        try {
            // Validate staff type selection
            if (staffTypeComboBox.getSelectedIndex() == 0) {
                throw new Exception("Please select a staff type.");
            }

            // Validate attribute selection
            String selectedAttribute = (String) attributeComboBox.getSelectedItem();
            if (selectedAttribute == null || selectedAttribute.isEmpty()) {
                throw new Exception("Please select an attribute to update.");
            }
        

            // Validate input based on selected attribute
            validateInput(selectedAttribute);

            // If all validations pass, proceed with the update (not implemented)
            JOptionPane.showMessageDialog(this, "Update successful!", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void validateInput(String selectedAttribute) throws Exception {
        // Validate Date fields
        if (selectedAttribute.equals("Birth Date") || selectedAttribute.equals("Work Start Date")) {
            if (dateChooser.isVisible()) {
                Date selectedDate = dateChooser.getDate();
                if (selectedDate == null) {
                    throw new Exception("Date field must be filled.");
                }
                if (selectedDate.after(LIMIT_DATE)) {
                    throw new FutureDateException(LIMIT_DATE);
                }
            }
        }
        
        // Validate Radio Buttons for other cases
        if (selectedAttribute.equals("Is Finished Internship") || selectedAttribute.equals("Works in Intensive Care")) {
            if (trueOrFalseGroup.getSelection() == null){ 
                throw new Exception("Field must be filled.");
            }
            
        }

        // Validate text fields
        if (textField.isVisible()) {
        	
        	
        	if( selectedAttribute.equals("Salary") ) {
        		if (textField.getText().isEmpty()) {
                    throw new Exception("Salary Field must be filled.");

        		}
        	}
        	if( selectedAttribute.equals("User Name") ) {
        		if (textField.getText().isEmpty()) {
                    throw new Exception("User Name Field must be filled.");

        		}
        	}
        	if( selectedAttribute.equals("License Number") ) {
        		if (textField.getText().isEmpty()) {
                    throw new Exception("License Number Field must be filled.");

        		}
        	}
        	
        	
        	
        	
        	 // Validate Password field
            if (selectedAttribute.equals("Password") && passwordField.isVisible()) {
                if (passwordField.getPassword().length == 0) {
                    throw new Exception("Password field must be filled.");
                }
            } 
            
            if (textField.getText().trim().isEmpty() && (!(validateDate()) && genderGroup.getSelection()==null)) {
                throw new Exception("Field must be filled.");
            }

            // ID, Phone Number, Salary, License Number must contain only numbers
            if (selectedAttribute.equals("ID") || selectedAttribute.equals("Phone Number") ||
                selectedAttribute.equals("Salary") || selectedAttribute.equals("License Number")) {
                if (!textField.getText().matches("\\d+")) {
                    throw new Exception("Field must only contain numbers.");
                }
            }
        }

      

       
    }

    private boolean validateDate() {
        String selectedAttribute = (String) attributeComboBox.getSelectedItem();
        if (selectedAttribute.equals("Birth Date") || selectedAttribute.equals("Work Start Date")) {
            Date selectedDate = dateChooser.getDate();
            if (selectedDate.before(LIMIT_DATE)) {
                return true;
            }
        }
        return false;
    }

    private void addLabelAndTextField(String labelText, JTextField textField, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        JLabel label = new JLabel(labelText);
        inputPanel.add(label, gbc);
        gbc.gridx = 1;
        inputPanel.add(textField, gbc);
    }

    private void addLabelAndDateChooser(String labelText, JDateChooser dateChooser, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        JLabel label = new JLabel(labelText);
        inputPanel.add(label, gbc);
        gbc.gridx = 1;
        inputPanel.add(dateChooser, gbc);
    }

    private void addLabelAndPasswordField(String labelText, JPasswordField passwordField, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        JLabel label = new JLabel(labelText);
        inputPanel.add(label, gbc);
        gbc.gridx = 1;
        inputPanel.add(passwordField, gbc);
    }

    private void addGenderOptions(GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        JLabel label = new JLabel("Gender:");
        inputPanel.add(label, gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(maleRadioButton, gbc);
        gbc.gridx = 2;
        inputPanel.add(femaleRadioButton, gbc);
        gbc.gridx = 3;
        inputPanel.add(otherRadioButton, gbc);
    }

    private void addLabelAndRadioButtons(String labelText, String[] options, ButtonGroup group, GridBagConstraints gbc, int row) {
        // Create a new JLabel with the provided text
        JLabel label = new JLabel(labelText);
        
        // Set the constraints for the label
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = GridBagConstraints.RELATIVE; // Span to next column
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding
        
        // Add the label to the inputPanel
        inputPanel.add(label, gbc);
        
        // Prepare the constraints for the radio buttons
        gbc.gridx = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Make radio buttons span to the end
        gbc.anchor = GridBagConstraints.WEST;
        
        // Clear any previous radio buttons
        inputPanel.removeAll();
        inputPanel.add(label, gbc); // Add the label again in the panel

        // Add radio buttons for each option
        gbc.gridwidth = 1; // Reset grid width for radio buttons
        for (String option : options) {
            JRadioButton radioButton = new JRadioButton(option);
            group.add(radioButton);
            inputPanel.add(radioButton, gbc);
            gbc.gridx++; // Move to the next column for each radio button
        }
        
        // Refresh the panel to reflect changes
        inputPanel.revalidate();
        inputPanel.repaint();
    }


    private void addSpecializationOption(GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        JLabel label = new JLabel("Specialization:");
        inputPanel.add(label, gbc);
        gbc.gridx = 1;
        inputPanel.add(specializationComboBox, gbc);
    }

   
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Update Staff Member Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(200, 200);  // Set the desired size (width x height)

            // Get screen size and calculate window location
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension screenSize = toolkit.getScreenSize();
            int screenWidth = screenSize.width;
            int screenHeight = screenSize.height;
            int windowWidth = 300;
            int windowHeight = 200;

            // Center the window on the screen
            int x = (screenWidth - windowWidth) / 2;
            int y = (screenHeight - windowHeight) / 2;
            frame.setLocation(x, y);

            frame.setSize(new Dimension(400, 200));
            frame.add(new UpdateStaffMemberPanel());
            frame.setVisible(true);
        });
    }
}