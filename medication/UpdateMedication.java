package medication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import exceptions.InvalidUserDetails;

public class UpdateMedication extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final Color BACKGROUND_COLOR = new Color(0xA9BED2);
    private static final Color TEXTFIELD_BACKGROUND_COLOR = new Color(0x698DB0);
    private static final Color BUTTON_COLOR = new Color(0x698DB0);

    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JComboBox<String> comboBox;
    private JPanel cardPanel;

    /**
     * Create the panel.
     */
    public UpdateMedication() {
        setBackground(BACKGROUND_COLOR);

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 1.0, 0.0, 1.0};
        setLayout(gridBagLayout);

        JLabel lblNewLabel = new JLabel("What Do You Want To Update?");
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.gridheight = 5;
        gbc_lblNewLabel.gridwidth = 16;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 3;
        gbc_lblNewLabel.gridy = 1;
        add(lblNewLabel, gbc_lblNewLabel);

        JPanel panel_3 = new JPanel();
        panel_3.setBackground(BACKGROUND_COLOR);
        GridBagConstraints gbc_panel_3 = new GridBagConstraints();
        gbc_panel_3.insets = new Insets(0, 0, 5, 5);
        gbc_panel_3.fill = GridBagConstraints.BOTH;
        gbc_panel_3.gridx = 2;
        gbc_panel_3.gridy = 6;
        add(panel_3, gbc_panel_3);

        comboBox = new JComboBox<>();
        comboBox.setBackground(TEXTFIELD_BACKGROUND_COLOR);
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Code", "Name", "Dosage", "Number Of Doses"}));
        GridBagConstraints gbc_comboBox = new GridBagConstraints();
        gbc_comboBox.gridwidth = 7;
        gbc_comboBox.insets = new Insets(0, 10, 5, 5);
        gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox.gridx = 3;
        gbc_comboBox.gridy = 6;
        add(comboBox, gbc_comboBox);

        cardPanel = new JPanel();
        cardPanel.setLayout(new CardLayout());
        cardPanel.setVisible(false);  // Initially hide the cardPanel
        GridBagConstraints gbc_cardPanel = new GridBagConstraints();
        gbc_cardPanel.gridwidth = 9;
        gbc_cardPanel.insets = new Insets(0, 0, 5, 5);
        gbc_cardPanel.fill = GridBagConstraints.BOTH;
        gbc_cardPanel.gridx = 8;
        gbc_cardPanel.gridy = 9;
        add(cardPanel, gbc_cardPanel);

        createCardPanel(cardPanel);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBackground(BUTTON_COLOR);
        btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
        gbc_btnUpdate.insets = new Insets(0, 0, 5, 5);
        gbc_btnUpdate.gridx = 18;
        gbc_btnUpdate.gridy = 11;
        add(btnUpdate, gbc_btnUpdate);

        JPanel panel = new JPanel();
        panel.setBackground(BACKGROUND_COLOR);
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 20;
        gbc_panel.gridy = 12;
        add(panel, gbc_panel);

        btnUpdate.addActionListener(e -> {
            try {
                String selectedItem = (String) comboBox.getSelectedItem();

                if (selectedItem == null || selectedItem.isEmpty()) {
                    throw new InvalidUserDetails("Please select an option to update.");
                }

                JTextField currentTextField;

                switch (selectedItem) {
                    case "Code":
                        currentTextField = textField;
                        if (currentTextField.getText().isEmpty()) {
                            throw new InvalidUserDetails("Field cannot be empty.");
                        }
                        if (!isInteger(currentTextField.getText())) {
                            throw new InvalidUserDetails("Code must contain only numbers.");
                        }
                        break;
                    case "Dosage":
                        currentTextField = textField_2;
                        if (currentTextField.getText().isEmpty()) {
                            throw new InvalidUserDetails("Field cannot be empty.");
                        }
                        if (!isDouble(currentTextField.getText())) {
                            throw new InvalidUserDetails("Dosage must contain only numbers.");
                        }
                        break;
                    case "Number Of Doses":
                        currentTextField = textField_3;
                        if (currentTextField.getText().isEmpty()) {
                            throw new InvalidUserDetails("Field cannot be empty.");
                        }
                        if (!isInteger(currentTextField.getText())) {
                            throw new InvalidUserDetails("Number Of Doses must contain only numbers.");
                        }
                        break;
                    case "Name":
                        currentTextField = textField_1;
                        if (currentTextField.getText().isEmpty()) {
                            throw new InvalidUserDetails("Field cannot be empty.");
                        }
                        break;
                }
                JOptionPane.showMessageDialog(null, "Medication updated successfully.");

            } catch (InvalidUserDetails ex) {
                showErrorMessage(ex.getMessage());
            }
        });

        comboBox.addActionListener(e -> {
            CardLayout cl = (CardLayout) cardPanel.getLayout();
            String selectedItem = (String) comboBox.getSelectedItem();
            String panelName = switch (selectedItem) {
                case "Code" -> "Code";
                case "Name" -> "Name";
                case "Dosage" -> "Dosage";
                case "Number Of Doses" -> "Number Of Doses";
                default -> "Code";
            };
            if (selectedItem == null || selectedItem.isEmpty()) {
                cardPanel.setVisible(false);  // Hide cardPanel if no valid selection
            } else {
                cardPanel.setVisible(true);  // Show cardPanel when a valid option is selected
                cl.show(cardPanel, panelName);
            }
        });
    }

    private void createCardPanel(JPanel cardPanel) {
        cardPanel.add(createTextFieldPanel("Code:", textField = new JTextField(10)), "Code");
        cardPanel.add(createTextFieldPanel("Name:", textField_1 = new JTextField(10)), "Name");
        cardPanel.add(createTextFieldPanel("Dosage:", textField_2 = new JTextField(10)), "Dosage");
        cardPanel.add(createTextFieldPanel("Number Of Doses:", textField_3 = new JTextField(10)), "Number Of Doses");
    }

    private JPanel createTextFieldPanel(String labelText, JTextField textField) {
        JPanel panel = new JPanel();
        panel.setBackground(BACKGROUND_COLOR);
        panel.add(new JLabel(labelText));
        textField.setBackground(TEXTFIELD_BACKGROUND_COLOR);
        textField.setForeground(Color.WHITE);
        panel.add(textField);
        return panel;
    }

    private boolean isInteger(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isDouble(String text) {
        try {
            Double.parseDouble(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
            //bghjk
        }
    }

    
    
    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Update Medication");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(200, 200);  

            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension screenSize = toolkit.getScreenSize();
            int screenWidth = screenSize.width;
            int screenHeight = screenSize.height;
            int windowWidth = 300;
            int windowHeight = 200;

            int x = (screenWidth - windowWidth) / 2;
            int y = (screenHeight - windowHeight) / 2;
            frame.setLocation(x, y);

            frame.setSize(new Dimension(470, 250));
            frame.getContentPane().add(new UpdateMedication());
            frame.setVisible(true);
        });
    }
}
