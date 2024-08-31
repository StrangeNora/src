package medication;


import javax.swing.JPanel;

import Patient.UpdatePatient;

import java.awt.Frame;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import control.Hospital;
import enums.Role;
import model.*;
import panels.GenericListPanel;
import utils.UtilsMethods;



public class Medications extends SectionPanel<Medication> {


		private static final long serialVersionUID = 1L;
		
	    public Medications(Role userRole, String sectionName, DefaultListModel<Medication> listModel, JPanel quickLinksPanel) {
	    	super(userRole, sectionName, listModel, quickLinksPanel);
	    	this.initGenericListPanel(this::removeMedicationtFromHospital, this::showAddMedicationDialog, this::showUpdateMedicationDialog);
	    }
	    
	    private void removeMedicationtFromHospital(Medication v) {
	        Hospital.getInstance().removeMedication(v);
	    }

	    private void showAddMedicationDialog() {
	       AddMedication addMedication = new AddMedication(this);
	        JDialog dialog = new JDialog((Frame) null, "Add Medication", true);
	        dialog.getContentPane().add(addMedication);
	        dialog.pack();
	        dialog.setLocationRelativeTo(null);
	        dialog.setVisible(true);
	    }
	    private void showUpdateMedicationDialog(Medication m) {
	        UpdateMedication updateMedication = new UpdateMedication(this,m);
	        JDialog dialog = new JDialog((Frame) null, "Update Medication", true);
	        dialog.getContentPane().add(updateMedication);
	        dialog.pack();
	        dialog.setLocationRelativeTo(null);
	        dialog.setVisible(true);
	    }
	    
	    @Override
	    protected void load() {
	        Hospital hospital = Hospital.getInstance();
	        HashMap<Integer, Medication> Medication = hospital.getMedications();
	        for (Medication v : Medication.values()) {
	            listModel.addElement(v);
	        }
	    }
	    
	    protected boolean canAdd() {
	    	return userRole != Role.Nurse;
	    }
	    
	    protected boolean canRemove() {
	    	return userRole != Role.Nurse;
	    }
	    
	    protected boolean canUpdate() {
	    	return userRole != Role.Nurse;
	    }

		@Override
		public void initializeQuickPanelButtons() {
			quickLinksPanel.removeAll();
	    	quickLinksPanel.add(UtilsMethods.getRightPanelTitleLabel(UtilsMethods.QUICK_LINKS_TITLE));
	    	
	    	if(canAdd()) {
				JButton addButton = UtilsMethods.createPanelButton("Add Medication");
		    	addButton.addActionListener(e -> {
		    		showAddMedicationDialog();
		    	});
		    	quickLinksPanel.add(addButton);
	    	}
	    	
	    	quickLinksPanel.repaint();
		}

		@Override
		protected Object[][] getTable() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected String[] getColumns() {
			// TODO Auto-generated method stub
			return null;
		}
	}


