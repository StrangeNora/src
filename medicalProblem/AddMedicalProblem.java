package medicalProblem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import control.Hospital;
import exceptions.FutureDateException;
import exceptions.InvalidUserDetails;
import model.Department;

public class AddMedicalProblem extends JPanel {
	
	

    private static final long serialVersionUID = 1L;
    private JTextField nameField;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JComboBox<Department> departmentsComboBox;
    private JComboBox<String> optionsComboBox;
    private JPanel fieldsPanel;
    private JButton saveButton;
    private ButtonGroup group;

    public AddMedicalProblem() {
    	
        this.setBackground(new Color(0xA9BED2));

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel titleLabel = new JLabel("Add A Medical Problem");
        titleLabel.setFont(new Font("Traditional Arabic", Font.PLAIN, 22));
        gbc.gridwidth = 3;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(titleLabel, gbc);

        JLabel selectLabel = new JLabel("Select:");
        selectLabel.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(selectLabel, gbc);

        optionsComboBox = new JComboBox<>(new String[] {"", "Fracture", "Injury", "Disease"});
        gbc.gridx = 1;
        gbc.gridwidth = 10;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(optionsComboBox, gbc);
        optionsComboBox.setBackground(new Color(0x698DB0));

        fieldsPanel = new JPanel();
        fieldsPanel.setBackground(new Color(0xA9BED2));
        fieldsPanel.setLayout(new GridBagLayout());
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        add(fieldsPanel, gbc);

        saveButton = new JButton("Save");
        gbc.gridy = 3;
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(saveButton, gbc);

        optionsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFields();
            }
        });
        
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String selectedItem = (String) optionsComboBox.getSelectedItem();

                    if (selectedItem == null || selectedItem.isEmpty()) {
                        throw new InvalidUserDetails("Please select a medical problem to add.");
                    }

                    switch (selectedItem) {
                        case "Fracture":
                            if (nameField.getText().isEmpty() || textField1.getText().trim().isEmpty() || group.getSelection() == null) {
                                throw new InvalidUserDetails("Field cannot be empty.");
                            }
                            break;

                        case "Disease":
                            if (nameField.getText().isEmpty() || textField2.getText().trim().isEmpty()) {
                                throw new InvalidUserDetails("Field cannot be empty.");
                            }
                            break;

                        case "Injury":
                            if (nameField.getText().isEmpty() || textField1.getText().trim().isEmpty() || textField3.getText().trim().isEmpty()) {
                                throw new InvalidUserDetails("Field cannot be empty.");
                            }
                            if (!textField3.getText().matches("\\d+\\.\\d*") || !textField3.getText().matches("\\d+")) {
                                throw new InvalidUserDetails("Common Recovery Time Can Only Contain Numbers.");
                            }
                            break;
                    }
                    JOptionPane.showMessageDialog(null, "Medical Problem updated successfully.");

                } catch (InvalidUserDetails ex) {
                    showErrorMessage(ex.getMessage());
                } catch (FutureDateException ec) {
                    JOptionPane.showMessageDialog(null, "Invalid Date Input.");
                }
            }
        });

        

        updateFields(); // Initialize fields based on default selection
    }
    
    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }


    private void updateFields() {
        // Clear the fields panel
        fieldsPanel.removeAll();

        String selectedOption = (String) optionsComboBox.getSelectedItem();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;

        if (!selectedOption.isEmpty()) {
            JLabel departmentLabel = new JLabel("Department:");
            departmentLabel.setFont(new Font("Times New Roman", Font.ITALIC, 15));
            gbc.anchor = GridBagConstraints.EAST;  // Align the label to the right
            fieldsPanel.add(departmentLabel, gbc);

            Collection<Department> departments = Hospital.getInstance().getDepartments().values();
            Department[] departmentArray = departments.toArray(new Department[0]);
            departmentsComboBox = new JComboBox<>(departmentArray);
            gbc.gridx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.WEST;  // Align the combo box to the left
            fieldsPanel.add(departmentsComboBox, gbc);
            departmentsComboBox.setBackground(new Color(0x698DB0));

            gbc.gridx = 0;
            gbc.gridy++;
        }

        if ("Fracture".equals(selectedOption)) {
            fieldsPanel.add(createLabel("Name:"), gbc);
            nameField = createTextField();
            gbc.gridx = 1;
            fieldsPanel.add(nameField, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            fieldsPanel.add(createLabel("Location:"), gbc);
            textField1 = createTextField();
            gbc.gridx = 1;
            fieldsPanel.add(textField1, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            fieldsPanel.add(createLabel("Requires Cast:"), gbc);
            JPanel castPanel = new JPanel();
            JRadioButton trueButton = new JRadioButton("True");
            JRadioButton falseButton = new JRadioButton("False");
            group = new ButtonGroup();  // Initialize the class-level ButtonGroup
            group.add(trueButton);
            group.add(falseButton);
            castPanel.add(trueButton);
            castPanel.add(falseButton);
            gbc.gridx = 1;
            fieldsPanel.add(castPanel, gbc);

        } else if ("Disease".equals(selectedOption)) {
            fieldsPanel.add(createLabel("Name:"), gbc);
            nameField = createTextField();
            gbc.gridx = 1;
            fieldsPanel.add(nameField, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            fieldsPanel.add(createLabel("Description:"), gbc);
            textField2 = createTextField();
            gbc.gridx = 1;
            fieldsPanel.add(textField2, gbc);

        } else if ("Injury".equals(selectedOption)) {
            fieldsPanel.add(createLabel("Name:"), gbc);
            nameField = createTextField();
            gbc.gridx = 1;
            fieldsPanel.add(nameField, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            fieldsPanel.add(createLabel("Location:"), gbc);
            textField1 = createTextField();
            gbc.gridx = 1;
            fieldsPanel.add(textField1, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            fieldsPanel.add(createLabel("Common Recovery Time:"), gbc);
            textField3 = createTextField();
            gbc.gridx = 1;
            fieldsPanel.add(textField3, gbc);
        }

        fieldsPanel.revalidate();
        fieldsPanel.repaint();
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        label.setHorizontalAlignment(SwingConstants.RIGHT); // Align label text to the right
        return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField(20);
		textField.setBackground(new Color(0x698DB0)); 

        return textField;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Add Medical Problem");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 360);
            frame.setLocationRelativeTo(null);
            frame.add(new AddMedicalProblem());
            frame.setVisible(true);
        });
    }
}
