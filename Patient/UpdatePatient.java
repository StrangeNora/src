package Patient;

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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.toedter.calendar.JDateChooser;

import control.Hospital;
import enums.BiologicalSex;
import enums.HealthFund;
import exceptions.FutureDateException;
import exceptions.InvalidUserDetails;
import exceptions.ObjectAlreadyExistsException;
import staffMember.UpdateStaffMemberPanel;

public class UpdatePatient extends JPanel {

	 private static final long serialVersionUID = 1L;
	    private JPanel inputPanel;
	    private JComboBox<String> attributeComboBox;
	    private JTextField idField;
	    private JTextField firstNameField;
	    private JTextField lastNameField;
	    private JTextField adressField;
	    private JTextField numberField;
	    private JTextField emailField;
	    private JRadioButton maleRadioButton;
	    private JRadioButton femaleRadioButton;
	    private ButtonGroup genderGroup;
	    private JDateChooser birthDateChooser;
	    private JRadioButton otherRadioButton;
	    private JComboBox<HealthFund> healthFundComboBox;
	    private BiologicalSex[] selectedSex;
	    private Patients patients;
	    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    private static Date maxAllowedDate;


	    
	    static {
	        try {
	            maxAllowedDate = sdf.parse("30/04/2024");
	        } catch (ParseException e) {
	            e.printStackTrace();
	            maxAllowedDate = new Date(); // Fallback to current date if parsing fails
	        }
	    }
	    
	    public UpdatePatient(Patients patients) {
	    	this.patients=patients;

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
	        attributeComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "ID", "First Name", "Last Name", "Address", "Phone Number", "Gender", "Biological Sex", "Email", "HealthFund", "Birthdate"}));

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

	        idField = new JTextField(10);
	        firstNameField = new JTextField(10);
	        lastNameField = new JTextField(10);
	        adressField = new JTextField(10);
	        numberField = new JTextField(10);
	        emailField = new JTextField(10);

	        JComponent[] components = {
	            birthDateChooser, idField, firstNameField, lastNameField,
	            adressField, numberField, emailField
	        };

	        for (JComponent component : components) {
	            component.setBackground(new Color(0x698DB0));
	            component.setForeground(Color.WHITE);
	        }

	        healthFundComboBox = new JComboBox<>();
	        healthFundComboBox.setModel(new DefaultComboBoxModel<>(HealthFund.values()));

	        JButton updateButton = new JButton("Update");
	        updateButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		
	        		try {
	        			String selectedItem = (String) attributeComboBox.getSelectedItem();
	        			
	        			if(selectedItem == null || selectedItem.isEmpty()) {
	        				throw new InvalidUserDetails("choose an attribute to update.");
	        			}
	        		     
	        			if(selectedItem == "ID") {
	        				
	        				if(idField.getText().trim().isEmpty())  {
	        					throw new InvalidUserDetails("Field Cannot Be Empty.");
	        				}
	        				if(!idField.getText().matches("\\d+")) {
	        					throw new InvalidUserDetails("Feild Must Only Contain Numbers");
	        				}
	        			}
	        			
	        			if (selectedItem == "Phone Number") {
	        				if(numberField.getText().trim().isEmpty()) {
	        					
	        				}
	        				if(!numberField.getText().matches("\\d+")) {
	        					throw new InvalidUserDetails("Feild Must Only Contain Numbers");
	        				}
	        			}
	        			if(selectedItem == "First Name") {
	        			
	        				if(firstNameField.getText().trim().isEmpty()) {
	        					throw new InvalidUserDetails ("Feild Cannot Be Empty.");
	        				}
	        			}
	        			if(selectedItem == "Last Name") {
	        				if(lastNameField.getText().trim().isEmpty() ) {
	        					throw new InvalidUserDetails ("Feild Cannot Be Empty.");

	        				}
	        			}
	        			if(selectedItem == "Address") {
	        				if(adressField.getText().trim().isEmpty()) {
	        					throw new InvalidUserDetails ("Feild Cannot Be Empty.");

	        				}
	        			}
	        			if(selectedItem == "Email") {
	        				if(emailField.getText().trim().isEmpty()) {
	        					throw new InvalidUserDetails ("Feild Cannot Be Empty.");

	        				}
	        			}
	        			if(selectedItem == "Gender" || selectedItem == "Biological Sex") {
	        				if(genderGroup.getSelection() == null || selectedSex == null) {
	        					throw new InvalidUserDetails("Field Cannot Be Empty");
	        				}
	        			}
	        			if(selectedItem == "Health Fund") {
	        				if(healthFundComboBox.getSelectedItem() == null){
	        					throw new InvalidUserDetails("Field Cannot Be Empty");
	        				}
	        			}
	        			if(selectedItem == "Birthdate") {
	        				Hospital.getInstance().getPatients().containsKey(p);
	        				if(birthDateChooser.getDate() == null) {
	        					throw new InvalidUserDetails("Field Cannot Be Empty");
	        				}
	        				if(birthDateChooser.getDate().after(maxAllowedDate)) {
	        					throw new FutureDateException(maxAllowedDate);
	        				}
	        			}
	        			
	                    JOptionPane.showMessageDialog(null, "Patient updated successfully.");

	        		}
	        		
	        		catch(InvalidUserDetails ex){
	                    showErrorMessage(ex.getMessage());
	        		}catch(FutureDateException ex) {
	                    showErrorMessage(ex.getMessage());

	        		}catch(ObjectAlreadyExistsException ex) {
	                    JOptionPane.showMessageDialog(null, "Patient Already Exists!");

	        		}
	        	}
	        });

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
	                addLabelAndTextField("ID:", idField, gbc, 0);
	                break;
	            case "First Name":
	                addLabelAndTextField("First Name:", firstNameField, gbc, 1);
	                break;
	            case "Last Name":
	                addLabelAndTextField("Last Name:", lastNameField, gbc, 2);
	                break;
	            case "Address":
	                addLabelAndTextField("Address:", adressField, gbc, 3);
	                break;
	            case "Phone Number":
	                addLabelAndTextField("Phone Number:", numberField, gbc, 4);
	                break;
	            case "Email":
	                addLabelAndTextField("Email:", emailField, gbc, 5);
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

	    
	    private void showErrorMessage(String message) {
	        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
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

