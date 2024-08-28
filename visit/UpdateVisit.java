package visit;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import com.toedter.calendar.JDateChooser;

import exceptions.FutureDateException;
import exceptions.InvalidUserDetails;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Insets;
import java.awt.Toolkit;
import java.util.Date;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class UpdateVisit extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JDateChooser startDateChooser;
    private JDateChooser endDateChooser;
    private JComboBox<String> medicalProblemsComboBox;
    private JComboBox<String> treatmentsComboBox;
    private JLabel lblNumber;
    private JLabel lblStartDate;
    private JLabel lblEndDate;
    private JLabel lblMedicalProblems;
    private JLabel lblTreatments;
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

    public UpdateVisit() {
        this.setBackground(new Color(0xA9BED2));
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
        gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
        setLayout(gridBagLayout);

        JLabel lblNewLabel = new JLabel("What Do You Want To Update?");
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.gridheight = 3;
        gbc_lblNewLabel.gridwidth = 12;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 3;
        gbc_lblNewLabel.gridy = 1;
        add(lblNewLabel, gbc_lblNewLabel);

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setBackground(new Color(0x698DB0));
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Number", "Start Date", "End Date", "Medical Problems", "Treatments"}));
        GridBagConstraints gbc_comboBox = new GridBagConstraints();
        gbc_comboBox.gridwidth = 3;
        gbc_comboBox.insets = new Insets(0, 0, 5, 5);
        gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox.gridx = 2;
        gbc_comboBox.gridy = 4;
        add(comboBox, gbc_comboBox);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBackground(new Color(0x698DB0));
        btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
        gbc_btnUpdate.insets = new Insets(0, 0, 0, 5);
        gbc_btnUpdate.gridx = 15;
        gbc_btnUpdate.gridy = 12;
        add(btnUpdate, gbc_btnUpdate);

        // Labels and input fields (initially hidden)
        lblNumber = new JLabel("Number:");
        GridBagConstraints gbc_lblNumber = new GridBagConstraints();
        gbc_lblNumber.anchor = GridBagConstraints.EAST;
        gbc_lblNumber.insets = new Insets(0, 0, 5, 5);
        gbc_lblNumber.gridx = 4;
        gbc_lblNumber.gridy = 7;
        add(lblNumber, gbc_lblNumber);
        lblNumber.setVisible(false);

        textField = new JTextField();
        textField.setBackground(new Color(0x698DB0));
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.gridwidth = 4;
        gbc_textField.insets = new Insets(0, 0, 5, 5);
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.gridx = 5;
        gbc_textField.gridy = 7;
        add(textField, gbc_textField);
        textField.setColumns(10);
        textField.setVisible(false);

        lblStartDate = new JLabel("Start Date:");
        GridBagConstraints gbc_lblStartDate = new GridBagConstraints();
        gbc_lblStartDate.anchor = GridBagConstraints.EAST;
        gbc_lblStartDate.insets = new Insets(0, 0, 5, 5);
        gbc_lblStartDate.gridx = 4;
        gbc_lblStartDate.gridy = 8;
        add(lblStartDate, gbc_lblStartDate);
        lblStartDate.setVisible(false);

        startDateChooser = new JDateChooser();
        startDateChooser.setBackground(new Color(0x698DB0));
        GridBagConstraints gbc_startDateChooser = new GridBagConstraints();
        gbc_startDateChooser.gridwidth = 9;
        gbc_startDateChooser.insets = new Insets(0, 0, 5, 5);
        gbc_startDateChooser.fill = GridBagConstraints.BOTH;
        gbc_startDateChooser.gridx = 5;
        gbc_startDateChooser.gridy = 8;
        add(startDateChooser, gbc_startDateChooser);
        startDateChooser.setVisible(false);
        JTextField dateTextField_1 = (JTextField) startDateChooser.getDateEditor().getUiComponent();
        dateTextField_1.setBackground(new Color(0x698DB0)); // Match the background color of other text fields
        dateTextField_1.setForeground(Color.WHITE); // Set the text color to white

        lblEndDate = new JLabel("End Date:");
        GridBagConstraints gbc_lblEndDate = new GridBagConstraints();
        gbc_lblEndDate.anchor = GridBagConstraints.EAST;
        gbc_lblEndDate.insets = new Insets(0, 0, 5, 5);
        gbc_lblEndDate.gridx = 4;
        gbc_lblEndDate.gridy = 9;
        add(lblEndDate, gbc_lblEndDate);
        lblEndDate.setVisible(false);

        endDateChooser = new JDateChooser();
        endDateChooser.setBackground(new Color(0x698DB0));
        GridBagConstraints gbc_endDateChooser = new GridBagConstraints();
        gbc_endDateChooser.gridwidth = 9;
        gbc_endDateChooser.insets = new Insets(0, 0, 5, 5);
        gbc_endDateChooser.fill = GridBagConstraints.BOTH;
        gbc_endDateChooser.gridx = 5;
        gbc_endDateChooser.gridy = 9;
        add(endDateChooser, gbc_endDateChooser);
        endDateChooser.setVisible(false);
        JTextField dateTextField = (JTextField) endDateChooser.getDateEditor().getUiComponent();
        dateTextField.setBackground(new Color(0x698DB0)); // Match the background color of other text fields
        dateTextField.setForeground(Color.WHITE); // Set the text color to white

        lblMedicalProblems = new JLabel("Medical Problems:");
        GridBagConstraints gbc_lblMedicalProblems = new GridBagConstraints();
        gbc_lblMedicalProblems.anchor = GridBagConstraints.EAST;
        gbc_lblMedicalProblems.insets = new Insets(0, 0, 5, 5);
        gbc_lblMedicalProblems.gridx = 4;
        gbc_lblMedicalProblems.gridy = 10;
        add(lblMedicalProblems, gbc_lblMedicalProblems);
        lblMedicalProblems.setVisible(false);

        medicalProblemsComboBox = new JComboBox<>();
        medicalProblemsComboBox.setBackground(new Color(0x698DB0));
        medicalProblemsComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Problem 1", "Problem 2", "Problem 3"}));
        GridBagConstraints gbc_medicalProblemsComboBox = new GridBagConstraints();
        gbc_medicalProblemsComboBox.gridwidth = 4;
        gbc_medicalProblemsComboBox.insets = new Insets(0, 0, 5, 5);
        gbc_medicalProblemsComboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_medicalProblemsComboBox.gridx = 5;
        gbc_medicalProblemsComboBox.gridy = 10;
        add(medicalProblemsComboBox, gbc_medicalProblemsComboBox);
        medicalProblemsComboBox.setVisible(false);

        lblTreatments = new JLabel("Treatments:");
        GridBagConstraints gbc_lblTreatments = new GridBagConstraints();
        gbc_lblTreatments.anchor = GridBagConstraints.EAST;
        gbc_lblTreatments.insets = new Insets(0, 0, 5, 5);
        gbc_lblTreatments.gridx = 4;
        gbc_lblTreatments.gridy = 11;
        add(lblTreatments, gbc_lblTreatments);
        lblTreatments.setVisible(false);

        treatmentsComboBox = new JComboBox<>();
        treatmentsComboBox.setBackground(new Color(0x698DB0));
        treatmentsComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Treatment 1", "Treatment 2", "Treatment 3"}));
        GridBagConstraints gbc_treatmentsComboBox = new GridBagConstraints();
        gbc_treatmentsComboBox.gridwidth = 4;
        gbc_treatmentsComboBox.insets = new Insets(0, 0, 5, 5);
        gbc_treatmentsComboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_treatmentsComboBox.gridx = 5;
        gbc_treatmentsComboBox.gridy = 11;
        add(treatmentsComboBox, gbc_treatmentsComboBox);
        treatmentsComboBox.setVisible(false);

        btnUpdate.addActionListener(e -> {
            try {
                String selectedItem = (String) comboBox.getSelectedItem();
                
                if (selectedItem == null || selectedItem.isEmpty()) {
                    throw new InvalidUserDetails("Please select an option to update.");
                }
                
                JTextField currentTextField;

                switch (selectedItem) {
                    case "Number":
                        currentTextField = textField;
                        if (currentTextField.getText().isEmpty()) {
                            throw new InvalidUserDetails("Field cannot be empty.");
                        }
                        if (!isInteger(currentTextField.getText())) {
                            throw new InvalidUserDetails("This Field must contain only numbers.");
                        }
                        break;
                    case "Start Date":
                        currentTextField = dateTextField_1;
                        if (currentTextField.getText().isEmpty()) {
                            throw new InvalidUserDetails("Field cannot be empty.");
                        }
                        if (startDateChooser.getDate().after(maxAllowedDate)) {
                            throw new FutureDateException(startDateChooser.getDate());
                        }
                        break;
                    case "End Date":
                        currentTextField = dateTextField;
                        if (currentTextField.getText().isEmpty()) {
                            throw new InvalidUserDetails("Field cannot be empty.");
                        }
                        if (endDateChooser.getDate().after(maxAllowedDate)) {
                            throw new FutureDateException(endDateChooser.getDate());
                        }
                        break;
                    case "Medical Problems":
                        String selectedMedicalProblem = (String) medicalProblemsComboBox.getSelectedItem();
                        if (selectedMedicalProblem == null || selectedMedicalProblem.isEmpty()) {
                            throw new InvalidUserDetails("Field cannot be empty.");
                        }
                        break;
                    case "Treatments":
                        String selectedTreatment = (String) treatmentsComboBox.getSelectedItem();
                        if (selectedTreatment == null || selectedTreatment.isEmpty()) {
                            throw new InvalidUserDetails("Field cannot be empty.");
                        }
                        break;
                }
                JOptionPane.showMessageDialog(null, "Medication updated successfully.");
            } catch (InvalidUserDetails ex) {
                showErrorMessage(ex.getMessage());
            } catch (FutureDateException ec) {
                JOptionPane.showMessageDialog(null, "Invalid Date Input.");
            }
        });

        
        // Action listener for combo box
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Hide all fields initially
                hideAllFields();
                
                // Show the selected field(s)
                switch (comboBox.getSelectedItem().toString()) {
                    case "Number":
                        lblNumber.setVisible(true);
                        textField.setVisible(true);
                        break;
                    case "Start Date":
                        lblStartDate.setVisible(true);
                        startDateChooser.setVisible(true);
                        break;
                    case "End Date":
                        lblEndDate.setVisible(true);
                        endDateChooser.setVisible(true);
                        break;
                    case "Medical Problems":
                        lblMedicalProblems.setVisible(true);
                        medicalProblemsComboBox.setVisible(true);
                        break;
                    case "Treatments":
                        lblTreatments.setVisible(true);
                        treatmentsComboBox.setVisible(true);
                        break;
                }
                // Refresh the panel
                SwingUtilities.updateComponentTreeUI(UpdateVisit.this);
            }
        });
    }
    
    private boolean isInteger(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }


    // Method to hide all fields
    private void hideAllFields() {
        lblNumber.setVisible(false);
        textField.setVisible(false);
        lblStartDate.setVisible(false);
        startDateChooser.setVisible(false);
        lblEndDate.setVisible(false);
        endDateChooser.setVisible(false);
        lblMedicalProblems.setVisible(false);
        medicalProblemsComboBox.setVisible(false);
        lblTreatments.setVisible(false);
        treatmentsComboBox.setVisible(false);
    }


	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Update Visit");
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

            frame.setSize(new Dimension(450, 250));
            frame.getContentPane().add(new UpdateVisit());
            frame.setVisible(true);
        });
    }


}
