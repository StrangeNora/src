package treatment;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import department.AddDepartment;
import exceptions.InvalidUserDetails;
import exceptions.ObjectAlreadyExistsException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddTreatment extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public AddTreatment(Treatments t) {
		
        this.setBackground(new Color(0xA9BED2));

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Add a Treatment");
		lblNewLabel.setFont(new Font("Traditional Arabic", Font.BOLD, 22));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridheight = 2;
		gbc_lblNewLabel.gridwidth = 12;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(0xA9BED2));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridheight = 11;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 1;
		add(panel_1, gbc_panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Serial Number:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 3;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 6;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBackground(new Color(0x698DB0)); 
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 6;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 6;
		gbc_textField.gridy = 6;
		add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Description:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.gridwidth = 3;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 8;
		add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBackground(new Color(0x698DB0)); 
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 6;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 6;
		gbc_textField_1.gridy = 8;
		add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					if(textField.getText().trim().isEmpty() || textField_1.getText().trim().isEmpty()) {
						throw new InvalidUserDetails("All Fields Must Be Filled.");
					}
					if(!textField.getText().matches("\\d+")) {
						throw new InvalidUserDetails("Serial Number Field Must Only Contain Numbers.");
					}
					
					JOptionPane.showMessageDialog(null, "Treatment Added Successfully!");

					
				}catch(InvalidUserDetails ec) {
					JOptionPane.showMessageDialog(null, ec.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					
				}catch(ObjectAlreadyExistsException ex) {
        			JOptionPane.showMessageDialog(AddTreatment.this, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		btnNewButton.setBackground(new Color(0x698DB0));
		btnNewButton.setFont(new Font("Traditional Arabic", Font.PLAIN, 15));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 13;
		gbc_btnNewButton.gridy = 11;
		add(btnNewButton, gbc_btnNewButton);
		
		JPanel panel = new JPanel();
        panel.setBackground(new Color(0xA9BED2));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 14;
		gbc_panel.gridy = 12;
		add(panel, gbc_panel);

	}
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Add Treatment");
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
            frame.getContentPane().add(new AddTreatment(null));
            frame.setVisible(true);
        });
    }

}
