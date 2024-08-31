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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import control.Hospital;
import exceptions.InvalidUserDetails;
import exceptions.ObjectDoesNotExist;
import medication.CountMedication;
import model.Doctor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AppointANewManager extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public AppointANewManager() {
        this.setBackground(new Color(0xA9BED2));
        this.setPreferredSize(new Dimension(390,150));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(0xA9BED2));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridheight = 7;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 1;
		add(panel_1, gbc_panel_1);
		
		JLabel lblNewLabel = new JLabel("Manager ID:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 3;
		add(lblNewLabel, gbc_lblNewLabel);
		
		textField = new JTextField();
		textField.setBackground(new Color(0x698DB0));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 8;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 3;
		add(textField, gbc_textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Appoint");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField.getText().trim().isEmpty()) {
						throw new NullPointerException("Filed Cannot Be Empty.");
					}
					if(!textField.getText().matches("\\d+")) {
						throw new InvalidUserDetails("Field Must Only Contain Numbers.");
					}
					if(!Hospital.getInstance().getStaffMembers().containsKey(Integer.parseInt(textField.getText()))) {
						throw new ObjectDoesNotExist(null, "Doctor Does Not Exist", null);
					}
					if(!(Hospital.getInstance().getStaffMember(Integer.parseInt(textField.getText())) instanceof Doctor)) {
						throw new InvalidUserDetails("This StaffMember Is Not A Doctor");
					}
        			JOptionPane.showMessageDialog(null,"Doctor Appointed Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

				}catch(NullPointerException ex) {
        			JOptionPane.showMessageDialog(null, ex.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
				}catch(InvalidUserDetails ex) {
        			JOptionPane.showMessageDialog(null, ex.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
				}catch(ObjectDoesNotExist ex) {
        			JOptionPane.showMessageDialog(null, ex.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnNewButton.setBackground(new Color(0x698DB0));
		btnNewButton.setHorizontalAlignment(SwingConstants.RIGHT);
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 10;
		gbc_btnNewButton.gridy = 6;
		add(btnNewButton, gbc_btnNewButton);
		
		JPanel panel = new JPanel();
        panel.setBackground(new Color(0xA9BED2));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 14;
		gbc_panel.gridy = 9;
		add(panel, gbc_panel);

	}
	 
}
