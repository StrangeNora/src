package medicalProblem;

import javax.swing.*;
import control.Hospital;
import exceptions.InvalidUserDetails;
import model.Department;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class UpdateMedicalProblem extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField textFieldName;
    private JComboBox<Department> departmentsComboBox;
    private JTextField textFieldDescription;
    private JTextField textFieldLocation;
    private JTextField textFieldCommonRecoveryTime;
    private JRadioButton trueRadioButton;
    private JRadioButton falseRadioButton;
    private ButtonGroup trueOrFalseGroup;
    private CardLayout cardLayout;
    private JPanel cardsPanel;
    private JLabel nameLabel;
    private JLabel departmentLabel;
    private JLabel locationLabel;
    private JLabel requiresCastLabel;
    private JLabel descriptionLabel;
    private JLabel commonRecoveryTimeLabel;

    public UpdateMedicalProblem(MedicalProblems m) {
        setBackground(new Color(0xA9BED2));
        trueOrFalseGroup = new ButtonGroup();

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Title Label
        JLabel lblTitle = new JLabel("Update Medical Problem");
        lblTitle.setFont(new Font("Traditional Arabic", Font.BOLD, 22));
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        add(lblTitle, gbc);

        // ComboBox for problem types
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setBackground(new Color(0x698DB0));
        comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Select Option", "Disease", "Fracture", "Injury"}));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        add(comboBox, gbc);

        // Initialize card layout and panels
        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);

        // Default empty panel
        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(new Color(0xA9BED2));
        cardsPanel.add(emptyPanel, "empty");

        // Fetch departments and add to combo box
        Collection<Department> departments = Hospital.getInstance().getDepartments().values();
        Department[] departmentArray = departments.toArray(new Department[0]);
        departmentsComboBox = new JComboBox<>(departmentArray);
        departmentsComboBox.setBackground(new Color(0x698DB0));
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        add(departmentsComboBox, gbc);

        // Initialize JTextField components
        textFieldName = new JTextField();
        textFieldDescription = new JTextField();
        textFieldLocation = new JTextField();
        textFieldCommonRecoveryTime = new JTextField();

        textFieldDescription.setBackground(new Color(0x698DB0));
        textFieldLocation.setBackground(new Color(0x698DB0));
        textFieldCommonRecoveryTime.setBackground(new Color(0x698DB0));

        // Initialize Radio Buttons
        trueRadioButton = new JRadioButton("Yes");
        falseRadioButton = new JRadioButton("No");
        trueOrFalseGroup.add(trueRadioButton);
        trueOrFalseGroup.add(falseRadioButton);

        // Panels for different problem types
        createPanels();

        // Add the cardsPanel to the main layout
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(cardsPanel, gbc);

        // Update Button
        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String selectedItem = (String) comboBox.getSelectedItem();

                    if ("Select Option".equals(selectedItem)) {
                        throw new InvalidUserDetails("Please choose the type of medical problem you want to update.");
                    }

                    validateFields(selectedItem);

                    // Assuming successful update
                    JOptionPane.showMessageDialog(null, "Update Successful");

                } catch (InvalidUserDetails ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(btnUpdate, gbc);

        // Action listener for combo box to switch panels
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) comboBox.getSelectedItem();
                switch (selectedOption) {
                    case "Disease":
                        cardLayout.show(cardsPanel, "Disease");
                        break;
                    case "Fracture":
                        cardLayout.show(cardsPanel, "Fracture");
                        break;
                    case "Injury":
                        cardLayout.show(cardsPanel, "Injury");
                        break;
                    default:
                        cardLayout.show(cardsPanel, "empty");
                        break;
                }
                showRelevantFields(selectedOption);
            }
        });
    }

    private void createPanels() {
        // Disease Panel
        JPanel diseasePanel = new JPanel(new GridBagLayout());
        diseasePanel.setBackground(new Color(0xA9BED2));
        diseasePanel.add(createLabel("Name:"), createGbc(0, 0));
        diseasePanel.add(textFieldName, createGbc(1, 0));
        diseasePanel.add(createLabel("Description:"), createGbc(0, 1));
        diseasePanel.add(textFieldDescription, createGbc(1, 1));
        diseasePanel.add(createLabel("Common Recovery Time:"), createGbc(0, 2));
        diseasePanel.add(textFieldCommonRecoveryTime, createGbc(1, 2));
        diseasePanel.add(createLabel("Location:"), createGbc(0, 3));
        diseasePanel.add(textFieldLocation, createGbc(1, 3));
        cardsPanel.add(diseasePanel, "Disease");

        // Fracture Panel
        JPanel fracturePanel = new JPanel(new GridBagLayout());
        fracturePanel.setBackground(new Color(0xA9BED2));
        fracturePanel.add(createLabel("Name:"), createGbc(0, 0));
        fracturePanel.add(textFieldName, createGbc(1, 0));
        fracturePanel.add(createLabel("Location:"), createGbc(0, 1));
        fracturePanel.add(textFieldLocation, createGbc(1, 1));
        fracturePanel.add(createLabel("Requires Cast:"), createGbc(0, 2));
        JPanel castPanel = new JPanel();
        castPanel.add(trueRadioButton);
        castPanel.add(falseRadioButton);
        fracturePanel.add(castPanel, createGbc(1, 2));
        cardsPanel.add(fracturePanel, "Fracture");

        // Injury Panel
        JPanel injuryPanel = new JPanel(new GridBagLayout());
        injuryPanel.setBackground(new Color(0xA9BED2));
        injuryPanel.add(createLabel("Name:"), createGbc(0, 0));
        injuryPanel.add(textFieldName, createGbc(1, 0));
        injuryPanel.add(createLabel("Description:"), createGbc(0, 1));
        injuryPanel.add(textFieldDescription, createGbc(1, 1));
        cardsPanel.add(injuryPanel, "Injury");
    }

    private GridBagConstraints createGbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = GridBagConstraints.WEST;
        return gbc;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.BLACK);
        return label;
    }

    private void validateFields(String type) throws InvalidUserDetails {
        if (textFieldName.getText().trim().isEmpty()) {
            throw new InvalidUserDetails("Name field cannot be empty.");
        }
        if ("Injury".equals(type) && textFieldLocation.getText().trim().isEmpty()) {
            throw new InvalidUserDetails("Location field cannot be empty.");
        }
        if ("Injury".equals(type) && textFieldCommonRecoveryTime.getText().trim().isEmpty()) {
            throw new InvalidUserDetails("Common recovery time field cannot be empty.");
        }
        if ("Injury".equals(type) && !textFieldCommonRecoveryTime.getText().matches("\\d+\\.\\d+")) {
            throw new InvalidUserDetails("Common recovery time field must contain a valid number.");
        }
        if ("Fracture".equals(type) && trueOrFalseGroup.getSelection() == null) {
            throw new InvalidUserDetails("Please select an option for 'Requires Cast'.");
        }
    }

    private void showRelevantFields(String option) {
        hideAllFields();
        switch (option) {
            case "Disease":
                textFieldName.setVisible(true);
                textFieldDescription.setVisible(true);
                textFieldCommonRecoveryTime.setVisible(true);
                textFieldLocation.setVisible(true);
                break;
            case "Fracture":
                textFieldName.setVisible(true);
                textFieldLocation.setVisible(true);
                trueRadioButton.setVisible(true);
                falseRadioButton.setVisible(true);
                break;
            case "Injury":
                textFieldName.setVisible(true);
                textFieldDescription.setVisible(true);
                textFieldCommonRecoveryTime.setVisible(true);
                break;
        }
    }

    private void hideAllFields() {
        textFieldName.setVisible(false);
        textFieldDescription.setVisible(false);
        textFieldLocation.setVisible(false);
        textFieldCommonRecoveryTime.setVisible(false);
        trueRadioButton.setVisible(false);
        falseRadioButton.setVisible(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Update Medical Problem");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 300);
            frame.setLocationRelativeTo(null);
            frame.getContentPane().add(new UpdateMedicalProblem(null));
            frame.setVisible(true);
        });
    }
}
