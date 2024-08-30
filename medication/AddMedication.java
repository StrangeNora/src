package medication;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import exceptions.InvalidUserDetails;
import exceptions.ObjectAlreadyExistsException;

public class AddMedication extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField textField_0;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;

    public AddMedication(Medications m) {

        this.setBackground(new Color(0xA9BED2));

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 66, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 59, 0, 0, 0, 0, 0, 0, 20, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        JLabel lblNewLabel = new JLabel("Add A Medication");
        lblNewLabel.setFont(new Font("Traditional Arabic", Font.PLAIN, 22));
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.gridheight = 2;
        gbc_lblNewLabel.gridwidth = 11;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 2;
        gbc_lblNewLabel.gridy = 1;
        add(lblNewLabel, gbc_lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Code:");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1.gridx = 2;
        gbc_lblNewLabel_1.gridy = 3;
        add(lblNewLabel_1, gbc_lblNewLabel_1);

        textField_0 = new JTextField();
        textField_0.setBackground(new Color(0x698DB0));
        textField_0.setForeground(Color.WHITE); 
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.gridwidth = 6;
        gbc_textField.insets = new Insets(0, 0, 5, 5);
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.gridx = 3;
        gbc_textField.gridy = 3;
        add(textField_0, gbc_textField);
        textField_0.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("Name:");
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_2.gridx = 2;
        gbc_lblNewLabel_2.gridy = 4;
        add(lblNewLabel_2, gbc_lblNewLabel_2);

        textField_1 = new JTextField();
        textField_1.setBackground(new Color(0x698DB0));
        textField_1.setForeground(Color.WHITE); 
        textField_1.setColumns(10);
        GridBagConstraints gbc_textField_1 = new GridBagConstraints();
        gbc_textField_1.gridwidth = 6;
        gbc_textField_1.insets = new Insets(0, 0, 5, 5);
        gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_1.gridx = 3;
        gbc_textField_1.gridy = 4;
        add(textField_1, gbc_textField_1);

        JLabel lblNewLabel_3 = new JLabel("Dosage:");
        lblNewLabel_3.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
        gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_3.gridx = 2;
        gbc_lblNewLabel_3.gridy = 5;
        add(lblNewLabel_3, gbc_lblNewLabel_3);

        textField_2 = new JTextField();
        textField_2.setBackground(new Color(0x698DB0));
        textField_2.setForeground(Color.WHITE); 
        textField_2.setColumns(10);
        GridBagConstraints gbc_textField_2 = new GridBagConstraints();
        gbc_textField_2.gridwidth = 6;
        gbc_textField_2.insets = new Insets(0, 0, 5, 5);
        gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_2.gridx = 3;
        gbc_textField_2.gridy = 5;
        add(textField_2, gbc_textField_2);

        JLabel lblNewLabel_4 = new JLabel("Number Of Dose:");
        lblNewLabel_4.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
        gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_4.gridx = 2;
        gbc_lblNewLabel_4.gridy = 6;
        add(lblNewLabel_4, gbc_lblNewLabel_4);

        textField_3 = new JTextField();
        textField_3.setBackground(new Color(0x698DB0));
        textField_3.setForeground(Color.WHITE); 
        textField_3.setColumns(10);
        GridBagConstraints gbc_textField_3 = new GridBagConstraints();
        gbc_textField_3.gridwidth = 6;
        gbc_textField_3.insets = new Insets(0, 0, 5, 5);
        gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_3.gridx = 3;
        gbc_textField_3.gridy = 6;
        add(textField_3, gbc_textField_3);

        JButton btnNewButton = new JButton("Save");
        btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton.gridx = 11;
        gbc_btnNewButton.gridy = 8;
        add(btnNewButton, gbc_btnNewButton);

        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(validateFields()) {
                        JOptionPane.showMessageDialog(null, "Medication saved successfully.");
                    }
                } catch (InvalidUserDetails ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel panel = new JPanel();
        panel.setBackground(new Color(0xA9BED2));
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 14;
        gbc_panel.gridy = 9;
        add(panel, gbc_panel);
    }
//TODO addmeds
    private boolean validateFields() throws InvalidUserDetails  {
    	try {
		        if (textField_0.getText().isEmpty() || textField_1.getText().isEmpty() || textField_2.getText().isEmpty()
		        || textField_3.getText().isEmpty()) {
		            throw new InvalidUserDetails("All Fields Must Be Filled.");
		        }
		        if(!textField_0.getText().trim().matches("\\d+")) {
		            throw new InvalidUserDetails("Code Must Only Contain Numbers.");
		
		        }
		        if(!textField_3.getText().trim().matches("\\d+" )) {
		            throw new InvalidUserDetails("Number Of Doses Must Only Contain Numbers.");
		
		        }
		        if(!textField_2.getText().trim().matches("\\d+(\\.\\d+)?" )) {
		            throw new InvalidUserDetails("Dosage Must Only Contain Numbers.");
		
		        }
		        
    	}catch(InvalidUserDetails ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;

    	}catch(ObjectAlreadyExistsException o){
            JOptionPane.showMessageDialog(null, "Medication Already Exists!");

		}
    	return true;

        
    }

	

}
