package medicalProblem;

import javax.swing.JPanel;


import java.awt.Frame;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JPanel;

import control.Hospital;
import enums.Role;
import model.*;
import panels.GenericListPanel;



public class MedicalProblems extends JPanel {


		private static final long serialVersionUID = 1L;
		private GenericListPanel<MedicalProblem> genericListPanel;
	    private DefaultListModel<MedicalProblem> listModel;
	    private Role userRole;

	    public MedicalProblems(Role userRole, String sectionName, DefaultListModel<MedicalProblem> listModel) {
	    	this.userRole = userRole;
	        this.listModel = listModel;
	        genericListPanel = new GenericListPanel<>(
	        		sectionName,
	        		listModel, 
	        		canRemove() ? this::removeMedicalProblemtFromHospital : null,
	        		canAdd() ? this::showAddMedicalProblemDialog : null,
	        		canUpdate() ? this::showUpdateMedicalProblemDialog : null
	        	);
	        loadMedicalProblemsFromHospital();
	    }

	    public JPanel getPanel() {
	        return genericListPanel.getPanel();
	    }

	    private void loadMedicalProblemsFromHospital() {
	        Hospital hospital = Hospital.getInstance();
	        HashMap<String, MedicalProblem> MedicalProblem = hospital.getMedicalProblems();
	        for (MedicalProblem v : MedicalProblem.values()) {
	            listModel.addElement(v);
	        }
	    }

	    public void refreshList() {
	        listModel.clear();
	        loadMedicalProblemsFromHospital();
	    }

	    private void removeMedicalProblemtFromHospital(MedicalProblem v) {
	        Hospital.getInstance().removeMedicalProblem(v);
	    }

	    private void showAddMedicalProblemDialog() {
	       AddMedicalProblem addMedicalProblem = new AddMedicalProblem(this);
	        JDialog dialog = new JDialog((Frame) null, "Add MedicalProblem", true);
	        dialog.getContentPane().add(addMedicalProblem);
	        dialog.pack();
	        dialog.setLocationRelativeTo(null);
	        dialog.setVisible(true);
	    }
	    private void showUpdateMedicalProblemDialog(MedicalProblem m) {
	    	UpdateMedicalProblem updateMedicalProblem = new UpdateMedicalProblem(this);
	        JDialog dialog = new JDialog((Frame) null, "Update MedicalProblem", true);
	        dialog.getContentPane().add(updateMedicalProblem);
	        dialog.pack();
	        dialog.setLocationRelativeTo(null);
	        dialog.setVisible(true);
	    }
	    
	    private boolean canAdd() {
	    	return userRole != Role.Nurse;
	    }
	    
	    private boolean canRemove() {
	    	return userRole == Role.Admin;
	    }
	    
	    private boolean canUpdate() {
	    	return userRole == Role.Admin;
	    }
	}

