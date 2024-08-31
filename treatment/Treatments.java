package treatment;

import javax.swing.JPanel;


import java.awt.Frame;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import control.Hospital;
import department.AddDoctorToDepartment;
import enums.Role;
import medicalProblem.AddTreatmentToMedicalProblem;
import model.*;
import panels.GenericListPanel;
import staffMember.AddDepartmentToStaffMember;
import treatment.*;
import utils.UtilsMethods;




public class Treatments extends SectionPanel<Treatment> {
		private static final long serialVersionUID = 1L;
	    
	    public Treatments(Role userRole, String sectionName, DefaultListModel<Treatment> listModel, JPanel quickLinksPanel) {
	    	super(userRole, sectionName, listModel, quickLinksPanel);
	    	this.initGenericListPanel(this::removeTreatmentFromHospital, this::showAddTreatmentDialog, this::showUpdateTreatmenttDialog);
	    }

	    private void removeTreatmentFromHospital(Treatment t) {
	        Hospital.getInstance().removeTreatment(t);
	    }

	    private void showAddTreatmentDialog() {
	       AddTreatment addTreatment = new AddTreatment(this);
	        JDialog dialog = new JDialog((Frame) null, "Add Treatment", true);
	        dialog.getContentPane().add(addTreatment);
	        dialog.pack();
	        dialog.setLocationRelativeTo(null);
	        dialog.setVisible(true);
	    }
	    private void showUpdateTreatmenttDialog(Treatment t) {
	       UpdateTreatment updateTreatment = new UpdateTreatment(this,t);
	        JDialog dialog = new JDialog((Frame) null, "Update Treatment", true);
	        dialog.getContentPane().add(updateTreatment);
	        dialog.pack();
	        dialog.setLocationRelativeTo(null);
	        dialog.setVisible(true);
	    }
	    
	    protected void load() {
	        Hospital hospital = Hospital.getInstance();
	        HashMap<Integer, Treatment> treatment = hospital.getTreatments();
	        for (Treatment t : treatment.values()) {
	            listModel.addElement(t);
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
				JButton addButton = UtilsMethods.createPanelButton("Add Treatment");
		    	addButton.addActionListener(e -> {
		    		showAddTreatmentDialog();
		    	});
		    	quickLinksPanel.add(addButton);
		    	
		    	JButton addMedication = UtilsMethods.createPanelButton("Add Medication To Treatment");
		    	addMedication.addActionListener(e -> {
		    		Treatment selectedMember = getSelectedObject();
		    		if(selectedMember == null) {
		    			JOptionPane.showMessageDialog(null, "Please select one to add to");
		    			return;
		    		}
		    		AddMedicationToTreatment addMedicationToTreatment = new AddMedicationToTreatment(this, selectedMember);
			        JDialog dialog = new JDialog((Frame) null, "Add Medication To Treatment", true);
			        dialog.getContentPane().add(addMedicationToTreatment);
			        dialog.pack();
			        dialog.setLocationRelativeTo(null);
			        dialog.setVisible(true);
		    	});
		    	quickLinksPanel.add(addMedication);
		    	
		    	JButton addDoctor = UtilsMethods.createPanelButton("Add Doctor To Treatment");
		    	addDoctor.addActionListener(e -> {
		    		Treatment selectedMember = getSelectedObject();
		    		if(selectedMember == null) {
		    			JOptionPane.showMessageDialog(null, "Please select one to add to");
		    			return;
		    		}
		    		AddDoctorToTreatment addDoctorToTreatment = new AddDoctorToTreatment(this, selectedMember);
			        JDialog dialog = new JDialog((Frame) null, "Add Doctor To Treatment", true);
			        dialog.getContentPane().add(addDoctorToTreatment);
			        dialog.pack();
			        dialog.setLocationRelativeTo(null);
			        dialog.setVisible(true);
		    	});
		    	quickLinksPanel.add(addDoctor);
		    	
		    	JButton addMedicalProblem = UtilsMethods.createPanelButton("Add Medical Problem To Treatment");
		    	addMedicalProblem.addActionListener(e -> {
		    		Treatment selectedMember = getSelectedObject();
		    		if(selectedMember == null) {
		    			JOptionPane.showMessageDialog(null, "Please select one to add to");
		    			return;
		    		}
		    		AddMedicalProblemToTreatment addMedicalProblemToTreatment = new AddMedicalProblemToTreatment(this, selectedMember);
			        JDialog dialog = new JDialog((Frame) null, "Add Medical Problem To Treatment", true);
			        dialog.getContentPane().add(addMedicalProblemToTreatment);
			        dialog.pack();
			        dialog.setLocationRelativeTo(null);
			        dialog.setVisible(true);
		    	});
		    	quickLinksPanel.add(addMedicalProblem);
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
