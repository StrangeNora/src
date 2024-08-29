package treatment;

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
import treatment.*;




public class Treatments extends JPanel {
		private static final long serialVersionUID = 1L;
		private GenericListPanel<Treatment> genericListPanel;
	    private DefaultListModel<Treatment> listModel;
	    private Role userRole;
	    
	    public Treatments(Role userRole, String sectionName, DefaultListModel<Treatment> listModel) {
	    	this.userRole = userRole;
	        this.listModel = listModel;
	        genericListPanel = new GenericListPanel<>(
	        		sectionName,
	        		listModel, 
	        		canRemove() ? this::removeTreatmentFromHospital : null,
	        		canAdd() ? this::showAddTreatmentDialog : null,
	        		canUpdate() ? this::showUpdateTreatmenttDialog : null
	        	);
	        loadTreatmentsFromHospital();
	    }

	    public JPanel getPanel() {
	        return genericListPanel.getPanel();
	    }

	    private void loadTreatmentsFromHospital() {
	        Hospital hospital = Hospital.getInstance();
	        HashMap<Integer, Treatment> treatment = hospital.getTreatments();
	        for (Treatment t : treatment.values()) {
	            listModel.addElement(t);
	        }
	    }

	    public void refreshList() {
	        listModel.clear();
	        loadTreatmentsFromHospital();
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
	   //     UpdateTreatment updateTreatment = new UpdateTreatment(this);
	        JDialog dialog = new JDialog((Frame) null, "Update Treatment", true);
	     //   dialog.getContentPane().add(updateTreatment);
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

