package department;

import javax.swing.*;

import enums.Specialization;
import exceptions.FutureDateException;
import exceptions.InvalidUserDetails;
import exceptions.ObjectAlreadyExistsException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateDepartment extends JPanel {

    private static final long serialVersionUID = 1L;
    private CardLayout cardLayout;
    private JPanel cardsPanel;
    private JTextField nameField;
    private JTextField idField;
    private JTextField locationField;
    private JComboBox<Specialization> specializationComboBox;

    public UpdateDepartment() {
    	this.setBackground(new Color(0xA9BED2));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title Label
        JLabel lblTitle = new JLabel("Update Department");
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 22));
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        add(lblTitle, gbc);

        // ComboBox for options
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setBackground(new Color(0x698DB0)); 
        comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Select Option", "Name", "Manager ID", "Location", "Specialization"}));
        comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridy = 1; // Position the combo box in the second row
        add(comboBox, gbc);

        // Create and add cards for each option
        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);
        gbc.gridy = 2; // Position the cards panel in the third row
        gbc.weighty = 1.0; // Allow the panel to expand vertically
        gbc.fill = GridBagConstraints.BOTH;
        add(cardsPanel, gbc);

        createPanels();

        // Update Button
        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBackground(new Color(0x698DB0));
        btnUpdate.addActionListener(e -> {
            try {
                String selectedItem = (String) comboBox.getSelectedItem();
                
                if (selectedItem == "Select Option") {
                    throw new InvalidUserDetails("Please select an option to update.");
                }
                if(selectedItem == "Name") {
                	if(nameField.getText().trim().isEmpty()) {
                        throw new NullPointerException("This Field Cannot Be Empty.");

                	}
                }
                if(selectedItem == "Manager ID") {
                	if(idField.getText().trim().isEmpty()) {
                        throw new NullPointerException("This Field Cannot Be Empty.");
                	}
                	if(!idField.getText().matches("\\d+")) {
                        throw new InvalidUserDetails("This Field Must Only Contain Numbers.");
                	}
                }
                if(selectedItem == "Location") {
                	if(locationField.getText().trim().isEmpty()) {
                        throw new NullPointerException("This Field Cannot Be Empty.");
                	}
                }
                
                JOptionPane.showMessageDialog(null, "Department Updated Successfuly!");

                
            }catch(InvalidUserDetails ex) {
                showErrorMessage(ex.getMessage());

            }catch(ObjectAlreadyExistsException ex) {
                JOptionPane.showMessageDialog(null, "Department Already Exists!");
            }catch(NullPointerException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());

            }
                
        });
        
        btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        gbc.gridy = 3; // Position the update button in the fourth row
        gbc.weighty = 0; // Reset vertical weight
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        add(btnUpdate, gbc);

        // Action listener for combo box
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) comboBox.getSelectedItem();
                cardLayout.show(cardsPanel, selectedOption);
            }
        });
    }

    private void createPanels() {
        // Create panels for each option
        JPanel defaultPanel = new JPanel();
        defaultPanel.setBackground(new Color(0xA9BED2));
        cardsPanel.add(defaultPanel, "Select Option");

        JPanel namePanel = new JPanel(new GridBagLayout());
        namePanel.setBackground(new Color(0xA9BED2));
        nameField = new JTextField(20);
        nameField.setBackground(new Color(0x698DB0));
        addFieldToPanel(namePanel, "Name:", nameField);
        cardsPanel.add(namePanel, "Name");

        JPanel idPanel = new JPanel(new GridBagLayout());
        idPanel.setBackground(new Color(0xA9BED2));
        idField = new JTextField(20);
        idField.setBackground(new Color (0x698DB0));
        addFieldToPanel(idPanel, "Manager ID:", idField);
        cardsPanel.add(idPanel, "Manager ID");

        JPanel locationPanel = new JPanel(new GridBagLayout());
        locationPanel.setBackground(new Color(0xA9BED2));
        locationField = new JTextField(20);
        locationField.setBackground(new Color(0x698DB0));
        addFieldToPanel(locationPanel, "Location:", locationField);
        cardsPanel.add(locationPanel, "Location");

        JPanel specializationPanel = new JPanel(new GridBagLayout());
        specializationPanel.setBackground(new Color(0xA9BED2));
        specializationComboBox = new JComboBox<>(); 
		specializationComboBox.setBackground(new Color(0x698DB0)); 
		specializationComboBox.setModel(new DefaultComboBoxModel<>(Specialization.values()));		
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 9;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 4;
		gbc_comboBox.gridy = 6;
		add(specializationComboBox, gbc_comboBox);        specializationPanel.add(new JLabel("Specialization:"), createGbc(0, 0));
        specializationPanel.add(specializationComboBox, createGbc(1, 0));
        cardsPanel.add(specializationPanel, "Specialization");
    }

    private GridBagConstraints createGbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        return gbc;
    }

    private void addFieldToPanel(JPanel panel, String labelText, JTextField textField) {
        panel.add(new JLabel(labelText), createGbc(0, 0));
        panel.add(textField, createGbc(1, 0));
    }
    
    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Update Department");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(350, 200);
            frame.setLocationRelativeTo(null);
            frame.getContentPane().add(new UpdateDepartment());
            frame.setVisible(true);
        });
    }
}
