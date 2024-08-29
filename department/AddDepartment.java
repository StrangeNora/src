package department;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import enums.Specialization;
import exceptions.InvalidUserDetails;
import exceptions.ObjectAlreadyExistsException;
import visit.AddVisit;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddDepartment extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_2;
	private JComboBox<Specialization>  specializationComboBox;

	/**
	 * Create the panel.
	 */
	public AddDepartment(Departments d) {
		
        this.setBackground(new Color(0xA9BED2));

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(0xA9BED2));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 10;
		gbc_panel_1.gridy = 0;
		add(panel_1, gbc_panel_1);
		
		JLabel lblNewLabel = new JLabel("Add a Department\r\n");
		lblNewLabel.setFont(new Font("Traditional Arabic", Font.BOLD, 22));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridheight = 2;
		gbc_lblNewLabel.gridwidth = 12;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(0xA9BED2));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 10;
		gbc_panel_2.gridy = 3;
		add(panel_2, gbc_panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Number:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 4;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBackground(new Color(0x698DB0)); 
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 9;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 4;
		gbc_textField.gridy = 4;
		add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1_2 = new JLabel("Location:");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		GridBagConstraints gbc_lblNewLabel_1_2 = new GridBagConstraints();
		gbc_lblNewLabel_1_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_2.gridx = 2;
		gbc_lblNewLabel_1_2.gridy = 5;
		add(lblNewLabel_1_2, gbc_lblNewLabel_1_2);
		
		textField_2 = new JTextField();
		textField_2.setBackground(new Color(0x698DB0)); 
		textField_2.setColumns(10);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.gridwidth = 9;
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 4;
		gbc_textField_2.gridy = 5;
		add(textField_2, gbc_textField_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Specialization:");
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		GridBagConstraints gbc_lblNewLabel_1_3 = new GridBagConstraints();
		gbc_lblNewLabel_1_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_3.gridx = 2;
		gbc_lblNewLabel_1_3.gridy = 6;
		add(lblNewLabel_1_3, gbc_lblNewLabel_1_3);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField.getText().trim().isEmpty() ||  textField_2.getText().trim().isEmpty()) {
						throw new InvalidUserDetails("All Fields Must Be Filled.");
					}
					if(!textField.getText().matches("\\d+")) {
						throw new InvalidUserDetails("Number Field Must Only Contain Numbers.");

					}
		            JOptionPane.showMessageDialog(null, "Department Added Successfully!");

				}catch(InvalidUserDetails ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
				}catch(ObjectAlreadyExistsException ec) {
					JOptionPane.showMessageDialog(null, ec.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

				}

			}

		});
		
		specializationComboBox = new JComboBox<>(); 
		specializationComboBox.setBackground(new Color(0x698DB0)); 
		specializationComboBox.setModel(new DefaultComboBoxModel<>(Specialization.values()));		
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 9;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 4;
		gbc_comboBox.gridy = 6;
		add(specializationComboBox, gbc_comboBox);
		btnNewButton.setBackground(new Color(0x698DB0));
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 13;
		gbc_btnNewButton.gridy = 9;
		add(btnNewButton, gbc_btnNewButton);
		
		

	}
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Add Department");
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

            frame.setSize(new Dimension(420, 280));
            frame.getContentPane().add(new AddDepartment(null));
            frame.setVisible(true);
        });
    }

}
