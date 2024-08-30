package medicalProblem;

import javax.swing.JPanel;


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



public class MedicalProblems extends SectionPanel<MedicalProblem> {


		private static final long serialVersionUID = 1L;

	    public MedicalProblems(Role userRole, String sectionName, DefaultListModel<MedicalProblem> listModel, JPanel quickLinksPanel) {
	    	super(userRole, sectionName, listModel, quickLinksPanel);
	    	this.initGenericListPanel(this::removeMedicalProblemtFromHospital, this::showAddMedicalProblemDialog, this::showUpdateMedicalProblemDialog);
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
	    	UpdateMedicalProblem updateMedicalProblem = new UpdateMedicalProblem(this,m);
	        JDialog dialog = new JDialog((Frame) null, "Update MedicalProblem", true);
	        dialog.getContentPane().add(updateMedicalProblem);
	        dialog.pack();
	        dialog.setLocationRelativeTo(null);
	        dialog.setVisible(true);
	    }
	    
	    protected void load() {
	        Hospital hospital = Hospital.getInstance();
	        HashMap<String, MedicalProblem> MedicalProblem = hospital.getMedicalProblems();
	        for (MedicalProblem v : MedicalProblem.values()) {
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
				JButton addButton = UtilsMethods.createPanelButton("Add Medical Problem");
		    	addButton.addActionListener(e -> {
		    		showAddMedicalProblemDialog();
		    	});
		    	quickLinksPanel.add(addButton);
	    	}
		}
	}

