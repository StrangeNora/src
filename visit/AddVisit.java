package visit;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;

import exceptions.FutureDateException;
import exceptions.InvalidUserDetails;
import exceptions.ObjectAlreadyExistsException;

import javax.swing.UIManager;
import javax.swing.DefaultComboBoxModel;


public class AddVisit extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JDateChooser startDateChooser;
	private JDateChooser endDateChooser;
	private JComboBox comboBox_1;
	private JComboBox comboBox;
	 private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	    private static final Date MAX_DATE;

	    static {
	        try {
	            MAX_DATE = DATE_FORMAT.parse("30/04/2024");
	        } catch (ParseException e) {
	            throw new RuntimeException("Date parsing error", e);
	        }
	    }


	

	/**
	 * Create the panel.
	 */
	public AddVisit(Visits v) {
		
        this.setBackground(new Color(0xA9BED2));

		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color (0xA9BED2));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 5;
		gbc_panel_1.gridy = 0;
		add(panel_1, gbc_panel_1);
		
		JLabel lblNewLabel = new JLabel("Add A Visit");
		lblNewLabel.setFont(new Font("Traditional Arabic", Font.BOLD | Font.ITALIC, 22));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridheight = 3;
		gbc_lblNewLabel.gridwidth = 14;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 5;
		gbc_lblNewLabel.gridy = 3;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Number:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 5;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 6;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBackground(new Color(0x698DB0)); // Set the background color of the text field
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 9;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 7;
		gbc_textField.gridy = 6;
		add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Patient:");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1.gridwidth = 5;
		gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1.gridx = 2;
		gbc_lblNewLabel_1_1.gridy = 7;
		add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);
		
		textField_1 = new JTextField();
		textField_1.setBackground(new Color(0x698DB0)); // Set the background color of the text field
		textField_1.setColumns(10);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 9;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 7;
		gbc_textField_1.gridy = 7;
		add(textField_1, gbc_textField_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Start Date:");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		GridBagConstraints gbc_lblNewLabel_1_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1_1.gridwidth = 5;
		gbc_lblNewLabel_1_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1_1.gridx = 2;
		gbc_lblNewLabel_1_1_1.gridy = 8;
		add(lblNewLabel_1_1_1, gbc_lblNewLabel_1_1_1);
        
		JDateChooser startDateChooser = new JDateChooser();
		startDateChooser.setBackground(new Color(0x698DB0)); // Set the background color of the text field
		startDateChooser.setForeground(Color.WHITE); // Set the text color to white
		startDateChooser.getCalendarButton().setPreferredSize(new Dimension(12, 12));
		GridBagConstraints gbc_startDateChooser = new GridBagConstraints();
		gbc_startDateChooser.gridwidth = 9;
		gbc_startDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_startDateChooser.fill = GridBagConstraints.BOTH;
		gbc_startDateChooser.gridx = 7;
		gbc_startDateChooser.gridy = 8;
		add(startDateChooser, gbc_startDateChooser);
		Date selectedDate = startDateChooser.getDate();
		JTextField dateTextField = (JTextField) startDateChooser.getDateEditor().getUiComponent();
		dateTextField.setBackground(new Color(0x698DB0)); 

		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("End Date:");
		lblNewLabel_1_1_1_1.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		GridBagConstraints gbc_lblNewLabel_1_1_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1_1_1.gridwidth = 5;
		gbc_lblNewLabel_1_1_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1_1_1.gridx = 2;
		gbc_lblNewLabel_1_1_1_1.gridy = 9;
		add(lblNewLabel_1_1_1_1, gbc_lblNewLabel_1_1_1_1);
		
		JDateChooser endDateChooser = new JDateChooser();
		endDateChooser.getCalendarButton().setPreferredSize(new Dimension(12, 12));
		endDateChooser.setForeground(Color.WHITE);
		endDateChooser.setBackground(UIManager.getColor("Button.light"));
		GridBagConstraints gbc_endDateChooser = new GridBagConstraints();
		gbc_endDateChooser.gridwidth = 9;
		gbc_endDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_endDateChooser.fill = GridBagConstraints.BOTH;
		gbc_endDateChooser.gridx = 7;
		gbc_endDateChooser.gridy = 9;
		add(endDateChooser, gbc_endDateChooser);
		Date selectedDate_1 = endDateChooser.getDate();
		JTextField dateTextField_1 = (JTextField) endDateChooser.getDateEditor().getUiComponent();
		dateTextField_1.setBackground(new Color(0x698DB0)); 

		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Medical Problems:");
		lblNewLabel_1_1_1_2.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		GridBagConstraints gbc_lblNewLabel_1_1_1_2 = new GridBagConstraints();
		gbc_lblNewLabel_1_1_1_2.gridwidth = 5;
		gbc_lblNewLabel_1_1_1_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1_1_2.gridx = 2;
		gbc_lblNewLabel_1_1_1_2.gridy = 10;
		add(lblNewLabel_1_1_1_2, gbc_lblNewLabel_1_1_1_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "one"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		comboBox.setBackground(new Color(0x698DB0)); 
		gbc_comboBox.gridwidth = 9;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 7;
		gbc_comboBox.gridy = 10;
		add(comboBox, gbc_comboBox);
		
		JLabel lblNewLabel_1_1_1_3 = new JLabel("Treatments:");
		lblNewLabel_1_1_1_3.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		GridBagConstraints gbc_lblNewLabel_1_1_1_3 = new GridBagConstraints();
		gbc_lblNewLabel_1_1_1_3.gridwidth = 5;
		gbc_lblNewLabel_1_1_1_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1_1_3.gridx = 2;
		gbc_lblNewLabel_1_1_1_3.gridy = 11;
		add(lblNewLabel_1_1_1_3, gbc_lblNewLabel_1_1_1_3);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {" ", "two"}));
		comboBox_1.setBackground(new Color(0x698DB0)); 
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.gridwidth = 9;
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 7;
		gbc_comboBox_1.gridy = 11;
		add(comboBox_1, gbc_comboBox_1);
		
		JButton btnNewButton = new JButton("Save\r\n");
		btnNewButton.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 16;
		gbc_btnNewButton.gridy = 12;
		add(btnNewButton, gbc_btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(textField.getText().trim().isEmpty() || textField_1.getText().trim().isEmpty() || startDateChooser.getDate()==null 
                       || endDateChooser.getDate()==null || comboBox.getSelectedItem()==null || comboBox_1.getSelectedItem()==null) {
                    	throw new InvalidUserDetails("All Fields Must Be Filled.");
                    }
                    if (!textField.getText().matches("\\d+")) {
                        throw new InvalidUserDetails("Number Field Must Only Contain Numbers.");
                    }
                    if(startDateChooser.getDate().after(MAX_DATE) || endDateChooser.getDate().after(MAX_DATE)) {
                    	throw new FutureDateException(MAX_DATE);
                    }

                } catch (InvalidUserDetails ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                catch(FutureDateException ec) {
                    JOptionPane.showMessageDialog(null, "Invalid Date Input.");

                }
                JOptionPane.showMessageDialog(null, "Visit Added Successfully!.");
            }
        });
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color (0xA9BED2));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 17;
		gbc_panel.gridy = 13;
		add(panel, gbc_panel);

	}
	
	
    

	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Add Visit");
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

            frame.setSize(new Dimension(500, 300));
            frame.getContentPane().add(new AddVisit(null));
            frame.setVisible(true);
        });
    }
}