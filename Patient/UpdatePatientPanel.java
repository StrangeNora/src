package Patient;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;

import enums.BiologicalSex;
import enums.HealthFund;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatePatientPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPanel inputPanel;
    private JComboBox<String> attributeComboBox;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private ButtonGroup genderGroup;
    private JDateChooser birthDateChooser;
    private JRadioButton otherRadioButton;
    private JComboBox<HealthFund> healthFundComboBox;
    private BiologicalSex[] selectedSex;
    
    
    public UpdatePatientPanel() {
        this.setBackground(new Color(0x698DB0));
        setLayout(new BorderLayout());

        // Initialize the radio buttons first
        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");
        otherRadioButton = new JRadioButton("Other");

        // Group the radio buttons
        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        genderGroup.add(otherRadioButton);

        // Now you can set up the action listeners
        selectedSex = new BiologicalSex[1];
        maleRadioButton.addActionListener(e -> selectedSex[0] = BiologicalSex.M);
        femaleRadioButton.addActionListener(e -> selectedSex[0] = BiologicalSex.F);
        maleRadioButton.setSelected(true);
        selectedSex[0] = BiologicalSex.M;

        JLabel titleLabel = new JLabel("What Do You Want To Update?");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel northPanel = new JPanel();
        northPanel.setBackground(new Color(0xA9BED2));
        northPanel.setLayout(new BorderLayout());
        northPanel.add(titleLabel, BorderLayout.NORTH);

        String[] options = {"ID", "First Name", "Last Name", "Address", "Phone Number", "Gender", "Biological Sex", "Email", "HealthFund", "Birthdate"};
        attributeComboBox = new JComboBox<>(options);

        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setBackground(new Color(0xA9BED2));
        comboBoxPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel selectLabel = new JLabel("Select:");
        comboBoxPanel.add(selectLabel);
        comboBoxPanel.add(attributeComboBox);

        northPanel.add(comboBoxPanel, BorderLayout.CENTER);
        inputPanel = new JPanel();
        inputPanel.setBackground(new Color(0xA9BED2));
        inputPanel.setLayout(new GridBagLayout());

        birthDateChooser = new JDateChooser();
        birthDateChooser.getCalendarButton().setPreferredSize(new Dimension(12, 12));
        birthDateChooser.setPreferredSize(new Dimension(82, birthDateChooser.getPreferredSize().height));

        add(northPanel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);

        attributeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateInputPanel();
            }
        });

        textField1 = new JTextField(10);
        textField2 = new JTextField(10);
        textField3 = new JTextField(10);
        textField4 = new JTextField(10);
        textField5 = new JTextField(10);
        textField6 = new JTextField(10);
        textField7 = new JTextField(10);
        textField8 = new JTextField(10);
        textField9 = new JTextField(10);

        JComponent[] components = {
            birthDateChooser, textField1, textField2, textField3,
            textField4, textField5, textField6, textField7,
            textField8, textField9
        };

        for (JComponent component : components) {
            component.setBackground(new Color(0x698DB0));
            component.setForeground(Color.WHITE);
        }

        healthFundComboBox = new JComboBox<>();
        healthFundComboBox.setModel(new DefaultComboBoxModel<>(HealthFund.values()));

        JButton updateButton = new JButton("Update");

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southPanel.setBackground(new Color(0xA9BED2));
        southPanel.add(updateButton);

        add(southPanel, BorderLayout.SOUTH);
    }

    private void updateInputPanel() {
        inputPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        String selectedOption = (String) attributeComboBox.getSelectedItem();
        switch (selectedOption) {
            case "ID":
                addLabelAndTextField("ID:", textField1, gbc, 0);
                break;
            case "First Name":
                addLabelAndTextField("First Name:", textField2, gbc, 1);
                break;
            case "Last Name":
                addLabelAndTextField("Last Name:", textField3, gbc, 2);
                break;
            case "Address":
                addLabelAndTextField("Address:", textField4, gbc, 3);
                break;
            case "Phone Number":
                addLabelAndTextField("Phone Number:", textField5, gbc, 4);
                break;
            case "Email":
                addLabelAndTextField("Email:", textField6, gbc, 5);
                break;
            case "Gender":
                addGenderOptions(gbc, 6);
                break;
            case "Biological Sex":

                addBiologicalSexOptions(gbc, 7);
                break;
            case "HealthFund":
                addHealthFundOption(gbc, 8);
                break;
            case "Birthdate":
                addLabelAndDateChooser("Birthdate:", birthDateChooser, gbc, 9);
                break;
        }

        inputPanel.revalidate();
        inputPanel.repaint();
    }

    private void addLabelAndTextField(String labelText, JComponent textField, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        JLabel label = new JLabel(labelText);
        inputPanel.add(label, gbc);

        gbc.gridx = 1;
        gbc.gridy = row;
        inputPanel.add(textField, gbc);
    }

    private void addGenderOptions(GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        JLabel label = new JLabel("Gender:");
        inputPanel.add(label, gbc);

        gbc.gridx = 1;
        gbc.gridy = row;
        inputPanel.add(maleRadioButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = row;
        inputPanel.add(femaleRadioButton, gbc);

        gbc.gridx = 3;
        gbc.gridy = row;
        inputPanel.add(otherRadioButton, gbc);
    }

    private void addBiologicalSexOptions(GridBagConstraints gbc, int row) {
        // Initialize the array to hold the selected biological sex
        BiologicalSex[] selectedSex = new BiologicalSex[1];

        // Set up the label for "Biological Sex"
        gbc.gridx = 0;
        gbc.gridy = row;
        JLabel label = new JLabel("Biological Sex:");
        inputPanel.add(label, gbc);

        // Set up the male radio button
        gbc.gridx = 1;
        gbc.gridy = row;
        inputPanel.add(maleRadioButton, gbc);

        // Set up the female radio button
        gbc.gridx = 2;
        gbc.gridy = row;
        inputPanel.add(femaleRadioButton, gbc);

        // Add action listeners to update selectedSex based on the selected radio button
        maleRadioButton.addActionListener(e -> selectedSex[0] = BiologicalSex.M);
        femaleRadioButton.addActionListener(e -> selectedSex[0] = BiologicalSex.F);

        // Default selection
        maleRadioButton.setSelected(true);  
        selectedSex[0] = BiologicalSex.M;
    }

    
    
    private void addHealthFundOption(GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        JLabel label = new JLabel("Health Fund:");
        inputPanel.add(label, gbc);

        gbc.gridx = 1;
        inputPanel.add(healthFundComboBox, gbc);
    }

    private void addLabelAndDateChooser(String labelText, JDateChooser dateChooser, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        JLabel label = new JLabel(labelText);
        inputPanel.add(label, gbc);

        gbc.gridx = 1;
        inputPanel.add(dateChooser, gbc);
    }


   
}


   