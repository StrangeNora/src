package treatment;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import exceptions.InvalidUserDetails;
import exceptions.ObjectAlreadyExistsException;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateTreatment extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField serialNumberField;
	private JTextField descriptionField;
	private JLabel serialNumberLabel;
	private JLabel descriptionLabel;
	private JButton updateButton;

	public UpdateTreatment() {

		this.setBackground(new Color(0xA9BED2));

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("What Do You Want To Update?");
		lblNewLabel.setFont(new Font("Traditional Arabic", Font.BOLD, 22));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridheight = 3;
		gbc_lblNewLabel.gridwidth = 12;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 2;
		add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Select:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 3;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 3;
		gbc_lblNewLabel_1.gridy = 6;
		add(lblNewLabel_1, gbc_lblNewLabel_1);

		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setBackground(new Color(0x698DB0)); 
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"", "Serial Number", "Description"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 5;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 7;
		gbc_comboBox.gridy = 6;
		add(comboBox, gbc_comboBox);

		serialNumberLabel = new JLabel("Serial Number:");
		serialNumberLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		GridBagConstraints gbc_serialNumberLabel = new GridBagConstraints();
		gbc_serialNumberLabel.anchor = GridBagConstraints.EAST;
		gbc_serialNumberLabel.gridwidth = 3;
		gbc_serialNumberLabel.insets = new Insets(0, 0, 5, 5);
		gbc_serialNumberLabel.gridx = 5;
		gbc_serialNumberLabel.gridy = 8;
		add(serialNumberLabel, gbc_serialNumberLabel);
		serialNumberLabel.setVisible(false);

		serialNumberField = new JTextField();
		serialNumberField.setForeground(Color.WHITE); // Set the text color to white
		serialNumberField.setBackground(new Color(0x698DB0)); 
		GridBagConstraints gbc_serialNumberField = new GridBagConstraints();
		gbc_serialNumberField.gridwidth = 4;
		gbc_serialNumberField.insets = new Insets(0, 0, 5, 5);
		gbc_serialNumberField.fill = GridBagConstraints.HORIZONTAL;
		gbc_serialNumberField.gridx = 9;
		gbc_serialNumberField.gridy = 8;
		add(serialNumberField, gbc_serialNumberField);
		serialNumberField.setColumns(10);
		serialNumberField.setVisible(false);

		descriptionLabel = new JLabel("Description:");
		descriptionLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		GridBagConstraints gbc_descriptionLabel = new GridBagConstraints();
		gbc_descriptionLabel.anchor = GridBagConstraints.EAST;
		gbc_descriptionLabel.gridwidth = 3;
		gbc_descriptionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_descriptionLabel.gridx = 5;
		gbc_descriptionLabel.gridy = 9;
		add(descriptionLabel, gbc_descriptionLabel);
		descriptionLabel.setVisible(false);

		descriptionField = new JTextField();
		descriptionField.setForeground(Color.WHITE);
		descriptionField.setBackground(new Color(0x698DB0)); 
		GridBagConstraints gbc_descriptionField = new GridBagConstraints();
		gbc_descriptionField.gridwidth = 4;
		gbc_descriptionField.insets = new Insets(0, 0, 5, 5);
		gbc_descriptionField.fill = GridBagConstraints.HORIZONTAL;
		gbc_descriptionField.gridx = 9;
		gbc_descriptionField.gridy = 9;
		add(descriptionField, gbc_descriptionField);
		descriptionField.setColumns(10);
		descriptionField.setVisible(false);

		updateButton = new JButton("Update");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

				}catch(InvalidUserDetails ex) {
					JOptionPane.showMessageDialog(UpdateTreatment.this, ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);

				}catch(ObjectAlreadyExistsException ec) {
					JOptionPane.showMessageDialog(UpdateTreatment.this, ec.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);

				}
			}
		});


		updateButton.setBackground(new Color(0x698DB0));
		updateButton.setFont(new Font("Traditional Arabic", Font.PLAIN, 15));
		GridBagConstraints gbc_updateButton = new GridBagConstraints();
		gbc_updateButton.gridwidth = 2;
		gbc_updateButton.insets = new Insets(0, 0, 5, 5);
		gbc_updateButton.gridx = 14;
		gbc_updateButton.gridy = 12;
		add(updateButton, gbc_updateButton);

		// Action listener to show/hide fields based on selection
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selected = (String) comboBox.getSelectedItem();
				if (selected.equals("Serial Number")) {
					serialNumberLabel.setVisible(true);
					serialNumberField.setVisible(true);
					descriptionLabel.setVisible(false);
					descriptionField.setVisible(false);
				} else if (selected.equals("Description")) {
					descriptionLabel.setVisible(true);
					descriptionField.setVisible(true);
					serialNumberLabel.setVisible(false);
					serialNumberField.setVisible(false);
				} else {
					serialNumberLabel.setVisible(false);
					serialNumberField.setVisible(false);
					descriptionLabel.setVisible(false);
					descriptionField.setVisible(false);
				}
			}
		});
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Update Treatment");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(470, 250);
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Dimension screenSize = toolkit.getScreenSize();
			int screenWidth = screenSize.width;
			int screenHeight = screenSize.height;
			int x = (screenWidth - frame.getWidth()) / 2;
			int y = (screenHeight - frame.getHeight()) / 2;
			frame.setLocation(x, y);

			frame.getContentPane().add(new UpdateTreatment());
			frame.setVisible(true);
		});
	}
}
