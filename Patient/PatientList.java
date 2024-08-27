package Patient;
import model.*;

import control.*;
import enums.*;
import exceptions.ObjectAlreadyExistsException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class PatientList {
	private JPanel panel;
	private DefaultListModel<Patient> listModel;
	private JTextField searchField;
	private JList<Patient> list;

	public PatientList(String sectionName, DefaultListModel<Patient> listModel) {
		this.listModel = listModel;
		panel = new JPanel(new BorderLayout());
		JPanel searchPanel = new JPanel();
		searchField = new JTextField(15);
		JButton searchButton = new JButton("Search");
		JButton addButton = new JButton("Add");

		searchPanel.add(searchField);
		searchPanel.add(searchButton);
		searchPanel.add(addButton);

		// List and scroll pane
		list = new JList<>(listModel);
		JScrollPane listScrollPane = new JScrollPane(list);

		panel.add(searchPanel, BorderLayout.NORTH);
		panel.add(listScrollPane, BorderLayout.CENTER);

		// Create the popup menu and the "Remove" item
		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem removeMenuItem = new JMenuItem("Remove");
		popupMenu.add(removeMenuItem);

		// Add action listener to "Remove" menu item
		removeMenuItem.addActionListener(e -> {
			int selectedIndex = list.getSelectedIndex();
			if (selectedIndex != -1) {
				Patient selectedPatient = listModel.getElementAt(selectedIndex);
		        Hospital.getInstance().removePatient(selectedPatient);
				listModel.remove(selectedIndex);
				 JOptionPane.showMessageDialog(null, "Patient Removed Successfully");
				 
				
			}
		});

		// Add mouse listener to show popup menu on right-click
		list.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				showPopup(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				showPopup(evt);
			}

			private void showPopup(java.awt.event.MouseEvent evt) {
				if (evt.isPopupTrigger()) { // This is true for right-clicks
					int index = list.locationToIndex(evt.getPoint());
					list.setSelectedIndex(index); // Select the item that was right-clicked
					popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
				}
			}
		});

		// Add action listener for the "Add" button
		/**addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddPatient addPatient = new AddPatient(PatientList.this);
                JDialog dialog = new JDialog((Frame) null, "Add Patient", true);
                dialog.getContentPane().add(addPatient);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);}
		});**/

		// Add action listener for the "Search" button
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String searchText = searchField.getText().trim();
				if (listModel.isEmpty()) {
					JOptionPane.showMessageDialog(null, "The List Is Empty At The Moment", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				boolean found = false;
				for (int i = 0; i < listModel.size(); i++) {
					Patient patient = listModel.get(i);
					if (patient.toString().toLowerCase().contains(searchText.toLowerCase())) {
						list.ensureIndexIsVisible(i);
						list.setSelectedIndex(i);
						list.setSelectionBackground(Color.decode("#7F9DBB")); // Use the desired color
						found = true;
						break;
					}
				}

				if (!found) {
					JOptionPane.showMessageDialog(null, "The Patient Does Not Exist", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		loadPatientsFromHospital();
	}


	public JPanel getPanel() {
		return panel;
	}
	private void loadPatientsFromHospital() {
		Hospital hospital = Hospital.getInstance();
		HashMap<Integer, Patient> patients = hospital.getPatients();
		for (Patient patient : patients.values()) {
			listModel.addElement(patient);
		}

	}
	public void  refreshList() {
		listModel.clear();
		loadPatientsFromHospital();
	}
}
